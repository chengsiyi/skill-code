package com.chengsy.code.chat.protocol;

import com.chengsy.code.chat.message.LoginRequestMessage;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author chengsiyi
 * @date 2022/4/8 11:53
 */
public class MessageCodecShareableTest {
    public static void main(String[] args) throws Exception {

        LoginRequestMessage message = new LoginRequestMessage("zhangsan", "23asdas12");

        EmbeddedChannel channel = new EmbeddedChannel(
                new ProtocolFrameDecoder(),
                new LoggingHandler(),
                new MessageCodecSharable()
        );
//        channel.writeOutbound(message);
        ByteBuf buf = ByteBufAllocator.DEFAULT.buffer();

        new MessageCodec().encode(null, message, buf);
        ByteBuf s1 = buf.slice(0, 100);
        ByteBuf s2 = buf.slice(100, buf.readableBytes() - 100);
        s1.retain();
        channel.writeInbound(s1);
        channel.writeInbound(s2);

    }
}
