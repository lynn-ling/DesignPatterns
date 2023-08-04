package com.company.abstractfactory;

//这里的食物，是显示生活中有的一个东西，并且不是具体的某个食物，所以用抽象方法比较合适
//所以一般情况，名词用抽象类，形容词用接口
public abstract class Food {
    abstract void printName();
}
