package com.jingren.jing.question.service.zhentioption;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.question.bean.zhentioption.ZhentiQuestionOption;
import com.jingren.jing.question.dao.zhentioption.ZhentiQuestionOptionMapper;
@Service
public class ZhentiQuestionOptionServiceImpl implements ZhentiQuestionOptionService{

	@Resource
	private ZhentiQuestionOptionMapper zhentiQuestionOptionMapper;
	@Override
	public boolean saveZhentiQuestionOption(ZhentiQuestionOption zhentiQuestionOption) {
		return zhentiQuestionOptionMapper.saveZhentiQuestionOption(zhentiQuestionOption);
	}

	@Override
	public boolean updateZhentiQuestionOption(ZhentiQuestionOption zhentiQuestionOption) {
		return zhentiQuestionOptionMapper.updateZhentiQuestionOption(zhentiQuestionOption);
	}

	@Override
	public boolean deleteZhentiQuestionOption(Integer zhenti_question_id) {
		return zhentiQuestionOptionMapper.deleteZhentiQuestionOption(zhenti_question_id);
	}

	@Override
	public List<ZhentiQuestionOption> getZhentiQuestionOptionList(Map<String, Object> map) {
		return zhentiQuestionOptionMapper.getZhentiQuestionOptionList(map);
	}

}
