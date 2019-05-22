package com.viagra.chapter02.aio;

import com.sun.javafx.image.ByteToBytePixelConverter;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class AcceptCompletionHandler implements
        CompletionHandler<AsynchronousSocketChannel,AsyncTimeServerHandler> {
/*
我 们 从 attachment 获 取 成 员 变 量 AsynchronousServerSocketChannel ， 然 后 继 续 调 用 它 的
accept 方 法 。 有 的 读 者 可 能 会 心 存 疑 惑 ： 既 然 已 经 接 收 客 户 端 成 功 了 ， 为 什 么 还 要 再 次 调 用
accept 方 法 呢 ？ 原 因 是 这 样 的 ： 调 用 AsynchronousServerSocke ℃ hannel 的 accept 方 法 后 ，
如 果 有 新 的 客 户 端 连 接 接 入 ， 系 统 将 回 调 我 们 传 入 的 CompletionHandler 实 例 的 completed
方 法 ， 表 示 新 的 客 户 端 己 经 接 入 成 功 。 因 为 一 个 AsynchronousServerSocket Channel 可 以 接
收 成 千 上 万 个 客 户 端 ， 所 以 需 要 继 续 调 用 它 的 accept 方 法 ， 接 收 其 他 的 客 户 端 连 接 ， 最 终
形 成 一 个 循 环 。 每 当 接 收 一 个 客 户 读 连 接 成 功 之 后 ， 再 异 步 接 收 新 的 客 户 端 连 接 。
链 路 建 立 成 功 之 后 ， 服 务 端 需 要 接 收 客 户 端 的 请 求 消 息 ， 在 代 码 第 19 行 创 建 新 的
ByteBuffer ， 预 分 配 IMB 的 缓 冲 区 ， 第 20 行 通 过 调 用 A synchronousSocketChannel的 read方法进行异步操作。
 */
    @Override
    public void completed(AsynchronousSocketChannel result,
                          AsyncTimeServerHandler attachment) {

        attachment.asynchronousServerSocketChannel.accept(attachment,this);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        result.read(buffer,buffer,new ReadCompletionHandler(result));

    }

    @Override
    public void failed(Throwable exc,
                       AsyncTimeServerHandler attachment) {

        exc.printStackTrace();
        attachment.latch.countDown();

    }
}
