package com.jingren.jing.oa.service.oa_edu;

import java.util.List;
import java.util.Map;

import com.jingren.jing.oa.bean.oa_edu.OAEdu;

public interface OAEduService {
	/**
	* @Title: OAEduMapper.java 
	* @Package com.jingren.jing.oa.dao.oa_edu 
	* @Description: TODO 保存教育经历
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年3月1日 下午3:22:43 
	* @version 网校+CRM系统 V1.0
	 */
	boolean saveOAEdu(OAEdu oaEdu);
	/**
	* @Title: OAEduMapper.java 
	* @Package com.jingren.jing.oa.dao.oa_edu 
	* @Description: TODO 修改教育经历
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年3月1日 下午3:23:15 
	* @version 网校+CRM系统 V1.0
	 */
	boolean updateOAEdu(OAEdu oaEdu);
	/**
	* @Title: OAEduMapper.java 
	* @Package com.jingren.jing.oa.dao.oa_edu 
	* @Description: TODO 获取教育经历列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年3月1日 下午3:25:16 
	* @version 网校+CRM系统 V1.0
	 */
	List<OAEdu> getOAEduList(Map<String, Object> map);
}
