package ink.bonismo.notebook.arithmetic.sort;

import java.util.Arrays;

/**
 * @author Created by bonismo@hotmail.com on 2019/7/11 6:32 PM
 * @Description: 1.数列最小、最大值差过大时，不适用计数排序
 * 2.元素非整数，不适用计数排序
 * @Version: 1.0
 * @link https://mp.weixin.qq.com/s/bIQnEL2eIehQtpTKrkc7uw
 * 计数算法：
 * 1.获取最大，最小值，通过差建立 +1 数组。
 * 2.计算每个值对应索引出现的次数。（如果是不稳定计数排序，则每个值根据该索引上的出现的次数输入值，不保证相同的值顺序性） 注：将初始数组的值做为索引存储，次数数组该索引的值作为重复次数
 * 3.数组变形，记录最终最大的值位于的最终位置。（最大值的索引是最终位置的值 -1 ） 注：变形数组的索引 = 初始数组的值，变形数组的值 -1 = 结果数组的索引
 * 4.倒序遍历初始数组，将变形数组索引所在的值 -1 作为结果数组的索引，将初始数组的值插入结果数组的索引。
 * <p>
 * 次数数组：将初始数组的值作为索引，初始数组的值出现次数作为次数数组的值                          次数数组的索引是初始数组的值，次数数组的值是初始数组值的重复次数
 * 变形数组：通过次数数组的最终索引是初始数组的最大值的特点，计算出结果数组的最大索引值（需要 -1 ）    变形数组的索引是初始数组的值，变形数组的值 -1 是初始数组值排序后的索引
 * 结果数组：倒序遍历，将变形数组的索引上的值 -1 作为新数组的索引，将当前初始数组遍历值赋予该索引
 * <p>
 * 本质：将初始数组的值通过 max - min 建立次数数组，然后统计次数数组的索引次数为变形数组，建立新数组，倒序遍历初始数组，根据初始数组值对应在变形数组上的索引的值 -1 确定该值的正确索引
 */
public class CountingSortWithStandard {


    public static int[] countSort(int[] array) {
        //1.得到数列的最大值和最小值，并算出差值d
        int max = array[0];
        int min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
            if (array[i] < min) {
                min = array[i];
            }
        }
        System.out.println(" Max : " + max + "\n" + " Min : " + min);
        int d = max - min;
        //2.创建统计数组并统计对应元素个数（例：0 ~ 10 范围为11个数值，所以 max - min + 1）
        int[] countArray = new int[d + 1];
        for (int i = 0; i < array.length; i++) {
            countArray[array[i] - min]++;
        }
        System.out.println("Array                 : " + Arrays.toString(array));
        System.out.println("Calculate Count Array : " + Arrays.toString(countArray));
        //3.统计数组做变形，后面的元素等于前面的元素之和
        // 目的就是为了计算元素排序后的最终位置（准确来说是最大的最终位置）
        int sum = 0;
        for (int i = 0; i < countArray.length; i++) {
            sum += countArray[i];
            countArray[i] = sum;
        }
        System.out.println("Transfiguration Array : " + Arrays.toString(countArray));
        //4.倒序遍历原始数列，从统计数组找到正确位置，输出到结果数组
        int[] sortedArray = new int[array.length];
        for (int i = array.length - 1; i >= 0; i--) {
            // array[i] - min 计算出该值在变形（当前索引值=所有前索引之和）数组的索引
            // countArray[ array[i] - min ] - 1 ,计算出变形数组在新建排序数组的应当索引（实际 -1 即可，然后再递减，下次遇见一样的，直接放入同样值前边，因为是倒序遍历）
            System.out.println("Result Value :" + array[i]);
            System.out.println("Result Index : " + (countArray[array[i] - min] - 1));
            // 根据当前新数组索引将正在遍历的值赋值到应当索引
            // sortArray[countArray[ array[i] - min ] - 1]
            sortedArray[countArray[array[i] - min] - 1] = array[i];
            countArray[array[i] - min]--;
            System.out.println("Count Array : " + Arrays.toString(countArray));
            System.out.println("Sort Array : " + Arrays.toString(sortedArray));
        }
        return sortedArray;
    }

    public static void main(String[] args) {
        int[] array = new int[]{96, 94, 94, 98, 99, 98, 99, 96, 96, 97};
        int[] sortedArray = countSort(array);
        System.out.println(Arrays.toString(sortedArray));
    }

}
