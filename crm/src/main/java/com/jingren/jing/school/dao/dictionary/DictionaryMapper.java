package com.jingren.jing.school.dao.dictionary;

import java.util.List;
import java.util.Map;

import com.jingren.jing.school.bean.dictionary.Dictionary;

public interface DictionaryMapper {

	/**
	 * 保存数据词典
	* @Title: DictionaryMapper.java 
	* @Package com.jingren.jing.school.dao.dictionary 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月14日 上午11:57:56 
	* @version 网校+CRM系统 V1.0
	 */
	boolean saveDictionary(Dictionary dictionary);
	/**
	 * 修改数据词典
	* @Title: DictionaryMapper.java 
	* @Package com.jingren.jing.school.dao.dictionary 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月14日 上午11:58:54 
	* @version 网校+CRM系统 V1.0
	 */
	boolean updateDictionary(Dictionary dictionary);
	/**
	 * 删除数据字典
	* @Title: DictionaryMapper.java 
	* @Package com.jingren.jing.school.dao.dictionary 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月14日 上午11:59:24 
	* @version 网校+CRM系统 V1.0
	 */
	boolean delteDictionary(Integer dic_id);
	/**
	 * 数据字典列表
	* @Title: DictionaryMapper.java 
	* @Package com.jingren.jing.school.dao.dictionary 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月14日 下午12:00:23 
	* @version 网校+CRM系统 V1.0
	 */
	List<Dictionary> getDictionaryList(Map<String, Object> map);
	/**
	 * 数据字典数量
	* @Title: DictionaryMapper.java 
	* @Package com.jingren.jing.school.dao.dictionary 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月14日 下午12:00:52 
	* @version 网校+CRM系统 V1.0
	 */
	Integer getDictionaryNumber(Map<String, Object> map);
	/**
	 * 获取数据字典
	* @Title: DictionaryMapper.java 
	* @Package com.jingren.jing.school.dao.dictionary 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月14日 下午12:03:24 
	* @version 网校+CRM系统 V1.0
	 */
	Dictionary getDictionary(Map<String, Object> map);
}
