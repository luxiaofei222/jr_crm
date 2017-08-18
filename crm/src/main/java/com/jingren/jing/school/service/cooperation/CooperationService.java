package com.jingren.jing.school.service.cooperation;

import java.util.List;
import java.util.Map;

import com.jingren.jing.school.bean.cooperation.Cooperation;

public interface CooperationService {
	/**
	 * 添加合作伙伴
	 * 
	 * @Title: CopperationMapper.java
	 * @Package com.jingren.jing.school.dao.cooperation
	 * @Description: TODO
	 * @author 鲁晓飞 MR.Lu
	 * @date 2016年11月4日 上午8:49:14
	 * @version 网校+CRM系统 V1.0
	 */
	boolean saveCooperation(Cooperation cooperation);

	/**
	 * 修改合作伙伴信息
	 * 
	 * @Title: CooperationMapper.java
	 * @Package com.jingren.jing.school.dao.cooperation
	 * @Description: TODO
	 * @author 鲁晓飞 MR.Lu
	 * @date 2016年11月4日 上午8:50:14
	 * @version 网校+CRM系统 V1.0
	 */
	boolean updateCooperation(Cooperation cooperation);

	/**
	 * 删除合作伙伴信息
	* @Title: CooperationMapper.java 
	* @Package com.jingren.jing.school.dao.cooperation 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月4日 上午8:50:55 
	* @version 网校+CRM系统 V1.0
	 */
	boolean deleteCooperation(Integer cooperation_id);
	/**
	 * 获取合作伙伴信息
	* @Title: CooperationMapper.java 
	* @Package com.jingren.jing.school.dao.cooperation 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月4日 上午8:51:35 
	* @version 网校+CRM系统 V1.0
	 */
	Cooperation getCooperation(Map<String , Object> map);
	/**
	 * 获取合作伙伴列表
	* @Title: CooperationMapper.java 
	* @Package com.jingren.jing.school.dao.cooperation 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月4日 上午8:52:12 
	* @version 网校+CRM系统 V1.0
	 */
	List<Cooperation> getCooperationList(Map<String, Object> map);
	/**
	 * 获取合作伙伴数量
	* @Title: CooperationMapper.java 
	* @Package com.jingren.jing.school.dao.cooperation 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月4日 上午8:52:48 
	* @version 网校+CRM系统 V1.0
	 */
	int getCooperationNumber(Map<String, Object> map);
}
