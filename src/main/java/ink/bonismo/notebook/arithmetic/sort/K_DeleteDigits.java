package ink.bonismo.notebook.arithmetic.sort;

import java.util.Arrays;

/**
 * @Author: Created by bonismo@hotmail.com on 2019/7/23 5:27 PM
 * @Description: 删除 K 个数字获取最小值
 * @Version: 1.0
 */
public class K_DeleteDigits {

    public static String removeKDigits(String num, int k) {
        int newLen = num.length() - k;
        // 当删除数字个数 > 数字长度直接返回
        if (newLen < 0) {
            return null;
        }

        char[] stack = new char[num.length()];

        int top = 0;
        for (int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);
            // 当栈顶数字大于当前遍历的数字，栈顶数字出栈

            // 当栈顶数字大于当前遍历的数字时，从栈顶递减循环栈内元素跟当前元素比较，
            // 当栈内元素 X 不大于当前遍历元素时，记录该栈内元素索引，
            // 然后将当前元素复制给 X 索引后一位，即 stack[++] = c
            while (top > 0 && stack[top - 1] > c && k > 0) {
                // Arrays 两行可以省略，此处主要为了明确显示出栈过程
                 stack = Arrays.copyOfRange(stack, 0, top - 1);
                 stack = Arrays.copyOf(stack, num.length());
                top -= 1;
                k -= 1;
            }
            stack[top++] = c;
        }

        // 找到栈中第一个非零数字的位置，以此构建新的整数字符串
        int offset = 0;
        while (offset < newLen && stack[offset] == '0') {
            offset++;
        }
        System.out.println("Stack : " + Arrays.toString(stack));
        System.out.println("String : " + new String(stack, offset, newLen - offset));
        return offset == newLen ? "0" : new String(stack, offset, newLen - offset);
    }

    public static void main(String[] args) {
        System.out.println("Delete K Count Digits : " + removeKDigits("19534762", 5));
        System.out.println("Delete K Count Digits : " + removeKDigits("30200", 1));
        System.out.println("Delete K Count Digits : " + removeKDigits("11", 3));
        System.out.println("Delete K Count Digits : " + removeKDigits("541270936", 3));
    }
}
