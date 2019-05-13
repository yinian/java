package com.viagra.exception;

/**
 * @Author: HASEE
 * @Description:
 * @Date: Created in 11:37 2019/5/13
 * @Modified By:
 */
public class TweetNotFoundException extends RuntimeException {

	public TweetNotFoundException(String tweetId) {
		super("Tweet not found with id " + tweetId);
	}
}
