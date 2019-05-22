package com.viagra.chapter02.nio;

public class TimeServer {

    public static void main(String[] args) {
/*
下 面 对 NIO 创 建 的 TimeServer 进 行 简 单 分 析 。设 置 监 听端 口 。
 创 建 了 一 个 被 称 为 MultiplexerTimeServer 的 多 路 复 用 类 ， 它 是一 个独 立 的 线 程 ，
 负 责 轮 询 多 路 复 用 器 SeIctor ， 可 以 处 理 多 个 客 户 端 的 并 发 接 入 。
 */
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                // 采用默认值
            }
        }

        MultiplexerTimeServer timeServer = new MultiplexerTimeServer(port);
        new Thread(timeServer, "NIO-MultiplexerTimeServer-001").start();
    }
}
