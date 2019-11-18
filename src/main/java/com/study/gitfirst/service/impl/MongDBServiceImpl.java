package com.study.gitfirst.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.gitfirst.model.People;
import com.study.gitfirst.repository.MongoTestRepository;
import com.study.gitfirst.service.IMongoDBService;

/**
 * FileName: MongDBServiceImpl.java
 * Author:   machao
 * Date:     2019/11/11 19:29
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 */
@Service
public class MongDBServiceImpl implements IMongoDBService {
	@Autowired
	private MongoTestRepository mongoRepository;
	@Autowired
	MongoTemplate mongoTemplate;
	@Override
	public List<People> findPeopleByName(String name) {
		Query query = new Query(Criteria.where("name").is(name));
		return mongoTemplate.find(query, People.class);
	}
	
	@Override
	public People findPeopleByName2(String name) {
		return mongoRepository.findByName(name);
	}
	@Override
	@Transactional
	public Boolean savePeople(People people) {
		if(people == null) { return false;}
		mongoRepository.save(people);
		return true;
	}
	@Override
	@Transactional
	public Boolean saveDeptByTemplate(People dept) {
		if(dept == null) { return false;}
		mongoTemplate.insert(dept);
		return true;
	}
	@Override
	public Boolean deleteOnePeopleByName(People people) {
		Query query = new Query(Criteria.where("name").is(people.getName()));
		query.addCriteria(Criteria.where("desc").is(people.getDesc()));
		mongoTemplate.findAndRemove(query, People.class);
		return true;
	}
	@Override
	public Boolean updatePeopleByName(People people) {
		Query query = new Query(Criteria.where("name").is(people.getName()));
		Update update = Update.update("name",people.getNewName());
		update.set("desc",people.getDesc());
		mongoTemplate.updateFirst(query, update, People.class);
		return true;
	}

	@Override
	public Boolean updatePeopleByRepository(People people) {
		//findPeopleByName(people.getName());
		mongoRepository.save(people);
		return true;
	}
	
}
