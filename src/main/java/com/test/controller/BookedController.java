package com.test.controller;


import com.test.commons.Filefunction;
import com.test.commons.HttpInfo;
import com.test.dao.TeacherDao;
import com.test.domain.*;
import com.test.service.BookedService;
import com.test.service.TeacherService;
import com.test.service.TestLinkService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/17.
 */
@Controller
public class BookedController {

    private Logger logger = LoggerFactory.getLogger(BookedController.class);

    @Autowired
    private BookedService bookedService;

    @Autowired
    private TeacherService teacherService;


    private int totalNumber;

    @RequestMapping(value = "/bookedList",produces = "text/html;charset=UTF-8")
    public String showBookedList(Model model, Page page, Search search,
                               @RequestParam(value = "title",required = false)String title,
                               @RequestParam(value = "currentPage",defaultValue = "1",required=false)int currentPage,
                               @RequestParam(value = "flag",required=false,defaultValue = "0")Integer flag,
                                 @RequestParam(value = "state",required=false,defaultValue = "1")Integer state,
                               @RequestParam(value = "num",required=false)Integer num
                              ,HttpServletRequest request){

        if (title != null) {
            try {
                if (title != null && title.equals(new String(title.getBytes("ISO-8859-1"), "ISO-8859-1"))) {
                    title = new String(title.getBytes("ISO-8859-1"), "utf-8");
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        Booked booked = new Booked();
        booked.setBookState(state);
        Map<String,Object> parameter = new HashMap<String, Object>();
        parameter.put("booked",booked);
        parameter.put("title",title);
        if (num != null) {page.setPageNumber(num);} else {num=page.getPageNumber();}
        totalNumber = bookedService.countBooked(parameter);
        page.setCurrentPage(currentPage);
        page.setTotalNumber(totalNumber);
        logger.info(page.toString()+"/n============================/n"+booked.toString()+"/n/n/n/n");


        parameter.put("page",page);
        parameter.put("flag",flag);
        logger.info("page:"+page.toString()+"\n search:"+search.toString());
        logger.info(bookedService.findBookedAll(parameter).toString());
        model.addAttribute("bookedList",bookedService.findBookedAll(parameter));
        model.addAttribute("num", num);
        model.addAttribute("page",page);
        model.addAttribute("flag",flag);
        model.addAttribute("state",state);
        model.addAttribute("title",title);
        model.addAttribute("keywords","bookedList");

        String realPath = request.getSession().getServletContext().getRealPath("");
        model.addAttribute("realPath",realPath);
        return "manager/bookedList";
    }


    @RequestMapping(value = "/addbooked",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String addbooked(@ModelAttribute Booked booked) {
        logger.info("before:"+booked.toString());
        try {
            //booked.setBookState(1);

            Booked findhad = new Booked();
            findhad.setBookState(3);
            findhad.setBookUserId(booked.getBookUserId());
            System.out.print("=findhad.toString()==========:    "+findhad.toString());
            int findnum = bookedService.findhadfinish(findhad);
            if (findnum > 0){
                return "有未完成的预约，添加失败";
            }else if(findnum == 0) {
                bookedService.addBooked(booked);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
            return "添加失败"+e;
        }
        return "添加成功";
    }

    @RequestMapping(value = "/delbooked",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String deletebooked(@ModelAttribute Booked booked) {
        logger.info("before:"+booked.toString());
        try {
            bookedService.deleteBooked(booked);
        }catch (Exception e)
        {
            e.printStackTrace();
            logger.info("删除失败"+e.toString());
            return "删除失败"+e;
        }
        logger.info("删除成功");
        return "删除成功";
    }


    @RequestMapping(value = "/modifystate",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String modifystate(Booked booked) {
        try {
            bookedService.changeBookedState(booked);
        }catch (Exception e)
        {
            e.printStackTrace();
            logger.info("审核失败"+e.toString());
            return "审核失败"+e;
        }
        logger.info("审核成功");
        return "审核成功";
    }

    @RequestMapping(value = "/finishbook",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String finishbook(@ModelAttribute Booked booked) {
        try {
            bookedService.changeBookedState(booked);
        }catch (Exception e)
        {
            e.printStackTrace();
            logger.info("结束失败"+e.toString());
            return "结束失败"+e;
        }
        logger.info("结束成功");
        return "结束成功";
    }


    @RequestMapping(value = "/booked",produces = "text/html;charset=UTF-8")
    public String showBooked(Model model, Page page,Booked booked,HttpServletRequest request,
                             @RequestParam(value = "currentPage",defaultValue = "1",required=false)int currentPage,
                             @RequestParam(value = "flag",required=false,defaultValue = "0")Integer flag,
                             @RequestParam(value = "num",required=false)Integer num){
        HttpSession session = request.getSession();
        UserInfo userInfo = (UserInfo)session.getAttribute("user");
        System.out.println("==========session: "+(String)session.getAttribute("name")+
        "/n =================== userInfo : "+userInfo.toString());
        //String title = userInfo.getUserId().toString();
        String title = "";

        booked.setBookUserId(userInfo.getUserId());
        Map<String,Object> parameter = new HashMap<String, Object>();
        parameter.put("booked",booked);
        parameter.put("title",title);

        if (num != null) {page.setPageNumber(num);} else {num=page.getPageNumber();}
        totalNumber = bookedService.countBooked(parameter);
        page.setCurrentPage(currentPage);
        page.setTotalNumber(totalNumber);
        logger.info(page.toString());


        parameter.put("page",page);
        parameter.put("flag",flag);
        logger.info(bookedService.findBookedAll(parameter).toString());
        model.addAttribute("bookedlist",bookedService.findBookedAll(parameter));
        model.addAttribute("num", num);
        model.addAttribute("page",page);
        model.addAttribute("flag",flag);
        model.addAttribute("title",title);
        model.addAttribute("keywords","bookedList");

        model.addAttribute("teacherList",teacherService.findteacher());

        return "front/onlinebook";
    }
}



