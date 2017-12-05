package com.chengsy.code.algorithm;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author chengsy
 */
public class Multiples {

    /**
     * 找出1000以内3或5倍数的数字的和
     * https://projecteuler.net/problem=1
     *
     * @param limitNum
     */
    public void sumMultiples(int limitNum) {
        int sum = 0;
        for (int i = 0; i < limitNum; i++) {
            if (i % 3 == 0 || i % 5 == 0) {
                sum += i;
            }
        }
        System.out.println(sum);
    }

    /**
     * 求给定范围内所有Fibonacci数偶数项之和
     * https://projecteuler.net/problem=2
     *
     * @param limitNum
     */
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

    /**
     * 分解质因数
     * https://projecteuler.net/problem=3
     *
     * @param limitNum 目标数
     */
    public Map<Integer, Integer> decompositionPrime(long limitNum) {
        Map<Integer, Integer> primeMap = new ConcurrentHashMap<Integer, Integer>(50);
        long temNum = (long) Math.ceil(Math.sqrt(limitNum));
        for (int i = 2; i <= temNum; i++) {
            while (limitNum % i == 0 && limitNum != i) {
                if (primeMap.containsKey(i)) {
                    primeMap.put(i, primeMap.get(i) + 1);
                } else {
                    primeMap.put(i, 1);
                }
                limitNum = limitNum / i;
            }
            if (limitNum == i) {
                if (primeMap.containsKey(i)) {
                    primeMap.put(i, primeMap.get(i) + 1);
                } else {
                    primeMap.put(i, 1);
                }
                break;
            }
        }
        return primeMap;
    }

    /**
     * 找到两个给定位数的最大数字的积 构成的回文数
     * 1.判断回文数
     * 2.找到满足给定位数的数字
     * https://projecteuler.net/problem=4
     *
     * @param digit
     */
    public void findPalindrome(int digit) {
        int maxNum = (int) Math.pow(10, digit) - 1;
        int minNum = (int) Math.pow(10, digit - 1);
        int targetNum = 0;
        int max = 0;
        boolean flag;
        for (int i = maxNum; i >= minNum; i--) {
            for (int j = i; j > minNum; j--) {
                targetNum = i * j;
                flag = palindrome(targetNum);
                if (flag && targetNum > max) {
                    max = targetNum;
                }
            }
        }
        System.out.println(String.format("最大的回文数字是:%d", max));
    }

    public boolean palindrome(int num) {
        String palindromeNum = String.valueOf(num);
        boolean flag = true;
        int length = palindromeNum.length();
        int loop;
        if (length % 2 == 0) {
            loop = length / 2;
        } else {
            loop = (length / 2) + 1;
        }
        for (int i = 0; i < loop; i++) {
            if (palindromeNum.charAt(i) != palindromeNum.charAt(length - 1 - i)) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    /**
     * https://projecteuler.net/problem=5
     * 找出能被指定范围内数字整除的最小自然数
     */
    public void smallestMultiple(int limit) {
        boolean flag = true;
        Map<Integer, Integer> primeMap = new ConcurrentHashMap<Integer, Integer>(limit);
        Map<Integer, Integer> compositeMap;
        // 将给定范围内的数字区分开来
        for (int i = 2; i <= limit; i++) {
            flag = isPrime(i);
            if (flag) {
                primeMap.put(i, 1);
            } else {
                // 分解质因数
                compositeMap = decompositionPrime(i);
                for (Map.Entry<Integer, Integer> result : compositeMap.entrySet()) {
                    // 分解出来的质因数根据权重重新赋值
                    if (primeMap.containsKey(result.getKey())) {
                        if (primeMap.get(result.getKey()) < result.getValue()) {
                            primeMap.put(result.getKey(), result.getValue());
                        }
                    } else {
                        primeMap.put(result.getKey(), result.getValue());
                    }
                }
            }
        }
        int num = 1;
        for (Map.Entry<Integer, Integer> result : primeMap.entrySet()) {
            num = num * (int) Math.pow(result.getKey(), result.getValue());
        }
        System.out.println(num);
    }

    /**
     * 判断是不是质数
     *
     * @param num
     * @return
     */
    private boolean isPrime(int num) {
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}