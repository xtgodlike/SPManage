package com.qy.sp.manage.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.qy.sp.manage.common.utils.DateTimeUtils;
import com.qy.sp.manage.common.utils.MapCacheManager;
import com.qy.sp.manage.common.utils.MapCacheManager.CacheProvider;
import com.qy.sp.manage.service.MobileSegmentService;
import com.qy.sp.manage.service.StatisticFailService;



@Component
public class TimerTask {
	private Logger log = Logger.getLogger(TimerTask.class);
	@Resource
	private MobileSegmentService mobileSegmentService;
	private static int count_segment = 0;
	private static Map<String,String> keysMap = new ConcurrentHashMap<String, String>();
	@Resource
	private StatisticFailService statisticFailService;

//	@Scheduled(cron="* 0/5 * * * ? ") //间隔5分钟执行  ,开发时用以测试，采用5秒一次
    public void mobileSegment(){
//		System.out.println("SHIT!");
		boolean result = false;
		if(this.count_segment != 0){
			result = this.mobileSegmentService.addMobileSegment();
		}else{
			result = this.mobileSegmentService.addMobileSegmentWithOrders();
		}
		this.count_segment++;
	}
    
    /**
     * 每天0点清理日月限缓存
     * */
	@Scheduled(cron="0 0 0 * * ?") 
    public void limitTask(){
		log.info("limitTask start:"+DateTimeUtils.getCurrentTime());
		MapCacheManager.getInstance().getDailyClearMapCache().clear();
		MapCacheManager.getInstance().getSmsOrderCache().clear();
		for(String key : keysMap.keySet()){
			CacheProvider provider = MapCacheManager.getInstance().getCustomCacheProvider(key);
			if(provider != null){
				provider.clear();
			}
		}
		log.info("limitTask end:"+DateTimeUtils.getCurrentTime());
	}
	public static void putClearDailyKey(String key){	
		keysMap.put(key, key);
	}
	public static void removeClearDailyKey(String key){
		keysMap.remove(key);
	}
	public static boolean containsClearDailyKey(String key){
		return keysMap.containsKey(key);
	}
	
	 /**
     * 每个整点小时统计更新SDK操作日报
	 * @throws ParseException 
     * */
	@Scheduled(cron="0 0 0/1 * * ? ") 
//	@Scheduled(cron="0 0/1 * * * ?  ") 
    public void stSDKOperTask() throws ParseException{
//		Date nowDate = DateTimeUtils.addDays(DateTimeUtils.getCurrentDate(), -1);
//    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//		String s = format.format(nowDate);
//		nowDate = format.parse(s);
//		System.out.println("nowDate="+nowDate); // 19
//    	Date nowDate = DateTimeUtils.StringToDate("2016-11-04", DateTimeUtils.yyyyMMdd);
		Date nowDate = DateTimeUtils.getCurrentDate();
		log.info("staSDKOperTask start:"+DateTimeUtils.getCurrentTime());
		// 更新SDK操作统计
		try {
			statisticFailService.setStatisticSdkOper(nowDate);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.info("更新SDK操作统计处理异常："+e.toString());
		}
		// 更新SDK操作日报
		try {
			statisticFailService.setStaSdkDaily(DateTimeUtils.formatTime(nowDate, DateTimeUtils.yyyyMMdd));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.info("更新SDK操作日报处理异常："+e.toString());
		}
		
		log.info("staSDKOperTask end:"+DateTimeUtils.getCurrentTime());
	}
}
