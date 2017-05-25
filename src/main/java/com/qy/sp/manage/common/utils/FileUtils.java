package com.qy.sp.manage.common.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.http.HttpServletResponse;

public class FileUtils {

	
	/**
	 * 将流转换为string
	 * @param iStream
	 * @return
	 * @throws Exception
	 */
	public static String reader(InputStream iStream) throws Exception{
		BufferedReader br = null;
		InputStreamReader reader = null;
		StringBuffer buff = new StringBuffer();
		try {
			reader = new InputStreamReader(iStream);
			br = new BufferedReader(reader);
			String line = null;
			while ((line = br.readLine()) != null) {
				buff.append(line);
			}
		}finally{
			if(null!=iStream){
				iStream.close();
			}
			if(null!=reader){
				reader.close();
			}
			if(null!=br){
				br.close();
			}
		}
		return buff.toString();
	}
	
	public static void downloadNet(String path, HttpServletResponse response)
			throws MalformedURLException {
		try {
			//path是指欲下载的文件的路径。
			File file = new File(path);
			// 取得文件名。
			String filename = file.getName();
			// 取得文件的后缀名。
			// String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

			URL url = new URL(path);
			URLConnection conn = url.openConnection();
			InputStream inStream = conn.getInputStream();

			// 获得此 URL 的内容。
			int length = Integer.parseInt(conn.getHeaderField("Content-Length"));
			// 以流的形式下载文件。
			InputStream fis = new BufferedInputStream(inStream);
			byte[] buffer = InputStreamToByte(fis);

			// 清空response
			response.reset();
			response.addHeader("Content-Disposition", "attachment;filename="+ new String(filename.getBytes()));
			response.addHeader("Content-Length", "" + length);
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
			response.setContentType("application/octet-stream");
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
			inStream.close();
			fis.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public static byte[] InputStreamToByte(InputStream is) throws IOException {
		ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
		int ch;
		while ((ch = is.read()) != -1) {
			bytestream.write(ch);
		}
		byte imgdata[] = bytestream.toByteArray();
		bytestream.close();
		return imgdata;
	}
	 public static boolean uploadFile(InputStream sourceFileInputStream, File targetFile,boolean isAppend) {
	    	boolean isSuccess =false;
	        BufferedInputStream inBuff = null;
	        BufferedOutputStream outBuff = null;
	        try {
	            // 新建文件输入流并对它进行缓冲
	            inBuff = new BufferedInputStream(sourceFileInputStream);
	            // 新建文件输出流并对它进行缓冲
	            outBuff = new BufferedOutputStream(new FileOutputStream(targetFile,isAppend));
	            // 缓冲数组
	            byte[] b = new byte[1024 * 5];
	            int len;
	            while ((len = inBuff.read(b)) != -1) {
	                outBuff.write(b, 0, len);
	            }
	            // 刷新此缓冲的输出流
	            outBuff.flush();
	            isSuccess = true;
	        } catch (Exception e) {
	        	isSuccess = false;
	        } finally {
	            // 关闭流
	            try {
	                if (inBuff != null)
	                    inBuff.close();
	                if (outBuff != null)
	                    outBuff.close();
	            } catch (Exception e2) {
	                e2.printStackTrace();
	            }
	        }
	        return isSuccess;
	    }
	   public static File getDir(String dir) {
	        File fileDir = new File(dir);
	        if (!fileDir.exists()) {
	            fileDir.mkdirs();
	        }
	        return fileDir;
	    }
}
