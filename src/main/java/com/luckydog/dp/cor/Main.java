package com.luckydog.dp.cor;

import java.util.ArrayList;
import java.util.List;

//需求：由FilterChain中的某一个Filter决定链条是否继续

public class Main {
    public static void main(String[] args) {
        Msg msg = new Msg();
        //情况①
//        msg.setMsg("大家好:)，<script>,欢迎访问 mashibing.com，大家都是996");
        //情况②
        msg.setMsg("大家好:)，<script>,欢迎访问 mashibing.com，大家都是992");

        FilterChain fc = new FilterChain();
        fc.add(new HTMLFilter()).add(new SensitiveFilter());

        /*
        因为SensitiveFilter在链条上比fc2这个链条靠前
        情况①：当SensitiveFilter后return false时，fc2链条的过滤都不执行
        情况②：当SensitiveFilter后return true时，fc2链条的过滤继续执行
         */
        FilterChain fc2 = new FilterChain();
        fc2.add(new FaceFilter()).add(new URLFilter());

        fc.add(fc2);
        fc.doFilter(msg);

        System.out.println(msg);
        /*
        情况①：
        Msg{msg='大家好:)，[script],欢迎访问 mashibing.com，大家都是955'}
        可以看到笑脸没有被替换，同时网址也没有变成过滤器里定义的那样
        情况②：
        Msg{msg='大家好^V^，[script],欢迎访问 http://www.mashibing.com，大家都是992'}
        没有关键字996，因此后面的链条过滤器继续执行
         */
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
    //当doFilter返回是true的时候链条继续往下执行，返回是false的时候就不继续往下执行了
    boolean doFilter(Msg m);
}

class HTMLFilter implements Filter{
    @Override
    public boolean doFilter(Msg m) {
        String r = m.getMsg();
        r = r.replace('<','[');
        r = r.replace('>',']');
        m.setMsg(r);
        return true;
    }
}

//SensitiveFilter这里return的是false，所以如果有996这个敏感词，那么它后面的链条就不会再执行
//如果没有996这个敏感词，它后面的链条就会执行
class SensitiveFilter implements Filter{
    @Override
    public boolean doFilter(Msg m) {
        String r = m.getMsg();
        if(r.contains("996")){
            r = r.replaceAll("996","955");
            m.setMsg(r);
            return false;
        }
        return true;

    }
}

class FaceFilter implements Filter{
    @Override
    public boolean doFilter(Msg m) {
        String r = m.getMsg();
        r = r.replace(":)","^V^");
        m.setMsg(r);
        return true;
    }
}

class URLFilter implements Filter{
    @Override
    public boolean doFilter(Msg m) {
        String r = m.getMsg();
        r = r.replaceAll("mashibing.com","http://www.mashibing.com");
        m.setMsg(r);
        return true;
    }
}

class FilterChain implements Filter{
    List<Filter> filters = new ArrayList<>();

    public FilterChain add(Filter f) {
        filters.add(f);
        return this;
    }

    public boolean doFilter(Msg m) {
        for(Filter f:filters){
            if(!f.doFilter(m)) return false;
        }
        return true;
    }
}