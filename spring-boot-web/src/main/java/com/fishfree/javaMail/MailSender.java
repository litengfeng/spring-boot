/*
 * -------------------------------------------------------------------------------------
 *    Mi-Me Confidential
 *
 *    Copyright (C) 2016 Shanghai Mi-Me Financial Information Service Co., Ltd.
 *    All rights reserved.
 *
 *   No part of this file may be reproduced or transmitted in any form or by any means,
 *    electronic, mechanical, photocopying, recording, or otherwise, without prior
 *    written permission of Shanghai Mi-Me Financial Information Service Co., Ltd.
 * -------------------------------------------------------------------------------------
 */
package com.fishfree.javaMail;

import org.springframework.util.StringUtils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.util.List;
import java.util.Properties;

/**
 * 邮件发送类
 *
 * @author litengfeng
 * @version 1.0
 * @date 2018/6/7 16:37
 * @project spring-boot-demo
 */


public class MailSender {

    //邮件实体
    private MailEntity mail = new MailEntity();

    /**
     * 设置邮件标题
     *
     * @param title
     * @return
     */
    public MailSender title(String title) {
        mail.setTitle(title);
        return this;
    }

    /**
     * 设置邮件内容
     *
     * @param content
     * @return
     */
    public MailSender content(String content) {
        mail.setContent(content);
        return this;
    }

    /**
     * 设置邮件内容格式类型
     *
     * @param contentType
     * @return
     */
    public MailSender contentType(String contentType) {
        mail.setContentType(contentType);
        return this;
    }

    /**
     * 设置接收邮箱地址
     *
     * @param targets
     * @return
     */
    public MailSender targets(List<String> targets) {
        mail.setRecvList(targets);
        return this;
    }

    public void send() throws Exception {
        //默认邮箱类型
        if (mail.getContentType() == null) {
            mail.setContentType(MailContentTypeEnum.HTML.getType());
        }
        if (StringUtils.isEmpty(mail.getTitle())) {
            throw new Exception("邮箱标题为空，调用title方式设置");
        }
        if (StringUtils.isEmpty(mail.getContent())) {
            throw new Exception("邮箱内容为空，调用content方式设置");
        }
        if (StringUtils.isEmpty(mail.getRecvList().isEmpty())) {
            throw new Exception("邮箱接收人为空，调用targets方式设置");
        }

        //读取mail.properties文件内容
        final PropertyUtil propertyUtil = new PropertyUtil("mail");
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", true);

        properties.put("mail.smtp.host", propertyUtil.getValue("mail.smtp.service"));
        properties.put("mail.smtp.port", propertyUtil.getValue("mail.smtp.port"));
        properties.put("mail.user", propertyUtil.getValue("mail.from.addr"));
        properties.put("mail.password", propertyUtil.getValue("mail.from.pwd"));
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.starttls.enable", "true");

//        properties.put("mail.smtp.socketFactory.port", "mail.smtp.port");
//        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//        properties.put("mail.smtp.socketFactory.fallback", "false");

        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                //用户名和密码
                String userName = properties.getProperty("mail.user");
                String password = properties.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);
            }
        };

        //使用环境属性和授权信息创建邮箱会话
        Session mailSession = Session.getDefaultInstance(properties, authenticator);
        //创建邮件消息
        MimeMessage mimeMessage = new MimeMessage(mailSession);
        //设置发件人
        String nickName = MimeUtility.encodeText(propertyUtil.getValue("mail.from.nickname"));
        InternetAddress from = new InternetAddress(nickName + "<" + properties.get("mail.user") + ">");
        mimeMessage.setFrom(from);
        //设置邮箱标题
        mimeMessage.setSubject(mail.getTitle());
        //html发送邮件
        if (mail.getContentType().equals(MailContentTypeEnum.HTML)) {
            mimeMessage.setContent(mail.getContent(), mail.getContentType());
        } else if (mail.getContentType().equals(MailContentTypeEnum.TEXT)) {
            mimeMessage.setText(mail.getContent());//文本发送邮件
        }
        //收件人
        List<String> targets = mail.getRecvList();
        InternetAddress[] addressArray = new InternetAddress[targets.size()];
        for (int i = 0; i < targets.size(); i++) {
            addressArray[i] = new InternetAddress(targets.get(i));
        }
        mimeMessage.setRecipients(Message.RecipientType.TO, addressArray);
        System.out.println("send mime message: "+ mimeMessage.toString());
        //发送邮件
        Transport.send(mimeMessage);
    }
}
