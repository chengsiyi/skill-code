package com.chengsy.code.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Demo implements Runnable {
    public static final Logger logger = LoggerFactory.getLogger(Demo.class);
    public void run() {
        System.out.println("hi,I'm a thread, and I implement the Runnable!");
    }

    static ExecutorService fix = Executors.newFixedThreadPool(5);

    public static void main(String[] args) throws Exception {
        Queue<Future> queue = new LinkedList<>();
        Long start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            queue.add(fix.submit(() -> {
                try {
                    logger.info("{}",Thread.currentThread().getId());
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }));
        }
        for (Future future : queue) {
            future.get();
        }
        System.out.println(System.currentTimeMillis() - start);
    }
}


