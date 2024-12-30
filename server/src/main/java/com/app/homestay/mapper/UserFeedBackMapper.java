package com.app.homestay.mapper;

import com.app.homestay.domain.FeedBackInfo;
import com.app.homestay.domain.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserFeedBackMapper {

    int add(FeedBackInfo info);

    List<FeedBackInfo> query();

    List<FeedBackInfo> queryName(String username);
}
