package com.qy.sp.manage.controller.business;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
import com.qy.sp.manage.common.utils.StringUtil;
import com.qy.sp.manage.controller.base.BaseController;
import com.qy.sp.manage.dao.TChannelPipleDao;
import com.qy.sp.manage.dto.Page;
import com.qy.sp.manage.dto.TBlobContent;
import com.qy.sp.manage.dto.TChannel;
import com.qy.sp.manage.dto.TChannelPiple;
import com.qy.sp.manage.dto.TChannelPipleKey;
import com.qy.sp.manage.dto.TChannelProvinceLimit;
import com.qy.sp.manage.dto.TChannelProvinceLimitKey;
import com.qy.sp.manage.dto.TContract;
import com.qy.sp.manage.dto.TDic;
import com.qy.sp.manage.dto.THost;
import com.qy.sp.manage.dto.TPiple;
import com.qy.sp.manage.dto.TPipleMobileLimit;
import com.qy.sp.manage.dto.TPipleProduct;
import com.qy.sp.manage.dto.TPipleProvince;
import com.qy.sp.manage.dto.TSupplier;
import com.qy.sp.manage.dto.UserSession;
import com.qy.sp.manage.service.ChannelService;
import com.qy.sp.manage.service.ContractService;
import com.qy.sp.manage.service.DicService;
import com.qy.sp.manage.service.PipleService;
import com.qy.sp.manage.service.SupplierService;



@Controller
@RequestMapping(value = "/piple")
public class PipleController extends BaseController{

	private Logger log = Logger.getLogger(PipleController.class);
	
	@Resource
	private PipleService pipleService;
	
	@Resource
	private SupplierService supplierService;
	
	@Resource
	private ChannelService channelService;
	
	@Resource
	private ContractService contractService;
	
	@Resource
	private DicService dicService;
	
	@RequestMapping(value = "/toPipleList.do")
	public String toPipleList(HttpServletRequest request) throws Exception{
		String pageNumber = request.getParameter("pageNumber")==null?"1":request.getParameter("pageNumber");
		String pipleName = stringOf(request.getParameter("pipleName"));
		String supplierId = stringOf(request.getParameter("supplierId"));
		String pipleNumber = stringOf(request.getParameter("pipleNumber"));
		Integer hostId = isEmpty(request.getParameter("hostId"))?null:Integer.valueOf(request.getParameter("hostId"));
		Integer codeType = isEmpty(request.getParameter("codeType"))?null:Integer.valueOf(request.getParameter("codeType"));
		Integer pipleType = isEmpty(request.getParameter("pipleType"))?null:Integer.valueOf(request.getParameter("pipleType"));
		Integer calcType = isEmpty(request.getParameter("calcType"))?null:Integer.valueOf(request.getParameter("calcType"));
		Integer pluginType = isEmpty(request.getParameter("pluginType"))?null:Integer.valueOf(request.getParameter("pluginType"));
		Integer opStatus = isEmpty(request.getParameter("opStatus"))?null:Integer.valueOf(request.getParameter("opStatus"));
		UserSession uSession = (UserSession) request.getSession().getAttribute(Global.U_SESSION);
		TPiple piple = new TPiple();
		piple.setPipleName(pipleName);
		piple.setSupplierId(supplierId);
		piple.setPipleNumber(pipleNumber);
		piple.setHostId(hostId);
		piple.setCodeType(codeType);
		piple.setPipleType(pipleType);
		piple.setCalcType(calcType);
		piple.setPluginType(pluginType);
		piple.setOpStatus(opStatus);
		
		int items = pipleService.getPipleItems(piple,uSession);
		Page page = new Page();
		page.setPageAllCount(items);
		page.setPageNumber(Integer.parseInt(pageNumber));
		List<TPiple> piples = pipleService.getPipleList(piple, page,uSession);
		List<TSupplier> suppliers = supplierService.getAllSuppliers();
		List<THost> hosts = dicService.getAllHosts();
		List<TDic> pipleTypes = dicService.getDicsForDTypeId(Global.DicID.PIPLE_TYPE);
		List<TDic> calcTypes = dicService.getDicsForDTypeId(Global.DicID.CALC_TYPE);
		List<TDic> codeTypes = dicService.getDicsForDTypeId(Global.DicID.CODE_TYPE);
		List<TDic> pluginTypes = dicService.getDicsForDTypeId(Global.DicID.PLUGIN_TYPE);
		request.setAttribute("page", page);
		request.setAttribute("piple", piple);
		request.setAttribute("piples", piples);
		request.setAttribute("suppliers", suppliers);
		request.setAttribute("hosts", hosts);
		request.setAttribute("pipleTypes", pipleTypes);
		request.setAttribute("calcTypes", calcTypes);
		request.setAttribute("codeTypes", codeTypes);
		request.setAttribute("pluginTypes", pluginTypes);
		
		return "/piple/listPiple";
	}
	
	/**
	 * 跳转新增通道页面
	 * @throws Exception
	 * */
	@RequestMapping(value = "/toAddPiple.do")
	public String toAddPiple(HttpServletRequest request) throws Exception{
		List<TSupplier> suppliers = supplierService.getAllSuppliers();
		List<THost> hosts = dicService.getAllHosts();
		List<TDic> pipleTypes = dicService.getDicsForDTypeId(Global.DicID.PIPLE_TYPE);
		List<TDic> calcTypes = dicService.getDicsForDTypeId(Global.DicID.CALC_TYPE);
		List<TDic> codeTypes = dicService.getDicsForDTypeId(Global.DicID.CODE_TYPE);
		List<TDic> pluginTypes = dicService.getDicsForDTypeId(Global.DicID.PLUGIN_TYPE);
		request.setAttribute("suppliers", suppliers);
		request.setAttribute("hosts", hosts);
		request.setAttribute("pipleTypes", pipleTypes);
		request.setAttribute("calcTypes", calcTypes);
		request.setAttribute("codeTypes", codeTypes);
		request.setAttribute("pluginTypes", pluginTypes);
		return "/piple/updatePiple";
	}
	
	@RequestMapping(value = "/doUpdatePiple.do")
	public String doUpdatePiple(HttpServletRequest request) throws Exception{
		String pipleId = null;
		String pipleName = null;
		String supplierId = null;
		int opStatus = 0;
		String pipleUrlA = null;
		String pipleUrlB = null;
		String notifyUrlA = null;
		String notifyUrlB = null;
		String channelUrlA = null;
		String channelUrlB = null;
		String pipleAuthA = null;
		String pipleAuthB = null;
		String pipleAuthC = null;
		String pipleAuthD = null;
		String piplefId =null;
		String channelfId = null;
		String pluginfId = null;
		String testPluginfId = null;
		int hostId = 0;
		int codeType = 0;
		int pipleType = 0;
		int calcType = 0;
		int pluginType = 0;
		String pluginVersion = null; 
		double settlementNum = 0; // settlementRatio
		UserSession uSession = (UserSession) request.getSession().getAttribute(Global.U_SESSION);
		List<TBlobContent> bContents = new ArrayList<TBlobContent>();
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
				     if(fieldName.equals("pipleId")) pipleId = stringOf(fieldValue);
				     if(fieldName.equals("pipleName")) pipleName = stringOf(fieldValue);
				     if(fieldName.equals("supplierId")) supplierId = stringOf(fieldValue);
				     if(fieldName.equals("opStatus")) opStatus = intOf(fieldValue);
				     if(fieldName.equals("pipleUrlA")) pipleUrlA = stringOf(fieldValue);
				     if(fieldName.equals("pipleUrlB")) pipleUrlB = stringOf(fieldValue);
				     if(fieldName.equals("notifyUrlA")) notifyUrlA = stringOf(fieldValue);
				     if(fieldName.equals("notifyUrlB")) notifyUrlB = stringOf(fieldValue);
				     if(fieldName.equals("channelUrlA")) channelUrlA = stringOf(fieldValue);
				     if(fieldName.equals("channelUrlB")) channelUrlB = stringOf(fieldValue);
				     if(fieldName.equals("pipleAuthA")) pipleAuthA = stringOf(fieldValue);
				     if(fieldName.equals("pipleAuthB")) pipleAuthB = stringOf(fieldValue);
				     if(fieldName.equals("pipleAuthC")) pipleAuthC = stringOf(fieldValue);
				     if(fieldName.equals("pipleAuthD")) pipleAuthD = stringOf(fieldValue);
				     if(fieldName.equals("hostId")) hostId = intOf(fieldValue);
				     if(fieldName.equals("codeType")) codeType = intOf(fieldValue);
				     if(fieldName.equals("pipleType")) pipleType = intOf(fieldValue);
				     if(fieldName.equals("calcType")) calcType = intOf(fieldValue);
				     if(fieldName.equals("pluginType")) pluginType = intOf(fieldValue);
				     if(fieldName.equals("pluginVersion")) pluginVersion = stringOf(fieldValue);
				     if(fieldName.equals("settlementNum")) settlementNum = doubleOf(fieldValue);
			    } else {
			    	  log.info("上传文件的大小:" + item.getSize());
			    	  log.info("上传文件的类型:" + item.getContentType());
			    	  log.info("上传文件的名称:" + item.getName());
				      String fileName = item.getName(); // 文件名
				      String fieldName = item.getFieldName(); // 参数名
				      InputStream fileStr = item.getInputStream();
				      byte[] fileByte = FileUtils.InputStreamToByte(fileStr);
				       if(fieldName.equals("piplef") && fileByte.length!=0){ // 通道文档
				    	TBlobContent pipleBC = new TBlobContent();
				    	piplefId = KeyHelper.createKey();
				    	pipleBC.setFileId(piplefId);
				    	pipleBC.setFilename(fileName);
				    	pipleBC.setFileContent(fileByte);
				    	bContents.add(pipleBC);
				      }else if(fieldName.equals("channelf") && fileByte.length!=0){ // 渠道文档
				    	TBlobContent channelBC = new TBlobContent();
				    	channelfId = KeyHelper.createKey();
				    	channelBC.setFileId(channelfId);
				    	channelBC.setFilename(fileName);
				    	channelBC.setFileContent(fileByte);
				    	bContents.add(channelBC);
				      }else if(fieldName.equals("pluginf") && fileByte.length!=0){ // 正式插件
					    	TBlobContent pluginBC = new TBlobContent();
					    	pluginfId = KeyHelper.createKey();
					    	pluginBC.setFileId(pluginfId);
					    	pluginBC.setFilename(fileName);
					    	pluginBC.setFileContent(fileByte);
					    	bContents.add(pluginBC);
					   }else if(fieldName.equals("testPluginf") && fileByte.length!=0){ // 测试插件
					    	TBlobContent pluginBC = new TBlobContent();
					    	testPluginfId = KeyHelper.createKey();
					    	pluginBC.setFileId(testPluginfId);
					    	pluginBC.setFilename(fileName);
					    	pluginBC.setFileContent(fileByte);
					    	bContents.add(pluginBC);
					   }
			    }
		    }
		   TPiple piple = null;
		   if(pipleId!=null && !"".equals(pipleId)){
			   piple = pipleService.getPipleByPipleId(pipleId);
			   if(piple==null) { // 则pipleId为指定ID
			    	 piple = new TPiple();
					   piple.setPipleId(pipleId);
			   }
		   }else{
			   piple = new TPiple();
			   piple.setPipleId(KeyHelper.createKey());
		   }
			piple.setPipleName(pipleName);
			piple.setSupplierId(supplierId);
			piple.setOpStatus(opStatus);
			piple.setPipleUrlA(pipleUrlA);
			piple.setPipleUrlB(pipleUrlB);
			piple.setNotifyUrlA(notifyUrlA);
			piple.setNotifyUrlB(notifyUrlB);
			piple.setChannelUrlA(channelUrlA);
			piple.setChannelUrlB(channelUrlB);
			piple.setPipleAuthA(pipleAuthA);
			piple.setPipleAuthB(pipleAuthB);
			piple.setPipleAuthC(pipleAuthC);
			piple.setPipleAuthD(pipleAuthD);
			piple.setPipleDoc(piplefId);
			piple.setChannelDoc(channelfId);
			piple.setPluginId(pluginfId);
			piple.setPluginVersion(pluginVersion);
			piple.setTestPluginId(testPluginfId);
			piple.setHostId(hostId);
			piple.setPipleType(pipleType);
			piple.setCalcType(calcType);
			piple.setCodeType(codeType);
			piple.setPluginType(pluginType==0?null:pluginType);
			piple.setSettlementRatio(settlementNum==0?0:settlementNum/100);
//			 if(piple.getPipleNumber()==null || "".equals(piple.getPipleNumber())){
//				   piple.setPipleNumber(pipleNumber);
//			   }
			pipleService.updatePiple(piple, bContents,uSession);
			return "redirect:/piple/toPipleList.do";
		    }catch (Exception e) {
				e.printStackTrace();
				return null;
			}
	}
	
	/**
	 * 验证通道名称是否已经存在
	 * @throws Exception
	 */
	@RequestMapping(value = "/verifyPipleName.do")
	public void verifyPipleName(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String pipleName = stringOf(new String(request.getParameter("pipleName").getBytes("iso-8859-1"),"utf-8"));
		String pipleId = request.getParameter("pipleId");
		if(!"".equals(pipleId) && pipleId!=null){
			TPiple piple = pipleService.getPipleByPipleId(pipleId);
			if(pipleName.equals(piple.getPipleName())){
				response.getWriter().write("OK");
				return;
			}
		}
		TPiple piple = pipleService.getPipleByPipleName(pipleName);
		if(piple!=null){
			response.getWriter().write("FAIL");
		}else{
			response.getWriter().write("OK");
		}
	}
	
	/**
	 * 验证通道ID是否已经存在
	 * @throws Exception
	 */
	@RequestMapping(value = "/verifyPipleId.do")
	public void verifyPipleId(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String pipleId = request.getParameter("pipleId");
		String pipleNumber = stringOf(request.getParameter("pipleNumber"));
		TPiple piple = pipleService.getPipleByPipleId(pipleId);
		if(piple!=null && StringUtil.isEmptyString(pipleNumber)){
			response.getWriter().write("FAIL");
		}else{
			response.getWriter().write("OK");
		}
	}
	
	@RequestMapping(value = "/toUpdatePiple.do")
	public String toUpdatePiple(HttpServletRequest request) throws Exception{
		String pipleId = request.getParameter("pipleId");
		TPiple piple = pipleService.getPipleByPipleId(pipleId);
		List<THost> hosts = dicService.getAllHosts();
		List<TDic> pipleTypes = dicService.getDicsForDTypeId(Global.DicID.PIPLE_TYPE);
		List<TDic> calcTypes = dicService.getDicsForDTypeId(Global.DicID.CALC_TYPE);
		List<TDic> codeTypes = dicService.getDicsForDTypeId(Global.DicID.CODE_TYPE);
		List<TDic> pluginTypes = dicService.getDicsForDTypeId(Global.DicID.PLUGIN_TYPE);
		List<TSupplier> suppliers = supplierService.getAllSuppliers();
		request.setAttribute("suppliers", suppliers);
		request.setAttribute("piple", piple);
		request.setAttribute("hosts", hosts);
		request.setAttribute("pipleTypes", pipleTypes);
		request.setAttribute("calcTypes", calcTypes);
		request.setAttribute("codeTypes", codeTypes);
		request.setAttribute("pluginTypes", pluginTypes);
		return "/piple/updatePiple";
	}
	
	@RequestMapping(value = "/toPipleProList.do")
	public String toListPiplePro(HttpServletRequest request) throws Exception{
		String pipleId = request.getParameter("pipleId");
		List<TPipleProduct> piplePros = pipleService.getPipleProList(pipleId);
		TPiple piple = pipleService.getPipleByPipleId(pipleId);
		request.setAttribute("piple", piple);
		request.setAttribute("piplePros", piplePros);
		return "/piple/listPiplePro";
	}
	
	@RequestMapping(value = "/doUpdatePiplePro.do")
	public void doUpdatePiplePro(HttpServletRequest request,HttpServletResponse response) throws Exception{
		try {
			String pipleId = request.getParameter("pipleId");
			List<TPipleProduct> piplePros = pipleService.getPipleProList(pipleId);
			List<TPipleProduct> newPPors = new ArrayList<TPipleProduct>();
			for(TPipleProduct pp:piplePros){
				TPipleProduct ppx  = new TPipleProduct();
				String id = pp.getPipleId()+pp.getProductId();
				String ppCodex = request.getParameter("pipleProductCode"+id);
				String ppACodex = request.getParameter("pipleProductAbbrCode"+id);
				int opStatusx = intOf(request.getParameter("opStatus"+id));
				ppx.setPipleId(pp.getPipleId());
				ppx.setProductId(pp.getProductId());
				ppx.setPipleProductCode(ppCodex);
				ppx.setPipleProductAbbrCode(ppACodex);
				ppx.setOpStatus(opStatusx);
				newPPors.add(ppx);
			}
			pipleService.updatePiplePros(newPPors);
			response.getWriter().write("OK");
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().write("FAIL");
		}
	}
	
	@RequestMapping(value = "/toPipleChannelList.do")
	public String toListPipleChannel(HttpServletRequest request) throws Exception{
		String pipleId = request.getParameter("pipleId");
		List<TChannelPiple> channelPiples = pipleService.getChannelPipleList(pipleId);
		TPiple piple = pipleService.getPipleByPipleId(pipleId);
		request.setAttribute("piple", piple);
		request.setAttribute("channelPiples", channelPiples);
		return "/piple/listPipleChannel";
	}
	
	@RequestMapping(value = "/toAddChannelPiple.do")
	public String toAddChannelPiple(HttpServletRequest request) throws Exception{
		String pipleId = request.getParameter("pipleId");
//		UserSession uSession = (UserSession) request.getSession().getAttribute(Global.U_SESSION);
//		List<TChannel> channels = channelService.getChannelsByUserId(uSession.getUserId());
		List<TChannel> channels =channelService.getAllChannels();
		request.setAttribute("pipleId", pipleId);
		request.setAttribute("channels", channels);
		return "/piple/updatePipleChannel";
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
		TChannel channel = channelService.getChannelById(channelId);
		request.setAttribute("pipleId", pipleId);
		request.setAttribute("channel", channel);
		request.setAttribute("channelPiple", channelPiple);
		return "/piple/updatePipleChannel";
	}
	
	@RequestMapping(value = "/doDeleteChannelPiple.do")
	public String doDeleteChannelPiple(HttpServletRequest request) throws Exception{
		String pipleId = request.getParameter("pipleId");
		String channelId = request.getParameter("channelId");
		TChannelPipleKey key = new TChannelPipleKey();
		key.setChannelId(channelId);
		key.setPipleId(pipleId);
		pipleService.deleteChannelPipleByKey(key);
		return "redirect:/piple/toPipleChannelList.do?pipleId="+pipleId;
	}
	
	@RequestMapping(value = "/toPipleContractlList.do")
	public String toPipleContractlList(HttpServletRequest request) throws Exception{
		String pipleId = request.getParameter("pipleId");
		List<TContract> contracts = contractService.getContractListByPipId(pipleId);
		TPiple piple = pipleService.getPipleByPipleId(pipleId);
		request.setAttribute("piple", piple);
		request.setAttribute("contracts", contracts);
		return "/piple/listPipleContract";
	}
	
	@RequestMapping(value = "/toAddPipleContract.do")
	public String toAddPipleContract(HttpServletRequest request) throws Exception{
		String pipleId = request.getParameter("pipleId");
		request.setAttribute("pipleId", pipleId);
		return "/piple/updatePipleContract";
	}
	
	@RequestMapping(value = "/toUpdatePipleContract.do")
	public String toUpdatePipleContract(HttpServletRequest request) throws Exception{
		String pipleId = request.getParameter("pipleId");
		String contractId = request.getParameter("contractId");
		TContract contract = contractService.getContractById(contractId);
		request.setAttribute("pipleId", pipleId);
		request.setAttribute("contract", contract);
		return "/piple/updatePipleContract";
	}
	
	@RequestMapping(value = "/doUpdatePipleContract.do")
	public void doUpdatePipleContract(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String pipleId = null;
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
				     if(fieldName.equals("pipleId")) pipleId = stringOf(fieldValue);
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
		   TPiple piple = new TPiple();
		   piple.setPipleId(pipleId);
		   piple.setContractId(fileId);
		   TContract contract =  new TContract();
		   contract.setPipleId(pipleId);
		   contract.setContractId(contractId);
		   contract.setPartyA(partyA);
		   contract.setPartyB(partyB);
		   contract.setPartyC(partyC);
		   contract.setValidateStart(validateStart);
		   contract.setValidateEnd(validateEnd);
		   contract.setSignDate(signDate);
		   contract.setOpStatus(opStatus);
		   contract.setContractType(Global.ContractType.PIPLE);
		   contract.setFileId(fileId);
		   contractService.updatePipleContract(piple, contract, contractBC);
			response.getWriter().write("OK");
		    }catch (Exception e) {
				e.printStackTrace();
				response.getWriter().write(e.getMessage());
			}
	}

	@RequestMapping(value = "/toPipleProvinceList.do")
	public String toListPipleProvince(HttpServletRequest request) throws Exception{
		String pipleId = request.getParameter("pipleId");
		List<TPipleProvince> pipleProvinces = pipleService.getPipleProvinceList(pipleId);
		TPiple piple = pipleService.getPipleByPipleId(pipleId);
		request.setAttribute("piple", piple);
		request.setAttribute("pipleProvinces", pipleProvinces);
		return "/piple/listPipleProvince";
	}
	
	@RequestMapping(value = "/doUpdatePipleProvince.do")
	public void doUpdatePipleProvince(HttpServletRequest request,HttpServletResponse response) throws Exception{
		try {
			String pipleId = stringOf(request.getParameter("pipleId"));
			List<TPipleProvince> pps = pipleService.getPipleProvinceList(pipleId);
			List<TPipleProvince> newpps = new ArrayList<TPipleProvince>();
			for(TPipleProvince pp:pps){
				TPipleProvince ppx  = new TPipleProvince();
				int id = pp.getProvinceId();
				int opStatusx = intOf(request.getParameter("opStatus"+id));
				ppx.setPipleId(pipleId);
				ppx.setProvinceId(pp.getProvinceId());
				ppx.setPriority(pp.getPriority());
				ppx.setOpStatus(opStatusx);
				newpps.add(ppx);
			}
			pipleService.updatePipleProvince(pipleId, newpps);
			response.getWriter().write("OK");
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().write("FAIL");
		}
	}
	
	
	@RequestMapping(value = "/toChannelProvinceLimit.do")
	public String toChannelProvinceLimit(HttpServletRequest request) throws Exception{
		String pipleId = request.getParameter("pipleId");
		String channelId = request.getParameter("channelId");
		List<TPipleProvince> pipleProvinces = pipleService.getPipleProvinceList(pipleId);  
		TChannelProvinceLimitKey key = new TChannelProvinceLimitKey();
		key.setPipleId(pipleId);
		key.setChannelId(channelId);
		List<TChannelProvinceLimit> cpls = pipleService.getListByPipleIdAndChannelId(key);
		TPiple piple = pipleService.getPipleByPipleId(pipleId);
		TChannel channel = channelService.getChannelById(channelId);
		request.setAttribute("piple", piple);
		request.setAttribute("channel", channel);
		request.setAttribute("pipleProvinces", pipleProvinces);
		request.setAttribute("cpls", cpls);
		return "/piple/updateChannelProvinceLimit";
	}
	
	@RequestMapping(value = "/doUpdateChannelProvinceLimit.do")
	public void doUpdateChannelProvinceLimit(HttpServletRequest request,HttpServletResponse response) throws Exception{
		try {
			String pipleId = stringOf(request.getParameter("pipleId"));
			String channelId = stringOf(request.getParameter("channelId"));
			TChannelPipleKey cpk = new TChannelPipleKey();
			cpk.setChannelId(channelId);
			cpk.setPipleId(pipleId);
			TChannelPiple cPiple = pipleService.getChannelPipleByKey(cpk);
			TChannelProvinceLimitKey key = new TChannelProvinceLimitKey();
			key.setPipleId(pipleId);
			key.setChannelId(channelId);
			List<TChannelProvinceLimit> cpls = pipleService.getListByPipleIdAndChannelId(key);
			List<TChannelProvinceLimit> newcpls = new ArrayList<TChannelProvinceLimit>();
			boolean isExists = false;
			double sumtradeDay = 0;
			double sumtradeMonth = 0;
			if(cpls!=null && cpls.size()!=0){
				for (TChannelProvinceLimit cpl : cpls) {
					TChannelProvinceLimit nc  = new TChannelProvinceLimit();
					nc.setPipleId(pipleId);
					nc.setChannelId(channelId);
					nc.setProvinceId(cpl.getProvinceId());
					if(cpl.getOpStatus()==Global.OP_STATUS.OPEN){
						int id = cpl.getProvinceId();
						int tradeDayx = intOf(request.getParameter("tradeDay"+id));
						int tradeMonthx = intOf(request.getParameter("tradeMonth"+id));
						nc.setTradeDay(tradeDayx);
						nc.setTradeMonth(tradeMonthx);
						sumtradeDay =sumtradeDay+tradeDayx;
						sumtradeMonth =sumtradeMonth+tradeMonthx;
					}else{
						nc.setTradeDay(cpl.getTradeDay());
						nc.setTradeMonth(cpl.getTradeMonth());
					}
					newcpls.add(nc);
				}
				isExists = true;
			}else{
				List<TPipleProvince> pipleProvinces = pipleService.getPipleProvinceList(pipleId); 
				for (TPipleProvince pp : pipleProvinces) {
					TChannelProvinceLimit nc = new TChannelProvinceLimit();
					nc.setPipleId(pipleId);
					nc.setChannelId(channelId);
					nc.setProvinceId(pp.getProvinceId());
					if(pp.getOpStatus()==Global.OP_STATUS.OPEN){
						int id = pp.getProvinceId();
						int tradeDayx = intOf(request.getParameter("tradeDay"+id));
						int tradeMonthx = intOf(request.getParameter("tradeMonth"+id));
						nc.setTradeDay(tradeDayx);
						nc.setTradeMonth(tradeMonthx);
						sumtradeDay =sumtradeDay+tradeDayx;
						sumtradeMonth =sumtradeMonth+tradeMonthx;
					}else{// 未开通省份限量默认为0
						nc.setTradeDay(0);
						nc.setTradeMonth(0);
					}
					newcpls.add(nc);
				}
			}
			double dayst = 0;
			double monthst = 0;
			if(sumtradeDay>cPiple.getTradeDay() && cPiple.getTradeDay()!=0){
				response.getWriter().write("DAYEXCESS"+"|"+cPiple.getTradeDay());
				return;
			}else if(cPiple.getTradeDay()==0){
				 dayst = -1;
			}else{
				 dayst = cPiple.getTradeDay()-sumtradeDay;
			}
			
			if(sumtradeMonth>cPiple.getTradeMonth() && cPiple.getTradeDay()!=0){
				response.getWriter().write("MONTHEXCESS"+"|"+cPiple.getTradeMonth());
				return;
			}else if(cPiple.getTradeMonth()==0){
				 monthst = -1;
			}else{
				monthst = cPiple.getTradeMonth()-sumtradeDay;
			}
			pipleService.updateChannelProvinceLimits(newcpls, isExists);
			response.getWriter().write("OK"+"|"+dayst+"|"+monthst);
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().write("FAIL"+"|"+e.getMessage());
		}
	}
	
	@RequestMapping(value = "/toUpdatePipleMobileLimit.do")
	public String toUpdatePipleMobileLimit(HttpServletRequest request) throws Exception{
		String pipleId = request.getParameter("pipleId");
		TPiple piple = pipleService.getPipleByPipleId(pipleId);
		TPipleMobileLimit pMobileLimit = pipleService.getPipleMobileLimit(pipleId);
		request.setAttribute("pipleId", pipleId);
		request.setAttribute("piple", piple);
		request.setAttribute("pMobileLimit", pMobileLimit);
		return "/piple/updatePipleMobileLimit";
	}
	
	@RequestMapping(value="/doUpdatePipleMobileLimit.do")
	public void doUpdatePipleMobileLimit(HttpServletRequest request,HttpServletResponse response) throws Exception{
		try {
			String pipleId = request.getParameter("pipleId");
			int tradeDay = intOf(request.getParameter("tradeDay"));
			int tradeMonth = intOf(request.getParameter("tradeMonth"));
			int successNumDay = intOf(request.getParameter("successNumDay"));
			int successNumMonth = intOf(request.getParameter("successNumMonth"));
			int reqNumDay = intOf(request.getParameter("reqNumDay"));
			int reqNumMonth = intOf(request.getParameter("reqNumMonth"));
			TPipleMobileLimit pMobileLimit = new TPipleMobileLimit();
			pMobileLimit.setPipleId(pipleId);
			pMobileLimit.setTradeDay(tradeDay);
			pMobileLimit.setTradeMonth(tradeMonth);
			pMobileLimit.setSuccessNumDay(successNumDay);
			pMobileLimit.setSuccessNumMonth(successNumMonth);
			pMobileLimit.setReqNumDay(reqNumDay);
			pMobileLimit.setReqNumMonth(reqNumMonth);
			pipleService.updatePipleMobileLimit(pMobileLimit);
			response.getWriter().write("OK");
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().write("FAIL");
		}
	}
}
