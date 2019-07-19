package com.spring.demo;

import java.util.Arrays;

/**
 * @Author: Created by bonismo@hotmail.com on 2019/7/19 10:49 AM
 * @Description:
 * @Version: 1.0
 * @Link: https://mp.weixin.qq.com/s?__biz=MzIxMjE5MTE1Nw==&mid=2653195207&idx=2&sn=12689c6c1a92e7ec3cce4d423019ec2a&chksm=8c99f91dbbee700b8e760d06b27582037ab0713295dacf2b5a7a7f954c0032fe860aa0bf8b74&scene=21#wechat_redirect
 * 二叉堆：完全二叉树，顺序存储，数组形式
 * 假设：父节点下标是 parent，左孩子节点下标是 2*parent+1，由孩子节点下标是 2*parent+2
 * 父节点下标是: floor(i-1)/2
 * 最后一个非叶子节点下标: (array.length - 2) / 2
 */
public class SmallBinaryHeapOperator {

    /**
     * 节点上浮调整
     *
     * @param array 原二叉堆
     */
    public static void upAdjust(int[] array) {
        int childIndex = array.length - 1;
        int parentIndex = (childIndex - 1) / 2;
        // 保存插入叶子节点值
        int temp = array[childIndex];
        // 当子节点索引大于0（还有父节点时）且插入叶子节点值小于当前父节点值进行交换
        while (childIndex > 0 && temp < array[parentIndex]) {
            // 原父节点下沉至子节点
            array[childIndex] = array[parentIndex];
            // 原父节点索引变为新子节点索引
            childIndex = parentIndex;
            // 根据二叉堆原理，计算新父节点索引
            parentIndex = (parentIndex - 1) / 2;
        }
        // 循环结束后，将插入值父值到最新子节点索引
        array[childIndex] = temp;
    }

    /**
     * 节点下沉调整
     *
     * @param array       原二叉堆
     * @param parentIndex 下沉父节点下标索引
     */
    public static void downAdjust(int[] array, int parentIndex, int length) {
        // 保存父节点值
        int temp = array[parentIndex];
        int childIndex = 2 * parentIndex + 1;
        while (childIndex < length) {
            // 判断是否有右节点，且右节点值小于左节点，子节点定位到右节点
            if (childIndex + 1 < length && array[childIndex + 1] < array[childIndex]) {
                childIndex++;
            }

            // 如果父节点小于任何子节点，跳出
            if (temp <= array[childIndex]) {
                break;
            }

            array[parentIndex] = array[childIndex];
            parentIndex = childIndex;
            childIndex = childIndex * 2 + 1;
        }
        array[parentIndex] = temp;
    }

    public static void buildHeap(int[] array) {
        // 根据最后一个非叶子节点下标递减
        // 最后一个非叶子节点下标: (array.length - 2) / 2
        for (int i = (array.length - 2) / 2; i >= 0; i--) {
            downAdjust(array, i, array.length);
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{1, 3, 2, 6, 5, 7, 8, 9, 10, 0};
        upAdjust(array);
        System.out.println(Arrays.toString(array));
        int[] disorder = new int[]{7, 1, 3, 10, 5, 2, 8, 9, 6};
        buildHeap(disorder);
        System.out.println(Arrays.toString(disorder));
    }
}
