package com.coolron.service.permission;


import com.coolron.pojo.permission.PermissionTree;
import com.coolron.pojo.permission.RolePermissionVo;

import java.util.List;

/**
 * @Auther: xf
 * @Date: 2018/11/24 21:59
 * @Description:
 */
public interface PermissionService {
    List<PermissionTree> getPermissionTree(Integer roleId);
    void updatePermission(RolePermissionVo rolePermissionVo);
}
