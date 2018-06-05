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
package com.fishfree.restful.example.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author litengfeng
 * @version 1.0
 * @date 2018/6/5 15:07
 * @project spring-boot-demo
 */

//使用Mock进行测试

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MockServletContext.class})
//与之前的spring的@ContextConfiguration含义一致
public class UserControllerTest {

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        //初始化mock
        mockMvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
    }


    @Test
    public void testUserController() throws Exception {

        RequestBuilder request = null;

        // 1、get查一下user列表，应该为空
        request = get("/user/");
        mockMvc.perform(request).andExpect(status().isOk()).andExpect(content().string(equalTo("[]")));

        // 2、post提交一个user
        request = post("/user/")
                .param("id", "1")
                .param("name", "测试")
                .param("age", "20");
        mockMvc.perform(request)
                .andExpect(content().string(equalTo("success")));

        //3、获取刚才插入的数据
        request = get("/user/")
                .param("id", "1");
        mockMvc.perform(request).andExpect(content().string(equalTo("[{\"id\":1,\"name\":\"测试\",\"age\":20}]")));

        //4、修改数据
        request = put("/user/1")
                .param("name", "测试put").param("age", "30");
        mockMvc.perform(request).andExpect(content().string(equalTo("success")));
        // 5、get一个id为1的user
        request = get("/user/1");
        mockMvc.perform(request)
                .andExpect(content().string(equalTo("{\"id\":1,\"name\":\"测试put\",\"age\":30}")));

        // 6、del删除id为1的user
        request = delete("/user/1");
        mockMvc.perform(request)
                .andExpect(content().string(equalTo("success")));

        // 7、get查一下user列表，应该为空
        request = get("/user/");
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));
    }
}
