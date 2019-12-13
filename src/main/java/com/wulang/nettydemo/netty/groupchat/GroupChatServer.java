package com.wulang.nettydemo.netty.groupchat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * 此类为编写的群聊系统，作为提升可以自己做一个私聊系统，实现方式类似
 *
 * @author wulang
 * @create 2019/12/12/20:48
 */
public class GroupChatServer {
    //监听端口
    private int port;
    public GroupChatServer(int port){
        this.port = port;
    }

    public void run() throws InterruptedException {
        NioEventLoopGroup boosGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(boosGroup,workGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,128)
                    .childOption(ChannelOption.SO_KEEPALIVE,true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            //获取到 pipeline
                            ChannelPipeline pipeline = ch.pipeline();
                            //向 pipeline 加入一个解码器
                            pipeline.addLast("decode",new StringDecoder());
                            //向 pipeline 加入一个解码器
                            pipeline.addLast("encode",new StringEncoder());
                            //加入自己业务处理的 handler
                            pipeline.addLast(new GroupChatServerHandler());
                        }
                    });
            System.out.println("netty 服务器启动......");
            ChannelFuture channelFuture = b.bind(7000).sync();
            //监听关闭事件
            channelFuture.channel().closeFuture().sync();
        }finally {
            boosGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new GroupChatServer(7000).run();
    }
}
