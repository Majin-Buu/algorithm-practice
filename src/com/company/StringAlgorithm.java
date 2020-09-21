package com.company;

/**
 * @author MaJin_Buu
 */
public class StringAlgorithm {
    public static void main(String[] args) {
        // 反转字符数组
        char[] str = new char[]{'s', 'b', 'g', 't'};
        reverseString(str);
        System.out.println(str);
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
