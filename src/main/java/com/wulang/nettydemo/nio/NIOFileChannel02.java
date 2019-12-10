package com.wulang.nettydemo.nio;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author wulang
 * @create 2019/12/3/20:43
 */
public class NIOFileChannel02 {
    public static void main(String[] args) throws Exception {
        File file = new File("d:\\file01.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        FileChannel fileChannel = fileInputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate((int)file.length());
        fileChannel.read(byteBuffer);
//        byteBuffer.flip();
        System.out.println(new String(byteBuffer.array()));
        fileInputStream.close();
    }
}
