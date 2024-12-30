package com.app.homestay.service;

import com.app.homestay.domain.UserInfo;

import java.util.List;

public interface UserService {

    int register(UserInfo info);

    UserInfo isRegister(UserInfo info);

    List<UserInfo> query();

    int pwd(UserInfo userInfo);

    int edit(UserInfo userInfo);
}
