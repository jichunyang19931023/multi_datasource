package com.jcy.demo.config.db;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @Decription: 动态数据源配置
 **/
public class DynamicDataSource extends AbstractRoutingDataSource {
    
	@Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getDataSource();
    }
}