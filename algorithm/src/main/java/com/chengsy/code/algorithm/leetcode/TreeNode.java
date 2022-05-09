package com.chengsy.code.algorithm.leetcode;

/**
 * @author chengsiyi
 * @date 2022/4/1 12:34
 */
public class TreeNode<T> {
    T data;
    int deep;
    TreeNode<T> left;
    TreeNode<T> right;

    public TreeNode() {
    }

    public TreeNode(T data) {
        this.data = data;
    }

    public TreeNode(T data, TreeNode<T> left, TreeNode<T> right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
}
