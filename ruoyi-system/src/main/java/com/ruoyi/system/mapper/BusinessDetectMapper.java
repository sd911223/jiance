package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.BusinessDetect;

import java.util.List;

/**
 * 【商户检测】Mapper接口
 *
 * @author ruoyi
 * @date 2020-11-29
 */
public interface BusinessDetectMapper {
    /**
     * 查询【商户检测】
     *
     * @param id 【主键】ID
     * @return 【商户检测】
     */
    BusinessDetect selectBusinessDetectById(Long id);

    /**
     * 查询【商户检测】列表
     *
     * @param businessDetect 【有效的】
     * @return 【商户检测】集合
     */
    List<BusinessDetect> selectBusinessDetectList(BusinessDetect businessDetect);

    /**
     * 新增【商户检测】
     *
     * @param businessDetect 【商户检测】
     * @return 结果
     */
    int insertBusinessDetect(BusinessDetect businessDetect);

    /**
     * 修改【商户检测】
     *
     * @param businessDetect 【商户检测】
     * @return 结果
     */
    int updateBusinessDetect(BusinessDetect businessDetect);

    /**
     * 删除【商户检测】
     *
     * @param id 【商户检测】ID
     * @return 结果
     */
    int deleteBusinessDetectById(Long id);

    /**
     * 批量删除【商户检测】
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteBusinessDetectByIds(String[] ids);
}
