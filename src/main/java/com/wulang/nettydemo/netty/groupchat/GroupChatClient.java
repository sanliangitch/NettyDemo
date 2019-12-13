package com.wulang.nettydemo.netty.groupchat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Scanner;

/**
 * @author wulang
 * @create 2019/12/12/21:34
 */
public class GroupChatClient {
    //属性
    private final String host;
    private final int port;
    public GroupChatClient(String host,int port){
        this.host = host;
        this.port = port;
    }

    private void run() throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();;
        try {
            //做相关设置
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            //得到 pipeline
                            ChannelPipeline pipeline = ch.pipeline();
                            //编码器
                            pipeline.addLast("decode",new StringDecoder());
                            //解码器
                            pipeline.addLast("encode",new StringEncoder());
                            //自己的定义的handler
                            pipeline.addLast(new GroupChatClientHandler());
                        }
                    });
            ChannelFuture channelFuture = bootstrap.connect(host, port).sync();
            Channel channel = channelFuture.channel();
            System.out.println("----------"+channel.localAddress()+"----------");
            //客户端需要输入信息，创建一个扫描器
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()){
                String msg = scanner.nextLine();
                //通过 channel 发送到服务器端
                channel.writeAndFlush(msg+"\r\n");
            }
        }finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new GroupChatClient("127.0.0.1",7000).run();
    }
}
