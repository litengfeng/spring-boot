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
package com.fishfree.config.fastjson;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * spring-boot中使用fastjson
 * 增加fastjson对输出信息就行转换
 * @author litengfeng
 * @version 1.0
 * @date 2018/6/6 18:06
 * @project spring-boot-demo
 */

//spring mvc 5.0 之之前继承WebMvcConfigurerAdapter类，之后直接实现WebMvcConfigurer接口即可
@Configuration
public class FastJsonConfiguration implements WebMvcConfigurer {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        //配置序列化属性
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.PrettyFormat);

        //设置返回数据格式和编码，处理中文出现乱码问题
        List<MediaType> list = new ArrayList<>();
        list.add(MediaType.APPLICATION_JSON_UTF8);

        fastJsonHttpMessageConverter.setSupportedMediaTypes(list);
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        //添加converter到列表中
        converters.add(fastJsonHttpMessageConverter);
    }
}
