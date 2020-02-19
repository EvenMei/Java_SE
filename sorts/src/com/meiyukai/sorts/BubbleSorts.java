package com.meiyukai.sorts;

import java.util.Arrays;

public class BubbleSorts {


    /**
     * 冒泡排序
     * @param args
     */
    public static void main(String[] args) {

        int a[] =new int[]{2,8,20,11,9,7,3,22,70};
        for(int i = 0; i<a.length-1 ; i++){
            for(int j=0 ; j<a.length-1-i ; j++){
                if(a[j] > a[j+1]){
                    int temp = a[j];
                    a[j]=a[j+1];
                    a[j+1] = temp;
                }
            }
        }


        System.out.println("sort :  " + Arrays.toString(a));
    }
}
