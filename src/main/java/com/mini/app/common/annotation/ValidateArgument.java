package com.mini.app.common.annotation;

import java.lang.annotation.*;

/**
 * @ClassName: ValidateArgument
 * @Description: 单个检验参数条件
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface ValidateArgument {
    /*待检验参数名*/
    String fieldName() default "";

    /*是否必须*/
    boolean required() default true;

    /*长度*/
    int maxLength() default Integer.MAX_VALUE;

    /*是否是数字类型*/
    boolean isNumberType() default false;


}
