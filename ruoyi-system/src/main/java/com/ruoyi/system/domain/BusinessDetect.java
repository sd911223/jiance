package com.ruoyi.system.domain;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 business_detect
 * 
 * @author ruoyi
 * @date 2020-11-29
 */
public class BusinessDetect extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /** 检测地址 */
    @Excel(name = "检测地址")
    private String detectAddress;

    /** 检测部门 */
    @Excel(name = "检测部门")
    private String detectDepartment;

    /** 检测结果 */
    @Excel(name = "检测结果")
    private String detectResult;

    /** 检测时间 */
    @Excel(name = "检测时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date detectData;

    /** 检测机构 */
    @Excel(name = "检测机构")
    private String detectAgency;

    /** 检验天数 */
    @Excel(name = "检验天数")
    private Long examineDays;

    /** 是否有效 effective:有效/invalid:无效 */
//    @Excel(name = "是否有效 effective:有效/invalid:无效")
    private String isEffective;

    /** 预留1 */
    @Excel(name = "预留1")
    private String reserved1;

    /** 预留2 */
    @Excel(name = "预留2")
    private String reserved2;

    /** 预留3 */
//    @Excel(name = "预留3")
    private String reserved3;

    /** 预留4 */
//    @Excel(name = "预留4")
    private String reserved4;

    /** 预留5 */
//    @Excel(name = "预留5")
    private String reserved5;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setDetectAddress(String detectAddress) 
    {
        this.detectAddress = detectAddress;
    }

    public String getDetectAddress() 
    {
        return detectAddress;
    }
    public void setDetectDepartment(String detectDepartment) 
    {
        this.detectDepartment = detectDepartment;
    }

    public String getDetectDepartment() 
    {
        return detectDepartment;
    }
    public void setDetectResult(String detectResult) 
    {
        this.detectResult = detectResult;
    }

    public String getDetectResult() 
    {
        return detectResult;
    }
    public void setDetectData(Date detectData) 
    {
        this.detectData = detectData;
    }

    public Date getDetectData() 
    {
        return detectData;
    }
    public void setDetectAgency(String detectAgency) 
    {
        this.detectAgency = detectAgency;
    }

    public String getDetectAgency() 
    {
        return detectAgency;
    }
    public void setExamineDays(Long examineDays) 
    {
        this.examineDays = examineDays;
    }

    public Long getExamineDays() 
    {
        return examineDays;
    }
    public void setIsEffective(String isEffective) 
    {
        this.isEffective = isEffective;
    }

    public String getIsEffective() 
    {
        return isEffective;
    }
    public void setReserved1(String reserved1) 
    {
        this.reserved1 = reserved1;
    }

    public String getReserved1() 
    {
        return reserved1;
    }
    public void setReserved2(String reserved2) 
    {
        this.reserved2 = reserved2;
    }

    public String getReserved2() 
    {
        return reserved2;
    }
    public void setReserved3(String reserved3) 
    {
        this.reserved3 = reserved3;
    }

    public String getReserved3() 
    {
        return reserved3;
    }
    public void setReserved4(String reserved4) 
    {
        this.reserved4 = reserved4;
    }

    public String getReserved4() 
    {
        return reserved4;
    }
    public void setReserved5(String reserved5) 
    {
        this.reserved5 = reserved5;
    }

    public String getReserved5() 
    {
        return reserved5;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("detectAddress", getDetectAddress())
            .append("detectDepartment", getDetectDepartment())
            .append("detectResult", getDetectResult())
            .append("detectData", getDetectData())
            .append("detectAgency", getDetectAgency())
            .append("examineDays", getExamineDays())
            .append("isEffective", getIsEffective())
            .append("reserved1", getReserved1())
            .append("reserved2", getReserved2())
            .append("reserved3", getReserved3())
            .append("reserved4", getReserved4())
            .append("reserved5", getReserved5())
            .toString();
    }
}
