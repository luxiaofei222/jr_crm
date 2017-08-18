package com.jingren.jing.common.mail.util;

import java.io.UnsupportedEncodingException;

import javax.mail.internet.MimeUtility;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.log4j.Logger;
import com.jingren.jing.common.mail.bean.MailBean;
/**
 * 邮箱发送工具类
* @Title: MailUtil.java 
* @Package com.jingren.jing.common.mail.util 
* @Description: TODO
* @author 鲁晓飞 MR.Lu   
* @date 2016年11月1日 下午1:47:44 
* @version 网校+CRM系统 V1.0
 */
public class MailUtil {

	private static  Logger logger = Logger.getLogger(MailUtil.class);
	
	public static boolean send_mail(MailBean mailBean){
		// 发送email  
        HtmlEmail email = new HtmlEmail();  
        try {  
            // 这里是SMTP发送服务器的名字：163的如下："smtp.163.com"  
            email.setHostName(mailBean.getHost());  
            // 收件人的邮箱  
            email.addTo(mailBean.getReceiver());  
            // 发送人的邮箱  
            try {
				email.setFrom(mailBean.getSender(), MimeUtility.encodeText(mailBean.getName(), "UTF-8", "B"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}  
            // 如果需要认证信息的话，设置认证：用户名-密码。分别为发件人在邮件服务器上的注册名称和密码  
            email.setAuthentication(mailBean.getUsername(), mailBean.getPassword());  
            // 要发送的邮件主题  
            email.setSubject(mailBean.getSubject());  
            // 要发送的信息，由于使用了HtmlEmail，可以在邮件内容中使用HTML标签  
            email.setMsg(mailBean.getMessage());  
            // 字符编码集的设置  
            email.setCharset(MailBean.ENCODEING);  
            // 发送  
            email.send();
            
            if (logger.isDebugEnabled()) {  
                logger.debug(mailBean.getSender() + " 发送邮件到 " + mailBean.getReceiver());  
            }  
            return true;  
        } catch (EmailException e) {  
            e.printStackTrace();  
            logger.info(mailBean.getSender() + " 发送邮件到 " + mailBean.getReceiver()  
                    + " 失败");  
            return false;  
        }  
	}
}
