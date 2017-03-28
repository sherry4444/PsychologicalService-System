package com.test.controller;


import com.test.config.PasswordUtil;
import com.test.domain.Page;
import com.test.domain.Search;
import com.test.domain.Student;
import com.test.domain.UserInfo;
import com.test.service.CollegeMajorService;
import com.test.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/17.
 */
@Controller
public class StudentController {

    private Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Resource
    private StudentService studentService;

    @Resource
    private CollegeMajorService collegeMajorService;

    private int totalNumber;


    @RequestMapping(value = "/studentList",produces = "text/html;charset=UTF-8")
    public String showStudentList(Model model, Page page, Search search,
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
        totalNumber = studentService.countStudent(title);
        page.setCurrentPage(currentPage);
        page.setTotalNumber(totalNumber);
        logger.info(page.toString());
        Map<String,Object> parameter = new HashMap<String, Object>();
        parameter.put("title",title);
        parameter.put("page",page);
        parameter.put("flag",flag);
        logger.info("page:"+page.toString()+"\n search:"+search.toString());
        logger.info(studentService.findstudentAll(parameter).toString());
        model.addAttribute("studentList",studentService.findstudentAll(parameter));
        model.addAttribute("num", num);
        model.addAttribute("page",page);
        model.addAttribute("flag",flag);
        model.addAttribute("title",title);
        model.addAttribute("keywords","studentList");

        String collegemajor = null;
        model.addAttribute("college",collegeMajorService.findcollegeAll(collegemajor));
        model.addAttribute("major",collegeMajorService.findmajorAll(collegemajor));
        return "manager/studentList";
    }

    @RequestMapping(value = "/addstudent",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String addStudent(@ModelAttribute Student student) {
        logger.info(student.toString());
        try {
            student.setUserInfo(new UserInfo(student.getStudentName(),PasswordUtil.generate("12345678"), student.getStudentNumber() + "@ps.com", 1));
            logger.info("after: " + student.toString());
            studentService.addStudent(student);
        }catch (Exception e)
        {
             e.printStackTrace();
             return "添加失败";
        }
        return "添加成功";
    }

    @RequestMapping(value = "/deletestudent",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String deleteStudent(@ModelAttribute Student student) {
        logger.info(student.toString());
        try {
            studentService.deletestudent(student);
        }catch (Exception e)
        {
            e.printStackTrace();
            logger.info("删除失败"+e.toString());
            return "删除失败"+e;
        }
        logger.info("删除成功");
        return "删除成功";
    }

    @RequestMapping(value = "/modifystudent",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String modifyStudent(@ModelAttribute Student student) {
        logger.info(student.toString());
        try {
            if (student.getUserInfo().getPassword() != null) {
                student.getUserInfo().setPassword(PasswordUtil.generate(student.getUserInfo().getPassword()));
            }
            student.getUserInfo().setRole(1);
            studentService.updatestudent(student);
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
