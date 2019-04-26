package com.viagra.design.creational.simpleFactory;
/**
 * @Author: HASEE
 * @Description:
 * @Date: Created in 11:04 2019/4/26
 * @Modified By:
 */
public class VideoFactory  {

	public Video getVideo(Class c){
		Video video = null;
		try {
			video = (Video) Class.forName(c.getName()).newInstance();
		}catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return video;
	}

}
