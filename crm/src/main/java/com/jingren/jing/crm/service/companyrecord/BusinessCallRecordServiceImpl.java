package com.jingren.jing.crm.service.companyrecord;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.crm.bean.companyrecord.BusinessCallRecord;
import com.jingren.jing.crm.dao.companyrecord.BusinessCallRecordMapper;
@Service
public class BusinessCallRecordServiceImpl implements BusinessCallRecordService {

	@Resource
	private BusinessCallRecordMapper businessCallRecordMapper;
	@Override
	public boolean saveBusinessCallRecord(BusinessCallRecord businessCallRecord) {
		return businessCallRecordMapper.saveBusinessCallRecord(businessCallRecord);
	}

	@Override
	public boolean updateBusinessCallRecord(BusinessCallRecord businessCallRecord) {
		return businessCallRecordMapper.updateBusinessCallRecord(businessCallRecord);
	}

	@Override
	public boolean deleteBusinessCallRecord(Integer record_id) {
		return businessCallRecordMapper.deleteBusinessCallRecord(record_id);
	}

	@Override
	public BusinessCallRecord getBusinessCallRecord(Map<String, Object> map) {
		return businessCallRecordMapper.getBusinessCallRecord(map);
	}

	@Override
	public List<BusinessCallRecord> getBusinessCallRecordList(Map<String, Object> map) {
		return businessCallRecordMapper.getBusinessCallRecordList(map);
	}

	@Override
	public Integer getBusinessCallRecordNumber(Map<String, Object> map) {
		return businessCallRecordMapper.getBusinessCallRecordNumber(map);
	}

	@Override
	public List<BusinessCallRecord> getBusinessCallRecordByHuifu(Map<String, Object> map) {
		return businessCallRecordMapper.getBusinessCallRecordByHuifu(map);
	}

}
