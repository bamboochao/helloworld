package com.studygit.first.event;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

/**
 * FileName: TestListening
 * Author:   machao
 * Date:     2019/10/09 11:35
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 */
@Component
@EnableAsync
public class TestListening /* implements ApplicationListener<TestEvent> */{

	/*
	 * @Override
	 * 
	 * @Async public void onApplicationEvent(TestEvent event) { try { Long time =
	 * System.currentTimeMillis(); System.out.println("收到发送的事件啦，开始处理");
	 * System.out.println(event.toString()); Thread.sleep(5000);
	 * System.out.println("事件处理完毕,耗时："+(System.currentTimeMillis()-time)); } catch
	 * (InterruptedException e) { e.printStackTrace(); }
	 * 
	 * }
	 */
	
	@EventListener
	@Async
	public void myApplicationEvent(TestEvent event) {
		try {
			Long time = System.currentTimeMillis();
			System.out.println("我通过注解也监听收到发送的事件啦，开始处理");
			System.out.println(event.toString());
			Thread.sleep(3000);
			System.out.println("注解监听事件处理完毕,耗时："+(System.currentTimeMillis()-time));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
