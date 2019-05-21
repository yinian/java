package com.viagra.java.concurrent.java7Concurrencycookbook.chapter03.demo05.phaser_tasks;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;
/*
创建一个类名为FileSearch并一定实现Runnable 接口。
这个类实现的操作是在文件夹和其子文件夹中搜索确定的扩展名并在24小时内修改的文件。
 */
public class FileSearch implements Runnable {

    /**
     * 初始目录
     */
    private String initPath;

    /**
     * 文件的后缀名
     */
    private String end;

    /**
     * 存储要查找的符合后缀名的文件的全路径
     */
    private List<String> results;

    /**
     * 控制FileSearch对象的执行.它们的执行将分成3个步骤：
     * 1. 在指定的目录和子目录中查找指定扩展名的的文件
     * 2. 过滤结果.只想得到今天修改的文件
     * 3. 打印结果
     */

    private Phaser phaser;

    public FileSearch(String initPath, String end, Phaser phaser) {
        this.initPath = initPath;
        this.end = end;
        this.phaser = phaser;
        results = new ArrayList<>();
    }


    /*
    来实现 run() 方法，使用之前描述的辅助方法来执行，并使用Phaser对象控制phases间的改变。首先，调用phaser对象的
    // arriveAndAwaitAdvance() 方法。直到使用线程被创建完成，搜索行为才会开始。
     */

    @Override
    public void run() {
        /*
        等待所有的FileSearch对象的创建，将会阻塞
        比如要是Main中创建的system线程先启动，那么要等到apps和documents线程创建结束
         */
        phaser.arriveAndAwaitAdvance();

        System.out.printf("%s: Starting.\n", Thread.currentThread().getName());
        // 1.查找文件
        File file = new File(initPath);
        if (file.isDirectory()){
            directoryProcess(file);
        }

        // 如果列表为空，在phaser撤销然后结束
        if (!checkResults()){
            return;
        }

        // 2.过滤结果
        filterResults();
        // 如果列表为空,在phaser撤销然后结束
        if (!checkResults()) {
            return;
        }

        //3.打印信息
        showInfo();
        phaser.arriveAndDeregister();
        System.out.printf("%s: Work completed.\n", Thread.currentThread().getName());



    }


    /**
     * 第一阶段：处理目录
     */
    private void directoryProcess(File file) {
        File[] list = file.listFiles();
        if (list != null) {
            for (int i = 0; i < list.length; i++) {
                if (list[i].isDirectory()) {
                    // 目录, 递归调用
                    directoryProcess(list[i]);
                } else {
                    // 文件
                    fileProcess(list[i]);
                }
            }
        }
    }

    private void fileProcess(File file) {
        if (file.getName().endsWith(end)) {
            // 将文件的全路径加入到列表中
            results.add(file.getAbsolutePath());
        }
    }

    /**
     * 第二阶段：过滤文件. 过滤第一阶段包含的列表.
     */
    private void filterResults() {
        List<String> newResults = new ArrayList<>();
        long actualDate = new Date().getTime();
        for (int i = 0; i < results.size(); i++) {
            File file = new File(results.get(i));
            long fileDate = file.lastModified();	// 文件的最后修改时间

            // 不超过24小时,加入到列表中
            if (actualDate - fileDate < TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS)) {
                newResults.add(results.get(i));
            }
        }

        results = newResults;
    }

    /**
     * 第三阶段：打印信息
     */
    private void showInfo() {
        for (int i = 0; i < results.size(); i++) {
            File file = new File(results.get(i));
            System.out.printf("%s: %s\n", Thread.currentThread().getName(), file.getAbsolutePath());
        }
        // 等待所有在phaser
        phaser.arriveAndAwaitAdvance();
    }

    /**
     * 检查结果
     */
    private boolean checkResults() {

        /*
         首先，检查结果List的大小。如果为0，对象写信息到操控台表明情况，然后调用Phaser对象的
        arriveAndDeregister() 方法通知此线程已经结束actual phase，并离开phased操作。
         */
        if (results.isEmpty()){
            System.out.printf("%s: Phase %d: 0 results.\n", Thread.currentThread().getName(), phaser.getPhase());
            System.out.printf("%s: Phase %d: End.\n", Thread.currentThread().getName(), phaser.getPhase());
            // 没有要查找的文件.撤销Phaser
            // 提醒Phaser这个线程已经结束了实际的阶段,它将离开phased的操作
            phaser.arriveAndDeregister();
            return false;
        }else{
            /*
            // 15. 另一种情况，如果结果list有元素，那么对象写信息到操控台表明情况，调用 Phaser对象懂得
            // arriveAndAwaitAdvance() 方法并通知 actual phase，它会被阻塞直到phased
            // 操作的全部参与线程结束actual phase。

             */
            System.out.printf("%s: Phase %d: %d results.\n", Thread.currentThread().getName(), phaser.getPhase(),
                    results.size());
            /*
            此阶段结束，等待继续执行下一个phase
            提醒Phaser这个线程已经结束了实际的阶段，它将阻塞直到在这个阶段的操作的所有参与的线程完成实际的阶段
             */
            phaser.arriveAndAwaitAdvance();
            return true;
        }
    }
}