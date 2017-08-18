package com.jingren.jing.school.dao.baoming;

import java.util.List;
import java.util.Map;

import com.jingren.jing.school.bean.baoming.Baoming;

public interface BaomingMapper {

	/**
	* @Title: BaomingMapper.java 
	* @Package com.jingren.jing.school.dao.baoming 
	* @Description: TODO 获取报名信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年4月5日 下午5:20:16 
	* @version 网校+CRM系统 V1.0
	 */
	Baoming getBaoming(Map<String, Object> map);
	/**
	* @Title: BaomingMapper.java 
	* @Package com.jingren.jing.school.dao.baoming 
	* @Description: TODO 报名列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年4月5日 下午5:20:45 
	* @version 网校+CRM系统 V1.0
	 */
	List<Baoming> getBaomingList(Map<String, Object> map);
	/**
	* @Title: BaomingMapper.java 
	* @Package com.jingren.jing.school.dao.baoming 
	* @Description: TODO 获取报名数量
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年4月5日 下午5:21:22 
	* @version 网校+CRM系统 V1.0
	 */
	Integer getBaomingNumber(Map<String, Object> map);
	/**
	* @Title: BaomingMapper.java 
	* @Package com.jingren.jing.school.dao.baoming 
	* @Description: TODO 删除报名信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年4月5日 下午5:52:47 
	* @version 网校+CRM系统 V1.0
	 */
	boolean deleteBaoming(Integer baoming_id);
	/**
	* @Title: BaomingMapper.java 
	* @Package com.jingren.jing.school.dao.baoming 
	* @Description: TODO 网络报名
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月2日 下午5:15:54 
	* @version 网校+CRM系统 V1.0
	 */
	boolean saveBaoming(Baoming baoming);
}
