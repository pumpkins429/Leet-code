package com.lrh.leetcode;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;

/*
 * @author      : pumpkins
 * @date        : 2024/3/23 0:33
 * @description : ...
 */
public class T389_RansomNote {

    @Test
    public void test() {

    }

    /**
     * 可以尝试用hashMap的api来实现
     * TODO 太慢，待优化
     * @param ransomNote
     * @param magazine
     * @return
     */
    public boolean canConstruct(String ransomNote, String magazine) {

        // 键为字符，值为字符的个数
        HashMap<Character, Integer> map = new HashMap<>();
        // magazine加入hashmap
        for (int i = 0; i < magazine.length(); i++) {
            Character c = magazine.charAt(i);
            Integer cut = map.putIfAbsent(c, 1);
            if (cut != null) {
                map.put(c, ++cut);
            }
        }

        // 取出magazine中的对比
        for (int i = 0; i < ransomNote.length(); i++) {
            char c = ransomNote.charAt(i);
            Integer cut = map.get(c);
            if (cut == null) {
                return false;
            } else if(cut == 1) {
                map.remove(c);
            } else {
                map.put(c, --cut);
            }
        }

        return true;
    }
}
