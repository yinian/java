package com.viagra.java.concurrent.java7Concurrencycookbook.chapter02.demo07_multiconditions_lock;
/*
让我们创建一个类来模拟文本文件。创建FileMock类，包括两个属性：
一个字符串数组类型，名叫content，另一个int类型，名叫index。
它们将存储文件内容和被检索到的模拟文件的行数。
 */
public class FileMock {

    // 模拟文件的内容
    private String[] content;

    // 将要处理的文本行
    private int index;

    /**
     * 使用随机的字符串初始化文件内容
     * size : 数组的长度
     * length : 数组中每个字符串的长度
     */
    public FileMock(int size, int length) {

        content = new String[size];
        for (int i = 0; i < size; i++) {
            StringBuffer buffer = new StringBuffer(length);
            for (int j = 0; j < length; j++) {
                int indice = (int) Math.random() * 255;
                buffer.append((char)indice);

            }

            content[i] = buffer.toString();

        }

        index = 0;
    }

    /**
     * 是否还有要处理的文本行
     */
    public boolean hasMoreLines() {
        return index < content.length;
    }

    public String getLine() {
        if (this.hasMoreLines()) {
            System.out.println("Mock: " + (content.length - index));
            return content[index++];
        }

        return null;
    }






}
