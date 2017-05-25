// JavaScript Document

/**
 * 
 * @param tableId
 * @param maxIndId
 * @return
 */
function addFileTr(fileDivId, maxIndId)
{
	var maxIndObj = document.getElementById(maxIndId);
	maxIndObj.value = parseInt(maxIndObj.value) + 1;
	
	var htmlStr = "";
	htmlStr += "<div class=\"uploadDiv\" id=\"uploadDiv_"+maxIndObj.value+"\">";
	htmlStr += "	<input type=\"hidden\" name=\"fileInd\" value=\""+maxIndObj.value+"\"/>";
//	htmlStr += "	<input id=\"uploadFile_"+maxIndObj.value+"\" name=\"signFile\" type=\"file\" class=\"uploadFile\" onclick=\"fileInputOnChange('uploadFile_"+maxIndObj.value+"','uploadFileBtn_"+maxIndObj.value+"');\" onchange=\"fileInputOnChange('uploadFile_"+maxIndObj.value+"','uploadFileBtn_"+maxIndObj.value+"');\"/>";
//	htmlStr += "	<input id=\"uploadFileBtn_"+maxIndObj.value+"\" value=\"添加附件\" type=\"button\" title=\"点击添加附件\" class=\"uploadFileBtn\" onclick=\"uploadFile_"+maxIndObj.value+".click();\" onmouseout=\"this.style.background=\'#F5F7FA\';\" onmouseover=\"this.style.background=\'#E1E1E1\';\"/>";
	htmlStr += "	<input id=\"uploadFile_"+maxIndObj.value+"\" name=\"signFile\" type=\"file\" class=\"uploadFile\" onmouseout=\"document.getElementById('uploadFileBtn_"+maxIndObj.value+"').style.background=\'#F5F7FA\';\" onmouseover=\"document.getElementById('uploadFileBtn_"+maxIndObj.value+"').style.background=\'#E1E1E1\';\" onclick=\"fileInputOnChange('uploadFile_"+maxIndObj.value+"','uploadFileBtn_"+maxIndObj.value+"');\" onchange=\"fileInputOnChange('uploadFile_"+maxIndObj.value+"','uploadFileBtn_"+maxIndObj.value+"');\"/>";
	htmlStr += "	<input id=\"uploadFileBtn_"+maxIndObj.value+"\" value=\"添加附件\" type=\"button\" title=\"点击添加附件\" class=\"uploadFileBtn\"/>";
	htmlStr += "	<div class=\"deleteFile\" onclick=\"document.getElementById('uploadDiv_"+maxIndObj.value+"').parentNode.removeChild(document.getElementById('uploadDiv_"+maxIndObj.value+"'));\" title=\"点击删除附件\" onmouseout=\"this.style.background=\'#D8D8D8\';\" onmouseover=\"this.style.background=\'#A1A1A1\';\">×</div>";
	htmlStr += "</div>";
	
	$("#"+fileDivId).append(htmlStr);
}

/*
 * 相应file控件的onchange事件，将文件地址复制给Text控件
 */
function fileInputOnChange(fileId, fileTextId){
	var fileObj = document.getElementById(fileId);
	var fileTextObj = document.getElementById(fileTextId);
	if (window.navigator.userAgent.indexOf("MSIE") >= 0){	//IE浏览器
		if(window.navigator.userAgent.indexOf("MSIE 9.0") >= 0){
			fileTextObj.value = fileObj.value.substring(fileObj.value.lastIndexOf('\\')+1,fileObj.value.length);
		}else{
			fileObj.select();
			fileTextObj.value = document.selection.createRange().text;
		}
	}else if(window.navigator.userAgent.indexOf("Firefox") >= 1){	//火狐
		if(fileObj.files != null){
			fileTextObj.value = fileObj.value.substring(fileObj.value.lastIndexOf('\\')+1,fileObj.value.length);
		}
	}else{
		fileTextObj.value = fileObj.value.substring(fileObj.value.lastIndexOf('\\')+1,fileObj.value.length);
	}
}