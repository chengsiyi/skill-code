package com.chengsy.code.thread.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author chengsiyi
 * @date 2020/12/1 19:44
 */
public class CountDownLatchTest extends JucBaseTest implements Runnable {

    private final CountDownLatch countDownLatch;

    private final int sleepTime;

    public CountDownLatchTest(CountDownLatch countDownLatch, int sleepTime) {
        this.countDownLatch = countDownLatch;
        this.sleepTime = sleepTime;
    }

    @Override
    public void run() {
        try {
            print(" is running。");
            TimeUnit.MILLISECONDS.sleep(sleepTime);
            print(" finished。");
            //计数器减减
            countDownLatch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int count = 10;
        final CountDownLatch latch = new CountDownLatch(count);
        for (int i = 0; i < count; i++) {
            threadPool.execute(new CountDownLatchTest(latch, (i + 1) * 1000));
        }

        try {
            print(" waiting...");
            //主线程等待其它事件发生
            latch.await();
            //其它事件已发生，继续执行主线程
            print(" continue。。。");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
