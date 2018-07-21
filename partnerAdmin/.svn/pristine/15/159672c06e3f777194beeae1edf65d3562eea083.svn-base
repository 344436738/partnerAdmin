package com.base.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.bean.SiteType;
import com.base.exception.ServiceException;
import com.base.mapper.SiteTypeMapper;
import com.base.model.SiteTypeModel;

/**
 * 
 * <br>
 * <b>功能：</b>SiteTypeService<br>
 * <b>作者：</b><br>
 * <b>日期：</b> 2013-03-01 <br>
 */
@Service("siteTypeService")
public class SiteTypeService<T> extends BaseService<T> {
	private final static Logger log = Logger.getLogger(SiteTypeService.class);

	@Autowired
	private SiteTypeMapper<T> mapper;

	/**
	 * 添加站点类型
	 */
	public void add(T t) throws Exception {
		validation(t, 0);
		getMapper().add(t);
	}

	/**
	 * 修改站点类型
	 */
	public void update(T t) throws Exception {
		validation(t, 1);
		getMapper().update(t);
	}

	public void delete(Object... ids) throws Exception {
		if (ids == null || ids.length < 1) {
			return;
		}
		for (Object id : ids) {
			getMapper().deleteSiteRel((Integer) id);
		}
		super.delete(ids);
	}

	public List<T> queryByAll() throws Exception {
		return getMapper().queryByAll();
	}

	public List<T> queryBySiteId(Integer siteId) throws Exception {
		return getMapper().queryBySiteId(siteId);
	}

	/**
	 * 验证是否重复
	 * 
	 * @param t
	 * @param num
	 * @throws Exception
	 */
	private void validation(T t, int num) throws Exception {
		SiteType bean = (SiteType) t;
		// 验证name是否重复
		SiteTypeModel model = new SiteTypeModel();
		model.setName(bean.getName());
		int count = getMapper().queryByCount(model);
		if (count > num) {
			throw new ServiceException("name is can't be duplicate");
		}
		// 验证code是否重复
		model.setName(null);
		model.setCode(bean.getCode());
		count = getMapper().queryByCount(model);
		if (count > num) {
			throw new ServiceException("code is can't be duplicate");
		}
	}

	public SiteTypeMapper<T> getMapper() {
		return mapper;
	}

}
