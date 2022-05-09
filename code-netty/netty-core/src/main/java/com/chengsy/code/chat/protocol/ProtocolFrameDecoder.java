package com.chengsy.code.chat.protocol;

import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * @author chengsiyi
 * @date 2022/4/8 11:01
 */
public class ProtocolFrameDecoder extends LengthFieldBasedFrameDecoder {

    public ProtocolFrameDecoder() {
        this(1024, 12, 4, 0, 0);
    }

    public ProtocolFrameDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength, int lengthAdjustment, int initialBytesToStrip) {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength, lengthAdjustment, initialBytesToStrip);
    }
}
