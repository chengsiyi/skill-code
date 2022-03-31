package com.chengsy.code.netty.echo.client;

import com.chengsy.code.CodecUtil;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author chengsiyi
 * @date 2020/6/19 10:58
 */
public class NioClient {

    private SocketChannel clientSocketChannel;
    private Selector selector;
    private final List<String> responseQueue = new ArrayList<>();

    private CountDownLatch connected = new CountDownLatch(1);

    public NioClient() throws IOException, InterruptedException {

        clientSocketChannel = SocketChannel.open();

        clientSocketChannel.configureBlocking(false);

        selector = Selector.open();

        clientSocketChannel.register(selector, SelectionKey.OP_CONNECT);

        clientSocketChannel.connect(new InetSocketAddress(8080));

        new Thread(() -> {
            try {
                handleKeys();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        if (connected.getCount() != 0) {
            connected.await();
        }
        System.out.println("Client 启动完成");
    }

    @SuppressWarnings("Duplicates")
    private void handleKeys() throws IOException {
        while (true) {

            int selectNums = selector.select(30 * 1000L);
            if (selectNums == 0) {
                continue;
            }


            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                if (!key.isValid()) {
                    continue;
                }

                handleKey(key);
            }
        }
    }

    private synchronized void handleKey(SelectionKey key) throws IOException {

        if (key.isConnectable()) {
            handleConnectableKey(key);
        }

        if (key.isReadable()) {
            handleReadableKey(key);
        }

        if (key.isWritable()) {
            handleWritableKey(key);
        }
    }

    private void handleConnectableKey(SelectionKey key) throws IOException {

        if (!clientSocketChannel.isConnectionPending()) {
            return;
        }
        clientSocketChannel.finishConnect();

        System.out.println("接受新的 Channel");

        clientSocketChannel.register(selector, SelectionKey.OP_READ, responseQueue);

        connected.countDown();
    }

    @SuppressWarnings("Duplicates")
    private void handleReadableKey(SelectionKey key) throws ClosedChannelException {

        SocketChannel clientSocketChannel = (SocketChannel) key.channel();

        ByteBuffer readBuffer = CodecUtil.read(clientSocketChannel);

        if (readBuffer.position() > 0) {
            String content = CodecUtil.newString(readBuffer);
            System.out.println("读取数据：" + content);
        }
    }

    @SuppressWarnings("Duplicates")
    private void handleWritableKey(SelectionKey key) throws ClosedChannelException {

        SocketChannel clientSocketChannel = (SocketChannel) key.channel();


        List<String> responseQueue = (ArrayList<String>) key.attachment();
        for (String content : responseQueue) {

            System.out.println("写入数据：" + content);

            CodecUtil.write(clientSocketChannel, content);
        }
        responseQueue.clear();


        clientSocketChannel.register(selector, SelectionKey.OP_READ, responseQueue);
    }

    public synchronized void send(String content) throws ClosedChannelException {

        responseQueue.add(content);

        System.out.println("写入数据：" + content);

        clientSocketChannel.register(selector, SelectionKey.OP_WRITE, responseQueue);
        selector.wakeup();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        NioClient client = new NioClient();
        for (int i = 0; i < 30; i++) {
            client.send("nihao: " + i);
            Thread.sleep(1000L);
        }
    }

}
