package com.company.spring.v2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
spring AOP test
 */

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("app_auto.xml");
        Tank t = (Tank)context.getBean("tank");
        t.move();
        /*
        method start...1692624582560
        Tank moving claclacla...
        method stop...1692624592436
         */
    }
}
