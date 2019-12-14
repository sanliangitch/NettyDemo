package com.wulang.nettydemo.netty.inboundhandlerandoutboundhandler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * @author wulang
 * @create 2019/12/14/17:03
 */
public class MyServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        /**
         * initChannel 最先被调用激活
         * decode 解码被调用
         * channelRead0 做接收到数据之后处理的业务逻辑
         * encode 会送客户端数据，编码器被调用
         */
        //入站的handler进行解码 MyByteToLongDecoder
//        pipeline.addLast(new MyByteToLongDecoder());
        pipeline.addLast(new MyByteToLongReplayingDecoder());
        //出站的handler进行编码
        pipeline.addLast(new MyLongToByteEncoder());
        //自定义的handler 处理业务逻辑
        pipeline.addLast(new MyServerHandler());
        System.out.println("xx");
    }
}
