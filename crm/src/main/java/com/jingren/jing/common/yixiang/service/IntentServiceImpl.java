package com.jingren.jing.common.yixiang.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.common.yixiang.bean.Intent;
import com.jingren.jing.common.yixiang.dao.IntentMapper;
@Service
public class IntentServiceImpl implements IntentService{

	@Resource
	private IntentMapper intentMapper;
	@Override
	public boolean saveIntent(Intent intent) {
		return intentMapper.saveIntent(intent);
	}

}
