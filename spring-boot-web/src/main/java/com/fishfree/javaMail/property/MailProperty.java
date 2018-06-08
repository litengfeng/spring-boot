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
package com.fishfree.javaMail.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author litengfeng
 * @version 1.0
 * @date 2018/6/8 13:58
 * @project spring-boot-demo
 */
@Component
@ConfigurationProperties(prefix = "mail")
@Data
public class MailProperty {

    private From from;
    private Smtp smtp;

    @Data
    class Smtp{
        private String service;
        private String port;
    }

    @Data
    class From {
        private String addr;
        private String pwd;
        private String nikcName;
    }
}
