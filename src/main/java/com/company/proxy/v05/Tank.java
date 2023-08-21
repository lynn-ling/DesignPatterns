package com.company.proxy.v05;

/*
问题：我想记录坦克的移动时间
最简单的办法：修改代码，记录时间
问题2：如果无法改变方法源码呢？
用继承？
v04:使用代理
v05:代理各种类型
问题3：如何实现代理的各种组合？继承？Decorator？
 */

import java.util.Random;

public class Tank implements Movable{
//模拟坦克移动了一段时间
    @Override
    public void move() {
        System.out.println("Tank moving claclacla...");
        try {
            Thread.sleep(new Random().nextInt(10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    //TankTimeProxy可以代理Tank，TankLogProxy也可以代理Tank
    //但TankTimeProxy没法代理TankLogProxy
    // 因此这种方法没办法实现TankLogProxy包TankTimeProxy，TankTimeProxy里面再包Tank
    public static void main(String[] args) {
        new TankLogProxy().move();
    }
}


class TankTimeProxy implements Movable {
    Tank tank = new Tank();
    @Override
    public void move() {
        long start = System.currentTimeMillis();
        tank.move();
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}

class TankLogProxy implements Movable {
    Tank tank = new Tank();
    @Override
    public void move() {
        System.out.println("start moving...");
        tank.move();
        System.out.println("stopped!");
    }
}

interface Movable {
    void move();
}
