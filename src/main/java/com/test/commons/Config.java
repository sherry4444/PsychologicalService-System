package com.test.commons;

/**
 * Created by Administrator on 2017/3/29.
 */

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
public class Config {
    private Properties cfg = new Properties();
    public Config(){}
    //从src/main/resources/开始
    public Config(String file){
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File f = new File(classLoader.getResource(file).getFile());
            cfg.load(new FileInputStream(f));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    public String getString (String key){
        return cfg.getProperty(key);
    }
    public int getInt(String key){
        return Integer.parseInt(cfg.getProperty(key));
    }
    public double getDouble(String key){
        return Double.parseDouble(getString(key));
    }

    /**
     * 测试main函数
     * @param p
     */
//	public static void main(String [] p){
//		Config con = new Config("Mail.properties");
//		System.out.println(con.getString("mail.transport.protocol"));
//		System.out.println(con.getString("mail.debug"));
//	}
}
