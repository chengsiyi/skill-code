package com.chengsy.code.algorithm.leetcode;

/**
 * @author chengsiyi
 * @date 2022/3/30 13:57
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }


    public static ListNode addNode(ListNode head, Integer d) {
        // 先实例化一个结点
        ListNode node = new ListNode(d);
        if (head == null) {
            return node;
        }
        ListNode tmp = head;
        // 遍历到尾节点
        while (tmp.next != null) {
            tmp = tmp.next;
        }
        tmp.next = node;
        return head;
    }
}
