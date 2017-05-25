package com.qy.sp.manage.controller.business;

import java.io.InputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qy.sp.manage.common.utils.FileUtils;
import com.qy.sp.manage.common.utils.Global;
import com.qy.sp.manage.common.utils.KeyHelper;
import com.qy.sp.manage.controller.base.BaseController;
import com.qy.sp.manage.dto.Page;
import com.qy.sp.manage.dto.TBlobContent;
import com.qy.sp.manage.dto.TChannel;
import com.qy.sp.manage.dto.TChannelPiple;
import com.qy.sp.manage.dto.TChannelPipleKey;
import com.qy.sp.manage.dto.TContract;
import com.qy.sp.manage.dto.TPiple;
import com.qy.sp.manage.dto.UserSession;
import com.qy.sp.manage.service.ChannelService;
import com.qy.sp.manage.service.ContractService;
import com.qy.sp.manage.service.PipleService;

@Controller
@RequestMapping(value = "/channel")
public class ChannelController extends BaseController{
	
	private Logger log = Logger.getLogger(ChannelController.class);
	
	@Resource
	private ChannelService channelService;
	
	@Resource
	private ContractService contractService;
	
	@Resource
	private PipleService pipleService;
	
	@RequestMapping(value = "/toChannelList.do")
	public String toChannelList(HttpServletRequest request) throws Exception{
		String pageNumber = request.getParameter("pageNumber")==null?"1":request.getParameter("pageNumber");
		String fullName = stringOf(request.getParameter("fullName"));
		String tel = stringOf(request.getParameter("tel"));
		String apiKey = stringOf(request.getParameter("apiKey"));
		Integer opStatus = isEmpty(request.getParameter("opStatus"))?null:Integer.valueOf(request.getParameter("opStatus"));
		UserSession uSession = (UserSession) request.getSession().getAttribute(Global.U_SESSION);
		TChannel channel = new TChannel();
		channel.setFullName(fullName);
		channel.setTel(tel);
		channel.setApiKey(apiKey);
		channel.setOpStatus(opStatus);
		
		int items = channelService.getChannelItems(channel,uSession);
		Page page = new Page();
		page.setPageAllCount(items);
		page.setPageNumber(Integer.parseInt(pageNumber));
		List<TChannel> channels = channelService.getChannelList(channel, page,uSession);
		
		request.setAttribute("page", page);
		request.setAttribute("channel", channel);
		request.setAttribute("channels", channels);
		
		return "/channel/listChannel";
	}
	
	@RequestMapping(value = "/toAddChannel.do")
	public String toAddChannel(HttpServletRequest request)  throws Exception{
		return "/channel/updateChannel";
	}
	
	@RequestMapping(value = "/toUpdateChannel.do")
	public String toUpdateChannel(HttpServletRequest request)  throws Exception{
		String channelId = stringOf(request.getParameter("channelId"));
		TChannel channel = channelService.getChannelById(channelId);
		request.setAttribute("channel", channel);
		return "/channel/updateChannel";
	}
	
	/**
	 * 验证渠道全称是否已经存在
	 * @throws Exception
	 */
	@RequestMapping(value = "/verifyChannelName.do")
	public void verifyChannelName(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String fullName = stringOf(new String(request.getParameter("fullName").getBytes("iso-8859-1"),"utf-8"));
		String channelId = request.getParameter("channelId");
		if(!"".equals(channelId) && channelId!=null){
			TChannel channel = channelService.getChannelById(channelId);
			if(fullName.equals(channel.getFullName())){
				response.getWriter().write("OK");
				return;
			}
		}
		TChannel channel = channelService.getChannelByFullName(fullName);
		if(channel!=null){
			response.getWriter().write("FAIL");
		}else{
			response.getWriter().write("OK");
		}
	}
	
	@RequestMapping(value = "/doUpdateChannel.do")
	public void doUpdateChannel(HttpServletRequest request,HttpServletResponse response) throws Exception{
		try {
			String channelId = stringOf(request.getParameter("channelId"));
			String fullName = stringOf(request.getParameter("fullName"));
			String abbrName = stringOf(request.getParameter("abbrName"));
			int opStatus = intOf(request.getParameter("opStatus"));
			String contactor = stringOf(request.getParameter("contactor"));
			String tel = stringOf(request.getParameter("tel"));
			String email = stringOf(request.getParameter("email"));
			String qq = stringOf(request.getParameter("qq"));
			String apiPwd = stringOf(request.getParameter("apiPwd"));
			UserSession uSession = (UserSession) request.getSession().getAttribute(Global.U_SESSION);
			TChannel channel = new TChannel();
			channel.setChannelId(channelId);
			channel.setFullName(fullName);
			channel.setAbbrName(abbrName);
			channel.setOpStatus(opStatus);
			channel.setContactor(contactor);
			channel.setTel(tel);
			channel.setEmail(email);
			channel.setQq(qq);
			channel.setApiPwd(apiPwd);
			channelService.updateChannel(channel,uSession);
			response.getWriter().write("OK");
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().write("FAIL");
		}
	}
	
	@RequestMapping(value = "/toChannelContractlList.do")
	public String toChannelContractlList(HttpServletRequest request) throws Exception{
		String channelId = request.getParameter("channelId");
		List<TContract> contracts = contractService.getContractListByChannelId(channelId);
		TChannel channel = channelService.getChannelById(channelId);
		request.setAttribute("channel", channel);
		request.setAttribute("contracts", contracts);
		return "/channel/listChannelContract";
	}
	
	@RequestMapping(value = "/toAddChannelContract.do")
	public String toAddChannelContract(HttpServletRequest request) throws Exception{
		String channelId = request.getParameter("channelId");
		request.setAttribute("channelId", channelId);
		return "/channel/updateChannelContract";
	}
	
	@RequestMapping(value = "/toUpdateChannelContract.do")
	public String toUpdateChannelContract(HttpServletRequest request) throws Exception{
		String channelId = request.getParameter("channelId");
		String contractId = request.getParameter("contractId");
		TContract contract = contractService.getContractById(contractId);
		request.setAttribute("channelId", channelId);
		request.setAttribute("contract", contract);
		return "/channel/updateChannelContract";
	}
	
	@RequestMapping(value = "/doUpdateChannelContract.do")
	public void doUpdateChannelContract(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String channelId = null;
		String contractId = null;
		String partyA = null;
		String partyB = null;
		String partyC = null;
		Date validateStart = null;
		Date validateEnd = null;
		Date signDate = null;
		int opStatus = 0;
		String fileId = null;
		TBlobContent contractBC = null;
	 try {
		  request.setCharacterEncoding("UTF-8");
		  DiskFileItemFactory factory = new DiskFileItemFactory();
		  ServletFileUpload upload = new ServletFileUpload(factory);
		   List items = upload.parseRequest(request);
		   Iterator itr = items.iterator();
		   while (itr.hasNext()) {
			    FileItem item = (FileItem) itr.next();
			    if (item.isFormField()) {
			    	 String fieldName = item.getFieldName();
			    	 String fieldValue = item.getString("UTF-8");
				     log.info("表单参数名:" +fieldName+ "，表单参数值:" +fieldValue );
				     if(fieldName.equals("channelId")) channelId = stringOf(fieldValue);
				     if(fieldName.equals("contractId")) contractId = stringOf(fieldValue);
				     if(fieldName.equals("partyA")) partyA = stringOf(fieldValue);
				     if(fieldName.equals("partyB")) partyB = stringOf(fieldValue);
				     if(fieldName.equals("partyC")) partyC = stringOf(fieldValue);
				     if(fieldName.equals("validateStart")) validateStart = dateOf(fieldValue);
				     if(fieldName.equals("validateEnd")) validateEnd = dateOf(fieldValue);
				     if(fieldName.equals("signDate")) signDate = dateOf(fieldValue);
				     if(fieldName.equals("opStatus")) opStatus = intOf(fieldValue);
			    } else {
			    		log.info("上传文件的大小:" + item.getSize());
			    		log.info("上传文件的类型:" + item.getContentType());
			    		log.info("上传文件的名称:" + item.getName());
				      String fileName = item.getName(); // 文件名
				      String fieldName = item.getFieldName(); // 参数名
				      InputStream fileStr = item.getInputStream();
				      byte[] fileByte = FileUtils.InputStreamToByte(fileStr);
				       if(fieldName.equals("contractf") && fileByte.length!=0){ // 通道文档
				    	contractBC = new TBlobContent();
				    	fileId = KeyHelper.createKey();
				    	contractBC.setFileId(fileId);
				    	contractBC.setFilename(fileName);
				    	contractBC.setFileContent(fileByte);
				      }
			    }
		    }
		   TChannel channel = new TChannel();
		   channel.setChannelId(channelId);
		   channel.setContractId(fileId);
		   TContract contract =  new TContract();
		   contract.setChannelId(channelId);
		   contract.setContractId(contractId);
		   contract.setPartyA(partyA);
		   contract.setPartyB(partyB);
		   contract.setPartyC(partyC);
		   contract.setValidateStart(validateStart);
		   contract.setValidateEnd(validateEnd);
		   contract.setSignDate(signDate);
		   contract.setOpStatus(opStatus);
		   contract.setContractType(Global.ContractType.CHANNEL);
		   contract.setFileId(fileId);
		   contractService.updateChannelContract(channel, contract, contractBC);
			response.getWriter().write("OK");
		    }catch (Exception e) {
				e.printStackTrace();
				response.getWriter().write(e.getMessage());
			}
	}
	
	@RequestMapping(value = "/toChannelPipleList.do")
	public String toChannelPipleList(HttpServletRequest request) throws Exception{
		String channelId = stringOf(request.getParameter("channelId"));
		List<TChannelPiple> channelPiples = channelService.getChannelPipleList(channelId);
		TChannel channel = channelService.getChannelById(channelId);
		request.setAttribute("channelPiples", channelPiples);
		request.setAttribute("channel", channel);
		return "/channel/listChannelPiple";
	}
	
	@RequestMapping(value = "/toAddChannelPiple.do")
	public String toAddChannelPiple(HttpServletRequest request) throws Exception{
		String channelId = request.getParameter("channelId");
		List<TPiple> piples = pipleService.getAllPiples();
		request.setAttribute("channelId", channelId);
		request.setAttribute("piples", piples);
		return "/channel/updateChannelPiple";
	}
	
	@RequestMapping(value = "/doUpdatePipleChannel.do")
	public void doUpdatePipleChannel(HttpServletRequest request,HttpServletResponse response) throws Exception{
		try {
			String pipleId = stringOf(request.getParameter("pipleId"));
			String channelId = stringOf(request.getParameter("channelId"));
			String notifyUrl = stringOf(request.getParameter("notifyUrl"));
			int volt = intOf(request.getParameter("volt"));
			int tradeMonth = intOf(request.getParameter("tradeMonth"));
			int tradeDay = intOf(request.getParameter("tradeDay"));
			int opStatus = intOf(request.getParameter("opStatus"));
			double settlementNum = doubleOf(request.getParameter("settlementNum"));
			TChannelPiple channelPiple = new TChannelPiple();
			channelPiple.setPipleId(pipleId);
			channelPiple.setChannelId(channelId);
			channelPiple.setNotifyUrl(notifyUrl);
			channelPiple.setVolt(volt);
			channelPiple.setTradeDay(tradeDay);
			channelPiple.setTradeMonth(tradeMonth);
			channelPiple.setOpStatus(opStatus);
			channelPiple.setSettlementRatio(settlementNum==0?0:settlementNum/100);
			pipleService.updateChannelPiple(channelPiple);
			response.getWriter().write("OK");
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().write("FAIL");
		}
	}
	
	@RequestMapping(value = "/toUpdateChannelPiple.do")
	public String toUpdateChannelPiple(HttpServletRequest request) throws Exception{
		String pipleId = request.getParameter("pipleId");
		String channelId = request.getParameter("channelId");
		TChannelPipleKey key = new TChannelPipleKey();
		key.setChannelId(channelId);
		key.setPipleId(pipleId);
		TChannelPiple channelPiple = pipleService.getChannelPipleByKey(key);
		TPiple piple = pipleService.getPipleByPipleId(pipleId);
		request.setAttribute("channelId", channelId);
		request.setAttribute("piple", piple);
		request.setAttribute("channelPiple", channelPiple);
		return "/channel/updateChannelPiple";
	}
	
	@RequestMapping(value = "/doDeleteChannelPiple.do")
	public String doDeleteChannelPiple(HttpServletRequest request) throws Exception{
		String pipleId = request.getParameter("pipleId");
		String channelId = request.getParameter("channelId");
		TChannelPipleKey key = new TChannelPipleKey();
		key.setChannelId(channelId);
		key.setPipleId(pipleId);
		pipleService.deleteChannelPipleByKey(key);
		return "redirect:/channel/toChannelPipleList.do?channelId="+channelId;
	}
}
