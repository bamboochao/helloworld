package com.study.gitfirst.controller;

import org.springframework.beans.factory.annotation.Value;

/**
 * FileName: BaseController.java
 * Author:   machao
 * Date:     2019/11/18 10:15
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 */

public class BaseController {
	@Value("${spring.profiles.active}")
	protected String activeEnv;
}
