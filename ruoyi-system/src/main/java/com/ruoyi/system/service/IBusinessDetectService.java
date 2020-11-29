package com.ruoyi.system.service;

import com.ruoyi.system.domain.BusinessDetect;

import java.util.List;

/**
 * 【请填写功能名称】Service接口
 *
 * @author ruoyi
 * @date 2020-11-29
 */
public interface IBusinessDetectService {
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    BusinessDetect selectBusinessDetectById(Long id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param businessDetect 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    List<BusinessDetect> selectBusinessDetectList(BusinessDetect businessDetect);

    /**
     * 新增【请填写功能名称】
     *
     * @param businessDetect 【请填写功能名称】
     * @return 结果
     */
    int insertBusinessDetect(BusinessDetect businessDetect);

    /**
     * 修改【请填写功能名称】
     *
     * @param businessDetect 【请填写功能名称】
     * @return 结果
     */
    int updateBusinessDetect(BusinessDetect businessDetect);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteBusinessDetectByIds(String ids);

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    int deleteBusinessDetectById(Long id);

    /**
     * 导入ex
     *
     * @param userList
     * @param updateSupport
     * @return
     */
    String importBusinessDetect(List<BusinessDetect> userList, boolean updateSupport);
}
