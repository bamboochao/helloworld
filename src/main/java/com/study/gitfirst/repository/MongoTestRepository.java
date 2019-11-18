package com.study.gitfirst.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.study.gitfirst.model.People;

/**
 * FileName: MongodbRepository.java
 * Author:   machao
 * Date:     2019/11/11 19:36
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 */

public interface MongoTestRepository extends MongoRepository<People, ObjectId>  {
	public People findByName(String name);
}
