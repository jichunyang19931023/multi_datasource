package com.jcy.demo.aspect;

import java.lang.reflect.Method;

import javax.persistence.EntityManager;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.hibernate.engine.spi.SessionImplementor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.jcy.demo.annotation.DataSource;
import com.jcy.demo.config.db.DataSourceContextHolder;
import com.jcy.demo.constant.ContextConst;

import lombok.extern.slf4j.Slf4j;

/**
 * @Decription: 动态数据源配置 @Order(-10) ：在事务开启前切换数据源
 **/
@Component
@Aspect
@Order(-10)
@Slf4j
public class DynamicDataSourceAspect {
    @Autowired
    private EntityManager entityManager;

    /**
     * 服务类中的方法开始前，如果有@DataSource，会断开之前的连接，关闭之前的事务(一个事务对应一个数据源)
     **/
    @Before("execution(* com.jcy.demo.service.*.*(..)) && @annotation(com.jcy.demo.annotation.DataSource)")
    public void before(JoinPoint point){
        try {
            DataSource annotationOfClass = point.getTarget().getClass().getAnnotation(DataSource.class);
            String methodName = point.getSignature().getName();
            Class[] parameterTypes = ((MethodSignature) point.getSignature()).getParameterTypes();
            Method method = point.getTarget().getClass().getMethod(methodName, parameterTypes);
            DataSource methodAnnotation = method.getAnnotation(DataSource.class);
            methodAnnotation = methodAnnotation == null ? annotationOfClass:methodAnnotation;
            ContextConst.DataSourceType dataSourceType = methodAnnotation != null && methodAnnotation.value() !=null ? methodAnnotation.value() :ContextConst.DataSourceType.DATASOURCE_1;
            DataSourceContextHolder.setDataSource(dataSourceType.name());
            SessionImplementor session = entityManager.unwrap(SessionImplementor.class);
            //最关键的一句代码， 手动断开连接，不用重新设置 ，会自动重新设置连接
            session.disconnect();
        } catch (NoSuchMethodException e) {
            log.error("切换动态数据源发生异常", e);
        } catch(Exception e){
        	log.error("切换动态数据源发生异常", e);
        }
    }

    /**
     * 服务类的方法结束后，会清除数据源，此时会变更为默认的数据源
     **/
    @After("execution(* com.jcy.demo.service.*.*(..)) && @annotation(com.jcy.demo.annotation.DataSource)")
    public void after(JoinPoint point){
        DataSourceContextHolder.clearDataSource();
    }
}
