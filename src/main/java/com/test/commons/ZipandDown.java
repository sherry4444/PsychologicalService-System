package com.test.commons;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by server2 on 2017/1/4.
 */
public class ZipandDown {
    private static final Logger log = LoggerFactory.getLogger(ZipandDown.class);
    //文件打包下载
    static HttpServletResponse downLoadFiles(List<File> files, File file, HttpServletResponse response)
            throws Exception {
        try {
            /**这个集合就是你想要打包的所有文件，
             * 这里假设已经准备好了所要打包的文件*/
            //List<File> files = new ArrayList<File>();

            if (!file.exists()) {
                file.createNewFile();
            }
            response.reset();//清空response
            //创建文件输出流
            FileOutputStream fous = new FileOutputStream(file);
            ZipOutputStream zipOut = new ZipOutputStream(fous);
            zipFile(files, zipOut);
            zipOut.close();
            fous.close();
            return downloadZip(file, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }


    /**
     * 把接受的全部文件打成压缩包
     */
    private static void zipFile(List files, ZipOutputStream outputStream) {
        int size = files.size();
        for (int i = 0; i < size; i++) {
            File file = (File) files.get(i);
            zipFile(file, "", outputStream);
        }
    }

    // 删除服务端下载的文件
     static void deleteDirectory(File file) {
        if (file.isFile()) {// 表示该文件不是文件夹
            file.delete();
        } else {
            // 首先得到当前的路径
            String[] childFilePaths = file.list();
            for (String childFilePath : childFilePaths) {
                File childFile = new File(file.getAbsolutePath() + "/" + childFilePath);
                deleteDirectory(childFile);
            }
            file.delete();
        }
    }

    /**
     * 根据输入的文件与输出流对文件进行打包
     */
    private static void zipFile(File file, String parentPath, ZipOutputStream zos) {
        if (file.exists()) {
            if (file.isDirectory()) {//处理文件夹
                parentPath += file.getName() + File.separator;
                File[] files = file.listFiles();
                if (files.length != 0) {
                    for (File f : files) {
                        zipFile(f, parentPath, zos);
                    }
                } else {       //空目录则创建当前目录
                    try {
                        zos.putNextEntry(new ZipEntry(parentPath));
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            } else {
                FileInputStream fis = null;
                try {
                    fis = new FileInputStream(file);
                    ZipEntry ze = new ZipEntry(parentPath + file.getName());
                    zos.putNextEntry(ze);
                    byte[] content = new byte[1024];
                    int len;
                    while ((len = fis.read(content)) != -1) {
                        zos.write(content, 0, len);
                        zos.flush();
                    }

                } catch (IOException e) {
                    log.error("创建ZIP文件失败", e);
                } finally {
                    try {
                        if (fis != null) {
                            fis.close();
                        }
                    } catch (IOException e) {
                        log.error("创建ZIP文件失败", e);
                    }
                }
            }
        }
    }

     //下载ZIP包
    private static HttpServletResponse downloadZip(File file, HttpServletResponse response) {
        try {
            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(file.getPath()));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();

            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");

            //如果输出的是中文名的文件，在此处就要用URLEncoder.encode方法进行处理
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(file.getName(), "UTF-8"));
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                File f = new File(file.getPath());
                f.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return response;
    }

    //下载指定文件,文件夹则拒绝访问
    static HttpServletResponse downloadFile(String path, HttpServletResponse response) {
        try {
            // path是指欲下载的文件的路径。
            File file = new File(path);
            // 取得文件名。
            String filename = file.getName();
            // 取得文件的后缀名。
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + filename);
            response.addHeader("Content-Length", "" + file.length());
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return response;
    }
}
