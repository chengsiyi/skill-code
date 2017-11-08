/*
 * Copyright (c) 2001-2017 GuaHao.com Corporation Limited. All rights reserved. 
 * This software is the confidential and proprietary information of GuaHao Company. 
 * ("Confidential Information"). 
 * You shall not disclose such Confidential Information and shall use it only 
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.chengsy.code.netty.echo.client;

import java.net.InetSocketAddress;
import java.util.logging.SocketHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author chengsy
 * @version V1.0
 * @since 2017-11-06 09:05
 */
public class EchoClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(EchoClient.class);

    private final String host;
    private final int port;

    public EchoClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                // 用于Nio的实现，适用于Nio传输的Channel类型
                .channel(NioSocketChannel.class)
                .remoteAddress(new InetSocketAddress(host, port))
                // 在创建Channel时，向ChannelPipeline中添加一个EchoClientHandler实例
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new EchoClientHandler());
                    }
                });
            // 连接到远程节点，阻塞等待直到连接完成
            ChannelFuture future = bootstrap.connect().sync();
            // 阻塞，直到Channel关闭
            future.channel().closeFuture().sync();
        } finally {
            // 关闭线程池并且释放所有的资源
            group.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws Exception{

        String host = "127.0.0.1";
        int port = 8081;
        new EchoClient(host, port).start();
    }
}
