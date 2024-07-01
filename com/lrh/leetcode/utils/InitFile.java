package com.lrh.leetcode.utils;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * @author      : pumpkins
 * @date        : 2024/3/23 12:02
 * @description : 生成题目文件的工具类
 */
public class InitFile {
    /**
     * 快速创建java文件
     * @param num 题目编号
     * @param url 力扣题目url，用来提取题目名字
     */
    public static void initFile(Integer num, String url) {
        // 处理文件名
        Pattern pattern = Pattern.compile("/problems/(.*?)/");
        Matcher matcher = pattern.matcher(url);
        if (!matcher.find()) {
            System.out.println("URL格式不正确！");
            return;
        }
        String[] parts = matcher.group(1).split("-");

        StringBuilder sb = new StringBuilder();
        for (String part : parts) {
            sb.append(Character.toUpperCase(part.charAt(0))).append(part.substring(1));
        }
        String prefixPath = "C:/mess/Java/idea_workspace/leetcode/com/lrh/leetcode/";
        String className = "T" + num + "_" + sb;
        String suffix = ".java";

        String code = "package com.lrh.leetcode;\n" +
                "\n" +
                "import org.junit.jupiter.api.Test;" +
                "\n" +
                "public class " + className + " {\n" +
                "    @Test\n" +
                "    public void Test() {\n" +
                "        \n" +
                "    }\n" +
                "}\n";

        File file = new File(prefixPath + className + suffix);
        if(file.exists()) {
            System.out.println(file.getAbsolutePath() + " 文件已存在！！");
            return;
        }


        try(FileWriter fw = new FileWriter(prefixPath + className + suffix)) {
            fw.write(code);
            fw.flush();
            System.out.println(new File(prefixPath + className + suffix).getAbsolutePath() +" 文件创建成功");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test() {
        Integer num = 112;
        String url = "https://leetcode.cn/problems/path-sum/description/?envType=study-plan-v2&envId=top-interview-150";
        InitFile.initFile(num, url);
    }



}
