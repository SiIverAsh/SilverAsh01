package com.app.homestay.mapper;

import com.app.homestay.domain.CollectionInfo;
import com.app.homestay.domain.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CollectionMapper {

    int add(CollectionInfo collectionInfo);

    List<CollectionInfo> query(String username);

    int del(int uid);

    CollectionInfo isColl(CollectionInfo collectionInfo);
}
