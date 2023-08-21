package com.company.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

//通过反射动态创建对象
public class Test08 {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        //获得Class对象
        Class<?> c1 = Class.forName("com.company.reflection.User");

        //构造一个对象
        User user = (User)c1.newInstance(); //本质上是调用了类的无参构造器
        System.out.println(user); //User{name='null', id=0, age=0}

        //通过构造器创建对象
        Constructor<?> declaredConstructor = c1.getDeclaredConstructor(String.class, int.class, int.class);
        Object user2 = declaredConstructor.newInstance("秦朝", 001, 18);
        System.out.println(user2); //User{name='秦朝', id=1, age=18}

        //通过反射调用普通方法
        User user3 = (User)c1.newInstance();
        //通过反射获取一个方法
        Method setName = c1.getDeclaredMethod("setName", String.class);
        //invoke：激活的意思。里面的参数：第一个参数是对象，第二个参数是方法的值
        //下面代码的意思是：user3这个对象的setName方法的值设为狂神
        setName.invoke(user3,"狂神");
        System.out.println(user3.getName());
        //狂神

        //通过反射操作属性
        User user4 = (User)c1.newInstance();
        Field name = c1.getDeclaredField("name");
        //不能直接操作私有属性，我们需要关闭程序的安全检测
        name.setAccessible(true); //关闭程序的安全检测
        name.set(user4,"狂神2");
        System.out.println(user4.getName());
        //狂神2

    }
}
