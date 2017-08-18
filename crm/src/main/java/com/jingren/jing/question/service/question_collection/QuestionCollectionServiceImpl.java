package com.jingren.jing.question.service.question_collection;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.question.bean.question_collection.QuestionCollection;
import com.jingren.jing.question.dao.question_collection.QuestionCollectionMapper;
@Service
public class QuestionCollectionServiceImpl implements QuestionCollectionService {

	@Resource
	private QuestionCollectionMapper questionCollectionMapper;
	@Override
	public boolean saveQuestionCollection(QuestionCollection questionCollection) {
		return questionCollectionMapper.saveQuestionCollection(questionCollection);
	}

	@Override
	public boolean deleteQuestionCollection(Integer question_collection_id) {
		return questionCollectionMapper.deleteQuestionCollection(question_collection_id);
	}

	@Override
	public QuestionCollection getQuestionCollection(Map<String, Object> map) {
		return questionCollectionMapper.getQuestionCollection(map);
	}

	@Override
	public List<QuestionCollection> getQuestionCollectionList(Map<String, Object> map) {
		return questionCollectionMapper.getQuestionCollectionList(map);
	}

	@Override
	public Integer getQuestionCollectionNumber(Map<String, Object> map) {
		return questionCollectionMapper.getQuestionCollectionNumber(map);
	}

}
