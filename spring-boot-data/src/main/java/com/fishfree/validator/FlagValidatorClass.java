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
package com.fishfree.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author litengfeng
 * @version 1.0
 * @date 2018/6/11 16:12
 * @project spring-boot-demo
 */


public class FlagValidatorClass implements ConstraintValidator<FlagValidator, Object> {

    private String values;

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        String[] value_array = values.split(",");
        for(String v : value_array){
            if(v.equals(value)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void initialize(FlagValidator constraintAnnotation) {
        this.values = constraintAnnotation.values();
    }
}
