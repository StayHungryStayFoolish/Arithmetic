package ink.bonismo.notebook.arithmetic;

import java.util.Arrays;

/**
 * @Author: Created by bonismo@hotmail.com on 2019/7/18 5:33 PM
 * @Description:
 * @Version: 1.0
 */
public class DictionaryArithmetic {

    public static int[] findNearestNumber(int[] numbers) {
        int[] numbersCopy = Arrays.copyOf(numbers, numbers.length);
        //
        int point = findTransferPoint(numbers);
        if (0 == point) {
            return numbers;
        }
        exchangeHead(numbersCopy, point);
        reverse(numbersCopy, point);
        return numbersCopy;
    }

    /**
     * 从后向前查找数据逆序的边界，如果数组是 12354，返回5所在的下标索引
     *
     * @param numbers 12354
     * @return 3
     */
    private static int findTransferPoint(int[] numbers) {
        for (int i = numbers.length - 1; i > 0; i--) {
            if (numbers[i] > numbers[i - 1]) {
                return i;
            }
        }
        return 0;
    }

    /**
     * 将逆序区域的前一位和逆序区域刚刚大于它的数组交换位置 12354 --> 12453
     *
     * @param numbers 12354
     * @param point   3(5的下标索引)
     * @return 3和4互换位置
     */
    private static int[] exchangeHead(int[] numbers, int point) {
        int head = numbers[point - 1];
        for (int i = numbers.length - 1; i > 0; i--) {
            if (head < numbers[i]) {
                numbers[point - 1] = numbers[i];
                numbers[i] = head;
                break;
            }
        }
        return numbers;
    }

    /**
     * 将逆序区域转换为顺序
     *
     * @param numbers 12453
     * @param point   逆序的下标索引（5的下标索引）
     * @return 12435
     */
    private static int[] reverse(int[] numbers, int point) {
        for (int i = point, j = numbers.length - 1; i < j; i++, j--) {
            int temp = numbers[i];
            numbers[i] = numbers[j];
            numbers[j] = temp;
        }
        return numbers;
    }

    private static void output(int[] numbers) {
        for (int number : numbers) {
            System.out.print(number);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 5};
        for (int i = 0; i < 10; i++) {
            numbers = findNearestNumber(numbers);
            output(numbers);
        }
    }
}

/*
12354 12354
12435 12435
12453 12453
12534 12534
12543 12543
13452 13245
13524 13254
13542 13425
14352 13452
14523 13524
 */
