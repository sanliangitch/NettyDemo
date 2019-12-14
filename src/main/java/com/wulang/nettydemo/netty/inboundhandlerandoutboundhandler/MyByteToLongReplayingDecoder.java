package com.wulang.nettydemo.netty.inboundhandlerandoutboundhandler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 *  解码器-ReplayingDecoder
 *
 * 1️⃣并不是所有的 ByteBuf 操作都被支持，如果调用了一个不被支持的方法，
 *    将会抛出一个 UnsupportedOperationException。
 * 2️⃣ReplayingDecoder 在某些情况下可能稍慢于 ByteToMessageDecoder，
 *    例如网络缓慢并且消息格式复杂时，消息会被拆成了多个碎片，速度变慢
 *
 * @author wulang
 * @create 2019/12/14/18:06
 */
public class MyByteToLongReplayingDecoder extends ReplayingDecoder<Void> {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("MyByteToLongReplayingDecoder 被调用");
        //在 ReplayingDecoder 不需要判断数据是否足够读取，内部会进行处理判断
        out.add(in.readLong());
    }
}
