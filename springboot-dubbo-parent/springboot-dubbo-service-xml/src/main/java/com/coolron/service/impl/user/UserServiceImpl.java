package com.coolron.service.impl.user;

import com.coolron.dao.user.UserMapper;
import com.coolron.pojo.user.User;
import com.coolron.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: xf
 * @Date: 2018/10/03 15:16
 * @Description:
 * service 注解不在是spring提供的，是com.alibaba.dubbo.config.annotation.Service注解
 * 版本、超时、接口属性可不配
 */
@Service("userService")
@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper mapper;

    @Override
    public List<User> getAllUser() {
        return mapper.getAll();
    }

    @Override
    public int saveUser(User user) {
        return mapper.insertSelective(user);
    }

    @Override
    public User getUser(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateUser(User user) {
        return mapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public int deleteUser(Integer id) {
        return mapper.deleteByPrimaryKey(id);
    }

}
