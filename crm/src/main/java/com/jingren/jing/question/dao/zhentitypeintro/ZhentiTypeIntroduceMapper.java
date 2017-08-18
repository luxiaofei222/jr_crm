package com.jingren.jing.question.dao.zhentitypeintro;

import java.util.List;
import java.util.Map;

import com.jingren.jing.question.bean.zhentitypeintro.ZhentiTypeIntroduce;

public interface ZhentiTypeIntroduceMapper {

	/**
	* @Title: ZhentiTypeIntroduceMapper.java 
	* @Package com.jingren.jing.question.dao.zhentitypeintro 
	* @Description: TODO 保存题目类型介绍
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月9日 下午1:13:20 
	* @version 网校+CRM系统 V1.0
	 */
	boolean saveZhentiTypeIntroduce(ZhentiTypeIntroduce zhentiTypeIntroduce);
	/**
	* @Title: ZhentiTypeIntroduceMapper.java 
	* @Package com.jingren.jing.question.dao.zhentitypeintro 
	* @Description: TODO 修改题目类型介绍
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月9日 下午1:13:57 
	* @version 网校+CRM系统 V1.0
	 */
	boolean updateZhentiTypeIntroduce(ZhentiTypeIntroduce zhentiTypeIntroduce);
	/**
	* @Title: ZhentiTypeIntroduceMapper.java 
	* @Package com.jingren.jing.question.dao.zhentitypeintro 
	* @Description: TODO 删除题目类型介绍
	* @author 鲁晓飞 MR.Lu    
	* @date 2017年2月9日 下午1:14:37 
	* @version 网校+CRM系统 V1.0
	 */
	boolean deleteZhentiTypeIntroduce(Integer zhenti_id);
	/**
	* @Title: ZhentiTypeIntroduceMapper.java 
	* @Package com.jingren.jing.question.dao.zhentitypeintro 
	* @Description: TODO 获取题目介绍
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月9日 下午1:16:48 
	* @version 网校+CRM系统 V1.0
	 */
	ZhentiTypeIntroduce getZhentiTypeIntroduce(Map<String, Object> map);
	/**
	* @Title: ZhentiTypeIntroduceMapper.java 
	* @Package com.jingren.jing.question.dao.zhentitypeintro 
	* @Description: TODO 题型介绍列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月13日 上午8:48:28 
	* @version 网校+CRM系统 V1.0
	 */
	List<ZhentiTypeIntroduce> getZhentiTypeIntroduceList(Map<String, Object> map);
}
