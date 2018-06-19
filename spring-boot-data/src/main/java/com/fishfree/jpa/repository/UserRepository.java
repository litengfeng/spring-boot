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


import com.fishfree.jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author litengfeng
 * @version 1.0
 * @date 2018/6/4 11:39
 * @project spring-boot-demo
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    //@Query 自定义查下语句
    @Query(value = "select * from user where id > ?1",nativeQuery = true)
    List<User> nativeQuery(long id);

    @Transactional
    @Modifying
    @Query(value = "delete from user where id = ?1",nativeQuery = true)
    List<User> customDelete(long id);
}
