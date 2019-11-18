package com.study.gitfirst.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.gitfirst.jpa.DeptRepository;
import com.study.gitfirst.model.Dept;
import com.study.gitfirst.model.IPeople;
import com.study.gitfirst.model.People;
import com.study.gitfirst.result.ServerResult;
import com.study.gitfirst.service.IDeptService;

@RestController
@RequestMapping(value="/dept")
public class MavenController {
	
	@Autowired
	private DeptRepository deptJPA;
	
	@Autowired
	private IDeptService deptService;
	
	/**
	 * 测试方法，用于测试前端到后台controller层
	 * @param name 测试字符串 主分支测试冲突
	 * @return
	 */
	@PostMapping(value="/hello/{name}")
	public IPeople sayHelloController(@PathVariable String name, HttpServletRequest req) {
		String brokerage = req.getHeader("brokerage");
		String terminal = req.getHeader("terminal");
		System.out.println(brokerage);
		System.out.println(terminal);
		IPeople people = new People();
		people.sayHello(name);
		return people;
	}
	
	/**
	 * 通过部门id查询部门信息
	 * @param id 部门id
	 * @return
	 */
	@PostMapping("/{id}")
	public ServerResult findDeptById(@PathVariable Integer id) {
		if(id != null) {
			Optional<Dept> optional =  deptJPA.findById(id);
			return new ServerResult("查询部门信息成功！", optional.get());
		}
		return new ServerResult("查询部门信息失败！", null);
	}
	
	/**
	 * 查询所有部门信息
	 * @return
	 */
	@GetMapping
	public List<Dept> findDepts() {
		List<Dept> deptList =  deptJPA.findAll();
		return deptList;
	}
	
	/**
	 * 通过部门id删除一条部门信息记录
	 * @param id
	 * @return
	 */
	@Transactional(rollbackFor=Exception.class)
	@DeleteMapping("/{id}")
	public ServerResult deleteDeptById(@PathVariable Integer id) {
		deptJPA.deleteById(id);
		return new ServerResult("删除部门信息成功！", null);
	}
	
	/**
	 * 保存一条部门信息记录
	 * @param dept 部门对象
	 * @return
	 */
	@Transactional(rollbackFor=Exception.class)
	@PostMapping
	public ServerResult saveDept(@RequestBody Dept dept) {
		dept = deptJPA.save(dept);
		return new ServerResult("保存部门信息成功！", dept);
	}
	
	/**
	 * 通过部门id更新部门信息
	 * @param dept 部门对象
	 * @param id 部门ID
	 * @return
	 */
	@Transactional(rollbackFor=Exception.class)
	@PostMapping("/update/{id}")
	public ServerResult updateDeptById(@RequestBody Dept dept,@PathVariable Integer id) {
		dept.setId(id);
		deptJPA.save(dept);
		return new ServerResult("修改部门信息成功！", dept);
	}
	
	/**
	 * 分页不带参查询
	 * @param page 第几页
	 * @param size 每页大小
	 * @return
	 */
	@GetMapping("/{page}/{size}")
	public Page<Dept> findDepts(@PathVariable Integer page, @PathVariable Integer size) {
		Page<Dept> deptPage =  deptService.findDeptNoCriteria(page, size);
		return deptPage;
	}
	
	/**
	 * 分页带参查询
	 * @param page 第几页
	 * @param size 每页大小
	 * @param dept 部门信息
	 * @return
	 */
	@PostMapping("/{page}/{size}")
	public Page<Dept> findDepts(@PathVariable Integer page, @PathVariable Integer size, @RequestBody Dept dept) {
		Page<Dept> deptPage =  deptService.findDeptCriteria(page, size, dept);
		return deptPage;
	}
	
}
