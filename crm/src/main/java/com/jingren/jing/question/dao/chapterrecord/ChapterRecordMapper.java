package com.jingren.jing.question.dao.chapterrecord;

import java.util.List;
import java.util.Map;

import com.jingren.jing.question.bean.chapterrecord.ChapterRecord;

public interface ChapterRecordMapper {

	/**
	* @Title: ChapterRecordMapper.java 
	* @Package com.jingren.jing.question.dao.chapterrecord 
	* @Description: TODO 保存章节练习练习记录
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月20日 下午1:41:13 
	* @version 网校+CRM系统 V1.0
	 */
	boolean saveChapterRecord(ChapterRecord chapterRecord);
	/**
	* @Title: ChapterRecordMapper.java 
	* @Package com.jingren.jing.question.dao.chapterrecord 
	* @Description: TODO 删除用户练习记录
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月20日 下午1:43:56 
	* @version 网校+CRM系统 V1.0
	 */
	boolean deleteChapterRecord(ChapterRecord chapterRecord);
	/**
	* @Title: ChapterRecordMapper.java 
	* @Package com.jingren.jing.question.dao.chapterrecord 
	* @Description: TODO 练习记录列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月20日 下午1:56:31 
	* @version 网校+CRM系统 V1.0
	 */
	List<ChapterRecord> getChapterRecordList(Map<String, Object> map);
	/**
	* @Title: ChapterRecordMapper.java 
	* @Package com.jingren.jing.question.dao.chapterrecord 
	* @Description: TODO 章节练习数量-题目去重
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月20日 下午1:57:01 
	* @version 网校+CRM系统 V1.0
	 */
	Integer getChapterRecordNumber(Map<String, Object> map);
	/**
	* @Title: ChapterRecordMapper.java 
	* @Package com.jingren.jing.question.dao.chapterrecord 
	* @Description: TODO 普通的查询数量
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月4日 上午8:45:28 
	* @version 网校+CRM系统 V1.0
	 */
	Integer getChapterRecordNumberNormal(Map<String, Object> map);
	/**
	* @Title: ChapterRecordMapper.java 
	* @Package com.jingren.jing.question.dao.chapterrecord 
	* @Description: TODO 获取记录信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月6日 下午5:11:22 
	* @version 网校+CRM系统 V1.0
	 */
	ChapterRecord getChapterRecord(Map<String, Object> map);
	
}
