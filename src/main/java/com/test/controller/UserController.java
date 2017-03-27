package com.test.controller;

import com.test.dao.ManagerDao;
import com.test.dao.StudentDao;
import com.test.dao.TeacherDao;
import com.test.domain.*;
import com.test.service.ManagerService;
import com.test.service.StudentService;
import com.test.service.TeacherService;
import com.test.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

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

    @Resource
    private StudentDao studentDao;

    @Resource
    private TeacherDao teacherDao;

    @Resource
    private ManagerDao managerDao;

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

    @RequestMapping(value = "/addUser",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String addUser(@ModelAttribute UserInfo userInfo){
        logger.info("addUser"+userInfo.toString());
        try {
            userService.addUserInfo(userInfo);
            Integer userid = userService.finduserid(userInfo);
            if(userInfo.getRole() == 1)
            {
                Student student = new Student();
                student.setStu_userId(userid);
                student.setStudentName(userInfo.getUserName());
                studentDao.addstudent(student);
            }
            if (userInfo.getRole() == 2)
            {
                Teacher teacher = new Teacher();
                teacher.setTc_userId(userid);
                teacher.setTeacherName(userInfo.getUserName());
                teacherDao.addteacher(teacher);
            }
            if (userInfo.getRole() == 3)
            {
                Manager manager = new Manager();
                manager.setMg_userId(userid);
                manager.setManagerName(userInfo.getUserName());
                managerDao.addmanager(manager);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
            logger.info("添加失败"+e.toString());
            return "添加失败"+e;
        }
        logger.info("添加成功");
        return "添加成功";
    }

    @RequestMapping(value = "/deleteUser",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String deleteUser(@ModelAttribute UserInfo userInfo) {
        logger.info("deleteUser "+userInfo.toString());
        try {
            userService.deleteUser(userInfo);
            if(userInfo.getRole() == 1)
            {
                Student student = new Student();
                student.setStu_userId(userInfo.getUserId());
                studentDao.deleteUserinStudent(student);
            }
            if (userInfo.getRole() == 2)
            {
                Teacher teacher = new Teacher();
                teacher.setTc_userId(userInfo.getUserId());
                teacherDao.deleteUserinTeacher(teacher);
            }
            if (userInfo.getRole() == 3)
            {
                Manager manager = new Manager();
                manager.setMg_userId(userInfo.getUserId());
                managerDao.deleteUserinManager(manager);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
            logger.info("删除失败"+e.toString());
            return "删除失败"+e;
        }
        logger.info("删除成功");
        return "删除成功";
    }

    @RequestMapping(value = "/modifyUser",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String modifyUser(@ModelAttribute UserInfo userInfo) {
        logger.info("modifyUser "+userInfo.toString());
        try {
            userService.updateUser(userInfo);
            if(userInfo.getRole() == 1)
            {
                Student student = new Student();
                student.setStudentName(userInfo.getUserName());
                student.setStu_userId(userInfo.getUserId());
                studentDao.updateUserinStudent(student);
            }
            if (userInfo.getRole() == 2)
            {
                Teacher teacher = new Teacher();
                teacher.setTeacherName(userInfo.getUserName());
                teacher.setTc_userId(userInfo.getUserId());
                teacherDao.updateUserinTeacher(teacher);
            }
            if (userInfo.getRole() == 3)
            {
                Manager manager = new Manager();
                manager.setManagerName(userInfo.getUserName());
                manager.setMg_userId(userInfo.getUserId());
                managerDao.updateUserinManager(manager);
            }
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
