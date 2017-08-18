package com.jingren.jing.conult.service.conultinfo;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jingren.jing.conult.bean.conultinfo.NetConult;
import com.jingren.jing.conult.dao.conultinfo.NetConultMapper;
@Service
public class NetConultServiceImpl implements NetConultService {

	@Resource
	private NetConultMapper netConultMapper;
	@Override
	public boolean saveNetConult(NetConult netConult) {
		return netConultMapper.saveNetConult(netConult);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean updateNetConult(NetConult netConult) {
		return netConultMapper.updateNetConult(netConult);
	}

	@Override
	public boolean deleteNetConult(Integer conult_id) {
		return netConultMapper.deleteNetConult(conult_id);
	}

	@Override
	public NetConult getNetConult(Map<String, Object> map) {
		return netConultMapper.getNetConult(map);
	}

	@Override
	public List<NetConult> getNetConultList(Map<String, Object> map) {
		return netConultMapper.getNetConultList(map);
	}

	@Override
	public Integer getNetConultNumber(Map<String, Object> map) {
		return netConultMapper.getNetConultNumber(map);
	}

}
