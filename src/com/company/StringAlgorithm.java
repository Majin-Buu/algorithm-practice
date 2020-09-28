package com.company;

import java.util.Arrays;

/**
 * @author MaJin_Buu
 */
public class StringAlgorithm {
    public static void main(String[] args) {
        // 反转字符数组
        char[] str = new char[]{'s', 'b', 'g', 't'};
        reverseString(str);
        System.out.println(str);

        String[] str1 = {"J", "a", "v", "a", "中"};
        String[] str2 = {"如", "何", "把", "两", "个", "数", "组", "合", "并", "为", "一", "个"};
        int strLen1 = str1.length;//保存第一个数组长度
        int strLen2 = str2.length;//保存第二个数组长度
        str1 = Arrays.copyOf(str1, strLen1 + strLen2);//扩容
        System.arraycopy(str2, 0, str1, strLen1, strLen2);//将第二个数组与第一个数组合并
        System.out.println(Arrays.toString(str1));//输出数组

        int[] digits = {1, 2, 3};
        int[] digitMin = {1, 2, 3, 4};

        digitMin = Arrays.copyOf(digitMin, digits.length + digitMin.length);
        System.arraycopy(digits, 0, digitMin, digitMin.length, digits.length);

    }

    public static void reverseString(char[] s) {
        if (s.length < 2) {
            return;
        }
        int n = s.length / 2;
        for (int i = 0; i < n; i++) {
            char str = s[i];
            s[i] = s[s.length - 1 - i];
            s[s.length - 1 - i] = str;
        }
    }
}
