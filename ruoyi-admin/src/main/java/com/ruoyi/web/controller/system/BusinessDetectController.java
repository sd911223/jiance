package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.BusinessDetect;
import com.ruoyi.system.service.IBusinessDetectService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
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
                if (("expired").equals(list.get(i).getReserved1())) {
                    list.get(i).setReserved1("过期");
                }
                if ((("Nexpired").equals(list.get(i).getReserved1())) || (("").equals(list.get(i).getReserved1()))) {
                    list.get(i).setReserved1("未过期");
                }
            }
        }

        return getDataTable(list);
    }

    /**
     * 导出【商户检测】列表
     */

    @GetMapping("/download")
    public void download(@RequestParam("id") Long id, HttpServletResponse response) throws IOException {
        try {
            Resource resource = new ClassPathResource("static/poi/" + id + ".xlsx");
            File file = resource.getFile();
            String filename = resource.getFilename();
            InputStream inputStream = new FileInputStream(file);
            //强制下载不打开
            response.setContentType("application/force-download");
            OutputStream out = response.getOutputStream();
            //使用URLEncoder来防止文件名乱码或者读取错误
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(filename, "UTF-8"));
            int b = 0;
            byte[] buffer = new byte[1000000];
            while (b != -1) {
                b = inputStream.read(buffer);
                if (b != -1) out.write(buffer, 0, b);
            }
            inputStream.close();
            out.close();
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

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
    @Log(title = "【新增商户检测】", businessType = BusinessType.INSERT)
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
     * 修改【商户检测】
     */
    @GetMapping("/edit1")
    public String edit1(@RequestParam("id") Long id, ModelMap mmap) {
        BusinessDetect businessDetect = businessDetectService.selectBusinessDetectById(id);
        SimpleDateFormat sdf = new SimpleDateFormat(" yyyy-MM-dd HH:mm:ss ");
        String format = sdf.format(businessDetect.getDetectData());
        businessDetect.setReserved2(format);
        mmap.put("businessDetect", businessDetect);
        return prefix + "/qrcode";
    }

    /**
     * 修改保存【商户检测】
     */
    @RequiresPermissions("system:detect:edit")
    @Log(title = "【修改保存商户检测】", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BusinessDetect businessDetect) {
        return toAjax(businessDetectService.updateBusinessDetect(businessDetect));
    }

    /**
     * 删除【商户检测】
     */
    @RequiresPermissions("system:detect:remove")
    @Log(title = "【删除商户检测】", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(businessDetectService.deleteBusinessDetectByIds(ids));
    }
}
