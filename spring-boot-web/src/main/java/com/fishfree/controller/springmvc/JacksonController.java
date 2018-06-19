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
package com.fishfree.controller.springmvc;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;

/**
 * @author litengfeng
 * @version 1.0
 * @date 2018/6/13 14:39
 * @project spring-boot-demo
 */


@RestControllerAdvice
@RestController
@RequestMapping("springmvc")
public class JacksonController {

    @PostMapping(value = "user")
    @JsonView(User.WithoutPasswordView.class)
    public User getUser(Date date){
        System.out.println(date);
        return new User("eric,","7!jd#h23");
    }


    @InitBinder
    public void initBind(WebDataBinder binder){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        binder.registerCustomEditor(Date.class,new CustomDateEditor(sdf,false));
    }

}
class User{

    interface WithoutPasswordView{};
    interface WithPasswordView extends WithoutPasswordView{};


    private String name;
    private String password;

    public User() {
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    @JsonView(WithoutPasswordView.class)
    public String getName(){
        return name;
    }
    @JsonView(WithPasswordView.class)
    public String getPassword(){
        return password;
    }

}
