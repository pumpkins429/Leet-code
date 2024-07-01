package com.lrh.leetcode.utils;

public class ListNode {
	public int val;
	public ListNode next;

	public ListNode() {
	}


	public ListNode(int val, ListNode next) {
		this.val = val;
		this.next = next;
	}

	//生成链表
	public ListNode generateListNode(Integer[] is) {
		ListNode list = new ListNode(0, null), tmp = list;

		for (Integer num : is) {
			tmp.next = new ListNode(num, null);
			tmp = tmp.next;

		}


		return list.next;
	}

	public static void main(String[] args) {
		//System.out.println(new ListNode().generateListNode(new Integer[] {1, 2, 3, 4, 5}));
		System.out.println('b' ^ 32);
	}

	@Override
	public String toString() {
		return "ListNode [val=" + val + ", next=" + next + "]";
	}


	public int getVal() {
		return val;
	}

	public void setVal(int val) {
		this.val = val;
	}

	public ListNode getNext() {
		return next;
	}

	public void setNext(ListNode next) {
		this.next = next;
	}
}
