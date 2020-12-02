package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.BusinessDetect;
import com.ruoyi.system.mapper.BusinessDetectMapper;
import com.ruoyi.system.service.IBusinessDetectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author ruoyi
 * @date 2020-11-29
 */
@Service
public class BusinessDetectServiceImpl implements IBusinessDetectService {
    @Autowired
    private BusinessDetectMapper businessDetectMapper;

    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public BusinessDetect selectBusinessDetectById(Long id) {
        return businessDetectMapper.selectBusinessDetectById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param businessDetect 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<BusinessDetect> selectBusinessDetectList(BusinessDetect businessDetect) {
        return businessDetectMapper.selectBusinessDetectList(businessDetect);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param businessDetect 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertBusinessDetect(BusinessDetect businessDetect) {
        return businessDetectMapper.insertBusinessDetect(businessDetect);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param businessDetect 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateBusinessDetect(BusinessDetect businessDetect) {
        return businessDetectMapper.updateBusinessDetect(businessDetect);
    }

    /**
     * 删除【请填写功能名称】对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteBusinessDetectByIds(String ids) {
        return businessDetectMapper.deleteBusinessDetectByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteBusinessDetectById(Long id) {
        return businessDetectMapper.deleteBusinessDetectById(id);
    }

    @Override
    public String importBusinessDetect(List<BusinessDetect> userList, boolean updateSupport) {

        return this.importUser(userList, true);
    }

    /**
     * 导入用户数据
     *
     * @param userList        用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    public String importUser(List<BusinessDetect> userList, Boolean isUpdateSupport) {
        if (StringUtils.isNull(userList) || userList.size() == 0) {
            throw new BusinessException("导入用户数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (BusinessDetect businessDetect : userList) {
            businessDetect.setIsEffective("effective");
            try {
                // 验证是否存在这个用户
                BusinessDetect detect = new BusinessDetect();
                detect.setDetectAddress(businessDetect.getDetectAddress().trim());
                List<BusinessDetect> list = businessDetectMapper.selectBusinessDetectList(detect);
                if (list.isEmpty()) {
                    businessDetectMapper.insertBusinessDetect(businessDetect);
                } else {
                    businessDetect.setId(list.get(0).getId());
                    businessDetectMapper.updateBusinessDetect(businessDetect);
                }

            } catch (Exception e) {
                failureNum++;
                String msg = "<br/>" + failureNum + "、账号 " + businessDetect.getExamineDays() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
            }
        }
        if (failureNum > 0) {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new BusinessException(failureMsg.toString());
        } else {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }
}
