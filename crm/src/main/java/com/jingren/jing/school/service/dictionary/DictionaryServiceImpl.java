package com.jingren.jing.school.service.dictionary;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.school.bean.dictionary.Dictionary;
import com.jingren.jing.school.dao.dictionary.DictionaryMapper;
@Service
public class DictionaryServiceImpl implements DictionaryService{
	@Resource
	private DictionaryMapper dictionaryMapper;

	@Override
	public boolean saveDictionary(Dictionary dictionary) {
		return dictionaryMapper.saveDictionary(dictionary);
	}

	@Override
	public boolean updateDictionary(Dictionary dictionary) {
		return dictionaryMapper.updateDictionary(dictionary);
	}

	@Override
	public boolean delteDictionary(Integer dic_id) {
		return dictionaryMapper.delteDictionary(dic_id);
	}

	@Override
	public List<Dictionary> getDictionaryList(Map<String, Object> map) {
		return dictionaryMapper.getDictionaryList(map);
	}

	@Override
	public Integer getDictionaryNumber(Map<String, Object> map) {
		return dictionaryMapper.getDictionaryNumber(map);
	}

	@Override
	public Dictionary getDictionary(Map<String, Object> map) {
		return dictionaryMapper.getDictionary(map);
	}

}
