package com.wangpei.spring.controller;

import com.wangpei.spring.config.PropertyGroupConfigMap;
import com.wangpei.spring.config.TestGroup1ConfigMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Program Name: spring-annotation-master
 * <p>
 *  获取zookeeper分组数据测试
 * <p>
 * Created by wangpei on 2018/5/11 0011
 *
 * @author wangpei
 * @version 1.0
 */
@RestController()
@RequestMapping("group1/demo")
public class Group1Controller {

    @Autowired
    private TestGroup1ConfigMap testGroup1ConfigMap;

    @Autowired
    private PropertyGroupConfigMap propertyGroupConfigMap;
    /**
     * 获取分组name
     * @param hello
     * @return
     */
    @RequestMapping("getName/{hello}")
    @ResponseBody
    public String getName(@PathVariable("hello") String hello){
        return hello + testGroup1ConfigMap.get("name");
    }
}
