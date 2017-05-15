package com.test.commons;

/**
 * Created by Administrator on 2017/3/29.
 */


import com.sun.mail.util.MailSSLSocketFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Properties;


public class MailUtils {

    private static String SendServer;
    private static String port;
    private static String username;
    private static String passcode;
    private static String auth;
    private static String protocol;
    private static String debug;
    private static String receiver;
    private static Session session;

    /**
     * 初始化所有配置信息,类型作为参数传入
     */
    private static void InitandSend(String type, String title, String contents, String to) {

        Config config = new Config("Mail.properties");
        SendServer = config.getString(type + ".mail.host");
        port = config.getString(type + ".mail.port");
        username = config.getString(type + ".mail.username");
        passcode = config.getString(type + ".mail.password");
        auth = config.getString("mail.smtp.auth");
        protocol = config.getString("mail.transport.protocol");
        debug = config.getString("mail.debug");
        receiver = config.getString("mail.receiver");

        // 1,獲得session對象
        Properties props = new Properties();
        // 邮箱服务器
        props.setProperty("mail.smtp.host", SendServer);
        // 发送服务需要身份验证
        props.setProperty("mail.smtp.auth", auth);
        // 发送邮件协议名称
        props.setProperty("mail.transport.protocol", protocol);
        // 设置端口
        props.setProperty("mail.smtp.port", port);
        // 便于调试
        // props.put("mail.debug", "true");//便于调试

        //SSL加密
        MailSSLSocketFactory sf;
        try {
            sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            props.put("mail.smtp.ssl.enable", "true");
//            if(!type.equals("163")) {
                props.put("mail.smtp.starttls.enable", "true");//要设置这个东西 否则SSL 验证不过
//            }
            props.put("mail.smtp.ssl.socketFactory", sf);
        } catch (GeneralSecurityException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        // Session session = Session.getDefaultInstance(props);
        session = Session.getDefaultInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                //用户名，密码（授权码）
                return new PasswordAuthentication(username, passcode);// 邮箱和第三方登录授权码
            }
        });

        Message message = new MimeMessage(session);// 2， 创建代表邮件的对象Message
        try {
            message.setFrom(new InternetAddress(username));// 设置发件人
            message.addRecipient(RecipientType.TO, new InternetAddress(to));            // 设置收件人
            message.setSubject(title);// 设置标题
            message.setSentDate(new Date());// 设置发送时间
            // 设置正文(有链接选择text/html;charset=utf-8)
            message.setContent(contents, "text/html;charset=utf-8");
            Transport.send(message);// 3，发送邮件Transport
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * 发送邮件的方法
	 */
    public static void sendByQQ(HttpServletRequest request, String to) {
        System.out.println("QQ send");
        String title = "欢迎使用校园心理辅导系统，密码找回！";
        //String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/modifyPassword";
        String basePath = request.getScheme() + "://" + "127.0.0.1" + ":" + request.getServerPort() + request.getContextPath() + "/modifyPassword";
        String baidu = "http://www.baidu.com";
        String contents = "请勿回复本邮件.点击下面的链接,重设密码<br/><a href=" + basePath + " target='_BLANK'>点击我重新设置密码</a>" +
                "<br/><a href=" + baidu + " target='_BLANK'>百度</a>";
        InitandSend("qq", title, contents, to);
    }


    public static void sendBy163(String to) {
        System.out.println("163 send");
        String title = "欢迎使用";
        String contents = "<h1>你看到的是一封测试邮件</h1><h3><a href=''>链接</a></h3>";
        InitandSend("163", title, contents, to);
    }

    public static void sendBySina(String to) {
        System.out.println("Sina send");
        String title = "欢迎使用，请激活！";
        String contents = "<h2>你看到的是一封测试邮件</h2><br/>正如你所见，使用163mail组件发送的";
        InitandSend("sina", title, contents, to);

    }

    ////////////////////////////////////////////////////////
 /*   public static void main(String[] a) {
        System.out.println("开始");
        sendBy163("15627236812@163.com");
        //sendBySina("597356241@qq.com");
        System.out.println("end");

    }*/
}
