package com.dfh.csma.system.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.dfh.csma.system.entity.SysUser;
import com.dfh.csma.system.entity.SysUserDepart;
import com.dfh.csma.system.model.DepartIdModel;

import java.util.List;

/**
 * <p>
 * SysUserDpeart用户组织机构service
 * </p>
 * @Author ZhiLin
 *
 */
public interface ISysUserDepartService extends IService<SysUserDepart> {


	/**
	 * 根据指定用户id查询部门信息
	 * @param userId
	 * @return
	 */
	List<DepartIdModel> queryDepartIdsOfUser(String userId);


	/**
	 * 根据部门id查询用户信息
	 * @param depId
	 * @return
	 */
	List<SysUser> queryUserByDepId(String depId);
  	/**
	 * 根据部门code，查询当前部门和下级部门的用户信息
	 */
	public List<SysUser> queryUserByDepCode(String depCode);
}
