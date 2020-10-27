package com.mini.app.common.annotation;

import java.lang.annotation.*;

/**
 * @ClassName: ValidateArguments
 * @Description: 自定义非空验证
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface ValidateArguments {

    ValidateArgument[] validateArguments() default {};

}
