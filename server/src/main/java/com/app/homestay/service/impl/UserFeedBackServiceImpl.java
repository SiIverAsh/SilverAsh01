package com.app.homestay.service.impl;

import com.app.homestay.domain.FeedBackInfo;
import com.app.homestay.domain.UserInfo;
import com.app.homestay.mapper.UserFeedBackMapper;
import com.app.homestay.mapper.UserMapper;
import com.app.homestay.service.UserFeedBackService;
import com.app.homestay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service(value = "userFeedBackService")
public class UserFeedBackServiceImpl implements UserFeedBackService {


    @Autowired
    UserFeedBackMapper backMapper;


    @Override
    public int add(FeedBackInfo feedBackInfo) {
        return backMapper.add(feedBackInfo);
    }

    @Override
    public List<FeedBackInfo> query() {
        return backMapper.query();
    }

    @Override
    public List<FeedBackInfo> queryName(String username) {
        return backMapper.queryName(username);
    }
}
