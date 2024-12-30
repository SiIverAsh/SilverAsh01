package com.app.homestay.controller;

import com.app.homestay.domain.FeedBackInfo;
import com.app.homestay.domain.FeedBackInfoBean;
import com.app.homestay.http.HttpResponse;
import com.app.homestay.http.StatusCode;
import com.app.homestay.service.UserFeedBackService;
import com.app.homestay.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/feed")
public class UserFeedBackController {

    @Autowired
    UserFeedBackService userFeedBackService;


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public HttpResponse<FeedBackInfo> add(FeedBackInfo feedBackInfo) {
        HttpResponse<FeedBackInfo> httpResponse = null;

        feedBackInfo.setCreate_time(DateUtils.getCurrentTime());
        int row = userFeedBackService.add(feedBackInfo);
        if (row > 0) {
            httpResponse = new HttpResponse<FeedBackInfo>(StatusCode.Success);
            httpResponse.setMsg("反馈成功");
        } else {
            httpResponse = new HttpResponse<FeedBackInfo>(StatusCode.Success);
            httpResponse.setMsg("反馈失败");
        }

        httpResponse.setData(new FeedBackInfo());
        return httpResponse;
    }


    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    public HttpResponse<FeedBackInfoBean> query(String username) {
        HttpResponse<FeedBackInfoBean> httpResponse = null;
        List<FeedBackInfo> query = new ArrayList<>();
        if (username == null) {
            query = userFeedBackService.query();
        } else {
            query = userFeedBackService.queryName(username);
        }

        httpResponse = new HttpResponse<FeedBackInfoBean>(StatusCode.Success);
        httpResponse.setData(new FeedBackInfoBean(query));
        return httpResponse;
    }
}
