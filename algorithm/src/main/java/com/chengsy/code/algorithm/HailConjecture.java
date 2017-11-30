/*
 * Copyright (c) 2001-2017 GuaHao.com Corporation Limited. All rights reserved. 
 * This software is the confidential and proprietary information of GuaHao Company. 
 * ("Confidential Information"). 
 * You shall not disclose such Confidential Information and shall use it only 
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.chengsy.code.algorithm;

import java.util.List;
import java.util.Map;
import java.util.TreeSet;
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

    Map<Long,Integer> result = new ConcurrentHashMap<Long, Integer>();

    public void hailConjecture(long a, int count, long max) {
        System.out.print(a);
        if (a > 1) {
            count ++;
            if (a % 2 == 0) {
                a = a / 2;
            } else {
                a = 3 * a + 1;
            }
            if (a > max) {
                max = a;
            }
            System.out.print("->");
        } else {
            System.out.println();
            System.out.println(String.format("参数经过%d步跌落到谷底.", count));
            System.out.println(String.format("整个过程中，最大值为%d.", max));
            return;
        }
        hailConjecture(a, count, max);
    }

}
