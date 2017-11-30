/*
 * Copyright (c) 2001-2017 GuaHao.com Corporation Limited. All rights reserved. 
 * This software is the confidential and proprietary information of GuaHao Company. 
 * ("Confidential Information"). 
 * You shall not disclose such Confidential Information and shall use it only 
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.chengsy.code.algorithm.test;

import com.chengsy.code.algorithm.HailConjecture;

import org.testng.annotations.Test;

/**
 * 冰雹猜想（角谷猜想）
 * 任意写出一个自然数N，并且按照以下的规律进行变换：
 * 如果是个奇数，则下一步变成3N+1。
 * 如果是个偶数，则下一步变成N/2。
 * 无论N是怎样一个数字，最终都无法逃脱回到4-2-1循环。
 *
 * @author chengsy
 * @version V1.0
 * @since 2017-11-30 14:05
 */
public class HailConjectureTest {

    private HailConjecture hailConjecture = new HailConjecture();

    @Test
    public void testHailConjecture() throws Exception {
        hailConjecture.hailConjecture(29,0, 1);
    }

    /**
     * 获取100W以内 变换次数最多的数字
     * @throws Exception
     */
    //    @Test
    public void testHailConjectureUnderMillion() throws Exception {
        for (int a = 1; a < 1000000; a ++){
            hailConjecture.hailConjecture(a, 0, 1);
        }
    }
}
