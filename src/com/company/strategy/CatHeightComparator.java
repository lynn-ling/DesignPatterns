package com.company.strategy;

//实现Comparator接口，比较Cat的height大小
public class CatHeightComparator implements Comparator<Cat>{
    @Override
    public int compare(Cat o1, Cat o2) {
        if(o1.height < o2.height) return 1;
        else if(o1.height > o2.height) return -1;
        else return 0;
    }
}
