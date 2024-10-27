package com.lrh.leetcode;

import org.junit.jupiter.api.Test;

public class T443_StringCompression {
    @Test
    public void test() {
        // char[] chars = new char[]{'a','a','b','b','c','c','c'};
        // char[] chars = new char[]{'a'};
        // char[] chars = new char[]{'a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'};
        // char[] chars = new char[]{'a', 'a', 'a', 'b', 'b', 'a', 'a'};
        char[] chars = new char[]{'a', 'b', 'c'};
        int compress = compress(chars);
        for (int i = 0; i < compress; i++) {
            System.out.print(chars[i]);
        }
    }


    /**
     * 要求只使用常量额外空间的算法
     * 思路：双指针
     * TODO 待优化 写得太烂了！！！！！！！！！！
     * @param chars
     * @return
     */
    public int compress(char[] chars) {

        if (chars.length == 1) {
            return 1;
        }

        // 当前保存元素的有标
        int currentSave = 0;
        // 用于比较的第一个元素的游标
        int current = 0;
        // 记录元素的出现次数
        int cut = 0;
        // 保存数组的长度
        int len = 0;

        for (int i = 0; i < chars.length; i++) {
            // 双指针比较
            if (chars[i] == chars[current]) {
                cut++;
            } else if (chars[i] != chars[current]) {
                if (cut == 1) {
                    currentSave++;
                    chars[currentSave] = chars[i];
                    len++;
                    current = i;
                } else {
                    String cutStr = String.valueOf(cut);
                    for (int j = 0; j < cutStr.length(); j++) {
                        currentSave++;
                        chars[currentSave] = cutStr.charAt(j);
                        len++;
                    }
                    cut = 1;
                    len++;
                    current = i;
                    chars[++currentSave] = chars[current];
                }
            }
        }
        // 结尾特判：
        if (cut != 1) {
            String cutStr = String.valueOf(cut);
            for (int j = 0; j < cutStr.length(); j++) {
                currentSave++;
                chars[currentSave] = cutStr.charAt(j);
                len++;
            }
            cut = 1;
            len++;
        } else {
            len++;
        }

        return len;
    }


}
