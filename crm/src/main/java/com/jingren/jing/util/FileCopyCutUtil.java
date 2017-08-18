package com.jingren.jing.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopyCutUtil {
	
	public static void main(String[] args) throws IOException {
		copyFile("d://hehe.zip", "d://test//nihao.zip");
	}
	/**
	* @Title: FileCopyCutUtil.java 
	* @Package com.jingren.jing.util 
	* @Description: TODO 复制文件到另一个文件夹
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月19日 上午9:22:24 
	* @version 网校+CRM系统 V1.0
	 */
	public static void copyFile(String file1, String file2) throws IOException {
		FileInputStream fis = new FileInputStream(file1);
		FileOutputStream fos = new FileOutputStream(file2);
		int temp;
		while ((temp = fis.read()) != -1) {
			fos.write(temp);
		}
		fis.close();
		fos.close();
	}

	/**
	* @Title: FileCopyCutUtil.java 
	* @Package com.jingren.jing.util 
	* @Description: TODO 剪切文件
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月19日 上午9:22:12 
	* @version 网校+CRM系统 V1.0
	 */
	public static void cutRename(String file1, String file2) {
		try {
			copyFile(file1, file2);// 复制
			File f = new File(file1);
			f.delete();// 删除源文件，达到重命名的目的
			System.out.println("成功" + file2);
			System.out.println(file1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("失败" + file2);
			e.printStackTrace();
		}
	}
}
