package com.wulang.nettydemo.netty.groupchat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wulang
 * @create 2019/12/12/21:02
 */
public class GroupChatServerHandler extends SimpleChannelInboundHandler<String> {

//    public static List<Channel> channels = new ArrayList<Channel>();

    //定义一个 Channel 组，管理所有的 Channel
    // GlobalEventExecutor.INSTANCE 是全局的事件执行器，是一个单例
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * handlerAdded 表示连接建立后，一旦连接，第一个被执行
     * 将当前 Channel 加入到 ChannelGroup
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        //将该客户端加入聊天的信息推送给其他在线的客户端
        /**
         * 该方法会将 channelGroup 中所有的 channel 遍历，并发送消息
         * 不需要自己遍历
         */
        channelGroup.writeAndFlush("[客户端]" + channel.remoteAddress() + "，时间为" + sdf.format(new Date()) +",加入聊天室\n");
        channelGroup.add(channel);
    }

    /**
     * 表示 channel 处于活动状态，提示 xx上线
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + "上线了~");
    }

    /**
     * 表示 channel 处于不活动状态，提示 xx离线
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + "离线了~");
    }

    /**
     * 断开连接，将 xx 离开信息推送给当前在线的客户
     *
     * 一旦执行这个方法，会自动 channelGroup 中删除这个 channel
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("[客户端]" + channel.remoteAddress() + "离开了\n");
        System.out.println("当前channelGroup的大小：" + channelGroup.size());
    }

    /**
     * 读取数据
     * @param channelHandlerContext
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String msg) throws Exception {
        //获取到当前channel
        Channel channel = channelHandlerContext.channel();
        //遍历 channelGroup 根据不同的情况回送不同的消息--重点是排除自己
        channelGroup.forEach(ch -> {
            if (channel != ch){
                //不是当前的channel，转发消息
                ch.writeAndFlush("[客户]" + channel.remoteAddress()+ "，时间为" + sdf.format(new Date()) +"发送的消息为" + msg + "\n");
            }else {
                ch.writeAndFlush("[自己]" + sdf.format(new Date()) + "发送了消息" + msg + "\n");
            }
        });
    }

    /**
     * 遇到异常，关闭通道
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //关闭通道
        ctx.close();
    }
}
