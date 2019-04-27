package com.viagra.design.creational.abstractfactory;

/**
 * @Author: HASEE
 * @Description:
 * @Date: Created in 16:19 2019/4/26
 * @Modified By:
 */
public class Test {

	public static void main(String[] args) {
		//language=JSON
		CourseFactory courseFactory = new JavaCourseFactory();

		Video video = courseFactory.getVideo();
		Article article = courseFactory.getArticle();
		video.produce();
		article.produce();
	}
}
