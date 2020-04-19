package com.jcy.demo.config.db;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;
import com.jcy.demo.constant.ContextConst;

/**
 * @Decription: 动态多数据源配置
 **/
@Configuration
public class MutiplyDataSource {
    /**
     * 数据源1
     * @return
     */
    @Bean(name = "dataSource1")
    @ConfigurationProperties(prefix = "spring.datasource.druid.datasource1")
    public DataSource dataSource1(){
        return new DruidDataSource();
    }

    /**
     * 数据源2
     * @return
     */
    @Bean(name = "dataSource2")
    @ConfigurationProperties(prefix = "spring.datasource.druid.datasource2")
    public DataSource dataSource2(){
        return new DruidDataSource();
    }
    
    /**
     * 数据源3
     * @return
     */
    @Bean(name = "dataSource3")
    @ConfigurationProperties(prefix = "spring.datasource.druid.datasource3")
    public DataSource dataSource3(){
        return new DruidDataSource();
    }

    @Primary
    @Bean(name = "dynamicDataSource")
    public DataSource dynamicDataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        //配置默认数据源
        dynamicDataSource.setDefaultTargetDataSource(dataSource1());

        //配置多数据源
        HashMap<Object, Object> dataSourceMap = new HashMap();
        dataSourceMap.put(ContextConst.DataSourceType.DATASOURCE_1.name(), dataSource1());
        dataSourceMap.put(ContextConst.DataSourceType.DATASOURCE_2.name(), dataSource2());
        dataSourceMap.put(ContextConst.DataSourceType.DATASOURCE_3.name(), dataSource3());
        dynamicDataSource.setTargetDataSources(dataSourceMap);
        return dynamicDataSource;
    }

    /**
     * 配置@Transactional注解事务
     * @return
     */
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dynamicDataSource());
    }


}
