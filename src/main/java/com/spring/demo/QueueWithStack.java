package com.spring.demo;

import java.util.Stack;

/**
 * @Author: Created by bonismo@hotmail.com on 2019/7/22 11:47 AM
 * @Description: 用栈模拟队列
 * @Version: 1.0
 * @Link: https://mp.weixin.qq.com/s?__biz=MzIxMjE5MTE1Nw==&mid=2653195750&idx=1&sn=231558735ddf64a9c10ad721ad537ea2&chksm=8c99ff3cbbee762a37e74e42c585688bf6fbfe70422d723b29219d1d9bbdf524217c97e2228b&scene=21#wechat_redirect
 */
public class QueueWithStack {

    private Stack<Integer> stackA = new Stack<>();
    private Stack<Integer> stackB = new Stack<>();

    /**
     * 入队
     */
    public void enQueue(int element) {
        stackA.push(element);
    }

    /**
     * 出队
     */
    public Integer deQueue() {
        if (stackB.isEmpty()) {
            if (stackA.isEmpty()) {
                return null;
            }
            transfer();
        }
        return stackB.pop();
    }

    private void transfer() {
        while (!stackA.isEmpty()) {
            stackB.push(stackA.pop());
        }
    }

    public static void main(String[] args) {
        QueueWithStack queue = new QueueWithStack();
        queue.enQueue(1);
        queue.enQueue(2);
        queue.enQueue(3);
        System.out.println("DeQueue : " + queue.deQueue());
        System.out.println("Stack A : " + queue.stackA);
        System.out.println("Stack B : " + queue.stackB);
        queue.enQueue(4);
        System.out.println("DeQueue : " + queue.deQueue());
        System.out.println("DeQueue : " + queue.deQueue());
        System.out.println("DeQueue : " + queue.deQueue());
        System.out.println("DeQueue : " + queue.deQueue());
        System.out.println("Stack A : " + queue.stackA);
        System.out.println("Stack B : " + queue.stackB);

    }
}
