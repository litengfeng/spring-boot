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
package com.fishfree.controller.model;

import lombok.Data;

/**
 * @author litengfeng
 * @version 1.0
 * @date 2018/6/12 10:17
 * @project spring-boot-demo
 */


@Data
public class Account {
    private Long no;
    private String name;

    public Account(Long no, String name) {
        this.no = no;
        this.name = name;
    }

    public Account() {
    }

    @Override
    public String toString() {
        return "Account{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}
