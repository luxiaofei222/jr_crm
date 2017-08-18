package com.jingren.jing.school.service.clearance;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.school.bean.clearance.ClearanceVideo;
import com.jingren.jing.school.dao.clearance.ClearanceVideoMappr;
@Service
public class ClearanceVideoServiceImpl implements ClearanceVideoService{

	@Resource
	private ClearanceVideoMappr clearanceVideoMappr;
	@Override
	public boolean saveClearanceVideo(ClearanceVideo clearanceVideo) {
		return clearanceVideoMappr.saveClearanceVideo(clearanceVideo);
	}

	@Override
	public boolean deleteClearanceVideo(Integer clear_video_id) {
		return clearanceVideoMappr.deleteClearanceVideo(clear_video_id);
	}

	@Override
	public List<ClearanceVideo> getClearanceVideoList(Map<String, Object> map) {
		return clearanceVideoMappr.getClearanceVideoList(map);
	}

}
