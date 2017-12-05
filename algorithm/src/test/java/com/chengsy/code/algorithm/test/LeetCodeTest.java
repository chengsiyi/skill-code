package com.chengsy.code.algorithm.test;

import com.chengsy.code.algorithm.LeetCode;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.Assert.*;

public class LeetCodeTest {

    LeetCode leetCode = new LeetCode();

    @Test
    public void testTwoSum() throws Exception {
        System.out.println(Arrays.toString(leetCode.twoSum(new int[]{2, 7, 11, 15,20,34,57,23,54,12,54}, 54)));
    }

}