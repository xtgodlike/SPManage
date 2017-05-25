package com.qy.sp.manage.common.utils;

import java.util.HashMap;
import java.util.Map;

public interface Global {

	public final static String U_SESSION = "U_SESSION";//用户登录Session
	public final static String MENUES = "QY_MENU"; //菜单
	public final static String CP_LAYOUT = "CP_LAYOUT"; // CP定制
	
	
	/**
	 * 开通状态
	 * T_CHARGE_TYPE_RELA；T_CP_CHARGER_RELA；T_CHANEL_CHARGER_STATUS；T_CP_CHARGER_RELA；T_CHANNEL,T_CHANNEL_FEE_RELA,T_CP_FEE_RELA
	 * @author Mel
	 * 
	 */
	public static final class OpStatus {
		public final static int OFF = 0; // 未开通
		public final static int ON 	= 1; // 已经开通		
	}
	
	/**
	 * 
	 * 模块代码
	 * @author Jvi
	 */
	public static final class ModuleCode {
		public final static String ROOT   = "QY.OA";
		public final static String NEWQY  = "QY.OA.NEWQY";
		public final static String SYSTEM = "QY.OA.SYSTEM";
		public final static String SIGN   = "QY.OA.SIGN";
	}
	
	/**
	 * 固定角色ID
	 * 
	 * @author Mel
	 * 
	 */
	public static final class Roles_Fixed {
		public final static String ADMIN 			= "1"; // 系统管理员
		public final static String CP_ADMIN			= "1000"; // CP管理员
		public final static String CP_ROUTINE		= "1001"; // CP运营
		public final static String CHANNEL_ADMIN	= "1002"; // 渠道管理员
		
		public final static String PIPLE_ALL		= "9001"; // 查询开放所有计费通道和渠道信息，不校验用户和通道对应关系，以及用户和渠道对应关系
		public final static String CHANNEL_ALL		= "9002"; // 查询开放所有渠道信息，不校验用户和渠道对应关系
	}
	
	public static final class Role_Writable {
		public final static int WRITABLE 			= 0; // 可修改
		public final static int DISWRITABLE			= 1; // 不可修改
	}
	
	public static final class User_Type {
		public final static int NORMAL 			= 0; // 普通
		public final static int CP_ADMIN		= 1; // CP管理人员
		public final static int CHANNEL_ADMIN	= 2; // 渠道人员
		public final static int CP_ROUTINE		= 3; // CP运营人员
		public final static int PIPLE_MGR		= 4; // 通道经理
		public final static int CHANNEL_MGR		= 5; // 渠道经理
	}
	
	public static final class GlobalID{
		public final static int DEFAULT_CP_SHIFT_FLAG		= 1000;
		public final static int DEFAULT_CHANNEL_REDUCE_FLAG	= 1001;
		public final static int DEFAULT_REDUCE_RATE			= 1002;
	}
	
	
	public static final class UserStatus{
		public final static int OPEN  = 1; //激活
		public final static int CLOSE = 2; //禁用
	}
	
	/**
	 * CP状态
	 * T_CPINFO
	 * @author Mel
	 * 
	 */
	public static final class CP_OpStatus {
		public final static int UNAUDITED 	= 1; // 待审核
		public final static int SUSPEND		= 2; // 待商用		
		public final static int RUNNING		= 3; // 商用
		public final static int STOP 		= 4; //暂停，系统维护时启用
	}
	
	/**
	 * 商用状态
	 * T_APP
	 * @author xietian
	 * 
	 */
	public static final class AppCommStatus {
		public final static int CREATE	 			= 0; // 待审核
		public final static int WAIT					= 1; // 待商用
		public final static int RUN					= 2; //	商用
	}
	
	/**
	 * 商用状态
	 * T_PRODUCT
	 * @author xietian
	 * 
	 */
	public static final class ProductCommStatus {
		public final static int CREATE	 			= 0; // 待审核
		public final static int WAIT					= 1; // 待商用
		public final static int RUN					= 2; //	商用
	}
	
	
	/**
	 * 计费点状态
	 * T_CHARGER
	 * @author Mel
	 * 
	 */
	public static final class Charger_OpStatus {
		public final static int OFF 	= 0; // 暂停
		public final static int ON		= 1; // 启用
	}	
	
	/**
	 * 是否扣量标志
	 * T_CHANEL_CHARGER_STATUS；T_CP_CHARGER_RELA
	 * @author Mel
	 * 
	 */
	public static final class Reduce_Flag {
		public final static int OFF 	= 0; // 暂停
		public final static int ON		= 1; // 启用
	}
	
	/***
	 * 订单交易状态
	 * @author xietian
	 *
	 */
	public final static class OrderStatus{
		public final static int INIT 	= 0; 					//未完成
		public final static int TRADING 	= 1; 			//交易中
		public final static int SUCCESS = 2; 			//成功
		public final static int FAIL 	= 3; 					//失败
	}
		
	// 订单交易状态说明
	public static final class OrderStatusDesc{
		public static Map<Integer, String> message = new HashMap<Integer, String>();
		static{
			message.put(OrderStatus.INIT, "未完成"); 
			message.put(OrderStatus.TRADING, "交易中"); 
			message.put(OrderStatus.SUCCESS, "交易成功");
			message.put(OrderStatus.FAIL, "交易失败");
		}
	}
	
	/***
	 * 订单流程状态
	 * @author xietian
	 *
	 */
	  public final static class SubStatus{
		  	public static final int PAY_INIT = 0;															// 初始化
		    public static final int PAY_GET_SMS_SUCCESS = 1;									// 获取验证码成功
		    public static final int PAY_GET_SMS_FAIL = 2;											// 获取验证码失败
		    public static final int PAY_FILTER_LOCAL_SMS_SUCCESS = 11;				// 截取本地短信成功
		    public static final int PAY_FILTER_LOCAL_SMS_FAIL = 12;						// 截取本地短信失败
		    public static final int PAY_SUBMIT_CODE_SUCCESS = 13;						// 提交验证码成功
		    public static final int PAY_SUBMIT_CODE_FAIL = 14;								// 提交验证码失败
		    public static final int PAY_SEND_MESSAGE_SUCCESS = 15;						// 发送短信指令成功
		    public static final int PAY_SEND_MESSAGE_FAIL = 16;								// 发送短信指令失败
		    public static final int PAY_SUCCESS = 3;													// 支付成功
		    public static final int PAY_ERROR = 4;														// 支付失败
	  }
	  
		// 订单流程状态说明
		public static final class SubStatusDesc{
			public static Map<Integer, String> message = new HashMap<Integer, String>();
			static{
				message.put(SubStatus.PAY_INIT, "初始化"); 
				message.put(SubStatus.PAY_GET_SMS_SUCCESS, "获取验证码成功"); 
				message.put(SubStatus.PAY_GET_SMS_FAIL, "获取验证码失败"); 
				message.put(SubStatus.PAY_FILTER_LOCAL_SMS_SUCCESS, "截取本地短信成功"); 
				message.put(SubStatus.PAY_FILTER_LOCAL_SMS_FAIL, "截取本地短信失败"); 
				message.put(SubStatus.PAY_SUBMIT_CODE_SUCCESS, "提交验证码成功"); 
				message.put(SubStatus.PAY_SUBMIT_CODE_FAIL, "提交验证码失败"); 
				message.put(SubStatus.PAY_SEND_MESSAGE_SUCCESS, "发送短信指令成功"); 
				message.put(SubStatus.PAY_SEND_MESSAGE_FAIL, "发送短信指令失败"); 
				message.put(SubStatus.PAY_SUCCESS, "支付成功"); 
				message.put(SubStatus.PAY_ERROR, "支付失败"); 
			}
		}
		
	/**
	 * 扣量状态
	 * @author Mel
	 */
	public static final class Shift_Status {
		public final static int NOT_SHIFTED 	= 0; // 未扣量
		public final static int SHIFTED			= 1; // 被扣量
	}
	
	// 扣量状态说明
	public static final class ShiftStatusDesc{
		public static Map<Integer, String> message = new HashMap<Integer, String>();
		static{
			message.put(Shift_Status.NOT_SHIFTED, "未扣量"); 
			message.put(Shift_Status.SHIFTED, "已扣量"); 
		}
	}
	
	public static long DAY_LENGTH = 86400000l;
	
	public static final class StasticIncomeKeys {
		public final static String INCOME 			= "income"; // 收入
		
	}
	
	//数据库中所有op_status字段的值，都套用这个
		public final static class OP_STATUS{
			public final static int OPEN 	= 1;
			public final static int CLOSE 	= 0;
		}
		
		//交易是否被扣量
			public final static class DEC_STATUS{
				public final static int DEDUCTED 	= 1;
				public final static int UNDEDUCTED 	= 0;
			}
		
		
		//所有通道的ID列表
		public final static class PIPLE_ID{
			public final static String MZ_PC 			= "6001";
			public final static String PM_IREAD 		= "6011";
			public final static String CB		 		= "6012";
		}
		
		// 返回结果
		public final static class Result{
			public final static String SUCCESS 	= "0"; //成功
			public final static String ERROR 	= "1";//失败
		}
		
		// 合同类型
		public final static class ContractType{
			public final static int PIPLE 	= 1;
			public final static int CHANNEL 	= 2;
		}
		
		// 字典类型ID
		public final static class DicID{
			public final static int PIPLE_TYPE 	= 1;  // 通道类型
			public final static int CALC_TYPE 	= 2;  // 结算类型
			public final static int CODE_TYPE 	= 3;  // 代码类型
			public final static int PLUGIN_TYPE 	= 5;  // 插件类型
			// SDK相关
			public final static int SDK_TASK_STEP =  4; // SDK任务执行步骤
		}
		
		// 结算类型
		public final static class PipleCalcType{
			public final static int WEEK 	= 20001;  // 周
			public final static int C_MONTH 	= 20002;  // 次月 N+1
			public final static int  ARRIVAL = 20003; // 到账
			public final static int  SEEBILL = 20004;   // 见账单
			public final static int CC_MONTH 	= 20005;  // 次次月 N+2
		}
		
		// 序号类型
		public final static class SeqType{
			public final static String PIPLE_NUMBER_KEY	= "PipleSeq";  // 通道编号序列号
			public final static String API_KEY 	= "ApiKeySeq";  // 渠道APIKEY序列号
			public final static String APP_KEY = "AppSeq";	// APP序列号
		}
		
		// 
		public final static class SdkOperStaType{
			public final static int USER_ACT	= 1;  // 启动活跃用户
			public final static int USER_ADD	= 2;  // 新增用户
			public final static int USER_PAY_REQ	= 3;  // 支付请求用户
			public final static int USER_PAY_SUC	= 4;  // 支付成功用户
		}
}
