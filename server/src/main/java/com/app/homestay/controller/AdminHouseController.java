package com.app.homestay.controller;


import com.app.homestay.Constants;
import com.app.homestay.domain.HouseBean;
import com.app.homestay.domain.HouseInfo;
import com.app.homestay.http.HttpResponse;
import com.app.homestay.http.StatusCode;
import com.app.homestay.service.HouseService;
import com.app.homestay.utils.DateUtils;
import com.app.homestay.utils.ImageFileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("house")
public class AdminHouseController {

    @Autowired
    HouseService houseService;


    /**
     * 上传房子信息
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public HttpResponse<HouseInfo> add(HouseInfo houseInfo, MultipartFile file) {

        HttpResponse<HouseInfo> httpResponse = null;
        if (null != file) {
            String img = ImageFileUtils.loadImg(file);
            if (img == null) {
                httpResponse = new HttpResponse<HouseInfo>(StatusCode.Fail);
                httpResponse.setMsg("图片上传失败");
                return httpResponse;
            }
            houseInfo.setHouse_img(Constants.BASE_URL + img);
        }
        houseInfo.setCreate_time(DateUtils.getCurrentTime());

        int row = houseService.add(houseInfo);
        if (row > 0) {
            httpResponse = new HttpResponse<HouseInfo>(StatusCode.Success);
            httpResponse.setMsg("上架成功");
        } else {
            httpResponse = new HttpResponse<HouseInfo>(StatusCode.Fail);
            httpResponse.setMsg("上架失败");
        }
        httpResponse.setData(houseInfo);

        return httpResponse;
    }


    /**
     * 查询房子列表
     */

    @RequestMapping(value = "houseAll", method = RequestMethod.GET)
    @ResponseBody
    public HttpResponse<HouseBean> houseAll() {

        HttpResponse<HouseBean> httpResponse = null;
        List<HouseInfo> houseInfos = houseService.houseAll();
        httpResponse = new HttpResponse<HouseBean>(StatusCode.Success);
        httpResponse.setData(new HouseBean(houseInfos));
        return httpResponse;

    }

    /**
     * 通过uid删除房子信息
     */
    @RequestMapping(value = "del", method = RequestMethod.GET)
    @ResponseBody
    public HttpResponse<HouseInfo> del(int uid) {

        HttpResponse<HouseInfo> httpResponse = null;
        int row = houseService.del(uid);
        if (row > 0) {
            httpResponse = new HttpResponse<HouseInfo>(StatusCode.Success);
            httpResponse.setMsg("删除成功");
        } else {
            httpResponse = new HttpResponse<HouseInfo>(StatusCode.Fail);
            httpResponse.setMsg("删除失败");
        }
        httpResponse.setData(new HouseInfo());
        return httpResponse;

    }

    /**
     * 通过uid修改房子信息
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public HttpResponse<HouseInfo> update(HouseInfo houseInfo, MultipartFile file) {
        HttpResponse<HouseInfo> httpResponse = null;

        if (file != null) {
            String img = ImageFileUtils.loadImg(file);
            houseInfo.setHouse_img(Constants.BASE_URL + img);
        }
        int row = houseService.update(houseInfo);
        if (row > 0) {
            httpResponse = new HttpResponse<HouseInfo>(StatusCode.Success);
            httpResponse.setMsg("修改成功");
        } else {
            httpResponse = new HttpResponse<HouseInfo>(StatusCode.Fail);
            httpResponse.setMsg("修改失败");
        }
        httpResponse.setData(new HouseInfo());
        return httpResponse;

    }

}
