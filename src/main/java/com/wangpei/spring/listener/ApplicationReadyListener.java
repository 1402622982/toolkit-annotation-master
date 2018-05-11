package com.wangpei.spring.listener;

import com.dangdang.config.service.GeneralConfigGroup;
import com.dangdang.config.service.zookeeper.ZookeeperConfigGroup;
import com.dangdang.config.service.zookeeper.ZookeeperConfigProfile;
import com.wangpei.spring.annotation.ConfigToolkitMap;
import com.wangpei.spring.config.TestGroup1ConfigMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.PropertySource;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Program Name: spring-annotation-master
 * <p>
 * Description:
 * <p>
 * Created by wangpei on 2018/5/11 0011
 *
 * @author wangpei
 * @version 1.0
 */
@PropertySource(value = "classpath:application.properties")
public class ApplicationReadyListener implements ApplicationListener<ApplicationReadyEvent> {
    Logger logger = LoggerFactory.getLogger(ApplicationReadyListener.class);
    @Value("${zk.url}")
    private String ZK_URL;

    @Value("${zk.dev}")
    private String ZK_DEV;

    @Value("${zk.version}")
    private String ZK_VERSION;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
       /* Assert.notNull(ZK_URL,"获取zookeeper配置中心路径错误！");*/
        ApplicationContext ac = event.getApplicationContext();
        // 获取使用了ConfigToolkitMap注解的所有bean
        Map<String, Object> beanMap = ac.getBeansWithAnnotation(ConfigToolkitMap.class);
        if (null != beanMap) {
            for (Map.Entry<String, Object> bean : beanMap.entrySet()) {
                // 获取bean名称
                String beanName = bean.getKey();
                ConcurrentHashMap val = (ConcurrentHashMap) bean.getValue();
                // 获取bean对应的对象
                // 注:所有使用了ConfigToolkitMap注解的对象都必须继承ConcurrentHashMap
                ConcurrentHashMap o = (ConcurrentHashMap) ac.getBean(beanName);
                ConfigToolkitMap c = o.getClass().getAnnotation(ConfigToolkitMap.class);
                String groupName = c.groupName();
                logger.info("groupName ======= " + groupName);
                // 获取zookeeper分组数据
                ZookeeperConfigProfile configProfile = new ZookeeperConfigProfile("localhost:2181", "/dev", "wangp");
                GeneralConfigGroup configGroup = new ZookeeperConfigGroup(configProfile, groupName);
                if (null != configGroup) {
                    for (Map.Entry cg : configGroup.entrySet()) {
                        // 对象赋值 两种都ok
                        o.put(cg.getKey(), cg.getValue());
                     /*  val.put(cg.getKey(),cg.getValue());*/
                    }
                }
                else {
                    logger.info("获取分组数据为null,[groupName] ---- " + groupName);
                }
            }
        }
        TestGroup1ConfigMap t = ac.getBean(TestGroup1ConfigMap.class);
        String name = t.get("name");
        logger.info("[name] ------ " + name);
    }
}
