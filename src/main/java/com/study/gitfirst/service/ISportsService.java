package com.study.gitfirst.service;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

/**
 * FileName: ISportsService
 * Author:   machao
 * Date:     2019/10/09 16:44
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 */

public interface ISportsService {
	void specialPoint();
}
@Order(2)
@Service
class JumpService implements ISportsService{

	@Override
	public void specialPoint() {
		System.out.println("more higher!");
	}
	
}
@Order(1)
@Service
class RunService implements ISportsService{

	@Override
	public void specialPoint() {
		System.out.println("more faster!");
	}
	
}
@Order(3)
@Service
class WeightService implements ISportsService{

	@Override
	public void specialPoint() {
		System.out.println("more stronger!");
	}
	
}
