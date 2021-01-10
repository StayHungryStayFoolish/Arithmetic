package ink.bonismo.notebook.arithmetic.sort;

import java.util.Arrays;

/**
 * @author Created by bonismo@hotmail.com on 2019/7/11 11:29 AM
 * @Description:
 * @Version: 1.0
 * @link https://mp.weixin.qq.com/s?__biz=MzIxMjE5MTE1Nw==&mid=2653195533&idx=1&sn=02918dc51b07837ce1119f00d7900dbc&chksm=8c99ffd7bbee76c1d2e2e9b198259795285ec2c305d3613a5e39622195fd1c32bb6dbe52fa08&scene=21#wechat_redirect
 */
public class CountingSortSimple {

    public static int[] countSort(int[] array) {
        //1.得到数列的最大值
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }

        //2.根据数列最大值确定统计数组的长度
        int[] countArray = new int[max + 1];
        //3.遍历数列，填充统计数组
        for (int i = 0; i < array.length; i++) {
            countArray[array[i]]++;
        }
        System.out.println("----" + Arrays.toString(countArray));
        //4.遍历统计数组，输出结果
        int index = 0;
        int[] sortedArray = new int[array.length];
        for (int i = 0; i < countArray.length; i++) {
            System.out.println("--- i --- "+i);
            for (int j = 0; j < countArray[i]; j++) {
                sortedArray[index++] = i;
                System.out.println("--- sort --- "+i);
            }
        }
        return sortedArray;
    }

    public static void main(String[] args) {
        int[] array = new int[]{4, 4, 6, 5, 3, 2, 8, 1, 7, 5, 6, 0, 10};
        int[] sortedArray = countSort(array);
        System.out.println("======"+Arrays.toString(sortedArray));
        int[] ar = new int[]{95, 94, 91, 98, 99, 90, 99, 93, 91, 92};

        int[] ar1 = new int[]{90, 99, 95, 94, 95};

        int[] sortedArray1 = countSort(ar);
        System.out.println("======"+Arrays.toString(sortedArray1));
    }
}
