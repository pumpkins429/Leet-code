package com.lrh.algorithm.mess;

import org.junit.jupiter.api.Test;

/*
 * @author      : pumpkins
 * @date        : 2024/4/13 8:48
 * @description : ...
 */
public class TreeTest {
    /*
     *            *
     *           * *
     *          * * *
     *         *     *
     *        * *   * *
     *       * * * * * *
     *      *     *     *
     *     * *   * *   * *
     *    * * * * * * * * *
     *   *     *     *     *
     *  * *   * *   * *   * *
     * * * * * * * * * * * * *
     *            *
     *            *
     *            *
     *            *
     *  */

    @Test
    public void test() {

        int size = 12; // 设置树上半部分的高度
        // 打印树的上半部分
        for (int i = 1; i <= size; i++) {

            // 打印没层数前面的空格
            for (int j = size; j > i; j--) {
                System.out.print(" ");
            }

            // 根据当前层数i打印相对应规律的层，分为三种，分别是i%3=0,i%3=1,i%3=2
            // 观察每层可知，如果当前层为i，则这一层输出x个*，其中中间间隔的空格有y个，且x+y=2*i-1

            if (i % 3 == 1) {
                for (int k = 1; k <= (2 * i - 1); k++) {
                    // 观察可知，当输出到第k个，k%6=1时输出*，比如第7层，分别在第1、第7、第13输出*，而1%6=7%6=13%6=1
                    if (k % 6 == 1 || k == (2 * i - 1)) {
                        System.out.print("*");
                    } else {
                        System.out.print(" ");
                    }
                }
            } else if (i % 3 == 2) {
                for (int k = 1; k <= (2 * i - 1); k++) {
                    // 观察可知，比如第五层，在1 3 7 9的位置输出*，2 4 5 6 8的位置输出空格，
                    // 找规律可知当k % 3 == 1 && k % 2 != 0输出*；
                    //         当k % 3 == 0 && k % 2 != 0输出空格
                    if ((k % 3 == 1 && k % 2 != 0) || (k % 3 == 0 && k % 2 != 0)) {
                        System.out.print("*");
                    } else {
                        System.out.print(" ");
                    }
                }
            } else if (i % 3 == 0) {
                for (int k = 1; k <= (2 * i - 1); k++) {
                    if (k % 2 == 1) {
                        System.out.print("*");
                    } else {
                        System.out.print(" ");
                    }
                }
            }

            System.out.println();
        }

        // 打印树的下半部分（树干）
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= size - 1; j++) {
                System.out.print(" ");
            }
            System.out.println("*");
        }
    }
}
