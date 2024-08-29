package com.lrh.leetcode;

import com.lrh.leetcode.utils.ListNode;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class T2095_DeleteTheMiddleNodeOfALinkedList {
    @Test
    public void test() {
        System.out.println(deleteMiddle(ListNode.generateListNode(Arrays.asList(1, 2, 3, 4).toArray(new Integer[0]))));
        System.out.println(deleteMiddle(ListNode.generateListNode(Arrays.asList(1, 2).toArray(new Integer[0]))));
    }

    /**
     * 思路：
     * 方法一：先计算出中间节点的下标，然后遍历找到该元素进行删除 这里的遍历可以用两个指针，一快一慢
     * 方法二：将元素复制到数组中，然后将数组中的中间节点设为负数，再将数组转化为链表
     * @param head
     * @return
     */
    public ListNode deleteMiddle(ListNode head) {
        return v1(head);
        // return v2(head);
    }

    // 方法一：遍历
    public static ListNode v1(ListNode head) {

        // mid和len为快慢指针，当len遍历完后，mid为中间位置
        int mid = 0, len = 0;
        boolean flag = false; // 当flag为true时mid才往后
        ListNode midNode = head, endNode = head;
        ListNode preMid = null;// 需要记录mid的上一个元素

        while (endNode != null) {
            if (flag) {
                preMid = midNode;
                midNode = midNode.next;
                mid++;
            }

            endNode = endNode.next;
            len++;
            flag = !flag;
        }

        // 删除中间节点
        // 特判
        if(len == 1) {
            return null;
        }

        // 删除节点
        preMid.next = midNode.next;

        return head;
    }

    // 方法二 太慢！
    public static ListNode v2(ListNode head) {

        int len = 0;
        int[] array = new int[100001];
        ListNode tmp = head;

        // 复制到数组中
        while (tmp != null) {
            array[len] = tmp.val;
            tmp = tmp.next;
            len++;
        }

        // 特判
        if (len == 1) {
            return null;
        }

        // 处理中间节点
        array[len / 2] = -1;

        // 复制回链表
        ListNode newHead = new ListNode(head.val, null);
        tmp = newHead;
        for (int i = 1; i < len; i++) {
            if (array[i] != -1) {
                tmp.next = new ListNode(array[i], null);
                tmp = tmp.next;
            }
        }

        return newHead;
    }
}
