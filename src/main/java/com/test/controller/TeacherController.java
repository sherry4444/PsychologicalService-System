package com.test.controller;


import com.test.domain.Page;
import com.test.domain.Search;

import com.test.service.TeacherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/17.
 */
@Controller
public class TeacherController {

    private Logger logger = LoggerFactory.getLogger(TeacherController.class);

    @Autowired
    private TeacherService teacherService;

    private int totalNumber;

    @RequestMapping(value = "/teacherList",produces = "text/html;charset=UTF-8")
    public String showTeacherList(Model model, Page page, Search search,
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
        totalNumber = teacherService.countTeacher(title);
        page.setCurrentPage(currentPage);
        page.setTotalNumber(totalNumber);
        logger.info(page.toString());
        Map<String,Object> parameter = new HashMap<String, Object>();
        parameter.put("title",title);
        parameter.put("page",page);
        parameter.put("flag",flag);
        logger.info("page:"+page.toString()+"\n search:"+search.toString());
        logger.info(teacherService.findteacherAll(parameter).toString());
        model.addAttribute("teacherList",teacherService.findteacherAll(parameter));
        model.addAttribute("num", num);
        model.addAttribute("page",page);
        model.addAttribute("flag",flag);
        model.addAttribute("title",title);
        model.addAttribute("keywords","teacherList");
        return "manager/teacherList";
    }
}
