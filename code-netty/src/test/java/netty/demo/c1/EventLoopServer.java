package netty.demo.c1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.DefaultEventLoopGroup;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;

/**
 * @author chengsiyi
 * @date 2022/4/3 16:13
 */
public class EventLoopServer {
    public static final Logger logger = LoggerFactory.getLogger(EventLoopServer.class);

    public static void main(String[] args) {

        EventLoopGroup group = new DefaultEventLoopGroup();
        // parent 只负责 ServerSocketChannel 上的 accept
        EventLoopGroup parent = new NioEventLoopGroup();
        // child 只负责 SocketChannel 读写
        EventLoopGroup child = new NioEventLoopGroup(2);
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(parent, child)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
                        ch.pipeline().addLast("handler1", new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) {
                                ByteBuf buf = (ByteBuf) msg;
                                logger.info(buf.toString(Charset.defaultCharset()));
                                ctx.fireChannelRead(msg);
                            }

                        });
                        ch.pipeline().addLast(group, "handle2", new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) {
                                ByteBuf buf = (ByteBuf) msg;
                                logger.info(buf.toString(Charset.defaultCharset()));
                            }
                        });
                    }
                }).bind(8080);
    }
}
