package com.lrh.leetcode;

import org.junit.jupiter.api.Test;

public class T1137_NThTribonacciNumber {
    @Test
    public void test() {
        System.out.println(tribonacci(25));
    }

    /**
     * 思路：记忆化搜索？
     *
     * @param n
     * @return
     */
    static int[] f = new int[38];

    static {
        f[0] = 0;
        f[1] = 1;
        f[2] = 1;
    }

    public int tribonacci(int n) {

        if (n <= 2) {
            return f[n];
        }

        for (int i = 3; i <= n; i++) {
            f[i] = f[i - 1] + f[i - 2] + f[i - 3];
        }

        return f[n];
    }
}
