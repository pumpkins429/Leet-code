package com.lrh.leetcode;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;

public class T345_ReverseVowelsOfAString {
    @Test
    public void test() {

    }

    /**
     * 思路:
     * 双指针
     *
     * @param s
     * @return
     */
    public String reverseVowels(String s) {
        StringBuilder ans = new StringBuilder(s);
        int i = 0, j = s.length() - 1;
        HashSet<Character> vowel = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));
        while (i < j) {
            while (i < j) {
                if (vowel.contains(s.charAt(i))) {
                    break;
                } else {
                    i++;
                }
            }
            while (j > i) {
                if (vowel.contains(s.charAt(j))) {
                    break;
                } else {
                    j--;
                }
            }
            ans.setCharAt(i, s.charAt(j));
            ans.setCharAt(j, s.charAt(i));

            i++;
            j--;
        }

        return ans.toString();
    }
}
