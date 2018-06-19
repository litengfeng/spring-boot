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

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSONObject;
import com.fishfree.controller.model.Account;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Date;
import java.util.Map;

/**
 * @author litengfeng
 * @version 1.0
 * @date 2018/6/11 17:52
 * @project spring-boot-demo
 */

@RestController
@RequestMapping("springmvc")
@SessionAttributes(value = {"account", "saveTime"}, types = {Account.class, Date.class})
public class SpringMvcController {

    @GetMapping("session/attributes/{no}/{name}")
    public String sessionAttributes(@PathVariable Long no, @PathVariable String name, ModelAndView modelAndView) {
        modelAndView.addObject("account", new Account(no, name));
        modelAndView.addObject("saveTime", new Date());
        return "成功";
    }

    /**
     * 获取之前@SessionAttributes注解
     * 设定的session值，#{@code @ModelAttribute}
     * 会自定获取绑定的值
     *
     * @param account
     * @param saveTime
     * @return
     */
    @GetMapping("session/attributes/get")
    public String getSessionAttributes(@ModelAttribute Account account, @ModelAttribute Date saveTime) {
        return "account: " + JSONUtils.toJSONString(account) + ", saveTime " + saveTime;
    }

    @GetMapping(value = "/requestparam")
    public String requestParam(String id, @RequestParam(required = false) Map<String, String> requestMap) {
        return "id = " + id + " ,request map = " + JSONUtils.toJSONString(requestMap);
    }

    @GetMapping(value = "/requestheader", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String requestParam(@RequestHeader("Accept-Encoding") String encoding,
                               @RequestHeader("Host") String host,
                               @RequestHeader(value = "Keep-Alive", required = false) Long keepAlive,
                               @RequestHeader("Accept-Language") String acceptLanguage,
                               @RequestHeader("Accept") String accept) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" encoding:").append(encoding).append("\r\n");
        stringBuilder.append(" host:").append(host).append("\r\n");
        stringBuilder.append(" keepAlive:").append(keepAlive).append("\r\n");
        stringBuilder.append(" acceptLanguage:").append(acceptLanguage).append("\r\n");
        stringBuilder.append(" accept:").append(accept).append("\r\n");
        return stringBuilder.toString();
    }

    @RequestMapping("/request/body")
    public String requestBody(@Valid @RequestBody Account account, BindingResult result) {
        return "account: " + account;
    }


}
