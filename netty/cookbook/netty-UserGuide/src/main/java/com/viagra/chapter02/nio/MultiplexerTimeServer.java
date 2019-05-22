package com.viagra.chapter02.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/*
 它 是 个独 立 的 线 程 ，负 责 轮 询 多 路 复 用 器 SeIctor ， 可 以 处 理 多 个 客 户 端 的 并 发 接 入 。
 */
public class MultiplexerTimeServer implements Runnable {

    private Selector selector;

    private ServerSocketChannel serverChannel;

    private volatile boolean stop;

    /*
     在 构 造 方 法 中 进 行 资 源 初 始 化 。 创 建 多 路 复 用 器 Selector 、
ServerSocketChanneI ， 对 Channel 和 TCP 参 数 进 行 配 置 。
例 如 ， 将 ServerSocketChanneI 设置 为 异 步 非 阻 塞 模 式 ，
它 的 backlog 设 为 1024 。 系 统 资 源 初 始 化 成 功 后 ， 将 ServerSocketChannel 注 册 到 Selector ，
监 听 SelectionKey.OP_ACCEPT 操 作 位 。 如 果 资 源 初 始 化 失 败 （ 例如 端 口 被 占 用 ） ， 则 退 出 。
     */
    public MultiplexerTimeServer(int port) {

        try {
            selector = Selector.open();
            serverChannel = ServerSocketChannel.open();
            serverChannel.configureBlocking(false);
            serverChannel.socket().bind(new InetSocketAddress(port), 1024);
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("The time server is tart in port : " + port);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }


    }

    public void stop() {
        this.stop = true;
    }
/*
在 线 程 的 run 方 法 的 while 循 环 体 中 循 环 遍 历 selector ， 它 的 休 眠 时 间为 1 s 。
无 论 是 否 有 读 写 等 事 件 发 生 ， selector 每 隔 1 s 都 被 唤 醒 一 次 。 selector 也 提 供 了
个 无 参 的 select 方 法 ： 当 有 处 于 就 绪 状 态 的 Channel 时 ， selector 将 返 回 该 Channel 的
SelectionKey 集 合 。 通 过 对 就 绪 状 态 的 Channel 集 合 进 行 迭 代 ，可 以 进 行 网 络 的 异 步 读 写
操 作 。
 */
    @Override
    public void run() {

        while (!stop) {

            try {
                selector.select(1000);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> it = selectionKeys.iterator();
                SelectionKey key = null;
                while (it.hasNext()) {
                    key = it.next();
                    it.remove();

                    try {
                        handleInput(key);
                    } catch (IOException e) {
                        if (key != null){
                            key.cancel();
                            if (key.channel()!=null)
                                key.channel().close();
                        }
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //	// 多路复用器关闭后，所有注册在上面的Channel和Pipe等资源都会被自动去注册并关闭，所以不需要重复释放资源
        if (selector != null){

            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }

    private void handleInput(SelectionKey key) throws IOException {
        if (key.isValid()){
            /*
            109~117 行 处 理 新 接 入 的 客 户 端 请 求 消 息 ， 根 据 SelectionKey 的 操 作 位 进 行 判 断 即
可 获 知 网 络 事 件 的 类 型 ， 通 过 ServerSocketChannel 的 accept 接 收 客 户 端 的 连 接 请 求 并 创
建 SocketChannel 实 例 。 完 成 上 述 操 作 后 ， 相 当 于 完 成 了 TCP 的 三 次 握 手 ， TCP 物 理 链 路
正 式 建 立 。 注 意 ， 我 们 需 要 将 新 创 建 的 SocketChanneI 设 置 为 异 步 非 阻 塞 ， 同 时 也 可 以 对
其 TCP 参 数 进 行 设 置 ， 例 如 TCP 接 收 和 发 送 缓 冲 区 的 大 小 等 。 但 作 为 入 门 的 例 了 ， 以 上
例 程 没 有 进 行 额 外 的 参 数 设 置 。
             */

            if (key.isAcceptable()){
                // Accept the new connection
                ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                SocketChannel sc = ssc.accept();
                sc.configureBlocking(false);
                // Add the new connection to the selector
                sc.register(selector,SelectionKey.OP_READ);

            }
/*
140 ～ 165 行 用 于 读 取 客 户 端 的 请 求 消 息 。 首 先 创 建 一 个 ByteBufTer,
由 于 我 们 事先 无 法 得 知 客 户 端 发 送 的 码 流 大 小 ， 作 为 例 程 ，
我 们 开 辟 一 个 ] M B 的 缓 冲 区 。 然 后 调 用SocketChannel 的 read 方 法 读 取 请 求 码 流 。
 注 意 ， 由 于 我 们 己 经 将 SocketChanneI 设 置 为 异步 非 阻 塞 模 式 ， 因 此 它 的 read 是 非 阻 塞 的 。
  使 用 返 回 值 进 行 判 断 ， 看 读 取 到 的 字 节 数 ， 返回 值 有 以 下 三 种 可 能 的 结 果 。
1.返 回 值 大 于 0 ： 读 到 了 字 节 ， 对 字 节 进 行 编 解 码 ：
2.0 返 回 值 等 于 0 ： 没 有 读 取 到 字 节 ， 属 于 正 常 场 景 ， 忽 略 ：
3.返 回 值 为 · 1 ： 链 路 己 经 关 闭 ， 需 要 关 闭 SocketChanneI ， 释 放 资 源 。
当 读 取 到 码 流 以 后 ， 进 行 解 码 。 首 先 对 readBuffer 进 行 flip 操 作 ， 它 的 作 用 是 将 缓 冲
区 当 前 的 limit 设 置 为 position ， position 设 置 为 0 ， 用 于 后 续 对 缓 冲 区 的 读 取 操 作 。 然 后 根
据 缓 冲 区 可 读 的 字 节 个 数 创 建 字 节 数 组 ， 调 用 ByteBuffer 的 get 操 作 将 缓 冲 区 可 读 的 字 节
数 组 复 制 到 新 创 建 的 字 节 数 组 中 ， 最 后 调 用 字 符 串 的 构 造 函 数 创 建 请 求 消 息 体 并 打 印 。
如果请求指令是"QUERY TIME ORDER",则把服务器的当前时间编码后返回给客户端。
 */
            if (key.isReadable()){
                // Read the data
                SocketChannel sc = (SocketChannel) key.channel();
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                int readBytes = sc.read(readBuffer);
                if (readBytes > 0){
                    readBuffer.flip();
                    byte[] bytes = new byte[readBuffer.remaining()];
                    readBuffer.get(bytes);
                    String body = new String(bytes,"UTF-8");
                    System.out.println("The time server receive order : "
                            + body);
                    String currentTime = "QUERY TIME ORDER"
                            .equalsIgnoreCase(body) ? new java.util.Date(
                            System.currentTimeMillis()).toString()
                            : "BAD ORDER";

                    doWrite(sc,currentTime);
                }else if (readBytes < 0){
                    // 对端链路关闭
                    key.cancel();
                    sc.close();
                }else{
                    ; // 读到0字节，忽略
                }
            }

        }
    }
    /*
177 ～ 186 行 将 应 答 消 息 异 步 发 送 给 客 户 端 。 我 们 看 下 关 键 代 码 ， 首 先 将 字 符 串 编
码 成 字 节 数 组 ， 根 据 字 节 数 组 的 容 量 创 建 ByteBuffer ， 调 用 ByteBuffer 的 put 操 作 将 字 节
数 组 复 制 到 缓 冲 区 中 ， 然 后 对 缓 冲 区 进 行 flip 操 作 ， 最 后 调 用 SocketChannel 的 write 方 法
将 缓 冲 区 中 的 字 节 数 组 发 送 出 去 。 需 要 指 出 的 是 ， 由 于 SocketChannel 是 异 步 非 阻 塞 的 ，
它 并 不 保 证 一 次 能 够 把 需 要 发 送 的 字 节 数 组 发 送 完 ， 此 时 会 出 现 “ 写 半 包 ” 问 题 。 我 们 需
要 注 册 写 操 作 ， 不 断 轮 询 Selector 将 没 有 发 送 完 的 ByteBuffer 发 送 完 毕 ， 然 后 可 以 通 过
ByteBuffer 的 hasRemain() 方 法 判 断 消 息 是 否 发 送 完 成 。 此 处 仅 仅 是 个 简 单 的 入 门 级 例 程 ，
没 有 演 示 如 何 处 理 “ 写 半 包 ” 场 景 ， 后 续 的 章 节 会 有 详 细 说 明 。

     */
    private void doWrite(SocketChannel channel, String response) throws IOException {

        if (response != null && response.trim().length() > 0){
            byte[] bytes = response.getBytes();
            ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
            writeBuffer.put(bytes);
            writeBuffer.flip();
            channel.write(writeBuffer);
        }
    }


}
