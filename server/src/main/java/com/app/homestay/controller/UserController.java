package com.app.homestay.controller;


import com.app.homestay.Constants;
import com.app.homestay.domain.UserBean;
import com.app.homestay.domain.UserInfo;
import com.app.homestay.http.HttpResponse;
import com.app.homestay.http.StatusCode;
import com.app.homestay.service.UserService;
import com.app.homestay.utils.ImageFileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {


    @Autowired
    UserService userService;


    @RequestMapping(value = "/register", method = RequestMethod.GET)
    @ResponseBody
    public HttpResponse<UserInfo> register(UserInfo userInfo) {
        HttpResponse<UserInfo> httpResponse = null;

        UserInfo register = userService.isRegister(userInfo);
        if (register == null) {
            //需要注册
            int row = userService.register(userInfo);
            if (row > 0) {
                httpResponse = new HttpResponse<UserInfo>(StatusCode.Success);
                httpResponse.setMsg("注册成功");
            }
        } else {
            httpResponse = new HttpResponse<UserInfo>(StatusCode.Fail);
            httpResponse.setMsg("该账号已注册");
        }
        httpResponse.setData(userService.isRegister(userInfo));
        return httpResponse;
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public HttpResponse<UserInfo> login(UserInfo userInfo) {
        HttpResponse<UserInfo> httpResponse = null;

        UserInfo user = userService.isRegister(userInfo);

        if (null != user) {
            if (!user.getPassword().equals(userInfo.getPassword())) {
                httpResponse = new HttpResponse<UserInfo>(StatusCode.Fail);
                httpResponse.setMsg("密码错误");
            } else if (user.getIdentity() != userInfo.getIdentity()) {
                httpResponse = new HttpResponse<UserInfo>(StatusCode.Fail);
                httpResponse.setMsg("账号类型错误，请选择正确的帐号登录");
            } else {
                httpResponse = new HttpResponse<UserInfo>(StatusCode.Success);
                httpResponse.setMsg("登录成功");
                httpResponse.setData(user);
            }


        } else {
            httpResponse = new HttpResponse<UserInfo>(StatusCode.Fail);
            httpResponse.setMsg("该账号暂未注册");
        }
        return httpResponse;
    }

    /**
     * 查询所有注册用户
     */
    @RequestMapping(value = "query", method = RequestMethod.GET)
    @ResponseBody
    public HttpResponse<UserBean> query() {

        HttpResponse<UserBean> httpResponse = null;

        List<UserInfo> userInfoList = userService.query();
        httpResponse = new HttpResponse<UserBean>(StatusCode.Success);
        httpResponse.setData(new UserBean(userInfoList));

        return httpResponse;
    }


    /**
     * 修改密码
     */
    @RequestMapping(value = "pwd", method = RequestMethod.POST)
    @ResponseBody
    public HttpResponse<UserInfo> pwd(UserInfo userInfo) {
        HttpResponse<UserInfo> httpResponse = null;

        int row = userService.pwd(userInfo);
        if (row > 0) {
            httpResponse = new HttpResponse<UserInfo>(StatusCode.Success);
            httpResponse.setMsg("修改成功");
        } else {
            httpResponse = new HttpResponse<UserInfo>(StatusCode.Fail);
            httpResponse.setMsg("修改失败");
        }
        httpResponse.setData(new UserInfo());
        return httpResponse;
    }

    /**
     * 修改用户信息
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public HttpResponse<UserInfo> edit(UserInfo userInfo, MultipartFile file) {
        HttpResponse<UserInfo> httpResponse = null;

        if (null != file) {
            String projectPath = ImageFileUtils.loadImg(file);
            userInfo.setAvatar(Constants.BASE_URL + projectPath);
        }
        int row = userService.edit(userInfo);
        if (row > 0) {
            UserInfo register = userService.isRegister(userInfo);
            if (register!=null){
                httpResponse = new HttpResponse<UserInfo>(StatusCode.Success);
                httpResponse.setMsg("修改成功");
                httpResponse.setData(register);
            }else {
                httpResponse = new HttpResponse<UserInfo>(StatusCode.Fail);
                httpResponse.setMsg("修改失败");
                httpResponse.setData(new UserInfo());
            }

        } else {
            httpResponse = new HttpResponse<UserInfo>(StatusCode.Fail);
            httpResponse.setMsg("修改失败");
            httpResponse.setData(new UserInfo());
        }

        return httpResponse;
    }
}
