package com.jingren.jing.school.service.comment;

import java.util.List;
import java.util.Map;

import com.jingren.jing.school.bean.comment.VideoComment;

public interface VideoCommentService {
	/**
	 * 保存评论信息
	* @Title: VideoCommentMapper.java 
	* @Package com.jingren.jing.school.dao.comment 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月8日 下午2:07:29 
	* @version 网校+CRM系统 V1.0
	 */
	boolean saveComment(VideoComment videoComment);
	/**
	 * 删除评论
	* @Title: VideoCommentMapper.java 
	* @Package com.jingren.jing.school.dao.comment 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月8日 下午2:07:57 
	* @version 网校+CRM系统 V1.0
	 */
	boolean deleteComment(Integer comment_id);
	/**
	 * 评论列表
	* @Title: VideoCommentMapper.java 
	* @Package com.jingren.jing.school.dao.comment 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月8日 下午2:09:19 
	* @version 网校+CRM系统 V1.0
	 */
	List<VideoComment> getVideoCommentList(Map<String, Object> map);
	/**
	 * 获取评论数量
	* @Title: VideoCommentMapper.java 
	* @Package com.jingren.jing.school.dao.comment 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月8日 下午2:09:57 
	* @version 网校+CRM系统 V1.0
	 */
	Integer getCommentNumber(Map<String, Object> map);
	/**
	 * 评论详细信息
	* @Title: VideoCommentMapper.java 
	* @Package com.jingren.jing.school.dao.comment 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月8日 下午2:10:24 
	* @version 网校+CRM系统 V1.0
	 */
	VideoComment getVideoComment(Map<String, Object> map);
}
