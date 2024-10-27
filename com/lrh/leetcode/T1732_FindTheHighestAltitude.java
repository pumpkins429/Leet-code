package com.lrh.leetcode;

import org.junit.jupiter.api.Test;
public class T1732_FindTheHighestAltitude {
    @Test
    public void test() {
        System.out.println(largestAltitude(new int[]{-4, -3, -2, -1, 4, 3, 2}));
    }

    /**
     * 思路：前缀和
     *
     * @param gain
     * @return
     */
    public int largestAltitude(int[] gain) {

        int max = 0;
        int tmp = 0;
        for (int i = 0; i < gain.length; i++) {
            tmp += gain[i];
            max = Math.max(max, tmp);
        }

        return max;
    }
}
