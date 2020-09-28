package com.company;

import java.util.*;

/**
 * @author MaJin_Buu
 */
public class ArrayAlgorithm {

    public static void main(String[] args) {
        int[] ints = new int[]{1, 2, 3, 4, 5};
        // 第一题(删除排序数组中的重复项)
        System.out.println("删除排序数组中的重复项: " + arrayDeduplication(ints));
        System.out.println("删除排序数组中的重复项: " + Arrays.toString(ints));
        // 第二题(冒泡排序)
        System.out.println(Arrays.toString(bubbleSort(new int[]{1, 2, 3, 1, 4, 7})));
        // 第三题(买卖股票的最佳时机 II)
        // 暴力法
        System.out.println(stockAlgorithm(new int[]{5, 4, 5, 6, 1}));
        // 峰值法
        System.out.println(valleyPeak(new int[]{7, 1, 5, 3, 6, 4}));
        // 简单一次遍历
        System.out.println(loopOnly(new int[]{5, 4, 5, 6, 1}));
        // 旋转数组(暴力遍历)
        ints = new int[]{1, 2, 3, 4, 5};
        spinArray(ints, 3);
        System.out.println(Arrays.toString(ints));
        // 旋转数组(开辟新空间)
        ints = new int[]{1, 2, 3, 4, 5};
        openArray(ints, 3);
        System.out.println("旋转数组(开辟新空间): " + Arrays.toString(ints));

        int[] i = new int[]{1, 2};
        hand(i);
        System.out.println(Arrays.toString(i));
        // 两个数组的交集
        System.out.println(Arrays.toString(intersect(new int[]{1, 2, 3}, new int[]{1, 2, 3})));
        // 数组末尾加一，并且将位数大的往前放
        System.out.println(Arrays.toString(plusOne(new int[]{9, 9, 9})));
        int[] moveZeroes = {1, 2, 3, 0, 5};
        moveZeroesSec(moveZeroes);
        System.out.println(Arrays.toString(moveZeroes));
        System.out.println(Arrays.toString(twoSum(new int[]{1, 2, 3, 4, 5, 6, 7, 9}, 10)));

    }

    private static void hand(int[] i) {
        i[1] = 3;
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
        int low;
        // 峰值
        int peak;
        // 指针
        int i = 0;
        // 一次遍历
        while (i < prices.length - 1) {
            // 看第一个和第二个形成的是什么峰值还是谷值
            while (i < prices.length - 1 && prices[i] >= prices[i + 1]) {
                // 这样做是为了把后面赋值给峰值或者谷值，而且后面再循环的时候就是从第二个开始循环方便理解就是1，2，1，2会形成一个峰值和一个谷值
                i++;
            }
            low = prices[i];
            while (i < prices.length - 1 && prices[i] <= prices[i + 1]) {
                // 这样做是为了把后面赋值给峰值或者谷值，而且后面再循环的时候就是从第二个开始循环方便理解就是1，2，1，2会形成一个峰值和一个谷值
                i++;
            }
            peak = prices[i];
            max += peak - low;
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

    /**
     * 旋转数组
     * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
     * 示例 1:
     * 输入: [1,2,3,4,5,6,7] 和 k = 3
     * 输出: [5,6,7,1,2,3,4]
     * 解释:
     * 向右旋转 1 步: [7,1,2,3,4,5,6]
     * 向右旋转 2 步: [6,7,1,2,3,4,5]
     * 向右旋转 3 步: [5,6,7,1,2,3,4]
     * 示例 2:
     * 输入: [-1,-100,3,99] 和 k = 2
     * 输出: [3,99,-1,-100]
     * 解释:
     * 向右旋转 1 步: [99,-1,-100,3]
     * 向右旋转 2 步: [3,99,-1,-100]
     */
    public static void spinArray(int[] nums, int k) {
        if (nums.length == 0) {
            return;
        }
        int num, middle;
        for (int i = 0; i < k; i++) {
            middle = nums[nums.length - 1];
            for (int j = 0; j < nums.length; j++) {
                num = nums[j];
                nums[j] = middle;
                middle = num;
            }
        }

    }

    /**
     * 开辟新空间
     */
    public static void openArray(int[] nums, int k) {
        if (nums.length == 0) {
            return;
        }
        int[] open = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            open[(i + k) % nums.length] = nums[i];
        }
        for (int i = 0; i < open.length; i++) {
            nums[i] = open[i];
        }


    }

    /**
     * 两个数组的交集 II（哈希表解决）
     * 给定两个数组，编写一个函数来计算它们的交集。
     * 示例 1：
     * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
     * 输出：[2,2]
     * 示例 2:
     * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
     * 输出：[4,9]
     */
    public static int[] intersect(int[] nums1, int[] nums2) {
        // 创建哈希表
        Map<Integer, Integer> map = new HashMap<>(16);
        List<Integer> list = new ArrayList<>();
        // 判断谁的长度短就把谁放到哈希表中，以减少内存消耗
        if (nums1.length > nums2.length) {
            for (int i : nums2) {
                int count = map.getOrDefault(i, 0) + 1;
                map.put(i, count);
            }
            // 遍历另一个数组
            for (int value : nums1) {
                if (map.containsKey(value)) {
                    if (map.get(value) == 0) {
                        continue;
                    }
                    list.add(value);
                    Integer integer = map.get(value);
                    map.put(value, integer - 1);
                }
            }
        } else {
            for (int i : nums1) {
                int count = map.getOrDefault(i, 0) + 1;
                map.put(i, count);
            }
            // 遍历另一个数组
            for (int value : nums2) {
                if (map.containsKey(value)) {
                    if (map.get(value) == 0) {
                        continue;
                    }
                    list.add(value);
                    Integer integer = map.get(value);
                    map.put(value, integer - 1);
                }
            }
        }

        return list.stream().mapToInt(Integer::valueOf).toArray();
    }

    /**
     * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
     * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
     * 你可以假设除了整数 0 之外，这个整数不会以零开头。
     * 示例 1:
     * 输入: [1,2,3]
     * 输出: [1,2,4]
     * 解释: 输入数组表示数字 123。
     * 示例 2:
     * 输入: [4,3,2,1]
     * 输出: [4,3,2,2]
     * 解释: 输入数组表示数字 4321。
     * 这个算法是真牛逼
     */
    public static int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i]++;
            digits[i] = digits[i] % 10;
            if (digits[i] != 0) {
                return digits;
            }
        }
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }

    /**
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     * 示例:
     * 输入: [0,1,0,3,12]
     * 输出: [1,3,12,0,0]
     * 说明:
     * 必须在原数组上操作，不能拷贝额外的数组。
     * 尽量减少操作次数。
     */
    // TODO 纪念一下自己解出来的 虽然写法很垃圾
    public static void moveZeroes(int[] nums) {
        // 思路  先循环遇到0就把0往后的元素往前移动
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            count++;
            if (nums[i] == 0) {
                int num, middle;
                middle = nums[i];
                for (int j = nums.length - 1; j >= i; j--) {
                    num = nums[j];
                    nums[j] = middle;
                    middle = num;
                }
                i--;
            }
            if (count == nums.length) {
                return;
            }
        }
    }

    /**
     * @param nums 知识点j++ 和 ++j 的区别（回来想着看）
     */
    public static void moveZeroesSec(int[] nums) {
        if (nums == null) {
            return;
        }
        //第一次遍历的时候，j指针记录非0的个数，只要是非0的统统都赋给nums[j]
        int j = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] != 0) {
                nums[j++] = nums[i];
            }
        }
        //非0元素统计完了，剩下的都是0了
        //所以第二次遍历把末尾的元素都赋为0即可
        for (int i = j; i < nums.length; ++i) {
            nums[i] = 0;
        }
    }

    /**
     * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
     * 示例:
     * 给定 nums = [2, 7, 11, 15], target = 9
     * 因为 nums[0] + nums[1] = 2 + 7 = 9
     * 所以返回 [0, 1]
     */
    // TODO myself
    public static int[] twoSum(int[] nums, int target) {
        // 返回值
        Set<Integer> set = new HashSet<>();
        // 双指针
        // 慢指针
        int index = 0;
        while (index < nums.length) {
            // 快指针
            for (int i = index + 1; i < nums.length; i++) {
                if (nums[index] + nums[i] == target) {
                    if (set.contains(index) || set.contains(i)) {
                        continue;
                    }
                    set.add(index);
                    set.add(i);
                    index++;
                    i = index;
                }
            }
            index++;
        }
        return set.stream().mapToInt(Integer::valueOf).toArray();
    }

}
