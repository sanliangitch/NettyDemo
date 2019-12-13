package com.wulang.nettydemo.netty.groupchat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author wulang
 * @create 2019/12/12/21:48
 */
public class GroupChatClientHandler extends SimpleChannelInboundHandler<String> {
    /**
     * 读取数据
     * @param channelHandlerContext
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String msg) throws Exception {
        System.out.println(msg.trim());
    }
}
