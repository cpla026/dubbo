package com.coolron.controller.permission;

import com.alibaba.dubbo.config.annotation.Reference;
import com.coolron.common.utils.ApiResult;
import com.coolron.pojo.permission.Role;
import com.coolron.service.permission.RoleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Auther: xf
 * @Date: 2018/11/24 21:35
 * @Description: 角色表的维护
 */
@RequestMapping(value = "role")
@RestController
public class RoleController {

    @Reference
    private RoleService roleService;

    @GetMapping(value={"list"})
    public ApiResult list() {
        List<Role> roleList = roleService.list();
        return ApiResult.ok(roleList);
    }

    @PostMapping(value="save")
    public ApiResult saver(@RequestBody Role role) {
        int result = roleService.save(role);
        return ApiResult.ok(result);
    }

    @GetMapping(value="get/{id}")
    public ApiResult getUser(@PathVariable Integer id) {
        Role role = roleService.get(id);
        return ApiResult.ok(role);
    }

    @PutMapping(value="update")
    public ApiResult putUser(@RequestBody Role role) {
        int result = roleService.update(role);
        return ApiResult.ok(result);
    }

    @DeleteMapping(value="delete/{id}")
    public ApiResult delete(@PathVariable Integer id) {
        int result = roleService.delete(id);
        return ApiResult.ok(result);
    }
}
