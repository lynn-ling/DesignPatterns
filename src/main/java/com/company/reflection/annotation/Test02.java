package com.company.reflection.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//测试元注解
//@MyAnnotation  //放在类上面会报错
@MyAnnotation2
public class Test02 {
    @MyAnnotation //放在方法上面可以
    public void test() {

    }

}

//Target 表示我们的注解可以用在哪些地方
//定义一个注解,作用域是方法
@Target(value = ElementType.METHOD)
//Retention 表示我们的注解在什么地方还有效
@Retention(value = RetentionPolicy.RUNTIME) //一般自定义的类的value值都是RUNTIME
@interface MyAnnotation {

}

//定义一个注解，作用域是方法，类，接口或者枚举
@Target(value = {ElementType.METHOD,ElementType.TYPE})
@interface MyAnnotation2 {

}







