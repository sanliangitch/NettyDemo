# NettyDemo
自己编写NIO/BIO/Netty等代码编写，后期补上自己实现的RPC框架

## Netty问题整理
* 服务端启动路径是什么
* 默认情况下，Netty服务器起多少线程？何时启动？
* Netty如何解决jdk空轮询的bug？
* Netty如何保证异步串行无锁化？
* Netty在哪里检测有新连接接入的？
* 新连接是怎样注册到NioEventLoop线程的？
* Netty如何判断ChannelHandler类型的？
* 对于ChannelHandler的添加应该遵循什么顺序？
* 用户手动触发事件传播，不同的触发方式有什么样的区别？
* 内存的类别有哪些？
* 如何减少多线程内存分配之间的竞争？
* 不同大小的内存是如何进行分配的？
* 解码器抽象的解码过程
* Netty里面有哪些拆箱即用的解码器
* Netty两大性能优化工具类：FastThreadLocal、Recycler
* Netty高性能并发调优