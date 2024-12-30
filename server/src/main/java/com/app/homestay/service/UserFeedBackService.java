package com.app.homestay.service;

import com.app.homestay.domain.FeedBackInfo;
import com.app.homestay.domain.UserInfo;

import java.util.List;

public interface UserFeedBackService {

    int add(FeedBackInfo feedBackInfo);


    List<FeedBackInfo> query();

    List<FeedBackInfo> queryName(String username);
}
