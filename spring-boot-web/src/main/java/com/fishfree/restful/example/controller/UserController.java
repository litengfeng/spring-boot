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

import com.fishfree.restful.example.entity.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author litengfeng
 * @version 1.0
 * @date 2018/6/5 14:50
 * @project spring-boot-demo
 */

//@RestController
@RequestMapping("user")
public class UserController {

    //模拟数据库
    private Map<Long, User> map = new ConcurrentHashMap<>();

    //获取所有的用户
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<User> getUsers() {
        List<User> users = new ArrayList<>(map.values());
        return users;
    }

    //获取指定id的用户
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable Long id) {
        return map.get(id);
    }

    //提交用户，新增
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String postUser(@ModelAttribute User user) {
        map.put(user.getId(), user);
        return "success";
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public String putUser(@PathVariable Long id, @ModelAttribute User user) {
        User u = map.get(id);
        u.setName(user.getName());
        u.setAge(user.getAge());
        return "success";
    }

    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable Long id){
        map.remove(id);
        return "success";
    }
}
