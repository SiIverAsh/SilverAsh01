package com.app.homestay.mapper;

import com.app.homestay.domain.OrderInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserOrderMapper {


    int add(OrderInfo orderInfo);


    List<OrderInfo> query(OrderInfo orderInfo);

    List<OrderInfo> queryAll(OrderInfo orderInfo);

    int del(int uid);

    int pay(OrderInfo orderInfo);
}
