package com.dfh.csma.system.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * <p>
 * 在线用户信息
 * </p>
 *
 * @Author scott
 * @since 2018-12-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class LoginUser {

	/**
	 * 登录人id
	 */
	private String id;

	/**
	 * 登录人账号
	 */
	private String username;

	/**
	 * 登录人名字
	 */
	private String realname;

	/**
	 * 登录人密码
	 */
	private String password;

     /**
      * 当前登录部门code
      */
    private String orgCode;


	/**
	 * 状态(1：正常 2：冻结 ）
	 */
	private Integer status;

	private String delFlag;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 *  身份（1 普通员工 2 上级）
	 */
	private Integer identity;

	/**
	 * 管理部门ids
	 */
	private String departIds;

}
