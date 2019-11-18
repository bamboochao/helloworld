package com.study.gitfirst;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.studygit.first.event")
@ComponentScan("com.study.gitfirst")
@SpringBootApplication
public class SpringBootTest {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootTest.class, args);
	}

}
