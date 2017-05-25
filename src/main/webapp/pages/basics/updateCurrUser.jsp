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
    <title>SP计费平台-个人资料</title>
    <jsp:include page="baseJs.jsp" />
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
    });	
    </script>
</head>
<body>
    <div class="content-mainBG">
	<form action="<%=path%>/basics/doUpdateCurrUser.do" id="frm" name="frm" method="post">
    <table class="table-user" border="0" cellpadding="0" cellspacing="0">
        <thead>
            <tr>
                <th colspan="3">
                	<div class="table-user-nav">
                		<div class="table-user-nav-div" onclick="javascript:document.location.href='<%=path%>/basics/userCenter.do'">
                			帐号信息
                		</div>
	                	<div class="currentUserNav" onclick="javascript:document.location.href='<%=path%>/basics/toUpdateUserInfo.do?userId=${user.userId}'" >
	                		联系信息
	                	</div>
	                	<div class="table-user-nav-div" onclick="javascript:document.location.href='<%=path%>/basics/toUpdatePassword.do?userId=${user.userId}'">
	                		修改密码
	                	</div>
                	</div>
                </th>
            </tr>
        </thead>
        <tbody>
             <tr>  
                <td width="40%"><font color="FF0000">*</font>联系人：</td>
                <td width="30%">
                	<input type="hidden" id="userId" name="userId" value="${user.userId}" />
                    <input name="Name" id="Name" type="text" class="inputText" value="${user.chnName}" />
                </td>
                <td width="30%"><div id="NameTip"></div></td>
            </tr>
            
            <tr>
                <td><font color="FF0000">*</font>联系电话：</td>
                <td>
                    <input name="mobile" id="mobile" class="inputText" type="text" value="${user.telephone }" /></td>
                <td><div id="mobileTip"></div></td>     
            </tr>
            
             <tr>
                <td><font color="FF0000">*</font>邮箱：</td>
                <td>
                    <input name="email" id="email" class="inputText" type="text" value="${user.email }" /></td>
                <td><div id="emailTip"></div></td>     
            </tr>
        </tbody>
        <tfoot>
            <tr>
                <td colspan="3">
                    <input class="defaultBut" type="submit" value="修改" />
                 </td>
            </tr>
        </tfoot>
    </table>
   </form>
   </div>
</body>
</html>
