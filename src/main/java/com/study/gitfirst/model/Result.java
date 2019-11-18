package com.study.gitfirst.model;

import com.study.gitfirst.constant.SystemConstant;

import lombok.Data;

/**
 * FileName: Result.java
 * Author:   machao
 * Date:     2019/11/11 19:40
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 */
@Data
public class Result {
	public Object data;
	public String message;
	public Integer code = SystemConstant.UNKNOW_ERROR;
	public Boolean isSuccess = false;
	private long timestamp = System.currentTimeMillis();
	
	public void setResult(Object data, String message, Integer code, Boolean isSuccess) {
		setData(data);
		setMessage(message);
		setCode(code);
		setIsSuccess(isSuccess);
	}
	public void setResultSuccess(Object data) {
		setResult(data,"success!",SystemConstant.SUCCESS,true);
	}
	public void setResultError(String message, Integer code) {
		setResult(null,message,code,false);
	}
}
