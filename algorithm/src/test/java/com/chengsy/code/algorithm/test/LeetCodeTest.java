package com.chengsy.code.algorithm.test;

import com.alibaba.fastjson.JSON;
import com.chengsy.code.algorithm.leetcode.LeetCode;
import com.chengsy.code.algorithm.leetcode.ListNode;
import com.chengsy.code.algorithm.leetcode.TreeNode;
import io.netty.util.NettyRuntime;
import org.testng.annotations.Test;

import java.util.Arrays;

public class LeetCodeTest {

    LeetCode leetCode = new LeetCode();

    @Test
    public void testTwoSum() throws Exception {
        System.out.println(Arrays.toString(leetCode.twoSum(new int[]{3, 2, 4}, 6)));
    }

    @Test
    public void addTwoNumbers() throws Exception {
        int[] a = {2, 4, 3};
        int[] b = {5, 6, 4};
        System.out.println(JSON.toJSONString(leetCode.addTwoNumbers(buildNode(a), buildNode(b))));
    }

    ListNode buildNode(int[] nums) {
        ListNode node = null;
        for (int i : nums) {
            node = ListNode.addNode(node, i);
        }
        return node;
    }

    @Test
    public void lengthOfLongestSubstring() throws Exception {
        String s = "adfdcfdsa";
        System.out.println(s + ":" + leetCode.lengthOfLongestSubstring(s));
        System.out.println(s + ":" + leetCode.lengthOfLongestSubstringV2(s));
    }

    @Test
    public void findMedianSortedArrays() throws Exception {
        int[] a = {2, 4, 6, 19, 34, 56, 78, 450, 460, 600};
        int[] b = {5, 6};
        // 2,4,5,6,6,9,12,14,19
        System.out.println(leetCode.findMedianSortedArrays(a, b));
    }

    @Test
    public void longestPalindrome() {
        String s = "babad";
        System.out.println(leetCode.longestPalindrome(s));
    }

    @Test
    public void maxArea() {
        int[] a = {2, 4, 6, 19, 34, 56, 78, 450, 460, 600};
        System.out.println(leetCode.maxArea(a));
    }

    @Test
    public void lemonadeChange() {
        int[] a = {5,5,5,10,20};
        System.out.println(leetCode.lemonadeChange(a));
    }

    @Test
    public void findTree() {
        //          node1
        //        /      \
        //   node2       node3
        //   /   \        /
        //node4 node5  node6
        //                \
        //               node7
        TreeNode<Integer> node7 = new TreeNode<>(7, null, null);
        TreeNode<Integer> node6 = new TreeNode<>(6, null, node7);
        TreeNode<Integer> node5 = new TreeNode<>(5, null, null);
        TreeNode<Integer> node4 = new TreeNode<>(4, null, null);
        TreeNode<Integer> node3 = new TreeNode<>(3, node6, null);
        TreeNode<Integer> node2 = new TreeNode<>(2, node4, node5);
        TreeNode<Integer> node1 = new TreeNode<>(1, node2, node3);
        System.out.println(leetCode.minDeep2(node1));

    }

    @Test
    public void netty(){
        System.out.println(NettyRuntime.availableProcessors());
    }

}
