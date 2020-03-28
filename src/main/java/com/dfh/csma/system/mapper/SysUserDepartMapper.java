package com.dfh.csma.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dfh.csma.system.entity.SysUserDepart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysUserDepartMapper extends BaseMapper<SysUserDepart>{

	List<SysUserDepart> getUserDepartByUid(@Param("userId") String userId);
}
