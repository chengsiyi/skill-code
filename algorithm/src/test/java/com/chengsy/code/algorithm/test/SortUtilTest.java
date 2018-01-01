package com.chengsy.code.algorithm.test;

import com.chengsy.code.algorithm.SortUtil;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;

public class SortUtilTest {

    private int[] nums;

    @BeforeMethod
    public void init() {
        nums = new int[]{4, 4, 8, 1, 42, 75, 3, 34, 5, 2,23,600,99,34928,234};
    }

    @AfterMethod
    public void print() {
        System.out.println(Arrays.toString(nums));
    }

    @Test
    public void testBubbleSort() throws Exception {
        nums = SortUtil.bubbleSort(nums);
    }

    @Test
    public void testQuickSort() throws Exception {
        nums = SortUtil.quickSort(nums, 0, nums.length - 1);
    }

}