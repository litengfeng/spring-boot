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
package com.fishfree.inteceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fishfree.jpa.entity.LoggerEntity;
import com.fishfree.jpa.repository.LoggerRepository;
import com.fishfree.util.RequestUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 日志拦截器
 *
 * @author litengfeng
 * @version 1.0
 * @date 2018/6/8 14:27
 * @project spring-boot-demo
 */


public class LoggerInterceptor implements HandlerInterceptor {

    private final String LOGGER_TIEM = "_logger_time";
    private final String LOGGER_ENTITY = "_logger_entity";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LoggerEntity loggerEntity = new LoggerEntity();
        loggerEntity.setClientIp(RequestUtil.getClientIp(request));
        loggerEntity.setMethod(request.getMethod());
        loggerEntity.setUri(request.getRequestURI());
//        loggerEntity.setTime(new Date());
        loggerEntity.setSessionId(request.getRequestedSessionId());

        String paramData = JSON.toJSONString(request.getParameterMap(),
                SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteMapNullValue
        );
        loggerEntity.setParamData(paramData);
        loggerEntity.setType(RequestUtil.getRequestType(request));

        request.setAttribute(LOGGER_ENTITY, loggerEntity);
        request.setAttribute(LOGGER_TIEM, System.currentTimeMillis());

        return true;
    }

    //在请求进行渲染之前进行拦截
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        long currentTime = System.currentTimeMillis();
        long time = Long.parseLong(request.getAttribute(LOGGER_TIEM).toString());
        LoggerEntity loggerEntity = (LoggerEntity) request.getAttribute(LOGGER_ENTITY);
        loggerEntity.setHttpStatusCode(response.getStatus() + "");
        loggerEntity.setReturnData(JSON.toJSONString(request.getAttribute(RequestUtil.LOGGER_RETURN),
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.DisableCircularReferenceDetect
        ));
        loggerEntity.setTimeConsuming(Integer.parseInt((currentTime - time)+""));
        loggerEntity.setReturnTime(currentTime+"");

        //保存日志到数据库中
        getDAO(LoggerRepository.class,request).save(loggerEntity);
    }

    private <T> T getDAO(Class<T> clazz,HttpServletRequest request){
        //通过WebApplicationContext获取bean
        ApplicationContext  applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
        return applicationContext.getBean(clazz);
    }
}
