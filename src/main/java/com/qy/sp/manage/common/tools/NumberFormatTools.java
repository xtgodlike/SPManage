package com.qy.sp.manage.common.tools;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class NumberFormatTools {
	public static double round(Double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }

        BigDecimal b = (null == v ? new BigDecimal("0.0") : new BigDecimal(Double.toString(v)));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
	
	public static String doubleFormat(double d, int digitals){
		DecimalFormat df2=(DecimalFormat) DecimalFormat.getInstance();
		StringBuffer pattern = new StringBuffer("0");
		if(digitals > 0){
			pattern.append(".");
		}
		for(int i = 0; i < digitals; i++){
			pattern.append("0");
		}
		df2.applyPattern(pattern.toString());
		String val = df2.format(d);
		return val;
	} 
}
