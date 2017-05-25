<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SP计费管理平台</title>
<!-- <link rel="icon" href="<%=request.getContextPath()%>/images/title.ico" type="image/x-icon" /> -->
<link rel="stylesheet" type="text/css" href="../../css/easyui/default/easyui.css">
<script type="text/javascript" src="../../js/jquery.min.js"></script>
<script type="text/javascript" src="../../js/jquery.easyui.min.js"></script>
<style type="text/css">
.selected{background:#00FFFF;}
.easyui-accordion div p{height:35px;padding-left:10px;margin-top:0px;margin-bottom:0px;line-height:35px;}
</style>
<script type="text/javascript">
   
   function openTab(id,name,url){
	  //alert(id+";"+name+";"+url);
	  changeItemBackground(id);
	   addTab(name,url);
   }
   function changeItemBackground(id){
	   var item = $(".easyui-accordion div p").removeClass("selected");
	   $("#"+id).addClass("selected");
   }
   
   function addTab(subtitle,url){
    if(!$('#tabs').tabs('exists',subtitle)){
        $('#tabs').tabs('add',{
            title:subtitle,
            content:createFrame(url),
            closable:true,
            width:$('#mainPanle').width()-10,
            height:$('#mainPanle').height()-26
        });
    }else{
        $('#tabs').tabs('select',subtitle);
    }
    //tabClose();
}

    function createFrame(url)
    {
        var s = '<iframe name="mainFrame" scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
        return s;
    }
    
   function moduleIndex(menusData)
   {
      var text="";
      text += '<ul>';
      $.each(menusData,function(j,o)
      {
          text += '<li><div ><a target="mainFrame" href="'+o.attributes+'" >' + o.text + '</a></div></li> ';
      }); 
      text += '</ul>';
      return text;
   }
   
    </script>
</head>
<body class="easyui-layout"  onload="openTab('1001','个人信息','<%=request.getContextPath()%>/basics/userCenter.do')">
		<div data-options="region:'north',split:true" title="欢迎使用计费管理平台!" style="height:70px;">
			<div style="padding-top:5px;text-align:left;width: 50%;float: left;" >
			<!--	<img src="<%=request.getContextPath()%>/images/qy_logo1.png"/> -->
			</div>
			<div style="padding-top:5px;text-align:right;width: 50%;float: right;" >
	   		<span title="个人信息" onClick="openTab('1001','个人信息','<%=request.getContextPath()%>/basics/userCenter.do')" style="vertical-align: top;height:28px;line-height:28px;text-align: center;">
	   			<img src="<%=request.getContextPath()%>/images/user-female.png">
	   			${U_SESSION.chnName}
	   		</span>
	   		&nbsp;|&nbsp;
	    	<a title="安全退出" href="<%=request.getContextPath()%>/basics/logOut.do" style="vertical-align: top;padding-right: 20px;height:28px;line-height:28px;">
	    		<img src="<%=request.getContextPath()%>/images/logout.png">
	    		安全退出
	    	</a>
			</div> 
		</div>
		<div data-options="region:'west',split:true" title="导航菜单" style="width:120px;">
			<div class="easyui-accordion" data-options="fit:true,border:false">
			 <c:forEach items="${QY_MENU.rootMenus}" var="p">
         		<div title="${p.selfMenu.moduleName}">
          		<c:forEach items="${p.childMenu}" var="c" varStatus="status">
          		<p  id="${c.selfMenu.moduleId}" onClick="openTab('${c.selfMenu.moduleId}','${c.selfMenu.moduleName}','<%=request.getContextPath()%>${c.selfMenu.moduleLink}')"><span>${c.selfMenu.moduleName}</span></p>
          		</c:forEach>
          	 	</div>
          	 </c:forEach>
          </div>
		</div>
		<div id="mainPanle" data-options="region:'center',title:''">
			<div id="tabs" class="easyui-tabs" data-options="fit:true,border:false,plain:true">
			</div>
		</div>
</body>
</html>