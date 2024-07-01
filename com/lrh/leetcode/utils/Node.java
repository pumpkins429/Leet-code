package com.lrh.leetcode.utils;

public class Node {
	public int val;
	public Node left;
	public Node right;
	public Node next;

	public Node() {}

	public Node(int _val) {
		val = _val;
	}

	public Node(int _val, Node _left, Node _right, Node _next) {
		val = _val;
		left = _left;
		right = _right;
		next = _next;
	}


	/**
	 * 根据数组生成二叉树，返回根节点
	 *
	 * @param list
	 * @param pos  初始为0
	 * @return
	 */
	public static Node geneTreeNode(Integer[] list, int pos) {
		// 根节点和两个子节点的关系是，2i+1,2i+2
		Node node = null;

		if (pos >= 0 && pos < list.length && list[pos] != null) {
			// 给当前结点赋值
			node = new Node(list[pos]);
			// 左子树
			node.left = geneTreeNode(list, 2 * pos + 1);
			// 右子树
			node.right = geneTreeNode(list, 2 * pos + 2);
			node.next = null;
		}

		return node;
	}

	@Override
	public String toString() {
		return "Node [val=" + val + ", left=" + left + ", right=" + right + ", next=" + next + "]";
	}

	public static void main(String[] args) {
		Node node = Node.geneTreeNode(new Integer[] {1,2,3,4,5,6,7}, 0);
		System.out.println(node);
	}
}
