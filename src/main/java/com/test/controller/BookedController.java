package com.test.controller;


import com.test.commons.Filefunction;
import com.test.commons.HttpInfo;
import com.test.dao.TeacherDao;
import com.test.domain.Booked;
import com.test.domain.Page;
import com.test.domain.Search;
import com.test.domain.TestLink;
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

        if (num != null) {page.setPageNumber(num);} else {num=page.getPageNumber();}
        totalNumber = bookedService.countBooked(title);
        page.setCurrentPage(currentPage);
        page.setTotalNumber(totalNumber);
        logger.info(page.toString());
        Map<String,Object> parameter = new HashMap<String, Object>();
        parameter.put("title",title);
        parameter.put("page",page);
        parameter.put("flag",flag);
        logger.info("page:"+page.toString()+"\n search:"+search.toString());
        logger.info(bookedService.findBookedAll(parameter).toString());
        model.addAttribute("bookedList",bookedService.findBookedAll(parameter));
        model.addAttribute("num", num);
        model.addAttribute("page",page);
        model.addAttribute("flag",flag);
        model.addAttribute("title",title);
        model.addAttribute("keywords","bookedList");

        String realPath = request.getSession().getServletContext().getRealPath("");
        model.addAttribute("realPath",realPath);
        return "manager/bookedList";
    }

    @RequestMapping(value = "/addbooked",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String addTeacher(@ModelAttribute Booked booked) {
        logger.info("before:"+booked.toString());

        try {
            booked.setBookState(1);
            bookedService.addBooked(booked);
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


    @RequestMapping(value = "/bookedsuccess,",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")

    @ResponseBody
    public String bookedsuccess() {
        try {
            Booked booked = new Booked();
            booked.setBookState(2);
            bookedService.changeBookedState(booked);
        }catch (Exception e)
        {
            e.printStackTrace();
            logger.info("修改失败"+e.toString());
            return "修改失败"+e;
        }
        logger.info("修改成功");
        return "修改成功";
    }

    @RequestMapping(value = "/bookedfail",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String bookedfail(@ModelAttribute Booked booked) {
        try {
            booked.setBookState(3);
            bookedService.feedbackBooked(booked);
        }catch (Exception e)
        {
            e.printStackTrace();
            logger.info("修改失败"+e.toString());
            return "修改失败"+e;
        }
        logger.info("修改成功");
        return "修改成功";
    }


    @RequestMapping(value = "/booked",produces = "text/html;charset=UTF-8")
    public String showBooked(Model model, Page page,
                             @RequestParam(value = "title",required = false)String title,
                             @RequestParam(value = "currentPage",defaultValue = "1",required=false)int currentPage,
                             @RequestParam(value = "flag",required=false,defaultValue = "0")Integer flag,
                             @RequestParam(value = "num",required=false)Integer num){

        if (title != null) {
            try {
                if (title != null && title.equals(new String(title.getBytes("ISO-8859-1"), "ISO-8859-1"))) {
                    title = new String(title.getBytes("ISO-8859-1"), "utf-8");
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        if (num != null) {page.setPageNumber(num);} else {num=page.getPageNumber();}
        totalNumber = bookedService.countBooked(title);
        page.setCurrentPage(currentPage);
        page.setTotalNumber(totalNumber);
        logger.info(page.toString());
        Map<String,Object> parameter = new HashMap<String, Object>();
        parameter.put("title",title);
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

        return "front/booked";
    }
}
