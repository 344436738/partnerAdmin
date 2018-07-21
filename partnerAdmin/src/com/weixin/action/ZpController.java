package com.weixin.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.base.action.BaseAction;
import com.base.annotation.Auth;
import com.base.utils.HtmlUtil;
import com.base.utils.SessionUtils;
import com.base.utils.StringUtil;
import com.weixin.bean.WeixinHdrecord;
import com.weixin.bean.WeixinHuodong;
import com.weixin.bean.WeixinPrizerecord;
import com.weixin.model.WeixinHdrecordModel;
import com.weixin.model.WeixinHuodongModel;
import com.weixin.model.WeixinPrizerecordModel;
import com.weixin.service.WeixinHdrecordService;
import com.weixin.service.WeixinHuodongService;
import com.weixin.service.WeixinPrizerecordService;
import com.weixin.utils.HdUtils;


/**
 * 微信大转盘
* 
 */
@Controller
@RequestMapping("/zpController")
public class ZpController extends BaseAction{
	
	@Autowired(required=false) //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private WeixinHuodongService<WeixinHuodong> weixinHuodongService; 
	@Autowired(required=false) //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private WeixinHdrecordService<WeixinHdrecord> weixinHdrecordService; 
	@Autowired(required=false) //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private WeixinPrizerecordService<WeixinPrizerecord> weixinPrizerecordService; 
	
	/**
     * 刮刮乐
     * @param request
     * @return
	 * @throws Exception 
     */
	@Auth(verifyLogin = false, verifyURL = false)
    @RequestMapping(params="goGglNew")
	public ModelAndView goGglNew(HttpServletRequest request) throws Exception{
    	System.out.println("###############################");
    	Map<String,Object>  context = getRootMap();
//    	String id = request.getParameter("hdId");
    	String opendid = request.getParameter("openid");
    	String accountid = request.getParameter("accountid");
//    	org.jeecgframework.core.util.LogUtil.info("...opendId...."+opendId+"....hdId..."+id);
    	WeixinHuodongModel model=new WeixinHuodongModel();
    	model.setAccountid(StringUtil.ObjectToInteger(accountid));
    	model.setType("2");
    	List<WeixinHuodong>  hdlst= weixinHuodongService.queryByList(model);
    	WeixinHuodong bean = new WeixinHuodong();
		if(hdlst.size()!=0){
			bean = hdlst.get(0);
		}
    	if(bean!=null){
    		String gl = bean.getGl();
    		String glArr[] = gl.split("/");
    		int randomNum = HdUtils.createPrice(Integer.parseInt(glArr[0]),Integer.parseInt(glArr[1]));
    		//randomNum = 1;
    		context.put("prize", randomNum);
    		SessionUtils.setAttr(request, "hdid", bean.getId());
    		SessionUtils.setAttr(request, "accountid", accountid);
    		SessionUtils.setAttr(request, "opendid", opendid);
    		SessionUtils.setAttr(request, "prize", randomNum);
    		context.put("huodongEntity", bean);
    	}
    	
    	return forword("weixinHuodong/ggl",context);
	}
    
    /**
     * 刮刮乐
     * @param request
     * @return
     * @throws Exception 
     */
	@Auth(verifyLogin = false, verifyURL = false)
	@RequestMapping(params="doGgl")
    @ResponseBody
    public void doGgl(HttpServletRequest request,HttpServletResponse response) throws Exception{
//    	Timestamp nowTime = new Timestamp(new java.util.Date().getTime());
//    	String message = "";
//    	AjaxJson j = new AjaxJson();
		Map<String,Object> context = new HashMap<String, Object>();
		String hdid = SessionUtils.getAttr(request, "hdid").toString();
		String openid = SessionUtils.getAttr(request, "opendid").toString();
		
		String accountid = request.getParameter("accountid");
		WeixinHdrecordModel Hdrecordmodel=new WeixinHdrecordModel();
		Hdrecordmodel.setAccountid(StringUtil.ObjectToInteger(accountid));
		Hdrecordmodel.setHdid(StringUtil.ObjectToInteger(hdid));
		Hdrecordmodel.setOpenid(openid);
		List<WeixinHdrecord> hdRecrdList = weixinHdrecordService.queryByList(Hdrecordmodel);
		if(hdRecrdList.size()>0){
			WeixinHdrecord hdRecord = hdRecrdList.get(0);
			int total = hdRecord.getTotal();
			 WeixinHuodong huodongEntitiy = weixinHuodongService.queryById(hdid);
			if(total<Integer.parseInt(huodongEntitiy.getCount())){
				WeixinPrizerecordModel PrizeereCordmodel=new WeixinPrizerecordModel();
				PrizeereCordmodel.setAccountid(StringUtil.ObjectToInteger(accountid));
				PrizeereCordmodel.setHdid(StringUtil.ObjectToInteger(hdid));
				PrizeereCordmodel.setOppid(openid);
				List<WeixinPrizerecord> prizeList = weixinPrizerecordService.queryByList(PrizeereCordmodel);
			
				if(prizeList.size()>0){
					sendFailureMessage(response, "对不起本次活动你已经中奖，不能在抽奖！");
					return;
				}else{
					
					hdRecord.setTotal(total+1);
					weixinHdrecordService.updateBySelective(hdRecord);
					context.put("success", true);
					HtmlUtil.writerJson(response, context);
					return;
				}
			}else{
				sendFailureMessage(response, "对不起您已经抽奖"+total+"次，不能在抽奖！");
				return;
			}
		}else{
			WeixinHdrecord hdRecord = new WeixinHdrecord();
			hdRecord.setAddtime(new Date());
			hdRecord.setHdid(StringUtil.ObjectToInteger(hdid));
			hdRecord.setOpenid(openid);
			hdRecord.setTotal(1);
			hdRecord.setNickname("");
			hdRecord.setAccountid(StringUtil.ObjectToInteger(accountid));
			weixinHdrecordService.add(hdRecord);
		}
    }
    
    /**
	 * 保存刮刮乐抽奖记录和中奖记录
	 * @return
     * @throws Exception 
	*/
	@Auth(verifyLogin = false, verifyURL = false)
	@RequestMapping(params = "saveRecord")
	@ResponseBody
	public void saveRecord(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String mobile = request.getParameter("mobile");
		Object hdIdObj = session.getAttribute("hdid");
		Object opendIdObj = session.getAttribute("opendId");
		Object prizeObj =  session.getAttribute("prize");
		String accountid = request.getParameter("accountid");
		String prize = "";
		String hdid = "";
		String opendid = "";
		if(prizeObj!=null){
			prize = prizeObj.toString();
		}
		
		if(hdIdObj!=null){
			hdid = hdIdObj.toString();
		}
		
		if(opendIdObj!=null){
			opendid = opendIdObj.toString();
		}
		//Timestamp nowTime = new Timestamp(new java.util.Date().getTime());
		//org.jeecgframework.core.util.LogUtil.info("....prize...."+prize);
		if("1".equals(prize)||"2".equals(prize)||"3".equals(prize)){
			WeixinPrizerecord prizeEntity = new WeixinPrizerecord();
			prizeEntity.setHdid(StringUtil.ObjectToInteger(hdid));
			prizeEntity.setPrize(prize);
			prizeEntity.setMobile(mobile);
			prizeEntity.setAddtime(new Date());
			prizeEntity.setOppid(opendid);
			prizeEntity.setAccountid(StringUtil.ObjectToInteger(accountid));
			weixinPrizerecordService.add(prizeEntity);
		}
		sendSuccessMessage(response, "保存成功");
	}
	
	
	/**
	 * 大转盘
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@Auth(verifyLogin = false, verifyURL = false)
	@RequestMapping(params="goZhuanpan")
	public ModelAndView goZhuanpan(HttpServletRequest request) throws Exception{
		Map<String,Object>  context = getRootMap();
		String openid = request.getParameter("openid");
		String accountid = request.getParameter("accountid");
		
		//获取当前公众帐号的大转盘规则
		WeixinHuodongModel model=new WeixinHuodongModel();
    	model.setAccountid(StringUtil.ObjectToInteger(accountid));
    	model.setType("2");
    	List<WeixinHuodong>  hdlst= weixinHuodongService.queryByList(model);
    	WeixinHuodong hdEntity = new WeixinHuodong();
		if(hdlst.size()!=0){
			hdEntity = hdlst.get(0);
			//显示前台的活动规则
			request.setAttribute("hdEntity", hdEntity);
			request.setAttribute("hdid", hdEntity.getId());
			request.setAttribute("openid", openid);
			request.setAttribute("accountid", accountid);
			SessionUtils.setAttr(request, "opendid", openid);
		}
	
		return forword("weixinHuodong/zhuanpan", context);
	}
	
    /**
	 * 大转盘抽奖记录
	 * @return
     * @throws Exception 
	 */
	@Auth(verifyLogin = false, verifyURL = false)
	@RequestMapping(params = "getZpPize")
	@ResponseBody
	public void doZpPize(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String hdid =  request.getParameter("hdid");
		String openid = SessionUtils.getAttr(request, "opendid").toString();
		String accountid = request.getParameter("accountid");
		Map<String,Object> context = new HashMap<String, Object>();
		if(StringUtil.isNotEmpty(hdid)&&StringUtil.isNotEmpty(openid)){
			WeixinHuodong hdEntity = weixinHuodongService.queryById(hdid);
			WeixinHdrecordModel HdModel=new WeixinHdrecordModel();
			HdModel.setAccountid(StringUtil.ObjectToInteger(accountid));
			HdModel.setHdid(StringUtil.ObjectToInteger(hdid));
			HdModel.setOpenid(openid);
			List<WeixinHdrecord> hdRecrdList = weixinHdrecordService.queryByList(HdModel);
			if(hdEntity!=null){
				//String gl = hdEntity.getGl();
	    		//String glArr[] = gl.split("/");
	    		boolean randomNum1 = HdUtils.createPriced(hdEntity.getGlone());
	    		boolean randomNum2 = HdUtils.createPriced(hdEntity.getGltwo());
	    		boolean randomNum3 = HdUtils.createPriced(hdEntity.getGlthree());
	    		if(randomNum1){
	    			context.put("prizetype", randomNum1);
	    			//保存到session中奖后可以保存
	    			SessionUtils.setAttr(request, "hdid", hdid);
	        		SessionUtils.setAttr(request, "accountid", accountid);
	        		SessionUtils.setAttr(request, "opendid", openid);
	        		SessionUtils.setAttr(request, "prize", randomNum1);
	    		}else if(randomNum2){
	    			context.put("prizetype", randomNum2);
	    			//保存到session中奖后可以保存
	    			SessionUtils.setAttr(request, "hdid", hdid);
	        		SessionUtils.setAttr(request, "accountid", accountid);
	        		SessionUtils.setAttr(request, "opendid", openid);
	        		SessionUtils.setAttr(request, "prize", randomNum2);
	    		}else if(randomNum3){
	    			context.put("prizetype", randomNum3);
	    			//保存到session中奖后可以保存
	    			SessionUtils.setAttr(request, "hdid", hdid);
	        		SessionUtils.setAttr(request, "accountid", accountid);
	        		SessionUtils.setAttr(request, "opendid", openid);
	        		SessionUtils.setAttr(request, "prize", randomNum3);
	    		}
				if(hdRecrdList.size()>0){
					WeixinHdrecord hdRecord = hdRecrdList.get(0);
					int total = hdRecord.getTotal();
					if(total<Integer.parseInt(hdEntity.getCount())){
						
						WeixinPrizerecordModel PrizeereCordmodel=new WeixinPrizerecordModel();
						PrizeereCordmodel.setAccountid(StringUtil.ObjectToInteger(accountid));
						PrizeereCordmodel.setHdid(StringUtil.ObjectToInteger(hdid));
						PrizeereCordmodel.setOppid(openid);
						List<WeixinPrizerecord> prizeList = weixinPrizerecordService.queryByList(PrizeereCordmodel);
						if(prizeList.size()>0){
							
							context.put("error", "getsn");
							context.put("prizetype", prizeList.get(0).getPrize());
							context.put("success", false);
							HtmlUtil.writerJson(response, context);
							return;
						}else{
							hdRecord.setTotal(total+1);
							weixinHdrecordService.updateBySelective(hdRecord);
						}
						
					}else{
						
						context.put("error", "invalid");
						context.put("total", total);
						context.put("success", false);
						HtmlUtil.writerJson(response, context);
						return;
					}
				}else{
					
					WeixinHdrecord hdRecord = new WeixinHdrecord();
					hdRecord.setAddtime(new Date());
					hdRecord.setHdid(StringUtil.ObjectToInteger(hdid));
					hdRecord.setOpenid(openid);
					hdRecord.setTotal(1);
					hdRecord.setNickname("");
					hdRecord.setAccountid(StringUtil.ObjectToInteger(accountid));
					weixinHdrecordService.add(hdRecord);
				}
			}
		}
		context.put("success", true);
		HtmlUtil.writerJson(response, context);
	}
	
	/**
	 * 保存大转盘的中奖记录
	 * @return
	 * @throws Exception 
	 */
	@Auth(verifyLogin = false, verifyURL = false)
	@RequestMapping(params = "saveZpPrize")
	@ResponseBody
	public void saveZpPrize(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String mobile = request.getParameter("mobile");
		HttpSession session = request.getSession();
		Object hdIdObj = session.getAttribute("hdid");
		Object openIdObj = session.getAttribute("openId");
		Object prizeObj = session.getAttribute("prize");
		String hdid = "",openid="",prize="",accountid="";
		accountid = request.getParameter("accountid");
		if(hdIdObj!=null){
			hdid = hdIdObj.toString();
		}
		if(openIdObj!=null){
			openid = openIdObj.toString();
		}
		if(prizeObj!=null){
			prize = prizeObj.toString();
		}
		//Timestamp nowTime = new Timestamp(new java.util.Date().getTime());
		WeixinPrizerecord prizeEntity = new WeixinPrizerecord();
		prizeEntity.setHdid(StringUtil.ObjectToInteger(hdid));
		prizeEntity.setPrize(prize);
		prizeEntity.setMobile(mobile);
		prizeEntity.setAddtime(new Date());
		prizeEntity.setOppid(openid);
		prizeEntity.setAccountid(StringUtil.ObjectToInteger(accountid));
		weixinPrizerecordService.add(prizeEntity);
		
		sendSuccessMessage(response, "保存成功");
	}
	
	
    
    
}