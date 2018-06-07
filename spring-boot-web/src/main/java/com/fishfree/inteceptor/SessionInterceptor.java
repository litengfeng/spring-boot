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

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author litengfeng
 * @version 1.0
 * @date 2018/6/7 15:39
 * @project spring-boot-demo
 */


public class SessionInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //登录不做拦截
        if(request.getRequestURI().equals("/user/login") || request.getRequestURI().equals("/user/login_view")){
            return true;
        }

        Object obj = request.getSession().getAttribute("user_session");
        if(obj == null){
            //跳转到登录页面登录
            response.sendRedirect("/user/login_view");
            return false;
        }
        return true;
    }
}
