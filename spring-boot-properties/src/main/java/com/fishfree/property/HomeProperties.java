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
package com.fishfree.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * 编写自定义的属性---家乡
 *
 * @author litengfeng
 * @version 1.0
 * @date 2018/6/4 15:38
 * @project spring-boot-demo
 */

@Component
@ConfigurationProperties(prefix = "home")//将配置文件中以 home 前缀的属性值自动绑定到对应的字段中
@Data
@Validated
public class HomeProperties {

    @NotNull
    private String province1;

    private String city;

    private String desc;

    //家乡特产
    private List<String> specialty = new ArrayList<>();
}
