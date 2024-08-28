package com.lrh.leetcode;

import org.junit.jupiter.api.Test;
public class T151_ReverseWordsInAString {
    @Test
    public void test() {
        
    }

    /**
     * 思路：
     * 方法1.使用split方法，利用正则表达式分隔
     * 方法2.
     * @param s
     * @return
     */
    public String reverseWords(String s) {

        // 用正则表达式
        String[] split = s.split(" +");

        StringBuilder ans = new StringBuilder();

        for (int i = split.length - 1; i >= 0; i--) {
            ans.append(split[i]).append(" ");
        }

        return ans.toString().trim();
    }

    public String revertseWords(String s) {

        // TODO 双指针


        return null;
    }



}
