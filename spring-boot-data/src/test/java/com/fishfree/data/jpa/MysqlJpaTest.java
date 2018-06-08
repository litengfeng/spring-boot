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
package com.fishfree.data.jpa;

import com.fishfree.jpa.config.JpaConfiguration;
import com.fishfree.jpa.entity.Department;
import com.fishfree.jpa.entity.Role;
import com.fishfree.jpa.entity.User;
import com.fishfree.jpa.repository.DepartmentRepository;
import com.fishfree.jpa.repository.RoleRepository;
import com.fishfree.jpa.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.ldap.AutoConfigureDataLdap;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

/**
 * @author litengfeng
 * @version 1.0
 * @date 2018/6/4 14:21
 * @project spring-boot-demo
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JpaConfiguration.class)
@EnableAutoConfiguration(exclude = JpaRepositoriesAutoConfiguration.class)
public class MysqlJpaTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    RoleRepository roleRepository;

    //
//
    @Before
    public void initData() {
//        //清除所有数据
//        userRepository.deleteAll();
//        departmentRepository.deleteAll();
//        roleRepository.deleteAll();
//
//        //插入数据
//        Department department = new Department();
//        department.setName("开发部");
//        departmentRepository.save(department);
//        Assert.notNull(department.getId());
//
//        Role role = new Role();
//        role.setName("guest");
//        roleRepository.save(role);
//        Assert.notNull(role.getId());
//
//        User user = new User();
//        user.setName("李四");
//        user.setCreateDate(new Date());
//        user.setDepartment(department);
//        user.setPwd("123456");
//
//        List<Role> roles = roleRepository.findAll();
//        Assert.notNull(roles);
//        user.setRoles(roles);
//
//        userRepository.save(user);
//        Assert.notNull(user.getId());
    }


    @Test
    public void findPage() {
        Pageable pageable = new PageRequest(0, 10);
        Page<User> page = userRepository.findAll(pageable);
        Assert.notNull(page);
        for (User user : page.getContent()) {
            System.out.println("user name : " + user.getName()
                    + "department " + user.getDepartment().getName()
                    + "roles " + user.getRoles().get(0).getName());
        }
    }
}
