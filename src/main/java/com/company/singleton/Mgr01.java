package com.company.singleton;

/*
饿汉式
类加载到内存后，就实例化一个单例，JVM保证线程安全
即JVM保证每个class只会load到内存一次，保证INSTANCE也只初始化一次，值就永远只有这一个
简单实用，推荐使用
唯一缺点：不管用到与否，类装载时就完成实例化
 */
public class Mgr01 {

    //写final的必须进行初始化
    private static final Mgr01 INSTANCE = new Mgr01();

    //把构造方法写成私有的，让别人new不了
    private Mgr01(){};
    //别人要想用，只能调用getInstance方法。这里return的是静态的INSTANCE。不论调用多少次，返回的永远只是一个
    public static Mgr01 getInstance(){return INSTANCE;}

    //业务方法，根据业务写
    public void m(){
        System.out.println("m");
    }

    public static void main(String[] args) {
        Mgr01 m1 = Mgr01.getInstance();
        Mgr01 m2 = Mgr01.getInstance();
        System.out.println(m1 == m2);
        //true
    }
}
