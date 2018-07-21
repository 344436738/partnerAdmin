package com.business.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.business.bean.StResumeExchangerecord;
import com.business.bean.StResumeScorerecord;
import com.business.bean.StWebUser;
import com.business.mapper.StResumeExchangerecordMapper;
import com.business.mapper.StResumeMapper;
import com.business.mapper.StResumeScorerecordMapper;
import com.business.mapper.StWebUserMapper;
import com.business.model.StWebUserModel;
import com.business.model.UserDetailedModel;
import com.base.service.BaseService;

/**
 * 
 * <br>
 * <b>功能：</b>StResumeScorerecordService<br>
 */
@Service("stResumeScorerecordService")
public class StResumeScorerecordService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(StResumeScorerecordService.class);
	
	
	@Autowired
    private StResumeExchangerecordMapper<T> Stmapper;

	@Autowired
    private StResumeScorerecordMapper<T> mapper;

	@Autowired
    private StWebUserMapper<T> mapperUse;
	
	@Autowired
    private StResumeMapper<T> resumeMapper;
	
	public StResumeMapper<T> getResumeMapper(){
		return resumeMapper;
	}
	
		
	public StResumeScorerecordMapper<T> getMapper() {
		return mapper;
	}

	public StWebUserMapper<T> getMapperUse() {
		return mapperUse;
	}
	
	public StResumeExchangerecordMapper<T> getStmapper() {
		return Stmapper;
	}
	
	@Autowired
    private StResumeScorerecordMapper<StResumeScorerecord> smapper;
	
	public StResumeScorerecordMapper<StResumeScorerecord> getSmapper(){
		return smapper;
	}	
	
	public void insert(StResumeScorerecord bean){
		smapper.add(bean);
	}
	
	
	public List<UserDetailedModel> queryOrderBy(StWebUserModel model){
		List<StResumeScorerecord>  list=new  ArrayList<StResumeScorerecord>();  
		StringBuffer ss=new StringBuffer();
		StringBuffer ss1=new StringBuffer();	
		String a="";
		
		
		if(model.getNickname()!=null&&model.getNickname()!=""){
			ss.append(" phone="+model.getNickname());
			List<StWebUser> uselist=(List<StWebUser>) getMapperUse().selectStWebUser(ss.toString());
			for(int s=0;s<uselist.size();s++){
				a=uselist.get(0).getId().toString();
			}
			ss1.append("  userid="+a);
			Integer rowCount = getMapper().queryOrderByCount(ss1.toString());
			model.getPager().setRowCount(rowCount);
			ss1.append(" LIMIT "+model.getPage()+" , "+model.getRows());
			list=getMapper().queryOrderBy(ss1.toString());
			
			
		}
		else if(model.getRecipient()!=null&&model.getPhone()!=null&&model.getRecipient()!=""&&model.getPhone()!=""){
			ss.append("  gettime BETWEEN '"+model.getRecipient()+"' AND '"+model.getPhone()+"'");
			Integer rowCount = getMapper().queryOrderByCount(ss.toString());
			model.getPager().setRowCount(rowCount);
			ss.append(" LIMIT "+model.getPage()+" , "+model.getRows());
			list=getMapper().queryOrderBy(ss.toString());
		}
		else if(model.getRecipient()!=null&&model.getPhone()!=null&&model.getRecipient()!=""&&model.getPhone()!=""&&model.getNickname()!=null&&model.getNickname()!=""){
			ss.append(" and gettime BETWEEN '"+model.getRecipient()+"' AND '"+model.getPhone()+"'");
			Integer rowCount = getMapper().queryOrderByCount(ss.toString());
			model.getPager().setRowCount(rowCount);
			ss.append(" LIMIT "+model.getPage()+" , "+model.getRows());
			list=getMapper().queryOrderBy(ss.toString());
		}
		else{
			Integer rowCount = getMapper().queryOrderByCount("1=1");
			model.getPager().setRowCount(rowCount);
			list=getMapper().queryOrderBy(" 1=1 order by userid LIMIT "+model.getPage()+" , "+model.getRows());
		}
		List<UserDetailedModel> listAdd=new ArrayList<UserDetailedModel>();
		for(int i=0;i<list.size();i++){
			UserDetailedModel mode=new UserDetailedModel();
			mode.setDateTime(list.get(i).getGettime());
			Double Fa=0d;
			if(list.get(i).getTotalscore()!=null){
				mode.setFaShengHouJf(list.get(i).getTotalscore().toString());
				Fa=list.get(i).getTotalscore();
			}
					
			Double FaH=list.get(i).getGetscore();
			Double s=0d;
			if(FaH!=null){
				s=Fa-list.get(i).getGetscore();
		
			}
			mode.setFaShengQianJf(s.toString());
			
			
			mode.setLaiYuan(list.get(i).getReson());
			
			StWebUser use=(StWebUser) getMapperUse().queryById(list.get(i).getUserid());
			if(list.get(i).getUserid()==3){
				StWebUser use1=(StWebUser) getMapperUse().queryById(list.get(i).getUserid());
			}
			mode.setName(use.getNickname());
			mode.setPhone(use.getPhone());
			StResumeExchangerecord stRes=new StResumeExchangerecord();
			stRes.setScorerecordId(list.get(i).getId());
			StResumeExchangerecord cord=(StResumeExchangerecord) getStmapper().scoreExchange(stRes);
			if(cord!=null){
				if(cord.getPaystatus()==1){
					mode.setXiaofeiJf(FaH.toString()+"(已支付)");
				}
				if(cord.getPaystatus()==0){
					mode.setXiaofeiJf(FaH.toString()+"(未支付)");
				}
			}
			listAdd.add(mode);
		}
		return listAdd;
		
		
	}
	
	/**
	 * 简历上传周统计
	 * 
	 * **/
	public List<StWebUserModel> queryZhoutongji(StWebUserModel model){
		
					List<StWebUserModel>  mode1=new ArrayList<StWebUserModel>();
					
					Integer rowCount = getMapperUse().queryByCountUser(" 1=1 ");
					model.getPager().setRowCount(rowCount);
				
					List<StWebUser> uselist=(List<StWebUser>) getMapperUse().selectStWebUser(" 1=1  LIMIT "+model.getPage()+" , "+model.getRows());	
					
					for(int i=0;i<uselist.size();i++){
						StringBuffer ss=new StringBuffer();
						StringBuffer ss1=new StringBuffer();
						StWebUserModel WebUser=new StWebUserModel();
						double a=0;
						int nuu=0;
						int ter=0;
							if(model.getRecipient()!=null&&model.getPhone()!=null&&model.getRecipient()!=""&&model.getPhone()!=""){
									ss.append(" uploadby="+uselist.get(i).getId());
									ss.append(" and uploadtime BETWEEN '"+model.getRecipient()+"' AND '"+model.getPhone()+"'");
									nuu=getResumeMapper().queryNumber(ss.toString());
									ter=getResumeMapper().queryNumber(ss.toString()+" and status=1 ");
									ss1.append(" userid="+uselist.get(i).getId()+" and status=1");
								
									List<StResumeExchangerecord> word= (List<StResumeExchangerecord>) getStmapper().tongJiResumeExchangerecord(ss1.toString());
					                for(int j=0;j<word.size();j++){
					                	if(word.get(j).getExchangescore()<0){
					                		a+=word.get(j).getExchangescore();
					                	}
					                }
								}  
							else{
								ss.append(" uploadby="+uselist.get(i).getId());
								nuu=getResumeMapper().queryNumber(ss.toString());
								ss1.append(" userid="+uselist.get(i).getId()+" and status=1");
								ter=getResumeMapper().queryNumber(ss.toString()+" and status=1 ");
								List<StResumeExchangerecord> word= (List<StResumeExchangerecord>) getStmapper().tongJiResumeExchangerecord(ss1.toString());
								if(word!=null&&word.size()!=0){
									for(int j=0;j<word.size();j++){
					                	if(word.get(j)!=null&&word.get(j).getExchangescore()<0){
					                		a+=word.get(j).getExchangescore();
					                	}
					                }
								}
				                
							}
					
					
			              List<StResumeScorerecord> aar=getMapper().queryOrderBy(" userid="+uselist.get(i).getId()+"  order by id desc");
				              double zong=0;
			              for(int g=0;g<aar.size();g++){
			            	  if(aar.get(0)!=null&&aar.get(0).getTotalscore()!=null)
			            	  zong=aar.get(0).getTotalscore();
			              }		
		              
		            	 WebUser.setResumeNumber(nuu);
			              WebUser.setNickname(uselist.get(i).getNickname());
			              WebUser.setResumeAdoptNumber(ter);
			              WebUser.setExchangeIntegral(a);
			              WebUser.setCurrentIntegration(zong);
			              WebUser.setPhone(uselist.get(i).getPhone());
			              WebUser.setId(uselist.get(i).getId());
			              mode1.add(WebUser);
		              
				}	
		
			
			return mode1;
			
	
		}
	public double  queryDouble(String str){
		return mapper.queryDouble(str);
	}
}
