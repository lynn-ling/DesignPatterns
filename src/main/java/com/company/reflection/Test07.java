package com.company.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

//获得类的信息
public class Test07 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, NoSuchMethodException {
        Class<?> c1 = Class.forName("com.company.reflection.User");

        //获得类的名字
        System.out.println(c1.getName());  //获得包名+类名   com.company.reflection.User
        System.out.println(c1.getSimpleName());  //获得类名   User

        //获得类的属性。getDeclaredFields可以找到所有属性；getFields只能找到public属性
        Field[] fields = c1.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field);
        }
        /*
        private java.lang.String com.company.reflection.User.name
        private int com.company.reflection.User.id
        private int com.company.reflection.User.age
         */

        //获得指定属性的值
        Field name = c1.getDeclaredField("name");
        System.out.println(name); //private java.lang.String com.company.reflection.User.name

        //获得类的方法
        Method[] methods = c1.getMethods();
        for (Method method : methods) {
            System.out.println("正常的："+method);
            //获得本类及其父类的全部public方法
        }

        Method[] declaredMethods = c1.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println(declaredMethod);
            //获得本类的所有方法(包括private的)
        }

        //获得指定方法
        Method getName = c1.getMethod("getName", null);
        Method setName = c1.getMethod("setName", String.class);
        System.out.println(getName); //public java.lang.String com.company.reflection.User.getName()
        System.out.println(setName); //public void com.company.reflection.User.setName(java.lang.String)

        //获得指定的构造器
        Constructor<?>[] constructors = c1.getConstructors();
        for (Constructor<?> constructor : constructors) {
            System.out.println(constructor);
            //获得public的构造方法
        }
        Constructor<?>[] declaredConstructors = c1.getDeclaredConstructors();
        for (Constructor<?> declaredConstructor : declaredConstructors) {
            System.out.println(declaredConstructor);
            //获得全部的构造方法
        }

        //获得指定的构造器
        Constructor<?> declaredConstructor = c1.getDeclaredConstructor(String.class, int.class, int.class);
        System.out.println("指定："+declaredConstructor); //指定：public com.company.reflection.User(java.lang.String,int,int)
    }
}
































