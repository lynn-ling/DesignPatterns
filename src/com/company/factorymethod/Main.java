package com.company.factorymethod;

public class Main {
    public static void main(String[] args) {
        Moveable n = new Car();
        n.go();
        Moveable m = new Plane();
        m.go();
        Moveable l = new Broom();
        l.go();

    }
}
