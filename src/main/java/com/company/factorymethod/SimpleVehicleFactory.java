package com.company.factorymethod;

/*
简单工厂模式，方式简单，但可扩展性差
比如我要新添加一个火车类，还需要在这个代码里继续添加
并且这里面的内容还是得写死
 */
public class SimpleVehicleFactory {
    public Car createCar() {
        //前置操作
        return new Car();
    }

    public Broom createBroom() {
        //前置操作
        return new Broom();
    }

    public Plane createPlane() {
        //前置操作
        return new Plane();
    }
}
