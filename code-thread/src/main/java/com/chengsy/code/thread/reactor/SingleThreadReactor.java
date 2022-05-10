package com.chengsy.code.thread.reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

/**
 * @author chengsiyi
 * @date 2020/7/8 16:45
 */
public class SingleThreadReactor implements Runnable {

    private final Selector selector;
    private final ServerSocketChannel serverSocketChannel;


    public SingleThreadReactor(int port) throws IOException {
        selector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        // 绑定端口
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        // 设置为非阻塞
        serverSocketChannel.configureBlocking(false);
        SelectionKey sk = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        sk.attach(new Acceptor());
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                selector.select();
                Set selected = selector.selectedKeys();
                for (Object aSelected : selected) {
                    //Reactor负责dispatch收到的事件
                    dispatch((SelectionKey) (aSelected));
                }
                selected.clear();
            }
        } catch (IOException ex) { /* ... */ }
    }

    private void dispatch(SelectionKey k) {
        Runnable r = (Runnable) (k.attachment());
        //调用之前注册的callback对象
        if (r != null) {
            r.run();
        }
    }

    private class Acceptor implements Runnable {
        @Override
        public void run() {
            try {
                SocketChannel channel = serverSocketChannel.accept();
                if (channel != null) {
                    new Handler(selector, channel);
                }
            } catch (IOException ex) { /* ... */ }
        }
    }

    private class Handler implements Runnable {
        final SocketChannel channel;
        final SelectionKey sk;
        ByteBuffer input = ByteBuffer.allocate(1024);
        ByteBuffer output = ByteBuffer.allocate(1024);
        static final int READING = 0;
        static final int SENDING = 1;
        int state = READING;

        Handler(Selector selector, SocketChannel c) throws IOException {
            channel = c;
            c.configureBlocking(false);
            // Optionally try first read now
            sk = channel.register(selector, 0);

            //将Handler作为callback对象
            sk.attach(this);

            //第二步,注册Read就绪事件
            sk.interestOps(SelectionKey.OP_READ);
            selector.wakeup();
        }


        boolean inputIsComplete() {
            /* ... */
            return false;
        }

        boolean outputIsComplete() {

            /* ... */
            return false;
        }

        void process() {
            /* ... */
            return;
        }

        @Override
        public void run() {
            try {
                if (state == READING) {
                    read();
                } else if (state == SENDING) {
                    send();
                }
            } catch (IOException ex) { /* ... */ }
        }

        void read() throws IOException {
            channel.read(input);
            if (inputIsComplete()) {

                process();

                state = SENDING;
                // Normally also do first write now

                //第三步,接收write就绪事件
                sk.interestOps(SelectionKey.OP_WRITE);
            }
        }

        void send() throws IOException {
            channel.write(output);

            //write完就结束了, 关闭select key
            if (outputIsComplete()) {
                sk.cancel();
            }
        }
    }
}
