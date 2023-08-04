package com.company.singleton;

/*
lazy loading，也称懒汉式
虽然达到了按需初始化的目的（什么时候用什么时候初始化），但却带来线程不安全的问题
 */
public class Mgr03 {

    //一开始先不进行初始化
    private static Mgr03 INSTANCE;

    private Mgr03(){};

    //假设第一个线程判断INSTANCE为空，但还没走到new这一步
    // 这时第二个线程进来了，判断INSTANCE为空，这时它就new了一个实例出来
    //而于此同时第一个线程也new出来一个实例出来
    //这样new出来的两个实例就不是一个实例了
    public static Mgr03 getInstance(){
        if(INSTANCE == null){
            //通过sleep来进行模拟，多花点时间
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            INSTANCE = new Mgr03();
        }
        return INSTANCE;
    }


    public void m(){
        System.out.println("m");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            //1.原本的完整写法：
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Mgr03.getInstance().hashCode());
                }
            }).start();

            //2.简略写法：
            //匿名内部类。因为new的Thread里放的是参数一定是Runnable，并且Runnable里只有一个方法run()
            //这时我就可以不写run方法，也不用写Runnable，简写成下面这样就可以
            //这个()代表Runnable，{}里直接写run方法的方法实现，不用写run了
            new Thread(()->{
                System.out.println(Mgr03.getInstance().hashCode());
            }).start();

            //结果是：hashCode有一样的，也有不一样的
            //hashcode不一样一定是两个不同的对象，但hashcode相同也不一定是同一个对象，比如两个不同类里new出的对象
        }
    }
}
