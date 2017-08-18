package com.jingren.jing.school.dao.classtype;

import java.util.List;
import java.util.Map;

import com.jingren.jing.school.bean.classtype.ClassType;

public interface ClassTypeMapper {

	/**
	* @Title: ClassTypeMapper.java 
	* @Package com.jingren.jing.school.dao.classtype 
	* @Description: TODO 保存班型信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月12日 下午5:17:12 
	* @version 网校+CRM系统 V1.0
	 */
	boolean saveClassType(ClassType classType);
	/**
	* @Title: ClassTypeMapper.java 
	* @Package com.jingren.jing.school.dao.classtype 
	* @Description: TODO 修改班型信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月12日 下午5:17:40 
	* @version 网校+CRM系统 V1.0
	 */
	boolean updateClassType(ClassType classType);
	/**
	* @Title: ClassTypeMapper.java 
	* @Package com.jingren.jing.school.dao.classtype 
	* @Description: TODO 删除班型信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月12日 下午5:18:21 
	* @version 网校+CRM系统 V1.0
	 */
	boolean deleteClassType(Integer class_id);
	/**
	* @Title: ClassTypeMapper.java 
	* @Package com.jingren.jing.school.dao.classtype 
	* @Description: TODO 获取班型信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月12日 下午5:19:04 
	* @version 网校+CRM系统 V1.0
	 */
	ClassType getClassType(Map<String, Object> map);
	/**
	* @Title: ClassTypeMapper.java 
	* @Package com.jingren.jing.school.dao.classtype 
	* @Description: TODO 获取班型列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月12日 下午5:19:54 
	* @version 网校+CRM系统 V1.0
	 */
	List<ClassType> getClassTypeList(Map<String, Object> map);
	/**
	* @Title: ClassTypeMapper.java 
	* @Package com.jingren.jing.school.dao.classtype 
	* @Description: TODO 获取班型数量
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月12日 下午5:20:32 
	* @version 网校+CRM系统 V1.0
	 */
	Integer getClassTypeNumber(Map<String, Object> map);
}
