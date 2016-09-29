package com.rongyi.diamond.baselibiary.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.LinkedList;

/**
 * Author:    Diamond_Lin
 * Version    V1.0
 * Date:      16/9/29 下午9:25
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/9/29      Diamond_Lin            1.0                    1.0
 * Why & What is modified:
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ListType {
    Class list() default LinkedList.class;
    Class value();
}

