/*
 * Copyright (c) 2001-2017 GuaHao.com Corporation Limited. All rights reserved. 
 * This software is the confidential and proprietary information of GuaHao Company. 
 * ("Confidential Information"). 
 * You shall not disclose such Confidential Information and shall use it only 
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.chengsy.code.algorithm;

/**
 * 单链表
 * 特点：单个结点创建方便
 * 结点的删除方便
 * 结点的访问方便
 * @author chengsy
 * @version V1.0
 * @since 2017-11-08 09:14
 */
public class SingleLink<T> {
    /**
     * 头结点
     */
    Node<T> head = null;

    int size = 0;
    /**
     * 链表结点
     * @param <T>
     */
    private class Node<T> {

        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
        }
    }

    /**
     * 向链表中插入数据
     * @param d 数据
     */
    public void addNode(T d) {
        // 先实例化一个结点
        Node<T> node = new Node<T>(d);
        if (head == null) {
            head = node;
            size ++;
        } else {
            Node tmp = head;
            while (tmp.next != null) {
                tmp = tmp.next;
            }
            tmp.next = node;
            size ++;
        }
    }

    /**
     * 删除指定结点
     * @param index 结点索引
     * @return 删除结果
     */
    public boolean deleteNode(int index) {
        if (index < 1 || index > size) {
            return false;
        }

        if (index == 1) {
            head = head.next;
            size --;
            return true;
        }

        int i = 1;
        Node prev = head;
        Node cure = head.next;

        while (cure != null) {
            if (i == index) {
                prev.next = cure.next;
                size --;
                return true;
            }
            prev = cure;
            cure = cure.next;
            i ++;
        }
        return false;
    }

    /**
     * 获取链表长度
     * @return 长度
     */
    public int size() {
        return size;
    }

    /**
     * 删除结点
     * @param node 要删除的结点
     * @return 删除结果
     */
    private boolean unlink (Node<T> node) {
        if (node == null || node.next == null) {
            return false;
        }

        T data = node.data;
        node.data = node.next.data;
        node.next.data = data;
        node.next = node.next.next;
        size --;
        return true;
    }

    /**
     * 输出列表中的元素
     */
    public void printList() {
        Node node = head;
        while (node != null) {
            System.out.println(node.data);
            node = node.next;
        }
    }

}
