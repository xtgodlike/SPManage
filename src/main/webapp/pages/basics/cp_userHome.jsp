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
		 //修改账号信息
    	jQuery("#update_account_but").live('click', function(event){
    		var _root ='<%=request.getContextPath()%>';
			var _url = _root+"/basics/doUpdateCurrentAccount.do";
			var options = {
				url: _url,
		        success: function (data) {
		        	if(data=="OK"){
		        		jQuery("#accountMessage").parent().parent().show();
		        		jQuery("#accountMessage").attr("class","onCorrect");
		        		jQuery("#accountMessage").html("账号信息修改成功！");
		        	}else{
		        		jQuery("#accountMessage").parent().parent().show();
		        		jQuery("#accountMessage").attr("class","onError");
		        		jQuery("#accountMessage").html("帐号信息修改失败，系统异常!");
		        	}
		        }
		    };
			jQuery("#account_frm").ajaxSubmit(options);
    	});

		//修改联系信息
		jQuery("#update_lx_but").live('click', function(event){
    		var mobile = jQuery("#mobile").val();
    		var email = jQuery("#email").val();
    		var linkMan = jQuery("#linkMan").val();
    		
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
    		}else if(linkMan.length<2 || linkMan.length>30){
    			jQuery("#lxMessage").parent().parent().show();
    			jQuery("#lxMessage").attr("class","onError");
        		jQuery("#lxMessage").html("联系人至少2个字符,最多30个字符！");
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
    <div class="home_content_bg">
			<a>帐号信息</a>
	</div>
    <div class="content-mainBG">
	
	<form id="account_frm" name="account_frm" method="post">
    <table class="table-user" border="0" cellpadding="0" cellspacing="0">
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
                <td align="right">公司名称：</td>
                <td>
                    ${cpInfo.CPName}
                </td>
                 <td></td>
            </tr>
            <tr>
            	<td align="right">可配置渠道数量：</td>
            	<td>${cpInfo.channelMax}</td>
            	 <td></td>
            </tr>
            
            <tr>
            	<td align="right">状态：</td>
            	<td>
            		<c:if test="${cpInfo.CPStatus==1}">待审核</c:if>
       				<c:if test="${cpInfo.CPStatus==2}"><font style="color: red;">待商用（无法进行交易）</font></c:if>
       				<c:if test="${cpInfo.CPStatus==3}">商用</c:if>
       				<c:if test="${cpInfo.CPStatus==4}"><font style="color: red;">暂停（系统维护中，无法进行交易）</font></c:if>
            	</td>
            	<td>
            	</td>
            </tr>
            
            <tr>
            	<td align="right">数据接收接口地址：</td>
            	<td>
            		<input type="text" id="callBackUrl" value="${cpInfo.callbackUrl}" style="width: 400px;" name="callBackUrl" />
            	</td>
            	<td><div id="callBackUrlTip"></div></td>
            </tr>
             <c:if test="${U_SESSION.userType==1}">
            <tr>
            	<td colspan="3" style="text-align: left;padding-left: 200px;">
            		<input class="defaultBut" type="button" id="update_account_but" value="修改账号信息" />
            	</td>
            </tr>
            </c:if>
        </tbody>
    </table>
   </form>
   
   <!-- 计费点及费用方式 -->
    <table class="table-user"  cellpadding="0" cellspacing="0">
    	<thead>
    		<tr>
                <th colspan="3">
                	计费信息
                </th>
            </tr>
    	</thead>
		<tbody>
			<tr style="text-align: center;font-weight: bold;font-size: 15px;">
				<td width="20%" style="border-right: 1px solid #dbdbdb;border-bottom: 1px solid #dbdbdb;">
					运营商
				</td>
				<td width="20%" style="border-right: 1px solid #dbdbdb;border-bottom: 1px solid #dbdbdb;">
					计费方式
				</td>
				<td width="60%" style="border-bottom: 1px solid #dbdbdb;">
					费用方式(已开通)
				</td>
			</tr>
		
			<c:forEach items="${hosts}" var="host">	
			<tr>
				<td rowspan="${host.hostChargeFees.size()}" style="text-align: center;border-right: 1px solid #dbdbdb;border-bottom: 1px solid #dbdbdb;">
					${host.hostName}
				</td>
				<c:choose>
				<c:when test="${host.hostChargeFees.size()==0}">
					<td  style="padding-left: 8px;border-bottom: 1px solid #dbdbdb;color: #3cc457;" colspan="2">暂未开通</td>
				</c:when>
				<c:when test="${host.hostChargeFees.size()!=0}">
				<c:forEach items="${host.hostChargeFees}" var="hcf" varStatus="i">
				<c:if test="${i.index==0}">
					<td style="text-align:center;border-right: 1px solid #dbdbdb;border-bottom: 1px solid #dbdbdb;">
						${hcf.chargeType.chargeTypeName}
					</td>
					<td style="padding-left: 8px;border-bottom: 1px solid #dbdbdb;">
						<c:forEach items="${hcf.cpFeeRelas}" var="cfr">
						<c:if test="${cfr.opStatus==1}"> ${cfr.feeType.fee} |</c:if>
						</c:forEach>
					</td>
				</c:if>
				<c:if test="${i.index!=0}">
					<tr>
					<td style="text-align:center;border-right: 1px solid #dbdbdb;border-bottom: 1px solid #dbdbdb;">
						${hcf.chargeType.chargeTypeName}
					</td>
					<td  style="padding-left: 8px;border-bottom: 1px solid #dbdbdb;">
						<c:forEach items="${hcf.cpFeeRelas}" var="cfr">
						<c:if test="${cfr.opStatus==1}"> ${cfr.feeType.fee} |</c:if>
						</c:forEach>
					</td>
					</tr>
				</c:if>
				</c:forEach>
				</c:when>
				</c:choose>
			</tr>
			</c:forEach>
		</tbody>
    </table>
   
   
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
                <td width="15%" align="right"><font color="FF0000">*</font>联系人：</td>
                <td width="30%">
                	<input type="text" value="${cpInfo.linkman}" id="linkMan" name="linkMan" />
                </td>
                <td></td>
            </tr>
            <tr>    
                <td align="right"><font color="FF0000">*</font>联系电话：</td>
                <td>
                    <input name="mobile"  id="mobile" class="inputText" type="text" value="${cpInfo.adminUser.telephone }" />
                </td>
                <td></td>
            </tr>
            <tr>
            	<td align="right"><font color="FF0000">*</font>邮箱：</td>
            	<td><input name="email" id="email" class="inputText" type="text" value="${cpInfo.adminUser.email }" /></td>
            	<td></td>
            </tr>
            
            <c:if test="${U_SESSION.userType==1}">
	            <tr>
	            	<td colspan="3" style="text-align: left;padding-left: 200px;">
	            		<input class="defaultBut" type="button" id="update_lx_but" value="修改联系信息" />
	            	</td>
	            </tr>
            </c:if>
        </tbody>
    </table>
   </form>
   
   <c:if test="${U_SESSION.layoutId==0}">
   <!-- 文档信息 -->
   <table class="table-user" border="0" cellpadding="0" cellspacing="0">
        <thead>
            <tr>
                <th colspan="3">
                	文档信息
                </th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td width="15%" align="right">开发者中心：</td>
                <td width="30%">
                	 <c:if test="${U_SESSION.layoutId==0}">
                   	 	<a href="http://www.chinaunigame.net/feeapi4/" target="_Blank" title="点击访问">http://www.chinaunigame.net/feeapi4/</a>
                   	 </c:if>
                   	 <c:if test="${U_SESSION.layoutId==1}">
                   	 	<a href="http://${idn}/feeapi4/" target="_Blank" title="点击访问">http://${idn}/feeapi4/</a>
                   	 </c:if>
                </td>
                <td></td>
            </tr>
            <tr>
                <td width="15%" align="right">使用说明文档：</td>
                <td width="30%">
                   	 <a href="<%=request.getContextPath()%>/basics/downloadFlie.do?path=${cpPFPath}" target="_Blank" title="点击下载">计费渠道分销平台使用说明V2.0_合作商.doc</a>
                </td>
                <td></td>
            </tr>
        </tbody>
    </table>
   </c:if>
   
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
                   ${cpInfo.adminUser.userName}
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