package com.app.homestay.service;

import com.app.homestay.domain.OrderInfo;

import java.util.List;

public interface UserOrderService {
    int add(OrderInfo orderInfo);

    List<OrderInfo> query(OrderInfo orderInfo);


    List<OrderInfo> queryAll(OrderInfo orderInfo);

    int del(int uid);

    int pay(OrderInfo orderInfo);
}
