package com.jingren.jing.common.university.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.common.university.bean.Review;
import com.jingren.jing.common.university.dao.ReviewMapper;
@Service
public class ReviewServiceImpl implements ReviewService{

	@Resource
	private ReviewMapper reviewMapper;
	@Override
	public List<Review> getReviewList(Map<String, Object> map) {
		return reviewMapper.getReviewList(map);
	}

	@Override
	public Review getReview(Map<String, Object> map) {
		return reviewMapper.getReview(map);
	}

}
