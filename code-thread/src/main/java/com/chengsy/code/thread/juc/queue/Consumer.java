package com.chengsy.code.thread.juc.queue;

import com.chengsy.code.thread.juc.JucBaseTest;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author chengsiyi
 * @date 2020/12/22 20:58
 */
public class Consumer extends JucBaseTest implements Runnable {

    private final BlockingQueue<String> queue;

    public Consumer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                TimeUnit.MILLISECONDS.sleep(1000);
                print("prepare 消费");
                print("starting：" + queue.take());
                print("end 消费");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
