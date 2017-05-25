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
		//修改联系信息
		jQuery("#update_lx_but").live('click', function(event){
    		var mobile = jQuery("#mobile").val();
    		var email = jQuery("#email").val();
    		
    		
    		var m = "^([+-]?)\\d*\\.?\\d+$";
    		var e = "^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$";
    		if(mobile.length<7 || mobile.length>20){
    			jQuery("#lxMessage").parent().parent().show();
    			jQuery("#lxMessage").attr("class","onError");
        		jQuery("#lxMessage").html("联系电话必须是7-20位数字,请确认！");
    		}else if(email==''|| email.length>100){
    			jQuery("#lxMessage").parent().parent().show();
    			jQuery("#lxMessage").attr("class","onError");
        		jQuery("#lxMessage").html("email不能为空且最多100个字符！");
    		}else if(!mobile.match(m)){
    			jQuery("#lxMessage").parent().parent().show();
    			jQuery("#lxMessage").attr("class","onError");
        		jQuery("#lxMessage").html("电话号码必须为数字！");
    		}else if(!email.match(e)){
    			jQuery("#lxMessage").parent().parent().show();
    			jQuery("#lxMessage").attr("class","onError");
        		jQuery("#lxMessage").html("email格式不正确！");
    		}else{
    			var _root ='<%=request.getContextPath()%>';
    			var _url = _root+"/basics/doUpdateCurrUser.do";
    			var options = {
    				url: _url,
    		        success: function (data) {
    		        	if(data=="OK"){
    		        		jQuery("#lxMessage").parent().parent().show();
    		        		jQuery("#lxMessage").attr("class","onCorrect");
    		        		jQuery("#lxMessage").html("成功！");
    		        	}else{
    		        		jQuery("#lxMessage").parent().parent().show();
    		        		jQuery("#lxMessage").attr("class","onError");
    		        		jQuery("#lxMessage").html("修改联系信息失败，系统异常!");
    		        	}
    		        }
    		    };
    			jQuery("#lx_frm").ajaxSubmit(options);
    		}
    		
    	});
		
		//修改密码
		jQuery("#update_pwd_but").live('click', function(event){
    		var passwd = jQuery("#passwd").val();
    		var newPasswd = jQuery("#newPasswd").val();
    		var newPasswd2 = jQuery("#newPasswd2").val();
    		if(passwd=='' || newPasswd=='' || newPasswd2==''){
    			jQuery("#pwdMessage").attr("class","onError");
    			jQuery("#pwdMessage").parent().parent().show();
    			jQuery("#pwdMessage").html("密码不能为空!");
    		}else if(newPasswd.length<5){
    			jQuery("#pwdMessage").attr("class","onError");
    			jQuery("#pwdMessage").parent().parent().show();
    			jQuery("#pwdMessage").html("新密码长度不能少于5位!");
    		}else if(newPasswd!=newPasswd2){
    			jQuery("#pwdMessage").attr("class","onError");
    			jQuery("#pwdMessage").parent().parent().show();
    			jQuery("#pwdMessage").html("新密码与确认密码不一致!");
    		}else{ //验证通过，提交页面表单
    			var _root ='<%=request.getContextPath()%>';
    			var _url = _root+"/basics/doUpdatePassword.do";
    			var options = {
    				url: _url,
    		        success: function (data) {
    		        	if(data=="OK"){
    		        		jQuery("#pwdMessage").parent().parent().show();
    		        		jQuery("#pwdMessage").attr("class","onCorrect");
    		        		jQuery("#pwdMessage").html("成功！");
    		        	}else if(data=="FAIL_0"){
    		        		jQuery("#pwdMessage").parent().parent().show();
    		        		jQuery("#pwdMessage").attr("class","onError");
    		        		jQuery("#pwdMessage").html("原密码验证失败!");
    		        	}else{
    		        		jQuery("#pwdMessage").parent().parent().show();
    		        		jQuery("#pwdMessage").attr("class","onError");
    		        		jQuery("#pwdMessage").html("密码修改失败，系统异常!");
    		        	}
    		        }
    		    };
    			jQuery("#pw_frm").ajaxSubmit(options);
    		
    		}
    	});
    });	
    </script>
    
</head>
<body>
    <div class="content-mainBG">
	<form id="account_frm" name="account_frm" method="post">
    <table class="table-user" border="0" cellpadding="0" cellspacing="0">
    	<thead>
            <tr>
                <th colspan="3">
                	帐号信息
                </th>
            </tr>
        </thead>
        <tbody>
        	<tr style="display: none;">
        		<td colspan="3"><div id="accountMessage" align="left"></div></td>
        	</tr>
            <tr>
                <td width="15%" align="right">用户名：</td>
                <td width="30%">
                	${U_SESSION.userName}
                </td>
                <td></td>
            </tr>
            <tr>    
                <td align="right">姓名：</td>
                <td>
                    ${user.chnName}
                </td>
                <td></td>
            </tr>
        </tbody>
    </table>
   </form>
   
   
   <!-- 联系信息 -->
   <form id="lx_frm" name="lx_frm" method="post">
    <table class="table-user" border="0" cellpadding="0" cellspacing="0">
        <thead>
            <tr>
                <th colspan="3">
                	联系信息
                </th>
            </tr>
        </thead>
        <tbody>
        	 <tr style="display: none;">
        		<td colspan="3"><div id="lxMessage" align="left"></div></td>
        	</tr>
            <tr>    
                <td width="15%" align="right"><font color="FF0000">*</font>联系电话：</td>
                <td width="30%">
                    <input name="mobile"  id="mobile" class="inputText" type="text" value="${user.telephone }" />
                </td>
                <td></td>
            </tr>
            <tr>
            	<td align="right"><font color="FF0000">*</font>邮箱：</td>
            	<td><input name="email" id="email" class="inputText" type="text" value="${user.email }" /></td>
            	<td></td>
            </tr>
            
            <tr>
            	<td colspan="3" style="text-align: left;padding-left: 200px;">
            		<input class="defaultBut" type="button" id="update_lx_but" value="修改联系信息" />
            	</td>
            </tr>
        </tbody>
    </table>
   </form>
   
   <!-- 修改密码 -->
   <form id="pw_frm" name="pw_frm" method="post">
    <table class="table-user" border="0" cellpadding="0" cellspacing="0">
        <thead>
            <tr>
                <th colspan="3">
                	账户安全
                </th>
            </tr>
        </thead>
        <tbody>
            <tr style="display: none;">
        		<td colspan="3"><div id="pwdMessage" align="left"></div></td>
        	</tr>
            <tr>
                <td width="15%" align="right"><font color="FF0000">*</font>用户名：</td>
                <td width="30%">
                   ${user.userName}
                </td>
                <td></td>
                </tr>
            <tr>
                <td align="right"><font color="FF0000">*</font>原密码：</td>
                <td>
                    <input name="passwd" id="passwd" type="password" class="inputText" /></td>
                 <td><div id="passwdTip" align="left"></div></td>
            </tr>
            <tr>
                <td align="right"><font color="FF0000">*</font>新密码：</td>
                <td>
                    <input name="newPasswd" id="newPasswd" type="password" class="inputText" />
                 </td>
                  <td><div id="newPasswdTip"></div></td>
            </tr>
            <tr>
                <td align="right"><font color="FF0000">*</font>确认新密码：</td>
                <td>
                    <input name="newPasswd2" id="newPasswd2" type="password" class="inputText"/></td>
                 <td><div id="newPasswd2Tip"></div></td>
            </tr>
            
            <tr>
            	<td colspan="3" style="text-align: left;padding-left: 200px;">
            		<input class="defaultBut" type="button" id="update_pwd_but" value="修改密码" />
            	</td>
            </tr>
        </tbody>
    </table>
   </form>
   </div>
</body>
</html>