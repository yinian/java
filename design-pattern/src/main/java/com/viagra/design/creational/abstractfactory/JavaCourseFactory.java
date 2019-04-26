package com.viagra.design.creational.abstractfactory;

/**
 * @Author: HASEE
 * @Description:
 * @Date: Created in 16:16 2019/4/26
 * @Modified By:
 */
public class JavaCourseFactory implements CourseFactory{
	@Override
	public Video getVideo() {
		return new JavaVideo();
	}

	@Override
	public Article getArticle() {
		return new JavaArticle();
	}
}
