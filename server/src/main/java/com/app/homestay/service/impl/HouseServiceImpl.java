package com.app.homestay.service.impl;

import com.app.homestay.domain.HouseInfo;
import com.app.homestay.mapper.HouseMapper;
import com.app.homestay.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service(value = "houseService")
public class HouseServiceImpl implements HouseService {

    @Autowired
    HouseMapper houseMapper;

    @Override
    public int add(HouseInfo houseInfo) {
        return houseMapper.add(houseInfo);
    }

    @Override
    public List<HouseInfo> houseAll() {
        return houseMapper.houseAll();
    }

    @Override
    public int del(int uid) {
        return houseMapper.del(uid);
    }

    @Override
    public int update(HouseInfo houseInfo) {
        return houseMapper.update(houseInfo);
    }
}
