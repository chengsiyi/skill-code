package com.chengsy.code.algorithm.test;

import com.chengsy.code.algorithm.EulerProject;

import org.testng.annotations.Test;

public class EulerProjectTest {
    private EulerProject eulerProject = new EulerProject();

    @Test
    public void testSumMultiples() throws Exception {
        eulerProject.sumMultiples(1000);
    }

    @Test
    public void testFibonacci() throws Exception {
        eulerProject.sumFibonacciEven(4000000);
    }

    @Test
    public void testFindLargestPrime() throws Exception {
        System.out.println(eulerProject.decompositionPrime(13195));
    }

    @Test
    public void testFindPalindrome() throws Exception {
        eulerProject.findPalindrome(3);
    }

    @Test
    public void testSmallestMultiple() throws  Exception {
        eulerProject.smallestMultiple(20);
    }
}