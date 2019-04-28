package com.viagra.java.concurrent.java7Concurrencycookbook.chapter01.demo04_controlinterruption;

import java.io.File;

/**
 * @Author: HASEE
 * @Description:
 * @Date: Created in 17:53 2019/4/22
 * @Modified By:
 */
public class FileSearch implements Runnable {

	private String initPath;
	private String fileName;

	public FileSearch(String initPath, String fileName) {
		this.initPath = initPath;
		this.fileName = fileName;
	}

	@Override
	public void run() {

	}

	// 清理资源,这个示例中,空的实现
	private void cleanResources() {

	}

	// 处理目录
	private void directoryProcess(File file) throws InterruptedException {
		// 得到所有的文件
		File[] list = file.listFiles();
		if (list != null) {
			for (int i = 0; i < list.length; i++) {
				if (list[i].isDirectory()) {
					// 如果是目录,递归调用
					directoryProcess(list[i]);
				} else {
					// 文件
					fileProcess(list[i]);
				}
			}
		}
	}

	// 处理文件
	private void fileProcess(File file) throws InterruptedException {
		// 检查文件名称
		if (file.getName().equals(fileName)) {
			System.out.printf("%s : %s\n", Thread.currentThread().getName(), file.getAbsolutePath());
		}

		// 检查是否中断
		if (Thread.interrupted()){
			throw new InterruptedException();
		}
	}

}
