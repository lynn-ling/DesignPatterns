package com.company.observer.v9;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TestFrame1 extends Frame {

    public void launch() {
        //事件源对象：Button b，Button c
        Button b = new Button("press me");
        Button c = new Button("hello");
        b.addActionListener(new MyActionListener());
        c.addActionListener(new MyActionListener2());
        this.add(b,BorderLayout.NORTH);
        this.add(c,BorderLayout.SOUTH);
        this.pack();
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        this.setLocation(400,400);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new TestFrame1().launch();
    }

    //监听类：MyActionListener
    //监听者的接口：ActionListener
    private class MyActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) { // 事件类：ActionEvent
            //e.getSource：获取目前这个事件的事件源
            //这里就是获取Button的这个事件源，即b的那个按键，将按键的label改成press me again!
            ((Button)e.getSource()).setLabel("press me again!");
            System.out.println("button pressed!");
        }
    }

    //监听类：MyActionListener2
    private class MyActionListener2 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("button pressed 2!");
        }
    }

    //效果是：出现一个窗口，上面有一个按键名为press me的按键；下面有一个按键名为hello的按键
    //点击一下press me就会出现button pressed！然后press me会变成press me again。之后的每次点击都是press me again，并且结果都是button pressed！
    //每点击一次hello按键，结果都是button pressed 2！

}
