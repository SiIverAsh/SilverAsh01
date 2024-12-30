package com.app.homestay.service;

import com.app.homestay.domain.CollectionInfo;
import com.app.homestay.domain.UserInfo;

import java.util.List;

public interface CollectionService {

    int add(CollectionInfo collectionInfo);


    List<CollectionInfo> query(String username);

    int del(int uid);

    CollectionInfo isColl(CollectionInfo collectionInfo);
}
