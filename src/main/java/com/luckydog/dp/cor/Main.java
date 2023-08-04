package com.luckydog.dp.cor;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Msg msg = new Msg();
        msg.setMsg("大家好:)，<script>,欢迎访问 mashibing.com，大家都是996");

        FilterChain fc = new FilterChain();
        fc.add(new HTMLFilter()).add(new SensitiveFilter());

        FilterChain fc2 = new FilterChain();
        fc2.add(new FaceFilter()).add(new URLFilter());

        fc.add(fc2);
        fc.doFilter(msg);

        System.out.println(msg);
        //Msg{msg='大家好^V^，[script],欢迎访问 http://www.mashibing.com，大家都是955'}
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

class FaceFilter implements Filter{
    @Override
    public void doFilter(Msg m) {
        String r = m.getMsg();
        r = r.replace(":)","^V^");
        m.setMsg(r);
    }
}

class URLFilter implements Filter{
    @Override
    public void doFilter(Msg m) {
        String r = m.getMsg();
        r = r.replaceAll("mashibing.com","http://www.mashibing.com");
        m.setMsg(r);
    }
}

class FilterChain implements Filter{
    List<Filter> filters = new ArrayList<>();

    public FilterChain add(Filter f) {
        filters.add(f);
        return this;
    }

    public void doFilter(Msg m) {
        for(Filter f:filters){
            f.doFilter(m);
        }
    }
}