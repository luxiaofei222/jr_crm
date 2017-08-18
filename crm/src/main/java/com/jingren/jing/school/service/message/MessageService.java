package com.jingren.jing.school.service.message;

import java.util.List;
import java.util.Map;

import com.jingren.jing.school.bean.message.JRMessage;

public interface MessageService {
	/**
	 * 保存信息
	* @Title: MessageMapper.java 
	* @Package com.jingren.jing.school.dao.message 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月28日 上午11:12:10 
	* @version 网校+CRM系统 V1.0
	 */
	boolean saveMessage(JRMessage jrMessage);
	/**
	 * 删除信息
	* @Title: MessageMapper.java 
	* @Package com.jingren.jing.school.dao.message 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月28日 上午11:13:03 
	* @version 网校+CRM系统 V1.0
	 */
	boolean deleteMessage(Integer message_id);
	/**
	 * 获取消息详情
	* @Title: MessageMapper.java 
	* @Package com.jingren.jing.school.dao.message 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月28日 上午11:13:30 
	* @version 网校+CRM系统 V1.0
	 */
	JRMessage getMessage(Map<String, Object> map);
	/**
	 * 获取消息列表
	* @Title: MessageMapper.java 
	* @Package com.jingren.jing.school.dao.message 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月28日 上午11:14:08 
	* @version 网校+CRM系统 V1.0
	 */
	List<JRMessage> getMessageList(Map<String, Object> map);
	/**
	 * 获取消息数量
	* @Title: MessageMapper.java 
	* @Package com.jingren.jing.school.dao.message 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月28日 上午11:14:41 
	* @version 网校+CRM系统 V1.0
	 */
	Integer getMessageNumber(Map<String, Object> map);
	/**
	 * 修改消息
	* @Title: MessageMapper.java 
	* @Package com.jingren.jing.school.dao.message 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月28日 下午1:15:41 
	* @version 网校+CRM系统 V1.0
	 */
	boolean updateJrmessage(JRMessage jrMessage);
}
