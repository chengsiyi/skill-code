package com.chengsy.code.netty.echo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author chengsiyi
 * @date 2022/4/2 13:07
 */
public class MultiThreadServer {

    public static final Logger logger = LoggerFactory.getLogger(MultiThreadServer.class);

    public static void main(String[] args) throws IOException {
        Thread.currentThread().setName("boss");
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        ssc.bind(new InetSocketAddress(8080));

        Selector boss = Selector.open();
        ssc.register(boss, SelectionKey.OP_ACCEPT);
        Worker[] workers = new Worker[2];
        for (int i = 0; i < workers.length; i++) {
            workers[i] = new Worker("work-" + i);
        }
        AtomicInteger index = new AtomicInteger();
        while (true) {
            int select = boss.select();
            if (select <= 0) {
                continue;
            }
            Iterator<SelectionKey> iterator = boss.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                if (key.isAcceptable()) {
                    SocketChannel sc = ssc.accept();
                    logger.info("connected...{}", sc.getRemoteAddress());
                    sc.configureBlocking(false);
                    int current = index.get();
                    logger.info("before register...{},current:{}", sc.getRemoteAddress(), current);
                    int next = (current + 1) % workers.length;
                    workers[current].register(sc);
                    index.compareAndSet(current, next);
                    logger.info("after register...{},next:{}", sc.getRemoteAddress(), next);

                }
            }

        }
    }

    static class Worker implements Runnable {
        private Thread thread;
        private String name;
        private Selector selector;
        private volatile boolean isStart;
        private ConcurrentLinkedQueue<Runnable> queue = new ConcurrentLinkedQueue<>();

        public Worker(String name) {
            this.name = name;
        }

        public void register(SocketChannel sc) throws IOException {
            if (!isStart) {
                selector = Selector.open();
                thread = new Thread(this, name);
                thread.start();
                isStart = true;
            }
            queue.add(() -> {
                try {
                    sc.register(selector, SelectionKey.OP_READ);
                } catch (ClosedChannelException e) {
                    e.printStackTrace();
                }
            });
            selector.wakeup();

        }

        @Override
        public void run() {
            try {
                while (true) {
                    selector.select();
                    Runnable task = queue.poll();
                    if (task != null) {
                        task.run();
                    }
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        iterator.remove();
                        if (key.isReadable()) {
                            try {
                                SocketChannel sc = (SocketChannel) key.channel();
                                logger.info("read...{}", sc.getRemoteAddress());
                                ByteBuffer buffer = ByteBuffer.allocate(16);
                                int read = sc.read(buffer);
                                if (read == -1) {
                                    key.cancel();
                                } else {
                                    buffer.flip();
                                    System.out.println(buffer);
                                }
                            } catch (IOException e) {
                                key.cancel();
                                e.printStackTrace();
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
