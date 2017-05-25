package com.qy.sp.manage.controller.base;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qy.sp.manage.dto.TBlobContent;
import com.qy.sp.manage.service.BlobContentService;

public class BaseController {

	private String params; //参数列表

	private static Logger log = Logger.getLogger(BaseController.class);
	
	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}
	
	public int intOf(Object object) {
		if(isEmpty(object)){
			return 0;
		}else{
			return Integer.parseInt(object.toString());
		}
	}

	public int intOf(Object object, int _default) {
		if (isEmpty(object)) {
			return _default;
		} else {
			return Integer.parseInt(object.toString());
		}
	}

	public long longOf(Object object) {
		if (isEmpty(object)) {
			return 0;
		} else {
			return Long.parseLong(object.toString());
		}
	}

	public long longOf(Object object, long _default) {
		if (isEmpty(object)) {
			return _default;
		} else {
			return Long.parseLong(object.toString());
		}
	}

	public String stringOf(Object object) {
		if (isEmpty(object)) {
			return "";
		} else {
			return object.toString();
		}
	}

	public String stringOf(Object object,String _default) {
		if (isEmpty(object)) {
			return _default;
		} else {
			return object.toString();
		}
	}
	
	public double doubleOf(Object object) {
		if (isEmpty(object)) {
			return 0;
		} else {
			return Double.parseDouble(object.toString());
		}
	}

	public double doubleOf(Object object, double _default) {
		if (isEmpty(object)) {
			return _default;
		} else {
			return Double.parseDouble(object.toString());
		}
	}
	
	public String stringOf(Timestamp timestamp){
		if(isEmpty(timestamp)){
			return null;
		}else{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			return sdf.format(timestamp);
		}
	}
	

	public Date dateOf(Object object) {
		if (isEmpty(object)) {
			return null;
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = null;
			try {
				date = sdf.parse((String) object);
			} catch (ParseException e) {
				e.printStackTrace();
				return null;
			}
			return date;
		}
	}

	public Date date2Of(Object object) {
		if (isEmpty(object)) {
			return null;
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = null;
			try {
				date = sdf.parse((String) object);
			} catch (ParseException e) {
				return null;
			}
			return date;
		}
	}

	public Timestamp timeOf(Object object) {
		if (isEmpty(object)) {
			return null;
		} else {
			Timestamp time = (Timestamp) object;
			return new Timestamp(time.getTime());
		}
	}

	public Timestamp time2Of(Object object) {
		Date date = dateOf(object);
		if (isEmpty(date)) {
			return null;
		} else {
			return new Timestamp(date.getTime());
		}
	}

	public Timestamp time3Of(Object object) {
		Date date = date2Of(object);
		if (isEmpty(date)) {
			return null;
		} else {
			return new Timestamp(date.getTime());
		}
	}

	public boolean isEmpty(Object str) {
		return str == null || "".equals(str);
	}
	
}
