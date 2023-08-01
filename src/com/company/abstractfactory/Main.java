package com.company.abstractfactory;



public class Main {
    public static void main(String[] args) {
        //第一个产品是一辆车，可以跑
        Car c = new Car();
        c.go();
        //第二个产品是一把枪，可以打枪
        AK47 w = new AK47();
        w.shoot();
        //第三个产品是一个面包，可以打印出它的名字
        Bread b = new Bread();
        b.printName();
    }
}
