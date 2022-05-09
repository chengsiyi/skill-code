package com.chengsy.code.netty.echo.service.chat;

import cn.hutool.core.util.RandomUtil;
import com.chengsy.code.chat.message.LoginRequestMessage;
import com.chengsy.code.chat.message.LoginResponseMessage;
import com.chengsy.code.chat.protocol.MessageCodecSharable;
import com.chengsy.code.chat.protocol.ProtocolFrameDecoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * 聊天--服务端
 */
@Slf4j
public class BlackHorseChatServer {


    public static void main(String[] args) throws InterruptedException {

        final NioEventLoopGroup boss = new NioEventLoopGroup();
        final NioEventLoopGroup worker = new NioEventLoopGroup();
        // 局部变量
        MessageCodecSharable MESSAGE_CODEC = new MessageCodecSharable();
        LoggingHandler LOGGING_HANDLER = new LoggingHandler(LogLevel.DEBUG);


        try {
            final ServerBootstrap bs = new ServerBootstrap();
            bs.channel(NioServerSocketChannel.class);
            bs.group(boss, worker);
            bs.childHandler(new ChannelInitializer<SocketChannel>() {

                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new ProtocolFrameDecoder()); // 帧解码器 【与自定义编解码器 MessageCodecSharable一起配置参数】
                    ch.pipeline().addLast(LOGGING_HANDLER);            // 日志
                    ch.pipeline().addLast(MESSAGE_CODEC);              // 出站入站的 自定义编解码器 【 解析消息类型 】
                    // simple处理器 【针对性的对登录进行处理】 【流水线 会向上执行出站Handler,  到 ProcotolFrameDecoder(入站停止)】
                    ch.pipeline().addLast(new SimpleChannelInboundHandler<LoginRequestMessage>() {
                        @Override
                        protected void channelRead0(ChannelHandlerContext ctx, LoginRequestMessage message) throws Exception {
                            String username = message.getUsername();
                            String password = message.getPassword();
                            // todo check username and password
                            boolean checkResult = RandomUtil.randomBoolean();
                            LoginResponseMessage resp = null;
                            if (checkResult) {
                                resp = new LoginResponseMessage(true, "登录成功");
                            } else {
                                resp = new LoginResponseMessage(false, "用户名或密码不正确");

                            }
                            ctx.writeAndFlush(resp);
                        }
                    });
                }
            });

            ChannelFuture channelFuture = bs.bind(8081).sync();
            channelFuture.channel().closeFuture().sync();

        } catch (InterruptedException e) {

            log.error("server error", e);

        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }


}
