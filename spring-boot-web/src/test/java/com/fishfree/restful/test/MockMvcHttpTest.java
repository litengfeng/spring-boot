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
package com.fishfree.restful.test;

import com.fishfree.service.GreetingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author litengfeng
 * @version 1.0
 * @date 2018/6/5 18:21
 * @project spring-boot-demo
 */

@RunWith(SpringRunner.class)
//@SpringBootTest
//@AutoConfigureMockMvc
@WebMvcTest//使用这个注解替换上面两个
public class MockMvcHttpTest {

    /**
     * 1、不需要启动servlet容器，mock掉
     * 2、使用mockMvc进行模拟
     */
    @Autowired
    private MockMvc mockMvc;



    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/web"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("hello spring boot web")));
    }

    /**
     * 参考https://spring.io/guides/gs/testing-web/
     *  使用mock的方式注入bean
     */
    @MockBean
    private GreetingService greetingService;

    @Test
    public void greetingTest() throws Exception {
        /**
         *  使用Mockito
         * We use @MockBean to create and inject a mock for the GreetingService
         * (if you don’t do this the application context cannot start),
         * and we set its expectations using Mockito.
         */
        when(greetingService.greeting()).thenReturn("hello");
        this.mockMvc.perform(get("/greeting"))
                .andDo(print())//打印相应的请求信息
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("hello")));
    }

}
