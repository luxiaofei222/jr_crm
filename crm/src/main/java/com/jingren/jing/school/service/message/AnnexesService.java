package com.jingren.jing.school.service.message;

import java.util.List;
import java.util.Map;

import com.jingren.jing.school.bean.message.Annexes;

public interface AnnexesService {
	/**
	 * 保存附件
	* @Title: AnnexesMapper.java 
	* @Package com.jingren.jing.school.dao.message 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月28日 上午11:25:55 
	* @version 网校+CRM系统 V1.0
	 */
	boolean saveAnnexes(Annexes annexes);
	/**
	 * 删除附件
	* @Title: AnnexesMapper.java 
	* @Package com.jingren.jing.school.dao.message 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月28日 上午11:26:35 
	* @version 网校+CRM系统 V1.0
	 */
	boolean deleteAnnexes(Integer message_id);
	/**
	 * 获取附件列表
	* @Title: AnnexesMapper.java 
	* @Package com.jingren.jing.school.dao.message 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月28日 上午11:27:36 
	* @version 网校+CRM系统 V1.0
	 */
	List<Annexes> getAnnexesList(Map<String, Object> map);
	/**
	 * 获取附件数量
	* @Title: AnnexesMapper.java 
	* @Package com.jingren.jing.school.dao.message 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月28日 上午11:28:00 
	* @version 网校+CRM系统 V1.0
	 */
	Integer getAnnexesNumber(Map<String, Object> map);
}
