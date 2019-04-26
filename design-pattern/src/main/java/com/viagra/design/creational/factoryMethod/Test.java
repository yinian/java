package com.viagra.design.creational.factoryMethod;

/**
 * Created by vaigra
 */
public class Test {
    public static void main(String[] args) {

        VideoFactory videoFactory = new PythonVideoFactory();
        VideoFactory javaVideoFactory = new JavaVideoFactory();
        VideoFactory feVideoFactory = new FEVideoFactory();
        Video video = videoFactory.getVideo();
        video.produce();

    }

}
