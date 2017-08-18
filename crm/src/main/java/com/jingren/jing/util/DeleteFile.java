package com.jingren.jing.util;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public class DeleteFile {
	/**
	 * 删除单个文件
	 * 
	 * @param fileName
	 *            被删除文件的文件名
	 * @return 单个文件删除成功返回true,否则返回false
	 */
	public static boolean deleteFile1(String fileName,
			HttpServletRequest request) {
		String filePath=request.getServletContext().getRealPath("/")+fileName;
		File file = new File(filePath);
		if (file.isFile() && file.exists()) {
			file.delete();
			System.out.println("删除单个文件" + filePath + "成功！");
			return true;
		} else {
			System.out.println("删除单个文件" + filePath + "失败！");
			
			return false;
		}
	}
	/**
	 * 获取文件大小
	* @Title: DeleteFile.java 
	* @Package com.jingren.jing.util 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月28日 下午2:43:04 
	* @version 网校+CRM系统 V1.0
	 */
	public static String get_file_length(String fileName,
			HttpServletRequest request){
		String filePath=request.getServletContext().getRealPath("/")+fileName;
		File file = new File(filePath);
		long fileS=file.length();
		String size = ""; 
		 DecimalFormat df = new DecimalFormat("0.00"); 
         if (fileS < 1024) {
             size = df.format((double) fileS) + "BT";
         } else if (fileS < 1048576) {
             size = df.format((double) fileS / 1024) + "KB";
         } else if (fileS < 1073741824) {
             size = df.format((double) fileS / 1048576) + "MB";
         } else {
             size = df.format((double) fileS / 1073741824) +"GB";
         }
		return size;
	}
	/**
	 * 查找文件是否存在
	* @Title: DeleteFile.java
	* @Package com.chengxin.cheng.util
	* @Description: TODO
	* @author 鲁晓飞  
	* @date 2016年4月14日 下午2:05:34
	* @version 橙信橙客.LTD V1.0
	 */
	public static boolean findfile(String fileName,HttpServletRequest request){
		String filePath=request.getServletContext().getRealPath("/")+fileName;
		File file=new File(filePath);    
		if(!file.exists())    
		{    
			return false;   //不存在返回false
		}else{
			return true;//存在返回true
		} 
	}
	/**
	 * 去重
	 * @param list
	 * @return
	 */
	public static List<?> filterRepeat4List(List<?> list){
		List<Object> temp = new ArrayList<Object>();
		Map<Object,Boolean> tempMap = new HashMap<Object,Boolean>();
		for(Object obj : list){
			if(tempMap.get(obj) == null){
				tempMap.put(obj, true);
				temp.add(obj);
			}
		}
		return temp;
	}
	
	/**
	 * 判断手机号
	 * @param mobiles
	 * @return
	 */
	public static boolean isMobileNO(String mobiles){  
		Pattern p = Pattern.compile("^((13[0-9]{1})|(15[0-9]{1})|170|14[0-9]|(18[0-9]{1}))\\d{8}$");  
		Matcher m = p.matcher(mobiles);  
		return m.matches();  
	}
	/** 
     * 电话号码验证 
     *  
     * @param  str 
     * @return 验证通过返回true 
     */  
    public static boolean isPhone(String str) {   
        Pattern p1 = null,p2 = null;  
        Matcher m = null;  
        boolean b = false;    
        p1 = Pattern.compile("^[0][1-9]{2,3}-[0-9]{5,10}$");  // 验证带区号的  
        p2 = Pattern.compile("^[1-9]{1}[0-9]{5,8}$");         // 验证没有区号的  
        if(str.length() >9)  
        {   m = p1.matcher(str);  
            b = m.matches();    
        }else{  
            m = p2.matcher(str);  
            b = m.matches();   
        }    
        return b;  
    }  
}