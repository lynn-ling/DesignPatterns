package com.company.reflection;

//类的加载
public class Test04 {
    public static void main(String[] args) {
        A a = new A();
        System.out.println(A.m);
        /*
        A类静态代码块初始化
        A类的无参构造初始化
        100
         */
    }
}

class A {
    static {
        System.out.println("A类静态代码块初始化");
        m = 300;
    }
    static  int m = 100;

    public A() {
        System.out.println("A类的无参构造初始化");
    }
}





















