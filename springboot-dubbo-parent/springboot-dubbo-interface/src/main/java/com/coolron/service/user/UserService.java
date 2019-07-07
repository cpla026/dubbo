package com.coolron.service.user;


import com.coolron.pojo.user.User;

import java.util.List;

public interface UserService {

    List<User> getAllUser();

    int saveUser(User user);

    User getUser(Integer id);

    int updateUser(User user);

    int deleteUser(Integer id);

}
