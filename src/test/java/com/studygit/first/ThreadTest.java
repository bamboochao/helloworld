package com.studygit.first;

/**
 * FileName: ThreadTest.java
 * Author:   machao
 * Date:     2020/07/30 15:27
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 */

public class ThreadTest {
	static Thread t1 = new Thread(()-> {
		Thread thread = Thread.currentThread();
		thread.setName("ttttt1");
		System.out.println(thread.getName());
	});
	static Thread t2 = new Thread(()-> {
		try {
			t1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Thread thread = Thread.currentThread();
		thread.setName("ttttt2");
		System.out.println(thread.getName());
	});
	static Thread t3 = new Thread(()-> {
		try {
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Thread thread = Thread.currentThread();
		thread.setName("ttttt3");
		System.out.println(thread.getName());
	});
	
	public static void main(String[] args) {
		
		t3.start();
		t2.start();
		t1.start();
		try {
			t3.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(t3.getState());
		//t3.start();
	}

}
