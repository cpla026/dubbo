package com.coolron.common.validate;

import java.lang.annotation.*;

/**
 * @Auther: xf
 * @Date: 2018/11/24 23:24
 * @Description: 自定义注解
 */
// 标注这个类它可以标注的位置
@Target({ElementType.METHOD, ElementType.TYPE})
// 标注这个注解的注解保留时期
@Retention(RetentionPolicy.RUNTIME)
// 是否生成注解文档
@Documented
public @interface RequiredPermission {
    String value();
}
