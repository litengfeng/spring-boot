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
package com.fishfree.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 多个文件上传
 * 使用MultipartFile对象进行
 *
 * @author litengfeng
 * @version 1.0
 * @date 2018/6/8 17:52
 * @project spring-boot-demo
 */

@RestController
public class UploadController {

    private final static Logger LOGGER = LoggerFactory.getLogger(UploadController.class);

    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public String upload(HttpServletRequest request, MultipartFile[] file)  {
        String result = "上传成功";
        try{
            //获取上传文件的路径
            String uploadDir = request.getSession().getServletContext().getRealPath("/") + "uploads/";

            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            for (MultipartFile multipartFile : file) {
                executeUpload(multipartFile, uploadDir);
            }
        }catch (Exception e){
            LOGGER.error("load error",e);
            return "上传失败";
        }
        return result;
    }

    private void executeUpload(MultipartFile file, String dir) throws IOException {
        //文件后缀名称
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        //使用uuid作为文件的名称
        String fileName = UUID.randomUUID() + suffix;
        File serverFile = new File(dir + fileName);
        //直接使用MultipartFile的transferTo方法写入到服务器文件中
        file.transferTo(serverFile);
    }
}
