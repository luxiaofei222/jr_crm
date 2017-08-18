package com.jingren.jing.school.dao.myshoping;

import java.util.List;
import java.util.Map;

import com.jingren.jing.school.bean.myshoping.MyShoping;

public interface MyShopingMapper {
	/**
	 * 添加我的购物车
	* @Title: MyShopingMapper.java 
	* @Package com.jingren.jing.school.dao.MyShoping 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月21日 下午1:09:56 
	* @version 网校+CRM系统 V1.0
	 */
	boolean saveMyShoping(MyShoping MyShoping);
	/**
	 * 删除我的购物车
	* @Title: MyShopingMapper.java 
	* @Package com.jingren.jing.school.dao.MyShoping 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月21日 下午1:10:30 
	* @version 网校+CRM系统 V1.0
	 */
	boolean deleteMyShoping(Integer MyShoping_id);
	/**
	 * 获取我的购物车详情
	* @Title: MyShopingMapper.java 
	* @Package com.jingren.jing.school.dao.MyShoping 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月21日 下午1:13:15 
	* @version 网校+CRM系统 V1.0
	 */
	MyShoping getMyShoping(Map<String, Object> map);
	/**
	 * 获取我的购物车列表
	* @Title: MyShopingMapper.java 
	* @Package com.jingren.jing.school.dao.MyShoping 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月21日 下午1:13:45 
	* @version 网校+CRM系统 V1.0
	 */
	List<MyShoping> getMyShopingList(Map<String, Object> map);
	/**
	 * 获取我的购物车数量
	* @Title: MyShopingMapper.java 
	* @Package com.jingren.jing.school.dao.MyShoping 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月21日 下午1:14:18 
	* @version 网校+CRM系统 V1.0
	 */
	Integer getMyShopingNumber(Map<String, Object> map);
	/**
	 * 支付成功后清空购物车
	* @Title: MyShopingMapper.java 
	* @Package com.jingren.jing.school.dao.myshoping 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月23日 下午1:04:03 
	* @version 网校+CRM系统 V1.0
	 */
	boolean deleteMyshopingForPay(Map<String, Object> map);
}
