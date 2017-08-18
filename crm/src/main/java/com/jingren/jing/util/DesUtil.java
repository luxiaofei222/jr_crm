package com.jingren.jing.util;
import java.io.IOException;  
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import javax.crypto.Cipher;  
import javax.crypto.SecretKey;  
import javax.crypto.SecretKeyFactory;  
import javax.crypto.spec.DESKeySpec;  
import Decoder.BASE64Decoder;  
import Decoder.BASE64Encoder;
/**
 * DES加密-目前项目自用
* @ClassName: DesUtil 
* @Description: TODO
* @author 鲁晓飞
* @date 2016年11月17日 下午2:30:16 
*
 */
public class DesUtil {  
   
    public static void main(String[] args) throws Exception {  
   
    	System.out.println(9%8);
    }  
       
    /** 
     * Description 根据键值进行加密 
     * @param data  
     * @param key  加密键byte数组 
     * @return 
     * @throws Exception 
     */  
    public static String encrypt(String data, String key) throws Exception {  
        byte[] bt = encryptbyte(data.getBytes("UTF-8"), key.getBytes());  
        String strs = new BASE64Encoder().encode(bt);  
        return strs;  
    }  
    /**
     * 生成订单号
    * @Title: DesUtil.java 
    * @Package com.jingren.jing.util 
    * @Description: TODO
    * @author 鲁晓飞 MR.Lu   
    * @date 2016年11月23日 上午11:21:56 
    * @version 网校+CRM系统 V1.0
     */
    public static String get_order_number(){
    	SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    	String num=format.format(new Date());
    	int ran = (int) ((Math.random() * 9 + 1) * 100000);
    	System.out.println(num+ran);
		return "JR"+num+ran;
    }
    
    public static String get_baoming_number(){
    	SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    	String num=format.format(new Date());
    	int ran = (int) ((Math.random() * 9 + 1) * 100000);
    	System.out.println(num+ran);
		return "BM"+num+ran;
    }
    /**
     * 生成UUID
    * @Title: DesUtil.java
    * @Package com.chengxin.cheng.util
    * @Description: TODO
    * @author 鲁晓飞  
    * @date 2016年5月27日 上午11:18:27
    * @version 橙信橙客.LTD V1.0
     */
    public static String getUUID() {  
        UUID uuid = UUID.randomUUID();  
        String str = uuid.toString();  
        // 去掉"-"符号  
        String temp = str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);  
        return temp;  
    } 
    /** 
     * Description 根据键值进行解密 
     * @param data 
     * @param key  加密键byte数组 
     * @return 
     * @throws IOException 
     * @throws Exception 
     */  
    public static String decrypt(String data, String key) throws IOException,  
            Exception {  
        if (data == null)  
            return null;  
        BASE64Decoder decoder = new BASE64Decoder();  
        byte[] buf = decoder.decodeBuffer(data);  
        int pad = buf.length%8;
        if(pad==0){
        	 byte[] bt = decryptbyte(buf,key.getBytes("UTF-8"));  
             return new String(bt,"UTF-8"); 
        }else{
        	return null;
        }
    }  
   
    /** 
     * Description 根据键值进行加密 
     * @param data 
     * @param key  加密键byte数组 
     * @return 
     * @throws Exception 
     */  
    private static  byte[] encryptbyte(byte[] data, byte[] key) throws Exception {  
        // 生成一个可信任的随机数源  
        SecureRandom sr = new SecureRandom();  
   
        // 从原始密钥数据创建DESKeySpec对象  
        DESKeySpec dks = new DESKeySpec(key);  
   
        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象  
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");  
        SecretKey securekey = keyFactory.generateSecret(dks);  
   
        // Cipher对象实际完成加密操作  
        Cipher cipher = Cipher.getInstance("DES");  
   
        // 用密钥初始化Cipher对象  
        cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);  
   
        return cipher.doFinal(data);  
    }  
       
       
    /** 
     * Description 根据键值进行解密 
     * @param data 
     * @param key  加密键byte数组 
     * @return 
     * @throws Exception 
     */  
    private static byte[] decryptbyte(byte[] data, byte[] key) throws Exception {  
        // 生成一个可信任的随机数源  
        SecureRandom sr = new SecureRandom();  
   
        // 从原始密钥数据创建DESKeySpec对象  
        DESKeySpec dks = new DESKeySpec(key);  
   
        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象  
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");  
        SecretKey securekey = keyFactory.generateSecret(dks);  
   
        // Cipher对象实际完成解密操作  
        Cipher cipher = Cipher.getInstance("DES");  
   
        // 用密钥初始化Cipher对象  
        cipher.init(Cipher.DECRYPT_MODE, securekey, sr);  
   
        return cipher.doFinal(data);  
    }  
}  
