package com.company.proxy.v06;

/*
问题：我想记录坦克的移动时间
最简单的办法：修改代码，记录时间
问题2：如果无法改变方法源码呢？
用继承？
v04:使用代理
v05:代理各种类型
问题3：如何实现代理的各种组合？继承？Decorator？
v06：静态代理，代理的对象改成Movable类型--越来越像decorator了
 */

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
        //可以实现代理的相互嵌套
        new TankLogProxy(
                new TankTimeProxy(
                        new Tank()
                )
        ).move();
        //上面一段代码也可以写成下面这样
        Tank t = new Tank();
        TankTimeProxy ttp = new TankTimeProxy(t);
        TankLogProxy tlp = new TankLogProxy(ttp);
        tlp.move();
    }
    /*
    start moving...
    Tank moving claclacla...
    7807
    stopped!
     */
}


class TankTimeProxy implements Movable {
    Movable m;
    public TankTimeProxy(Movable m) {
        this.m = m;
    }

    @Override
    public void move() {
        long start = System.currentTimeMillis();
        m.move();
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}

class TankLogProxy implements Movable {
    Movable m;
    public TankLogProxy(Movable m) {
        this.m = m;
    }
    @Override
    public void move() {
        System.out.println("start moving...");
        m.move();
        System.out.println("stopped!");
    }
}

interface Movable {
    void move();
}
