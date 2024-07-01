package com.lrh.leetcode;

import org.junit.jupiter.api.Test;

/*
 * @author      : pumpkins
 * @date        : 2024/3/21 23:31
 * @description :
 */
public class T80_RemoveDuplicatesFromSortedArrayII {

    public int removeDuplicates(int[] nums) {
        // 计数器，当j找到的元素出现次数超过2时就不允许i再填充这个元素
        int cut = 0;
        // 双指针，i用来填充，j用来查找满足条件的元素
        int i = 0;

        for (int j = 0; j < nums.length; ) {
            if (nums[i] == nums[j] && cut < 2) {
                if (i > 0 && nums[i - 1] != nums[i]) {
                    cut = 1;
                } else {
                    cut++;
                }
                i++;
                j++;
            } else if (nums[i] == nums[j] && cut >= 2) {
                if (nums[i - 1] != nums[i]) {
                    i++;
                    cut = 1;
                }
                j++;
            } else if (nums[i] != nums[j]) {
                nums[i] = nums[j];
                // 应该抹去中间的其他值 比如 0 0 1 1 1 1 1 2 2 2 4，
                // 得到中间状态 0 0 1 1 2 1 1 2 2 2 4时，i等于4，j等于7时，
                // 如果不将中间的1清除改为2，后面cut变化会错误
                for (int k = i + 1; k < j; k++) {
                    nums[k] = nums[i];
                }

                i++;
                j++;
                cut = 1;
            }
        }

        return i;
    }

    @Test
    public void test() {
        // System.out.println(removeDuplicates(new int[]{1, 1, 1, 2, 2, 3}));
        // System.out.println(removeDuplicates(new int[]{0, 0, 1, 1, 1, 1, 2, 3, 3}));
        // System.out.println(removeDuplicates(new int[]{1, 2, 2}));
        System.out.println(removeDuplicates(new int[]{0,0,1,1,1,1,2,2,2,4}));// !!

    }

}
