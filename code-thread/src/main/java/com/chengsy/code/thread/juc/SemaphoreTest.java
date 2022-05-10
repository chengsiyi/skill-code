package com.chengsy.code.thread.juc;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author chengsiyi
 * @date 2020/12/1 19:58
 */
public class SemaphoreTest extends JucBaseTest {

    public static void main(String[] args) {
        // 线程数目
        int threadCount = 10;
        // 资源数目
        Semaphore semaphore = new Semaphore(3);


        // 启动若干线程
        for (int i = 0; i < threadCount; i++) {
            threadPool.execute(new ConsumeResourceTask((i + 1) * 1000, semaphore));
        }
    }
}

class ConsumeResourceTask extends JucBaseTest implements Runnable {
    private final Semaphore semaphore;
    private final int sleepTime;

    /**
     *
     */
    public ConsumeResourceTask(int sleepTime, Semaphore semaphore) {
        this.sleepTime = sleepTime;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            //获取资源
            semaphore.acquire();
            print(" 占用一个资源...");
            TimeUnit.MILLISECONDS.sleep(sleepTime);
            print(" 资源使用结束，释放资源");
            //释放资源
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
