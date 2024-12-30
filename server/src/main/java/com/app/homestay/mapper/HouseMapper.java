package com.app.homestay.mapper;

import com.app.homestay.domain.HouseInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HouseMapper {


    int add(HouseInfo houseInfo);

    List<HouseInfo> houseAll();

    int del(int uid);

    int update(HouseInfo houseInfo);
}
