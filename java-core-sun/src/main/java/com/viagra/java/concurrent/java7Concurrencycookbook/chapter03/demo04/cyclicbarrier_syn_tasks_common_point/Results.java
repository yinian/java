package com.viagra.java.concurrent.java7Concurrencycookbook.chapter03.demo04.cyclicbarrier_syn_tasks_common_point;
/*
实现一个类名为 Results。
此类会在array内保存被查找的数字在矩阵的每行里出现的次数。
 */
public class Results {

    /**
     * 存储出现的数字在数组中每一行
     */
    private int data[];

    /**
     * @param size	行
     */
    public Results(int size) {
        data = new int[size];
    }

    /**
     * 每一行,要查找的数字出现的次数
     * @param position	行索引
     * @param value		查找的数字的统计次数
     */
    public void setData(int position, int value) {
        data[position] = value;
    }

    public int[] getData() {
        return data;
    }



}
