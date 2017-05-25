//计算两个日期天数差的函数，通用
function DateDiff(sDate1, sDate2) {//sDate1和sDate2是yyyy-MM-dd格式
	var aDate, oDate1, oDate2, iDays;
	aDate = sDate1.split("-");
	oDate1 = new Date(aDate[1] + '-' + aDate[2] + '-' + aDate[0]); // 转换为yyyy-MM-dd格式
	aDate = sDate2.split("-");
	oDate2 = new Date(aDate[1] + '-' + aDate[2] + '-' + aDate[0]);
	var c = oDate2 - oDate1;
	
	if(c<0){
		//alert("日期选择错误！");
		return -1;
	}else{
		iDays = parseInt(Math.abs(c) / 1000 / 60 / 60 / 24); // 把相差的毫秒数转换为天数
		if(iDays==0){
			return 1;
		}else{
			return iDays; // 返回相差天数
		}
	}
}