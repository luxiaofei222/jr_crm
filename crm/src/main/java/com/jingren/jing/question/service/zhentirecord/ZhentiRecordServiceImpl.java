package com.jingren.jing.question.service.zhentirecord;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.question.bean.zhentirecord.ZhentiRecord;
import com.jingren.jing.question.dao.zhentirecord.ZhentiRecordMapper;
@Service
public class ZhentiRecordServiceImpl implements ZhentiRecordService {

	@Resource
	private ZhentiRecordMapper zhentiRecordMapper;
	@Override
	public boolean saveZhentiRecord(ZhentiRecord zhentiRecord) {
		return zhentiRecordMapper.saveZhentiRecord(zhentiRecord);
	}

	@Override
	public boolean deleteZhentiRecord(ZhentiRecord zhentiRecord) {
		return zhentiRecordMapper.deleteZhentiRecord(zhentiRecord);
	}

	@Override
	public ZhentiRecord getZhentiRecord(Map<String, Object> map) {
		return zhentiRecordMapper.getZhentiRecord(map);
	}

	@Override
	public List<ZhentiRecord> getZhentiRecordList(Map<String, Object> map) {
		return zhentiRecordMapper.getZhentiRecordList(map);
	}

	@Override
	public Integer getZhentiRecordNumber(Map<String, Object> map) {
		return zhentiRecordMapper.getZhentiRecordNumber(map);
	}

}
