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

import java.util.ArrayList;

/**
 * @author litengfeng
 * @version 1.0
 * @date 2018/6/8 9:53
 * @project spring-boot-demo
 */


public class TestMail {
    public static void main(String[] args) throws Exception {
        new MailSender().content("hello mail")
                .contentType(MailContentTypeEnum.TEXT.getType())
                .title("test mail send")
                .targets(new ArrayList<String>(){{add("390451983@qq.com");}})
                .send();
    }
}
