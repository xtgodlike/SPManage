<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	<title>SP计费平台-系统错误</title>
    <jsp:include page="../basics/baseJs.jsp" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/login.css" />
    <jsp:include page="../basics/validator.jsp" />
    <script type="text/javascript">
    $(document).ready(function () {
    	$.formValidator.initConfig({
    		 formID:"registerFrm",
    		 submitOnce:true,
    		 onError:function(msg,obj,errorlist){
    		 	
    		 },
    		 onSuccess:function(){
    		 	
    		 }
    	});
   });   
	
    
    </script>
</head>
<body>
 
 <div class="login-main-BG">
	<div class="login-top-BG"
		style="text-align: center;">
	</div>
	<div class="content-BG" style="padding-top:50px;">
	对不起，系统异常。请稍后重试！
	错误信息：${errorMsg}
	</div>
    <div class="login_footer">
    	<jsp:include page="../basics/foot.jsp" />
  	</div> 
  </div>
 
</body>
</html>