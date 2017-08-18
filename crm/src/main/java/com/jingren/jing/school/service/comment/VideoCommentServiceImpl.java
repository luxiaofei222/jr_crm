package com.jingren.jing.school.service.comment;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.school.bean.comment.VideoComment;
import com.jingren.jing.school.dao.comment.VideoCommentMapper;
@Service
public class VideoCommentServiceImpl implements VideoCommentService{
	@Resource
	private VideoCommentMapper videoCommentMapper;

	@Override
	public boolean saveComment(VideoComment videoComment) {
		return videoCommentMapper.saveComment(videoComment);
	}

	@Override
	public boolean deleteComment(Integer comment_id) {
		return videoCommentMapper.deleteComment(comment_id);
	}

	@Override
	public List<VideoComment> getVideoCommentList(Map<String, Object> map) {
		return videoCommentMapper.getVideoCommentList(map);
	}

	@Override
	public Integer getCommentNumber(Map<String, Object> map) {
		return videoCommentMapper.getCommentNumber(map);
	}

	@Override
	public VideoComment getVideoComment(Map<String, Object> map) {
		return videoCommentMapper.getVideoComment(map);
	}

}
