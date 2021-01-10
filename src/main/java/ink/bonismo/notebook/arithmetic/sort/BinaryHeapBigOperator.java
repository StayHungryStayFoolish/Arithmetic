package ink.bonismo.notebook.arithmetic.sort;

import java.util.Arrays;

/**
 * @Author: Created by bonismo@hotmail.com on 2019/7/19 5:16 PM
 * @Description: 构建最大堆
 * @Version: 1.0
 * @Link: https://mp.weixin.qq.com/s?__biz=MzIxMjE5MTE1Nw==&mid=2653195208&idx=1&sn=e3d6559402148458f0a4993b47d8bc6f&chksm=8c99f912bbee7004625a0b204acc8484acbdf4f1b18953e7ff5acbea958ec002d8c8ea072792&scene=21#wechat_redirect
 */
public class BinaryHeapBigOperator {

    /**
     * 下沉调整，将小于子节点元素下沉
     *
     * @param array       待调整的堆
     * @param parentIndex 要下沉几点
     * @param length      堆的有效大小
     */
    public static void downAdjust(int[] array, int parentIndex, int length) {
        int temp = array[parentIndex];
        int childIndex = parentIndex * 2 + 1;
        while (childIndex < length) {
            if (childIndex + 1 < length && array[childIndex + 1] > array[childIndex]) {
                childIndex++;
            }
            // 如果父节点大于等于任何一个子节点跳出循环
            if (temp >= array[childIndex]) {
                break;
            }
            array[parentIndex] = array[childIndex];
            parentIndex = childIndex;
            childIndex = 2 * childIndex + 1;
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

    public static void heapSort(int[] array) {
        buildHeap(array);
        System.out.println(Arrays.toString(array));
        // 循环删除堆顶元素，移到集合尾部，调节堆产生新的堆顶。
        // 最大堆从尾部依次删除，自动排序。
        for (int i = array.length - 1; i > 0; i--) {
            int temp = array[i];
            array[i] = array[0];
            array[0] = temp;
            downAdjust(array, 0, i);
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{1, 3, 2, 6, 5, 7, 8, 9, 10, 0};
        heapSort(array);
        System.out.println("Big Binary Heap Sort: " + Arrays.toString(array));

    }
}
