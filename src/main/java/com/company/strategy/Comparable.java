package com.company.strategy;

/*我想用Sorter类里的sort方法对狗也进行排序，我自己写个Comparable接口
要求所有要用我Sorter类进行排序的，请你实现接口，里面必须有一个compareTo方法
在用我这个接口的时候，请指定自己需要的类型，然后在我compareTo方法里面看到T的地方就把指定的类型替换过来
比如Cat要用，那么T的位置就是Cat；Dog要用，那么T的位置就是Dog*/
public interface Comparable<T> {
    int compareTo(T o);
}
