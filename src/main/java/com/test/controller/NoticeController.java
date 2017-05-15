package com.test.controller;


import com.alibaba.fastjson.JSON;
import com.test.commons.Filefunction;
import com.test.commons.HttpInfo;
import com.test.config.PasswordUtil;
import com.test.domain.*;
import com.test.service.NoticeService;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/17.
 */
@Controller
public class NoticeController {

    private Logger logger = LoggerFactory.getLogger(NoticeController.class);

    @Autowired
    private NoticeService noticeService;

    Filefunction filefunction = new Filefunction();

    private int totalNumber;

    @RequestMapping(value = "/noticeList",produces = "text/html;charset=UTF-8")
    public String showNoticeList(Model model, Page page, Search search,
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
        totalNumber = noticeService.countNotice(title);
        page.setCurrentPage(currentPage);
        page.setTotalNumber(totalNumber);
        logger.info(page.toString());
        Map<String,Object> parameter = new HashMap<String, Object>();
        parameter.put("title",title);
        parameter.put("page",page);
        parameter.put("flag",flag);
        logger.info("page:"+page.toString()+"\n search:"+search.toString());
        logger.info(noticeService.findnoticeAll(parameter).toString());
        model.addAttribute("noticeList",noticeService.findnoticeAll(parameter));
        model.addAttribute("num", num);
        model.addAttribute("page",page);
        model.addAttribute("flag",flag);
        model.addAttribute("title",title);
        model.addAttribute("keywords","noticeList");

        String realPath = request.getSession().getServletContext().getRealPath("");
        model.addAttribute("realPath",realPath);
        return "manager/noticeList";
    }

    @RequestMapping(value = "/addnotice",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String addnotice(@ModelAttribute Notice notice,
                             @RequestParam(value = "Img",required = false)MultipartFile myfile
                             ,HttpServletRequest request) {
        logger.info("before:"+notice.toString());
        String imgPath = "";
        try {
            if(myfile != null) {
                imgPath = filefunction.fileupoload(myfile, request);
                notice.setNoticeImage(HttpInfo.IMG_URL + imgPath);
            }
            noticeService.addnotice(notice);
            logger.info("imgpath: "+imgPath);
            logger.info("noticeimgpath: "+notice.toString());
        }catch (Exception e)
        {
            e.printStackTrace();
            return "添加失败"+e;
        }
        return "添加成功";
    }

    @RequestMapping(value = "/deletenotice",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String deletenotice(@ModelAttribute Notice notice,HttpServletRequest request,Model model) {
        logger.info("before:"+notice.toString());
        try {
            filefunction.filedelete(notice.getNoticeImage(),request);
            noticeService.deletenotice(notice);
        }catch (Exception e)
        {
            e.printStackTrace();
            logger.info("删除失败"+e.toString());
            return "删除失败"+e;
        }
        logger.info("删除成功");
        return "删除成功";
    }

    @RequestMapping(value = "/modifynotice",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String modifynotice(@ModelAttribute Notice notice,
                                @RequestParam(value = "Img",required = false)MultipartFile myfile,
                                HttpServletRequest request) {
        logger.info("before:"+notice.toString());
        String imgPath = "";
        try {
            if (notice.getNoticeImage() != null) {
                filefunction.filedelete(notice.getNoticeImage(), request);
            }
            if(myfile != null) {
                imgPath = filefunction.fileupoload(myfile, request);
                notice.setNoticeImage(HttpInfo.IMG_URL + imgPath);
            }else {
                notice.setNoticeImage(null);
            }
            if(notice.getNoticeTitle() == null) notice.setNoticeTitle(null);
            if (notice.getNoticeContent() == null) notice.setNoticeContent(null);
            noticeService.updatenotice(notice);
        }catch (Exception e)
        {
            e.printStackTrace();
            logger.info("修改失败"+e.toString());
            return "修改失败"+e;
        }
        logger.info("修改成功");
        return "修改成功";
    }

    @RequestMapping(value = "/notice",produces = "text/html;charset=UTF-8")
    public String showNotice(Model model, Page page, Search search,
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
        totalNumber = noticeService.countNotice(title);
        page.setCurrentPage(currentPage);
        page.setTotalNumber(totalNumber);
        logger.info(page.toString());
        Map<String,Object> parameter = new HashMap<String, Object>();
        parameter.put("title",title);
        parameter.put("page",page);
        parameter.put("flag",flag);
        logger.info("page:"+page.toString()+"\n search:"+search.toString());
        logger.info(noticeService.findnoticeAll(parameter).toString());
        model.addAttribute("noticeList",noticeService.findnoticeAll(parameter));
        model.addAttribute("num", num);
        model.addAttribute("page",page);
        model.addAttribute("flag",flag);
        model.addAttribute("title",title);
        model.addAttribute("keywords","noticeList");

        String realPath = request.getSession().getServletContext().getRealPath("");
        model.addAttribute("realPath",realPath);
        return "front/notice";
    }
}
