package com.wulang.nettydemo.netty.inboundhandlerandoutboundhandler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * @author wulang
 * @create 2019/12/14/17:11
 */
public class MyClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        /**
         *
         * initChannel 最先执行不解释
         * channelActive  发送数据
         * encode  编码方法
         *
         */
        //加入一个出站的handler 对数据进行一个编码
        pipeline.addLast(new MyLongToByteEncoder());

        //这时一个入站的解码器(入站handler )
//        pipeline.addLast(new MyByteToLongDecoder());
        pipeline.addLast(new MyByteToLongReplayingDecoder());
        //加入一个自定义的handler ， 处理业务
        pipeline.addLast(new MyClientHandler());
    }
}
