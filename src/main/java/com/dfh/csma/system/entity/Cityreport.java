package com.dfh.csma.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 城市归集上报
 * @Author: jeecg-boot
 * @Date: 2020-03-27
 * @Version: V1.0
 */
@Data
@TableName("dfh_cityreport")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "dfh_cityreport对象", description = "城市归集上报")
public class Cityreport implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.ID_WORKER_STR)
    @ApiModelProperty(value = "主键")
    private String id;
    /**
     * 上报单位id
     */
    @ApiModelProperty(value = "上报单位id")
    private String tunitid;
    /**
     * 上报单位名称
     */
    @ApiModelProperty(value = "上报单位名称")
    private String unitname;
    /**
     * 文件id
     */
    @ApiModelProperty(value = "文件id")
    private String fileid;
    /**
     * 上报说明
     */
    @ApiModelProperty(value = "上报说明")
    private String directions;
    /**
     * 内容
     */
    @ApiModelProperty(value = "内容")
    private String contents;
    /**
     * 目前状态 1上报 2已审核 3驳回
     */
    @ApiModelProperty(value = "目前状态")
    private Integer reportstatus;
    /**
     * 审核说明
     */
    @ApiModelProperty(value = "审核说明")
    private String examinedirections;
    /**
     * 所属规则
     */
    @ApiModelProperty(value = "所属规则")
    private Integer ruledi;
    /**
     * 上报时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date createTime;
    /**
     * 上报人
     */
    @ApiModelProperty(value = "上报人")
    private String uploaduser;
    /**
     * 审核时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "审核时间")
    private Date examinetime;
    /**
     * 审核单位
     */
    @ApiModelProperty(value = "审核单位")
    private Integer examineunit;
    /**
     * 审核人
     */
    @ApiModelProperty(value = "审核人")
    private String examineuser;
}
