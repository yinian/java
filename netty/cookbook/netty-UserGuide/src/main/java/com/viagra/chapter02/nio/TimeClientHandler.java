package com.viagra.chapter02.nio;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class TimeClientHandler implements Runnable {

    private String host;
    private int port;

    private Selector selector;
    private SocketChannel socketChannel;

    private volatile boolean stop;
/*
30 ～ 42 行 构 造 函 数 用 于 初 始 化 NIO 的 多 路 复 用 器 和 SocketChannel 对 象 。 需 要
注 意 的 是 ， 创 建 SocketChannel 之 后 ， 需 要 将 其 设 置 为 异 步 非 阻 塞 模 式 。我 们 可 以 设 置 SocketChanne 1 的 TCP 参 数 ， 例 如 接 收 和 发 送 的 TCP 缓 冲 区
大 小 。

 */
    public TimeClientHandler(String host, int port) {
        this.host = host == null ? "127.0.0.1" : host;
        this.port = port;

        try {
            selector = Selector.open();
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }


    @Override
    public void run() {
/*
56 ~ 61行 用 于 发 送 连 接 请 求 ， 作 为 示 例 ， 连 接 是 成 功 的 ， 所 以 不 需 要 做 重 连 操 作 ，
因 此 将 其 放 到 循 环 之 前 。 下 面 我 们 具 体 看 看 doConnect 的 实 现 ， 代 码 跳 到 第 1 16 ～ 123 行 ，
首 先 对 SocketChannel 的 connect() 操 作 进 行 判 断 。 如 果 连 接 成 功 ， 则 将 SocketChannel 注 册
到 多 路 复 用 器 Selector 上 ， 注 册 SelectionKey.OP READ ； 如 果 没 有 直 接 连 接 成 功 ， 则 说 明
服 务 端 没 有 返 回 TCP 握 手 应 答 消 息 ， 但 这 并 不 代 表 连 接 失 败 。 我 们 需 要 将 SocketChanneI
注 册 到 多 路 复 用 器 Selector 上 ， 注 册 SelectionKey.OP_CONNECT ， 当 服 务 端 返 回 TCP
syn-ack 消 息 后 ， Selector 就 能 够 轮 询 到 这 个 SocketChannel 处 于 连 接 就 绪 状 态 。

 */
        try {
            doConnect();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
/*
67 ～ 92 行 在 循 环 体 中 轮 询 多 路 复 用 器 Selector 。 当 有 就 绪 的 Channel 时 ，
 执 行 handlelnput(key) 方 法 。 下 面 我 们 就 对 handlelnput 方 法 进 行 分 析 。

 */
        while (!stop){

            try {
                selector.select(1000);
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> it = selectedKeys.iterator();
                SelectionKey key = null;
                while (it.hasNext()){
                    key = it.next();
                    it.remove();

                    try {
                        handleInput(key);
                    } catch (Exception e) {
                        if (key != null) {
                            key.cancel();
                            if (key.channel() != null)
                                key.channel().close();
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }

        // 多路复用器关闭后，所有注册在上面的Channel和Pipe等资源都会被自动去注册并关闭，所以不需要重复释放资源
        if (selector != null)
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


    }
/*
跳 到 第 113 行 ， 我 们 首 先 对 SelectionKey 进 行 判 断 ， 看 它 处 于 什 么 状 态 。 如 果 是
处 于 连 接 状 态 ， 说 明 服 务 端 己 经 返 回 ACK 应 答 消 息 。 这 时 我 们 需 要 对 连 接 结 果 进 行 判 断 ，
调 用 SocketChannel 的 finishConnect() 方 法 。 如 果 返 回 值 为 true ， 说 明 客 户 端 连 接 成 功 ； 如
果 返 回 值 为 false 或 者 直 接 抛 出 IOException ， 说 明 连 接 失 败 。 在 本 例 程 中 ， 返 回 值 为 true ，
说 明 连 接 成 功 。 将 SocketChannel 注 册 到 多 路 复 用 器 上 ， 注 册 SelectionKey.OP_READ 操 作
位 ， 监 听 网 络 读 操 作 ， 然 后 发 送 请 求 消 息 给 服 务 端 。

 */
    private void handleInput(SelectionKey key) throws IOException {
        if (key.isValid()) {
            // 判断是否连接成功
            SocketChannel sc = (SocketChannel) key.channel();
            if (key.isConnectable()) {
                if (sc.finishConnect()) {

                    sc.register(selector, SelectionKey.OP_READ);
                    /*
                    下 面 我 们 对 dowrite(sc) 进 行 分 析 。 代 码 跳 到 第 125 行 ， 我 们 构 造 请 求 消 息 体 ， 然 后 对
其 编 码 ， 写 入 到 发 送 缓 冲 区 中 ， 最 后 调 用 SocketChannel 的 write 方 法 进 行 发 送 。 由 于 发 送
是 异 步 的 ， 所 以 会 存 在 “ 半 包 写 ” 问 题 ， 此 处 不 再 赘 述 。 最 后 通 过 hasRemaining() 方 法 对
发 送 结 果 进 行 判 断 ， 如 果 缓 冲 区 中 的 消 息 全 部 发 送 完 成 ， 打 印 "Send order 2 server succeed."
                     */
                    doWrite(sc);
                } else {
                    System.exit(1);
                }
            }
/*
139~160 行 我 们 继 续 分 析 客 户 端 是如何读 取 时 间 服 务 器 应 答 消 息 的 。 如
果 客 户 端 接 收 到 了 服 务 端 的 应 答 消 息 ， 则 SocketChannel 是 可 读 的 ， 由 于 无 法 事 先 判 断 应
答 码 流 的 大 小 ， 我 们 就 预 分 配 IMB 的 接 收 缓 冲 区 用 于 读 取 应 答 消 息 ， 调 用 SocketChanneI
的 read() 方 法 进 行 异 步 读 取 操 作 。 由 于 是 异 步 操 作 ， 所 以 必 须 对 读 取 的 结 果 进 行 判 断 ， 这
部 分 的 处 理 逻 辑 己 经 在 2 ， 3 ， 3 小 节 详 细 介 绍 过 ， 此 处 不 再 赘 述 。 如 果 读 取 到 了 消 息 ， 则 对
 */
            if (key.isReadable()) {
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                int readBytes = sc.read(readBuffer);

                if (readBytes > 0){
                    readBuffer.flip();
                    byte[] bytes = new byte[readBuffer.remaining()];
                    readBuffer.get(bytes);
                    String body = new String(bytes, "UTF-8");
                    System.out.println("Now is : " + body);
                    this.stop = true;
                }else if(readBytes < 0){
                    // 对端链路关闭
                    key.cancel();
                    sc.close();
                }else
                    ;

            }
        }

    }


    private void doConnect() throws IOException {
        // 如果直接连接成功，则注册到多路复用器上，发送请求消息，读应答
        if (socketChannel.connect(new InetSocketAddress(host, port))) {
            socketChannel.register(selector, SelectionKey.OP_READ);
            doWrite(socketChannel);
        } else
            socketChannel.register(selector, SelectionKey.OP_CONNECT);

    }


    private void doWrite(SocketChannel sc) throws IOException {
        byte[] req = "QUERY TIME ORDER".getBytes();
        ByteBuffer writeBuffer = ByteBuffer.allocate(req.length);
        writeBuffer.put(req);
        writeBuffer.flip();
        sc.write(writeBuffer);
        if (!writeBuffer.hasRemaining())
            System.out.println("Send order 2 server succeed.");
    }

}
