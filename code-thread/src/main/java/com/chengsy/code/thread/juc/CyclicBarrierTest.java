package com.chengsy.code.thread.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * @author chengsiyi
 * @date 2020/12/22 19:47
 */
public class CyclicBarrierTest extends JucBaseTest implements Runnable {

    private CyclicBarrier cyclicBarrier;

    private int timeout;

    public CyclicBarrierTest(CyclicBarrier cyclicBarrier, int timeout) {
        this.cyclicBarrier = cyclicBarrier;
        this.timeout = timeout;
    }

    @Override
    public void run() {
        print("正在 running。。。");
        try {
            TimeUnit.SECONDS.sleep(timeout);
            print("到达栅栏处，等待其他线程到达。");
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        print("所有线程到达栅栏处，继续执行各自线程任务");
    }

    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(10, new Runnable() {
            @Override
            public void run() {
                print("所有线程到达栅栏处，处理其他事情");
            }
        });
        for (int i = 0; i < 10; i++) {
            threadPool.execute(new CyclicBarrierTest(barrier, i + 1));
        }
    }
}
