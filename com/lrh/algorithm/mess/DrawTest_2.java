package com.lrh.algorithm.mess;

import org.junit.jupiter.api.Test;

import java.security.SecureRandom;
import java.util.*;

/**
 * @author pumpkins
 * @date 2024/6/27 15:11
 * @description 实现一个简单的抽奖算法 gpt修改后的代码
 */
public class DrawTest_2 {

    // 保存用户的中奖概率
    private HashMap<Integer, List<Integer>> rateUsers = new HashMap<>();
    // 保存出现过的中奖概率，从小到大
    private SortedSet<Integer> rates = new TreeSet<>();
    // 保存1-10000对应的中奖群体
    private List<Integer> range = new ArrayList<>(RATE_TUPLE_LENGTH);

    // 斐波那契散列增量
    private static final int HASH_INCREMENT = 0x61c88647;
    private static final int RATE_TUPLE_LENGTH = 16384;
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    public static void main(String[] args) {
        DrawTest_2 drawTest = new DrawTest_2();

        // 随机生成一个长度为100-50000的用户积分列表
        int[] userScore = generateUserScoreList();

        long startTime = System.currentTimeMillis();
        // 初始化
        drawTest.init(userScore);
        System.out.println("初始化耗时" + (System.currentTimeMillis() - startTime) / 1000.0 + "秒");
        System.out.println("本次初始化生成用户列表长度为" + userScore.length);

        // 执行抽奖
        startTime = System.currentTimeMillis();
        for (int i = 1; i <= 10000; i++) {
            Integer winner = drawTest.doDraw();
        }
        System.out.println("10000次抽奖耗时" + (System.currentTimeMillis() - startTime) / 1000.0 + "秒");
    }

    @Test
    public void testIfCorrect() {
        // 用户积分列表
        int[] userScore = {20, 34, 160, 160, 160, 160, 30, 30, 45};

        // 初始化
        init(userScore);

        // 记录中奖情况
        int[] counts = new int[userScore.length];

        // 执行抽奖
        for (int i = 1; i <= 10000; i++) {
            Integer winner = doDraw();
            counts[winner]++;
        }

        System.out.println("10000次抽奖中：");
        for (int i = 0; i < userScore.length; i++) {
            System.out.printf("积分为%d的用户中奖次数为%d 实际中奖概率为%.4f%%\n",
                    userScore[i], counts[i], (counts[i] / 10000.0) * 100);
        }
    }

    /**
     * 初始化抽奖数据
     *
     * @param userScore 用户列表，索引为用户id，值为用户积分
     */
    public void init(int[] userScore) {
        // 初始化range列表
        range = new ArrayList<>(Collections.nCopies(RATE_TUPLE_LENGTH, null));

        // 计算用户总积分，积分相同的用户的积分只计算一次
        long totalScore = Arrays.stream(userScore).distinct().sum();

        // 根据用户的积分计算中奖概率并加入哈希表
        for (int i = 0; i < userScore.length; i++) {
            if (userScore[i] == 0) continue;

            int rate = (int) Math.round((1.0 * userScore[i] / totalScore) * 10000);
            rateUsers.computeIfAbsent(rate, k -> new ArrayList<>()).add(i);
            rates.add(rate);
        }

        // 初始化range数组，用于生成随机数1-10000时查询对应的中奖群体
        int flag = 0;
        for (Integer rate : rates) {
            for (int i = flag + 1; i <= flag + rate; i++) {
                range.set(calculateIndex(i), rate);
            }
            flag += rate;
        }

        // 补偿range数组未填充够10000
        for (int i = flag + 1; i <= 10000; i++) {
            range.set(calculateIndex(i), rates.last());
        }
    }

    /**
     * 执行抽奖，返回用户id
     *
     * @return 返回用户id
     */
    public Integer doDraw() {
        // 生成1-10000的随机数
        int num = SECURE_RANDOM.nextInt(10000) + 1;
        // 找到对应的中奖人群
        Integer rate = range.get(calculateIndex(num));
        List<Integer> userList = rateUsers.get(rate);

        // 从人群中随机选择一个用户为中奖用户
        return userList.size() == 1 ? userList.get(0) : userList.get(SECURE_RANDOM.nextInt(userList.size()));
    }

    /**
     * 计算斐波那契额散列索引
     *
     * @param val 输入值
     * @return 返回计算好的索引
     */
    private Integer calculateIndex(int val) {
        int hashCode = val * HASH_INCREMENT + HASH_INCREMENT;
        return hashCode & (RATE_TUPLE_LENGTH - 1);
    }

    /**
     * 随机生成用户积分列表
     *
     * @return 返回随机生成的用户积分列表
     */
    private static int[] generateUserScoreList() {
        int size = SECURE_RANDOM.nextInt(50000) + 101;
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = SECURE_RANDOM.nextInt(50000) + 1;
        }
        return arr;
    }

    /**
     * 测试是否正常生成索引
     */
    @Test
    public void testIndex() {
        // 测试斐波那契散列索引是否有效
        HashSet<Integer> set = new HashSet<>();
        for (int i = 1; i <= 10000; i++) {
            Integer index = calculateIndex(i);
            if (!set.add(index)) {
                System.out.println("索引重复: " + index);
                return;
            }
        }
        System.out.println("索引测试通过");
    }
}
