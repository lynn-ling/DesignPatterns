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
        大家好:)，[script]，欢迎访问 mashibing.com，大家都是955--HTMLFilter()--SensitiveFilter()
        response--SensitiveFilter()--HTMLFilter()
         */
    }
}



class Request {
    String str;

    @Override
    public String toString() {
        return "Request{" +
                "str='" + str + '\'' +
                '}';
    }
}

class Response {
    String str;

    @Override
    public String toString() {
        return "Response{" +
                "str='" + str + '\'' +
                '}';
    }
}

interface Filter {
    boolean doFilter(Request request, Response response,FilterChain chain);

}


class HTMLFilter implements Filter{
    @Override
    public boolean doFilter(Request request, Response response,FilterChain chain) {
        request.str = request.str.replaceAll("<","[").replaceAll(">","]") +  "--HTMLFilter()";
        chain.doFilter(request,response);
        response.str += "--HTMLFilter()";
        return true;
    }
}


class SensitiveFilter implements Filter{
    @Override
    public boolean doFilter(Request request, Response response,FilterChain chain) {
        request.str = request.str.replaceAll("996","955") + "--SensitiveFilter()";
        chain.doFilter(request,response);
        response.str += "--SensitiveFilter()";
        return true;
    }
}



class FilterChain{
    List<Filter> filters = new ArrayList<>();
    int index = 0;


    public FilterChain add(Filter f) {
        filters.add(f);
        return this;
    }

    public boolean doFilter(Request request, Response response) {
        if(index == filters.size()) return false;
        else{
            Filter f = filters.get(index);
            index ++;
            return f.doFilter(request,response,this);
        }
    }
}