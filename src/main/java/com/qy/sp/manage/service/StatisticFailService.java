package com.qy.sp.manage.service;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.bson.types.BasicBSONList;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.GroupCommand;
import com.qy.sp.manage.common.tools.NumberFormatTools;
import com.qy.sp.manage.common.tools.export.ExportGlobal;
import com.qy.sp.manage.common.tools.export.ReportBody;
import com.qy.sp.manage.common.tools.export.support.ReportDiv;
import com.qy.sp.manage.common.tools.export.support.ReportHeader;
import com.qy.sp.manage.common.utils.DateTimeUtils;
import com.qy.sp.manage.common.utils.Global;
import com.qy.sp.manage.common.utils.StringUtil;
import com.qy.sp.manage.dao.StaticDao;
import com.qy.sp.manage.dao.TChannelDao;
import com.qy.sp.manage.dao.TPipleDao;
import com.qy.sp.manage.dao.TSdkOperStaDao;
import com.qy.sp.manage.dao.TStaSdkDailyDao;
import com.qy.sp.manage.dao.TUserChannelDao;
import com.qy.sp.manage.dao.TUserPipleDao;
import com.qy.sp.manage.dao.TUserRoleDao;
import com.qy.sp.manage.dto.StaSdkDailySearchParam;
import com.qy.sp.manage.dto.TChannel;
import com.qy.sp.manage.dto.TPiple;
import com.qy.sp.manage.dto.TSdkOperSta;
import com.qy.sp.manage.dto.TStaSdkDaily;
import com.qy.sp.manage.dto.TUserRole;
import com.qy.sp.manage.dto.sta.RunDailyEntity;
import com.qy.sp.manage.dto.sta.SalesDailyEntity;
import com.qy.sp.manage.dto.sta.StaSdkDailyEntity;
import com.qy.sp.manage.dto.sta.StaticEntity;
import com.qy.sp.manage.dto.sta.StatisticBaseEntity;
import com.qy.sp.manage.dto.sta.StatisticSalesEntity;
import com.qy.sp.manage.entity.param.StaticCommonQueryParam;

@Service
public class StatisticFailService {

	@Resource
	private StaticDao staticDao;
	@Resource
	private TPipleDao tPipleDao;
	@Resource
	private TChannelDao tChannelDao;
	@Resource
	private TUserPipleDao tUserPipleDao;
	@Resource
	private TUserChannelDao tUserChannelDao;
	@Resource
	private TUserRoleDao tUserRoleDao;
	@Resource
	private TSdkOperStaDao tSdkOperStaDao;
	@Resource
	private TStaSdkDailyDao tStaSdkDailyDao;
	@Resource
	protected MongoTemplate mongoTemplate;
	public static final String MONGODB_SDK_PHONE = "mongodb_sdk_phone";
	
	public List<StaticEntity> getRunStatisticData(String userId,String pipleNumber, String pipleId, String channelId, String startDate, String endDate,boolean isProvince){		
		StaticCommonQueryParam param = new StaticCommonQueryParam();
		if(!StringUtil.isEmptyString(startDate)){
			Date sDate = DateTimeUtils.StringToDate(startDate, "yyyy-MM-dd");
			param.setStartDate(sDate);
		}
		if(!StringUtil.isEmptyString(endDate)){
			Date eDate = DateTimeUtils.StringToDate(endDate, "yyyy-MM-dd");
			param.setEndDate(eDate);			
		}
		
		if(!StringUtil.isEmptyString(pipleNumber)){
			param.setPipleNumber(pipleNumber);
		}
		if(!StringUtil.isEmptyString(pipleId)){
			param.setPipleId(pipleId);
		}
		if(!StringUtil.isEmptyString(channelId)){
			param.setChannelId(channelId);
		}
//		param.setUserId("1");
		List<String> channelIds = this.getChannelIds(userId);
		List<String> pipleIds = this.getPipleIds(userId);
		List<StaticEntity> listData = null;
		if(isProvince){
			listData = staticDao.getRunDataForProvince(param, pipleIds, channelIds);
		}else{
			listData = staticDao.selectByAdmin(param,pipleIds,channelIds);
		}
		
		for(int i = 0; i < listData.size(); i++){
			StaticEntity item = listData.get(i);
			item.setCompDateString(DateTimeUtils.formatTime(item.getCompDate(), "yyyy-MM-dd"));
			
			double totalCount = item.getCountToltal();//总条数不可能为0
			double totalSuccessCount = item.getCountChannel() + item.getCountDeducted();
			double successRate_preDeducted = totalSuccessCount / totalCount;
			double successRate_afterDeducted = item.getCountChannel() / totalCount;

			successRate_preDeducted = NumberFormatTools.round(successRate_preDeducted * 100, 2);
			successRate_afterDeducted = NumberFormatTools.round(successRate_afterDeducted * 100, 2);
			String preRate = successRate_preDeducted + "%";
			String afterRate = successRate_afterDeducted + "%";
			
			item.setSuccessRatePreDeducted(preRate);
			item.setSuccessRateAfterDeducted(afterRate);
		}
		
		Collections.reverse(listData);
		return listData;
	}
	
	
	public List<TPiple> getAllPiples(){
		return this.tPipleDao.getAllPiples(); 
	}
	
	public List<TChannel> getAllChannels(){
		return this.tChannelDao.getAllChannels();
	}
	
	public List<SalesDailyEntity> getSalesDailyData(String userId, String pipleNumber,String pipleId, String channelId, String startDate, String endDate){
		StaticCommonQueryParam param = new StaticCommonQueryParam();
		if(!StringUtil.isEmptyString(startDate)){
			Date sDate = DateTimeUtils.StringToDate(startDate, "yyyy-MM-dd");
			param.setStartDate(sDate);
		}
		if(!StringUtil.isEmptyString(endDate)){
			Date eDate = DateTimeUtils.StringToDate(endDate, "yyyy-MM-dd");
			param.setEndDate(eDate);			
		}
		if(!StringUtil.isEmptyString(pipleNumber)){
			param.setPipleNumber(pipleNumber);
		}
		if(!StringUtil.isEmptyString(pipleId)){
			param.setPipleId(pipleId);
		}
		if(!StringUtil.isEmptyString(channelId)){
			param.setChannelId(channelId);
		}
//		param.setUserId("1");
		List<String> channelIds = this.getChannelIds(userId);
		List<String> pipleIds = this.getPipleIds(userId);
		List<StatisticSalesEntity> listData = this.staticDao.selectSalesDailyByAdmin(param,pipleIds,channelIds);
		Map<StatisticBaseEntity, SalesDailyEntity> maps = new HashMap<StatisticBaseEntity, SalesDailyEntity>();
		for(StatisticSalesEntity iterator : listData){
			System.out.println("HAHA2");
			StatisticBaseEntity key = iterator.getStatisticBaseEntity();
			SalesDailyEntity value = maps.get(key);
			if(value == null){
				value = new SalesDailyEntity();
				value.setStatisticBaseEntity(iterator.getStatisticBaseEntity());
				maps.put(key, value);
			}

			int orderStatus = iterator.getOrderStatus();
			switch (orderStatus) {
				case Global.OrderStatus.FAIL:
					value.setAmountFail(value.getAmountFail() + iterator.getAmount());
					value.setCountFail(value.getCountFail() + iterator.getCount());
					break;
				case Global.OrderStatus.INIT:
				case Global.OrderStatus.TRADING:
					value.setAmountUnfinished(value.getAmountUnfinished() + iterator.getAmount());
					value.setCountUnfinished(value.getCountUnfinished() + iterator.getCount());
					break;
				case Global.OrderStatus.SUCCESS:
					if(iterator.getDeductedStatus() == Global.DEC_STATUS.DEDUCTED){
						value.setAmountDeducted(value.getAmountDeducted() + iterator.getAmount());
						value.setCountDeducted(value.getCountDeducted() + iterator.getCount());
						value.setAmountSuccess(value.getAmountSuccess()+value.getAmountDeducted());
						value.setCountSuccess(value.getCountSuccess()+value.getCountDeducted());
					}else{
						value.setAmountChannel(value.getAmountChannel() + iterator.getAmount());
						value.setCountChannel(value.getCountChannel() + iterator.getCount());
						value.setAmountSuccess(value.getAmountSuccess()+value.getAmountChannel());
						value.setCountSuccess(value.getCountSuccess()+value.getCountChannel());
					}
					break;
				default:
					break;
			}
			if(iterator.getPipleSetRatio()!=null){
				if(iterator.getPipleSetRatio()!=0){
					double pipleSetRatio = NumberFormatTools.round(iterator.getPipleSetRatio() * 100, 2);
					value.setPipleSetRatioStr(pipleSetRatio+"%");
				}
			}
			if(iterator.getChannelSetRatio()!=null){
				if(iterator.getChannelSetRatio()!=0){
					double channelSetRatio = NumberFormatTools.round(iterator.getChannelSetRatio() * 100, 2);
					value.setChannelSetRatioStr(channelSetRatio+"%");
				}
			}
		}

		//map to list
		List<SalesDailyEntity> datas = new ArrayList<SalesDailyEntity>();
		for(SalesDailyEntity item : maps.values()){
			item.initTotal();
			item.setCompDateString(DateTimeUtils.formatTime(item.getCompDate(), "yyyy-MM-dd"));
			
			Double amountChannel = item.getAmountChannel();
			Double amountDeducted = item.getAmountDeducted();
			Double amountTotal = item.getAmountTotal();
			Double amountUnFinished = item.getAmountUnfinished();
			Double amountFinished = amountTotal - amountUnFinished;
			if(amountFinished == 0){//所有交易都失败
				item.setRateDeducted("0.0%");
				item.setRatePreDeducted("0.0%");
			}else{
				Double ratePreDeducted = (amountChannel + amountDeducted) / amountTotal;
				ratePreDeducted = NumberFormatTools.round(ratePreDeducted * 100, 2);
				item.setRatePreDeducted(ratePreDeducted + "%");
				
				Double rateDeducted = amountChannel / amountTotal;
				rateDeducted = NumberFormatTools.round(rateDeducted * 100, 2);
				item.setRateDeducted(rateDeducted + "%");	
			}
			
			datas.add(item);
		}
		
		Collections.sort(datas);
		Collections.reverse(datas);
		return datas;
	}
	
	public List<RunDailyEntity> getRunDailyData(String userId, String pipleNumber,String pipleId, String channelId){
		StaticCommonQueryParam param = new StaticCommonQueryParam();
		if(!StringUtil.isEmptyString(pipleNumber)){
			param.setPipleNumber(pipleNumber);
		}
		if(!StringUtil.isEmptyString(pipleId)){
			param.setPipleId(pipleId);
		}
		if(!StringUtil.isEmptyString(channelId)){
			param.setChannelId(channelId);
		}
//		param.setUserId("1");
		List<String> channelIds = this.getChannelIds(userId);
		List<String> pipleIds = this.getPipleIds(userId);
		List<RunDailyEntity> listData = this.staticDao.getDayRunData(param,pipleIds,channelIds);
		return listData;
	}
	
	public List<String> getPipleIds(String userId){
		List<TUserRole> userRoles = tUserRoleDao.loadRoleByUserId(userId);
		boolean allPiple = false;
		for (TUserRole ur : userRoles) {
			if(ur.getRoleId().equals(Global.Roles_Fixed.PIPLE_ALL)){
				allPiple = true;
			}
		}
		List<String> pipleIds = new ArrayList<String>();
		if(!allPiple){
			pipleIds = tUserPipleDao.getPipleIdsByUserId(userId);
		}
		return pipleIds;
	}
	
	public List<String> getChannelIds(String userId){
		List<TUserRole> userRoles = tUserRoleDao.loadRoleByUserId(userId);
		boolean allChannel = false;
		for (TUserRole ur : userRoles) {
			if(ur.getRoleId().equals(Global.Roles_Fixed.CHANNEL_ALL)){
				allChannel = true;
			}
		}
		List<String> channelIds = new ArrayList<String>();
		if(!allChannel){
			channelIds = tUserChannelDao.getChannelIdsByUserId(userId);
		}
		return channelIds;
	}
	
	public ReportBody getRunDailyBody(List<RunDailyEntity> datas) throws Exception{
		ReportHeader _h0 = new ReportHeader(0,0,"通道编号",0,0,0);
		ReportHeader _h1 = new ReportHeader(0,1,"通道名称",0,0,0);
		ReportHeader _h2 = new ReportHeader(0,2,"渠道名称",0,0,0);
		ReportHeader _h3 = new ReportHeader(0,3,"计费点",0,0,0);
		ReportHeader _h4 = new ReportHeader(0,4,"总金额",0,0,0);
		ReportHeader _h5 = new ReportHeader(0,5,"未完成金额",0,0,0);
		ReportHeader _h6 = new ReportHeader(0,6,"未上行金额",0,0,0);
		ReportHeader _h7 = new ReportHeader(0,7,"失败金额",0,0,0);
		ReportHeader _h8 = new ReportHeader(0,8,"扣量前成功金额",0,0,0);
		ReportHeader _h9 = new ReportHeader(0,9,"扣量后成功金额",0,0,0);
		ReportHeader _h10 = new ReportHeader(0,10,"扣量金额",0,0,0);
		ReportHeader _h11 = new ReportHeader(0,11,"扣量前成功率",0,0,0);
		ReportHeader _h12 = new ReportHeader(0,12,"扣量后成功率",0,0,0);
		
		List<ReportHeader> _headers = new ArrayList<ReportHeader>();
		_headers.add(_h0);
		_headers.add(_h1);
		_headers.add(_h2);
		_headers.add(_h3);
		_headers.add(_h4);
		_headers.add(_h5);
		_headers.add(_h6);
		_headers.add(_h7);
		_headers.add(_h8);
		_headers.add(_h9);
		_headers.add(_h10);
		_headers.add(_h11);
		_headers.add(_h12);
		DecimalFormat df = new DecimalFormat("###.00");
		List<ReportDiv> _dataset = new ArrayList<ReportDiv>();
		for (int i = 0; i < datas.size(); i++) {
			RunDailyEntity rde = datas.get(i);
			ReportDiv _r0 = new ReportDiv();
			_r0.setRow(i+1);
			_r0.setCol(0);
			_r0.setType(ExportGlobal.CellType.STRING);
			_r0.setValue(rde.getPipleNumber());
			ReportDiv _r1 = new ReportDiv();
			_r1.setRow(i+1);
			_r1.setCol(1);
			_r1.setType(ExportGlobal.CellType.STRING);
			_r1.setValue(rde.getPipleName());
			ReportDiv _r2 = new ReportDiv();
			_r2.setRow(i+1);
			_r2.setCol(2);
			_r2.setType(ExportGlobal.CellType.STRING);
			_r2.setValue(rde.getChannelName());
			ReportDiv _r3 = new ReportDiv();
			_r3.setRow(i+1);
			_r3.setCol(3);
			_r3.setType(ExportGlobal.CellType.NUMBER);
			_r3.setValue(rde.getProductId());
			ReportDiv _r4 = new ReportDiv();
			_r4.setRow(i+1);
			_r4.setCol(4);
			_r4.setType(ExportGlobal.CellType.CURRENCY);
			_r4.setValue(rde.getAmountTotal());
			ReportDiv _r5 = new ReportDiv();
			_r5.setRow(i+1);
			_r5.setCol(5);
			_r5.setType(ExportGlobal.CellType.CURRENCY);
			_r5.setValue(rde.getAmountUnfinished());
			ReportDiv _r6 = new ReportDiv();
			_r6.setRow(i+1);
			_r6.setCol(6);
			_r6.setType(ExportGlobal.CellType.CURRENCY);
			_r6.setValue(rde.getAmountNotUp());
			ReportDiv _r7 = new ReportDiv();
			_r7.setRow(i+1);
			_r7.setCol(7);
			_r7.setType(ExportGlobal.CellType.CURRENCY);
			_r7.setValue(rde.getAmountFail());
			ReportDiv _r8 = new ReportDiv();
			_r8.setRow(i+1);
			_r8.setCol(8);
			_r8.setType(ExportGlobal.CellType.CURRENCY);
			_r8.setValue(rde.getAmountSuccess());
			ReportDiv _r9 = new ReportDiv();
			_r9.setRow(i+1);
			_r9.setCol(9);
			_r9.setType(ExportGlobal.CellType.CURRENCY);
			_r9.setValue(rde.getAmountChannel());
			ReportDiv _r10 = new ReportDiv();
			_r10.setRow(i+1);
			_r10.setCol(10);
			_r10.setType(ExportGlobal.CellType.CURRENCY);
			_r10.setValue(rde.getAmountDeducted());
			ReportDiv _r11 = new ReportDiv();
			_r11.setRow(i+1);
			_r11.setCol(11);
			_r11.setType(ExportGlobal.CellType.STRING);
			_r11.setValue(rde.getRatePreDeducted()==0?0:df.format(rde.getRatePreDeducted()*100)+"%");
//			_r11.setBackground(ExportGlobal.Background.GREEN);
			ReportDiv _r12 = new ReportDiv();
			_r12.setRow(i+1);
			_r12.setCol(12);
			_r12.setType(ExportGlobal.CellType.STRING);
			_r12.setValue(rde.getRatePreDeducted()==0?0:df.format(rde.getRateDeducted()*100)+"%");
//			_r12.setBackground(ExportGlobal.Background.GREEN);
			
			/************************/
			_dataset.add(_r0);
			_dataset.add(_r1);
			_dataset.add(_r2);
			_dataset.add(_r3);
			_dataset.add(_r4);
			_dataset.add(_r5);
			_dataset.add(_r6); 
			_dataset.add(_r7);
			_dataset.add(_r8);
			_dataset.add(_r9);
			_dataset.add(_r10);
			_dataset.add(_r11);
			_dataset.add(_r12);
		}

		ReportBody _body = new ReportBody();
		_body.setHeaders(_headers);
		_body.setName("SP平台当日运行统计");
		_body.setDivs(_dataset);
		return _body;
	}
	
	
	public ReportBody getSalesDailyBody(List<SalesDailyEntity> datas) throws Exception{
		ReportHeader _h1 = new ReportHeader(0,0,"交易日期",0,0,0);
		ReportHeader _h02 = new ReportHeader(0,1,"通道编号",0,0,0);
		ReportHeader _h2 = new ReportHeader(0,2,"通道名称",0,0,0);
		ReportHeader _h3 = new ReportHeader(0,3,"渠道名称",0,0,0);
		ReportHeader _h4 = new ReportHeader(0,4,"总金额",0,0,0);
		ReportHeader _h5 = new ReportHeader(0,5,"未完成金额",0,0,0);
		ReportHeader _h6 = new ReportHeader(0,6,"失败金额",0,0,0);
		ReportHeader _h7 = new ReportHeader(0,7,"总成功金额",0,0,0);
		ReportHeader _h8 = new ReportHeader(0,8,"扣量后成功金额",0,0,0);
		ReportHeader _h9 = new ReportHeader(0,9,"扣量金额",0,0,0);
		ReportHeader _h10 = new ReportHeader(0,10,"扣量前成功率",0,0,0);
		ReportHeader _h11 = new ReportHeader(0,11,"扣量后成功率",0,0,0);
		ReportHeader _h12 = new ReportHeader(0,12,"通道结算比例",0,0,0);
		ReportHeader _h13 = new ReportHeader(0,13,"渠道结算比例",0,0,0);
		
		List<ReportHeader> _headers = new ArrayList<ReportHeader>();
		_headers.add(_h1);
		_headers.add(_h02);
		_headers.add(_h2);
		_headers.add(_h3);
		_headers.add(_h4);
		_headers.add(_h5);
		_headers.add(_h6);
		_headers.add(_h7);
		_headers.add(_h8);
		_headers.add(_h9);
		_headers.add(_h10);
		_headers.add(_h11);
		_headers.add(_h12);
		_headers.add(_h13);
		DecimalFormat df = new DecimalFormat("###.00");
		List<ReportDiv> _dataset = new ArrayList<ReportDiv>();
		for (int i = 0; i < datas.size(); i++) {
			SalesDailyEntity sde = datas.get(i);
			ReportDiv _r1 = new ReportDiv();
			_r1.setRow(i+1);
			_r1.setCol(0);
			_r1.setType(ExportGlobal.CellType.DATE);
			_r1.setValue(DateTimeUtils.formatTime(sde.getCompDate(), "yyyy-MM-dd"));
			ReportDiv _r02 = new ReportDiv();
			_r02.setRow(i+1);
			_r02.setCol(1);
			_r02.setType(ExportGlobal.CellType.STRING);
			_r02.setValue(sde.getPipleNumber());
			ReportDiv _r2 = new ReportDiv();
			_r2.setRow(i+1);
			_r2.setCol(2);
			_r2.setType(ExportGlobal.CellType.STRING);
			_r2.setValue(sde.getPipleName());
			ReportDiv _r3 = new ReportDiv();
			_r3.setRow(i+1);
			_r3.setCol(3);
			_r3.setType(ExportGlobal.CellType.STRING);
			_r3.setValue(sde.getChannelName());
			ReportDiv _r4 = new ReportDiv();
			_r4.setRow(i+1);
			_r4.setCol(4);
			_r4.setType(ExportGlobal.CellType.CURRENCY);
			_r4.setValue(sde.getAmountTotal());
			ReportDiv _r5 = new ReportDiv();
			_r5.setRow(i+1);
			_r5.setCol(5);
			_r5.setType(ExportGlobal.CellType.CURRENCY);
			_r5.setValue(sde.getAmountUnfinished());
			ReportDiv _r6 = new ReportDiv();
			_r6.setRow(i+1);
			_r6.setCol(6);
			_r6.setType(ExportGlobal.CellType.CURRENCY);
			_r6.setValue(sde.getAmountFail());
			ReportDiv _r7 = new ReportDiv();
			_r7.setRow(i+1);
			_r7.setCol(7);
			_r7.setType(ExportGlobal.CellType.CURRENCY);
			_r7.setValue(sde.getAmountSuccess());
			ReportDiv _r8 = new ReportDiv();
			_r8.setRow(i+1);
			_r8.setCol(8);
			_r8.setType(ExportGlobal.CellType.CURRENCY);
			_r8.setValue(sde.getAmountChannel());
			ReportDiv _r9 = new ReportDiv();
			_r9.setRow(i+1);
			_r9.setCol(9);
			_r9.setType(ExportGlobal.CellType.CURRENCY);
			_r9.setValue(sde.getAmountDeducted());
			ReportDiv _r10 = new ReportDiv();
			_r10.setRow(i+1);
			_r10.setCol(10);
			_r10.setType(ExportGlobal.CellType.STRING);
			_r10.setValue(sde.getRatePreDeducted());
			ReportDiv _r11 = new ReportDiv();
			_r11.setRow(i+1);
			_r11.setCol(11);
			_r11.setType(ExportGlobal.CellType.STRING);
			_r11.setValue(sde.getRateDeducted());
			ReportDiv _r12 = new ReportDiv();
			_r12.setRow(i+1);
			_r12.setCol(12);
			_r12.setType(ExportGlobal.CellType.STRING);
			_r12.setValue(sde.getPipleSetRatioStr());
			ReportDiv _r13 = new ReportDiv();
			_r13.setRow(i+1);
			_r13.setCol(13);
			_r13.setType(ExportGlobal.CellType.STRING);
			_r13.setValue(sde.getChannelSetRatioStr());
						
			/************************/
			_dataset.add(_r1);
			_dataset.add(_r02);
			_dataset.add(_r2);
			_dataset.add(_r3);
			_dataset.add(_r4);
			_dataset.add(_r5);
			_dataset.add(_r6); 
			_dataset.add(_r7);
			_dataset.add(_r8);
			_dataset.add(_r9);
			_dataset.add(_r10);
			_dataset.add(_r11);
			_dataset.add(_r12);
			_dataset.add(_r13);
		}

		ReportBody _body = new ReportBody();
		_body.setHeaders(_headers);
		_body.setName("SP平台销售日报");
		_body.setDivs(_dataset);
		return _body;
	}
	
	/** 
	    * keyColumn : new String[]{"xxxName","xxxType"} <br> 
	    * condition : 查询条件 ，可为空<br> 
	    * initial : 分组统计初始变量，为空时自动为每列提供初始变量<br> 
	    * reduce ： 记录处理function<br> 
	    * finalize : finalize函数，可为空 <br> 
	    */
	    public BasicDBList group(String[] keyColumn, DBObject condition, 
	            DBObject initial, String reduce, String finalize) { 
	        DBObject key = new BasicDBObject(); 
	        for (int i = 0; i < keyColumn.length; i++) { 
	            key.put(keyColumn[i], true); 
	        } 
	        condition = (condition == null) ? new BasicDBObject() : condition; 
	        if (StringUtil.isEmptyString(finalize)) { 
	            finalize = null; 
	        } 
	        if (initial == null) {      //定义一些初始变量 
	            initial = new BasicDBObject(); 
	            initial.put("count", 0); 
//	            for (int i = 0; i < keyColumn.length; i++) { 
//	                DBObject index = new BasicDBObject(); 
//	                index.put("count", 0); 
//	                initial.put(keyColumn[i], index); 
//	            } 
	        } 
	        if(StringUtil.isEmptyString(reduce)){
	        	 reduce = "function(doc,prev){ " 
	        			 +  " prev.count++; "
	        			 +  " }"; 
	        }
	        long as = mongoTemplate.getCollection(MONGODB_SDK_PHONE).count();
	        BasicDBList resultList = (BasicDBList) mongoTemplate.getCollection(MONGODB_SDK_PHONE).group(key, condition, 
	                initial, reduce, finalize); 
	        return resultList; 
	    }
	
	/** 
	    * 包含日期分组 需keyf函数处理日期格式	 
	    * keyColumn : new String[]{"dateKey","key1","key2",...} <br> 
	    * condition : 查询条件 ，可为空<br> 
	    * initial : 分组统计初始变量，为空时自动为每列提供初始变量<br> 
	    * reduce ： 记录处理function<br> 
	    * finalize : finalize函数，可为空 <br> 
	    */
	    public List groupForKeyf(String[] keyColumn, BasicDBObject condition, 
	            DBObject initial, String reduce, String finalize) { 
	        StringBuffer keyf = new StringBuffer();
	        String dateKey = keyColumn[0];
	        keyf.append( "function(doc){ ");
	        keyf.append( " var date = new Date(doc."+dateKey+" );  ");
	        keyf.append( " var dateKey = '' + date.getFullYear()+ '-' + (date.getMonth()+1) + '-' + date.getDate() ; ");
	        keyf.append( " return {'day':dateKey");
	        for (int i = 1; i < keyColumn.length; i++) { 
	        	keyf.append(", '"+keyColumn[i]+"' : doc."+keyColumn[i]);
	        }
	        keyf.append( " }}; ");
	       
	        if (StringUtil.isEmptyString(finalize)) { 
	            finalize = null; 
	        } 
	        if (initial == null) {      //定义一些初始变量 
	            initial = new BasicDBObject(); 
	            initial.put("count", 0); 
//	            for (int i = 0; i < keyColumn.length; i++) { 
//	                DBObject index = new BasicDBObject(); 
//	                index.put("count", 0); 
//	                initial.put(keyColumn[i], index); 
//	            } 
	        } 
	        if(StringUtil.isEmptyString(reduce)){
	        	 reduce = "function(doc,prev){ " 
	        			 +  " prev.count++; "
	        			 +  " }"; 
	        }
	        DBCollection collection = mongoTemplate.getCollection(MONGODB_SDK_PHONE); 
	        long ccount = mongoTemplate.getCollection(MONGODB_SDK_PHONE).getCount();
	        GroupCommand gc = new GroupCommand(collection, keyf.toString(), condition, initial, reduce, null);  
	        DBObject  object =  mongoTemplate.getCollection(MONGODB_SDK_PHONE).group(gc);  
	        Map<String , Object> map = object.toMap(); 
	        List listKey = new ArrayList();  
	        List listValue = new ArrayList();  
	        Iterator it = map.keySet().iterator();  
	        while (it.hasNext()) {  
	            String keym = it.next().toString();  
	            listKey.add(keym);  
	            listValue.add(map.get(keym));  
	        }
//	     // 将Map Key 转化为List      
//	        List<String> mapKeyList = new ArrayList<String>(map.keySet());    
//	        System.out.println("mapKeyList:"+mapKeyList);  
	          
//	        // 将Map Key 转化为List      
//	        List<String> mapValuesList = new ArrayList<String>(map.values());    
//	        System.out.println("mapValuesList:"+mapValuesList);  
	        return listValue;
	    }
	    
	    public List<TSdkOperSta> addSdkOperStaList(Date date,List<TSdkOperSta> sList,BasicBSONList bList,int sosType){
	    	for (int i = 0; i < bList.size(); i++) {
				Object object = bList.get(i);
				JSONObject jsonObject = JSONObject.fromObject(object);
				String channelId = jsonObject.optString("channelId");
				String appId = jsonObject.optString("appId");
				int province = jsonObject.optInt("province");
				int host = jsonObject.optInt("host");
				int count = jsonObject.optInt("count");
				System.out.println(channelId+"-"+appId+"-"+province+"-"+host+"-"+sosType+"-"+count);
				TSdkOperSta sos = new TSdkOperSta();
				sos.setOperDate(date);
				sos.setAppId(appId);
				sos.setChannelId(channelId);
				sos.setProvinceId(province);
				sos.setHostId(host);
				sos.setStaNum(count);
				sos.setStaType(sosType);
				sList.add(sos);
			}
	    	return sList;
	    }
	    
	    public void setStatisticSdkOper(Date date) throws Exception{
	    	List<TSdkOperSta> sList = new ArrayList<TSdkOperSta>();
	    	if(date==null){
	    		date = DateTimeUtils.getCurrentDate();
	    	}
	    	// 后一天
	    	Date lastDate = DateTimeUtils.addDays(date, +1);
	    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String s = format.format(lastDate);
			lastDate = format.parse(s);
			
			// 注意：由于mogodb库时间会减去8小时运行，所有当前日期时间需先增加8小时后传入
//			Date mDate = DateTimeUtils.getDateDifferHour(date, 8);
//			Date mLastDate = DateTimeUtils.getDateDifferHour(lastDate, 8);
			
			String[] keyColumn = {"channelId","appId","province","host"};
			BasicDBObject cond = new BasicDBObject();
	        cond.put("$gt",date);
	        cond.put("$lt",lastDate);
	    	// 维度1：启动用户数 activationTime.time
	        BasicDBObject com1 = new BasicDBObject();
	        com1.put("activationTime.time", cond);
	        
			BasicBSONList acList = this.group(keyColumn, com1, null, null, null);
			sList = this.addSdkOperStaList(date,sList, acList, Global.SdkOperStaType.USER_ACT);
			
			// 维度2：新增用户数 regsiterTime
			 BasicDBObject com2 = new BasicDBObject();
			 com2.put("regsiterTime", cond);
			 BasicBSONList regList = this.group(keyColumn, com2, null, null, null);
			sList = this.addSdkOperStaList(date,sList, regList, Global.SdkOperStaType.USER_ADD);
			
			// 维度3：计费请求用户数 payRequestTime.time
			BasicDBObject com3 = new BasicDBObject();
			com3.put("payRequestTime.time", cond);
			BasicBSONList payRList = this.group(keyColumn, com3, null, null, null);
			sList = this.addSdkOperStaList(date,sList, payRList, Global.SdkOperStaType.USER_PAY_REQ);
			
			// 维度4：计费成功用户数 paySuccessTime.time
			BasicDBObject com4 = new BasicDBObject();
			com4.put("paySuccessTime.time", cond);
			BasicBSONList paySList = this.group(keyColumn, com4, null, null, null);
			sList = this.addSdkOperStaList(date,sList, paySList, Global.SdkOperStaType.USER_PAY_SUC);
			
			// 按同步日期删除
			tSdkOperStaDao.deleteByDate(date);
//			for (TSdkOperSta sos : sList) {
//				TSdkOperStaKey sosKey = new TSdkOperStaKey();
//				sosKey.setOperDate(sos.getOperDate());
//				sosKey.setAppId(sos.getAppId());
//				sosKey.setChannelId(sos.getChannelId());
//				sosKey.setHostId(sos.getHostId());
//				sosKey.setProvinceId(sos.getProvinceId());
//				sosKey.setStaType(sos.getStaType());
//				tSdkOperStaDao.deleteByPrimaryKey(sosKey);
//			}
			if(sList != null && sList.size() > 0){
				// 更新当日sdk操作统计
				tSdkOperStaDao.insertBatch(sList);
			}
	    }
	    
	    public void  setStaSdkDaily(String dateStr) throws Exception{
	    	if(StringUtil.isEmptyString(dateStr)){
	    		dateStr = DateTimeUtils.formatTime(DateTimeUtils.getCurrentDate(), DateTimeUtils.yyyyMMdd);
	    	}
	    	List<TStaSdkDaily> sdkDailies = tSdkOperStaDao.getStaSdkDailyData(dateStr);
//	    	for (TStaSdkDaily ssd : sdkDailies) {
//				TStaSdkDailyKey ssdKey = new TStaSdkDailyKey();
//				ssdKey.setCompDate(ssd.getCompDate());
//				ssdKey.setAppId(ssd.getAppId());
//				ssdKey.setChannelId(ssd.getChannelId());
//				ssdKey.setProvinceId(ssd.getProvinceId());
//				tStaSdkDailyDao.deleteByPrimaryKey(ssdKey);
//			}
	    	// 按统计日期删除数据
	    	tStaSdkDailyDao.deleteByDate(DateTimeUtils.StringToDate(dateStr, DateTimeUtils.yyyyMMdd));
	    	if(sdkDailies != null && sdkDailies.size() >0){
	    		tStaSdkDailyDao.insertBatch(sdkDailies);
	    	}
	    }
	    
	    public List<TStaSdkDaily> getStaSdkDailyList(StaSdkDailySearchParam param){
	    	return tStaSdkDailyDao.getStaSdkDailyList(param);
	    }
	    
	    public StaSdkDailyEntity getStaSdkDailyEntity(StaSdkDailySearchParam param){
	    	StaSdkDailyEntity ssde = new StaSdkDailyEntity();
	    	int suNumTotal = 0;
			int auNumTotal = 0;
			int  pruNumTotal = 0;
			int psuNumTotal = 0;
			int uniPuserNumTotal = 0;
			int uniPSuserNumTotal = 0;
			double uniPaysucRatioTotal = 0;
			int cmPuserNumTotal = 0;
			int cmPSuserNumTotal = 0;
			double cmPaysucRatioTotal = 0;
			int telePuserNumTotal = 0;
			int telePSuserNumTotal = 0;
			double telePaysucRatioTotal = 0;
			double infoFeeTotal = 0;
			double payRatioTotal = 0;
			double paySucRatioTotal = 0;
			double translateRatioTotal = 0;
			double arpuTotal = 0;
			List<TStaSdkDaily> listData = tStaSdkDailyDao.getStaSdkDailyList(param);
			ssde.setStaSdkDailies(listData);
			for (TStaSdkDaily ssd : listData) {
				suNumTotal = suNumTotal + ssd.getStartUserNum();
				auNumTotal = auNumTotal + ssd.getAddUserNum();
				pruNumTotal = pruNumTotal + ssd.getPayreqUserNum();
				psuNumTotal = psuNumTotal + ssd.getPaysucUserNum();
				uniPuserNumTotal = uniPuserNumTotal + ssd.getUniPuserNum();
				uniPSuserNumTotal = uniPSuserNumTotal + ssd.getUniPSuserNum();
				cmPuserNumTotal = cmPuserNumTotal + ssd.getCmPuserNum();
				cmPSuserNumTotal = cmPSuserNumTotal + ssd.getCmPSuserNum();
				telePuserNumTotal = telePuserNumTotal + ssd.getTelePuserNum();
				telePSuserNumTotal = telePSuserNumTotal + ssd.getTelePSuserNum();
				infoFeeTotal = infoFeeTotal + ssd.getInfoFee();
				
				ssde.setSuNumTotal(suNumTotal);
				ssde.setAuNumTotal(auNumTotal);
				ssde.setPruNumTotal(pruNumTotal);
				ssde.setPsuNumTotal(psuNumTotal);
				ssde.setUniPuserNumTotal(uniPuserNumTotal);
				ssde.setUniPSuserNumTotal(uniPSuserNumTotal);
				ssde.setCmPuserNumTotal(cmPuserNumTotal);
				ssde.setCmPSuserNumTotal(cmPSuserNumTotal);
				ssde.setTelePuserNumTotal(telePuserNumTotal);
				ssde.setTelePSuserNumTotal(telePSuserNumTotal);
				ssde.setInfoFeeTotal(infoFeeTotal);
			}
			// 付费成功率=付费成功用户数/付费请求用户数
			// 联通付费总成功率
			if(uniPuserNumTotal!=0 && uniPSuserNumTotal!=0){
				uniPaysucRatioTotal = (double)uniPSuserNumTotal/uniPuserNumTotal; 
			}
			ssde.setUniPaysucRatioTotal(uniPaysucRatioTotal);
			 // 移动付费总成功率
			if(cmPuserNumTotal!=0 && cmPSuserNumTotal!=0){
				cmPaysucRatioTotal = (double)cmPSuserNumTotal/cmPuserNumTotal;
			}
			ssde.setCmPaysucRatioTotal(cmPaysucRatioTotal);
			// 联通付费总成功率
			if(telePuserNumTotal!=0 && telePSuserNumTotal!=0){
				telePaysucRatioTotal = (double)telePSuserNumTotal/telePuserNumTotal;
			}
			ssde.setTelePaysucRatioTotal(telePaysucRatioTotal);
			// 付费率 = 付费请求用户数/启动用户数
			if(suNumTotal!=0 && pruNumTotal!=0){
				payRatioTotal = (double)pruNumTotal/suNumTotal;
			}
			ssde.setPayRatioTotal(payRatioTotal);
			// 付费成功率 = 付费成功用户数/付费请求用户数
			if(pruNumTotal!=0 && psuNumTotal!=0){
				paySucRatioTotal = (double)psuNumTotal/pruNumTotal;
			}
			ssde.setPaySucRatioTotal(paySucRatioTotal);
			// 转化率 = 付费成功用户数/启动用户数
			if(suNumTotal!=0 && psuNumTotal!=0){
				translateRatioTotal = (double)psuNumTotal/suNumTotal;
			}
			ssde.setTranslateRatioTotal(translateRatioTotal);
			// arpu = 信息费/启动用户数
			if(suNumTotal!=0 && infoFeeTotal!=0){
				arpuTotal = (double)infoFeeTotal/suNumTotal;
			}
			ssde.setArpuTotal(arpuTotal);
	    	return ssde;
	    }
	
}