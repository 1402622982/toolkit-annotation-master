package com.wangpei.spring.config;

import com.wangpei.spring.annotation.ConfigToolkitMap;

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
@ConfigToolkitMap(groupName = "property-group1")
public class PropertyGroupConfigMap extends ConcurrentHashMap<String,String>{

}
