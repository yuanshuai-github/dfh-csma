package com.dfh.csma.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dfh.csma.system.entity.SysRole;
import com.dfh.csma.system.mapper.SysRoleMapper;
import com.dfh.csma.system.mapper.SysUserMapper;
import com.dfh.csma.system.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @Author scott
 * @since 2018-12-19
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {
    @Autowired
    SysRoleMapper sysRoleMapper;
    @Autowired
    SysUserMapper sysUserMapper;

    /*@Override
    public Result importExcelCheckRoleCode(MultipartFile file, ImportParams params) throws Exception {
        List<SysRole> listSysRoles = ExcelImportUtil.importExcel(file.getInputStream(), SysRole.class, params);

        int totalCount = listSysRoles.size();

        List<String> errorStrs = new ArrayList<>();

        // 去除 listSysRoles 中重复的数据
        for (int i = 0; i < listSysRoles.size(); i++) {
            String roleCodeI = listSysRoles.get(i).getRoleCode();

            for (int j = i + 1; j < listSysRoles.size(); j++) {
                String roleCodeJ = listSysRoles.get(j).getRoleCode();
                // 发现重复数据
                if (roleCodeI.equals(roleCodeJ)) {
                    errorStrs.add("第 " + (j + 1) + " 行的 roleCode 值：" + roleCodeI + " 已存在，忽略导入");
                    listSysRoles.remove(j);
                    break;
                }
            }
        }

        // 去掉 sql 中的重复数据
        for (int i = 0; i < listSysRoles.size(); i++) {
            SysRole sysRoleExcel = listSysRoles.get(i);
            try {
                super.save(sysRoleExcel);
            } catch (org.springframework.dao.DuplicateKeyException e) {
                errorStrs.add("第 " + (i + 1) + " 行的 roleCode 值：" + sysRoleExcel.getRoleCode() + " 已存在，忽略导入");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (errorStrs.size() == 0) {
            return Result.ok("文件导入成功！总导入行数：" + totalCount);
        }
        JSONObject result = new JSONObject(5);
        result.put("totalCount", totalCount);
        result.put("errorCount", errorStrs.size());
        result.put("successCount", (errorStrs.size() - totalCount));
        result.put("msg", "总上传行数：" + totalCount + "，已导入行数：" + (errorStrs.size() - totalCount) + "，错误行数：" + errorStrs.size());
        String fileUrl = PmsUtil.saveErrorTxtByList(errorStrs, "roleImportExcelErrorLog");
        int lastIndex = fileUrl.lastIndexOf(File.separator);
        String fileName = fileUrl.substring(lastIndex + 1);
        result.put("fileUrl", "/sys/common/download/" + fileUrl);
        result.put("fileName", fileName);
        Result res = Result.ok(result);

        res.setCode(201);
        res.setMessage("文件导入成功，但有错误。");
        return res;
    }*/

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteRole(String roleid) {
        //1.删除角色和用户关系
        sysRoleMapper.deleteRoleUserRelation(roleid);
        //2.删除角色和权限关系
        sysRoleMapper.deleteRolePermissionRelation(roleid);
        //3.删除角色
        this.removeById(roleid);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteBatchRole(String[] roleIds) {
        //1.删除角色和用户关系
        sysUserMapper.deleteBathRoleUserRelation(roleIds);
        //2.删除角色和权限关系
        sysUserMapper.deleteBathRolePermissionRelation(roleIds);
        //3.删除角色
        this.removeByIds(Arrays.asList(roleIds));
        return true;
    }
}
