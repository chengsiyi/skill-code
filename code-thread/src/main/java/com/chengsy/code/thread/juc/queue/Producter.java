package com.chengsy.code.thread.juc.queue;

import com.chengsy.code.thread.juc.JucBaseTest;

import java.time.LocalDateTime;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author chengsiyi
 * @date 2020/12/22 20:51
 */
public class Producter extends JucBaseTest implements Runnable {

    private final BlockingQueue<String> fileQueue;

    public Producter(BlockingQueue<String> queue) {
        this.fileQueue = queue;
    }

    public String product(){
        return LocalDateTime.now().toString();
    }

    @Override
    public void run() {

        try {
            while (true){
                TimeUnit.SECONDS.sleep(1);
                print("开始生产。。。");
                fileQueue.put(product());
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        BlockingQueue<String> queue = new LinkedBlockingQueue<>(10);

        for (int i = 0; i < 1; i++) {
            threadPool.execute(new Producter(queue));
        }
        for (int i = 0; i < 6; i++) {
            threadPool.execute(new Consumer(queue));
        }
    }
}
