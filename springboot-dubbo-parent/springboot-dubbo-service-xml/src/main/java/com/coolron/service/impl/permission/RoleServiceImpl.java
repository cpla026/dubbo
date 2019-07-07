package com.coolron.service.impl.permission;

import com.coolron.dao.permission.RoleMapper;
import com.coolron.pojo.permission.Role;
import com.coolron.service.permission.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: xf
 * @Date: 2018/11/24 21:36
 * @Description: 角色逻辑层
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> list() {
        return roleMapper.list();
    }

    @Override
    public int save(Role role) {
        return roleMapper.insertSelective(role);
    }

    @Override
    public Role get(Integer id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public int update(Role role) {
        return roleMapper.updateByPrimaryKeySelective(role);
    }

    @Override
    public int delete(Integer id) {
        return roleMapper.deleteByPrimaryKey(id);
    }
}
