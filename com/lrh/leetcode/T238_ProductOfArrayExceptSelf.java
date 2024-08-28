package com.lrh.leetcode;

import org.junit.jupiter.api.Test;

public class T238_ProductOfArrayExceptSelf {
    @Test
    public void test() {

    }

    /**
     * 思路：
     * 方法1：前缀和思想，求出整个数组的乘积，再分别除以每一位就可以得到每一位上的答案
     * 方法2：不使用除法 TODO
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
        // 上三角和下三角
        int[] ans = new int[nums.length];
        ans[0] = 1;
        // 先计算下三角
        for (int i = 1; i < nums.length; i++) {
            ans[i] = ans[i - 1] * nums[i - 1];
        }

        int tmp = 1;
        // 计算上三角
        for (int i = nums.length - 2; i >= 0; i--) {
            tmp *= nums[i + 1];
            ans[i] *= tmp;
        }

        return ans;
    }

    /* public int[] productExceptSelf(int[] nums) {
        // 求出所有乘积
        int sum = 1;
        // 标记0的个数
        int zeroCut = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                sum *= nums[i];
            } else {
                zeroCut++;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0 && zeroCut == 1) {
                nums[i] = sum;
            } else if (nums[i] == 0 && zeroCut > 1) {
                nums[i] = 0;
            } else if (nums[i] != 0 && zeroCut != 0) {
                nums[i] = 0;
            } else {
                nums[i] = sum / nums[i];
            }
        }

        return nums;
    } */
}
