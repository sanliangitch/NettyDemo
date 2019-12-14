package com.wulang.nettydemo.netty.protocoltcp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;

public class MyClientHandler extends SimpleChannelInboundHandler<MessageProtocol> {

    private int count;
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //使用客户端发送10条数据 测试代码 编号
        for (int i = 0; i < 10; i++) {
            String message = "测试代码" + i;
            byte[] content = message.getBytes(Charset.forName("utf-8"));
            int length = message.getBytes(Charset.forName("utf-8")).length;
            MessageProtocol messageProtocol = new MessageProtocol();
            messageProtocol.setLen(length);
            messageProtocol.setContent(content);
            ctx.writeAndFlush(messageProtocol);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageProtocol msg) throws Exception {
        //接收到数据，并处理
        int len = msg.getLen();
        byte[] content = msg.getContent();

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("服务器回复到信息如下");
        System.out.println("长度=" + len);
        System.out.println("内容=" + new String(content, Charset.forName("utf-8")));
    }
}
