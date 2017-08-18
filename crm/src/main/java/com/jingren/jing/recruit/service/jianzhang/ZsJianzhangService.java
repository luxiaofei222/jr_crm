package com.jingren.jing.recruit.service.jianzhang;

import java.util.List;
import java.util.Map;

import com.jingren.jing.recruit.bean.jianzhang.ZsJianzhang;

public interface ZsJianzhangService {
	/**
	* @Title: ZsJianzhangMapper.java 
	* @Package com.jingren.jing.recruit.dao.jianzhang 
	* @Description: TODO 保存招生简章
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月26日 上午8:50:11 
	* @version 网校+CRM系统 V1.0
	 */
	boolean saveZsJianzhang(ZsJianzhang zsJianzhang);
	/**
	* @Title: ZsJianzhangMapper.java 
	* @Package com.jingren.jing.recruit.dao.jianzhang 
	* @Description: TODO 修改招生简单
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月26日 上午8:53:40 
	* @version 网校+CRM系统 V1.0
	 */
	boolean updateZsJianzhang(ZsJianzhang zsJianzhang);
	/**
	* @Title: ZsJianzhangMapper.java 
	* @Package com.jingren.jing.recruit.dao.jianzhang 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月26日 上午8:54:30 
	* @version 网校+CRM系统 V1.0
	 */
	boolean deleteZsJianzhang(ZsJianzhang zsJianzhang);
	/**
	* @Title: ZsJianzhangMapper.java 
	* @Package com.jingren.jing.recruit.dao.jianzhang 
	* @Description: TODO 获取简章信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月26日 上午8:59:38 
	* @version 网校+CRM系统 V1.0
	 */
	ZsJianzhang getZsJianzhang(Map<String, Object> map);
	/**
	* @Title: ZsJianzhangMapper.java 
	* @Package com.jingren.jing.recruit.dao.jianzhang 
	* @Description: TODO 获取简章列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月26日 上午9:00:12 
	* @version 网校+CRM系统 V1.0
	 */
	List<ZsJianzhang> getZsJianzhangList(Map<String, Object> map);
	/**
	* @Title: ZsJianzhangMapper.java 
	* @Package com.jingren.jing.recruit.dao.jianzhang 
	* @Description: TODO 获取简章数量
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月26日 上午9:01:21 
	* @version 网校+CRM系统 V1.0
	 */
	Integer getZsJianzhangNumber(Map<String, Object> map);
}
