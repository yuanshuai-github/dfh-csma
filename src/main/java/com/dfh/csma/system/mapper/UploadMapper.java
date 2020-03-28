package com.dfh.csma.system.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dfh.csma.system.model.FileModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UploadMapper extends BaseMapper<FileModel> {

}
