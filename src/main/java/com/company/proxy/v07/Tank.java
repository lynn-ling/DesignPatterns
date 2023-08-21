package com.company.proxy.v07;

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
使用jdk的动态代理（不像v06还有TankLogProxy喝TankTimeProxy这样的代理的类，而是会动态生成类，即看不见那个类的代码，运行的时候才生成）
 */

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Random;

public class Tank implements Movable{

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
        /*
        reflection：反射。通过这个对象可以找到对应的class类的那个对象，还可以找到是谁把这个class给load到内存的
        反射：
        当一个类被load到内存，这个类里面有各种属性，方法。
        我不需要看这个类的源码，通过分析class类的二进制字节码(Java的class文件都叫字节码)，就可以知道这个类里面有哪些属性，方法。
         */

        /*
        Proxy.newProxyInstance：创建代理对象
        第一个参数：用哪一个class loader把new出来的这个代理对象给load到内存
                  这时只需要和被代理对象用同一个就可以了
        第二个参数：new出来的这个代理对象，它需要实现哪些接口
        第三个参数：InvocationHandler(调用处理器)。被代理对象那个方法被调用时我们怎么做处理
                  是实现了InvocationHandler的对象，
         */
        Movable m = (Movable) Proxy.newProxyInstance(Tank.class.getClassLoader(),
                //new出来的代理对象要实现Movable接口
                new Class[]{Movable.class}, //tank.class.getInterfaces()
                new LogHandler(tank)
                //下面这个是匿名内部类，把它移出去变成外部类就是下面LogHandler那种写法。实现效果一样
//                new InvocationHandler(){
//                    @Override
//                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                        System.out.println("method " + method.getName() + " start..");
//                        Object o = method.invoke(tank, args);
//                        System.out.println("method " + method.getName() + " end!");
//                        return o;
//                    }
//                }

        );
        m.move();
        /*
        method move start..
        Tank moving claclacla...
        method move end!
         */
    }
}

class LogHandler implements InvocationHandler {

    Tank tank;

    public LogHandler(Tank tank) {
        this.tank = tank;
    }

    @Override
    /*
    invoke方法会在move()方法的时候被调用
    Method method表示哪个方法被调用，args表示往这个方法里传什么参数
    在这次的代码里，这里的Object proxy是在main方法里的m；Method method是move()方法
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("method " + method.getName() + " start..");
        /*
        相当于调用了tank.move()
        这里method是move()方法。只要我实现了Movable接口，我就可以对好多对象调用move()方法，
        而这里我是对tank调用move()方法，因此invoke里第一个参数要写tank
         */
        Object o = method.invoke(tank,args);
        System.out.println("method " + method.getName() + " end!");
        return o;
    }
}



interface Movable {
    void move();
}
