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
package com.fishfree.javaMail;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author litengfeng
 * @version 1.0
 * @date 2018/6/7 16:59
 * @project spring-boot-demo
 */


public class PropertyUtil {

    private final ResourceBundle resource;
    private final String fileName;

    public PropertyUtil(String fileName) {
        this.fileName = fileName;
        //资源绑定
        Locale locale = new Locale("zh", "CN");
        resource = ResourceBundle.getBundle(fileName, locale);
    }

    /**
     * 根据传入的key获取对象值
     * @param key properties文件中的key
     * @return String解析后的对应的key的值
     */
    public String getValue(String key) {
        return this.resource.getString(key);
    }
}
