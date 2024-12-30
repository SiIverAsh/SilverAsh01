package com.app.homestay.service;

import com.app.homestay.domain.HouseInfo;

import java.util.List;

public interface HouseService {
    int add(HouseInfo houseInfo);

    List<HouseInfo> houseAll();

    int del(int uid);

    int update(HouseInfo houseInfo);
}
