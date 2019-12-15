package com.wulang.nettydemo.netty.dubborpc.provider;

import com.wulang.nettydemo.netty.dubborpc.netty.NettyServer;

/**
 * ServerBootstrap 会启动一个服务提供者，就是 NettyServer
 *
 * @author wulang
 * @create 2019/12/15/18:00
 */
public class ServerBootstrap {
    public static void main(String[] args) {

        //代码代填..
        NettyServer.startServer("127.0.0.1", 7000);
    }
}
