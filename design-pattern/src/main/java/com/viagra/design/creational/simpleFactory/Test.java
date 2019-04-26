package com.viagra.design.creational.simpleFactory;

import ch.qos.logback.classic.util.LoggerNameUtil;

import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by viagra
 */
public class Test {
    public static void main(String[] args) {
//	    VideoFactory videoFactory = new VideoFactory();
//	    Video video = videoFactory.getVideo("java");

	    VideoFactory videoFactory = new VideoFactory();
	    Video video = videoFactory.getVideo(JavaVideo.class);
	    if (video == null){
		    return;
	    }
	    video.produce();
    }

}
