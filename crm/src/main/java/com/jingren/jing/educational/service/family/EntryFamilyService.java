package com.jingren.jing.educational.service.family;

import java.util.List;

import com.jingren.jing.educational.bean.family.EntryFamily;

public interface EntryFamilyService {
	/**
	* @Title: EntryFamilyMapper.java 
	* @Package com.jingren.jing.educational.dao.family 
	* @Description: TODO 保存报考学院家庭成员
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月17日 上午9:30:11 
	* @version 网校+CRM系统 V1.0
	 */
	boolean saveEntryFamily(EntryFamily entryFamily);
	/**
	* @Title: EntryFamilyMapper.java 
	* @Package com.jingren.jing.educational.dao.family 
	* @Description: TODO 删除报考学员家庭成员
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月17日 上午9:31:03 
	* @version 网校+CRM系统 V1.0
	 */
	boolean deleteEntryFamily(Integer entry_infoid);
	/**
	* @Title: EntryFamilyMapper.java 
	* @Package com.jingren.jing.educational.dao.family 
	* @Description: TODO 获取家庭成员信息列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月17日 上午9:32:00 
	* @version 网校+CRM系统 V1.0
	 */
	List<EntryFamily> getEntryFamilyList(Integer entry_infoid);
	/**
	* @Title: EntryFamilyMapper.java 
	* @Package com.jingren.jing.educational.dao.family 
	* @Description: TODO 修改家庭成员
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月13日 上午9:06:42 
	* @version 网校+CRM系统 V1.0
	 */
	boolean updateEntryFamily(EntryFamily entryFamily);
}
