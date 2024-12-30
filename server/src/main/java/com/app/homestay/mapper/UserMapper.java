package com.app.homestay.mapper;

import com.app.homestay.domain.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    int register(UserInfo info);

    UserInfo isRegister(UserInfo info);

    List<UserInfo> query();

    int pwd(UserInfo userInfo);

    int edit(UserInfo userInfo);
}
