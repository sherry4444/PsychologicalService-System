package com.test.controller;

import com.test.commons.HttpClientUtil;
import com.test.commons.MailUtils;
import com.test.domain.UserInfo;
import com.test.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.net.www.http.HttpClient;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/28.
 */
@Controller
public class ModifyDataController {


    private Logger logger = LoggerFactory.getLogger(ModifyDataController.class);

    @Resource
    private UserService userService;

    @RequestMapping(value = "/modifydata",method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
    public String modifydata_get(){
        logger.info("modifydata get 跳转");
        return "/login/modifydata";
    }

    @RequestMapping(value = "/modifydata",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    public String modifydata_post(){
        logger.info("modifydata post 跳转");
        return "/login/modifydata";
    }

    @RequestMapping(value = "/forgetpw",method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
    public String forgetPassword_get(){
        logger.info("modifydata get 跳转");
        return "/login/forgetpassword";
    }

    @RequestMapping(value = "/forgetPassword",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    public void forgetPassword(@ModelAttribute UserInfo userInfo,HttpServletRequest request){
        logger.info("forgetPassword post 跳转");
        MailUtils mailUtils = new MailUtils();
        mailUtils.sendMail(request,"suguihao@chinasie.com");
    }


    @RequestMapping(value = "/modifyPassword",method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
    public String modifyPassword(){
        logger.info("modifyPassword get 跳转");
        return "/login/modifypassword";
    }

    @RequestMapping(value = "/modifyPassword",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String modifyPassword_post(@ModelAttribute UserInfo userInfo){
        logger.info("modifyPassword post 跳转");
        try {
            UserInfo checkuser  = userService.finduserByName(userInfo);
            if (checkuser == null) {return "不存在此用户";}
            userService.modifypassword(userInfo);
        }catch (Exception e)
        {
            e.printStackTrace();
            return "修改失败";
        }
        return "修改成功";
    }

}
