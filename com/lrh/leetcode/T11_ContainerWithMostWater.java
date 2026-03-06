package com.lrh.leetcode;

import org.junit.Test;

/**
 * 双指针：
 * 初试时左指针指向最左端，右指针指向最右，往中间靠，每次移动指向最矮线条的指针
 *
 */
public class T11_ContainerWithMostWater {
    // 果然超时了
    public int maxArea_timeout(int[] height) {
        int pre;
        int nxt;

        int max = 0;

        for (int i = 0; i < height.length; i++) {
            pre = i;
            for (int j = i + 1; j < height.length; j++) {
                nxt = j;
                max = Integer.max(max, (nxt - pre) * Integer.min(height[pre], height[nxt]));
            }
        }

        return max;
    }


    public int maxArea(int[] height) {
        int max = 0;
        for (int i = 0, j = height.length - 1; i < j; ) {
            max = Integer.max(max, Integer.min(height[i], height[j]) * (j - i));
            if (height[i] >= height[j]) {
                j--;
            } else {
                i++;
            }
        }

        return max;
    }

    @Test
    public void test() {
        int[] height = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(maxArea_timeout(height));
        System.out.println(maxArea(height));
    }
}
