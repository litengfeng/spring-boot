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

import com.fishfree.jpa.entity.ValidatorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.MessageSourceResourceBundle;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.beanvalidation.MessageSourceResourceBundleLocator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

/**
 * @author litengfeng
 * @version 1.0
 * @date 2018/6/11 15:40
 * @project spring-boot-demo
 */


@RestController
public class ValidatorController {


    @Autowired
    MessageSource messageSource;

    @RequestMapping("validator")
    public String validator(@Valid ValidatorEntity validatorEntity, BindingResult result) {
        if (result.hasErrors()) {
            StringBuilder msg = new StringBuilder();
            List<FieldError> list = result.getFieldErrors();

            Locale currentLocale = LocaleContextHolder.getLocale();
            for (FieldError fieldError : list) {
                //获取错误信息
                String errorMessage = messageSource.getMessage(fieldError, currentLocale);
                //添加错误消息到集合中
                msg.append(fieldError.getField() + " : " + errorMessage + ", ");
            }
            return msg.toString();
        }
        return "验证通过";
    }
}
