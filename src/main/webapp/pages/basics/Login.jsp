<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache" /> 
<meta http-equiv="cache-control" content="no-cache" /> 
<meta http-equiv="expires" content="0" />
<title>SP计费管理平台-登陆</title>
<link rel="icon" href="<%=request.getContextPath()%>/images/title.ico" type="image/x-icon" />
<link href="<%=request.getContextPath() %>/css/login.css" type="text/css" rel="stylesheet"/>
<script src="<%=request.getContextPath()%>/js/common.js" type="text/javascript" language="javascript"></script>
<script src="<%=request.getContextPath()%>/js/jquery.min.js" type="text/javascript" language="javascript"></script>
<script language="JavaScript">
$(document).keyup(function(event){
	  if(event.keyCode ==13){
		  submitForm('loginForm');
	  }
});


function chageIcode(){
	var obj = document.getElementById("icode_img");
 	//获取当前的时间作为参数，无具体意义  
	var timenow = new Date().getTime();
	//每次请求需要一个不同的参数，否则可能会返回同样的验证码
	//这和浏览器的缓存机制有关系，也可以把页面设置为不缓存，这样就不用这个参数了。  
	obj.src = "<%=request.getContextPath() %>/jcaptcha?d=" + timenow;
}

/* jQuery(document).ready(function(){
	changeBGImage();
});

function changeBGImage(){
	 var image = new Array(7); //定义image为图片数量的数组
	 image [0] = "0.jpg"; //背景图象的路径
	 image [1] = "1.jpg";
	 image [2] = "2.jpg";
	 image [3] = "3.jpg";
	 image [4] = "4.jpg";
	 image [5] = "5.jpg";
	 image [6] = "6.jpg";
　 	 var number = Math.floor(Math.random() * image.length);
	 var imagePath = "../images/login/"+image[number];
	 jQuery("#bg8").css("background-image","url("+imagePath+")");
} */

//防止在iframe中打开登录页面
if (window != top) 
	top.location.href = location.href;
</script>
</head>
<body>
<div class="login-main-BG">
	<div class="login-top-BG"
		style="text-align: center;">
	</div>
    <div class="login-content-BG" id="bg8" style="background: url(../images/login/4.jpg) 50% repeat-x" >
    <div class="login-title">SP计费管理平台</div>
     <form action="<%=request.getContextPath() %>/basics/doLogin.do" method="post" id="loginForm" name="loginForm">
        <table>
        	<tbody>
        		<tr>
        			<td>
        				<div id="uidBG">
        					<input type="text" class="textClass" id="userName" name="userName" />
        				</div>
        			</td>
        		</tr>
        		<tr>
        			<td>
        				<div id="pwdBG">
        					<input type="password" id="password" name="password" class="textClass" />
        				</div>
        			</td>
        		</tr>
        		
        		<tr>
        			<td>
        				<div id="codeBG">
        					<input class="textClass" type='text' name='iCode' id="iCode" value='' maxlength="6" />
        				</div>
        				<img  id="icode_img" title="换一个？" onclick="chageIcode();" name="icode_img" src="<%=request.getContextPath() %>/jcaptcha" />
        			</td>
        		</tr>
        	</tbody>
        	
        	<tfoot>
        		<tr>
        			<td align="right">
        				<input type="button" value="登 录 " id="login" name="login" onclick="javascript:submitForm('loginForm');" class="btn-login" />
        			</td>
        		</tr>
        		
        		<tr>
        			<td>
        				<p class="error">${eMessage}</p>
        			</td>
        		</tr>
        		
        	</tfoot>
        </table>
       </form>
 
</div>
    <div class="login_footer">
    	<jsp:include page="foot.jsp" />
  </div> 
  </div>
</body>
</html>
