package com.wulang.nettydemo.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

/**
 * @author wulang
 * @create 2019/12/3/21:08
 */
public class NIOFileChannel04 {
    public static void main(String[] args) throws Exception {
        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\Administrator\\Desktop\\QQ图片20190509135254.jpg");
        FileOutputStream fileOutputStream = new FileOutputStream("a.jpg");
        FileChannel inputStreamChannel = fileInputStream.getChannel();
        FileChannel outputStreamChannel = fileOutputStream.getChannel();

        outputStreamChannel.transferFrom(inputStreamChannel,0,inputStreamChannel.size());

        outputStreamChannel.close();
        inputStreamChannel.close();

        fileInputStream.close();
        fileOutputStream.close();
    }
}
