package com.company.spring.v1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
spring AOP test
 */

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("app.xml");
        Tank t = (Tank)context.getBean("tank");
        t.move();
        /*
        method start...1692624717487
        Tank moving claclacla...
        method stop...1692624725707
         */
    }
}
