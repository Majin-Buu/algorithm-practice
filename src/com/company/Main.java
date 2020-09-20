package com.company;

import java.util.Arrays;

/**
 * @author MaJin_Buu
 */
public class Main {

    public static void main(String[] args) {
        // 第一题(删除排序数组中的重复项)
        System.out.println(arrayDeduplication(new int[]{1, 2}));
        // 第二题(冒泡排序)
        System.out.println(Arrays.toString(bubbleSort(new int[]{1, 2, 3, 1, 4, 7})));
        // 第三题(买卖股票的最佳时机 II)
        // 暴力法
        System.out.println(stockAlgorithm(new int[]{5, 4, 5, 6, 1}));
        // 峰值法
        System.out.println(valleyPeak(new int[]{5, 4, 5, 6, 1}));
        // 简单一次遍历
        System.out.println(loopOnly(new int[]{5, 4, 5, 6, 1}));
    }

    /**
     * 删除排序数组中的重复项
     * 给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
     * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
     * 示例1
     * 给定数组 nums = [1,1,2],
     * 函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
     * 你不需要考虑数组中超出新长度后面的元素。
     *
     * @return 数组长度
     */
    public static int arrayDeduplication(int[] nums) {
        int key = 2;
        if (nums.length < key) {
            return 0;
        }
        // 解题思路 使用双指针的方式进行统计去重后的数组长度
        // 慢指针
        int i = 0;
        // 快指针
        for (int j = 1; j < nums.length; j++) {
            if (nums[i] != nums[j]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }

    /**
     * 冒泡排序
     * 给定一个数组进行排序
     */
    public static int[] bubbleSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[i]) {
                    array[i] = array[i] ^ array[j];
                    array[j] = array[i] ^ array[j];
                    array[i] = array[i] ^ array[j];
                }
            }
        }
        return array;
    }

    /**
     * 暴力法
     * <p>
     * 买卖股票的最佳时机 II
     * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
     * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     * 示例 1:
     * 输入: [7,1,5,3,6,4]
     * 输出: 7
     * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     *      随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
     * 示例 2:
     * 输入: [1,2,3,4,5]
     * 输出: 4
     * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     *      注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
     *      因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
     * 示例 3:
     * 输入: [7,6,4,3,1]
     * 输出: 0
     * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
     */
    public static int stockAlgorithm(int[] array) {
        return stockAlgorithm(array, 0);
    }

    public static int stockAlgorithm(int[] array, int s) {
        // 解题思路 两层循环只要后面比前面大就可以进行买卖
        // 声明一个最终返回值
        int max = 0;
        if (array.length < 2) {
            return max;
        }
        // 进行循环重第一个到最后一个哪个会产生利润
        for (int i = s; i < array.length; i++) {
            // 他是用来接从i天到最后一共产生的利润用来决定具体是从那天产生的利润最高
            int maxProfit = 0;
            for (int j = i + 1; j < array.length; j++) {
                // 如果发现从第i天跟第j天可以产生利润那就停止，再看第j + 1天往后能不能产生利润
                if (array[i] < array[j]) {
                    // 当前利润
                    int profit = array[j] - array[i];
                    // 计算后面能不能再产生利润
                    int afterProfit = stockAlgorithm(array, j + 1);
                    // 累计利润
                    profit += afterProfit;
                    // 如果这轮循环的利润比上一轮大就进行交换
                    if (profit > maxProfit) {
                        maxProfit = profit;
                    }
                }
            }
            if (maxProfit > max) {
                max = maxProfit;
            }
        }

        return max;
    }

    /**
     * 峰值法
     */
    public static int valleyPeak(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }
        // 声明一个返回参数
        int max = 0;
        // 谷值
        int low = 0;
        // 峰值
        int peak = 0;
        // 指针
        int i = 0;
        // 一次遍历
        while (i < prices.length - 1) {
            // 看第一个和第二个形成的是什么峰值还是谷值
            while (i < prices.length - 1) {
                if (prices[i] <= prices[i + 1]) {
                    // 这样做是为了把后面赋值给峰值或者谷值，而且后面再循环的时候就是从第二个开始循环方便理解就是1，2，1，2会形成一个峰值和一个谷值
                    i++;
                    // 前面小后面大就把大的赋值给峰值
                    peak = prices[i];
                }
                if (prices[i] >= prices[i + 1]) {
                    // 这样做是为了把后面赋值给峰值或者谷值，而且后面再循环的时候就是从第二个开始循环方便理解就是1，2，1，2会形成一个峰值和一个谷值
                    i++;
                    // 前面小后面大就把大的赋值给峰值
                    low = prices[i];
                }
                max += peak - low;
            }
        }

        return max;
    }

    /**
     * 简单的一次循环，通过谷峰法可以看出来我们只取峰值就可以了
     */
    public static int loopOnly(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }
        // 声明一个返回参数
        int max = 0;
        // 一次遍历
        for (int i = 1; i < prices.length; i++) {
            if (prices[i - 1] < prices[i]) {
                max += prices[i] - prices[i - 1];
            }
        }
        return max;
    }

}
