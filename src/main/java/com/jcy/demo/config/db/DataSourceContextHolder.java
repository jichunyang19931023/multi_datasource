package com.jcy.demo.config.db;

import lombok.extern.slf4j.Slf4j;

/**
 * @Decription: 动态数据源配置
 **/

@Slf4j
public class DataSourceContextHolder {

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    public static void setDataSource(String dbType){
        log.info("====>切换到 ["+dbType+"] 数据源");
        contextHolder.set(dbType);
    }

    public static String getDataSource(){
        return contextHolder.get();
    }

    /**
     * 清除本地线程使用的数据源，使用默认的数据源
     **/
    public static void clearDataSource(){
        contextHolder.remove();
    }
}

