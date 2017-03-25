package com.test.controller;

import com.test.config.PasswordUtil;
import com.test.domain.*;
import com.test.service.ManagerService;
import com.test.service.TeacherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/15.
 */
@Controller
public class ManagerController {

    private Logger logger = LoggerFactory.getLogger(ManagerController.class);

    @Autowired
    private ManagerService managerService;

    private int totalNumber;


    @RequestMapping(value = "/managerhome",method = RequestMethod.GET)
    public String managerhome(){
        logger.info("userList get 跳转");
        return "/manager/manager_home";
    }

    @RequestMapping(value = "/managerList",produces = "text/html;charset=UTF-8")
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
        totalNumber = managerService.countManager(title);
        page.setCurrentPage(currentPage);
        page.setTotalNumber(totalNumber);
        logger.info(page.toString());
        Map<String,Object> parameter = new HashMap<String, Object>();
        parameter.put("title",title);
        parameter.put("page",page);
        parameter.put("flag",flag);
        logger.info("page:"+page.toString()+"\n search:"+search.toString());
        logger.info(managerService.findmanagerAll(parameter).toString());
        model.addAttribute("managerList",managerService.findmanagerAll(parameter));
        model.addAttribute("num", num);
        model.addAttribute("page",page);
        model.addAttribute("flag",flag);
        model.addAttribute("title",title);
        model.addAttribute("keywords","managerList");
        return "manager/managerList";
    }

    @RequestMapping(value = "/addmanager",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String addTeacher(@ModelAttribute Manager manager) {
        logger.info("before:"+manager.toString());
        try {
            manager.setUserInfo(new UserInfo(manager.getManagerName(), PasswordUtil.generate("12345678"),manager.getUserInfo().getUserEmail() , 3));
            logger.info("after: " + manager.toString());
            managerService.addManager(manager);
        }catch (Exception e)
        {
            e.printStackTrace();
            return "添加失败";
        }
        return "添加成功";
    }
}
