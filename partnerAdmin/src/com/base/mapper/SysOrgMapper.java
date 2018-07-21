package com.base.mapper;

import java.util.List;
import com.base.bean.SysOrg;
import com.base.model.SysOrgModel;


public interface SysOrgMapper<T> extends BaseMapper<T> {

	public List<T> getOrgList();
		
	public void updateOrgNodes(SysOrg sysOrg);
	
	public void addOrgNodes(SysOrg sysOrg);
	
	public SysOrg getOrgById(Integer id);
	
	public void zTreeOnDrop(SysOrg sysOrg);
	
	public List<Integer> getOrgByOrgId(Integer id);
	
	public List<SysOrg> queryByUser(SysOrgModel orgModel);	
}
