package netty.demo.c1;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.Scanner;

/**
 * @author chengsiyi
 * @date 2022/4/3 16:13
 */
public class EventLoopClient {
    public static final Logger logger = LoggerFactory.getLogger(EventLoopClient.class);
    public static void main(String[] args) throws Exception {
        NioEventLoopGroup group = new NioEventLoopGroup();
        ChannelFuture channelFuture = new Bootstrap().group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
                        ch.pipeline().addLast(new StringEncoder());
                    }
                }).connect(new InetSocketAddress("localhost", 8080));

        Channel channel = channelFuture.sync().channel();
        logger.debug("{}",channel);
        new Thread(()->{
            Scanner scanner = new Scanner(System.in);
            while (true){
                String line = scanner.nextLine();
                if ("q".equals(line)){
                    channel.close();
                    break;
                }
                channel.writeAndFlush(line);
            }
        },"input").start();

        ChannelFuture closeFuture = channel.closeFuture();
        logger.info("waiting close...");
        closeFuture.sync();
        logger.info("处理关闭之后的操作");
        group.shutdownGracefully().sync();

    }
}
