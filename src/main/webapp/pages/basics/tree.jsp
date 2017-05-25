<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%String path = request.getContextPath();%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
		<head>
		<title>tree</title>
		<jsp:include page="baseJs.jsp" />
		<script type="text/javascript">
	jQuery(document).ready(function() {
		jQuery(".v_tree").live('click', function(event) {
			var object = jQuery(this);
			var parentDiv = object.parent("div");
			var isChecked = object.attr("checked");
			var values = parentDiv.find("input[type='checkbox']");
			for ( var i = 0; i < values.length; i++) {
				jQuery(values[i]).attr("checked", isChecked);
			}

			//三级菜单
			var test3 = jQuery(".tree-main-2");
			for ( var q = 0; q < test3.length; q++) {
				var temp3 = jQuery(test3[q]).find("input[type='checkbox']");
				var checked3 = false;
				for ( var n = 1; n < temp3.length; n++) {
					if (jQuery(temp3[n]).attr("checked")) {
						checked3 = true;
						break;
					}
				}
				if (checked3) {
					jQuery(temp3[0]).attr("checked", true);
				}
			}

			//二级菜单
			var test2 = jQuery(".tree-main-1");
			for ( var k = 0; k < test2.length; k++) {
				var temp = jQuery(test2[k]).find("input[type='checkbox']");

				var checked2 = false;
				for ( var l = 1; l < temp.length; l++) {
					if (jQuery(temp[l]).attr("checked")) {
						checked2 = true;
						break;
					}
				}
				
				if(temp.length>1){
					if(!isChecked){
						jQuery(temp[0]).attr("checked", checked2);
					}
				}
				
				/*if(isChecked){
					jQuery(temp[0]).attr("checked", true);
				}*/
			}

			//根节点
			var test = jQuery("#tree-main").find("input[type='checkbox']");
			var childIsChecked = false;
			for ( var j = 1; j < test.length; j++) {
				if (jQuery(test[j]).attr("checked")) {
					childIsChecked = true;
					break;
				}
			}
			jQuery(test[0]).attr("checked", childIsChecked);
		});

	});
</script>
<style type="text/css">
.tree-main-1,.tree-main-2,.tree-main-3 {
	margin-left: 20px;
}
</style>
	</head>
	<body>
		<div class="content-mainBG">
		<div id="tree-main">
		<c:forEach items="${rmos}" var="s">
				<c:if test="${s.moduleLevel eq '0' }">
					<input type="checkbox" class="v_tree" name="modules" <c:if test="${s.isChecked == 1 }">checked="checked"</c:if>  value="${s.moduleId}" />${s.moduleName}
				</c:if>
				<c:if test="${s.moduleLevel eq '1' }">
					<div class="tree-main-1">
						<input type="checkbox" class="v_tree" name="modules" <c:if test="${s.isChecked == 1 }">checked="checked"</c:if> value="${s.moduleId}" />${s.moduleName}
						<c:forEach items="${rmos}" var="s2">
							<c:if test="${s2.parentMId eq s.moduleId}">
								<div class="tree-main-2">
									<input type="checkbox" class="v_tree" <c:if test="${s2.isChecked == 1 }">checked="checked"</c:if> name="modules" value="${s2.moduleId}" />${s2.moduleName}
								<c:forEach items="${rmos}" var="s3">
								<c:if test="${s3.parentMId eq s2.moduleId}">
									<div class="tree-main-3">
										<input type="checkbox" class="v_tree" <c:if test="${s3.isChecked == 1 }">checked="checked"</c:if> name="modules" value="${s3.moduleId}" />${s3.moduleName}
									</div>
								</c:if>
								</c:forEach>
								</div>
							</c:if>
						</c:forEach>
					</div>
				</c:if>
		</c:forEach>
		</div>
			<!-- tree
			<div id="tree-main">
				<input type="checkbox" class="v_tree" />
				系统根目录
				<div class="tree-main-1">
					<input type="checkbox" class="v_tree" />
					系统管理
					<div class="tree-main-2">
						<input type="checkbox" class="v_tree" />
						用户管理
						<div class="tree-main-3">
							<input type="checkbox" class="v_tree" />
							添加用户
						</div>
						<div class="tree-main-3">
							<input type="checkbox" class="v_tree" />
							修改用户
						</div>
						<div class="tree-main-3">
							<input type="checkbox" class="v_tree" />
							删除用户
						</div>
					</div>
					<div class="tree-main-2">
						<input type="checkbox" class="v_tree" />
						模块管理
						<div class="tree-main-3">
							<input type="checkbox" class="v_tree" />
							添加模块
						</div>
						<div class="tree-main-3">
							<input type="checkbox" class="v_tree" />
							修改模块
						</div>
						<div class="tree-main-3">
							<input type="checkbox" class="v_tree" />
							删除模块
						</div>
					</div>
				</div>
				<div class="tree-main-1">
					<input type="checkbox" class="v_tree" />
					业务管理
					<div class="tree-main-2">
						<input type="checkbox" class="v_tree" />
						个人业务
						<div class="tree-main-3">
							<input type="checkbox" class="v_tree" />
							添加
						</div>
						<div class="tree-main-3">
							<input type="checkbox" class="v_tree" />
							修改
						</div>
						<div class="tree-main-3">
							<input type="checkbox" class="v_tree" />
							删除
						</div>
					</div>
				</div>
			</div>
			 -->
			<!-- end -->
		</div>
	</body>
</html>