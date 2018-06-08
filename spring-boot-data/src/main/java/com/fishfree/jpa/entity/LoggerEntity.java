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
package com.fishfree.jpa.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author litengfeng
 * @version 1.0
 * @date 2018/6/8 14:28
 * @project spring-boot-demo
 */

@Entity
@Table(name = "logger")
@Data
public class LoggerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //客户端请求ip
    @Column(name = "client_ip", length = 32)
    private String clientIp;
    //客户端请求路径
    private String uri;
    //客户端请求类型，入ajax
    private String type;
    //客户端请求方法
    private String method;
    //请求参数
    @Column(name = "param_data")
    private String paramData;
    //sessionId
    @Column(name = "session_id")
    private String sessionId;
    //请求时间
    @Column(name = "time", insertable = false)
    private Date time;
    //接口返回时间
    @Column(name = "return_time")
    private String returnTime;
    //接口返回json数据
    @Column(name = "return_data")
    private String returnData;
    //返回码
    @Column(name = "http_status_code", length = 8)
    private String httpStatusCode;
    //请求时长
    @Column(name = "time_consuming")
    private int timeConsuming;
}
