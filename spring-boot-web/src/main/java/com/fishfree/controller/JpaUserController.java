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
package com.fishfree.controller;

import com.alibaba.fastjson.JSONObject;
import com.fishfree.jpa.entity.User;
import com.fishfree.jpa.repository.UserRepository;
import com.fishfree.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


/**
 * @author litengfeng
 * @version 1.0
 * @date 2018/6/6 15:33
 * @project spring-boot-demo
 */


@RestController
@RequestMapping(value = "user")
public class JpaUserController {
    @Autowired
    private UserRepository userRepository;

    //获取所有的会员列表
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public List<User> list() {
        return userRepository.findAll();
    }

    //获取大于指定id的数据
    @RequestMapping(value = "getGEId/{id}", method = RequestMethod.GET)
    public List<User> listGEId(@PathVariable Long id) {
        return userRepository.nativeQuery(id);
    }

    @RequestMapping(value = "customDelete/{id}")
    public String customDelete(@PathVariable Long id) {
        userRepository.customDelete(id);
        return "自定义删除成功";
    }

    @RequestMapping("/cutpage")
    public List<User> cutPage(int page) {
        PageRequest pageRequest = new PageRequest(page, 2);
        return userRepository.findAll(pageRequest).getContent();
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(User user, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String result = "login success";
        boolean loginFlag = true;
        User userExample = new User();
        userExample.setName(user.getName());

        Optional<User> userOptional = userRepository.findOne(Example.of(userExample));

        if (!userOptional.isPresent()) {
            result = "登录失败，不存在该用户";
            loginFlag = false;
        } else {
            if (!userOptional.get().getPwd().equals(user.getPwd())) {
                result = "登录失败，密码不正确";
                loginFlag = false;
            }
        }
        if (loginFlag) {
            //登录成功后设置到session中
            HttpSession session = request.getSession();
            session.setAttribute("user_session", user);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg", result);
        request.setAttribute(RequestUtil.LOGGER_RETURN, jsonObject);
        return result;
    }



}
