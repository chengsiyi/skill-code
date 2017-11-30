package com.chengsy.code.algorithm;

import java.util.LinkedList;
import java.util.List;

/**
 * If we list all the natural numbers below 10 that are multiples of 3 or 5,
 * we get 3, 5, 6 and 9. The sum of these multiples is 23.
 * Find the sum of all the multiples of 3 or 5 below 1000.
 * 找出1000以内3或5倍数的数字的和
 */
public class Multiples {

    public void sumMultiples(int limitNum) {
        int sum = 0;
        for (int i = 0; i < limitNum; i++) {
            if (i % 3 == 0 || i % 5 == 0) {
                sum += i;
            }
        }
        System.out.println(sum);
    }

    public void sumFibonacciEven(int limitNum) {
        List<Integer> fibonacciList = new LinkedList<Integer>();
        fibonacciList.add(1);
        fibonacciList.add(2);
        int sum = 2;
        for (int i = 1; i < fibonacciList.size(); i++) {
            int fibonacci = fibonacciList.get(i - 1) + fibonacciList.get(i);
            if (fibonacci > limitNum) {
                break;
            }
            if (fibonacci % 2 == 0) {
                sum += fibonacci;
            }
            fibonacciList.add(fibonacci);
        }
        System.out.println(sum);
    }

    // todo 求给定数字的最大质因数
    public void findLargestPrime(long limitNum) {
        limitNum = 600851475143L;
        for (long i = 0; i < limitNum; i++) {

        }
    }
}
