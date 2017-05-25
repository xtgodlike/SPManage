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
    <title>SP计费平台-修改用户信息</title>
    <jsp:include page="baseJs.jsp" />
    <jsp:include page="validator.jsp" />
    <jsp:include page="datetimepicker.jsp" />
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
    		onShow:"",
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
    		regExp:"num1",
    		dataType:"enum",
    		onError:"你输入的手机号码格式不正确"
    	});
    	
    	//证件号码
    	$("#idNo").formValidator({
    		onShow:"",
    		onFocus:"请输入姓名，1~30个字符",
    		onCorrect:"正确"
    	}).inputValidator({
    		min:1,
    		max:30,
    		onError:"长度不正确,请确认"
    	});
    	
    	//email
		$("#email").formValidator({
			onShow:"",
			onFocus:"请注意你输入的email格式",
			onCorrect:"正确"
		}).regexValidator({
			regExp:"email",
			dataType:"enum",
			onError:"email格式不正确"
		});
    	
		//用户状态
		$("#status").formValidator({
    		onShow:"",
    		onFocus:"请选择用户状态",
    		onCorrect:"正确"
    	}).inputValidator({
    		min:1,
    		onError:"请选择用户状态"
    	});
    	
    	//用户角色
		$("#role").formValidator({
    		onShow:"",
    		onFocus:"请选择用户角色",
    		onCorrect:"正确"
    	}).inputValidator({
    		min:1,
    		onError:"请选择用户角色"
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
		
		//用户等级
		/* $("#level").formValidator({
    		onShow:"",
    		onFocus:"请选择用户等级",
    		onCorrect:"正确"
    	}).inputValidator({
    		regExp:"num1",
    		dataType:"enum",
    		onError:"请选择用户等级"
    	}); */
		
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
		
		
		jQuery('.date_class').datetimepicker({
			language: 'zh-CN',
			//startView: "year", //默认打开视图
			format: "yyyy-mm-dd",
			autoclose:true,
	        minView: "month" //选择日期后，不会再跳转去选择时分秒 
	    });
		
    });	
    
    
   /*  function setValue(input,obj){
    	input.val($(obj).html());//赋值
        var cityId = getCityId($(obj).html());
        var cId = jQuery(input).parent().find(".city_value").attr("id"); //设置隐藏域的值为ID
        jQuery("#"+cId).val(cityId);
    } */
    
    </script>
</head>
<body>
	<div class="home_content_bg">
			<a class="home_current">修改用户信息</a>
	</div>  
 	<div class="content-mainBG">
	<form action="<%=path%>/basics/doUpdateUser.do" id="frm" name="frm" method="post">
    <table class="table-add" border="0" cellpadding="0" cellspacing="0">
        <tbody>
            <tr>
                <td width="40%" align="right"><font color="FF0000">*</font>用户名：</td>
                <td width="30%">
                	<input type="hidden" id="userId" name="userId" value="${user.userId}" />
                   ${user.userName }
                </td>
                <td width="30%">
                	<div id="userAccountTip"></div>&nbsp;
                </td>
                </tr><tr>
                <td align="right"><font color="FF0000">*</font>姓名：</td>
                <td>
                    <input name="Name" class="inputText" id="Name" type="text" value="${user.chnName}" /></td>
                 <td>
                	<div id="NameTip"></div>&nbsp;
                </td>
            </tr>
            
           <tr>
                <td align="right"><font color="FF0000">*</font>手机号：</td>
                <td>
                    <input name="mobile" class="inputText" id="mobile" type="text" value="${user.telephone}" />
                </td>
                <td>
                	<div id="mobileTip"></div>&nbsp;
                </td>
            </tr>
            
           <tr>
                <td align="right"><font color="FF0000">*</font>身份证号：</td>
                <td>
                    <input name="idNo" id="idNo" class="inputText" type="text" value="${user.cerNo}" /></td>
                     <td>
                	<div id="idNoTip"></div>&nbsp;
                </td>   
            </tr>
            
             <tr>   
                <td align="right"><font color="FF0000">*</font>出生日期：</td>
                <td>
                    <input name="birthday" id="birthday" type="text" readonly="readonly" class="inputText date_class" value="<fmt:formatDate value="${user.birthday}" pattern="yyyy-MM-dd" />" /></td>
                 <td>
                	<div id="birthdayTip"></div>&nbsp;
                </td>   
            </tr>
            
            <tr>
                <td align="right"><font color="FF0000">*</font>Email：</td>
                <td><input name="email" id="email" class="inputText" type="text" value="${user.email}" /></td>
                <td><div id="emailTip"></div>&nbsp;</td>   
            </tr>
			
            <tr>
                <td align="right"><font color="FF0000">*</font>用户状态：</td>
                <td>
                    <select name="status" id="status" class="selectText">
                    	<option value="">----用户状态----</option>
                    	<option value="1" <c:if test="${user.userStatus=='1'}">selected="selected"</c:if>>激活</option>
                    	<option value="2" <c:if test="${user.userStatus=='2'}">selected="selected"</c:if>>禁用</option>
                    </select>
                </td>
                 <td><div id="statusTip"></div>&nbsp;</td>
             </tr>
            
          <%--  <tr>
                <td><font color="FF0000">*</font>级别：</td>
                <td>
                     <select name="level" id="level" class="selectText">
                     	<option value="">----用户级别----</option>
                     	<option value="1" <c:if test="${user.level == 1}">selected="selected"</c:if>>总经理</option>
                     	<option value="2" <c:if test="${user.level == 2}">selected="selected"</c:if>>副总经理</option>
                     	<option value="3" <c:if test="${user.level == 3}">selected="selected"</c:if>>部门经理</option>
                     	<option value="4" <c:if test="${user.level == 4}">selected="selected"</c:if>>部门主管</option>
                     	<option value="5" <c:if test="${user.level == 5}">selected="selected"</c:if>>普通员工</option>
                     </select>
                </td>
                <td><div id="levelTip"></div></td>
            </tr> --%>
            
            <tr>
                <td align="right"><font color="FF0000">*</font>角色分配：</td>
                <td>
                    <c:forEach items="${roles}" var="s" varStatus="status">
	                    	<input type="checkbox" name="role" id="role${status.index}" <c:forEach items="${uRoles}" var="u"><c:if test="${u.roleId eq s.roleId }">checked="checked"</c:if></c:forEach>
	                    	 value="${s.roleId}" />${s.roleName}
	                    	<c:if test="${status.index!=0 && (status.index+1)%3==0}"><br/></c:if>
                    </c:forEach>
                </td>
                 <td><div id="roleTip"></div>&nbsp;</td>
            </tr>
            <tr>
                <td align="right">通道授权：</td>
                <td>
                    <c:forEach items="${piples}" var="p" varStatus="status">
	                    	<input type="checkbox" name="piple" id="piple${status.index}" <c:forEach items="${uPiples}" var="u"><c:if test="${u.pipleId eq p.pipleId }">checked="checked"</c:if></c:forEach>
	                    	 value="${p.pipleId}" />${p.pipleNumber}&nbsp;&nbsp;
	                    	<c:if test="${status.index!=0 && (status.index+1)%3==0}"><br/></c:if>
                    </c:forEach>
                </td>
                 <td><div id="pipleTip"></div>&nbsp;</td>
            </tr>
            <tr>
                <td align="right">渠道授权：</td>
                <td>
                    <c:forEach items="${channels}" var="c" varStatus="status">
	                    	<input type="checkbox" name="channel" id="channel${status.index}" <c:forEach items="${uChannels}" var="u"><c:if test="${u.channelId eq c.channelId }">checked="checked"</c:if></c:forEach>
	                    	 value="${c.channelId}" />${c.apiKey} &nbsp;&nbsp;
	                    	<c:if test="${status.index!=0 && (status.index+1)%3==0}"><br/></c:if>
                    </c:forEach>
                </td>
                 <td><div id="channelTip"></div>&nbsp;</td>
            </tr>
            <tr>
                <td align="right">CP授权：</td>
                <td>
                    <c:forEach items="${cps}" var="c" varStatus="status">
	                    	<input type="checkbox" name="cp" id="cp${status.index}" <c:forEach items="${uCPs}" var="u"><c:if test="${u.cpId eq c.cpId }">checked="checked"</c:if></c:forEach>
	                    	 value="${c.cpId}" />${c.abbrName} &nbsp;&nbsp;
	                    	<c:if test="${status.index!=0 && (status.index+1)%3==0}"><br/></c:if>
                    </c:forEach>
                </td>
                 <td><div id="channelTip"></div>&nbsp;</td>
            </tr>
        </tbody>
        <tfoot>
            <tr>
                <td colspan="3">
                    <input class="defaultBut" type="submit" value="修改" />
                    <input class="defaultBut" type="button" onclick="javascript:history.go(-1);" value="取消" />
                 </td>
            </tr>
        </tfoot>
    </table>
   </form>
   </div>
</body>
</html>
