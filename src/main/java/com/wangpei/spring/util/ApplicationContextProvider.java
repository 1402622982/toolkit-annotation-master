package com.wangpei.spring.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Program Name: spring-annotation-master
 * <p>
 * 获取spring上下文
 * <p>
 * Created by wangpei on 2018/5/11 0011
 *
 * @author wangpei
 * @version 1.0
 */
@Component
public class ApplicationContextProvider implements ApplicationContextAware {
    /**
     * spring上下文
     */
    private ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;

    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}
