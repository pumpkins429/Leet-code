package com.lrh.leetcode;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class T1431_KidsWithTheGreatestNumberOfCandies {
    @Test
    public void Test() {

    }

    /**
     * 思路：
     * maxCandy记录最多的糖果
     * 比较candies[i]+extraCandies与maxCandy的大小
     *
     * @param candies
     * @param extraCandies
     * @return
     */
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {

        int maxCandy = 0;
        for (int i = 0; i < candies.length; i++) {
            maxCandy = Math.max(maxCandy, candies[i]);
        }

        List<Boolean> ans = new ArrayList<>(candies.length);
        for (int i = 0; i < candies.length; i++) {
            if (candies[i] + extraCandies >= maxCandy) {
                ans.add(true);
            } else {
                ans.add(false);
            }
        }


        return ans;
    }


}
