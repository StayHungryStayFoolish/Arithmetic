package ink.bonismo.notebook.arithmetic;

import java.util.Arrays;

/**
 * @Author: Created by bonismo@hotmail.com on 2019/7/22 4:38 PM
 * @Description: 构建最小堆，使用优先队列输出最小元素
 * @Version: 1.0
 */
public class PrioritySmallQueue {

    private int[] array;
    private int size;

    public PrioritySmallQueue() {
        this.array = new int[5];
    }

    /**
     * 入队
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
     * 最小元素出队
     *
     * @return 优先队列最小元素
     */
    private int deQueue() throws Exception {
        if (size < 0) {
            throw new Exception("The Queue is empty !");
        }
        // 取最小堆得第一个元素
        int head = array[0];
        // 将最后一个元素移到第一位
        array[0] = array[--size];
        // 重新构建最小堆
        buildHeap(array);
        return head;
    }

    /**
     * 当父节点大于子节点时，子节点元素上浮
     */
    private void upAdjust() {
        int childIndex = size - 1;
        int parentIndex = (childIndex - 1) / 2;
        int temp = array[childIndex];
        while (childIndex > 0 && temp < array[parentIndex]) {
            array[childIndex] = array[parentIndex];
            childIndex = parentIndex;
            parentIndex = (childIndex - 1) / 2;
        }
        array[childIndex] = temp;
    }

    /**
     * 构建最小堆
     *
     * @param array 无序数组
     */
    private void buildHeap(int[] array) {
        // 根据最后一个非叶子节点下标递减
        // 最后一个非叶子节点下标: (array.length - 2) / 2
        for (int i = (size - 2) / 2; i >= 0; i--) {
            downAdjust(array, i, size);
        }
    }

    /**
     * 如果父节点大于子节点，父节点下沉调整
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

            // 如果父节点小于等于任何子节点，跳出
            if (temp <= array[childIndex]) {
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
        PrioritySmallQueue queue = new PrioritySmallQueue();
//        {7, 1, 3, 10, 5, 2, 8, 9, 6};
//        queue.enQueue(3);
//        queue.enQueue(5);
//        queue.enQueue(10);
//        queue.enQueue(2);
//        queue.enQueue(7);
        queue.enQueue(7);
        queue.enQueue(11);
        queue.enQueue(1);
        queue.enQueue(3);
        queue.enQueue(10);
        queue.enQueue(12);
        queue.enQueue(5);
        queue.enQueue(4);
        queue.enQueue(2);
        queue.enQueue(8);
        queue.enQueue(9);
        queue.enQueue(6);
        // [1, 5, 2, 6, 7, 3, 8, 9, 10]
        System.out.println("Inner Array : " + Arrays.toString(queue.array));
        System.out.println("DeQueue Element : " + queue.deQueue());
        System.out.println("DeQueue Element : " + queue.deQueue());
        System.out.println("DeQueue Element : " + queue.deQueue());
        System.out.println("DeQueue Element : " + queue.deQueue());
        System.out.println("DeQueue Element : " + queue.deQueue());
        System.out.println("DeQueue Element : " + queue.deQueue());
        System.out.println("DeQueue Element : " + queue.deQueue());
        System.out.println("DeQueue Element : " + queue.deQueue());
        System.out.println("DeQueue Element : " + queue.deQueue());
        System.out.println("DeQueue Element : " + queue.deQueue());
        System.out.println("DeQueue Element : " + queue.deQueue());
        System.out.println("DeQueue Element : " + queue.deQueue());
        System.out.println("Inner Array : " + Arrays.toString(queue.array));
    }
}
