package com.jingren.jing.conult.service.track;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jingren.jing.conult.bean.track.ConTrack;
import com.jingren.jing.conult.dao.track.ConTrackMapper;
@Service
public class ConTrackServiceImpl implements ConTrackService {
	@Resource
	private ConTrackMapper conTrackMapper;

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean saveConTrack(ConTrack conTrack) {
		
		return conTrackMapper.saveConTrack(conTrack);
	}

	@Override
	public boolean deleteConTrack(Integer contrack_id) {
		return conTrackMapper.deleteConTrack(contrack_id);
	}

	@Override
	public List<ConTrack> getConTrackList(Map<String, Object> map) {
		return conTrackMapper.getConTrackList(map);
	}

}
