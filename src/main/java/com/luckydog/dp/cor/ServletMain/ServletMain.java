package com.luckydog.dp.cor.ServletMain;

import java.util.ArrayList;
import java.util.List;

public class ServletMain {
    public static void main(String[] args) {
        Request request = new Request();
        request.str= "大家好:)，<script>，欢迎访问 mashibing.com，大家都是996";
        Response response = new Response();
        response.str = "response";

        FilterChain chain = new FilterChain();
        chain.add(new HTMLFilter()).add(new SensitiveFilter());
        chain.doFilter(request,response);
        System.out.println(request.str);
        System.out.println(response.str);
        /*
        结果为；
        大家好:)，[script]，欢迎访问 mashibing.com，大家都是955
        response--HTMLFilter()--SensitiveFilter()

        可见处理Request的时候，是按照HTMLFilter() -→ SensitiveFilter()的过滤舒徐，
        但无发实现处理Response的时候，按照SensitiveFilter() -→ HTMLFilter()的方向进行过滤的功能
         */
    }
}



class Request {
    String str;
}

class Response {
    String str;
}

interface Filter {
    boolean doFilter(Request request, Response response);
}


class HTMLFilter implements Filter{
    @Override
    public boolean doFilter(Request request, Response response) {
        request.str = request.str.replaceAll("<","[").replaceAll(">","]");
        response.str += "--HTMLFilter()";
        return true;
    }
}


class SensitiveFilter implements Filter{
    @Override
    public boolean doFilter(Request request, Response response) {
        request.str = request.str.replaceAll("996","955");
        response.str += "--SensitiveFilter()";
        return true;
    }
}



class FilterChain implements Filter{
    List<Filter> filters = new ArrayList<>();

    public FilterChain add(Filter f) {
        filters.add(f);
        return this;
    }

    public boolean doFilter(Request request, Response response) {
        for(Filter f:filters){
            f.doFilter(request,response);
        }
        return true;
    }
}