package com.viagra.chapter02.aio;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class ReadCompletionHandler implements
        CompletionHandler<Integer, ByteBuffer> {

    private AsynchronousSocketChannel channel;

    /*
    首 先 看 构 造 方 法 。 我 们 将 AsynchronousSocketChanneI 通 过 参 数 传 递 到 ReadCompletion
Handler 中 ， 当 作 成 员 变 量 来 使 用 ， 主 要 用 于 读 取 半 包 消 息 和 发 送 应 答 。 本 例 程 不 对 半 包
读 写 进 行 具 体 说 明 ， 对 此 感 兴 趣 的 读 者 可 以 关 注 后 续 章 节 对 Netty 半 包 处 理 的 专 题 介 绍 。
继 续 看 代 码 ， 第 25 ～ 38 行 是 读 取 到 消 息 后 的 处 理 。 首 先 对 attach ment 进 行 flip 操 作 ， 为
后 续 从 缓 冲 区 读 取 数 据 做 准 备 。 根 据 缓 冲 区 的 可 读 字 节 数 创 建 byte 数 组 ， 然 后 通 过 new
String 方 法 创 建 请 求 消 息 ， 对 请 求 消 息 进 行 判 断 ， 如 果 是 "QUERYTIME ORDER" 则 获 取 当
前 系 统 服 务 器 的 时 间 ， 调 用 doWrite 方 法 发 送 给 客 户 端 。
     */

    public ReadCompletionHandler(AsynchronousSocketChannel channel) {
        if (this.channel == null)
            this.channel = channel;
    }

    @Override
    public void completed(Integer result,
                          ByteBuffer attachment) {
        attachment.flip();
        byte[] body = new byte[attachment.remaining()];
        attachment.get(body);

        try {
            String req = new String(body, "UTF-8");

            System.out.println("The time server receive order : " + req);
            String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(req) ? new java.util.Date(
                    System.currentTimeMillis()).toString() : "BAD ORDER";

            doWrite(currentTime);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }
/*
 先 对 当 前 时 间 进 行 合 法 性 校 验 ， 如 果 合 法 ， 调 用 字 符 串 的 解 码
方 法 将 应 答 消 息 编 码 成 字 节 数 组 ， 然 后 将 它 复 制 到 发 送 缓 冲 区 writeBufTer 中 ， 最 后 调 用
AsynchronousSocketChan nel 的 异 步 write 方 法 。 正 如 前 面 介 绍 的 异 步 read 方 法 一 样 ， 它 也
有 三 个 与 read 方 法 相 同 的 参 数 ， 在 本 例 程 中 我 们 直 接 实 现 wri te 方 法 的 异 步 回 调 接 口
CompletionHandlero 代 码 跳 到 第 5 1 行 ， 对 发 送 的 writeBuffer 进 行 判 断 ， 如 果 还 有 剩 余 的
字 节 可 写 ， 说 明 没 有 发 送 完 成 ， 需 要 继 续 发 送 ， 直 到 发 送 成 功 。

 */
    private void doWrite(String currentTime) {
        if (currentTime != null && currentTime.trim().length() > 0) {
            byte[] bytes = (currentTime).getBytes();
            ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
            writeBuffer.put(bytes);
            writeBuffer.flip();
            channel.write(writeBuffer, writeBuffer,
                    new CompletionHandler<Integer, ByteBuffer>() {
                        @Override
                        public void completed(Integer result, ByteBuffer attachment) {
                            // 如果没有发送完成，继续发送
                            if (attachment.hasRemaining())
                                channel.write(attachment,attachment,this);
                        }

                        @Override
                        public void failed(Throwable exc, ByteBuffer attachment) {

                            try {
                                channel.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                    });
        }

    }

    @Override
    public void failed(Throwable exc, ByteBuffer attachment) {
        try {
            this.channel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
