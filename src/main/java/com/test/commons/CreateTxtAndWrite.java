package com.test.commons;

import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * Created by server2 on 2017/1/5.
 */
public class CreateTxtAndWrite {

    final org.slf4j.Logger logger = LoggerFactory.getLogger(CreateTxtAndWrite.class);

    static boolean creatFile(String filename) throws IOException {
        boolean flag = false;
        File file = new File(filename);
        if (!file.exists() && !file.isDirectory()) {
            file.mkdirs();
            System.out.println("文件夹已创建");
            flag = true;
        }
        return flag;
    }


    static boolean creatTxtFile(String filename) throws IOException {
        boolean flag = false;
        File file = new File(filename+".txt");
        if (!file.exists()) {
            file.createNewFile();
            flag = true;
        }
        return flag;
    }



    public static void contentToTxt(String filePath, String content) {

        String str; //原有txt内容
        String s1 = "";//内容更新
        try {
            File f = new File(filePath);
            if (f.exists()) {
                System.out.print("文件存在");
            } else {
                System.out.print("文件不存在");
                f.createNewFile();// 不存在则创建
            }
            BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(f),"utf-8"));
            while ((str = input.readLine()) != null) {
                s1 += str + "\n";
            }
            System.out.println(s1);
            input.close();
            s1 += content;

            BufferedWriter output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f),"utf-8"));
            output.write(s1);
            output.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }


    public static String readTxtFile(String filePath) {
        String line = "";
        InputStreamReader read = null;
        try {
            String encoding="UTF-8";
            File file=new File(filePath);
            if(file.isFile() && file.exists()){ //判断文件是否存在
               read = new InputStreamReader(
                        new FileInputStream(file),encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while((lineTxt = bufferedReader.readLine()) != null){
                    line += lineTxt;
                    System.out.println(lineTxt);
                }
                read.close();
            }else{
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }finally {
            try {
                read.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return line;
    }
}

