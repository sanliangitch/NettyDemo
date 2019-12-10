package com.wulang.nettydemo.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wulang
 * @create 2019/12/2/20:48
 */
public class BioServer {
    public static void main(String[] args) throws Exception {
        //1、创建一个线程池
        //2、如果有客户端连接，就创建一个线程和他通信，单独写一个方法

        ExecutorService executorService = Executors.newCachedThreadPool();
        //创建serverSocker
        ServerSocket serverSocket = new ServerSocket(6666);
        System.out.println("服务器启动");
        while (true){
           final Socket socket = serverSocket.accept();
            System.out.println("连接到一个客户端");
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    //可以和客户端通信
                    handler(socket);
                }
            });
        }
    }

    public static void handler(Socket socket){
        try {
            System.out.println("连接的线程为："+ Thread.currentThread().getId()+",name:"+Thread.currentThread().getName());
            byte[] bytes = new byte[1024];
            InputStream inputStream = socket.getInputStream();
            while (true){
                int read = inputStream.read(bytes);
                if (read != -1){
                    System.out.println("客户端发送数据"+ new String(bytes,0,read));
                }else {
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
