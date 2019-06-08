package com.viagra.java.concurrent.java7Concurrencycookbook.chapter05.fork_join.demo01.create_fork_join_pool;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class Main {


    public static void main(String[] args) {

        // 产品列表
        ProductListGenerator generator = new ProductListGenerator();
        List<Product> products = generator.generate(40);

        // 创建任务
        Task task = new Task(products,0,products.size(),0.20);

        // 创建一个ForJoinPool
        ForkJoinPool pool = new ForkJoinPool();

        // 执行任务
        pool.execute(task);

        // 输出pool的信息
        //.实现一个显示关于每隔5毫秒池中的变化信息的代码块。将池中的一些参数值写入到控制台，直到任务完成它的执行。
        do{
            System.out.printf("Main: Thread Count: %d\n", pool.getActiveThreadCount());
            System.out.printf("Main: Thread Steal: %d\n", pool.getStealCount());
            System.out.printf("Main: Paralelism: %d\n", pool.getParallelism());

            try {
                TimeUnit.MICROSECONDS.sleep(5);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }while (!task.isDone());

        //关闭
        pool.shutdown();

        // 检查任务是否正常完成
        //使用isCompletedNormally()方法检查假设任务完成时没有出错，在这种情况下，写入一条信息到控制台
        if (task.isCompletedNormally()){
            System.out.printf("Main: The process has completed normally.\n");
        }

        for (int i = 0; i < products.size(); i++) {

            Product product = products.get(i);
            if (product.getPrice()!=12){
                System.out.printf("Product %s: %f\n", product.getName(), product.getPrice());

            }

        }

        System.out.println("Main: End of the program.\n");

    }

}
