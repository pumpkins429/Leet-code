package com.lrh.leetcode.utils;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    /**
     * 根据数组生成二叉树，返回根节点
     *
     * @param list
     * @param pos  初始为0
     * @return
     */
    public static TreeNode geneTreeNode(Integer[] list, int pos) {
        // 根节点和两个子节点的关系是，2i+1,2i+2
        TreeNode node = null;

        if (pos >= 0 && pos < list.length && list[pos] != null) {
            // 给当前结点赋值
            node = new TreeNode(list[pos]);
            // 左子树
            node.left = geneTreeNode(list, 2 * pos + 1);
            // 右子树
            node.right = geneTreeNode(list, 2 * pos + 2);
        }

        return node;
    }

    @Override
    public String toString() {
        return "TreeNode [val=" + val + ", left=" + left + ", right=" + right + "]";
    }

    public static void main(String[] args) {
        /* TreeNode treeNode = TreeNode.geneTreeNode(new Integer[] { 1, 3, 2, 5 }, 0);
        System.out.println(treeNode); */


        TreeNode treeNode = TreeNode.geneTreeNode(new Integer[]{3, 9, 20, null, null, 15, 7}, 0);
        System.out.println(treeNode);
    }
}
