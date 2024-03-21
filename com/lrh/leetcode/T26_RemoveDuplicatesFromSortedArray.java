package com.lrh.leetcode;

import org.junit.jupiter.api.Test;

/*
 * @author      : pumpkins
 * @date        : 2024/3/19 0:37
 * @description : 双指针  使用快慢指针
 */
public class T26_RemoveDuplicatesFromSortedArray {

    @Test
    public void test() {

    }

    public int removeDuplicates(int[] nums) {

        int i = 0, j = 1;

        for (i = 0; j < nums.length; ++j) {
            if (nums[j] != nums[i]) {
                nums[++i] = nums[j];
            }
        }

        return i + 1;
    }
}
