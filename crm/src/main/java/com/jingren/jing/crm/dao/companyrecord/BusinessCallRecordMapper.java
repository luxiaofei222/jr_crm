package com.jingren.jing.crm.dao.companyrecord;

import java.util.List;
import java.util.Map;

import com.jingren.jing.crm.bean.companyrecord.BusinessCallRecord;

public interface BusinessCallRecordMapper {

	/**
	* @Title: BusinessCallRecordMapper.java 
	* @Package com.jingren.jing.crm.dao.companyrecord 
	* @Description: TODO 保存通话记录
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月22日 下午2:31:29 
	* @version 网校+CRM系统 V1.0
	 */
	boolean saveBusinessCallRecord(BusinessCallRecord businessCallRecord);
	/**
	* @Title: BusinessCallRecordMapper.java 
	* @Package com.jingren.jing.crm.dao.companyrecord 
	* @Description: TODO 修改通话记录
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月22日 下午2:32:01 
	* @version 网校+CRM系统 V1.0
	 */
	boolean updateBusinessCallRecord(BusinessCallRecord businessCallRecord);
	/**
	* @Title: BusinessCallRecordMapper.java 
	* @Package com.jingren.jing.crm.dao.companyrecord 
	* @Description: TODO 删除通话记录
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月22日 下午2:33:02 
	* @version 网校+CRM系统 V1.0
	 */
	boolean deleteBusinessCallRecord(Integer record_id);
	/**
	* @Title: BusinessCallRecordMapper.java 
	* @Package com.jingren.jing.crm.dao.companyrecord 
	* @Description: TODO 获取通话记录
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月22日 下午2:33:32 
	* @version 网校+CRM系统 V1.0
	 */
	BusinessCallRecord getBusinessCallRecord(Map<String, Object> map);
	/**
	* @Title: BusinessCallRecordMapper.java 
	* @Package com.jingren.jing.crm.dao.companyrecord 
	* @Description: TODO 通话记录列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月22日 下午2:34:04 
	* @version 网校+CRM系统 V1.0
	 */
	List<BusinessCallRecord> getBusinessCallRecordList(Map<String, Object> map);
	/**
	* @Title: BusinessCallRecordMapper.java 
	* @Package com.jingren.jing.crm.dao.companyrecord 
	* @Description: TODO 获取记录数量
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月22日 下午2:36:08 
	* @version 网校+CRM系统 V1.0
	 */
	Integer getBusinessCallRecordNumber(Map<String, Object> map);
	/**
	* @Title: BusinessCallRecordMapper.java 
	* @Package com.jingren.jing.crm.dao.companyrecord 
	* @Description: TODO 解决恢复联系人电话
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月7日 下午3:45:06 
	* @version 网校+CRM系统 V1.0
	 */
	List<BusinessCallRecord> getBusinessCallRecordByHuifu(Map<String, Object> map);
}
