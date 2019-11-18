package com.study.gitfirst.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.gitfirst.model.People;
import com.study.gitfirst.service.IMongoDBService;
import com.study.gitfirst.service.ISportsService;
import com.studygit.first.event.TestEvent;

/**
 * FileName: TestController.java
 * Author:   machao
 * Date:     2019/10/09 11:41
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 */
@RestController
@RequestMapping(value="/test")
public class TestController extends BaseController{
	@Autowired
	private ApplicationContext ac;
	@Autowired
	private IMongoDBService mongoservice;
	@GetMapping(value="/event")
	public String testEventController(String msg, Integer num) {
		Long time = System.currentTimeMillis();
		TestEvent testEvent = new TestEvent(msg, num);
		ac.publishEvent(testEvent);
		System.out.println(System.currentTimeMillis()-time);
		return "Test Finish";
	}
	@Autowired
	List<ISportsService> ssList;
	
	@GetMapping(value="/autowiredtest")
	public String testAutowiredController() {
		ssList.forEach(ss -> {ss.specialPoint();});
		return "Autowired Test Finish";
	}
	
	@GetMapping(value="/mongodbtest")
	public List<People> testMongodb(String name) {
		List<People> dept = mongoservice.findPeopleByName(name);
		return dept;
	}
	@GetMapping(value="/findPeople")
	public People findPeopleByName(String name) {
		People people = mongoservice.findPeopleByName2(name);
		return people;
	}
	
	@PostMapping("/deleteDept")
	public Boolean removeOnePeople(@RequestBody People people) {
		return mongoservice.deleteOnePeopleByName(people);
	}
	
	@PostMapping("/savePeople")
	public Boolean saveDeptToMongodb(@RequestBody People people) {
		return mongoservice.saveDeptByTemplate(people);
	}
	
	@PostMapping("/savePeople2")
	public Boolean saveDeptByRepository(@RequestBody People dept) {
		return mongoservice.savePeople(dept);
	}
	
	@PostMapping("/updatePeople")
	public Boolean updatePeople(@RequestBody People people) {
		return mongoservice.updatePeopleByName(people);
	}
	@PostMapping("/updatePeopleRepository")
	public Boolean updatePeopleByRepository(@RequestBody People people) {
		return mongoservice.updatePeopleByRepository(people);
	}
	
	@GetMapping(value="/injectProfileTest")
	public void testInjectProfile() {
		System.out.println(activeEnv);
	}
}
