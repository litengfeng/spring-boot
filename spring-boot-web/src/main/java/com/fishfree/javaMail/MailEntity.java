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

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 邮件实体类
 *
 * @author litengfeng
 * @version 1.0
 * @date 2018/6/7 16:37
 * @project spring-boot-demo
 */

@Data
public class MailEntity implements Serializable{
    //smtp服务器
    private String smtpService;
    //设置端口号
    private String smtpPort;
    //设置发送邮箱
    private String fromMailAddr;
    //设置发送邮箱的密码
    private String fromMailStmpPwd;
    //邮件标题
    private String title;
    //邮件内容
    private String content;
    //邮件内容格式类型
    private String contentType;
    //接收邮件集合
    private List<String> recvList = new ArrayList<>();
}
