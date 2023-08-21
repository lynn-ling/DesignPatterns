package com.company.reflection.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface MyAnnotation7 {
    String name() default "张三丰";
    int abilityNum() default 1;
    String[] abilityNames() default {"太极拳"};
}


public class TestAnnotation {

    @MyAnnotation7(name = "令狐冲",abilityNum = 2,abilityNames = {"独孤九剑","吸星大法"})
    public void people1(String name, int age){}
    @MyAnnotation7(name = "段誉",abilityNum = 2,abilityNames = {"凌波微步","六脉神剑"})
    public void people2(String name, int age){}
    @MyAnnotation7 //没有配置，则使用默认值
    public void people3(String name, int age){}

    public static void main(String[] args) {
        Method[] methods = TestAnnotation.class.getMethods(); //反射获取所有方法

        for (Method method : methods) { //遍历所有方法
            if(method.isAnnotationPresent(MyAnnotation7.class)) { //判断方法是否有MyAnnotation7注解
                MyAnnotation7 myAnno = method.getAnnotation(MyAnnotation7.class);
                System.out.print(myAnno.name() + "有" + myAnno.abilityNum() + "个技能： ");

                for(String abilityName : myAnno.abilityNames()) {
                    System.out.print(abilityName + " ");
                }
                System.out.println();
            }
        }
    }

    /*
    令狐冲有2个技能： 独孤九剑 吸星大法
    段誉有2个技能： 凌波微步 六脉神剑
    张三丰有1个技能： 太极拳
     */
}
