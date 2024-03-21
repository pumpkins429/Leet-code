package com.lrh.leetcode;

import org.junit.jupiter.api.Test;

/*
 * @author      : pumpkins
 * @date        : 2024/3/19 0:16
 * @description : 双指针
 */
public class T27_RemoveElement {

    @Test
    public void test() {
        int[] nums = {0,1,2,2,3,0,4,2};
        int val = 2;

        int i = removeElement(nums, val);

        for (int j = 0; j < i; j++) {
            System.out.println(nums[j]);
        }
    }

    public int removeElement(int[] nums, int val) {

        int i = 0;
        for (int j = 0; j < nums.length; ++j) {
            if (nums[j] != val) {
                nums[i++] = nums[j];
            }
        }

        return i;
    }
}
