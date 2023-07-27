package com.company.strategy;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        //new出来一个Cat的Sorter对象，把装有Cat对象的数组以及new出来的CatWeightComparator比较器的对象传入sort方法里
        Sorter<Cat> sorter1 = new Sorter();
        Cat[] a = {new Cat(3,3),new Cat(5,5),new Cat(1,1)};
        sorter1.sort(a,new CatWeightComparator());
        System.out.println(Arrays.toString(a));
        //[Cat{weight=1, height=1}, Cat{weight=3, height=3}, Cat{weight=5, height=5}]

        sorter1.sort(a,new CatHeightComparator());
        System.out.println(Arrays.toString(a));
        //[Cat{weight=5, height=5}, Cat{weight=3, height=3}, Cat{weight=1, height=1}]

        Sorter<Dog> sorter2 = new Sorter();
        Dog[] b = {new Dog(3),new Dog(5),new Dog(1)};
        sorter2.sort(b,new DogComparator());
        System.out.println(Arrays.toString(b));
        //[Dog{food=1}, Dog{food=3}, Dog{food=5}]



    }

}
