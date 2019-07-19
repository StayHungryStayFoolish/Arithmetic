package com.spring.demo;

import java.util.Arrays;

/**
 * @author Created by bonismo@hotmail.com on 2019/6/21 3:23 PM
 * @Description: Sort Practice
 * @Version: 1.0
 * @link https://www.cnblogs.com/guoyaohua/p/8600214.html
 */
public class SortPractice {

    public static int[] bubbleSort(int[] array) {
        if (array.length == 0) {
            return array;
        }
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j + 1] < array[j]) {
                    int temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                    count++;
                }
            }
        }
        System.out.println("Count : "+count);
        return array;
    }

    public static int[] selectionSort(int[] array) {
        if (array.length == 0) {
            return array;
        }
        for (int i = 0; i < array.length; i++) {
            int minIndex = i;
            for (int j = 0; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    // record min index
                    minIndex = j;
                }
            }
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
        return array;
    }

    public static int[] insertionSort(int[] array) {
        if (array.length == 0) {
            return array;
        }
        int current;
        for (int i = 0; i < array.length - 1; i++) {
            int preIndex = i;
            current = array[i + 1];
            while (preIndex >= 0 && current < array[preIndex]) {
                array[preIndex + 1] = array[preIndex];
                preIndex--;
            }
            array[preIndex + 1] = current;
        }
        return array;
    }

    public static int[] shellSort(int[] array) {
        if (array.length == 0) {
            return array;
        }
        int len = array.length;
        int temp;
        int gap = len / 2;
        while (gap > 0) {
            for (int i = gap; i < len; i++) {
                // 索引 5 的值
                temp = array[i];
                // 前索引 0
                int preIndex = i - gap;
                int per = array[preIndex];
                // 如果索引 0 > 索引 5 的值
                while (preIndex >= 0 && per > temp) {
                    // 索引 5 的值改为索引 0 的值
                    System.out.println("数组交换： " + array[preIndex + gap] + " -- Before -- " + array[preIndex]);
                    array[preIndex + gap] = array[preIndex];
                    System.out.println("数组交换： " + array[preIndex + gap] + " -- After -- " + array[preIndex]);


                    // 索引发生变化，
                    System.out.println(gap + " -- Before -- " + preIndex);

                    preIndex -= gap;

                    System.out.println(gap + " -- After -- " + preIndex);
                }
                array[preIndex + gap] = temp;
            }
            gap /= 2;
            System.out.println("Gap == " + gap);
        }
        return array;
    }

    public static int[] mergeSort(int[] array) {
        if (array.length < 2) {
            return array;
        }
        int mid = array.length / 2;
        int[] left = Arrays.copyOfRange(array, 0, mid);
        int[] right = Arrays.copyOfRange(array, mid, array.length);
        return merge(mergeSort(left), mergeSort(right));
    }

    private static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        for (int index = 0, i = 0, j = 0; index < result.length; index++) {
            if (i >= left.length) {
                result[index] = right[j++];
            } else if (j >= right.length) {
                result[index] = left[i++];
            } else if (left[i] > right[j]) {
                result[index] = right[j++];
            } else {
                result[index] = left[i++];
            }
        }
        return result;
    }

    public static int[] quickSort(int[] array, int start, int end) {
        if (array.length < 1 || start < 0 || end >= array.length || start > end) {
            return null;
        }
        int smallIndex = partition(array, start, end);
        if (smallIndex > start) {
            quickSort(array, start, smallIndex - 1);
        }
        if (smallIndex < end) {
            quickSort(array, smallIndex + 1, end);
        }
        return array;
    }

    private static int partition(int[] array, int start, int end) {
        int pivot = (int) (start + Math.random() * (end - start + 1));
        int smallIndex = start - 1;
        swap(array, pivot, end);
        for (int i = start; i <= end; i++) {
            if (array[i] <= array[end]) {
                System.out.println(array[i] + " == " + array[end]);
                smallIndex++;
                if (i > smallIndex) {
                    swap(array, i, smallIndex);
                }
            }
        }
        return smallIndex;
    }

    private static void swap(int[] array, int pivot, int end) {
        int temp = array[pivot];
        array[pivot] = array[end];
        array[end] = temp;
    }

    public static void main(String[] args) {
        int[] array = new int[]{9, 8, 1, 7, 2, 3, 5, 4, 6, 0};
//        int[] result = insertionSort(array);
//        int[] result = mergeSort(array);
//        int[] result = quickSort(array, 0, array.length - 1);
//        int[] result = bubbleSort(array);
//        System.out.println(Arrays.toString(result));
    }
}
