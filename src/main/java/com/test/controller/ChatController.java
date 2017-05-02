package com.test.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/4/22.
 */
@Controller
public class ChatController {

    //获取session
    HttpSession session ;
    ServletContext application;

    private Logger logger = LoggerFactory.getLogger(ChatController.class);
    /**
     * 控制跳转到聊天主界面
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/chatMain",produces = "text/html;charset=UTF-8")
    public String toChatMain(HttpServletRequest request, Model model){
        return "front/chatMain";
    }

    /**
     * 前端发送聊天内容
     * @param request
     * @param model
     */
    @RequestMapping(value = "sentContent",produces = "text/html;charset=UTF-8")
    public void sentContent(HttpServletRequest request,Model model,PrintWriter out) throws UnsupportedEncodingException {
        //存储信息的全局变量
        request.setCharacterEncoding("UTF-8");
        application = request.getSession().getServletContext();
        session = request.getSession();
        if(application.getAttribute("message")==null){
            application.setAttribute("message", "");
        }
        //获取application中存储的聊天内容
        String sourceMessage = application.getAttribute("message").toString();
        //获取前端发送的聊天内容
        String content = request.getParameter("content");
        logger.info("content:"+content);
        content = content.replace("<:", "<img src='/chatroom/static/pic/");
        content = content.replace(":>", ".gif'/>");
        logger.info("content:"+content);
        // 获取session中的登陆者
        String name = session.getAttribute("name").toString();
        application.setAttribute("name", name);
        logger.info("name:"+name);
        sourceMessage += this.getTime()+ "<font color='blue'><strong> "+name+"</strong></font> :"+content+"</br>";
        logger.info("sourceMessage: "+sourceMessage);
        application.setAttribute("message", sourceMessage);
        //写入返回结果
        out.write("success");
    }

    /**
     * 前端定时获取聊天信息
     * @param request
     * @param model
     * @param out
     */
    @RequestMapping(value = "/getMassageList",produces = "text/html;charset=UTF-8")
    public void getMassageList(HttpServletRequest request,Model model,PrintWriter out) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        application = request.getSession().getServletContext();
        if(application.getAttribute("message")==null){
            application.setAttribute("message", "");
        }
        //获取application中存储的聊天内容
        String sourceMessage = application.getAttribute("message").toString();
        String str = null;
        try {
            str = new String (sourceMessage.getBytes(), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        out.write(str);
    }

    /**
     * 前端定时获取在线人员
     * @param request
     * @param model
     * @param out
     */
    @RequestMapping(value = "getOnlineList",produces = "text/html;charset=UTF-8")
    public void getOnlineList(HttpServletRequest request,Model model,PrintWriter out) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        application = request.getSession().getServletContext();
        if(application.getAttribute("onLine")==null){
            application.setAttribute("onLine", "");
        }
        //获取application中存储在线人员
        String sourceOnline  =  application.getAttribute("name").toString();
        logger.info("name:"+sourceOnline);
        out.write(sourceOnline);
    }

    /**
     * 自定义日期格式
     * @return
     */
    private String getTime(){
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return df.format(date);
    }
}
