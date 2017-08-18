package com.jingren.jing.common.mail.dao;

import com.jingren.jing.common.mail.bean.MailBean;

public interface MailMapper {

	/**
	* @Title: MailService.java 
	* @Package com.jingren.jing.common.mail.service 
	* @Description: TODO获取模板
	* @author 鲁晓飞 MR.Lu    
	* @date 2017年2月22日 下午5:52:49 
	* @version 网校+CRM系统 V1.0
	 */
	MailBean getMailBean(Integer type);
}
