package com.jingren.jing.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.jingren.jing.common.files.FilesEnty;

/**
 * @Title: FilesListUtil.java
 * @Package com.jingren.jing.util
 * @Description: TODO 获取文件夹下文件工具类
 * @author 鲁晓飞 MR.Lu
 * @date 2016年12月9日 下午3:28:25
 * @version 网校+CRM系统 V1.0
 */
public class FilesListUtil {
	
	private static  final transient Logger log = Logger.getLogger(FilesListUtil .class);
	public static void main(String[] args) {
		String fileName="二级 第三章 心理测验技能 第二三节 特殊心理评估的实施 测验结果的解释.mp4";
		String prefix = fileName.substring(fileName.lastIndexOf(" "));// 获取后缀名
		String prefix1 = prefix.substring(prefix.lastIndexOf("."));
		int num = prefix1.length();
		String othername = prefix.substring(0, prefix.length() - num);
		System.out.println(othername);
		System.out.println(prefix1);
	}

	/**
	 * @Title: FilesListUtil.java
	 * @Package com.jingren.jing.util
	 * @Description: TODO 获取文件列表
	 * @author 鲁晓飞 MR.Lu
	 * @date 2016年12月9日 下午3:29:08
	 * @version 网校+CRM系统 V1.0
	 */
	public static List<FilesEnty> get_file_list(String filePath) {
		File file = new File(filePath);
		//File[]
		String[] tempList = file.list();
		List<FilesEnty> filesEnties = new ArrayList<>();
		log.info("获取的文件数量："+tempList.length);
		System.out.println(tempList.length);
		for (int i = 0; i < tempList.length; i++) {
			if(StringUtils.isNotBlank(tempList[i])&&!tempList[i].equals("video")){
				FilesEnty filesEnty = new FilesEnty();
				filesEnty.setFiles_name(tempList[i]);
				filesEnties.add(filesEnty);
			}
//			if (tempList[i].isFile()) {
//				
//				String fileName = tempList[i].getName();
//				log.info("未转码的文件名称"+fileName);
//				filesEnty.setFiles_name(fileName);
//				filesEnties.add(filesEnty);
//				String prefix;
//				if(fileName.contains(" ")){
//					 prefix = fileName.substring(fileName.lastIndexOf(" "));// 获取后缀名
//				}else{
//					 prefix = fileName;
//				}
//				String prefix1 = prefix.substring(prefix.lastIndexOf("."));
//				int num = prefix1.length();
//				String othername = prefix.substring(0, prefix.length() - num);
//				filesEnty.setFeiles_houzhui(prefix);
				// System.out.println("文 件："+tempList[i].getName());
//			}
		}
		return filesEnties;
	}

	/**
	 * @Title: FilesListUtil.java
	 * @Package com.jingren.jing.util
	 * @Description: TODO 移动文件到指定文件夹
	 * @author 鲁晓飞 MR.Lu
	 * @date 2016年12月9日 下午3:49:35
	 * @version 网校+CRM系统 V1.0
	 */
	public static String yidongfile(HttpServletRequest request, String filePath, String filename, String move_file) {
		//String Path = request.getServletContext().getRealPath("/");
		//File file=new File("/home");
		String Path=request.getServletContext().getRealPath("/");
		File afile = new File(Path + filePath + "/" + filename);
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String format1 = df.format(new Date());
		Random t = new Random();
		for (int i = 0; i < 3; i++) {
			format1 += t.nextInt(10);
		}
		String prefix = afile.getName().substring(afile.getName().lastIndexOf("."));
		if (afile.renameTo(new File(Path + move_file + "/" + format1 + prefix))) {
			System.out.println("文件移动成功");
		} else {
			System.out.println("File is failed to move!");
		}
		return "/" + move_file + "/" + format1 + prefix;
	}
	/**
	* @Title: FilesListUtil.java 
	* @Package com.jingren.jing.util 
	* @Description: TODO 获取文件名
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月9日 下午4:15:24 
	* @version 网校+CRM系统 V1.0
	 */
	public static String huoqufilename(String fileName){
		String prefix;
		if(fileName.contains(" ")){
			 prefix = fileName.substring(fileName.lastIndexOf(" "));
		}else{
			 prefix = fileName;
		}
		String prefix1 = prefix.substring(prefix.lastIndexOf("."));// 获取后缀名
		int num = prefix1.length();
		String othername = prefix.substring(0, prefix.length() - num);
		return othername;
	}
}
