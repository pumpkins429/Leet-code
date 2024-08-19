package com.lrh.leetcode;

import com.lrh.leetcode.utils.TreeNode;
import org.junit.jupiter.api.Test;

public class T112_PathSum {

    public boolean hasPathSum(TreeNode root, int targetSum) {
        return cal(root, targetSum);
    }

    public boolean cal(TreeNode tree, int count) {
        if (tree == null) {
            return false;
        }
        if (tree.left == null && tree.right == null && count == tree.val) {
            return true;
        } else if (tree.left != null || tree.right != null) {
            count -= tree.val;
            return cal(tree.left, count) || cal(tree.right, count);
        }
        return false;
    }

    @Test
    public void Test() {
    }
}
