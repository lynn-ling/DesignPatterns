package com.company.strategy;

public class Sorter {
    public static void sort(Cat[] arr){
        for (int i = 0; i < arr.length-1; i++) {
            int minPos = i;
            for (int j = i+1; j < arr.length; j++) {
                minPos = arr[j].compareTo(arr[minPos]) == -1 ? j : minPos;
            }
            System.out.println("minPos:" + minPos);
            swap(arr,i,minPos);
            System.out.println("经过第" + (i+1) + "次循环之后，数组的内容为：");
            print(arr);
        }
    }

    static void swap(Cat[] arr, int i, int j) {
        Cat temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void print(Cat[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
