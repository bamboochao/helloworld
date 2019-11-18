package com.studygit.first.event;

import org.springframework.context.ApplicationEvent;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
/**
 * FileName: TestEvent
 * Author:   machao
 * Date:     2019/10/09 09:54
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class TestEvent extends ApplicationEvent {
	private static final long serialVersionUID = 6841933261430635151L;
	String msg;
	Integer num;
	public TestEvent(Object source) {
		super(source);
	}
	public TestEvent(String str, Integer i) {
		super(str);
		msg = str;
		num = i;
	}

}
