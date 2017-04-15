package com.test.controller;


import com.test.commons.Filefunction;
import com.test.commons.HttpInfo;
import com.test.domain.Page;
import com.test.domain.Search;
import com.test.domain.TestLink;
import com.test.service.TestLinkService;
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
public class TestLinkController {

    private Logger logger = LoggerFactory.getLogger(TestLinkController.class);

    @Autowired
    private TestLinkService testLinkService;

    Filefunction filefunction = new Filefunction();

    private int totalNumber;

    @RequestMapping(value = "/testlinkList",produces = "text/html;charset=UTF-8")
    public String showTestLinkList(Model model, Page page, Search search,
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
        totalNumber = testLinkService.countlink(title);
        page.setCurrentPage(currentPage);
        page.setTotalNumber(totalNumber);
        logger.info(page.toString());
        Map<String,Object> parameter = new HashMap<String, Object>();
        parameter.put("title",title);
        parameter.put("page",page);
        parameter.put("flag",flag);
        logger.info("page:"+page.toString()+"\n search:"+search.toString());
        logger.info(testLinkService.findlinkAll(parameter).toString());
        model.addAttribute("testlinkList",testLinkService.findlinkAll(parameter));
        model.addAttribute("num", num);
        model.addAttribute("page",page);
        model.addAttribute("flag",flag);
        model.addAttribute("title",title);
        model.addAttribute("keywords","testlinkList");

        String realPath = request.getSession().getServletContext().getRealPath("");
        model.addAttribute("realPath",realPath);
        return "manager/testlinkList";
    }

    @RequestMapping(value = "/addlink",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String addlink(@ModelAttribute TestLink testLink,
                             @RequestParam(value = "Img",required = false)MultipartFile myfile
                             , HttpServletRequest request) {
        logger.info("before:"+testLink.toString());
        String imgPath = "";
        try {
            if(myfile != null) {
                imgPath = filefunction.fileupoload(myfile, request);
                testLink.setLinkImg(HttpInfo.IMG_URL + imgPath);
            }
            testLinkService.addlink(testLink);
            logger.info("imgpath: "+imgPath);
            logger.info("linkimgpath: "+testLink.toString());
        }catch (Exception e)
        {
            e.printStackTrace();
            return "添加失败"+e;
        }
        return "添加成功";
    }

    @RequestMapping(value = "/dellink",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String deletelink(@ModelAttribute TestLink testLink,HttpServletRequest request) {
        logger.info("before:"+testLink.toString());
        try {
            filefunction.filedelete(testLink.getLinkImg(),request);
            testLinkService.deletelink(testLink);
        }catch (Exception e)
        {
            e.printStackTrace();
            logger.info("删除失败"+e.toString());
            return "删除失败"+e;
        }
        logger.info("删除成功");
        return "删除成功";
    }



    @RequestMapping(value = "/psychtest",produces = "text/html;charset=UTF-8")
    public String showtestlink(Model model, Page page, Search search,
                                 @RequestParam(value = "title",required = false)String title,
                                 @RequestParam(value = "currentPage",defaultValue = "1",required=false)int currentPage,
                                 @RequestParam(value = "flag",required=false,defaultValue = "0")Integer flag,
                                 @RequestParam(value = "num",required=false)Integer num
                                 ,HttpServletRequest request){
        logger.info("/psychtest");

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
        totalNumber = testLinkService.countlink(title);
        page.setCurrentPage(currentPage);
        page.setTotalNumber(totalNumber);
        logger.info(page.toString());
        Map<String,Object> parameter = new HashMap<String, Object>();
        parameter.put("title",title);
        parameter.put("page",page);
        parameter.put("flag",flag);
        logger.info("page:"+page.toString()+"\n search:"+search.toString());
        logger.info(testLinkService.findlinkAll(parameter).toString());
        model.addAttribute("testlinkList",testLinkService.findlinkAll(parameter));
        model.addAttribute("num", num);
        model.addAttribute("page",page);
        model.addAttribute("flag",flag);
        model.addAttribute("title",title);
        model.addAttribute("keywords","testlinkList");

        return "front/psychtest";
    }
}
