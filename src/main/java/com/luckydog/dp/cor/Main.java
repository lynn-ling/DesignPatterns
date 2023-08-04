package com.luckydog.dp.cor;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Msg msg = new Msg();
        msg.setMsg("大家好:)，<script>,欢迎访问 mashibing.com，大家都是996");

        List<Filter> filters = new ArrayList<>();
        filters.add(new HTMLFilter());
        filters.add(new SensitiveFilter());

        for(Filter f:filters){
            f.doFilter(msg);
        }

        System.out.println(msg);
        //Msg{msg='大家好:)，[script],欢迎访问 mashibing.com，大家都是955'}
    }
}

class Msg {
    String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Msg{" +
                "msg='" + msg + '\'' +
                '}';
    }
}

interface Filter {
    void doFilter(Msg m);
}

class HTMLFilter implements Filter{
    @Override
    public void doFilter(Msg m) {
        String r = m.getMsg();
        r = r.replace('<','[');
        r = r.replace('>',']');
        m.setMsg(r);
    }
}

class SensitiveFilter implements Filter{
    @Override
    public void doFilter(Msg m) {
        String r = m.getMsg();
        r = r.replaceAll("996","955");
        m.setMsg(r);
    }
}