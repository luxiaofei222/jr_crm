package com.jingren.jing.conult.service.record;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.conult.bean.record.NetConultRecord;
import com.jingren.jing.conult.dao.record.NetConultRecordMapper;
@Service
public class NetConultRecordServiceImpl implements NetConultRecordService{

	@Resource
	private NetConultRecordMapper NetConultRecordMapper;
	@Override
	public boolean saveNetConultRecord(NetConultRecord netConultRecord) {
		return NetConultRecordMapper.saveNetConultRecord(netConultRecord);
	}

	@Override
	public boolean updateNetConultRecord(NetConultRecord netConultRecord) {
		return NetConultRecordMapper.updateNetConultRecord(netConultRecord);
	}

	@Override
	public boolean deleteNetConultRecord(Integer record_id) {
		return NetConultRecordMapper.deleteNetConultRecord(record_id);
	}

	@Override
	public NetConultRecord getNetConultRecord(Map<String, Object> map) {
		return NetConultRecordMapper.getNetConultRecord(map);
	}

	@Override
	public List<NetConultRecord> getNetConultRecordList(Map<String, Object> map) {
		return NetConultRecordMapper.getNetConultRecordList(map);
	}

	@Override
	public Integer getNetConultRecordNumber(Map<String, Object> map) {
		return NetConultRecordMapper.getNetConultRecordNumber(map);
	}

}
