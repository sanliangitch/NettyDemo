package com.wulang.nettydemo.netty.inboundhandlerandoutboundhandler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author wulang
 * @create 2019/12/14/17:14
 */
public class MyLongToByteEncoder extends MessageToByteEncoder<Long> {
    //编码方法
    @Override
    protected void encode(ChannelHandlerContext ctx, Long msg, ByteBuf out) throws Exception {
        System.out.println("MyLongToByteEncoder 编码 被调用");
        System.out.println("msg=" + msg);
        out.writeLong(msg);
    }
}
