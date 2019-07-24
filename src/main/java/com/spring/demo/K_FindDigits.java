package com.spring.demo;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.Arrays;

/**
 * @Author: Created by bonismo@hotmail.com on 2019/7/24 11:54 AM
 * @Description: 查找第 K 大的数字
 * @Version: 1.0
 * @Link: https://mp.weixin.qq.com/s?__biz=MzIxMjE5MTE1Nw==&mid=2653196576&idx=2&sn=482f6678841c0ff40e3847a7d31714cd&chksm=8c99e3fabbee6aec84dd5ceb3f37c4754c68d7605db37bf86e9bf96c97b90647a83750bf72af&mpshare=1&scene=23&srcid=#rd
 * 1. 排序法
 * 2. 使用堆特性
 * 3. 分治法
 */
public class K_FindDigits {

    /**
     * 最小元素出队
     *
     * @return 优先队列最小元素
     */
    public static int deQueue(int[] array) {
        int size = array.length;
        // 取最小堆得第一个元素
        int head = array[0];
        // 将最后一个元素移到第一位
        array[0] = array[--size];
        // 重新构建最小堆
        buildHeap(array, array.length);
        System.out.println(" ---- " + Arrays.toString(array));
        return head;
    }

    /**
     * 查找数组中第 K 大的数字
     *
     * @param array 数组
     * @return 最小堆顶元素
     */
    public static int findNumberK(int[] array, int k) {
        // 将数组前 K 个元素建立最小堆
        buildHeap(array, k);
        // 从 K 遍历整个数组，当遍历当前元素大于堆顶元素时交换，然后调整堆
        for (int i = k; i < array.length; i++) {
            if (array[i] > array[0]) {
                array[0] = array[i];
                downAdjust(array, 0, k);
            }
        }
        return array[0];
    }

    /**
     * 节点下沉调整（父节点元素大于子节点下沉）
     *
     * @param array  待调整堆
     * @param index  调整节点
     * @param length 堆大小
     */
    private static void downAdjust(int[] array, int index, int length) {
        int childIndex = index * 2 + 1;
        int temp = array[index];
        while (childIndex < length) {
            if (childIndex + 1 < length && array[childIndex + 1] < array[childIndex]) {
                childIndex++;
            }
            if (temp <= array[childIndex]) {
                break;
            }
            array[index] = array[childIndex];
            index = childIndex;
            childIndex = childIndex * 2 + 1;
        }
        array[index] = temp;
    }

    /**
     * 构建最小堆
     *
     * @param array  待调整堆
     * @param length 堆大小
     */
    private static void buildHeap(int[] array, int length) {
        for (int i = (length - 2) / 2; i >= 0; i--) {
            downAdjust(array, i, length);
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{7, 5, 15, 3, 17, 2, 20, 24, 1, 9, 12, 8};
        System.out.println(findNumberK(array, 5));

        int[] arr = new int[]{1, 2, 5, 3, 8, 6, 7, 11, 4, 10, 9, 12};
        buildHeap(arr, arr.length);
        System.out.println(Arrays.toString(arr));
        System.out.println("= : " + deQueue(arr));
        System.out.println("= : " + deQueue(arr));
        System.out.println("= : " + deQueue(arr));
        System.out.println("= : " + deQueue(arr));
        System.out.println("= : " + deQueue(arr));

    }
}
