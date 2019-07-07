package com.coolron.service.permission;


import com.coolron.pojo.permission.Role;

import java.util.List;

/**
 * @Auther: xf
 * @Date: 2018/11/24 21:35
 * @Description:
 */
public interface RoleService {
    List<Role> list();

    int save(Role role);

    Role get(Integer id);

    int update(Role role);

    int delete(Integer id);
}
