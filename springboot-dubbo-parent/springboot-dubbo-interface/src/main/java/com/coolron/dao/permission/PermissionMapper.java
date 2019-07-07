package com.coolron.dao.permission;

import com.coolron.pojo.permission.Permission;
import com.coolron.pojo.permission.PermissionTree;
import com.coolron.pojo.permission.RolePermission;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

    @Select("select * from permission")
    List<PermissionTree> getAll();

    @Select("select permission_id from role_permission where role_id = #{roleId}")
    List<Integer> getPermissionIds(@Param("roleId") Integer roleId);

    @Select("delete from role_permission where role_id = #{roleId}")
    void deleteRolePermissionByRoleId(@Param("roleId") Integer roleId);

    void insertRolePermission(RolePermission rolePermission);
}