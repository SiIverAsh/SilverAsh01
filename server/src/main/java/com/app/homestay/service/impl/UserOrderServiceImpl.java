package com.app.homestay.service.impl;

import com.app.homestay.domain.OrderInfo;
import com.app.homestay.mapper.UserOrderMapper;
import com.app.homestay.service.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service(value = "userOrderService")
public class UserOrderServiceImpl implements UserOrderService {

    @Autowired
    UserOrderMapper userOrderMapper;


    @Override
    public int add(OrderInfo orderInfo) {
        return userOrderMapper.add(orderInfo);
    }

    @Override
    public List<OrderInfo> query(OrderInfo orderInfo) {
        return userOrderMapper.query(orderInfo);
    }

    @Override
    public List<OrderInfo> queryAll(OrderInfo orderInfo) {
        return userOrderMapper.queryAll(orderInfo);
    }

    @Override
    public int del(int uid) {
        return userOrderMapper.del(uid);
    }

    @Override
    public int pay(OrderInfo orderInfo) {
        return userOrderMapper.pay(orderInfo);
    }
}
