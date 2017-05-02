package com.test.controller;

import com.test.config.PasswordUtil;
import com.test.domain.Manager;
import com.test.domain.Page;
import com.test.domain.Search;
import com.test.domain.UserInfo;
import com.test.service.ManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

    HttpSession session ;


    @RequestMapping(value = "/managerhome",method = RequestMethod.GET)
    public String managerhome(){
        logger.info("userList get 跳转");
        return "/manager/manager_home";
    }

    @RequestMapping(value = "/managerList",produces = "text/html;charset=UTF-8")
    public String showManagerList(Model model, Page page, Search search,HttpServletRequest request,
                                  @RequestParam(value = "title",required = false)String title,
                                  @RequestParam(value = "currentPage",defaultValue = "1",required=false)int currentPage,
                                  @RequestParam(value = "flag",required=false,defaultValue = "0")Integer flag,
                                  @RequestParam(value = "num",required=false)Integer num){
       /* session = request.getSession();
        String sourceOnline  = session.getAttribute("name").toString();
        logger.info("name:"+sourceOnline);*/

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
    public String addManager(@ModelAttribute Manager manager) {
        logger.info("before:"+manager.toString());
        try {
            if (manager.getUserInfo().getPassword() != null)
            {
                manager.getUserInfo().setPassword(PasswordUtil.generate(manager.getUserInfo().getPassword()));
            }
            logger.info("after: " + manager.toString());
            managerService.addManager(manager);
        }catch (Exception e)
        {
            e.printStackTrace();
            return "添加失败";
        }
        return "添加成功";
    }

    @RequestMapping(value = "/deletemanager",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String deleteManager(@ModelAttribute Manager manager) {
        logger.info("before:"+manager.toString());
        try {
            managerService.deletemanager(manager);
        }catch (Exception e)
        {
            e.printStackTrace();
            logger.info("删除失败"+e.toString());
            return "删除失败"+e;
        }
        logger.info("删除成功");
        return "删除成功";
    }

    @RequestMapping(value = "/modifymanager",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String modifymManager(@ModelAttribute Manager manager) {
        logger.info("before:"+manager.toString());
        try {
            if (manager.getUserInfo().getPassword() != null)
            {
               manager.getUserInfo().setPassword(PasswordUtil.generate(manager.getUserInfo().getPassword()));
            }
            managerService.updatemanager(manager);
        }catch (Exception e)
        {
            e.printStackTrace();
            logger.info("修改失败"+e.toString());
            return "修改失败"+e;
        }
        logger.info("修改成功");
        return "修改成功";
    }


}
