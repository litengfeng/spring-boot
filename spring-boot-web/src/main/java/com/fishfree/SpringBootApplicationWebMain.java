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
package com.fishfree;

import com.fishfree.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author litengfeng
 * @version 1.0
 * @date 2018/6/5 18:08
 * @project spring-boot-demo
 */

@RestController
@SpringBootApplication
public class SpringBootApplicationWebMain {

    public static void main(String[] args){
        SpringApplication.run(SpringBootApplicationWebMain.class);
    }

    @Autowired
    private GreetingService greetingService;

    @RequestMapping("/web")
    public String home(){
        return "hello spring boot web";
    }

    @RequestMapping("/greeting")
    public String greeting(){
        return greetingService.greeting();
    }
}
