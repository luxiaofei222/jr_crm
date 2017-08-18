package com.jingren.jing.school.dao.message;

import java.util.List;
import java.util.Map;

import com.jingren.jing.school.bean.message.PrivateMessage;


public interface PrivateMessageMapper {

	/**
	* @Title: PrivateMessageMapper.java 
	* @Package com.jingren.jing.school.dao.message 
	* @Description: TODO 发送私信
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年5月25日 下午1:57:13 
	* @version 网校+CRM系统 V1.0
	 */
	boolean savePrivateMessage(PrivateMessage privateMessage);
	/**
	* @Title: PrivateMessageMapper.java 
	* @Package com.jingren.jing.school.dao.message 
	* @Description: TODO 删除私信
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年5月25日 下午1:58:25 
	* @version 网校+CRM系统 V1.0
	 */
	boolean deletePrivateMessage(Integer message_id);
	/**
	* @Title: PrivateMessageMapper.java 
	* @Package com.jingren.jing.school.dao.message 
	* @Description: TODO 修改私信
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年5月25日 下午2:15:36 
	* @version 网校+CRM系统 V1.0
	 */
	boolean updatePrivateMessage(PrivateMessage privateMessage);
	/**
	* @Title: PrivateMessageMapper.java 
	* @Package com.jingren.jing.school.dao.message 
	* @Description: TODO 获取私信信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年5月25日 下午1:58:57 
	* @version 网校+CRM系统 V1.0
	 */
	PrivateMessage getPrivateMessage(Map<String, Object> map);
	/**
	* @Title: PrivateMessageMapper.java 
	* @Package com.jingren.jing.school.dao.message 
	* @Description: TODO 获取私信列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年5月25日 下午1:59:31 
	* @version 网校+CRM系统 V1.0
	 */
	List<PrivateMessage> getPrivateMessageList(Map<String, Object> map);
	/**
	* @Title: PrivateMessageMapper.java 
	* @Package com.jingren.jing.school.dao.message 
	* @Description: TODO 获取私信数量
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年5月25日 下午2:00:09 
	* @version 网校+CRM系统 V1.0
	 */
	Integer getPrivateMessageNumber(Map<String, Object> map);
	/**
	* @Title: PrivateMessageMapper.java 
	* @Package com.jingren.jing.school.dao.message 
	* @Description: TODO 私信联系列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年5月25日 下午2:20:38 
	* @version 网校+CRM系统 V1.0
	 */
	List<PrivateMessage> getPrivateMessageLianxiList(Map<String, Object> map);
}
