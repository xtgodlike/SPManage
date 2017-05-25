<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%String path = request.getContextPath();%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache" /> 
	<meta http-equiv="cache-control" content="no-cache" /> 
	<meta http-equiv="expires" content="0" />
	<title>SP计费平台-添加用户</title>
    <jsp:include page="baseJs.jsp" />
    <jsp:include page="datetimepicker.jsp" />
    <jsp:include page="validator.jsp" />
    <script type="text/javascript">
    $(document).ready(function () {
    	$.formValidator.initConfig({
    		 formID:"frm",
    		 submitOnce:true,
    		 onError:function(msg,obj,errorlist){
    		 	
    		 },
    		 onSuccess:function(){
    		 	
    		 }
    	});
    	
    	$("#userAccount").formValidator({
	    		onShow:"",
	    		onFocus:"用户名至少3个字符,最多20个字符,只支持字母数字与下划线",
	    		onCorrect:"该用户名可用"
    		}).inputValidator({
    			min:3,
    			max:20,
    			onError:"用户名至少3个字符,最多20个字符,只支持字母数字与下划线"
    		}).regexValidator({
    			regExp:"username",
    			dataType:"enum",
    			onError:"用户名格式不正确，只支持字母数字与下划线"
    		}).ajaxValidator({
				dataType : "html",
				async : true,
				url : "<%=request.getContextPath()%>"+"/basics/verifyUserAccount.do",
				success : function(data){
		            if( data.indexOf("OK") != -1 ) 
		            	return true;
		            if( data.indexOf("FAIL") !=-1 ) 
		            	return false;
					return false;
				},
				buttons: $("#savaUser"),
				error: function(jqXHR, textStatus, errorThrown){
					alert("服务器没有返回数据，可能服务器忙，请重试"+errorThrown);
				},
				onError : "该用户名不可用，请更换用户名",
				onWait : "正在对用户名进行合法性校验，请稍候..."
			});
	
		//姓名
    	$("#Name").formValidator({
    		onShow:"",
    		onFocus:"请输入姓名，1~6个字符",
    		onCorrect:"正确"
    	}).inputValidator({
    		min:1,
    		max:12,
    		onError:"长度不正确,请确认"
    	});
    	
    	//性别
    	/* $("#sex").formValidator({
    		onShow:"用户性别，必选项",
    		onFocus:"请选择用户性别",
    		onCorrect:"正确"
    	}).inputValidator({
    		min:1,
    		max:1,
    		onError:"用户性别，必选项"
    	}); */
    	
    	
    	$("#mobile").formValidator({
    		onShow:"",
    		onFocus:"手机号码必须是11位的,请确认",
    		onCorrect:"正确"
    	}).inputValidator({
    		min:11,
    		max:11,
    		onError:"手机号码必须是11位的,请确认"
    	}).regexValidator({
    		regExp:"mobile",
    		dataType:"enum",
    		onError:"你输入的手机号码格式不正确"
    	});
    	
    	
    	//出生日期
    	$("#birthday").formValidator({
    		onShow:"",
    		onFocus:"出生日期不正确,请确认",
    		onCorrect:"正确"
    	}).regexValidator({
    		regExp:"date",
    		dataType:"enum",
    		onError:"出生日期不正确,请确认"
    	});
    	
    	//证件号码
    	$("#idNo").formValidator({
    		onShow:"",
    		onFocus:"请输入身份证号，1~30个字符",
    		onCorrect:"正确"
    	}).inputValidator({
    		min:1,
    		max:30,
    		onError:"长度不正确,请确认"
    	});
    	
    	//密码验证
	    /*$("#passwd").formValidator({
    		onShow:"",
    		onFocus:"至少5个长度",
    		onCorrect:"密码合法"
    	}).inputValidator({
    		min:5,
    		max:30,
    		empty:{leftEmpty:false,rightEmpty:false,emptyError:"密码两边不能有空符号"},
    		onError:"密码长度不正确，请确认"
	    });
		$("#passwd2").formValidator({
			onShow:"",
			onFocus:"至少5个长度",
			onCorrect:"密码一致"
		}).inputValidator({
			min:5,
			max:30,
			empty:{leftEmpty:false,rightEmpty:false,emptyError:"重复密码两边不能有空符号"},
			onError:"确认密码长度不正确，请确认"
		}).compareValidator({
			desID:"passwd",
			operateor:"=",
			onError:"2次密码不一致,请确认"
		});*/
		
		//用户状态
		$("#status").formValidator({
    		onShow:"",
    		onFocus:"请选择用户状态",
    		onCorrect:"正确"
    	}).inputValidator({
    		min:1,
    		onError:"请选择用户状态"
    	});
    	
		$("#email").formValidator({
			onShow:"",
			onFocus:"请注意你输入的email格式",
			onCorrect:"正确"
		}).regexValidator({
			regExp:"email",
			dataType:"enum",
			onError:"email格式不正确"
		});
		
		//用户角色
		$(":checkbox[name='role']").formValidator({
			tipID:"roleTip",
    		onShow:"",
    		onFocus:"请选择用户角色",
    		onCorrect:"正确"
    	}).inputValidator({
    		min:1,
    		onError:"请选择用户角色"
    	}); 
		/***
		//用户通道
		$(":checkbox[name='piple']").formValidator({
			tipID:"pipleTip",
    		onShow:"",
    		onFocus:"请选择授权通道",
    		onCorrect:"正确"
    	}).inputValidator({
    		min:1,
    		onError:"请选择授权通道"
    	});
		//用户渠道
		$(":checkbox[name='channel']").formValidator({
			tipID:"channelTip",
    		onShow:"",
    		onFocus:"请选择授权渠道",
    		onCorrect:"正确"
    	}).inputValidator({
    		min:1,
    		onError:"请选择授权渠道"
    	});
    	***///
    	//备注
    	/* $("#remark").formValidator({
    		empty:true,
    		onShow:"备注，可以为空",
    		onFocus:"你要是输入了，只能输入1~50个字符",
    		onCorrect:"正确",
    		onEmpty:"您确定不输入备注？"
    	}).inputValidator({
    		min:1,
    		max:50,
    		onError:"备注支持1~50个字符"
    	}); */
    	
		jQuery('.date_class').datetimepicker({
			language: 'zh-CN',
			//startView: "year", //默认打开视图
			format: "yyyy-mm-dd",
			autoclose:true,
	        minView: "month" //选择日期后，不会再跳转去选择时分秒 
	    });
	    
    });	
    
    
    function setValue(input,obj){
    	input.val($(obj).html());//赋值
        var cityId = getCityId($(obj).html());
        var cId = jQuery(input).parent().find(".city_value").attr("id"); //设置隐藏域的值为ID
        jQuery("#"+cId).val(cityId);
    }
    
    </script>
</head>
<body>
	<div class="home_content_bg">
			<a class="home_current">添加用户</a>
	</div>  
	<div class="content-mainBG">
	<form action="<%=path%>/basics/doAddUser.do" id="frm" name="frm" method="post">
    <table class="table-add" border="0" cellpadding="0" cellspacing="0">
        <tbody>
            <tr>
                <td width="40%" align="right"><font color="FF0000">*</font>用户名：</td>
                <td width="30%">
                    <input name="userAccount" id="userAccount" type="text" class="inputText" />
                </td>
                <td width="30%">
                	<div id="userAccountTip"></div>&nbsp;
                </td>
           </tr>
           <tr>
                <td align="right"><font color="FF0000">*</font>姓名：</td>
                <td>
                    <input name="Name" id="Name" type="text" class="inputText" />
                </td>
                <td>
                	<div id="NameTip"></div>&nbsp;
                </td>
            </tr>
            
            <tr>   
                <td align="right"><font color="FF0000">*</font>手机号：</td>
                <td>
                    <input name="mobile" id="mobile" type="text" class="inputText" /></td>
                 <td>
                	<div id="mobileTip"></div>&nbsp;
                </td>
            </tr>
            
           <tr>   
                <td align="right"><font color="FF0000">*</font>身份证号：</td>
                <td>
                    <input name="idNo" id="idNo" type="text" class="inputText" /></td>
                 <td>
                	<div id="idNoTip"></div>&nbsp;
                </td>   
            </tr>
            
            <tr>   
                <td align="right"><font color="FF0000">*</font>出生日期：</td>
                <td>
                    <input name="birthday" id="birthday" type="text" class="inputText date_class" readonly="readonly" /></td>
                 <td>
                	<div id="birthdayTip"></div>&nbsp;
                </td>   
            </tr>
            
            <tr>   
                <td align="right"><font color="FF0000">*</font>Email：</td>
                <td>
                    <input name="email" id="email" type="text" class="inputText" /></td>
                 <td>
                	<div id="emailTip"></div>&nbsp;
                </td>   
            </tr>
            
            <!-- 
            <tr>
                <td><font color="FF0000">*</font>密码：</td>
                <td>
                    <input name="passwd" id="passwd" class="inputText" type="password"/>
                 </td>
                  <td>
                	<div id="passwdTip"></div>
                </td>   
                   </tr><tr>  
                 
                <td><font color="FF0000">*</font>确认密码：</td>
                <td>
                    <input name="passwd2" id="passwd2" class="inputText" type="password" /></td>
                <td><div id="passwd2Tip"></div></td>
            </tr>
             -->
            
            <tr>
                <td align="right"><font color="FF0000">*</font>用户状态：</td>
                <td>
                    <select name="status" id="status" class="selectText">
                    	<option value="">----用户状态----</option>
                     	<option value="1">激活</option>
                     	<option value="2">禁用</option>
                    </select>
                </td>
                 <td><div id="statusTip"></div>&nbsp;</td>
           </tr>
           
           	<tr>
                <td align="right"><font color="FF0000">*</font>角色分配：</td>
                <td>
                    <c:forEach items="${roles}" var="s" varStatus="status">
                    	<input type="checkbox" name="role" id="role${status.index}" value="${s.roleId}" />${s.roleName}
                    	<c:if test="${status.index!=0 && (status.index+1)%3==0}"><br/></c:if>
                    </c:forEach>
                </td>
                <td><div id="roleTip"></div>&nbsp;</td>
            </tr>
            <tr>
                <td align="right">通道授权：</td>
                <td>
                    <c:forEach items="${piples}" var="p" varStatus="status">
                    	<input type="checkbox" name="piple" id="piple${status.index}" value="${p.pipleId}" />${p.pipleNumber}
                    	<c:if test="${status.index!=0 && (status.index+1)%3==0}"><br/></c:if>
                    </c:forEach>
                </td>
                <td><div id="pipleTip"></div>&nbsp;</td>
            </tr>
            <tr>
                <td align="right">渠道授权：</td>
                <td>
                    <c:forEach items="${channels}" var="c" varStatus="status">
                    	<input type="checkbox" name="channel" id="channel${status.index}" value="${c.channelId}" />${c.apiKey}
                    	<c:if test="${status.index!=0 && (status.index+1)%3==0}"><br/></c:if>
                    </c:forEach>
                </td>
                <td><div id="channelTip"></div>&nbsp;</td>
            </tr>
             <tr>
                <td align="right">CP授权：</td>
                <td>
                    <c:forEach items="${cps}" var="c" varStatus="status">
                    	<input type="checkbox" name="cp" id="cp${status.index}" value="${c.cpId}" />${c.abbrName}
                    	<c:if test="${status.index!=0 && (status.index+1)%3==0}"><br/></c:if>
                    </c:forEach>
                </td>
                <td><div id="channelTip"></div>&nbsp;</td>
            </tr>
			<!-- <tr>
                <td>备注：</td>
                <td>
                    <textarea name="remark" id="remark" rows="4" cols="5" class="textareaText" ></textarea>
                </td>
                <td><div id="remarkTip"></div></td>
            </tr> -->
            
            <tr>
            	<td></td>
            	<td align="left">
            		<span class="alert_info">注：新添加用户名的密码与账号一致！</span>
            	</td>
            	<td>&nbsp;</td>
            </tr>
           
        </tbody>
        <tfoot>
            <tr>
                <td colspan="3">
                    <input class="defaultBut" type="submit" id="savaUser" value="+添加用户" />
                    <input class="defaultBut" type="button" onclick="javascript:history.go(-1);" value="取消" />
                 </td>
            </tr>
        </tfoot>
    </table>
   </form>
</div>
</body>
</html>
