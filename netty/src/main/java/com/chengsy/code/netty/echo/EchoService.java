/*
 * Copyright (c) 2001-2017 GuaHao.com Corporation Limited. All rights reserved. 
 * This software is the confidential and proprietary information of GuaHao Company. 
 * ("Confidential Information"). 
 * You shall not disclose such Confidential Information and shall use it only 
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.chengsy.code.netty.echo;

import java.net.InetSocketAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * TODO
 *
 * @author chengsy
 * @version V1.0
 * @since 2017-11-03 09:26
 */
public class EchoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EchoService.class);

    /**
     * 端口
     */
    private final int port;

    public EchoService(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            LOGGER.error("Usage:" + EchoService.class.getSimpleName() + "<port>");
            return;
        }
        int port = Integer.parseInt(args[0]);
        new EchoService(port).start();
    }

    private void start() throws Exception {
        final EchoServiceHandler serviceHandler = new EchoServiceHandler();
        // 创建EventLoopGroup
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(group)
                // 指定所使用的Nio传输Channel
                .channel(NioServerSocketChannel.class)
                // 使用指定端口的套接字地址
                .localAddress(new InetSocketAddress(port))
                // 添加一个EchoServerHandler到子Channel的ChannelPipeline
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        // 因为EchoServerHandler被标注为@Shareable,所以我们总是使用同样的实例
                        socketChannel.pipeline().addLast(serviceHandler);
                    }
                });
            // 异步地绑定服务器，调用sync()方法阻塞等待知道绑定完成
            ChannelFuture future = b.bind().sync();
            // 获取Channel的CloseFuture,并且阻塞当前线程知道它完成
            future.channel().closeFuture().sync();
        } finally {
            // 关闭EventLoopGroup,释放所有资源
            group.shutdownGracefully().sync();
        }
    }
}
