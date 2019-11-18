package com.study.gitfirst.service;

import org.springframework.data.domain.Page;

import com.study.gitfirst.model.Dept;

public interface IDeptService {
	/**
	 * 分页查询不带特殊查询条件
	 * @param page 当前页 从0开始
	 * @param size 每页大小
	 * @return Page<Dept>
	 */
	Page<Dept> findDeptNoCriteria(Integer page, Integer size);
	/**
	 * 分页查询带特殊查询条件 部门信息
	 * @param page 当前页 从0开始
	 * @param size 每页大小
	 * @param dept
	 * @return Page<Dept>
	 */
	Page<Dept> findDeptCriteria(Integer page, Integer size, Dept dept);
}
