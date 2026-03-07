package com.lrh.leetcode;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;


public class T679_MaxNumOfKSumPairs {
    public int maxOperations(int[] nums, int k) {
        int cut = 0;

        Arrays.sort(nums);
        int i = 0;
        int j = nums.length - 1;
        while (i < j) {
            if (nums[i] + nums[j] == k) {
                cut++;
                i++;
                j--;
            } else if (nums[i] + nums[j] > k) {
                j--;
            } else if (nums[i] + nums[j] < k) {
                i++;
            }
        }

        return cut;
    }


    @Test
    public void test() {
        System.out.println(maxOperations(new int[]{1, 2, 3, 4}, 5));
        System.out.println(maxOperations(new int[]{3,1,3,4,3}, 6)); // 1 3 3 3 4
    }
}
