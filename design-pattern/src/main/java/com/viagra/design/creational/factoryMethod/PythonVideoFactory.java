package com.viagra.design.creational.factoryMethod;

/**
 * @Author: HASEE
 * @Description:
 * @Date: Created in 14:54 2019/4/26
 * @Modified By:
 */
public class PythonVideoFactory extends VideoFactory {
	@Override
	public Video getVideo() {
		return new PythonVideo();
	}
}
