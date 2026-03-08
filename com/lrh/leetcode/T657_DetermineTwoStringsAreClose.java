package com.lrh.leetcode;

import org.junit.Test;
import org.junit.platform.commons.util.StringUtils;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 前提：字符串长度相同
 * 操作一等价于：aba baa 只要两个字符串排序后相同，则可以互相转换
 * 操作二等价于：bba aab 只要两个字符串的字符串的种类相同且出现的频率组成的数字也相同
 * FIXME 待优化，写得太垃了。。
 */
public class T657_DetermineTwoStringsAreClose {

    public boolean closeStrings(String word1, String word2) {
        if (word2.length() != word1.length()) {
            return false;
        }

        char[] w1 = word1.toCharArray();
        char[] w2 = word2.toCharArray();
        if (Arrays.equals(w1, w2)) {
            return true;
        }

        HashMap<Character, Integer> h1 = new HashMap<>();
        HashMap<Character, Integer> h2 = new HashMap<>();
        for (int i = 0; i < word1.length(); i++) {
            h1.compute(word1.charAt(i), (k, v) -> v == null ? 1 : v + 1);
            h2.compute(word2.charAt(i), (k, v) -> v == null ? 1 : v + 1);
        }
        if (!h1.keySet().equals(h2.keySet())) {
            return false;
        }
        return Arrays.equals(h1.values().stream().sorted().toArray(), h2.values().stream().sorted().toArray());
    }

    @Test
    public void test() {
        System.out.println(closeStrings("cabbba", "abbccc"));
        System.out.println(closeStrings("a", "aa"));
        System.out.println(closeStrings("abc", "bca"));
        System.out.println(closeStrings("abbzzca", "babzzcz"));
    }
}
