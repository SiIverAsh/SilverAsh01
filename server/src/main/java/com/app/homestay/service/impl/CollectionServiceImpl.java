package com.app.homestay.service.impl;

import com.app.homestay.domain.CollectionInfo;
import com.app.homestay.mapper.CollectionMapper;
import com.app.homestay.mapper.UserMapper;
import com.app.homestay.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service(value = "collectionService")
public class CollectionServiceImpl implements CollectionService {


    @Autowired
    CollectionMapper collectionMapper;


    @Override
    public int add(CollectionInfo collectionInfo) {
        return collectionMapper.add(collectionInfo);
    }

    @Override
    public List<CollectionInfo> query(String username) {
        return collectionMapper.query(username);
    }

    @Override
    public int del(int uid) {
        return collectionMapper.del(uid);
    }


    @Override
    public CollectionInfo isColl(CollectionInfo collectionInfo) {
        return collectionMapper.isColl(collectionInfo);
    }


}
