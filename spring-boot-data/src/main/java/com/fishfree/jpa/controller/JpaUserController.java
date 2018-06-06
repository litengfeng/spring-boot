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
package com.fishfree.jpa.controller;

import com.fishfree.jpa.entity.User;
import com.fishfree.jpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author litengfeng
 * @version 1.0
 * @date 2018/6/6 15:33
 * @project spring-boot-demo
 */


@RestController
@RequestMapping(value = "jpa")
public class JpaUserController {
    @Autowired
    private UserRepository userRepository;

    //获取所有的会员列表
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public List<User> list() {
        return userRepository.findAll();
    }

}
