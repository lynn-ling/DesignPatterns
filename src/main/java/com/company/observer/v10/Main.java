package com.company.observer.v10;

import java.util.ArrayList;
import java.util.List;

/*
有很多时候，观察者需要根据事件的具体情况来进行处理
大多数时候，我们处理事件的时候，需要事件源对象
事件也可以形成继承体系
自己写的
 */
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
        wakeUpEvent event = new wakeUpEvent(System.currentTimeMillis(),"bed",this);

        for(Observer o : observers) {
            o.actionOnWakeUp(event);
        }
    }
}

class Thief {
    private boolean steal = false;
    private List<Observer> observers = new ArrayList<>();

    {
        observers.add(new Dad());
        observers.add(new Mum());
        observers.add(new Dog());
    }

    public boolean isSteal() {return steal;}

    public void takeAction(){
        steal = true;
        takeActionEvent event= new takeActionEvent(System.currentTimeMillis(),"kitchen",this);

        for(Observer o : observers) {
            o.takeAction(event);
        }

    }
}


abstract class Event<T> {
    abstract T getSource();
}


class wakeUpEvent extends Event<Child>{
    long timestamp;
    String loc;
    Child source;

    public wakeUpEvent(long timestamp, String loc,Child source) {
        this.timestamp = timestamp;
        this.loc = loc;
        this.source = source;
    }

    @Override
    Child getSource() {
        return source;
    }
}

class takeActionEvent extends Event<Thief>{
    long timestamp;
    String loc;
    Thief source;

    public  takeActionEvent(long timestamp, String loc, Thief source) {
        this.timestamp = timestamp;
        this.loc = loc;
        this.source = source;
    }

    @Override
    Thief getSource() {
        return source;
    }
}

interface Observer {
    void actionOnWakeUp(wakeUpEvent event);
    void takeAction(takeActionEvent event);
}

class Dad implements Observer {
    public void feed() {
        System.out.println("dad feeding...");
    }
    public void pat(){
        System.out.println("dad patting...");
    }
    public void run() {
        System.out.println("dad running...");
    }
    public void beat() {
        System.out.println("dad beating...");
    }

    @Override
    public void actionOnWakeUp(wakeUpEvent event) {
        if(event.loc.equals("bed")){
            feed();
        }else{
            pat();
        }
    }

    @Override
    public void takeAction(takeActionEvent event) {
        if(event.loc.equals("bedroom")){
            beat();
        }else{
            run();
        }
    }
}

class Mum implements Observer {
    public void hug() {
        System.out.println("mum hugging...");
    }
    public void cry() {
        System.out.println("mum cring...");
    }

    @Override
    public void actionOnWakeUp(wakeUpEvent event) {
        hug();
    }

    @Override
    public void takeAction(takeActionEvent event) {
        cry();
    }
}

class Dog implements Observer {
    public void wang() {
        System.out.println("dog wang...");
    }
    public void bite() {
        System.out.println("dog biting...");
    }

    @Override
    public void actionOnWakeUp(wakeUpEvent event) {
        wang();
    }

    @Override
    public void takeAction(takeActionEvent event) {
        bite();
    }
}

public class Main {
    public static void main(String[] args) {
        Child c = new Child();
        c.wakeUp();
        Thief t = new Thief();
        t.takeAction();

    }
}
