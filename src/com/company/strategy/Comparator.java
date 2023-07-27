package com.company.strategy;

//定义一个Comparator接口，里面有compare方法
//这样想加什么比较器，只要实现这个接口，一一进行添加即可，不需要在Dog类或者Cat类里进行比较的代码的修改与添加了
//比如CatHeightComparator比较器，CatWeightComparator比较器以及DogComparator比较器，等等
//这就是策略模式
public interface Comparator<T> {
    int compare(T o1, T o2);
}
