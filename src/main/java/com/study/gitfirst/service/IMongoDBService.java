package com.study.gitfirst.service;

import java.util.List;

import com.study.gitfirst.model.People;

/**
 * FileName: IMongoDBService.java
 * Author:   machao
 * Date:     2019/11/11 19:28
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 */

public interface IMongoDBService {
	List<People> findPeopleByName(String name);
	People findPeopleByName2(String name);
	Boolean savePeople(People people);
	
	Boolean saveDeptByTemplate(People people);
	Boolean deleteOnePeopleByName(People people);

	Boolean updatePeopleByName(People people);
	Boolean updatePeopleByRepository(People people);
}
