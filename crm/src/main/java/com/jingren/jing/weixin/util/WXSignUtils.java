package com.jingren.jing.weixin.util;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

/**
 * 微信支付签名
 * @author iYjrg_xiebin
 * @date 2015年11月25日下午4:47:07
 */
public class WXSignUtils {
	//http://mch.weixin.qq.com/wiki/doc/api/index.php?chapter=4_3
	//商户Key：改成公司申请的即可
	//32位密码设置地址：http://www.sexauth.com/  jdex1hvufnm1sdcb0e81t36k0d0f15nc
	private static String Key = "2d606e307d91773b09314fe63c56d06f";

	/**
	 * 微信支付签名算法sign
	 * @param characterEncoding
	 * @param parameters
	 * @return
	 */
//	@SuppressWarnings("rawtypes")
//	public static String createSign(SortedMap<Object,Object> parameters){
//		StringBuffer sb = new StringBuffer();
//		Set es = parameters.entrySet();//所有参与传参的参数按照accsii排序（升序）
//		Iterator it = es.iterator();
//		while(it.hasNext()) {
//			Map.Entry entry = (Map.Entry)it.next();
//			String k = (String)entry.getKey();
//			Object v = entry.getValue();
//			if(null != v && !"".equals(v) 
//					&& !"sign".equals(k) && !"key".equals(k)) {
//				sb.append(k + "=" + v + "&");
//			}
//		}
//		sb.append("key=" + Key);
//		System.out.println("字符串拼接后是："+sb.toString());
//		String sign = MD5Util.MD5Encode(sb.toString(), characterEncoding).toUpperCase();
//		return sign;
//	}
	
	
	public static String createSign(SortedMap<String,Object> parameters){
		StringBuffer sb = new StringBuffer();
		Set es = parameters.entrySet();//所有参与传参的参数按照accsii排序（升序）
		Iterator it = es.iterator();
		while(it.hasNext()) {
			Map.Entry entry = (Map.Entry)it.next();
			String k = (String)entry.getKey();
			Object v = entry.getValue();
			if(null != v && !"".equals(v)
					&& !"sign".equals(k) && !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=" + Key);
		System.out.println("字符串拼接后是："+sb.toString());
		String sign = MD5Util.MD5Encode(sb.toString(), "UTF-8").toUpperCase();
		return sign;
	}
 
	/** 
     * 回调后将结果返回给微信 
     * @param return_code 
     * @param return_msg 
     * @return 
     */  
    public static String backWeixin(String return_code,String return_msg){  
        try{  
            StringBuffer bf = new StringBuffer();  
            bf.append("<xml>");  
              
            bf.append("<return_code><![CDATA[");  
            bf.append(return_code);  
            bf.append("]]></return_code>");  
              
            bf.append("<return_msg><![CDATA[");  
            bf.append(return_msg);  
            bf.append("]]></return_msg>");  
              
            bf.append("</xml>");  
            return bf.toString();  
        }catch(Exception ex){  
            ex.printStackTrace();  
        }  
  
        return "";  
    }  

}
