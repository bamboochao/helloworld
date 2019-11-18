package com.study.gitfirst.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.study.gitfirst.jpa.DeptRepository;
import com.study.gitfirst.model.Dept;
import com.study.gitfirst.service.IDeptService;

@Service("deptService")
public class DeptService implements IDeptService {
	@Autowired
	private DeptRepository deptRepository;
	
	@Override
	public Page<Dept> findDeptNoCriteria(Integer page, Integer size) {
		Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC, "id");
		return deptRepository.findAll(pageable);
	}

	@Override
	public Page<Dept> findDeptCriteria(Integer page, Integer size, final Dept dept) {
		Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC, "id");
		Page<Dept> deptPage = deptRepository.findAll(new Specification<Dept>() {

			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Dept> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> list = new ArrayList<Predicate>();
				if(dept.getName()!=null && !dept.getName().isEmpty()) {
					list.add(criteriaBuilder.like(root.get("name").as(String.class), StringUtils.trimWhitespace("%"+dept.getName()+"%")));
				}
				if(dept.getDescription()!=null && !dept.getDescription().isEmpty()) {
					list.add(criteriaBuilder.like(root.get("description").as(String.class), StringUtils.trimWhitespace("%"+dept.getDescription()+"%")));
				}
				Predicate[] predicateArr = new Predicate[list.size()];
				return criteriaBuilder.and(list.toArray(predicateArr));
			}
			
		}, pageable);
		return deptPage;
	}

}
