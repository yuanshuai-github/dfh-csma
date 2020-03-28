package com.dfh.csma.system.model;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@TableName("dfh_files")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class FileModel {


    /**
     * id
     */
    @TableId(type = IdType.ID_WORKER_STR)
    private String id;

    /**
     * 文件名
     */
    private String filename;


    /***
     * 路径
     */
    private String filepath;

    /**
     * 大小
     */
    private long filesize;

    /**
     * md5
     */
    private String md5;

    /**
     * 上传时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date uploadtime;

    /**
     * 创建人
     */
    private String uploaduser;
}
