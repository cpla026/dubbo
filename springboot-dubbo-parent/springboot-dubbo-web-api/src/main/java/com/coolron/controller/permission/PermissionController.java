package com.coolron.controller.permission;

import com.alibaba.dubbo.config.annotation.Reference;
import com.coolron.common.utils.ApiResult;
import com.coolron.pojo.permission.PermissionTree;
import com.coolron.pojo.permission.RolePermissionVo;
import com.coolron.service.permission.PermissionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Auther: xf
 * @Date: 2018/11/24 21:49
 * @Description:  权限
 */
@RestController
@RequestMapping(value = "permission")
public class PermissionController {

    @Reference
    private PermissionService permissionService;

    /**
     * 权限树
     * @param roleId  角色id 用户查询回显信息
     * @return
     */
    @GetMapping(value = "getPermissionTree/{roleId}")
    public ApiResult getPermissionTree(@PathVariable Integer roleId) {
        List<PermissionTree> list = permissionService.getPermissionTree(roleId);
        return ApiResult.ok(list);
    }

    /**
     * 修改 角色--权限
     * @param rolePermissionVo
     * @return
     */
    @PostMapping(value = "updatePermission}")
    public ApiResult updatePermission(@RequestBody RolePermissionVo rolePermissionVo) {
        permissionService.updatePermission(rolePermissionVo);
        return ApiResult.ok();
    }

}
