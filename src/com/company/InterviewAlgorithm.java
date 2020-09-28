package com.company;

/**
 * @author MaJin_Buu
 */
public class InterviewAlgorithm {
    public static void main(String[] args) {
        System.out.println(arrangeWords("My name is zhu liang shuang"));
    }

    /**
     * 「句子」是一个用空格分隔单词的字符串。给你一个满足下述格式的句子 text :
     * 句子的首字母大写
     * text 中的每个单词都用单个空格分隔。
     * 请你重新排列 text 中的单词，使所有单词按其长度的升序排列。如果两个单词的长度相同，则保留其在原句子中的相对顺序。
     * 请同样按上述格式返回新的句子。
     */
    // TODO myself
    public static String arrangeWords(String text) {
        // 先把首字母变成小写
        text = text.toLowerCase();
        String[] str = text.split(" ");
        int index = 0;
        while (index < str.length) {
            index++;
            for (int i = str.length - 1; i > 0; i--) {
                // 只要右边的元素长度大于左边的就进行移动
                if (str[i].length() < str[i - 1].length()) {
                    String temp = str[i - 1];
                    str[i - 1] = str[i];
                    str[i] = temp;
                }
            }
        }
        StringBuilder result = new StringBuilder();
        for (String strMin : str) {
            result.append(strMin).append(" ");
        }
        text = result.toString().trim();
        String head = text.substring(0, 1);
        head = head.toUpperCase();
        text = head + text.substring(1);
        return text;
    }
}
