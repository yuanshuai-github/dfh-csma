package com.dfh.csma.system.model;

import com.dfh.csma.system.entity.SysDepart;
import com.dfh.csma.system.entity.SysUser;
import lombok.Data;

/**
 * 包含 SysUser 和 SysDepart 的 Model
 *
 * @author sunjianlei
 */
@Data
public class SysUserSysDepartModel {

    private SysUser sysUser;
    private SysDepart sysDepart;

}
