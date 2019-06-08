package com.viagra.java.concurrent.java7Concurrencycookbook.chapter05.fork_join.demo01.create_fork_join_pool;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.concurrent.RecursiveAction;
/*
创建Task类，指定它继承RecursiveAction类
 */
@AllArgsConstructor
public class Task extends RecursiveAction {

    // 产品列表
    private List<Product> products;

    // 第一个和最后一个间隔分配到任务
    //声明两个私有的、int类型的属性first和last。这些属性将决定这个任务产品的阻塞过程
    private int first;
    private int last;
//声明一个私有的、double类型的属性increment，用来存储产品价格的增长
    private double increment;


    public Task() {
        super();
    }

    @Override
    protected void compute() {
/*
如果last和first的差小于10（任务只能更新价格小于10的产品），使用updatePrices()方法递增的设置产品的价格
 */
        if (last - first < 10){
            updatePrices();
        }else {
            /*
            如果last和first的差大于或等于10，则创建两个新的Task对象，一个处理产品的前半部分，
            另一个处理产品的后半部分，然后在ForkJoinPool中，使用invokeAll()方法执行它们
             */
            int middle = (last+first)/2;
            System.out.printf("Task: Pending tasks: %s\n",getQueuedTaskCount());
            Task t1 = new Task(products, first, middle + 1, increment);
            Task t2 = new Task(products, middle + 1, last, increment);
            System.out.println("t1 : " + t1);
            System.out.println("t2 : " + t2);
            System.out.println();
            invokeAll(t1,t2);
        }

    }
/*
这个方法更新产品队列中位于first值和last值之间的产品。
 */
    private void updatePrices() {

        System.out.println("first : " + this.first + "  last : " + last);
        System.out.println();
        for (int i = first; i < last; i++) {
            Product product = products.get(i);
            product.setPrice(product.getPrice() * (1 + increment));
        }
    }

    @Override
    public String toString() {
        return "Task [first=" + first + ", last=" + last + "]";
    }

}
