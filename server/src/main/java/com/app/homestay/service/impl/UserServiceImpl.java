package com.app.homestay.service.impl;

import com.app.homestay.domain.UserInfo;
import com.app.homestay.mapper.UserMapper;
import com.app.homestay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service(value = "userService")
public class UserServiceImpl implements UserService {


    @Autowired
    UserMapper userMapper;


    @Override
    public int register(UserInfo info) {
        return userMapper.register(info);
    }

    @Override
    public UserInfo isRegister(UserInfo info) {
        return userMapper.isRegister(info);
    }

    @Override
    public List<UserInfo> query() {
        return userMapper.query();
    }

    @Override
    public int pwd(UserInfo userInfo) {
        return userMapper.pwd(userInfo);
    }

    @Override
    public int edit(UserInfo userInfo) {
        return userMapper.edit(userInfo);
    }
}
