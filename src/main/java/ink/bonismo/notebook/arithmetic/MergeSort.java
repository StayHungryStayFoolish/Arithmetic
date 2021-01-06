package ink.bonismo.notebook.arithmetic;

import java.util.Arrays;

/**
 * @Author: Created by bonismo@hotmail.com on 2019/7/29 5:56 PM
 * @Description:
 * @Version: 1.0
 * @Link: https://mp.weixin.qq.com/s/FVOllQoELEK3rvjpdLAxIQ
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] array = new int[]{9, 8, 1, 7, 2, 3, 5, 4, 6, 0};
        sort(array);
        System.out.println(Arrays.toString(array));
    }

    public static void sort(int[] array) {
        int[] temp = new int[array.length];
        sort(array, temp, 0, array.length - 1);
    }


    private static void sort(int[] array, int[] tempArr, int startIndex, int endIndex) {
        if (endIndex <= startIndex) {
            return;
        }
        // 数组中间下标
        int middleIndex = startIndex + (endIndex - startIndex) / 2;
        sort(array, tempArr, startIndex, middleIndex);
        sort(array, tempArr, middleIndex + 1, endIndex);
        merge(array, tempArr, startIndex, middleIndex, endIndex);
    }

    private static void merge(int[] array, int[] tempArr, int startIndex, int middleIndex, int endIndex) {
        // 复制合并的数据
        for (int v = startIndex; v <= endIndex; v++) {
            tempArr[v] = array[v];
        }
        // 数组左边第一个下标
        int left = startIndex;
        // 数组右边第一个下标
        int right = middleIndex + 1;
        for (int k = startIndex; k <= endIndex; k++) {
            if (left > middleIndex) {
                array[k] = tempArr[right++];
            } else if (right > endIndex) {
                array[k] = tempArr[left++];
            } else if (tempArr[right] < tempArr[left]) {
                array[k] = tempArr[right++];
            } else {
                array[k] = tempArr[left++];
            }
        }
    }
}
