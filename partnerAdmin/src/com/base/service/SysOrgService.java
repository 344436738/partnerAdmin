package com.base.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.base.bean.SysOrg;
import com.base.mapper.SysOrgMapper;
import com.base.model.SysOrgModel;

@Service("sysOrgService")
public class SysOrgService<T> extends BaseService<T>{

	
	@Autowired
	private SysOrgMapper<T> mapper;
	
	public SysOrgMapper<T> getMapper() {
		return mapper;
	}
	
	/**
	 * 查询所有组织机构
	 * @param orgModel
	 * @return
	 */

	public List<SysOrg> getOrgList(SysOrgModel orgModel,HttpServletRequest request){
		List<SysOrg> orgList = new ArrayList<SysOrg>();
		orgList = (List<SysOrg>) getMapper().getOrgList();
		orgList = seticon(orgList, request);//设置页面图标
		return orgList;
	}

	/**
	 * 查询组织机构下的人员
	 * @param orgModel
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SysOrg> queryByUser(SysOrgModel orgModel,HttpServletRequest request){
		List<SysOrg> orgList = (List<SysOrg>) getMapper().getOrgList();
		List<SysOrg> orgUserList = new ArrayList<SysOrg>();
		List<SysOrg> userList = getMapper().queryByUser(orgModel);//已将user添加别名至sys org
		Set<Integer> orgids=new HashSet();
//		if(orgModel.getName()!=null&&!"".equals(orgModel.getName())){//如果需要显示出无用户的机构，放开此行注释
			for(int i=0;i<userList.size();i++){
				Integer orgPid=userList.get(i).getPid();
				orgids.addAll(getMyParentsOrgIds(orgList,orgPid));
			}
			boolean removeIt;
			for(int i=0;i<orgList.size();i++){
				removeIt=true;
				Iterator it=orgids.iterator();
				//HashSet没有取出元素的方法,只能用迭代器
				while(it.hasNext()){
					if(orgList.get(i).getId().equals(it.next())){
						removeIt=false;
					}
				}
				if(removeIt){
					orgList.remove(i);
					i--;
				}
			}
//		}
			
		userList=setUsericon(userList,request);//设置页面图标
		orgUserList.addAll(userList);
		orgList = seticon(orgList,request);//设置页面图标
		orgList.addAll(orgUserList);
		return (List<SysOrg>) orgList;
	}
	
	/**
	 * 查询指定机构的所有父id
	 * @param orgList
	 * @param orgPid
	 * @return
	 */
	private List<Integer> getMyParentsOrgIds(List<SysOrg> orgList,Integer orgPid){
		List<Integer> parentOrgIds=new ArrayList();
		for(int i=0;i<orgList.size();i++){
			if(orgList.get(i).getId().equals(orgPid)){
				parentOrgIds.add(orgPid);
				parentOrgIds.addAll(getMyParentsOrgIds(orgList,orgList.get(i).getPid()));
			}
		}
		return parentOrgIds;
	}
	
	/**
	 * 修改组织机构
	 * @param sysOrg
	 */
	public void updateOrgNodes(SysOrg sysOrg){
		getMapper().updateOrgNodes(sysOrg);
	}
	
	/***
	 * 移动某组织机构
	 * @param sysOrg
	 */
	public void zTreeOnDrop(SysOrg sysOrg){
		getMapper().zTreeOnDrop(sysOrg);
	}
	
	/**
	 * 增加组织机构节点
	 * @param sysOrg
	 */
	public void addOrgNodes(SysOrg sysOrg){
		getMapper().addOrgNodes(sysOrg);
	}

	/**
	 * 获取单个节点信息
	 * @param id
	 * @return
	 */
	public SysOrg getOrgById(Integer id){
		return getMapper().getOrgById(id);
	}

	/**
	 *  设置机构前的图标，用于机构树
	 * @param userList
	 * @param request
	 * @return
	 */
	private List<SysOrg> setUsericon(List<SysOrg> userList,HttpServletRequest request){
		for(int i=0;i<userList.size();i++){
			userList.get(i).setIcon(request.getContextPath()+"/css/zTreeStyle/img/customer.png");
		}
		return userList;
	}
	
	private List<SysOrg> seticon(List<SysOrg> orgList,HttpServletRequest request){
		for(int i=0;i<orgList.size();i++){
			if(orgList.get(i).getPid().equals(0)){
				orgList.get(i).setIconOpen(request.getContextPath()+"/css/zTreeStyle/img/diy/1_open.png");
				orgList.get(i).setIconClose(request.getContextPath()+"/css/zTreeStyle/img/diy/1_close.png");
			}
		}
		return orgList;
	}
}
