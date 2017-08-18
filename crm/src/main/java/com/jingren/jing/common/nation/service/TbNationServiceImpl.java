package com.jingren.jing.common.nation.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.common.nation.bean.TbNation;
import com.jingren.jing.common.nation.dao.TbNationMapper;
@Service
public class TbNationServiceImpl implements TbNationService {

	@Resource
	private TbNationMapper tbNationMapper;
	@Override
	public List<TbNation> getTbNationList() {
		return tbNationMapper.getTbNationList();
	}

}
