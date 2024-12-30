package com.app.homestay.controller;

import com.app.homestay.domain.CollectionBean;
import com.app.homestay.domain.CollectionInfo;
import com.app.homestay.http.HttpResponse;
import com.app.homestay.http.StatusCode;
import com.app.homestay.service.CollectionService;
import com.app.homestay.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/collection")
public class CollectionController {


    @Autowired
    CollectionService collectionService;


    /**
     * 添加收藏
     */

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public HttpResponse<CollectionInfo> add(CollectionInfo collectionInfo) {

        HttpResponse<CollectionInfo> httpResponse = null;

        collectionInfo.setCreate_time(DateUtils.getCurrentTime());

        //判断是否收藏过
        CollectionInfo coll = collectionService.isColl(collectionInfo);
        if (coll == null) {
            int row = collectionService.add(collectionInfo);
            if (row > 0) {
                httpResponse = new HttpResponse<CollectionInfo>(StatusCode.Success);
                httpResponse.setMsg("收藏成功");
            } else {
                httpResponse = new HttpResponse<CollectionInfo>(StatusCode.Fail);
                httpResponse.setMsg("收藏失败");
            }
        } else {
            httpResponse = new HttpResponse<CollectionInfo>(StatusCode.Fail);
            httpResponse.setMsg("您已经收藏过");
        }


        httpResponse.setData(collectionInfo);
        return httpResponse;
    }


    /**
     * 收藏列表
     */
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    @ResponseBody
    public HttpResponse<CollectionBean> query(String username) {

        HttpResponse<CollectionBean> httpResponse = null;

        List<CollectionInfo> query = collectionService.query(username);

        httpResponse = new HttpResponse<CollectionBean>(StatusCode.Success);

        httpResponse.setData(new CollectionBean(query));
        return httpResponse;
    }


    /**
     * 取消收藏
     */

    @RequestMapping(value = "/del", method = RequestMethod.GET)
    @ResponseBody
    public HttpResponse<CollectionInfo> del(int uid) {

        HttpResponse<CollectionInfo> httpResponse = null;

        int row = collectionService.del(uid);

        if (row > 0) {
            httpResponse = new HttpResponse<CollectionInfo>(StatusCode.Success);
            httpResponse.setMsg("取消收藏");
        } else {
            httpResponse = new HttpResponse<CollectionInfo>(StatusCode.Fail);
            httpResponse.setMsg("取消收藏失败");
        }
        httpResponse.setData(new CollectionInfo());
        return httpResponse;
    }
}
