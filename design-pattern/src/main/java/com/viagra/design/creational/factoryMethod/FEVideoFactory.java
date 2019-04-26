package com.viagra.design.creational.factoryMethod;

/**
 * @Author: HASEE
 * @Description:
 * @Date: Created in 15:12 2019/4/26
 * @Modified By:
 */
public class FEVideoFactory extends VideoFactory {

	@Override
	public Video getVideo() {
		return new FEVideo();
	}
}
