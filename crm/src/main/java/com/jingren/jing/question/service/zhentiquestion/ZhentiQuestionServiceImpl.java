package com.jingren.jing.question.service.zhentiquestion;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.question.bean.zhentiquestion.ZhentiQuestion;
import com.jingren.jing.question.dao.zhentiquestion.ZhentiQuestionMapper;
@Service
public class ZhentiQuestionServiceImpl implements ZhentiQuestionService {

	@Resource
	private ZhentiQuestionMapper zhentiQuestionMapper;
	@Override
	public boolean saveZhentiQuestion(ZhentiQuestion zhentiQuestion) {
		return zhentiQuestionMapper.saveZhentiQuestion(zhentiQuestion);
	}

	@Override
	public boolean updateZhentiQuestion(ZhentiQuestion zhentiQuestion) {
		return zhentiQuestionMapper.updateZhentiQuestion(zhentiQuestion);
	}

	@Override
	public boolean deleteZhentiQuestion(Integer zhenti_question_id) {
		return zhentiQuestionMapper.deleteZhentiQuestion(zhenti_question_id);
	}

	@Override
	public ZhentiQuestion getZhentiQuestion(Map<String, Object> map) {
		return zhentiQuestionMapper.getZhentiQuestion(map);
	}

	@Override
	public List<ZhentiQuestion> getZhentiQuestionList(Map<String, Object> map) {
		return zhentiQuestionMapper.getZhentiQuestionList(map);
	}

	@Override
	public Integer getZhentiQuestionNumber(Map<String, Object> map) {
		return zhentiQuestionMapper.getZhentiQuestionNumber(map);
	}

}
