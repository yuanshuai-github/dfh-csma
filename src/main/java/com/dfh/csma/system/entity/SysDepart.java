package com.dfh.csma.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.dfh.csma.system.annotation.Dict;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * <p>
 * 部门表
 * <p>
 *
 * @Author Steve
 * @Since 2019-01-22
 */
@Data
@TableName("sys_depart")
public class SysDepart implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(type = IdType.ID_WORKER_STR)
    private String id;
    /**
     * 父机构ID
     */
    private String parentId;
    /**
     * 机构/部门名称
     */
//	@Excel(name="机构/部门名称",width=15)
    private String departName;
    /**
     * 排序
     */
//	@Excel(name="排序",width=15)
    private Integer departOrder;
    /**
     * 机构编码
     */
//	@Excel(name="机构编码",width=15)
    private String orgCode;
    /**
     * 备注
     */
//	@Excel(name="备注",width=15)
    private String memo;
    /**
     * 状态（1启用，0不启用）
     */
//	@Excel(name="状态",width=15)
    @Dict(dicCode = "depart_status")
    private String status;
    /**
     * 删除状态（0，正常，1已删除）
     */
//	@Excel(name="删除状态",width=15)
    @Dict(dicCode = "del_flag")
    private String delFlag;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 创建日期
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     * 更新人
     */
    private String updateBy;
    /**
     * 更新日期
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 重写equals方法
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        SysDepart depart = (SysDepart) o;
        return Objects.equals(id, depart.id) &&
                Objects.equals(parentId, depart.parentId) &&
                Objects.equals(departName, depart.departName) &&
                Objects.equals(departOrder, depart.departOrder) &&
                Objects.equals(orgCode, depart.orgCode) &&
                Objects.equals(memo, depart.memo) &&
                Objects.equals(status, depart.status) &&
                Objects.equals(delFlag, depart.delFlag) &&
                Objects.equals(createBy, depart.createBy) &&
                Objects.equals(createTime, depart.createTime) &&
                Objects.equals(updateBy, depart.updateBy) &&
                Objects.equals(updateTime, depart.updateTime);
    }

    /**
     * 重写hashCode方法
     */
    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), id, parentId, departName,
                departOrder, orgCode, memo, status,
                delFlag, createBy, createTime, updateBy, updateTime);
    }
}
