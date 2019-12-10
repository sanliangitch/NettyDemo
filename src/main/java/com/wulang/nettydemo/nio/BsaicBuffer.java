package com.wulang.nettydemo.nio;

import java.nio.IntBuffer;

/**
 * @author wulang
 * @create 2019/12/2/21:26
 */
public class BsaicBuffer {
    public static void main(String[] args) {
        IntBuffer intBuffer = IntBuffer.allocate(5);
        intBuffer.put(10);
        intBuffer.put(11);
        intBuffer.put(12);
        intBuffer.put(13);
        intBuffer.put(14);
        //将buff转换，读写切换
        intBuffer.flip();
        //从下标为1的位置开始读取
        intBuffer.position(1);
        //读取到3的位置
        intBuffer.limit(3);
        while (intBuffer.hasRemaining()){
            System.out.println(intBuffer.get());
        }
    }
}
