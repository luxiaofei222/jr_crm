package com.jingren.jing.recruit.dao.xueli_baoming;

import java.util.List;
import java.util.Map;

import com.jingren.jing.recruit.bean.xueli_baoming.XueLiBaoMing;


public interface XueLiBaoMingMapper {

	/**
	* @Title: XueLiBaoMingMapper.java 
	* @Package com.jingren.jing.dao.xueli_baoming 
	* @Description: TODO 学历报名
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月29日 上午11:50:46 
	* @version V1.0
	 */
	boolean saveXueLiBaoMing(XueLiBaoMing xueLiBaoMing);
	/**
	* @Title: XueLiBaoMingMapper.java 
	* @Package com.jingren.jing.dao.xueli_baoming 
	* @Description: TODO 删除学历报名信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月29日 上午11:51:20 
	* @version V1.0
	 */
	boolean deleteXueLiBaoMing(Map<String, Object> map);
	/**
	* @Title: XueLiBaoMingMapper.java 
	* @Package com.jingren.jing.dao.xueli_baoming 
	* @Description: TODO 学历报名详情
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月29日 上午11:51:42 
	* @version V1.0
	 */
	XueLiBaoMing getXueLiBaoMing(Map<String, Object> map);
	/**
	* @Title: XueLiBaoMingMapper.java 
	* @Package com.jingren.jing.dao.xueli_baoming 
	* @Description: TODO 学历报名列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月29日 上午11:52:11 
	* @version V1.0
	 */
	List<XueLiBaoMing> getXueLiBaoMingList(Map<String, Object> map);
	/**
	* @Title: XueLiBaoMingMapper.java 
	* @Package com.jingren.jing.dao.xueli_baoming 
	* @Description: TODO 学历报名数量
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月29日 上午11:54:33 
	* @version V1.0
	 */
	Integer getXueLiBaoMingNumber(Map<String, Object> map);
}
