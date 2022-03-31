package com.chengsy.code.algorithm.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class LeetCode {
    public static final Logger logger = LoggerFactory.getLogger(LeetCode.class);

    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> index = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (index.containsKey(nums[i])) {
                result[1] = i;
                result[0] = index.get(nums[i]);
                return result;
            }
            index.put(target - nums[i], i);
        }
        return result;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0);
        ListNode current = result;
        // 进位数
        int carry = 0;
        while (l1 != null || l2 != null) {
            int x = l1 != null ? l1.val : 0;
            int y = l2 != null ? l2.val : 0;
            int sum = x + y + carry;
            carry = sum >= 10 ? 1 : 0;
            current.next = new ListNode(sum % 10);
            current = current.next;

            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        //如果最后两个数，相加的时候有进位数的时候，就将进位数，赋予链表的新节点。
        //两数相加最多小于20，所以的的值最大只能时1
        if (carry == 1) {
            current.next = new ListNode(carry);
        }
        //返回链表的头节点
        return result.next;

    }

    public int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> window = new HashMap<>();
        int left = 0;
        int right = 0;
        int length = 0;
        while (right < s.length()) {
            char rightChar = s.charAt(right);
            right++;
            if (window.containsKey(rightChar)) {
                left = Math.max(left, window.get(rightChar));
            }
            length = Math.max(length, right - left);
            window.put(rightChar, right);
        }
        return length;
    }

    public int lengthOfLongestSubstringV2(String s) {
        int[] last = new int[128];
        int n = s.length();
        int res = 0;
        // 窗口开始位置
        int start = 0;
        for (int i = 0; i < n; i++) {
            int index = s.charAt(i);
            start = Math.max(start, last[index]);
            res = Math.max(res, i - start + 1);
            last[index] = i + 1;
        }

        return res;
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        if (m == 0 && n == 0) {
            return 0;
        }
        int left = (m + n + 1) / 2;
        int right = (m + n + 2) / 2;
        logger.info("nums1:{},nums2:{}, left:{}, right:{}", nums1, nums2, left, right);
        return (getKth(nums1, 0, nums2, 0, left) +
                getKth(nums1, 0, nums2, 0, right)) * 0.5;


    }

    private double getKth(int[] num1, int start1, int[] num2, int start2, int k) {
        if (start1 >= num1.length) {
            return num2[start2 + k - 1];
        }
        if (start2 >= num2.length) {
            return num1[start1 + k - 1];
        }
        if (k == 1) {
            return Math.min(num1[start1], num2[start2]);
        }
        int midVal1 = (start1 + k / 2 - 1 < num1.length) ? num1[start1 + k / 2 - 1] : Integer.MAX_VALUE;
        int midVal2 = (start2 + k / 2 - 1 < num2.length) ? num2[start2 + k / 2 - 1] : Integer.MAX_VALUE;
        logger.info("start1:{},start2:{},midVal1:{},midVal2:{},k:{}", start1, start2, midVal1, midVal2, k);
        if (midVal1 > midVal2) {
            return getKth(num1, start1, num2, start2 + k / 2, k - k / 2);
        }
        return getKth(num1, start1 + k / 2, num2, start2, k - k / 2);

    }

}
