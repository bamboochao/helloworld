package com.study.gitfirst.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document("people")
public class People implements IPeople {
	@Id
	private ObjectId _id;
	
	private String sayValue;
	
	private String name;
	private String desc;
	private String newName;
	@Override
	public void sayHello(String name) {
		sayValue = "Hello, "+name;
	}
	
}
