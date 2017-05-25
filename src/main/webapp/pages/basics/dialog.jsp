<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipswindown.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tipswindown.js"></script>
<script type="text/javascript">
// 菜单按钮功能显示方式
function butShow(moduleLink,vId,showType,titleMsg) {
	var root = '<%=request.getContextPath()%>';
	if(showType==2){ // 弹出对话框方式
		var url = "iframe:"+root+moduleLink+vId;
		tipsWindown(titleMsg,url,"1024","768","true","","true","","");
	}else if(showType=='' || showType==1){ // 默认跳转页面方式
		document.location.href=root+moduleLink+vId;
	}
}
</script>