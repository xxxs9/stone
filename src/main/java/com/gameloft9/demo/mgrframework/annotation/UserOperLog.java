package com.gameloft9.demo.mgrframework.annotation;

import com.gameloft9.demo.mgrframework.beans.constant.OperType;

import java.lang.annotation.*;

/**
 * 用户操作注解
 *
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UserOperLog {
    /**操作类型*/
    OperType operType() default OperType.Query;

    /**备注*/
    String operationName() default "";
}
