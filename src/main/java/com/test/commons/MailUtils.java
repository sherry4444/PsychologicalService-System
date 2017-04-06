package com.test.commons;

/**
 * Created by Administrator on 2017/3/29.
 */


import com.sun.mail.util.MailSSLSocketFactory;

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
    /*
	 * 发送邮件的方法
	 */
    public static void sendMail(HttpServletRequest request, String to) {

        final String SendServer="smtp.qq.com";
        final String port = "465";
        final String username="597356241@qq.com";
        final String passcode = "bznmitlbpvwwbfec";
        String title="欢迎使用校园心理辅导系统，密码找回！";

        //String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/modifyPassword";
        String basePath = request.getScheme()+"://"+"127.0.0.1"+":"+request.getServerPort()+request.getContextPath()+"/modifyPassword";
        String baidu="http://www.baidu.com";
        String contents = "请勿回复本邮件.点击下面的链接,重设密码<br/><a href="+basePath+" target='_BLANK'>点击我重新设置密码</a>" +
                "<br/><a href="+baidu+" target='_BLANK'>百度</a>";

        // 1,獲得session對象
        Properties props = new Properties();
        // props.setProperty("mail.host", "localhost");
        // 163邮箱服务器
        props.setProperty("mail.smtp.host", SendServer);
        // 发送服务需要身份验证
        props.setProperty("mail.smtp.auth", "true");
        // 发送邮件协议名称
        props.setProperty("mail.transport.protocol", "smtp");
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
            props.put("mail.smtp.starttls.enable", "true");//要设置这个东西 否则SSL 验证不过
            props.put("mail.smtp.ssl.socketFactory", sf);
        } catch (GeneralSecurityException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        // Session session = Session.getDefaultInstance(props);
        Session session = Session.getDefaultInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                //用户名，密码（授权码）
                return new PasswordAuthentication(username, passcode);// 邮箱和第三方登录授权码
            }
        });

        // 2， 创建代表邮件的对象Message
        Message message = new MimeMessage(session);

        try {
            //设置自定义发件人昵称
            String nick=javax.mail.internet.MimeUtility.encodeText("校园心理辅导系统");

            message.setFrom(new InternetAddress(nick+" <"+username+">"));
            // 设置发件人
            message.setFrom(new InternetAddress(username));
            // 设置收件人
            message.addRecipient(RecipientType.TO, new InternetAddress(to));
            // 设置标题
            message.setSubject(title);
            // 设置发送时间
            message.setSentDate(new Date());
            // 设置正文(有链接选择text/html;charset=utf-8)
            message.setContent(contents, "text/html;charset=utf-8");

            // 3，发送邮件Transport
            Transport.send(message);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

/////////////////////////////////////////////////////////////////////// 163邮箱

   /* public static void send163(String to){

        final String SendServer="smtp.163.com";
        final String port = "25";
        final String username="15979940275@163.com";
        final String passcode = "";
        String title="欢迎使用，请激活！";
        String contents = "<h2>你看到的是一封测试邮件</h2><br/>正如你所见，使用mail组件发送的";


        Properties props = new Properties();
        props.setProperty("mail.host", SendServer);
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.port", port);

        Session session = Session.getDefaultInstance(props, new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, passcode);// 邮箱和第三方登录授权码
            }

        });

        // 2， 创建代表邮件的对象Message
        Message message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(username));
            message.addRecipient(RecipientType.TO, new InternetAddress(to));
            message.setSubject(title);
            message.setSentDate(new Date());
            message.setContent(contents, "text/html;charset=utf-8");
            // 3，发送邮件Transport
            Transport.send(message);

        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }*/

  /*  public static void send(String to, String title,String contents){
        Config config = new Config("outlookmail.properties");

        final String SendServer=config.getString("sendServer");
        final String port = config.getString("port");
        final String username=config.getString("username");
        final String passcode = config.getString("passcode");
//		String title="欢迎使用，请激活！";
//		String contents = "<h2>你看到的是一封测试邮件</h2><br/>正如你所见，使用mail组件发送的";


        Properties props = new Properties();
        props.setProperty("mail.host", SendServer);
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.port", port);

        MailSSLSocketFactory sf;
        try {
            sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            props.put("mail.smtp.ssl.enable", "true");
            props.put("mail.smtp.ssl.socketFactory", sf);
        } catch (GeneralSecurityException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        Session session = Session.getDefaultInstance(props, new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, passcode);// 邮箱和第三方登录授权码
            }

        });

        // 2， 创建代表邮件的对象Message
        Message message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(username));
            message.addRecipient(RecipientType.TO, new InternetAddress(to));
            message.setSubject(title);
            message.setSentDate(new Date());
            message.setContent(contents, "text/html;charset=utf-8");
            // 3，发送邮件Transport
            Transport.send(message);

        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }


    ////////////////////////////////////////////////////////
    public static void main(String[] a) {
        System.out.println("开始");
//		sendMail("15979940275@163.com");

//		String recieve = "kuangchengping@outlook.com";
//		send163(recieve);

        send("15979940275@163.com", "没有标题的标题","正如你所见，这是一封测试邮件");
        System.out.println("end");

    }*/
}
