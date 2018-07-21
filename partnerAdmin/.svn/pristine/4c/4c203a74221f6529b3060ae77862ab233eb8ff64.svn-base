package com.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.bean.Demo;
import com.base.mapper.DemoMapper;

@Service("demoService")
public class DemoService<T> extends BaseService<T> {

	@Autowired
	private DemoMapper<T> mapper;

	@Override
	public DemoMapper<T> getMapper() {
		return mapper;
	}

	public void addUser(Demo user) {
		getMapper().add((T) user);
	}

	public void deleteByUserId(Integer id) {
		getMapper().delete(id);
	}

	public void update(Demo user) {
		getMapper().update((T) user);
	}

	public Object queryByUserid(Integer id) {
		return getMapper().queryById(id);
	}

}
