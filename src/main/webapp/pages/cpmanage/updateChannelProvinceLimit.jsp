<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache" /> 
	<meta http-equiv="cache-control" content="no-cache" /> 
	<meta http-equiv="expires" content="0" />
	<title>CP代码管理-分省限量</title>
    <jsp:include page="../basics/baseJs.jsp" />
    <jsp:include page="../basics/validator.jsp" />
    <jsp:include page="../basics/dialog.jsp" />
    <script type="text/javascript">
    $(document).ready(function () {
    	$.formValidator.initConfig({
   		 formID:"frm",
   		 debug:true,
   		 onError:function(msg,obj,errorlist){
   		 	
   		 },
   		 onSuccess:function(){
   			// 更新提示信息
 			jQuery("#msgTip").attr("class","onCorrect");
 			jQuery("#msgTip").html("请求已提交，请稍候...");
 			var options = {
 				url: "<%=request.getContextPath()%>/piple/doUpdateChannelProvinceLimit.do",
 		        success: function (data){
 		        	var rs = data.split("|");
 		        	if(rs[0]=="DAYEXCESS"){
 		        		jQuery("#msgTip").attr("class","onError");
 		        		jQuery("#msgTip").html("代码省份分配日限额已超过代码日限总额："+rs[1]+"。配置失败！");
 		        	}
 		        	if(rs[0]=="MONTHEXCESS"){
 		        		jQuery("#msgTip").attr("class","onError");
 		        		jQuery("#msgTip").html("代码省份分配月限额已超过代码月限总额："+rs[1]+"。配置失败！");
 		        	}
 		        	if(rs[0]=="FAIL"){
 		        		jQuery("#msgTip").attr("class","onError");
 		        		jQuery("#msgTip").html("分省限额配置失败！"+rs[1]);
 		        	}
 		        	if(rs[0]=="OK"){
 		        		jQuery("#msgTip").attr("class","onCorrect");
 		        		var dm = "";
 		        		if(rs[1]=="-1.0"){
 		        			dm = "代码日限总额未限制！";
 		        		}else{
 		        			dm = "代码日限总额剩余："+rs[1]+"。";
 		        		}
 		        		var mm = "";
 		        		if(rs[2]=="-1.0"){
 		        			mm = "代码月限总额未限制！";
 		        		}else{
 		        			mm = "代码月限总额剩余："+rs[2]+"。";
 		        		}
 		        		jQuery("#msgTip").html("分省限额配置成功！</br>"+dm+"</br>"+mm);
 		        	}
 		        }
 		    };
 			jQuery("#frm").ajaxSubmit(options);
 			return false;
   		 }
   		});
		
    	jQuery("#closeWin").live('click', function(event){
    		closeEigsx();
		});
    });	
    </script>
</head>
<body>
	<div class="content-mainBG" style="min-width: 950px;">
	<form  id="frm" name="frm" method="post">
	<input type="hidden" id="pipleId" name="pipleId" value="${piple.pipleId}"/>
	<input type="hidden" id="channelId" name="channelId" value="${channel.channelId}"/>
    <table class="table-content" style="width: 30%;margin: auto;" border="0" cellpadding="0" cellspacing="0"> 
        <thead>
        	<tr>
                <td colspan="3" align="center" width="100%">
                  <div style="width: 400px;color:red;text-align:center;" id="msgTip">提示:0为默认值，不限制</div>
                </td>
                 </td>
            </tr>
        	<tr style="height: 50px;">
                <td colspan="3" align="center" width="100%">
                    <input class="defaultBut" type="submit" id="updateChannelProvinceLimit"  value="提交" />
                    <input class="defaultBut" type="button" id="closeWin" value="关闭" />
                 </td>
            </tr>
            <tr>
                <th style="width: 200px;">开通省份</th> 
                <th style="width: 80px;">日限</th>
                <th style="width: 80px;">月限</th>
            </tr>
        </thead>
        <tbody>
        	<c:if test="${empty cpls}">
        	<c:forEach items="${pipleProvinces}" var="p">
        	<c:if test="${p.opStatus==1}">
        	<tr>
                <td>${p.provinceName}</td>
                <td>
                	<input id="tradeDay${p.provinceId}" name="tradeDay${p.provinceId}"     style="width: 80px;" />
                </td> 
                  <td>
                	<input id="tradeMonth${p.provinceId}" name="tradeMonth${p.provinceId}"     style="width: 80px;" />
                </td>
            </tr>
            </c:if>
        	</c:forEach>
        	</c:if>
        	<c:if test="${!empty cpls}">
        	<c:forEach items="${cpls}" var="p">
        	<c:if test="${p.opStatus==1}">
        	<tr>
                <td>${p.provinceName}</td>
                <td>
                	<input class="_priority" id="tradeDay${p.provinceId}" name="tradeDay${p.provinceId}"  value="${p.tradeDay }"   maxlength="6"  style="width: 130px;" />
                </td> 
                  <td>
                	<input class="_priority" id="tradeMonth${p.provinceId}" name="tradeMonth${p.provinceId}"  value="${p.tradeMonth }"   maxlength="6"  style="width: 130px;" />
                </td> 
            </tr>
            </c:if>
        	</c:forEach>
        	</c:if>
        </tbody>
    </table>
   </form>
</div>
</body>
</html>
