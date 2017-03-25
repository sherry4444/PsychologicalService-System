package com.test.controller;


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

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.UnsupportedEncodingException;
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

    private int totalNumber;

    @RequestMapping(value = "/noticeList",produces = "text/html;charset=UTF-8")
    public String showNoticeList(Model model, Page page, Search search,
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
        return "manager/noticeList";
    }

    @RequestMapping(value = "/addnotice",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String addTeacher(@ModelAttribute Notice notice,
                             @RequestParam(value = "Img",required = false)MultipartFile myfile
                             , HttpServletRequest request) {
        logger.info("before:"+notice.toString());
        String imgPath = "";
        try {
            if(myfile.isEmpty()){
                System.out.println("文件未上传");
            }else{
                System.out.println("文件长度: " + myfile.getSize());
                System.out.println("文件类型: " + myfile.getContentType());
                System.out.println("文件名称: " + myfile.getName());
                System.out.println("文件原名: " + myfile.getOriginalFilename());
                System.out.println("========================================");
                // 获取图片的文件名
                String fileName = myfile.getOriginalFilename();
                // 获取图片的扩展名
                String extensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
                // 新的图片文件名 = 获取时间戳+"."图片扩展名
                imgPath = String.valueOf(System.currentTimeMillis()) + "." + extensionName;

                //如果用的是Tomcat服务器，则文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\WEB-INF\\upload\\文件夹中
                String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload");
                //imgPath = myfile.getOriginalFilename();
                //这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉，我是看它的源码才知道的
                FileUtils.copyInputStreamToFile(myfile.getInputStream(), new File(realPath,imgPath));

                FileUtils.deleteQuietly(new File(realPath+"/1.jpg"));
            }


            notice.setNoticeImage("/WEB-INF/upload/"+imgPath);
            noticeService.addnotice(notice);

        }catch (Exception e)
        {
            e.printStackTrace();
            return "添加失败"+e;
        }
        return "添加成功";
    }
}
