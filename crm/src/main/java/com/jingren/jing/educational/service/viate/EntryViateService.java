package com.jingren.jing.educational.service.viate;

import java.util.List;

import com.jingren.jing.educational.bean.viate.EntryViate;

public interface EntryViateService {
	/**
	* @Title: EntryViateMapper.java 
	* @Package com.jingren.jing.educational.dao.viate 
	* @Description: TODO 保存报考学员个人简历
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月17日 上午9:34:22 
	* @version 网校+CRM系统 V1.0
	 */
	boolean saveEntryViate(EntryViate entryViate);
	/**
	* @Title: EntryViateMapper.java 
	* @Package com.jingren.jing.educational.dao.viate 
	* @Description: TODO 删除个人简历
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月17日 上午9:34:56 
	* @version 网校+CRM系统 V1.0
	 */
	boolean deleteEntryViate(Integer entry_info_id);
	/**
	* @Title: EntryViateMapper.java 
	* @Package com.jingren.jing.educational.dao.viate 
	* @Description: TODO 获取个人简历列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月17日 上午9:35:20 
	* @version 网校+CRM系统 V1.0
	 */
	List<EntryViate> getEntryViateList(Integer entry_info_id);
	/**
	* @Title: EntryViateMapper.java 
	* @Package com.jingren.jing.educational.dao.viate 
	* @Description: TODO 修改个人简历
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月13日 上午9:05:37 
	* @version 网校+CRM系统 V1.0
	 */
	boolean updateEntryViate(EntryViate entryViate);
}
