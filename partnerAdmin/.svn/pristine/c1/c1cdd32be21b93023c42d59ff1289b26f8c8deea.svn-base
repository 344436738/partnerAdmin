package com.base.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.bean.SysLog;
import com.base.mapper.SysLogMapper;
import com.base.model.SysLogModel;

@Service("sysLogService")
public class SysLogService<T> extends BaseService<T>{

	@Autowired
	private SysLogMapper mapper;
	
	public SysLogMapper<T> getMapper() {
		return mapper;
	}
	
	public void add(T log) throws Exception{
		super.add(log);
	}
	
	public List<SysLog> foundByList(SysLogModel sysLogModel) throws Exception{
		return mapper.foundByList(sysLogModel);
	}
}
