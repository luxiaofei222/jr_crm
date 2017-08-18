package com.jingren.jing.oa.dao.oa_notice;

import java.util.List;
import java.util.Map;

import com.jingren.jing.oa.bean.oa_notice.Notice;

public interface NoticeMapper {

	/**
	* @Title: NoticeMapper.java 
	* @Package com.jingren.jing.oa.dao.oa_notice 
	* @Description: TODO 发表公告
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年3月31日 上午10:22:59 
	* @version 网校+CRM系统 V1.0
	 */
	boolean saveNotice(Notice notice);
	/**
	* @Title: NoticeMapper.java 
	* @Package com.jingren.jing.oa.dao.oa_notice 
	* @Description: TODO 修改公告
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年3月31日 上午10:23:35 
	* @version 网校+CRM系统 V1.0
	 */
	boolean updateNotice(Notice notice);
	/**
	* @Title: NoticeMapper.java 
	* @Package com.jingren.jing.oa.dao.oa_notice 
	* @Description: TODO 删除公告
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年3月31日 上午10:23:57 
	* @version 网校+CRM系统 V1.0
	 */
	boolean deleteNotice(Integer notice_id);
	/**
	* @Title: NoticeMapper.java 
	* @Package com.jingren.jing.oa.dao.oa_notice 
	* @Description: TODO 公告列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年3月31日 上午10:24:28 
	* @version 网校+CRM系统 V1.0
	 */
	List<Notice> getNoticeList(Map<String, Object> map);
	/**
	* @Title: NoticeMapper.java 
	* @Package com.jingren.jing.oa.dao.oa_notice 
	* @Description: TODO 公告数量
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年3月31日 上午10:25:00 
	* @version 网校+CRM系统 V1.0
	 */
	Integer getNoticeNumber(Map<String, Object> map);
	/**
	* @Title: NoticeMapper.java 
	* @Package com.jingren.jing.oa.dao.oa_notice 
	* @Description: TODO 获取最新的公告信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年3月31日 下午5:59:39 
	* @version 网校+CRM系统 V1.0
	 */
	List<Notice> getNotice();
}
