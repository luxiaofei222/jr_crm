package com.jingren.jing.recruit.dao.chengkaoyuanxiao;

import java.util.List;
import java.util.Map;

import com.jingren.jing.recruit.bean.chengkaoyuanxiao.CKZhaoSheng;

public interface CKZhaoShengMapper {

	/**
	* @Title: CKZhaoShengMapper.java 
	* @Package com.jingren.jing.recruit.dao.chengkaoyuanxiao 
	* @Description: TODO 保存成考招生信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月19日 上午9:55:12 
	* @version 网校+CRM系统 V1.0
	 */
	boolean saveCKZhaoSheng(CKZhaoSheng ckZhaoSheng);
	/**
	* @Title: CKZhaoShengMapper.java 
	* @Package com.jingren.jing.recruit.dao.chengkaoyuanxiao 
	* @Description: TODO 修改成考招生信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月19日 上午9:55:43 
	* @version 网校+CRM系统 V1.0
	 */
	boolean updateCKZhaoSheng(CKZhaoSheng ckZhaoSheng);
	/**
	* @Title: CKZhaoShengMapper.java 
	* @Package com.jingren.jing.recruit.dao.chengkaoyuanxiao 
	* @Description: TODO 删除招生信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月19日 上午9:58:53 
	* @version 网校+CRM系统 V1.0
	 */
	boolean deleteCKZhaoSheng(Integer zhaosheng_id);
	/**
	* @Title: CKZhaoShengMapper.java 
	* @Package com.jingren.jing.recruit.dao.chengkaoyuanxiao 
	* @Description: TODO 删除成考招生信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月19日 上午9:59:32 
	* @version 网校+CRM系统 V1.0
	 */
	CKZhaoSheng getCKZhaoSheng(Map<String, Object> map);
	/**
	* @Title: CKZhaoShengMapper.java 
	* @Package com.jingren.jing.recruit.dao.chengkaoyuanxiao 
	* @Description: TODO 成考招生信息列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月19日 上午10:00:01 
	* @version 网校+CRM系统 V1.0
	 */
	List<CKZhaoSheng> getCKZhaoShengList(Map<String, Object> map);
	/**
	* @Title: CKZhaoShengMapper.java 
	* @Package com.jingren.jing.recruit.dao.chengkaoyuanxiao 
	* @Description: TODO 成考招生数量
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月19日 上午10:01:13 
	* @version 网校+CRM系统 V1.0
	 */
	Integer getCKZhaoShengNumber(Map<String, Object> map);
}
