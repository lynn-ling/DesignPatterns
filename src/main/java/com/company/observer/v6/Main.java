package com.company.observer.v6;

import java.nio.file.WatchEvent;
import java.util.ArrayList;
import java.util.List;

class Child {
    private boolean cry = false;
    private List<Observer> observers = new ArrayList<>();

    {
        observers.add(new Dad());
        observers.add(new Mum());
        observers.add(new Dog());
    }

    public boolean isCry() {return cry;}

    public void wakeUp() {
        cry = true;
        //System.currentTimeMillis()：当前时间
        wakeUpEvent event = new wakeUpEvent(System.currentTimeMillis(),"bed");

        for(Observer o : observers) {
            o.actionOnWakeUp(event);
        }
    }
}

//事件类
class wakeUpEvent {
    long timestamp;
    String loc;

    public wakeUpEvent(long timestamp, String loc) {
        this.timestamp = timestamp;
        this.loc = loc;
    }
}

interface Observer {
    void actionOnWakeUp(wakeUpEvent event);
}

class Dad implements Observer {
    public void feed() {
        System.out.println("dad feeding...");
    }

    public void pat(){
        System.out.println("dad patting...");
    }

    @Override
    public void actionOnWakeUp(wakeUpEvent event) {
        if(event.loc.equals("bed")){
            feed();
        }else{
            pat();
        }
    }
}

class Mum implements Observer {
    public void hug() {
        System.out.println("mum hugging...");
    }

    @Override
    public void actionOnWakeUp(wakeUpEvent event) {
        hug();
    }
}

class Dog implements Observer {
    public void wang() {
        System.out.println("dog wang...");
    }

    @Override
    public void actionOnWakeUp(wakeUpEvent event) {
        wang();
    }
}

public class Main {
    public static void main(String[] args) {
        Child c = new Child();
        //do sth
        c.wakeUp();
        /*
        dad feeding...
        mum hugging...
        dog wang...
         */

        //当wakeUpEvent event = new wakeUpEvent(System.currentTimeMillis(),"bed");里把"bed"换成其他时：
        /*
        dad patting...
        mum hugging...
        dog wang...
         */
    }
}
