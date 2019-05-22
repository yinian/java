package com.viagra.chapter02.aio;

import java.util.concurrent.CountDownLatch;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;

public class AsyncTimeServerHandler implements Runnable{

    private int port;

    CountDownLatch latch;
    AsynchronousServerSocketChannel asynchronousServerSocketChannel;
/*
在 构 造 方 法 中 ，首 先 创 建 一 个 异 步 的 服 务 端 通 道 AsynchronousServerSocketChannel ，
然 后 调 用 它 的 bind 方 法绑 定 监 听 端 口 。 如 果 端 口 合 法 且 没 被 占 用 ，
则 绑 定 成 功 ， 打 印 启 动 成 功 提 示 到 控 制 台 。
 */
    public AsyncTimeServerHandler(int port) {
        this.port = port;

        try {
            asynchronousServerSocketChannel = AsynchronousServerSocketChannel.open();
            asynchronousServerSocketChannel.bind(new InetSocketAddress(port));
            System.out.println("The time server is start in port : " + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
/*
38行: 初 始 化 CountDownLatch 对 象 ， 它 的 作 用 是 在 完 成 一 组正 在 执 行 的 操 作 之 前 ，
允 许 当 前 的 线 程 一 直 阻 塞 。 在 本 例 程 中 ， 我 们 让 线 程 在 此 阻 塞 ， 防止 服 务 端 执 行 完 成 退 出 。
 */
        latch = new CountDownLatch(1);
        /*
        第 43 行 用 于 接 收 客 户 端 的 连 接 ， 由 于 是 异 步 操 作 ， 我 们 可 以 传 递 一 个 CompletionHandler
<AsynchronousSocketChanneI,? super A> 类 型 的 hand ler 实 例 接 收 accept 操 作 成 功 的 通 知 消息
         */
        doAccept();

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void doAccept(){
        asynchronousServerSocketChannel
                .accept(this,new AcceptCompletionHandler());
    }
}
