package com.company.cglib;


import org.aopalliance.intercept.MethodInvocation;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Random;

/*
cglib：code generation library。实现动态代理不需要接口
但如果被代理类是final的，cglib就无法实现动态代理
 */
public class Main {
    public static void main(String[] args) {
        //new个增强器类
        Enhancer enhancer = new Enhancer();
        //将增强器类的父类设成Tank类
        enhancer.setSuperclass(Tank.class);
        //设定回调，对象为方法拦截器，当被代理对象的方法调用的时候
        //会调用TimeMethodInterceptor的intercept
        enhancer.setCallback(new TimeMethodInterceptor());
        //生成动态代理
        Tank tank = (Tank)enhancer.create();
        tank.move();
        /*
        before
        Tank moving claclacla...
        after
         */
    }

}

class TimeMethodInterceptor implements MethodInterceptor {
    @Override
    //Object o就是生成的动态代理那个对象
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("before");
        Object result = null;
        result = methodProxy.invokeSuper(o,objects);
        System.out.println("after");
        return result;
    }
}


class Tank {
    public void move() {
        System.out.println("Tank moving claclacla...");
        try {
            Thread.sleep(new Random().nextInt(10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}


