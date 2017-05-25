package com.qy.sp.manage.service;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.qy.sp.manage.common.utils.ClientProperty;
import com.qy.sp.manage.common.utils.FileUtils;

@Service
public class FileUploadService {
	private Logger logger = Logger.getLogger(FileUploadService.class);
	public String uploadFile(String dir,String fileName,HttpServletRequest request) throws IOException{
		String uploadBasePath = ClientProperty.getProperty("config", "upload.root");
		String fileDir = uploadBasePath +dir;
		File uploadDir = FileUtils.getDir(fileDir);
		File file = new File(uploadDir,fileName);
		boolean isSuccess = FileUtils.uploadFile(request.getInputStream(), file,true);
		logger.debug("FileUploadService 上传文件结果:"+isSuccess);
		if(isSuccess)
			return "0";
		else
			return "-1";
	}
}
