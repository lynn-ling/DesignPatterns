package com.company.strategy;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Cat[] a = {new Cat(3,3),new Cat(5,5),new Cat(1,1)};
        Sorter sorter = new Sorter();
        sorter.sort(a);
        System.out.println(Arrays.toString(a));
        //[Cat{weight=1, height=1}, Cat{weight=3, height=3}, Cat{weight=5, height=5}]

        Dog[] b = {new Dog(3),new Dog(5),new Dog(1)};
        sorter.sort(b);
        System.out.println(Arrays.toString(b));
        //[Dog{food=1}, Dog{food=3}, Dog{food=5}]

    }

}
