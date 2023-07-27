package com.company.singleton;

/*
静态内部类方式
JVM保证单例。JVM加载class的时候只加载一次
加载外部类的时候不会加载内部类，这样可以实现懒加载
 */
public class Mgr07 {

    //外面的类在加载的时候，这个类的的静态内部类是不会被初始化的
    //只加载Mgr07的时候，Mgr07Holder是不会被初始化的
    private Mgr07(){};

    private static class Mgr07Holder{
        private final static Mgr07 INSTANCE = new Mgr07();
    }

    //在调用getInstance的时候，Mgr07Holder才会被加载
    public static Mgr07 getInstance(){
        return Mgr07Holder.INSTANCE;
    }


    public void m(){
        System.out.println("m");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                System.out.println(Mgr07.getInstance().hashCode());
            }).start();
        //结果是：hash值都一样
        }
    }
}
