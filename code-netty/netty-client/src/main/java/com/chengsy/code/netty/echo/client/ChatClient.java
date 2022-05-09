package com.chengsy.code.netty.echo.client;

import com.chengsy.code.chat.message.ChatRequestMessage;
import com.chengsy.code.chat.message.GroupChatRequestMessage;
import com.chengsy.code.chat.message.GroupCreateRequestMessage;
import com.chengsy.code.chat.message.GroupJoinRequestMessage;
import com.chengsy.code.chat.message.GroupMembersRequestMessage;
import com.chengsy.code.chat.message.GroupQuitRequestMessage;
import com.chengsy.code.chat.message.LoginRequestMessage;
import com.chengsy.code.chat.message.LoginResponseMessage;
import com.chengsy.code.chat.protocol.MessageCodecSharable;
import com.chengsy.code.chat.protocol.ProtocolFrameDecoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author chengsiyi
 * @date 2022/4/8 11:07
 */
@Slf4j
public class ChatClient {
    public static void main(String[] args) {
        NioEventLoopGroup group = new NioEventLoopGroup();
        MessageCodecSharable MESSAGE_CODEC = new MessageCodecSharable();
        LoggingHandler LOGGING_HANDLER = new LoggingHandler(LogLevel.INFO);
        CountDownLatch WAIT_FOR_LOGIN = new CountDownLatch(1);
        AtomicBoolean LOGIN = new AtomicBoolean();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.group(group);
            // 在创建Channel时，向ChannelPipeline中添加一个EchoClientHandler实例
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new ProtocolFrameDecoder());
                    ch.pipeline().addLast(LOGGING_HANDLER);
                    ch.pipeline().addLast(MESSAGE_CODEC);
                    ch.pipeline().addLast("client handler", new ChannelInboundHandlerAdapter() {

                        @Override
                        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                            log.debug("msg:{}", msg);
                            if (msg instanceof LoginResponseMessage){
                                if (((LoginResponseMessage) msg).isSuccess()){
                                    LOGIN.set(true);
                                }
                                WAIT_FOR_LOGIN.countDown();
                            }
                        }

                        // 在建立后触发active 事件
                        @Override
                        public void channelActive(ChannelHandlerContext ctx) {
                            // 负责用户在控制台输入，负责向服务器发送各种消息
                            new Thread(() -> {
                                Scanner scanner = new Scanner(System.in);
                                System.out.println("请输入用户名：");
                                String username = scanner.nextLine();
                                System.out.println("请输入密码：");
                                String password = scanner.nextLine();
                                LoginRequestMessage message = new LoginRequestMessage(username, password);
                                ctx.writeAndFlush(message);
                                System.out.println("登录中...");
                                try {
                                    WAIT_FOR_LOGIN.await();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                // 登录失败
                                if (!LOGIN.get()){
                                    ctx.channel().close();
                                    return;
                                }
                                while (true){
                                    System.out.println("============ 功能菜单 ============");
                                    System.out.println("send [username] [content]");
                                    System.out.println("gsend [group name] [content]");
                                    System.out.println("gcreate [group name] [m1,m2,m3...]");
                                    System.out.println("gmembers [group name]");
                                    System.out.println("gjoin [group name]");
                                    System.out.println("gquit [group name]");
                                    System.out.println("quit");
                                    System.out.println("==================================");
                                    String command = scanner.nextLine();
                                    final String[] s = command.split(" ");
                                    switch (s[0])
                                    {
                                        case "send": // 发送消息
                                            ctx.writeAndFlush(new ChatRequestMessage(username, s[1], s[2]));
                                            break;
                                        case "gsend": // 群里 发送消息
                                            ctx.writeAndFlush(new GroupChatRequestMessage(username, s[1], s[2]));
                                            break;
                                        case "gcreate": // 创建群
                                            final Set<String> set = new HashSet(Arrays.asList(s[2].split(",")));
                                            set.add(username);
                                            ctx.writeAndFlush(new GroupCreateRequestMessage(s[1], set));
                                            break;
                                        case "gmembers": // 查看群列表
                                            ctx.writeAndFlush(new GroupMembersRequestMessage(s[1]));
                                            break;
                                        case "gjoin":
                                            ctx.writeAndFlush(new GroupJoinRequestMessage(username, s[1]));
                                            break;
                                        case "gquit":
                                            ctx.writeAndFlush(new GroupQuitRequestMessage(username, s[1]));
                                            break;
                                        case "quit":
                                            ctx.channel().close(); // 触发 【channel.closeFuture().sync(); 向下运行】
                                            return;
                                    }
                                }

                            }, "system in").start();
                        }
                    });
                }
            });
            // 连接到远程节点，阻塞等待直到连接完成
            Channel channel = bootstrap.connect("localhost", 8080).sync().channel();
            // 阻塞，直到Channel关闭
            channel.closeFuture().sync();
        } catch (Exception e) {
            log.error("client error", e);
        } finally {
            // 关闭线程池并且释放所有的资源
            group.shutdownGracefully();
        }
    }
}
