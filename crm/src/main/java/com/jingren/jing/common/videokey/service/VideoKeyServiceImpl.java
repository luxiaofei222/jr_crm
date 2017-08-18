package com.jingren.jing.common.videokey.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.common.videokey.bean.VideoKey;
import com.jingren.jing.common.videokey.dao.VideoKeyMapper;
@Service("videoKeyService")
public class VideoKeyServiceImpl implements VideoKeyService {

	@Resource
	private VideoKeyMapper videoKeyMapper;
	@Override
	public boolean saveVideoKey(VideoKey videoKey) {
		return videoKeyMapper.saveVideoKey(videoKey);
	}

	@Override
	public boolean deleteVideoKey(String key_number) {
		return videoKeyMapper.deleteVideoKey(key_number);
	}

	@Override
	public VideoKey getVideoKey(Map<String, Object> map) {
		return videoKeyMapper.getVideoKey(map);
	}

	@Override
	public boolean deleteAllVideoKey() {
		return videoKeyMapper.deleteAllVideoKey();
	}

}
