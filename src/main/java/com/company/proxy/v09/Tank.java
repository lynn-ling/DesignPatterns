package com.company.proxy.v09;

/*
问题：我想记录坦克的移动时间
最简单的办法：修改代码，记录时间
问题2：如果无法改变方法源码呢？
用继承？
v04:使用代理
v05:代理各种类型
问题3：如何实现代理的各种组合？继承？Decorator？
v06：静态代理，代理的对象改成Movable类型--越来越像decorator了
v07:如果又stop方法需要代理。。。
如果想让LogProxy可以重用，不仅可以代理Tank，还可以代理任何其他可以代理的类型Object
（毕竟日志记录，时间计算是很多方法都需要的东西），这时应该怎么做呢？
分离代理行为与被代理对象
v08：横切代码与业务逻辑代码分离 AOP
v09: 通过反射观察生成的代理对象
jdk反射生成代理必须面向接口，这是由Proxy的内部实现决定的
这里的Tank如果没有实现Movable接口，是没有办法进行动态代理的
但ASM在被代理类是final的情况下也能实现动态代理
 */

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Random;

public class Tank implements Movable {

    @Override
    public void move() {
        System.out.println("Tank moving claclacla...");
        try {
            Thread.sleep(new Random().nextInt(10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
        Tank tank = new Tank();

        //自动生成了com/company/proxy/v09/$Proxy0.class文件，是反编译的，可以看到整个处理过程
        System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true");

        Movable m = (Movable) Proxy.newProxyInstance(Tank.class.getClassLoader(),
                new Class[]{Movable.class},
                new TimeProxy(tank)


        );
        m.move();
        /*
        method start...
        Tank moving claclacla...
        method stop...
         */
    }
}

class TimeProxy implements InvocationHandler {

    Movable m;

    public TimeProxy(Movable m) {
        this.m = m;
    }

    public void before() {
        System.out.println("method start...");
    }

    public void after() {
        System.out.println("method stop...");
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object o = method.invoke(m, args);
        after();
        return o;
    }
}


interface Movable {
    void move();
}
