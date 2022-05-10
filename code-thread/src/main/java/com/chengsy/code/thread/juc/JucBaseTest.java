package com.chengsy.code.thread.juc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author chengsiyi
 * @date 2020/12/16 20:13
 */
public class JucBaseTest {

    public static final Logger logger = LoggerFactory.getLogger(JucBaseTest.class);

    public static final ThreadPoolExecutor threadPool = new ThreadPoolExecutor(10, 20, 10, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(1024));

    public static void print(String str) {
        logger.info(str);
    }
}
