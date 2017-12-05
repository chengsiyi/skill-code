package com.chengsy.code.algorithm.test;

import com.chengsy.code.algorithm.EulerProject;

import org.testng.annotations.Test;

public class EulerProjectTest {
    private EulerProject multiples = new EulerProject();

    @Test
    public void testSumMultiples() throws Exception {
        multiples.sumMultiples(1000);
    }

    @Test
    public void testFibonacci() throws Exception {
        multiples.sumFibonacciEven(4000000);
    }

    @Test
    public void testFindLargestPrime() throws Exception {
        System.out.println(multiples.decompositionPrime(13195));
    }

    @Test
    public void testFindPalindrome() throws Exception {
        multiples.findPalindrome(3);
    }

    @Test
    public void testSmallestMultiple() throws  Exception {
        multiples.smallestMultiple(20);
    }
}