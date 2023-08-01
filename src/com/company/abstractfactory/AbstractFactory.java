package com.company.abstractfactory;

/*
抽象工厂
一个抽象的工厂里面有三个抽象的产品
 */
public abstract class AbstractFactory {
    abstract Food createFood();
    abstract Vehicle createVehicle();
    abstract Weapon createWeapon();
}
