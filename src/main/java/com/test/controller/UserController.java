package com.test.controller;

import com.test.domain.Page;
import com.test.domain.Search;
import com.test.domain.UserInfo;
import com.test.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by wgt on 2017/3/10.
 */
@Controller
public class UserController {

    @Resource
    private UserService userService;

    private int totalNumber;

    private Logger logger = LoggerFactory.getLogger(UserController.class);


    @RequestMapping(value = "/userList",produces = "text/html;charset=UTF-8")
    public String showUserList(Model model, Page page, Search search,
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
        totalNumber = userService.countUser(title);
        page.setCurrentPage(currentPage);
        page.setTotalNumber(totalNumber);
        logger.info(page.toString());
        Map<String,Object> parameter = new HashMap<String, Object>();
        parameter.put("title",title);
        parameter.put("page",page);
        parameter.put("flag",flag);
        logger.info("page:"+page.toString()+"\n search:"+search.toString());
        logger.info(userService.finduserAll(parameter).toString());
        model.addAttribute("userList",userService.finduserAll(parameter));
            model.addAttribute("num", num);
            model.addAttribute("page",page);
            model.addAttribute("flag",flag);
            model.addAttribute("title",title);
            model.addAttribute("keywords","userList");
        return "manager/userList";
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String add(UserInfo userInfo){
        logger.info("add "+userInfo.toString());
        userService.addUserInfo(userInfo);
        return "success";
    }
}
