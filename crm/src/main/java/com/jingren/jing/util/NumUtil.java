package com.jingren.jing.util;


import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * 创建人：fantasy <br>
 * 创建时间：2013-8-27 <br>
 * 功能描述： 处理数字的Util、计算百分比<br>
 */
public class NumUtil {
    /**
     * 转换为BigDecimal
     *  
     * @param o
     * @return BigDecimal
     * @author fantasy 
     * @date 2013-8-27
     */
    public static BigDecimal toBig(Object o) {
        if (o == null || o.toString().equals("") || o.toString().equals("NaN")) {
            return new BigDecimal(0);
        }
        return new BigDecimal(o.toString());
    }
    
    /**
     * 计算百分比
     *  
     * @param divisor
     * @param dividend
     * @return String
     * @author fantasy 
     * @date 2013-8-27
     */
    public static String getPercent(Object divisor, Object dividend){
        if(divisor == null || dividend == null){
            return "";
        }
        NumberFormat percent = NumberFormat.getPercentInstance();   
        //建立百分比格式化引用   
        percent.setMaximumFractionDigits(2);
        BigDecimal a = toBig(divisor);
        BigDecimal b = toBig(dividend);
        if(a.equals(toBig(0)) || b.equals(toBig(0)) || a.equals(toBig(0.0)) || b.equals(toBig(0.0))){
       	 return "0.00%";
        }
        BigDecimal c = a.divide(b, 4, BigDecimal.ROUND_DOWN);
        return percent.format(c);
    }
    
    /**
     * 计算比例
     *  
     * @param divisor
     * @param dividend
     * @return String
     * @author fantasy 
     * @date 2013-10-9
     */
    public static String divideNumber(Object divisor, Object dividend){
    	if(divisor == null || dividend == null){
            return "";
        }
    	 BigDecimal a = toBig(divisor);
         BigDecimal b = toBig(dividend);
         if(a.equals(toBig(0)) || b.equals(toBig(0))){
        	 return "0";
         }
         BigDecimal c = a.divide(b, 2, BigDecimal.ROUND_DOWN);
         return c.toString();
    }
    
    /**
     * 去两个数的平均值，四舍五入
     *  
     * @param divisor
     * @param dividend
     * @return int
     * @author fantasy 
     * @date 2013-11-6
     */
    public static int averageNumber(Object divisor, Object dividend){
    	if(divisor == null || dividend == null){
            return 0;
        }
    	BigDecimal a = toBig(divisor);
        BigDecimal b = toBig(dividend);
        if(a.equals(toBig(0)) || b.equals(toBig(0))){
       	 	return 0;
        }
        BigDecimal c = a.divide(b, 0, BigDecimal.ROUND_HALF_UP);
        return c.intValue();
    }
    /**
    * @Title: NumUtil.java 
    * @Package com.jingren.jing.util 
    * @Description: TODO 设置字节单位
    * @author 鲁晓飞 MR.Lu   
    * @date 2016年12月8日 上午10:15:33 
    * @version 网校+CRM系统 V1.0
     */
	public static String get_file_lenth(long filesize){
		String size = ""; 
		 DecimalFormat df = new DecimalFormat("0.00"); 
         if (filesize < 1024) {
             size = df.format((double) filesize) + "BT";
         } else if (filesize < 1048576) {
             size = df.format((double) filesize / 1024) + "KB";
         } else if (filesize < 1073741824) {
             size = df.format((double) filesize / 1048576) + "MB";
         } else {
             size = df.format((double) filesize / 1073741824) +"GB";
         }
		return size;
	}
	/**
	* @Title: NumUtil.java 
	* @Package com.jingren.jing.util 
	* @Description: TODO 阿拉伯数字转换
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月11日 下午4:18:18 
	* @version 网校+CRM系统 V1.0
	 */
	public static String  get_str_number(String str) {
		 String[] s1 = { "", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
	        String[] s2 = { "十", "百", "千", "万", "十", "百", "千", "亿", "十", "百", "千" };
	        String result = "";
	        int n = str.length();
	        for (int i = 0; i < n; i++) {

	            int num = str.charAt(i) - '0';
	            if (i != n - 1 && num != 0) {
	                result += s1[num] + s2[n - 2 - i];
	            } else {
	                result += s1[num];
	            }
	        }
	        return result;
 }
}