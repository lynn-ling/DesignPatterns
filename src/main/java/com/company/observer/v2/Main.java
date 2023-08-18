package com.company.observer.v2;

/*
面向对象的傻等
只要孩子不哭，我一直监视着
 */
class Child {
    private boolean cry = false;

    public boolean isCry(){
        return cry;
    }

    public void wakeUp(){
        System.out.println("Waked Up! Crying wuwuwu...");
        cry = true;
    }
}

public class Main {
    public static void main(String[] args) {
        Child child = new Child();
        while(!child.isCry()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("observing...");
        }
    }
}
