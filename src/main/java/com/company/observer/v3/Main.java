package com.company.observer.v3;

/*
加入观察者
 */
class Child {
    private boolean cry = false;
    //把观察者d放到这儿
    private Dad d = new Dad();

    public boolean isCry(){
        return cry;
    }

    public void wakeUp(){
        cry = true;
        //等到孩子哭的时候，直接调用观察者的feed方法
        d.feed();
    }

}

class Dad {
    public void feed() {
        System.out.println("dad feeding...");
    }
}

public class Main {
    public static void main(String[] args) {
        Child c = new Child();
        //do sth
        c.wakeUp();
        //结果是：dad feeding...
    }
}
