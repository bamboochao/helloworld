package com.study.gitfirst.jpa;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.study.gitfirst.model.Dept;

public interface DeptRepository extends JpaRepository<Dept, Integer>, JpaSpecificationExecutor<Dept>, Serializable {
	//@Query("select * from Dept d where d.name=?1")
	Optional<Dept> findByName(String name);
}
