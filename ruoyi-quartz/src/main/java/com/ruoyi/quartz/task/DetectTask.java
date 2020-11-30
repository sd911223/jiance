package com.ruoyi.quartz.task;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.BusinessDetect;
import com.ruoyi.system.mapper.BusinessDetectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component("detectTask")
public class DetectTask {
    private static final Logger log = LoggerFactory.getLogger(DetectTask.class);
    @Autowired
    BusinessDetectMapper businessDetectMapper;

    public void detectNoParams() {
        log.info("***********************开始执行检测机构定时任务*****************");
        BusinessDetect businessDetect = new BusinessDetect();
        businessDetect.setIsEffective("effective");
        businessDetect.setReserved1("Nexpired");
        List<BusinessDetect> businessDetects = businessDetectMapper.selectBusinessDetectList(businessDetect);
        if (!businessDetects.isEmpty()) {
            businessDetects.forEach(e -> {
                Date detectData = e.getDetectData();
                int days = DateUtils.differentDaysByMillisecond(detectData, new Date());
                log.info("====================检测过期机构{},天数{}", e.getDetectDepartment(), days);
                if (days >= e.getExamineDays()) {
                    e.setReserved1("expired");
                    businessDetectMapper.updateBusinessDetect(e);
                }
            });
        }
    }
}
