/*
 * Copyright (c) 2001-2017 GuaHao.com Corporation Limited. All rights reserved. 
 * This software is the confidential and proprietary information of GuaHao Company. 
 * ("Confidential Information"). 
 * You shall not disclose such Confidential Information and shall use it only 
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.chengsy.code.algorithm;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 冰雹猜想（角谷猜想）
 * 任意写出一个自然数N，并且按照以下的规律进行变换：
 * 如果是个奇数，则下一步变成3N+1。
 * 如果是个偶数，则下一步变成N/2。
 * 无论N是怎样一个数字，最终都无法逃脱回到4-2-1循环。
 *
 * @author chengsy
 * @version V1.0
 * @since 2017-11-30 14:43
 */
public class HailConjecture {

    Map<Long, Integer> result = new ConcurrentHashMap<Long, Integer>();

    /**
     * 10W-100W数据，执行时间相差10倍，时间复杂度logN
     * 1000000范围内,变换步数最多的数字是837799,一共变换了524步
     * 运算过程中，计算步数耗时:7261ms,找到变换次数最多的数字耗时:156ms
     * System.out.print最耗时 10W时执行时间为 23642ms
     *
     * @param targetNum
     * @param count
     * @param max
     * @return
     */
    public Integer hailConjecture(long targetNum, int count, long max) {
        //                System.out.print(targetNum);
        long tempNum;
        if (targetNum > 1) {
            count++;
            if (targetNum % 2 == 0) {
                tempNum = targetNum / 2;
            } else {
                tempNum = 3 * targetNum + 1;
            }
            if (tempNum > max) {
                max = targetNum;
            }
            put(tempNum, 0);
            //            System.out.print("->");
        } else {
            //            System.out.println();
            //            System.out.println(String.format("参数经过%d步跌落到谷底.", count));
            //            System.out.println(String.format("整个过程中，最大值为%d.", max));
            return count;
        }
        return hailConjecture(tempNum, count, max);
    }

    /**
     * 倒序查找，
     * 273125/1000000 4533ms
     * 27312/100000 577ms
     * 2725/10000 79ms
     * 正序查找，
     * 4328/10000 72ms
     * 43268/100000 572ms
     * 432365/1000000 5865ms
     *
     * @param limitNum
     */
    public void hailConjecture(long limitNum) {
        long begin = System.currentTimeMillis();
        int i = 0;
        for (long a = limitNum; a > 1; a--) {
            if (result.containsKey(a)) {
                continue;
            }
            i++;
            Integer count = hailConjecture(a, 0, 1);
            result.put(a, count);
        }
        System.out.println("最终调用次数" + i);
        long calculation = System.currentTimeMillis();
        Iterator<Map.Entry<Long, Integer>> it = result.entrySet().iterator();
        Long targetNum = 1L;
        Integer chainNum = 1;
        while (it.hasNext()) {
            Map.Entry<Long, Integer> entry = it.next();
            if (!entry.getValue().equals(0) && entry.getValue() > chainNum) {
                chainNum = entry.getValue();
                targetNum = entry.getKey();
            }
        }
        long find = System.currentTimeMillis();
        System.out
                .println(String.format("运算过程中，计算步数耗时:%dms,找到变换次数最多的数字耗时:%dms", calculation - begin, find - calculation));
        System.out.println(String.format("%d范围内,变换步数最多的数字是%d,一共变换了%d步", limitNum, targetNum, chainNum));
    }

    private void put(Long targetNum, Integer chainNum) {
        if (result.containsKey(targetNum)) {
            return;
        }
        result.put(targetNum, chainNum);
    }
}
