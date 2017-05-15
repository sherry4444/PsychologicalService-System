package com.test.controller;

import com.test.commons.HttpClientUtil;
import com.test.commons.MailUtils;
import com.test.config.PasswordUtil;
import com.test.domain.UserInfo;
import com.test.service.UserService;
import org.junit.Test;
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
import java.security.spec.ECField;
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
    public String modifydata_post(@ModelAttribute UserInfo userInfo){
        logger.info("modifydata post 跳转");
        try {
            Integer checkuserid  = userService.finduserid(userInfo);
            if (checkuserid == null) {return "不存在此用户";}
            else {
            userService.updateUser(userInfo);
            }
        }catch (Exception e) {
            e.printStackTrace();
            return "修改失败";
        }
        return "/login/modifydata";
    }

    @RequestMapping(value = "/forgetpw",method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
    public String forgetPassword_get(){
        logger.info("modifydata get 跳转");
        return "/login/forgetpassword";
    }


    @RequestMapping(value = "/forgetPassword",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String forgetPassword_post(@ModelAttribute UserInfo userInfo,HttpServletRequest request){
        logger.info("忘记密码_提交邮箱 post 跳转");
        UserInfo getuser = userService.finduserByName(userInfo);
        if(getuser != null) {
            MailUtils.sendByQQ(request, userInfo.getUserEmail());
            return "请登录该邮箱完成修改密码";
        }else {
            return "不存在该邮箱用户";
        }
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
            System.out.println("========modifyPassword post controller=========== "+userInfo.toString());
            if(userInfo.getUserEmail()!=null && userInfo.getPassword()!=null) {
                userInfo.setPassword(PasswordUtil.generate(userInfo.getPassword()));
                userService.modifypassword(userInfo);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
            return "修改失败";
        }
        return "修改成功";
    }

}
