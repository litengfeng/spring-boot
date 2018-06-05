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
package com.fishfree.restful.example.entity;

import lombok.Data;

/**
 * @author litengfeng
 * @version 1.0
 * @date 2018/6/5 14:51
 * @project spring-boot-demo
 */


@Data
public class User {
    private Long id;
    private String name;
    private int age;

}
