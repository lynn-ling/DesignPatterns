package com.luckydog.dp.cor;

public class Main {
    public static void main(String[] args) {
        Msg msg = new Msg();
        msg.setMsg("大家好:)，<script>,欢迎访问 mashibing.com，大家都是996");

        new HTMLFilter().doFilter(msg);
        new SensitiveFilter().doFilter(msg);

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

//定义接口，让其他的filter的相关具体处理来实现这个接口的doFilter方法即可
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