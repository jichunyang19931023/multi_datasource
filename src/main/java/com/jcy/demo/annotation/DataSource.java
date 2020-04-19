package com.jcy.demo.annotation;

import com.jcy.demo.constant.ContextConst;

import java.lang.annotation.*;

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {
    ContextConst.DataSourceType value() default ContextConst.DataSourceType.DATASOURCE_1;
}
