package com.company.factorymethod;

public class Main {
    public static void main(String[] args) {
        Moveable n = new CarFactory().createCar();
        n.go();
    }
}
