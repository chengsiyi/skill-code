package com.chengsy.code.algorithm.test;

import com.alibaba.fastjson.JSON;
import com.chengsy.code.algorithm.leetcode.LeetCode;
import com.chengsy.code.algorithm.leetcode.ListNode;
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

}
