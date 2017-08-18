package com.jingren.jing.school.service.video;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.school.bean.video.VideoRecord;
import com.jingren.jing.school.dao.video.VideoRecordMapper;
@Service
public class VideoRecordServiceImpl implements VideoRecordService {

	@Resource
	private VideoRecordMapper videoRecordMapper;
	
	@Override
	public boolean saveVideoRecord(VideoRecord videoRecord) {
		return videoRecordMapper.saveVideoRecord(videoRecord);
	}

	@Override
	public boolean updateVideoRecord(VideoRecord videoRecord) {
		return videoRecordMapper.updateVideoRecord(videoRecord);
	}

	@Override
	public boolean deleteVideoRecord(Map<String, Object> map) {
		return videoRecordMapper.deleteVideoRecord(map);
	}

	@Override
	public VideoRecord getVideoRecord(Map<String, Object> map) {
		return videoRecordMapper.getVideoRecord(map);
	}

	@Override
	public List<VideoRecord> getVideoRecordList(Map<String, Object> map) {
		return videoRecordMapper.getVideoRecordList(map);
	}

	@Override
	public Integer getVideoRecordNumber(Map<String, Object> map) {
		return videoRecordMapper.getVideoRecordNumber(map);
	}

}
