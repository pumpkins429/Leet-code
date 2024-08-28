package com.lrh.leetcode;

import org.junit.jupiter.api.Test;
public class T605_CanPlaceFlowers {
    @Test
    public void test() {
        
    }

    /**
     * 思路：
     * 贪心策略
     * @param flowerbed
     * @param n
     * @return
     */
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        // 特判
        if (flowerbed.length == 1 && flowerbed[0] == 0) {
            return true;
        }

        // 头特判
        if (flowerbed[0] == 0 && flowerbed.length > 1 && flowerbed[1] == 0) {
            flowerbed[0] = 1;
            n--;
        }

        for (int i = 1; i < flowerbed.length - 1; i++) {
            if (flowerbed[i - 1] == 0 && flowerbed[i] == 0 && flowerbed[i + 1] == 0) {
                flowerbed[i] = 1;
                n--;
                i++;
            }
        }

        // 尾特判
        if (flowerbed[flowerbed.length - 1] == 0 && flowerbed.length  > 1 && flowerbed[flowerbed.length - 2] == 0) {
            flowerbed[flowerbed.length - 1] = 1;
            n--;
        }

        return n <= 0;
    }
}
