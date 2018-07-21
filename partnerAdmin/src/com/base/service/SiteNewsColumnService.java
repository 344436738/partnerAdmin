package com.base.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.base.bean.SiteNewsColumn;
import com.base.mapper.SiteNewsColumnMapper;
import com.base.model.SiteNewsColumnModel;

/**
 * 
 * <br>
 * <b>功能：</b>SiteNewsColumnService<br>
 * <b>作者：</b>罗泽军<br>
 * <b>日期：</b> Dec 9, 2011 <br>
 * <b>版权所有：<b>版权所有(C) 2011，WWW.VOWO.COM<br>
 */
@Service("siteNewsColumnService")
public class SiteNewsColumnService<T> extends BaseService<T> {
	//private final static Logger log = Logger.getLogger(SiteNewsColumnService.class);

	@Autowired
	private SiteNewsColumnMapper<T> mapper;

	public List<T> queryAllColumn() throws Exception {
		SiteNewsColumnModel model = new SiteNewsColumnModel();
		model.setQueryAll(true);
		return getMapper().queryByList(model);
	}
	
	public List<SiteNewsColumn> queryAllColumnWithChild() throws Exception {
		SiteNewsColumnModel model = new SiteNewsColumnModel();
		model.setQueryAll(true);
		List<SiteNewsColumn> list=(List<SiteNewsColumn>)getMapper().queryByList(model);
		List<SiteNewsColumn> pidList=new ArrayList();
		for(int i=0;i<list.size();i++){
			if(list.get(i).getPid()==null){
				pidList.add(list.get(i));
			}
		}
		for(int i=0;i<pidList.size();i++){
			for(int j=0;j<list.size();j++){
				if(pidList.get(i).getId().equals(list.get(j).getPid())){
					pidList.get(i).getChildList().add(list.get(j));
				}
			}
		}
		
		return pidList;
	}
	
	public SiteNewsColumn getSiteNewsColumn(Integer id){
		return mapper.getSiteNewsColumn(id);
	}
	

	public SiteNewsColumnMapper<T> getMapper() {
		return mapper;
	}

	public List<SiteNewsColumn> findNewsColumnList(SiteNewsColumnModel model) {
		return getMapper().findNewsColumnList(model);
	}

}
