package com.jingren.jing.common.downerror.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.common.downerror.bean.DownError;
import com.jingren.jing.common.downerror.dao.DownErrorMapper;
@Service
public class DownErrorServiceImpl implements DownErrorService{

	@Resource
	private DownErrorMapper downErrorMapper;
	
	@Override
	public boolean saveDownError(DownError downError) {
		return downErrorMapper.saveDownError(downError);
	}

	@Override
	public boolean deleteDownError() {
		return downErrorMapper.deleteDownError();
	}

	@Override
	public List<DownError> getDownErrorList(Map<String, Object> map) {
		return downErrorMapper.getDownErrorList(map);
	}

}
