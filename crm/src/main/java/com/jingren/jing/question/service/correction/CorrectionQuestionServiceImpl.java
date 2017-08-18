package com.jingren.jing.question.service.correction;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.question.bean.correction.CorrectionQuestion;
import com.jingren.jing.question.dao.correction.CorrectionQuestionMapper;
@Service
public class CorrectionQuestionServiceImpl implements CorrectionQuestionService{

	@Resource
	private CorrectionQuestionMapper correctionQuestionMapper;
	@Override
	public boolean saveCorrectionQuestion(CorrectionQuestion correctionQuestion) {
		return correctionQuestionMapper.saveCorrectionQuestion(correctionQuestion);
	}

	@Override
	public boolean updateCorrectionQuestion(CorrectionQuestion correctionQuestion) {
		return correctionQuestionMapper.updateCorrectionQuestion(correctionQuestion);
	}

	@Override
	public boolean deleteCorrectionQuestion(Integer correction_id) {
		return correctionQuestionMapper.deleteCorrectionQuestion(correction_id);
	}

	@Override
	public CorrectionQuestion getCorrectionQuestion(Map<String, Object> map) {
		return correctionQuestionMapper.getCorrectionQuestion(map);
	}

	@Override
	public List<CorrectionQuestion> getCorrectionQuestionList(Map<String, Object> map) {
		return correctionQuestionMapper.getCorrectionQuestionList(map);
	}

	@Override
	public Integer getgetCorrectionQuestionNumber(Map<String, Object> map) {
		return correctionQuestionMapper.getgetCorrectionQuestionNumber(map);
	}

}
