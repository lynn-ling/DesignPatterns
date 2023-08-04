package com.company.singleton;

/*
lazy loading，也称懒汉式
虽然达到了按需初始化的目的（什么时候用什么时候初始化），但却带来线程不安全的问题
可以通过synchronized解决，但也带来效率下降
 */
public class Mgr04 {

    private static Mgr04 INSTANCE;

    private Mgr04(){};

    //加了锁synchronized以后，锁定的是Mgr04.class的对象
    public static synchronized Mgr04 getInstance(){
        if(INSTANCE == null){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            INSTANCE = new Mgr04();
        }
        return INSTANCE;
    }


    public void m(){
        System.out.println("m");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                System.out.println(Mgr04.getInstance().hashCode());
            }).start();
            //结果是：hashCode都是一样的
        }
    }
}
