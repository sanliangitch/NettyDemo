package com.wulang.nettydemo.netty.dubborpc.publicinterface;

/**
 * 这个是接口，是服务提供方和 服务消费方都需要
 *
 * @author wulang
 * @create 2019/12/15/17:59
 */
public interface HelloService {
    String hello(String mes);
}
