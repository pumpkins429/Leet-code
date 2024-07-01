package com.lrh.algorithm.mess;

import org.junit.jupiter.api.Test;

import java.security.SecureRandom;
import java.util.*;

/*
 * @author      : pumpkins
 * @date        : 2024/6/27 15:11
 * @description : 实现一个简单的抽奖算法
 * @Copyright   : ...
 */
/* 我们做了一个活动，根据用户的积分来抽奖，用户的积分都保存在一个数组里面

        arr = [20, 34, 160, 2…]，数组下标就是用户的 ID，则这里：

        ID 为 0 的用户的积分是 arr[0] 等于 20 分。

        ID 为 1 的用户的积分是 arr[1] 等于 34 分。

        请你设计一个抽奖算法，随机抽出一位中奖用户，要求积分越高中奖概率越高。

        返回值是中奖用户的 ID

        PS: 1<= arr.length <= 50000 且 1<= arr[i] <= 50000

        代码写出算法，

        并分析其时间复杂度，

        为其编写尽量多 unit test。 */
/*
 * 想法1：总概率为1，即始终有人中奖
 *       用户积分最低为0，最高没有上限，设所有用户的总积分为total（注意要去掉重复的用户积分，比如用户积分列表为2 2 2 4，则total应该为2+4=6），
 *       并设单用户的积分为score，那么可以这样设计：
 *       积分不为0的用户：按公平性应该设计中奖概率为 score/total，注意积分为0的用户：无中奖资格
 *       至此就可以初始化完用户的中奖概率数组userRate，这里设置用户的中奖概率精度为小数点后四位，这里可以取小数*1000
 *       然后初始化一个哈希表，key为中奖概率，value为用户列表，其中的用户的中奖概率都为key，用户列表存的则是用户的id，即对应用户中奖概率数组中的索引
 *       并且还需要一个数组range从小到大记录用户的中奖概率，用与后面生成随机数时循环匹配范围值，这里可以换成哈希表优化时间复杂度
 *       比如中奖的概率有A:0.2000,B:0.3000,C:0.5000，随机生成一个num(0-10000)
 *       当num = 1999,则抽中A中的用户
 *       当num = 4999，则抽中B中的用户
 *       当num = 9999，则抽中C中的用户
 *       这里还可以优化，初始化一个长度为10000的数组，利用斐波那契额散列索引将所有可能得随机数对应的中奖群体放入这个数组
 *       则只要初始化完成后，后续生成随机数找到中奖群体，只需要计算索引，不需要查询整个数组，复杂度为O(1)
 *
 *       然后得到中奖群体对应的概率后，查询哈希表得到中间群体用户列表，再根据用户列表的数量生成一个随机数，该随机数对应的用户即最终的中奖用户
 *
 *
 *  */
public class DrawTest {
    // 保存用户的中奖概率
    // List<Integer> userRate = new ArrayList<>();
    // 保存相同概率的用户
    static HashMap<Integer, List<Integer>> rateUsers = new HashMap<>();
    // 保存出现过的中奖概率，从小到大
    static SortedSet<Integer> rates = new TreeSet<>();
    // 保存1-10000对应的中奖群体
    static List<Integer> range = new ArrayList<>(16386);

    // 斐波那契散列增量，逻辑：黄金分割点：(√5 - 1) / 2 = 0.6180339887，Math.pow(2, 32) * 0.6180339887 = 0x61c88647
    private static final int HASH_INCREMENT = 0x61c88647;

    // 数组初始化长度, 这里取最接近10000的2^14
    private static final int RATE_TUPLE_LENGTH = 16384;

    // 测试
    public static void main(String[] args) {

        // 随机生成一个长度为100-50000的用户积分列表
        int[] userScore = generateUserScoreList();
        // int[] userScore = new int[]{20, 34, 160, 2};
        // int[] userScore = new int[]{20, 34, 160, 160, 160, 160, 2};
        // int[] userScore = new int[]{20, 34, 660, 660, 660, 660, 2};

        long currentTimeMillis = System.currentTimeMillis();
        // 先初始化
        init(userScore);
        System.out.println("初始化耗时" + (System.currentTimeMillis() - currentTimeMillis)/1000.0 + "秒");
        System.out.println("本次初始化生成用户列表长度为" + userScore.length);

        // 执行抽奖
       /*  Integer winner = doDraw();
        System.out.println(winner); */
        currentTimeMillis = System.currentTimeMillis();
        for (int i = 1; i <= 10000; i++) {
            Integer winner = doDraw();
            // System.out.println("第" + i + "次抽奖，" + "中奖用户为：" + winner + " ta的积分为：" + userScore[winner]);
        }
        System.out.println("10000次抽奖耗时" + (System.currentTimeMillis() - currentTimeMillis)/1000.0 + "秒");


        // 输出案例
        /*
        初始化耗时0.084秒
        本次初始化生成用户列表长度为26620
        10000次抽奖耗时0.195秒
        */
    }

    /**
     * 测试实际中奖概率是否符合理论中奖概率
     */
    @Test
    public void test_if_correct() {
        // 用户积分列表
        int[] userScore = new int[]{20, 34, 160, 160, 160, 160, 30, 30, 45};


        // 先初始化
        init(userScore);

        // 一些记录的值
        int count1 = 0;
        int count2 = 0;
        int count3 = 0;
        int count4 = 0;
        int count5 = 0;

        // 执行抽奖
        for (int i = 1; i <= 10000; i++) {
            Integer winner = doDraw();
            // System.out.println("第" + i + "次抽奖，" + "中奖用户位：" + winner + " ta的积分为：" + userScore[winner]);
            // 记录对应积分的抽奖情况
            switch (userScore[winner]) {
                case 20:
                    count1++;
                    break;
                case 30:
                    count2++;
                    break;
                case 34:
                    count3++;
                    break;
                case 45:
                    count4++;
                    break;
                case 160:
                    count5++;
                    break;
                default:
                    break;
            }
        }

        ArrayList<Integer> rateList = new ArrayList<>();
        for (Integer rate : rates) {
            rateList.add(rate);
        }

        System.out.println("10000次抽奖中：");
        System.out.println("积分为" + userScore[0] + "的用户中奖次数为" + count1 + " 理论中奖概率为" + rateList.get(0) / 10000.0 + "实际中奖概率为" + count1 / 10000.0);
        System.out.println("积分为" + userScore[1] + "的用户中奖次数为" + count2 + " 理论中奖概率为" + rateList.get(1) / 10000.0 + "实际中奖概率为" + count2 / 10000.0);
        System.out.println("积分为" + userScore[6] + "的用户中奖次数为" + count3 + " 理论中奖概率为" + rateList.get(2) / 10000.0 + "实际中奖概率为" + count3 / 10000.0);
        System.out.println("积分为" + userScore[8] + "的用户中奖次数为" + count4 + " 理论中奖概率为" + rateList.get(3) / 10000.0 + "实际中奖概率为" + count4 / 10000.0);
        System.out.println("积分为" + userScore[2] + "的用户中奖次数为" + count5 + " 理论中奖概率为" + rateList.get(4) / 10000.0 + "实际中奖概率为" + count5 / 10000.0);

        /*
        10000次抽奖中：
        积分为20的用户中奖次数为700 理论中奖概率为0.0692实际中奖概率为0.07
        积分为34的用户中奖次数为1026 理论中奖概率为0.1038实际中奖概率为0.1026
        积分为30的用户中奖次数为1141 理论中奖概率为0.1176实际中奖概率为0.1141
        积分为45的用户中奖次数为1560 理论中奖概率为0.1557实际中奖概率为0.156
        积分为160的用户中奖次数为5573 理论中奖概率为0.5536实际中奖概率为0.5573
        */

    }

    /**
     * 初始化抽奖数据
     *
     * @param userScore 用户列表，索引为用户id，值为用户积分
     */
    public static void init(int[] userScore) {

        // 初始化列表，使其大小为16386，所有元素都为null
        for (int i = 0; i < 16386; i++) {
            range.add(null);
        }

        // 先计算用户总积分，积分相同的用户的积分只计算一次，这里默认用户积分都>0
        long totalScore = 0;
        SortedSet<Integer> scoreSet = new TreeSet<>();
        for (int i = 0; i < userScore.length; i++) {
            scoreSet.add(userScore[i]);
        }
        for (Integer score : scoreSet) {
            totalScore += score;
        }

        // 根据用户的积分计算用户对应的中奖概率并加入哈希表
        for (int i = 0; i < userScore.length; i++) {
            if (0 == userScore[i]) {
                continue;
            }

            Integer tmp = (int) Math.round((1.0 * userScore[i] / totalScore) * 10000);
            List<Integer> userList = rateUsers.computeIfAbsent(tmp, v -> new ArrayList<Integer>());
            userList.add(i); // 加入用户id
            rates.add(tmp); // 保存概率
        }

        // 初始化range数组，用于生成随机数1-10000时查询对应的中奖群体
        int flag = 0;
        for (Integer rate : rates) {
            for (int i = flag + 1; i <= flag + rate; i++) {
                range.set(calculateIndex(i), rate);
            }
            flag += rate;
        }
        // 由于精度不够的原因，可能range数组未填充够10000，需要补偿上去
        for (int i = flag + 1; i <= 10000; i++) {
            range.set(calculateIndex(i), rates.last());
        }
    }

    /**
     * 执行抽奖，返回用户id
     *
     * @return 返回用户id
     */
    public static Integer doDraw() {

        // 先生成一个1-10000的随机数
        int num = new SecureRandom().nextInt(10000) + 1;
        // 找到对应的中奖人群
        Integer index = calculateIndex(num);
        Integer rate = range.get(index);
        List<Integer> userList = rateUsers.get(rate);

        // 从人群中随机选择一个用户为中奖用户
        if (1 == userList.size()) {
            return userList.get(0);
        } else {
            return userList.get(new SecureRandom().nextInt(userList.size()));
        }
    }

    /**
     * 计算斐波那契额散列索引
     *
     * @return 返回计算好的索引
     */
    public static Integer calculateIndex(int val) {
        int hashCode = val * HASH_INCREMENT + HASH_INCREMENT;
        return hashCode & (RATE_TUPLE_LENGTH - 1);
    }

    /**
     * 随机生成用户积分列表
     *
     * @return 返回随机生成的用户积分列表
     */
    public static int[] generateUserScoreList() {
        SecureRandom secureRandom = new SecureRandom();
        int size = secureRandom.nextInt(50000) + 101;
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = secureRandom.nextInt(50000) + 1;
        }
        return arr;
    }

    /**
     * 测试是否正常生成索引
     */
    @Test
    public void test_index() {
        // 测试斐波那契散列索引是否有效
        HashSet<Object> set = new HashSet<>();
        for (int i = 1; i <= 10000; i++) {
            Integer index = calculateIndex(i);
            if (set.contains(index)) {
                System.out.println("err");
                return;
            } else {
                set.add(index);
            }
        }
        System.out.println("ok");
    }
}
