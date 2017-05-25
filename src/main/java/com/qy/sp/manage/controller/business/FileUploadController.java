package com.qy.sp.manage.controller.business;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qy.sp.manage.service.FileUploadService;

@Controller
@RequestMapping(value = "/fileupload")
public class FileUploadController {
	@Resource
	private FileUploadService fileUploadService;
	@RequestMapping(value = "/file" ,produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String getPhoneConfigurations(@RequestHeader String dir,@RequestHeader String fileName,HttpServletRequest request){
		String result = "-1";
		try {
			result = fileUploadService.uploadFile(dir, fileName, request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
