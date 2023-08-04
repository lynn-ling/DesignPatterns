package com.company.singleton;

public class Main {

    public static void main(String[] args) {
	    //在外部无法new
//        Mgr01 mgr = new Mgr01();
        //想要用Mgr01的实例的时候，只能调用它的getInstance方法
        Mgr01 mgr = Mgr01.getInstance();
    }
}
