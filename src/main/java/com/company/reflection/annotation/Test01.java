package com.company.reflection.annotation;

import java.util.ArrayList;
import java.util.List;

//什么是注解
public class Test01 extends Object{

    //@Override 重写的注解
    @Override
    public String toString() {
        return "Test12{}";
    }

    //@Deprecated 不推荐程序员使用，但是可以使用，或者存在更好的方式
    @Deprecated
    public static void test() {
        System.out.println("Deprecated");
    }

    @SuppressWarnings("all") //当使用了这个注解以后，即使list没有被使用过，list也是正常显示成白色
    public void test02() {
        List list = new ArrayList(); //当list没有被使用过的时候，list显示成浅灰色
    }

    public static void main(String[] args) {
        test(); //写test()过程中，test是被线划死的。但是不耽误它使用
    }
}
