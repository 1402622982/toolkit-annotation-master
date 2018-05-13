package com.wangpei.spring.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Program Name: spring-annotation-master
 * <p>
 * Description:
 * <p>
 * Created by wangpei on 2018/5/12 0012
 *
 * @author wangpei
 * @version 1.0
 */
@PropertySource(value = "classpath:application.properties")
@Component("zkServerUtil")
public class ZKServerUtil {
    @Value("${zk.url}")
    private String ZK_URL;

    @Value("${zk.dev}")
    private String ZK_DEV;

    @Value("${zk.version}")
    private String ZK_VERSION;

    public String getZK_URL() {
        return ZK_URL;
    }

    public void setZK_URL(String ZK_URL) {
        this.ZK_URL = ZK_URL;
    }

    public String getZK_DEV() {
        return ZK_DEV;
    }

    public void setZK_DEV(String ZK_DEV) {
        this.ZK_DEV = ZK_DEV;
    }

    public String getZK_VERSION() {
        return ZK_VERSION;
    }

    public void setZK_VERSION(String ZK_VERSION) {
        this.ZK_VERSION = ZK_VERSION;
    }
}
