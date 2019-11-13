package com.lzy.util;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;

import com.lzy.util.vo.MailInfVo;
import sun.misc.BASE64Encoder;

/**
 * @Description: 邮件发送
 * @Author: lzy
 * @date: 2019-11-13 10:27
 */
public class MailUtils {

    /* 初始化 例子 */
    /**
     * 邮箱终端
     */
    private static String mHost     = "smtp.exmail.qq.com";
    /**
     * 发送邮件协议名称
     */
    private static String mProtocol = "smtp";
    /**
     * 发送服务器是否需要身份验证
     */
    private static String mAuth     = "true";
    /**
     * 发送邮件发件人
     */
    private static String mMail     = "vzpublicmailbox@vizhitech.com";
    /**
     * 发送邮件邮箱密码
     */
    private static String mPwd      = "Qwer4321";
    /**
     * 端口
     */
    private static int    mPort     = 587;



    public static void sendAttachMail(MailInfVo pMailInfVo) throws MessagingException, UnsupportedEncodingException {
        Properties prop = new Properties();
        prop.setProperty("mail.host", mHost);
        prop.setProperty("mail.transport.protocol", mProtocol);
        prop.setProperty("mail.smtp.auth", mAuth);
        // 创建session
        Session session = Session.getInstance(prop);
        // 通过session得到transport对象
        Transport ts = session.getTransport();
        // 连上邮件服务器
        ts.connect(mHost, mPort, mMail, mPwd);
        // 创建邮件
        Message message = createAttachMail(session, pMailInfVo);
        // 发送邮件
        ts.sendMessage(message, message.getAllRecipients());
        ts.close();
    }



    private static Message createAttachMail(Session pSession, MailInfVo pMailInfVo)
            throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = new MimeMessage(pSession);
        // 设置邮件基本信息
        // 设置发件人
        message.setFrom(new InternetAddress(mMail));
        // 收件人
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(pMailInfVo.getTo()));
        // 邮件标题
        message.setSubject(pMailInfVo.getSubject());
        // 创建邮件附件
        MimeBodyPart attach = new MimeBodyPart();
        DataHandler dh = new DataHandler(new FileDataSource(pMailInfVo.getFilePath()));
        attach.setDataHandler(dh);
        // 解决文件名中文乱码问题
        BASE64Encoder enc = new BASE64Encoder();
        String fileName = pMailInfVo.getFileName() == null ? dh.getName() : pMailInfVo.getFileName();
        attach.setFileName("=?GBK?B?" + enc.encode(fileName.getBytes("GBK")) + "?=");
        // 创建容器描述数据关系
        MimeMultipart mp = new MimeMultipart();
        mp.addBodyPart(attach);
        mp.setSubType("mixed");

        message.setContent(mp);
        message.saveChanges();
        return message;
    }
}
