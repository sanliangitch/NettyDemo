package com.wulang.nettydemo.nio;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author wulang
 * @create 2019/12/3/20:28
 */
public class NIOFileChannel01 {
    public static void main(String[] args) throws Exception {
        String str = "hello,世界!";
        //创建一个输出流->channel
        FileOutputStream fileOutputStream = new FileOutputStream("d:\\file01.txt");
        //通过 fileOutputStream 获取对应的 FileChannel
        //真是类型是 FileChannelImpl
        FileChannel fileChannel = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put(str.getBytes());
        //对 byteBuffer 进行翻转，先是写 换成读
        byteBuffer.flip();
        //将数据写到 fileChannel
        fileChannel.write(byteBuffer);
        fileChannel.close();
    }
}
