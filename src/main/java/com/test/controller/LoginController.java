package com.test.controller;


import com.test.config.PasswordUtil;
import com.test.config.Token;
import com.test.domain.UserInfo;
import com.test.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by Administrator on 2017/3/13.
 */
@Controller
public class LoginController {

    //获取session
    HttpSession session ;
    ServletContext application;

    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Token(save=true)
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login_get(){
        logger.info("login get 跳转");

        return "/login/login";
    }

    @Token(remove = true)
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login_post( Model model,HttpServletRequest request,
                             @RequestParam(value = "loginemail")String loginemail,
                             @RequestParam(value = "Password")String Password){
        logger.info("login post 跳转"+loginemail+"/"+Password);
        UserInfo userInfo = new UserInfo();
        userInfo.setUserEmail(loginemail);
        userInfo.setPassword(Password);
        UserInfo userInfo1 =  userService.finduserByName(userInfo);
        logger.info("login"+userInfo1.toString());
        if(PasswordUtil.verify(userInfo.getPassword(),userInfo1.getPassword()))
        {
            logger.info("登录成功："+PasswordUtil.verify(userInfo.getPassword(),userInfo1.getPassword()));
            session = request.getSession();
            session.setAttribute("name", userInfo1.getUserName()); // 保存当前登录的用户名
            //
            application = request.getSession().getServletContext();
            if (application.getAttribute("onLine") == null) {
                application.setAttribute("onLine", "");
            }
            String onLine = application.getAttribute("onLine").toString();
            onLine += userInfo1.getUserName() + "<br/>";
            application.setAttribute("onLine", onLine);
            logger.info("onLine："+onLine);
            model.addAttribute("user",userInfo1);
            return "front/index";

        }
        else {
            logger.info("登录失败："+PasswordUtil.verify(userInfo.getPassword(),userInfo1.getPassword()));
//            System.out.println(userInfo.getPassword()+"/"+userInfo1.getSalt());
//            String password = PasswordUtil.generate("12345678");
//            System.out.println(PasswordUtil.verify("12345678", "56580d84be5507769b35139f27442f61f43b70989885a170"));
            return "login/login";
        }

    }

    @Token(save=true)
    @RequestMapping(value = "/signup",method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
    public String signup_get(){
        logger.info("signup get 跳转");
        return "/login/login";
    }

    @Token(remove = true)
    @RequestMapping(value = "/signup",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    public String signup_post(UserInfo userInfo){
        logger.info("signup post 跳转");
        userInfo.setPassword(PasswordUtil.generate(userInfo.getPassword()));
        userInfo.setRole(0);
        logger.info("add "+userInfo.toString());
        userService.addUserInfo(userInfo);
        return "front/index";
    }

    @RequestMapping(value = "/success",method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
    public String success(){
        logger.info("success get 跳转");
        return "front/index";
    }

    @RequestMapping(value = "/index",method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
    public String init(Model model,UserInfo userInfo){
        logger.info("success get 跳转");
        model.addAttribute("user",userInfo);
        return "front/index";
    }



}
