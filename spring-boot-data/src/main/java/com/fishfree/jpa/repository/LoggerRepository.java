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
package com.fishfree.jpa.repository;

import com.fishfree.jpa.entity.LoggerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author litengfeng
 * @version 1.0
 * @date 2018/6/8 14:42
 * @project spring-boot-demo
 */


public interface LoggerRepository extends JpaRepository<LoggerEntity, Long> {
}
