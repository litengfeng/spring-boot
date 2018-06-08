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
package com.fishfree.util;

import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author litengfeng
 * @version 1.0
 * @date 2018/6/8 17:04
 * @project spring-boot-demo
 */


public class BeanUtil {


    /**
     * 通过web的httpServletRequest对象获取
     * Spring容器的WebApplicationContext上下文
     * 从而从容器内拿到相应的Bean
     * @param clazz
     * @param request
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> clazz, HttpServletRequest request){
        return WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext()).getBean(clazz);
    }
}
