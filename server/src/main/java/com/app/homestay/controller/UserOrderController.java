package com.app.homestay.controller;


import com.app.homestay.domain.OderInfoBean;
import com.app.homestay.domain.OrderInfo;
import com.app.homestay.http.HttpResponse;
import com.app.homestay.http.StatusCode;
import com.app.homestay.service.UserOrderService;
import com.app.homestay.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("order")
public class UserOrderController {

    @Autowired
    UserOrderService userOrderService;


    /**
     * 添加订单
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public HttpResponse<OrderInfo> add(OrderInfo orderInfo) {
        HttpResponse<OrderInfo> httpResponse = null;

        orderInfo.setCreate_time(DateUtils.getCurrentTime());
        int row = userOrderService.add(orderInfo);
        if (row > 0) {
            httpResponse = new HttpResponse<OrderInfo>(StatusCode.Success);
            httpResponse.setMsg("添加订单成功");
        } else {
            httpResponse = new HttpResponse<OrderInfo>(StatusCode.Fail);
            httpResponse.setMsg("添加订单失败");
        }
        httpResponse.setData(orderInfo);

        return httpResponse;
    }


    /**
     * 根据用户名查询订单    查询所有订单
     */
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    public HttpResponse<OderInfoBean> query(OrderInfo orderInfo) {
        HttpResponse<OderInfoBean> httpResponse = null;
        List<OrderInfo> query = new ArrayList<>();
        if (orderInfo.getUsername() == null) {
            query = userOrderService.queryAll(orderInfo);
        } else {
            query = userOrderService.query(orderInfo);
        }

        httpResponse = new HttpResponse<OderInfoBean>(StatusCode.Success);
        httpResponse.setData(new OderInfoBean(query));

        return httpResponse;

    }

    /**
     * 删除订单
     */
    @RequestMapping(value = "/del", method = RequestMethod.GET)
    @ResponseBody
    public HttpResponse<OrderInfo> del(int uid) {

        HttpResponse<OrderInfo> httpResponse = null;

        int row = userOrderService.del(uid);
        if (row > 0) {
            httpResponse = new HttpResponse<OrderInfo>(StatusCode.Success);
            httpResponse.setMsg("删除成功");
        } else {
            httpResponse = new HttpResponse<OrderInfo>(StatusCode.Success);
            httpResponse.setMsg("删除失败");
        }
        httpResponse.setData(new OrderInfo());
        return httpResponse;
    }


    /**
     * 支付订单
     */

    @RequestMapping(value = "/pay", method = RequestMethod.GET)
    @ResponseBody
    public HttpResponse<OrderInfo> pay(OrderInfo orderInfo) {

        HttpResponse<OrderInfo> httpResponse = null;
        orderInfo.setOrder_num(DateUtils.getRandom(10));
        int row = userOrderService.pay(orderInfo);
        if (row > 0) {
            httpResponse = new HttpResponse<OrderInfo>(StatusCode.Success);
            httpResponse.setMsg("支付成功");
        } else {
            httpResponse = new HttpResponse<OrderInfo>(StatusCode.Success);
            httpResponse.setMsg("支付失败");
        }
        httpResponse.setData(new OrderInfo());
        return httpResponse;
    }
}
