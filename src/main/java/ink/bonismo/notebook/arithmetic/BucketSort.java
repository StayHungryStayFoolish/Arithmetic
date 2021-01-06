package ink.bonismo.notebook.arithmetic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

/**
 * @Author: Created by bonismo@hotmail.com on 2019/7/18 11:17 AM
 * @Description:
 * @Version: 1.0
 * @link: https://mp.weixin.qq.com/s?__biz=MzIxMjE5MTE1Nw==&mid=2653195582&idx=1&sn=1e7ece4e48c20fb994e2cefdcbdce4c5&chksm=8c99ffe4bbee76f23d16ac1e0c7feeb16654ebb75e40d92c911bffa113059f52ce4508281a55&scene=21#wechat_redirect
 */
public class BucketSort {

    public static double[] bucketSort(double[] arr) {
        double max = arr[0];
        double min = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
            if (arr[i] < min) {
                min = arr[i];
            }
        }

        double differenceValue = max - min;
        // 初始化桶
        int bucketNum = arr.length;
        ArrayList<LinkedList<Double>> bucketList = new ArrayList<>();
        for (int i = 0; i < bucketNum; i++) {
            bucketList.add(new LinkedList<>());
        }

        // 遍历原数组，将元素放入桶
        for (int i = 0; i < arr.length; i++) {
            // 定位元素所在桶的索引位置，按照比例定位：(array[i] - min)  * (bucketNum-1) / d
            int num = (int) ((arr[i] - min) * (bucketNum - 1) / differenceValue);
            bucketList.get(num).add(arr[i]);
        }

        // 每个桶内部排序
        for (int i = 0; i < bucketList.size(); i++) {
            Collections.sort(bucketList.get(i));
        }

        // 输出元素
        double[] sortArr = new double[arr.length];
        int index = 0;
        for (LinkedList<Double> list : bucketList) {
            for (Double element : list) {
                sortArr[index] = element;
                index++;
            }
        }
        return sortArr;
    }

    public static void main(String[] args) {
        double[] array = new double[]{4.12, 6.421, 0.0023, 3.0, 2.123, 8.122, 4.12, 10.09};
        double[] sortedArray = bucketSort(array);
        System.out.println(Arrays.toString(sortedArray));
    }
}
