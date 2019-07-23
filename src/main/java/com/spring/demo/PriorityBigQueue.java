package com.spring.demo;

import java.util.Arrays;

/**
 * @Author: Created by bonismo@hotmail.com on 2019/7/22 12:33 PM
 * @Description: 构建最大堆，使用优先队列，优先弹出最大元素
 * @Version: 1.0
 * @link: https://mp.weixin.qq.com/s?__biz=MzIxMjE5MTE1Nw==&mid=2653195301&idx=1&sn=9d380bbf3c507ab684ff51596673268f&chksm=8c99feffbbee77e97fe047bdfd4afa8084f09d28df8516a6436207c13fed2c8c5366a8c5677e&scene=21#wechat_redirect
 */
public class PriorityBigQueue {


    private int[] array;
    private int size;

    public PriorityBigQueue() {
        this.array = new int[5];
    }


    /**
     * 元素入队
     *
     * @param element 元素
     */
    private void enQueue(int element) {
        if (size >= array.length) {
            resize();
        }
        array[size++] = element;
        upAdjust();
    }

    /**
     * 最大元素优先出队
     *
     * @return 优先队列中的最大元素
     */
    private int deQueue() throws Exception {
        if (size < 0) {
            throw new Exception("The Queue is empty !");
        }
        int head = array[0];
        array[0] = array[--size];
        downAdjust();
        System.out.println("---" + Arrays.toString(array));
        return head;
    }

    /**
     * 当子节点大于父节点时，子节点上浮
     * 注：该方法不同于二叉堆的最小堆的上浮算法。因为该方法的 size 一直在变化，非固定值。
     */
    private void upAdjust() {
        int childIndex = size - 1;
        int parentIndex = (childIndex - 1) / 2;
        int temp = array[childIndex];
        // 当子节点大于父节点时，将父节点值单向赋值给子节点
        while (childIndex > 0 && temp > array[parentIndex]) {
            array[childIndex] = array[parentIndex];
            childIndex = parentIndex;
            parentIndex = (childIndex - 1) / 2;
        }
        array[childIndex] = temp;
    }


    /**
     * 当父节点元素小于任意子元素时，父节点元素下沉
     * 注：该方法不同于二叉堆的最小堆、最大堆的上浮算法。因为该方法的 parentIndex 和 childIndex 在初始时是固定值，非变化的。
     */
    private void downAdjust() {
        int parentIndex = 0;
        int childIndex = 1;
        int temp = array[parentIndex];
        while (childIndex < size) {
            // 如果有右子节点，且右子节点大于左子节点，则定位到右子节点
            if (childIndex + 1 < size && array[childIndex + 1] > array[childIndex]) {
                childIndex++;
            }
            // 父节点大于子节点，直接退出当前循环
            if (temp > array[childIndex]) {
                break;
            }
            array[parentIndex] = array[childIndex];
            parentIndex = childIndex;
            childIndex = childIndex * 2 + 1;
        }
        array[parentIndex] = temp;
    }

    /**
     * 数组扩容
     */
    private void resize() {
        int newSize = this.size * 2;
        this.array = Arrays.copyOf(this.array, newSize);
    }

    public static void main(String[] args) throws Exception {
        PriorityBigQueue queue = new PriorityBigQueue();
        queue.enQueue(3);
        queue.enQueue(5);
        queue.enQueue(10);
        queue.enQueue(2);
        queue.enQueue(7);
        System.out.println("Inner Array : " + Arrays.toString(queue.array));
        System.out.println("DeQueue Element : " + queue.deQueue());
        System.out.println("DeQueue Element : " + queue.deQueue());
        System.out.println("DeQueue Element : " + queue.deQueue());
        System.out.println("DeQueue Element : " + queue.deQueue());
        System.out.println("DeQueue Element : " + queue.deQueue());
    }
}
