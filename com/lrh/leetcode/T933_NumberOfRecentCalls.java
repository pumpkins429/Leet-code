package com.lrh.leetcode;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

public class T933_NumberOfRecentCalls {
    @Test
    public void test() {
        RecentCounter recentCounter = new RecentCounter();
        System.out.println(recentCounter.ping(1));     // requests = [1]，范围是 [-2999,1]，返回 1
        System.out.println(recentCounter.ping(100));   // requests = [1, 100]，范围是 [-2900,100]，返回 2
        System.out.println(recentCounter.ping(3001));  // requests = [1, 100, 3001]，范围是 [1,3001]，返回 3
        System.out.println(recentCounter.ping(3002));  // requests = [1, 100, 3001, 3002]，范围是 [2,3002]，返回 3
    }


}
class RecentCounter {

    // 使用一个队列
    Queue<Integer> queue = null;

    public RecentCounter() {
        queue = new LinkedList<>();
    }

    public int ping(int t) {
        // 新请求先入队
        queue.offer(t);

        // 超时的请求剔除
        while (queue.peek() != null && queue.peek() < (t - 3000)) {
            queue.poll();
        }

        return queue.size();
    }
}
