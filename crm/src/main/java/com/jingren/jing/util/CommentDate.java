package com.jingren.jing.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CommentDate {
	public static String Commentdat(Date time) throws ParseException {
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
		long d1 = new Date().getTime();
		long a = time.getTime();
		long d = a / 1000;
		long c = d1 / 1000;
		long f = c - d;
		String g;
		if (f < 60) {
			// 一分钟内
			g = "刚刚";
		} else if (f < (60 * 60)) {
			// 一小时内
			g = f / (60) + "分钟前";
		} else if (f < (60 * 60 * 24)) {
			// 一天内
			g = f / (60 * 60) + "小时前";
		} else {
			// 一个月后
			g = bartDateFormat.format(time);
		}
		return g;
	}

	// 整数转成时：分：秒
	public static String secToTime(int time) {
		String timeStr = null;
		int hour = 0;
		int minute = 0;
		int second = 0;
		if (time <= 0)
			return "00:00:00";
		else {
			minute = time / 60;
			if (minute < 60) {
				second = time % 60;
				timeStr = "00:" + unitFormat(minute) + ":" + unitFormat(second);
			} else {
				hour = minute / 60;
				if (hour > 99)
					return "99:59:59";
				minute = minute % 60;
				second = time - hour * 3600 - minute * 60;
				timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);
			}
		}
		return timeStr;
	}

	public static String unitFormat(int i) {
		String retStr = null;
		if (i >= 0 && i < 10)
			retStr = "0" + Integer.toString(i);
		else
			retStr = "" + i;
		return retStr;
	}

	/**
	 * @Title: CommentDate.java
	 * @Package com.jingren.jing.util
	 * @Description: TODO 字符串转时间类型
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年2月21日 下午1:20:18
	 * @version 网校+CRM系统 V1.0
	 */
	public static Date get_String_date(String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date2 = sdf.parse(date);
		// long currentTime = date2.getTime();
		// currentTime +=24*60*60*1000-1000;//加23小时59分59秒
		// Date date3=new Date(currentTime);
		return date2;
	}

	/**
	* @Title: CommentDate.java 
	* @Package com.jingren.jing.util 
	* @Description: TODO 字符串转化为当天23点
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年8月2日 下午2:39:44 
	* @version 网校+CRM系统 V1.0
	 */
	public static Date get_String_date_lingchen(String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date2 = sdf.parse(date);
		 long currentTime = date2.getTime();
		 currentTime +=24*60*60*1000-1000;//加23小时59分59秒
		 Date date3=new Date(currentTime);
		return date3;
	}
	
	/**
	* @Title: CommentDate.java 
	* @Package com.jingren.jing.util 
	* @Description: TODO 带时间的日期转换
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年5月31日 下午4:25:24 
	* @version 网校+CRM系统 V1.0
	 */
	public static Date get_leave_date(String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date2 = sdf.parse(date);
		// long currentTime = date2.getTime();
		// currentTime +=24*60*60*1000-1000;//加23小时59分59秒
		// Date date3=new Date(currentTime);
		return date2;
	}
	/**
	* @Title: CommentDate.java 
	* @Package com.jingren.jing.util 
	* @Description: TODO 事件类型转化为字符串
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月22日 上午10:48:14 
	* @version 网校+CRM系统 V1.0
	 */
	public static String get_date_string(Date date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date2 = sdf.format(date);
		// long currentTime = date2.getTime();
		// currentTime +=24*60*60*1000-1000;//加23小时59分59秒
		// Date date3=new Date(currentTime);
		return date2;
	}
	/**
	* @Title: CommentDate.java 
	* @Package com.jingren.jing.util 
	* @Description: TODO 说说专用
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年3月30日 上午10:03:28 
	* @version 网校+CRM系统 V1.0
	 */
	public static String get_employee_string(Date date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("MM.dd");
		String date2 = sdf.format(date);
		// long currentTime = date2.getTime();
		// currentTime +=24*60*60*1000-1000;//加23小时59分59秒
		// Date date3=new Date(currentTime);
		return date2;
	}
	
	/**
	* @Title: CommentDate.java 
	* @Package com.jingren.jing.util 
	* @Description: TODO 获取今天周几
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年3月30日 上午10:05:44 
	* @version 网校+CRM系统 V1.0
	 */
	public static String getWeekOfDate(Date date) {      
	    String[] weekOfDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};        
	    Calendar calendar = Calendar.getInstance();      
	    if(date != null){        
	         calendar.setTime(date);      
	    }        
	    int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;      
	    if (w < 0){        
	        w = 0;      
	    }      
	    return weekOfDays[w];    
	}
	/**
	 * @Title: CommentDate.java
	 * @Package com.jingren.jing.util
	 * @Description: TODO 获取当天的0点
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年2月21日 下午1:49:43
	 * @version 网校+CRM系统 V1.0
	 */
	public static Date getStartTime() {
		Calendar todayStart = Calendar.getInstance();
		todayStart.set(Calendar.HOUR_OF_DAY, 0);
		todayStart.set(Calendar.MINUTE, 0);
		todayStart.set(Calendar.SECOND, 0);
		todayStart.set(Calendar.MILLISECOND, 0);
		return todayStart.getTime();
	}

	/**
	 * @Title: CommentDate.java
	 * @Package com.jingren.jing.util
	 * @Description: TODO 获取当天的23点
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年2月21日 下午1:50:08
	 * @version 网校+CRM系统 V1.0
	 */
	public static Date getnowEndTime() {
		Calendar todayEnd = Calendar.getInstance();
		todayEnd.set(Calendar.HOUR_OF_DAY, 23);
		todayEnd.set(Calendar.MINUTE, 59);
		todayEnd.set(Calendar.SECOND, 59);
		todayEnd.set(Calendar.MILLISECOND, 999);
		return todayEnd.getTime();
	}
	/**
	* @Title: CommentDate.java 
	* @Package com.jingren.jing.util 
	* @Description: TODO 获取本周一的日期
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年3月9日 下午5:45:25 
	* @version 网校+CRM系统 V1.0
	 */
	public static String getzhouyi(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(calendar.getTime());	
	}
	/**
	* @Title: CommentDate.java 
	* @Package com.jingren.jing.util 
	* @Description: TODO 获取本周五的日期
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年3月9日 下午5:45:52 
	* @version 网校+CRM系统 V1.0
	 */
	public static String getzhouwu(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(calendar.getTime());	
	}
	
	 /**  
     * 计算两个日期之间相差的天数  
     * @param smdate 较小的时间 
     * @param bdate  较大的时间 
     * @return 相差天数 
     * @throws ParseException  
     */    
    public static int daysBetween(Date smdate,Date bdate) throws ParseException    
    {    
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        smdate=sdf.parse(sdf.format(smdate));  
        bdate=sdf.parse(sdf.format(bdate));  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(smdate);    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(bdate);    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24);  
            
       return Integer.parseInt(String.valueOf(between_days));           
    }    
      
/**
* @Title: CommentDate.java 
* @Package com.jingren.jing.util 
* @Description: TODO 计算日期的相差天数
* @author 鲁晓飞 MR.Lu   
* @date 2017年6月2日 下午2:06:20 
* @version 网校+CRM系统 V1.0
 */
    public static int daysBetween(String smdate,String bdate) throws ParseException{  
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(sdf.parse(smdate));    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(sdf.parse(bdate));    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24);  
            
       return Integer.parseInt(String.valueOf(between_days));     
    }  
    
	/**
	* @Title: LogNoteContrller.java 
	* @Package com.jingren.jing.personal.controller.notelog 
	* @Description: TODO 获取年月列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月6日 下午6:27:49 
	* @version 网校+CRM系统 V1.0
	 */
	public static List<String> get_nianyuelist() throws ParseException {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -5);
		String before_six=c.get(Calendar.YEAR) + "-" + c.get(Calendar.MONTH);//六个月前
		ArrayList<String> result = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");// 格式化为年月
		Calendar min = Calendar.getInstance();
		Calendar max = Calendar.getInstance();
		min.setTime(sdf.parse(before_six));
		min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);
		max.setTime(sdf.parse(sdf.format(new Date())));
		max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);
		Calendar curr = min;
		while (curr.before(max)) {
			result.add(sdf.format(curr.getTime()));
			curr.add(Calendar.MONTH, 1);
		}
		//System.out.println(result);
		return result;
	}
}
