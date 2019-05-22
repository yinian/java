package com.viagra.chapter02.aio;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

public class AsyncTimeClientHandler implements
        CompletionHandler<Void, AsyncTimeClientHandler>, Runnable {

    private AsynchronousSocketChannel client;
    private String host;
    private int port;
    private CountDownLatch latch;
/*
首 先 通 过 AsynchronousSocketChannel 的 open 方 法 创 建 一
个 新 的 AsynchronousSocketChannel 对 象 。

 */
    public AsyncTimeClientHandler(String host, int port) {
        this.host = host;
        this.port = port;
        try {
            client = AsynchronousSocketChannel.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
//创 建 CountDown Latch 进 行等 待 ， 防 止 异 步 操 作 没 有 执 行 完 成 线 程 就 退 出 。
        latch = new CountDownLatch(1);
        //通 过 “ connect 方 法 发 起 异 步 操 作
        client.connect(new InetSocketAddress(host, port), this, this);


        try {
            latch.await();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void completed(Void result,
                          AsyncTimeClientHandler attachment) {
/*
 我 们
创 建 请 求 消 息 体 ， 对 其 进 行 编 码 ， 然 后 复 制 到 发 送 缓 冲 区 writeBuffer 中 ， 调 用 Asynchronous
SocketChannel 的 write 方 法 进 行 异 步 写 。 与 服 务 端 类 似 ， 我 们 可 以 实 现 CompletionHandler
<lnteger ， ByteBuffer > 接 口 用 于 写 操 作 完 成 后 的 回 调 。 如 果 发 送 缓 冲 区
中 仍 有 尚 未 发 送 的 字 节 ， 将 继 续 异 步 发 送 ， 如 果 己 经 发 送 完 成 ， 则 执 行 异 步 读 取 操 作 。

 */
        byte[] req = "QUERY TIME ORDER".getBytes();
        ByteBuffer writeBuffer = ByteBuffer.allocate(req.length);
        writeBuffer.put(req);
        writeBuffer.flip();
        client.write(writeBuffer, writeBuffer,
                new CompletionHandler<Integer, ByteBuffer>() {
                    @Override
                    public void completed(Integer result,
                                          ByteBuffer buffer) {

                        if (buffer.hasRemaining()) {
                            client.write(buffer, buffer, this);
                        } else {
                            /*
                            客 户 端 异 步 读 取 时 间 服 务 器 服 务 端 应 答 消 息 的 处 理 逻 辑 。
                            调 用 AsynchronousSocketchannel 的 read 方法异 步 读 取 服 务 端 的 响 应 消 息 。
                            由 于 read操 作 是 异 步 的 ，
                            所 以 我 们 通 过 内 部 匿 名 类 实 现 CompletionHandler<Integer ， ByteBuffer> 接
口 ， 当 读 取 完 成 被 JDK 回 调 时 ， 构 造 应 答 消 息 。 CompletionHandler 的
ByteBuffer 中 读 取 应 答 消 息 ， 然 后 打 印 结 果 。

                             */
                            ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                            client.read(readBuffer, readBuffer,
                                    new CompletionHandler<Integer, ByteBuffer>() {
                                        @Override
                                        public void completed(Integer result, ByteBuffer buffer1) {

                                            buffer1.flip();
                                            byte[] bytes = new byte[buffer.remaining()];
                                            buffer.get(bytes);
                                            String body;

                                            try {
                                                body = new String(bytes, "UTF-8");
                                                System.out.println("Now is : "
                                                        + body);
                                                latch.countDown();
                                            } catch (UnsupportedEncodingException e) {
                                                e.printStackTrace();
                                            }

                                        }
/*
当 读 取 发 生 异 常 时 ， 关 闭 链 路 ， 同 时 调 用 CountDownLatch 的
countDown 方 法 计 AsyncTimeClientHandler 线 程 执 行 完 毕 ， 客 户 端 退 出 执 行 。
 */
                                        @Override
                                        public void failed(Throwable exc, ByteBuffer buffer1) {
                                            try {
                                                client.close();
                                                latch.countDown();
                                            } catch (IOException e) {
                                                // ingnore on close
                                            }
                                        }
                                    });
                        }


                    }

                    @Override
                    public void failed(Throwable exc, ByteBuffer buffer) {
                        try {
                            client.close();
                            latch.countDown();
                        } catch (IOException e) {
                            // ingnore on close
                        }


                    }
                });

    }

    @Override
    public void failed(Throwable exc, AsyncTimeClientHandler attachment) {
        exc.printStackTrace();
        try {
            client.close();
            latch.countDown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
