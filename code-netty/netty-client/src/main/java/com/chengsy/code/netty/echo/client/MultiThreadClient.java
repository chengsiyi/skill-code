package com.chengsy.code.netty.echo.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * @author chengsiyi
 * @date 2022/4/2 13:33
 */
public class MultiThreadClient {
    public static void main(String[] args) {
        try (SocketChannel sc = SocketChannel.open()){
            sc.connect(new InetSocketAddress("localhost",8080));
            sc.write(Charset.defaultCharset().encode("0123456789abcdef1234567"));
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
