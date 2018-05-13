package com.wangpei.spring;

import com.wangpei.spring.listener.InitConfigGroupListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Program Name: spring-annotation-master
 * <p>
 * Description:
 * <p>
 * Created by wangpei on 2018/5/9 0009
 *
 * @author wangpei
 * @version 1.0
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args){


        SpringApplication springApplication = new SpringApplication(Application.class);
        springApplication.addListeners(new InitConfigGroupListener());
        springApplication.run(args);
    }
}
