package com.weixin.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.service.BaseService;
import com.base.utils.SessionUtils;
import com.sun.star.uno.RuntimeException;
import com.weixin.bean.WeixinAccount;
import com.weixin.mapper.WeixinAccountMapper;
import com.weixin.utils.WeiXinConstants;
import com.weixin.utils.WeixinUtil;

/**
 * 
 * <br>
 * <b>功能：</b>WeixinAccountService<br>
 */
@Service("weixinAccountService")
public class WeixinAccountService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(WeixinAccountService.class);
	

	

	@Autowired
    private WeixinAccountMapper<T> mapper;

		
	public WeixinAccountMapper<T> getMapper() {
		return mapper;
	}


	public List<WeixinAccount> findByUsername(String username) {
		
		return mapper.findByUsername(username);
	}
	

	
	public  void saveOrUpdate(WeixinAccount bean){
			mapper.update(bean);
		
	}
	
	public WeixinAccount findByToUsername(String toUserName) {
		//WeixinAccount weixinaccount = (WeixinAccount) SessionUtils.getAttr(request, WeiXinConstants.WEIXIN_ACCOUNT);
		//model.setAccountid(StringUtil.ObjectToInteger(weixinaccount));
		return mapper.findUniqueByProperty(toUserName);

	}
	/**
	 * 
	 * 通过userid获取账户信息
	 * 
	 **/
	
	public void setSessionAccount(Integer userid,HttpServletRequest request){
		
		WeixinAccount weixinaccount=mapper.queryByUserid(userid);
		SessionUtils.setAttr(request, WeiXinConstants.WEIXIN_ACCOUNT,  weixinaccount);
		
	}
	public String getAccessToken(HttpServletRequest request) throws Exception {
		String token = "";
		WeixinAccount account = findLoginWeixinAccount(request);
		token = account.getAccountaccesstoken();
		if (token != null && !"".equals(token)) {
			// 判断有效时间 是否超过2小时
			java.util.Date end = new java.util.Date();
			java.util.Date start = new java.util.Date(account.getADDTOEKNTIME()
					.getTime());
			if ((end.getTime() - start.getTime()) / 1000 / 3600 >= 2) {
				// 失效 重新获取
				String requestUrl = WeixinUtil.access_token_url.replace(
						"APPID", account.getAccountappid()).replace(
						"APPSECRET", account.getAccountappsecret());
				JSONObject jsonObject = WeixinUtil.httpRequest(requestUrl,
						"GET", null);
				if (null != jsonObject) {
					try {
						token = jsonObject.getString("access_token");
						// 重置token
						account.setAccountaccesstoken(token);
						// 重置事件
						account.setADDTOEKNTIME(new Date());
						this.saveOrUpdate(account);
					} catch (Exception e) {
						token = null;
						// 获取token失败
						String wrongMessage = "获取token失败 errcode:{} errmsg:{}"
								+ jsonObject.getInt("errcode")
								+ jsonObject.getString("errmsg");
						throw new RuntimeException(wrongMessage);
					}
				}
			} else {
				return account.getAccountaccesstoken();
			}
		} else {
			String requestUrl = WeixinUtil.access_token_url.replace("APPID",
					account.getAccountappid()).replace("APPSECRET",
					account.getAccountappsecret());
			JSONObject jsonObject = WeixinUtil.httpRequest(requestUrl, "GET",
					null);
			if (null != jsonObject) {
				try {
					token = jsonObject.getString("access_token");
					// 重置token
					account.setAccountaccesstoken(token);
					// 重置事件
					account.setADDTOEKNTIME(new Date());
					this.saveOrUpdate(account);
				} catch (Exception e) {
					token = null;
					// 获取token失败
					String wrongMessage = "获取token失败 errcode:{} errmsg:{}"
							+ jsonObject.getInt("errcode")
							+ jsonObject.getString("errmsg");
					throw new RuntimeException(wrongMessage);
				}
			}
		}
		return token;
	}
	
	
	public String getAccessToken(String weixinAccountId) throws Exception {
		String token = "";
		WeixinAccount account = findLoginWeixinAccount(weixinAccountId);
		token = account.getAccountaccesstoken();
		if (token != null && !"".equals(token)) {
			// 判断有效时间 是否超过2小时
			java.util.Date end = new java.util.Date();
			java.util.Date start = new java.util.Date(account.getADDTOEKNTIME()
					.getTime());
			if ((end.getTime() - start.getTime()) / 1000 / 3600 >= 2) {
				// 失效 重新获取
				String requestUrl = WeixinUtil.access_token_url.replace(
						"APPID", account.getAccountappid()).replace(
						"APPSECRET", account.getAccountappsecret());
				JSONObject jsonObject = WeixinUtil.httpRequest(requestUrl,
						"GET", null);
				if (null != jsonObject) {
					try {
						token = jsonObject.getString("access_token");
						// 重置token
						account.setAccountaccesstoken(token);
						// 重置事件
						account.setADDTOEKNTIME(new Date());
						this.saveOrUpdate(account);
					} catch (Exception e) {
						token = null;
						// 获取token失败
						String wrongMessage = "获取token失败 errcode:{} errmsg:{}"
								+ jsonObject.getInt("errcode")
								+ jsonObject.getString("errmsg");
						throw new RuntimeException(wrongMessage);
					}
				}
			} else {
				return account.getAccountaccesstoken();
			}
		} else {
			String requestUrl = WeixinUtil.access_token_url.replace("APPID",
					account.getAccountappid()).replace("APPSECRET",
					account.getAccountappsecret());
			JSONObject jsonObject = WeixinUtil.httpRequest(requestUrl, "GET",
					null);
			if (null != jsonObject) {
				try {
					token = jsonObject.getString("access_token");
					// 重置token
					account.setAccountaccesstoken(token);
					// 重置事件
					account.setADDTOEKNTIME(new Date());
					this.saveOrUpdate(account);
				} catch (Exception e) {
					token = null;
					// 获取token失败
					String wrongMessage = "获取token失败 errcode:{} errmsg:{}"
							+ jsonObject.getInt("errcode")
							+ jsonObject.getString("errmsg");
					throw new RuntimeException(wrongMessage);
				}
			}
		}
		return token;
	}
	
	/**
	 * @param request
	 * @return
	 */
	public WeixinAccount findLoginWeixinAccount(HttpServletRequest request) {
		WeixinAccount weixinaccount = (WeixinAccount) SessionUtils.getAttr(request, WeiXinConstants.WEIXIN_ACCOUNT);
		List<WeixinAccount> acclst = this.findByUsername(weixinaccount.getUSERNAME());
		WeixinAccount bean = acclst.size() != 0 ? acclst
				.get(0) : null;
		if (bean != null) {
			return bean;
		} else {
			WeixinAccount entity= new WeixinAccount();
			// 返回个临时对象，防止空指针
			entity.setWEIXIN_ACCOUNTID("-1");
			entity.setId(-1);
			return entity;
		}
	}
	
	
	/**
	 * @param request
	 * @return
	 */
	public WeixinAccount findLoginWeixinAccount(String weixinAccountId) {
		WeixinAccount bean = this.findByToUsername(weixinAccountId);
		if (bean != null) {
			return bean;
		} else {
			WeixinAccount entity= new WeixinAccount();
			// 返回个临时对象，防止空指针
			entity.setWEIXIN_ACCOUNTID("-1");
			entity.setId(-1);
			return entity;
		}
	}
	
}
