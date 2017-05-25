<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache" /> 
	<meta http-equiv="cache-control" content="no-cache" /> 
	<meta http-equiv="expires" content="0" />
    <title>SP计费平台-角色模块</title>
<style>
.branch {
	cursor: pointer;
	cursor: hand;
	display: block;
}
.leaf {
	display: block;
	margin-left: 16px;
}

a {
	text-decoration: none;
	color: #333;
}
a:hover {
	text-decoration: underline;
}
</style>

	<script language="JavaScript">
	//var GlobalId=null;//定义上次点击的radio的id，以便清除checkbox的选择状态
	//var openImg = new Image(); 
	//openImg.src = "open.gif"; 
	//var closedImg = new Image(); 
	//closedImg.src = "closed.gif"; 

	function tree() {
		this.branches = new Array();
		this.add = addBranch;
		this.write = writeTree;
		this.getChildren = getChildbranch;
		this.getParent = null;
	}

	function branch(id, text, parent,checked, obj) {
		this.id = id;
		this.name="modules";
		this.checked = checked;
		this.text = text;
		this.add = addLeaf;
		this.val = obj;
		
		this.leaves = new Array();
		this.write = writeBranch;
		this.getChildren = getChildren;
		this.parent = parent;
		this.getParent = getParent;
		
	}

	function leaf(text, link, parent,checked,obj) {
		this.text = text;
		this.name="modules";
		this.checked = checked;
		this.link = link;
		this.val = obj;
		this.write = writeLeaf;
		this.parent = parent;
		this.getParent = getParent;
		
	}

	function addBranch(branch) {
		this.branches[this.branches.length] = branch;
	}

	function addLeaf(leaf) {
		this.leaves[this.leaves.length] = leaf;
	}

	function writeTree() {
		var treeString = '';
		var numBranches = this.branches.length;
		for ( var i = 0; i < numBranches; i++)
			treeString += this.branches[i].write();
		document.write(treeString);
	}
	function CheckChild(id, cb) {
		var input;
		input = document.getElementById(id).getElementsByTagName("input");
		for ( var i = 0; i < input.length; i++) {
			if (input[i].type == "checkbox") {
				input[i].checked = cb.checked;
			}
		}
		//设置祖先节点
		SetAcientParent(cb.parentNode, cb);
	}
	function writeBranch() {
		var branchString = '<span class="branch">';
		//branchString += '<img src="open.gif" id="I' + this.id + '" onClick="showBranch(\'' + this.id + '\')"' + ' />'; 
		
		branchString += '<input type="checkbox"  value="'+ this.val+'" name="' + this.name
				+ '" onclick="CheckChild(\''+this.id+'\',this)"';
				
		if(this.checked){
			branchString += ' checked ';
		} 
		
		branchString +='"/>';
		branchString +='<span onClick="showBranch(\'' + this.id
				+ '\')">' + this.text;
		
		branchString += '</span></span>';
		branchString += '<span class="leaf" id="'; 
        branchString += this.id + '">';
		var numLeaves = this.leaves.length;
		for ( var j = 0; j < numLeaves; j++)
			branchString += this.leaves[j].write();
		branchString += '</span>';
		return branchString;
	}
	function IsCheckAll(o) {
		if (!o.checked)
			return false;
		var inputs = o.parentNode.parentNode.getElementsByTagName("input");
		for ( var i = 0; i < inputs.length; i++) {
			if (!inputs[i].checked)
				return false;
		}
		return true;
	}
	function SetAcientParent(sp, cb) {//设置祖先节点
		var IsChecked = cb.checked;
		while (sp.parentNode && sp.parentNode.previousSibling) {
			sp = sp.parentNode.previousSibling;
			if (sp.tagName != "SPAN")
				return;
			if (IsChecked)
				IsChecked = IsCheckAll(cb);
			pcb = sp.getElementsByTagName("input")[0];
			if (pcb)
				pcb.checked = IsChecked;
		}
	}
	function SetParent(cb) {//设置父节点
		var sp = cb.parentNode.parentNode.previousSibling;
		var pcb = sp.getElementsByTagName("input")[0];
		var IsChecked = IsCheckAll(cb, true);
		
		if (pcb) {
			pcb.checked = IsChecked;
			SetAcientParent(sp, pcb);
		}
		
		//只要有子结点选中就选中父节点
		if(cb.checked){
			pcb.checked = true;
		}
	}
	function writeLeaf() {
		var leafString = '<a href="' + this.link + '">';
		//leafString += '<img src="doc.gif" border="0">
		
		leafString += '<input type="checkbox"' ;
		
		if(this.checked){
			leafString += ' checked ';
		} 
		
		leafString += ' value="'+ this.val+'" name="' + this.name
				+ '" onclick="SetParent(this)"/>';
		leafString += this.text;
		leafString += '</a><br>';
		return leafString;
	}

	function showBranch(branch) {
		var objBranch = document.getElementById(branch).style;
		if (objBranch.display == "block")
			objBranch.display = "none";
		else
			objBranch.display = "block";
		//swapFolder('I' + branch); 
	}

	function swapFolder(img) {
		//objImg = document.getElementById(img); 
		//if (objImg.src.indexOf('closed.gif') > -1) 
		//        objImg.src = openImg.src; 
		//else 
		//        objImg.src = closedImg.src; 
	}

	function getChildbranch() {
		return this.branches;
	}

	function getChildren() {
		return this.leaves;
	}

	function getParent(id) {
		return this.parent;
	}
</script>
	</head>

	<body>
	<script language="JavaScript">
	${rmos}
	</script>
	</body>
</html>
