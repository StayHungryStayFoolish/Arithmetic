package com.spring.demo;

import java.util.Stack;

/**
 * @Author: Created by bonismo@hotmail.com on 2019/7/22 11:47 AM
 * @Description: 用栈模拟队列
 * @Version: 1.0
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
