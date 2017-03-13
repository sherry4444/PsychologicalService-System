package com.test.controller;


import com.test.domain.UserInfo;
import com.test.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;


/**
 * Created by Administrator on 2017/3/13.
 */
@Controller
public class loginController {


    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(loginController.class);

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login_get(){
        logger.info("login get 跳转");

        return "/login/login";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login_post(){
        logger.info("login post 跳转");
        return "success";
    }

    @RequestMapping(value = "/signup",method = RequestMethod.GET)
    public String signup_get(){
        logger.info("signup get 跳转");
        return "/login/login";
    }

    @RequestMapping(value = "/signup",method = RequestMethod.POST)
    public String signup_post(UserInfo userInfo){
        logger.info("signup post 跳转");
        logger.info("add "+userInfo.toString());
        userService.addUserInfo(userInfo);
        return "success";
    }

    @RequestMapping(value = "/success",method = RequestMethod.GET)
    public String success(){
        logger.info("success get 跳转");
        return "/success";
    }

}
