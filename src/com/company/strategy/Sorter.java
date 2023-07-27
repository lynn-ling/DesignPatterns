package com.company.strategy;

public class Sorter {
    public static void sort(int[] arr){
        for (int i = 0; i < arr.length-1; i++) {
            //从数组的第一个元素开始，设置一个变量 minPos 表示当前最小元素的位置
            int minPos = i;
            //从第二个元素开始遍历到最后一个元素，每次比较当前位置的元素和 minPos 所表示的元素，如果当前位置的元素更小，则更新 minPos
            for (int j = i+1; j < arr.length; j++) {
                minPos = arr[j] < arr[minPos] ? j : minPos;
            }
            System.out.println("minPos:" + minPos);
            //在每次遍历结束后，交换当前位置的元素和 minPos 所表示的元素
            swap(arr,i,minPos);
            System.out.println("经过第" + (i+1) + "次循环之后，数组的内容为：");
            print(arr);
        }
    }

    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
