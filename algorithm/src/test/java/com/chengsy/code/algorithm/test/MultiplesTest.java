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


}