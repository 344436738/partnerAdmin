package com.business.mapper;
import java.util.List;
import java.util.Map;

import com.base.mapper.BaseMapper;
import com.base.model.BaseModel;
import com.business.bean.StResume;
import com.business.bean.StResumeTrash;
import com.business.model.StResumeModel;

/**
 * StResume Mapper
 * @author Administrator
 *
 */
public interface StResumeMapper<T> extends BaseMapper<T> {
	
	public Map<String, Integer> resumeSum(StResumeModel model);
	public List<T> resumeSearch(StResumeModel model);
	public int getResumByTel(Map<String, Object> map);
	public void addResume(StResume stResume);
	public void addResumeTrash(StResume stResume);
	
	public int queryStResumeCount(StResumeModel model);
	
	public int queryNumber(String str);
	
}
