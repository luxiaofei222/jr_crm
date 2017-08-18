package com.jingren.jing.school.service.scnews;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.school.bean.scnews.ScNews;
import com.jingren.jing.school.dao.scnews.ScNewsMapper;
@Service
public class ScNewsServiceImpl implements ScNewsService{
	@Resource
	private ScNewsMapper scNewsMapper;

	@Override
	public boolean saveNews(ScNews scNews) {
		return scNewsMapper.saveNews(scNews);
	}

	@Override
	public boolean updateNews(ScNews scNews) {
		return scNewsMapper.updateNews(scNews);
	}

	@Override
	public boolean deleteNews(Integer news_id) {
		return scNewsMapper.deleteNews(news_id);
	}

	@Override
	public ScNews getNews(Map<String, Object> map) {
		return scNewsMapper.getNews(map);
	}

	@Override
	public List<ScNews> getNewsList(Map<String, Object> map) {
		return scNewsMapper.getNewsList(map);
	}

	@Override
	public Integer getNewsNumber(Map<String, Object> map) {
		return scNewsMapper.getNewsNumber(map);
	}

}
