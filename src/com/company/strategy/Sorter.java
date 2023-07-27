package com.company.strategy;

public class Sorter {

    //接口作为参数，是说给这个方法传参数的时候，可以把继承了这个接口的类的实例传给这个方法
    public static void sort(Comparable[] arr){
        for (int i = 0; i < arr.length-1; i++) {
            int minPos = i;
            for (int j = i+1; j < arr.length; j++) {
                minPos = arr[j].compareTo(arr[minPos]) == -1 ? j : minPos;
            }
            swap(arr,i,minPos);
        }
    }

    static void swap(Comparable[] arr, int i, int j) {
        Comparable temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void print(Comparable[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
