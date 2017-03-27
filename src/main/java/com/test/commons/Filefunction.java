package com.test.commons;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/3/24.
 */
public class Filefunction {

    private String filepath;
    private final Logger logger = LoggerFactory.getLogger(Filefunction.class);

    public String fileupoload(MultipartFile myfile,HttpServletRequest request) throws IOException {

        if(myfile.isEmpty()){
            logger.info("文件未上传");
        }else{
            logger.info("文件长度: " + myfile.getSize());
            logger.info("文件类型: " + myfile.getContentType());
            logger.info("文件名称: " + myfile.getName());
            logger.info("文件原名: " + myfile.getOriginalFilename());
            logger.info("========================================");

            // 获取图片的文件名
            String fileName = myfile.getOriginalFilename();
            // 获取图片的扩展名
            String extensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
            // 新的图片文件名 = 获取时间戳+"."图片扩展名
            filepath = String.valueOf(System.currentTimeMillis()) + "." + extensionName;

           /* SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss:SSS");
            filepath = simpleDateFormat.format(new Date())+"_"+myfile.getOriginalFilename();*/
            logger.info("filepath:"+filepath);

            //如果用的是Tomcat服务器，则文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\WEB-INF\\upload\\文件夹中
            String realPath = request.getSession().getServletContext().getRealPath(HttpInfo.IMG_URL);
            //imgPath = myfile.getOriginalFilename();
            //这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉，我是看它的源码才知道的
            FileUtils.copyInputStreamToFile(myfile.getInputStream(), new File(realPath,filepath));

        }
        return filepath;
    }


    public void filedelete(String filepath,HttpServletRequest request)
    {
        try{
        //删除文件
            FileUtils.deleteQuietly(new File(HttpInfo.IMG_URL_absolute+filepath));
        }catch (Exception e)
        {
            e.printStackTrace();
            logger.info("删除失败："+filepath);
        }
        logger.info("删除成功: "+filepath);
    }

}
