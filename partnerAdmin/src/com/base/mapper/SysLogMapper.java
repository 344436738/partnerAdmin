package com.base.mapper;

import java.util.List;

import com.base.bean.SysLog;
import com.base.model.SysLogModel;

public interface SysLogMapper<T> extends BaseMapper<T> {

	public List<SysLog> foundByList(SysLogModel sysLogModel);
}
