package com.chengsy.code.netty.echo.service.chat;

import com.chengsy.code.chat.protocol.MessageCodecSharable;
import com.chengsy.code.chat.protocol.ProtocolFrameDecoder;
import com.chengsy.code.netty.echo.service.chat.handler.ChatRequestMessageHandler;
import com.chengsy.code.netty.echo.service.chat.handler.LoginRequestMessageHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author chengsiyi
 * @date 2022/4/8 10:37
 */
@Slf4j
public class ChatServer {

    public static void main(String[] args) {

        // boss 只负责 ServerSocketChannel 上的 accept
        EventLoopGroup boss = new NioEventLoopGroup();
        // worker 只负责 SocketChannel 读写
        EventLoopGroup worker = new NioEventLoopGroup();
        MessageCodecSharable MESSAGE_CODEC = new MessageCodecSharable();
        LoggingHandler LOGGING_HANDLER = new LoggingHandler(LogLevel.DEBUG);
        LoginRequestMessageHandler LOGIN_HANDLER = new LoginRequestMessageHandler();
        ChatRequestMessageHandler CHAT_HANDLER = new ChatRequestMessageHandler();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(boss, worker);
            serverBootstrap.channel(NioServerSocketChannel.class);
            serverBootstrap.childHandler(new ChannelInitializer<NioSocketChannel>() {
                @Override
                protected void initChannel(NioSocketChannel ch) {
                    // 帧解码器 【与自定义编解码器 MessageCodecSharable一起配置参数】
                    ch.pipeline().addLast(new ProtocolFrameDecoder());
                    // 日志
                    ch.pipeline().addLast(LOGGING_HANDLER);
                    // 出站入站的 自定义编解码器 【 解析消息类型 】
                    ch.pipeline().addLast(MESSAGE_CODEC);
                    ch.pipeline().addLast(LOGIN_HANDLER);
                    ch.pipeline().addLast(CHAT_HANDLER);
                }
            });
            Channel channel = serverBootstrap.bind(8080).sync().channel();
            channel.closeFuture().sync();
        } catch (Exception e) {
            log.error("server error", e);
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }

}
