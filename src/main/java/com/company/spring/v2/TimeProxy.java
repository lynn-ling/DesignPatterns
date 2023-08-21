package com.company.spring.v2;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect //表示这是一个我要往里切的类
public class TimeProxy {
    //在执行Tank.move()方法前执行before()
    @Before("execution(void com.company.spring.v2.Tank.move())")
    public void before() {
        System.out.println("method start..." + System.currentTimeMillis());
    }

    //在执行Tank.move()方法后执行after()
    @After("execution(void com.company.spring.v2.Tank.move())")
    public void after(){
        System.out.println("method stop..." + System.currentTimeMillis());
    }
}
