package com.chengsy.code.algorithm.test;

import com.chengsy.code.algorithm.SingleLink;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * TODO
 *
 * @author chengsy
 * @version V1.0
 * @since 2017-11-08 10:15
 */
public class SingleLinkTest {

    private SingleLink<Integer> link = new SingleLink<Integer>();

    @BeforeMethod
    public void testAddNode() throws Exception {
        link.addNode(3);
        link.addNode(5);
        link.addNode(6);
        link.addNode(7);
        link.addNode(9);
        link.addNode(213);
        link.addNode(43);
        link.addNode(65);
        link.addNode(54);
    }

    @Test
    public void testDeleteNode() throws Exception {
        link.deleteNode(5);
    }

    @Test
    public void testSize() throws Exception {
        System.out.println("链表的长度为:" + link.size());
    }

    @AfterMethod
    public void testPrintList() throws Exception {
        link.printList();
    }

}