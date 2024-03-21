package com.lrh.leetcode;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/*
 * @author      : pumpkins
 * @date        : 2024/3/21 23:42
 * @description : 数组
 * TODO 进阶：尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。
 */
public class T169_MajorityElement {

    @Test
    public void test() {


    }


    /**
     * 笨方法：先排序，然后检查第1个元素和第n/2个元素如果两个位置上的元素相同就返回这位置上的元素
     * 如果还不同就检查第i个元素和第n/2+i个元素，i不大于n/2
     * TODO 太慢了，待优化
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {

        Arrays.sort(nums);

        int ans = 0;
        for (int i = 0, j = nums.length / 2;j < nums.length && i <= nums.length / 2; ++i, ++j) {
            if (nums[i] == nums[j]) {
                ans = nums[i];
                break;
            }
        }

        return ans;
    }
}
