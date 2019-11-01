package com.github.yinian.xxl.proxy.cglib;


import com.github.yinian.xxl.proxy.cglib.proxy.BookFacadeCglib;
import com.github.yinian.xxl.proxy.cglib.service.impl.BookFacadeImpl;

/**
 * @author yinian
 */
public class TestCglib {

	public static void main(String[] args) {
		BookFacadeCglib cglib = new BookFacadeCglib();
		BookFacadeImpl bookFacade = (BookFacadeImpl) cglib.getInstance(new BookFacadeImpl());
		bookFacade.addBook();

	}


}