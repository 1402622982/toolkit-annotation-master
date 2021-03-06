package com.wangpei.spring.listener;

import com.dangdang.config.service.GeneralConfigGroup;
import com.dangdang.config.service.observer.IObserver;
import com.dangdang.config.service.zookeeper.ZookeeperConfigGroup;
import com.dangdang.config.service.zookeeper.ZookeeperConfigProfile;
import com.wangpei.spring.annotation.ConfigToolkitMap;
import com.wangpei.spring.config.TestGroup1ConfigMap;
import com.wangpei.spring.util.ZKServerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;

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
public class InitConfigGroupListener implements ApplicationListener<ApplicationReadyEvent> {

    Logger logger = LoggerFactory.getLogger(InitConfigGroupListener.class);
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
       /* Assert.notNull(ZK_URL,"获取zookeeper配置中心路径错误！");*/
        ApplicationContext ac = event.getApplicationContext();
        // 获取使用了ConfigToolkitMap注解的所有bean
        Map<String, Object> beanMap = ac.getBeansWithAnnotation(ConfigToolkitMap.class);
        // 配置中心属性
        ZKServerUtil zkServerUtil = ac.getBean(ZKServerUtil.class);
        if (null != beanMap) {
            for (Map.Entry<String, Object> bean : beanMap.entrySet()) {
                // 获取bean名称
                String beanName = bean.getKey();
                ConcurrentHashMap val = (ConcurrentHashMap) bean.getValue();
                // 获取bean对应的对象GeneralConfigGroup
                // 注:所有使用了ConfigToolkitMap注解的对象都必须继承ConcurrentHashMap
                ConcurrentHashMap o = (ConcurrentHashMap) ac.getBean(beanName);
                ConfigToolkitMap c = o.getClass().getAnnotation(ConfigToolkitMap.class);
                String groupName = c.groupName();
                logger.info("groupName ======= " + groupName);
                // 获取zookeeper分组数据
                ZookeeperConfigProfile configProfile = new ZookeeperConfigProfile(zkServerUtil.getZK_URL(), zkServerUtil.getZK_DEV(), zkServerUtil.getZK_VERSION());
                GeneralConfigGroup configGroup = new ZookeeperConfigGroup(configProfile, groupName);
                //观察此分组下的节点数据是否发生变化
                configGroup.register(new IObserver() {
                    @Override
                    public void notified(String key, String val) {
                        logger.info("["+groupName+"] has changed,[property] ----- "+ key +",[value] ----- "+ val);
                        //节点数据发生变化，改变对应分组数据
                        o.put(key,val);
                    }
                });
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
