package com.jingren.jing.recruit.dao.help;

import java.util.List;
import java.util.Map;

import com.jingren.jing.recruit.bean.help.HelpCenter;

public interface HelpCenterMapper {

	/**
	* @Title: HelpCenterMapper.java 
	* @Package com.jingren.jing.recruit.dao.help 
	* @Description: TODO 保存帮助信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月22日 下午2:44:55 
	* @version 网校+CRM系统 V1.0
	 */
	boolean saveHelpCenter(HelpCenter helpCenter);
	/**
	* @Title: HelpCenterMapper.java 
	* @Package com.jingren.jing.recruit.dao.help 
	* @Description: TODO 修改帮助内容
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月22日 下午2:45:22 
	* @version 网校+CRM系统 V1.0
	 */
	boolean updateHelpCenter(HelpCenter helpCenter);
	/**
	* @Title: HelpCenterMapper.java 
	* @Package com.jingren.jing.recruit.dao.help 
	* @Description: TODO 删除帮助信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月22日 下午2:46:09 
	* @version 网校+CRM系统 V1.0
	 */
	boolean deleteHelpCenter(Map<String, Object> map);
	/**
	* @Title: HelpCenterMapper.java 
	* @Package com.jingren.jing.recruit.dao.help 
	* @Description: TODO 获取帮助信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月22日 下午2:46:34 
	* @version 网校+CRM系统 V1.0
	 */
	HelpCenter getHelpCenter(Map<String, Object> map);
	/**
	* @Title: HelpCenterMapper.java 
	* @Package com.jingren.jing.recruit.dao.help 
	* @Description: TODO 获取帮助信息列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月22日 下午2:47:08 
	* @version 网校+CRM系统 V1.0
	 */
	List<HelpCenter> getHelpCenterList(Map<String, Object> map);
	/**
	* @Title: HelpCenterMapper.java 
	* @Package com.jingren.jing.recruit.dao.help 
	* @Description: TODO 获取帮助信息数量
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月22日 下午2:47:37 
	* @version 网校+CRM系统 V1.0
	 */
	Integer getHelpCenterNumber(Map<String, Object> map);
}
