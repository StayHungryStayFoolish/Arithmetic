package ink.bonismo.notebook.arithmetic;

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
        System.out.println("Count : " + count);
        return array;
    }

    public static int[] selectionSort(int[] array) {
        if (array.length == 0) {
            return array;
        }
        for (int i = 0; i < array.length - 1; i++) {
            int min = i;
            // 每轮需要比较的次数 N-i
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[min]) {
                    // 记录目前能找到的最小值元素的下标
                    min = j;
                }
            }
            // 将找到的最小值和i位置所在的值进行交换
            if (i != min) {
                int tmp = array[i];
                array[i] = array[min];
                array[min] = tmp;
            }
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

    public static int[] insertionSortTwo(int[] array) {
        if (array.length == 0) {
            return array;
        }
        for (int i = 0; i < array.length - 1; i++) {
            int value = array[i];
            int j = 0;
            for (j = i - 1; j >= 0; j--) {
                if (array[j] > value) {
                    array[j + 1] = array[j];
                } else {
                    break;
                }
            }
            array[j + 1] = value;
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
                //如果左边的首位下标大于中部下标，证明左边的数据已经排完了
                result[index] = right[j++];
            } else if (j >= right.length) {
                //如果右边的首位下标大于了数组长度，证明右边的数据已经排完了。
                result[index] = left[i++];
            } else if (left[i] > right[j]) {
                //将右边的首位排入，然后右边的下标指针+1。
                result[index] = right[j++];
            } else {
                //将左边的首位排入，然后左边的下标指针+1。
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
        int[] result = insertionSort(array);
//        int[] result = mergeSort(array);
//        int[] result = quickSort(array, 0, array.length - 1);
//        int[] result = bubbleSort(array);
//        int[] result = selectionSort(array);
        System.out.println(Arrays.toString(result));
    }
}
