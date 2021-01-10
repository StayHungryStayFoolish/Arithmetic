package ink.bonismo.notebook.arithmetic.sort;

import java.util.Arrays;

/**
 * @author Created by bonismo@hotmail.com on 2019/7/17 11:39 AM
 * @Description:
 * @Version: 1.0
 * @link https://mp.weixin.qq.com/s?__biz=MzIxMjE5MTE1Nw==&mid=2653194666&idx=1&sn=69ce32870c0b981c40b1e124fbb6bba8&chksm=8c99fb70bbee72668cad223892ad362525d215e7f936458f99dd289eb82981099359310e9e54&scene=21#wechat_redirect
 */
public class BubbleSort {

    public static void bubbleSortSimple(int[] arr) {
        int temp = 0;
        for (int i = 0; i < arr.length; i++) {
            boolean isSort = true;
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    isSort = false;
                }
            }
            if (isSort) {
                break;
            }
        }
    }

    /**
     * {3,4,2,1,5,6,7,8} 该数组需要避免 5，6，7，8 的比较。因为已经是有序。
     */
    public static void bubbleSortOptimize(int[] arr) {
        int temp = 0;
        //记录最后一次交换的位置
        int lastExchangeIndex = 0;
        //无序数列的边界，每次比较只需要比到这里为止
        int sortBorder = arr.length - 1;
        for (int i = 0; i < arr.length; i++) {
            boolean isSort = true;
            for (int j = 0; j < sortBorder; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    //有元素交换，所以不是有序，标记变为false
                    isSort = false;
                    //把无序数列的边界更新为最后一次交换元素的位置
                    lastExchangeIndex = j;
                }
            }
            sortBorder = lastExchangeIndex;
            if (isSort) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {5, 10, 30, 2, 0, 9};
        bubbleSortOptimize(arr);
        System.out.println(Arrays.toString(arr));
    }
}
