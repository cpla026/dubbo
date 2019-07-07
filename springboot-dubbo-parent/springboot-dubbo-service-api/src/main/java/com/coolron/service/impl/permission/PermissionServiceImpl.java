package com.coolron.service.impl.permission;

import com.alibaba.dubbo.config.annotation.Service;
import com.coolron.dao.permission.PermissionMapper;
import com.coolron.pojo.permission.PermissionTree;
import com.coolron.pojo.permission.RolePermission;
import com.coolron.pojo.permission.RolePermissionVo;
import com.coolron.service.permission.PermissionService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: xf
 * @Date: 2018/11/24 21:59
 * @Description:
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;
    @Override
    public List<PermissionTree> getPermissionTree(Integer roleId) {
        // 存放查询结果
        List<PermissionTree> permissionList = Lists.newArrayList();
        // 查询所有的权限
        List<PermissionTree> permissions = permissionMapper.getAll();
        // role_permission 中选中的
        List<Integer> permissionIds = permissionMapper.getPermissionIds(roleId);
        // 中转容器  map 的索引查询可以提高效率
        Map<Integer, PermissionTree> permissionMap = new HashMap<>();
        for (PermissionTree p : permissions) {
            // 判断有没有选中
            if(permissionIds.contains(p.getId())){
                p.setChecked(true);
            }
            permissionMap.put(p.getId(), p);
        }
        for (PermissionTree p : permissions) {
            PermissionTree child = p;
            if(p.getPid() == 0){
                // 根节点
                permissionList.add(p);
            }else{
                PermissionTree parent = permissionMap.get(child.getPid());
                parent.getChildren().add(child);
            }
        }
        return permissionList;
    }

    // 事物处理
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updatePermission(RolePermissionVo rolePermissionVo) {
        // 1、根据roleId 删除所有的权限
        Integer roleId = rolePermissionVo.getRoleId();
        permissionMapper.deleteRolePermissionByRoleId(roleId);

        // 2. 新增
        List<RolePermission> rolePermissionList = rolePermissionVo.getRolePermissionList();
        if(rolePermissionList.size() > 0){
            for (RolePermission rolePermission : rolePermissionList) {
                // 单条更新
                permissionMapper.insertRolePermission(rolePermission);
            }
        }
    }
}
