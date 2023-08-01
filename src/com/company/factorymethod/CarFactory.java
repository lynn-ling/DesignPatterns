package com.company.factorymethod;

/*
工厂方法
针对每一种产品来做一个工厂
这里创建了生产Car的工厂，还可以按需添加生产Plane，Broom等等的工厂
 */
public class CarFactory {
    public Car createCar(){
        System.out.println("a car created!");
        return new Car();
    }
}
