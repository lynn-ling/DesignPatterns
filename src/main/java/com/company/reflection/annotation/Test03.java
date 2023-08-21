package com.company.reflection.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//自定义注解
public class Test03 {
    @MyAnnotation3(name = "秦疆") //因为参数name没有默认值，所以这里必须写
    public void test() {
    }

    @MyAnnotation4() //因为参数name有默认值，所以这里参数可以省略，也可以写
    public void test01() {
    }

    @MyAnnotation5(name = "秦疆",schools = {"清华","北大"}) //这里没有顺序要求
    public void test02() {
    }

    @MyAnnotation6("秦疆")
    public void test03() {
    }
}

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation3 {
    //注解的参数：参数类型+参数名():
    String name();  //没有设置name的默认值
}

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation4 {
    String name() default "";  //设置了name的默认值
}

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation5 {
    String name() default "";
    int age() default 0;
    int id() default -1;
    String[] schools();
}

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation6 {
    String value(); //当只有一个参数时，写成value，这样在使用这个注解的时候不需要写value = xx，只写xx即可
}




