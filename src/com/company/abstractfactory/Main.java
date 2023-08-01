package com.company.abstractfactory;

public class Main {
    public static void main(String[] args) {
        //这个是现代世界工厂造出来的具体的产品。如果想让魔法工厂造，只需要new MagicFactory，其他代码不用动即可
        AbstractFactory f = new ModernFactory();
        Vehicle c = f.createVehicle();
        c.go();
        Weapon w = f.createWeapon();
        w.shoot();
        Food b = f.createFood();
        b.printName();
    }
}
