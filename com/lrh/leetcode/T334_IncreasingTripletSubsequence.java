package com.lrh.leetcode;

import org.junit.jupiter.api.Test;

public class T334_IncreasingTripletSubsequence {
    @Test
    public void test() {
        System.out.println(increasingTriplet(new int[]{1, 2, 3, 4, 5}));
    }

    /**
     * 思路
     * 方法一：双向遍历，问题可以等价于存在leftMin[i - 1] < num[i] < rightMax[i + 1]，leftMin表示左边序列中的最小值，rightMax同理
     * <p>
     * 方法二：贪心策略：TODO
     *
     * @param nums
     * @return
     */
    public boolean increasingTriplet(int[] nums) {
        return v1(nums);
        // return v2(nums);
    }

    // 双向遍历
    public static boolean v1(int[] nums) {

        if (nums.length < 3) {
            return false;
        }

        // leftMin存入0~i的最小值，rightMax存入i~n-1的最大值
        int[] leftMin = new int[nums.length];
        int[] rightMax = new int[nums.length];
        leftMin[0] = nums[0];
        rightMax[nums.length - 1] = nums[nums.length - 1];

        for (int i = 1; i < nums.length; i++) {
            leftMin[i] = Math.min(nums[i], leftMin[i - 1]);
        }
        for (int i = nums.length - 2; i >= 0 ; i--) {
            rightMax[i] = Math.max(nums[i], rightMax[i + 1]);
        }

        // 判断是否存在leftMin[i - 1] < num[i] < rightMax[i + 1]
        for (int i = 1; i < nums.length - 1; i++) {
            if (nums[i] > leftMin[i - 1] && nums[i] < rightMax[i + 1]) {
                return true;
            }
        }

        return false;
    }

    /**
     * 贪心思想 TODO 思想好抽象。。还未理解
     * @param nums
     * @return
     */
    public static boolean v2(int[] nums) {


        return false;
    }
}
