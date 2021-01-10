package ink.bonismo.notebook.arithmetic.sort;

import java.util.Arrays;

/**
 * @Author: Created by bonismo@hotmail.com on 2020/10/14 3:40 下午
 * @Description:
 * @Version: 1.0
 */
public class SelectSort_One {

    public static void main(String[] args) {
        int[] arr = {5, 10, 30, 2, 0, 9};
        selectionSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void selectionSort(int[] arr) {
        if (null == arr || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length - 1; i++) {
            int midIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                midIndex = arr[j] < arr[midIndex] ? j : midIndex;
            }
            swap(arr, i, midIndex);
        }
    }

    private static void swap(int[] arr, int i, int midIndex) {
        int temp = arr[i];
        arr[i] = arr[midIndex];
        arr[midIndex] = temp;
    }
}
