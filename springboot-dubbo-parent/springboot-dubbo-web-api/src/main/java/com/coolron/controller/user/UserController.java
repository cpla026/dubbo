package com.coolron.controller.user;

import com.alibaba.dubbo.config.annotation.Reference;
import com.coolron.common.utils.ApiResult;
import com.coolron.common.validate.RequiredPermission;
import com.coolron.pojo.user.User;
import com.coolron.service.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @Auther: xf
 * @Date: 2018/10/03 15:15
 * @Description: 用户controller
 */
@Slf4j
@Api(description = "用户接口")
@RestController
@RequestMapping(value = "user")
public class UserController {

    @Reference
    private UserService userService;


    @RequiredPermission("user:view")
    @ApiOperation(value="获取用户列表", notes="")
    @RequestMapping(value={"getUserList"}, method= RequestMethod.GET)
    public ApiResult getUserList() {
        List<User> userList = userService.getAllUser();
        return ApiResult.ok(userList);
    }

    @RequiredPermission("user:save")
    @ApiOperation(value="创建用户", notes="根据User对象创建用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    @RequestMapping(value="saveUser", method=RequestMethod.POST)
    public ApiResult saveUser(@RequestBody User user) {
        int result = userService.saveUser(user);
        return ApiResult.ok(result);
    }

    @ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "getUser", value = "用户ID", required = true, dataType = "Long")
    @RequestMapping(value="getUser/{id}", method=RequestMethod.GET)
    public ApiResult getUser(@PathVariable Integer id) {
        User user = userService.getUser(id);
        return ApiResult.ok(user);
    }

    @ApiOperation(value="更新用户详细信息", notes="根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    })
    @RequestMapping(value="updateUser/{id}", method=RequestMethod.PUT)
    public ApiResult putUser(@PathVariable Integer id, @RequestBody User user) {
        int result = userService.updateUser(user);
        return ApiResult.ok(result);
    }

    @RequiredPermission("user:delete")
    @ApiOperation(value="删除用户", notes="根据url的id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
    @RequestMapping(value="delete/{id}", method=RequestMethod.DELETE)
    public ApiResult deleteUser(@PathVariable Integer id) {
        int result = userService.deleteUser(id);
        return ApiResult.ok(result);
    }
}
