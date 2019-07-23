package com.spring.demo;

/**
 * @Author: Created by bonismo@hotmail.com on 2019/7/23 2:57 PM
 * @Description:
 * @Version: 1.0
 * @Link: https://mp.weixin.qq.com/s?__biz=MzIxMjE5MTE1Nw==&mid=2653196069&idx=1&sn=55f0eec8664e82151ed2a6cb371179f8&chksm=8c99fdffbbee74e9d3884026da3756b79db20302b49a69c8c033173d50ee2afded6258f55a4e&scene=21#wechat_redirect
 */
public class BigNumberSum {

    public static String bigNumSum(String numA, String numB) {
        int aLength = numA.length();
        int bLength = numB.length();
        int maxLength = aLength > bLength ? aLength : bLength;

        // 将数组倒序存储到数组对应索引  12345 -> [5,4,3,2,1]
        // 构建倒序数组 A
        int[] arrayA = new int[maxLength + 1];
        for (int i = 0; i < aLength; i++) {
            arrayA[i] = numA.charAt(aLength - 1 - i) - '0';
        }

        // 构建倒序数组 B
        int[] arrayB = new int[maxLength + 1];
        for (int i = 0; i < bLength; i++) {
            arrayB[i] = numB.charAt(bLength - 1 - i) - '0';
        }

        int[] result = new int[maxLength + 1];
        for (int i = 0; i < result.length; i++) {
            // 数组 A 和 数组 B 相同索引上的值相加，大于等于10进位1
            int temp = result[i];
            temp += arrayA[i];
            temp += arrayB[i];

            if (temp >= 10) {
                temp = temp - 10;
                result[i + 1] = 1;
            }
            result[i] = temp;
        }

        StringBuffer buffer = new StringBuffer();
        boolean firstNum = false;
        for (int i = result.length - 1; i >= 0; i--) {
            if (!firstNum) {
                if (0 == result[i]) {
                    continue;
                }
                firstNum = true;
            }
            buffer.append(result[i]);
        }
        return buffer.toString();
    }

    public static void main(String[] args) {
        System.out.println(bigNumSum("426709752318", "95481253129"));
    }
}
