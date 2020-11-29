package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.BusinessDetect;
import com.ruoyi.system.service.IBusinessDetectService;
import com.ruoyi.web.controller.demo.domain.UserOperateModel;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * 【商户检测】Controller
 *
 * @author ruoyi
 * @date 2020-11-29
 */
@Controller
@RequestMapping("/system/detect")
public class BusinessDetectController extends BaseController {
    private String prefix = "system/detect";

    @Autowired
    private IBusinessDetectService businessDetectService;

    @RequiresPermissions("system:detect:view")
    @GetMapping()
    public String detect() {
        return prefix + "/detect";
    }

    /**
     * 查询【商户检测】列表
     */
    @RequiresPermissions("system:detect:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BusinessDetect businessDetect) {
        startPage();
        businessDetect.setIsEffective("effective");
        List<BusinessDetect> list = businessDetectService.selectBusinessDetectList(businessDetect);
        //判断是否过期
        if (!list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                Date detectData = list.get(i).getDetectData();
                int days = DateUtils.differentDaysByMillisecond(detectData, new Date());
                System.out.println(days);
                if (days >= list.get(i).getExamineDays()) {
                    list.get(i).setReserved1("expired");
                }
            }
        }

        return getDataTable(list);
    }

    /**
     * 导出【商户检测】列表
     */
    @RequiresPermissions("system:detect:export")
    @Log(title = "【商户检测】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BusinessDetect businessDetect) {
        List<BusinessDetect> list = businessDetectService.selectBusinessDetectList(businessDetect);
        ExcelUtil<BusinessDetect> util = new ExcelUtil<BusinessDetect>(BusinessDetect.class);
        return util.exportExcel(list, "detect");
    }

    /**
     * 导入【商户检测】列表
     */
    @PostMapping("/importExport")
    @RequiresPermissions("system:detect:import")
    @ResponseBody
    public AjaxResult implExport(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<BusinessDetect> util = new ExcelUtil<BusinessDetect>(BusinessDetect.class);
        List<BusinessDetect> userList = util.importExcel(file.getInputStream());
        String message = businessDetectService.importBusinessDetect(userList, updateSupport);
        return AjaxResult.success(message);
    }

    /**
     * 新增【商户检测】
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存【商户检测】
     */
    @RequiresPermissions("system:detect:add")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BusinessDetect businessDetect) {
        businessDetect.setIsEffective("effective");
        return toAjax(businessDetectService.insertBusinessDetect(businessDetect));
    }

    /**
     * 修改【商户检测】
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        BusinessDetect businessDetect = businessDetectService.selectBusinessDetectById(id);
        mmap.put("businessDetect", businessDetect);
        return prefix + "/edit";
    }

    /**
     * 修改保存【商户检测】
     */
    @RequiresPermissions("system:detect:edit")
    @Log(title = "【商户检测】", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BusinessDetect businessDetect) {
        return toAjax(businessDetectService.updateBusinessDetect(businessDetect));
    }

    /**
     * 删除【商户检测】
     */
    @RequiresPermissions("system:detect:remove")
    @Log(title = "【商户检测】", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(businessDetectService.deleteBusinessDetectByIds(ids));
    }
}
