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

/**
 * 邮件类型枚举
 *
 * @author litengfeng
 * @version 1.0
 * @date 2018/6/7 16:51
 * @project spring-boot-demo
 */

public enum MailContentTypeEnum {
    HTML("text/html;charset=UTF-8"),
    TEXT("text")
    ;

    private String type;

    MailContentTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
