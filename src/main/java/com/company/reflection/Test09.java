package com.company.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

//分析性能问题
public class Test09 {

    //普通方式调用
    public static void test01() {
        User user = new User();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000000; i++) {
            user.getName();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("普通方法执行10亿次：" + (endTime-startTime)+"ms"); //普通方法执行10亿次：2ms
    }

    //反射方式调用
    public static void test02() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Class c1 = User.class;
        User user = (User)c1.newInstance();
        Method getName = c1.getDeclaredMethod("getName", null);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000000; i++) {
            getName.invoke(user,null);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("反射方法执行10亿次：" + (endTime-startTime)+"ms"); //反射方法执行10亿次：1027ms
    }


    //反射方式调用 关闭检测
    public static void test03() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Class c1 = User.class;
        User user = (User)c1.newInstance();
        Method getName = c1.getDeclaredMethod("getName", null);
        getName.setAccessible(true);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000000; i++) {
            getName.invoke(user,null);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("关闭检测反射方法执行10亿次：" + (endTime-startTime)+"ms"); //关闭检测反射方法执行10亿次：503ms
    }

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        test01();
        test02();
        test03();
    }
}
