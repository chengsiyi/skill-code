package com.chengsy.code.algorithm.test;

import com.chengsy.code.algorithm.Multiples;

import org.testng.annotations.Test;

public class MultiplesTest {
    private Multiples multiples = new Multiples();

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
        multiples.findLargestPrime(13195);
    }

    @Test
    public void testFindPalindrome() throws Exception {
        multiples.findPalindrome(3);
    }
}