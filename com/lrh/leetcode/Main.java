package com.lrh.leetcode;

/*
 * @author      : pumpkins
 * @date        : 2024/3/23 13:12
 * @description : ...
 */
public class Main {
    public static void main(String[] args) {
        String url = "https://leetcode.cn/problems/simplify-path/description/?envType=study-plan-v2&envId=top-interview-150";
        String result = getPathFromUrl(url);
        System.out.println(result);
    }

    public static String getPathFromUrl(String url) {
        String[] parts = url.split("/");
        StringBuilder sb = new StringBuilder();
        for (String part : parts) {
            if (!part.isEmpty()) {
                sb.append(part).append("/");
            }
        }
        return sb.toString().substring(0, sb.length() - 1);
    }
}
