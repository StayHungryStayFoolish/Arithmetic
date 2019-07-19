package com.spring.demo;

import java.util.Arrays;

/**
 * @author Created by bonismo@hotmail.com on 2019/7/17 3:51 PM
 * @Description: 鸡尾酒排序只使用大部分元素已经有序前提。（冒泡排序变种）
 * @Version: 1.0
 * @link: https://mp.weixin.qq.com/s?__biz=MzIxMjE5MTE1Nw==&mid=2653194919&idx=2&sn=f473bc9e0da124a303312a11902e2f52&chksm=8c99f87dbbee716b531df3fcf5882998f28794aad5609f225883d6c2dc71ba51b8a5126b32be&scene=21#wechat_redirect
 */
public class CocktailSort {


    public static void cocktailSort(int[] arr) {
        int temp = 0;
        for (int i = 0; i < arr.length / 2; i++) {
            //有序标记，每一轮的初始是true
            boolean isSort = true;
            // 从左到右比较并交换值
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
            isSort = true;
            // 从右到左比较并交换值
            for (int j = arr.length - i - 1; j > i; j--) {
                if (arr[j] < arr[j - 1]) {
                    temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                    isSort = false;
                }
            }
            if (isSort) {
                break;
            }
        }
    }

    public static void cocktailSortOptimize(int[] arr) {
        int temp = 0;
        // 记录右侧最后一次交换位置
        int lastRightExchangeIndex = 0;
        // 记录左侧最后一次交换位置
        int lastLeftExchangeIndex = 0;
        // 无序右边界，比较到该位置即可
        int rightBorder = arr.length - 1;
        // 无序左边界，比较到该位置即可
        int leftBorder = 0;
        for (int i = 0; i < arr.length / 2; i++) {
            //有序标记，每一轮的初始是true
            boolean isSort = true;
            // 从左到右比较并交换值
            for (int j = leftBorder; j < rightBorder; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    isSort = false;
                    lastRightExchangeIndex = j;
                }
            }
            rightBorder = lastRightExchangeIndex;
            if (isSort) {
                break;
            }
            isSort = true;
            // 从右到左比较并交换值
            for (int j = rightBorder; j > leftBorder; j--) {
                if (arr[j] < arr[j - 1]) {
                    temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                    isSort = false;
                    lastLeftExchangeIndex = j;
                }
            }
            leftBorder = lastLeftExchangeIndex;
            if (isSort) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2, 3, 4, 5, 6, 7, 8, 1};
//        int[] arr = new int[]{5, 8, 6, 3, 9, 2, 1, 7};
        cocktailSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
