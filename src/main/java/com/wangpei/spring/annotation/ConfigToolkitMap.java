package com.wangpei.spring.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * Program Name: spring-annotation-master
 * <p>
 *  获取配置中心分组数据 注：所有使用了ConfigToolkitMap注解的对象都必须继承ConcurrentHashMap
 * <p>
 * Created by wangpei on 2018/5/11 0011
 *
 * @author wangpei
 * @version 1.0
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface ConfigToolkitMap {
    String groupName();
}
