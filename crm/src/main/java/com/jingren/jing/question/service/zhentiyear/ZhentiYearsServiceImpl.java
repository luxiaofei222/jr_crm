package com.jingren.jing.question.service.zhentiyear;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.question.bean.zhentiyear.ZhentiYears;
import com.jingren.jing.question.dao.zhentiyear.ZhentiYearsMapper;
@Service
public class ZhentiYearsServiceImpl implements ZhentiYearsService {
	@Resource
	private ZhentiYearsMapper zhentiYearsMapper;

	@Override
	public boolean saveZhentiYears(ZhentiYears zhentiYears) {
		return zhentiYearsMapper.saveZhentiYears(zhentiYears);
	}

	@Override
	public boolean updateZhentiYears(ZhentiYears zhentiYears) {
		return zhentiYearsMapper.updateZhentiYears(zhentiYears);
	}

	@Override
	public ZhentiYears getZhentiYears(Map<String, Object> map) {
		return zhentiYearsMapper.getZhentiYears(map);
	}

	@Override
	public List<ZhentiYears> getZhentiYearsList(Map<String, Object> map) {
		return zhentiYearsMapper.getZhentiYearsList(map);
	}

	@Override
	public Integer getZhentiYearsNumber(Map<String, Object> map) {
		return zhentiYearsMapper.getZhentiYearsNumber(map);
	}

	@Override
	public boolean deleteZhentiYears(Integer zhenti_id) {
		return zhentiYearsMapper.deleteZhentiYears(zhenti_id);
	}

}
