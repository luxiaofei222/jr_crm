package com.jingren.jing.util;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;


/**
 * 上传地址转换
 * */
public class UploadAddress {
	/**
	 * MultipartFile 类型
	* @Title: UploadAddress.java
	* @Package com.chengxin.cheng.util
	* @Description: TODO
	* @author 鲁晓飞  
	* @date 2016年2月29日 下午3:16:41
	* @version 橙信橙客.LTD V1.0
	 */
	public static String getUploadDate(MultipartFile pic1,
			HttpServletRequest request, String images) {// images 上传的文件夹
		String filePath=request.getServletContext().getRealPath("/");
		String ext1 = FilenameUtils.getExtension(pic1.getOriginalFilename());
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String format1 = df.format(new Date());
		Random t = new Random();
		for (int i = 0; i < 3; i++) {
			format1 += t.nextInt(10);
		}
		try {
			pic1.transferTo(new File(filePath + images + "/" + format1 + "."
					+ ext1));
			
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String addr = "/" + images + "/" + format1 + "." + ext1;
		return addr;
	}
/**
 * 文件命名
* @Title: UploadAddress.java 
* @Package com.chengxin.cheng.util 
* @Description: TODO 
* @author 鲁晓飞   
* @date 2016年7月6日 上午9:06:18 
* @version V1.0
 */
	public static String get_file_name(){
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String format1 = df.format(new Date());
		Random t = new Random();
		for (int i = 0; i < 3; i++) {
			format1 += t.nextInt(10);
		}
		return format1;
	}
	
	/**
	 * 生成二维码
	* @Title: UploadAddress.java 
	* @Package com.jingren.jing.util 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年10月24日 下午1:51:18 
	* @version V1.0
	 */
	public static String up_qrcode(HttpServletRequest request,String content,String path_file){
		try {
		 	String filePath=request.getServletContext().getRealPath("/");
		     MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
		     String cont="";
			Map hints = new HashMap();
//		     hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
			hints.put(EncodeHintType.MARGIN, 0);
		     BitMatrix bitMatrix = multiFormatWriter.encode(cont+content, BarcodeFormat.QR_CODE, 400, 400,hints);
		     String fomat=UploadAddress.get_file_name();
		     File file1 = new File(filePath+path_file,fomat+".png");
		     MatrixToImageWriter.writeToFile(bitMatrix, "png", file1);
		     String addr = "/" + path_file + "/" + fomat + ".png" ;
		     return addr;
		 } catch (Exception e) {
		     e.printStackTrace();
		 }
		return null;
	}
}
