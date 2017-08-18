package com.jingren.jing.question.service.zhentitypeintro;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.question.bean.zhentitypeintro.ZhentiTypeIntroduce;
import com.jingren.jing.question.dao.zhentitypeintro.ZhentiTypeIntroduceMapper;
@Service
public class ZhentiTypeIntroduceServiceImpl implements ZhentiTypeIntroduceService{
	@Resource
	private ZhentiTypeIntroduceMapper zhentiTypeIntroduceMapper;
	@Override
	public boolean saveZhentiTypeIntroduce(ZhentiTypeIntroduce zhentiTypeIntroduce) {
		return zhentiTypeIntroduceMapper.saveZhentiTypeIntroduce(zhentiTypeIntroduce);
	}

	@Override
	public boolean updateZhentiTypeIntroduce(ZhentiTypeIntroduce zhentiTypeIntroduce) {
		return zhentiTypeIntroduceMapper.updateZhentiTypeIntroduce(zhentiTypeIntroduce);
	}

	@Override
	public boolean deleteZhentiTypeIntroduce(Integer zhenti_id) {
		return zhentiTypeIntroduceMapper.deleteZhentiTypeIntroduce(zhenti_id);
	}

	@Override
	public ZhentiTypeIntroduce getZhentiTypeIntroduce(Map<String, Object> map) {
		return zhentiTypeIntroduceMapper.getZhentiTypeIntroduce(map);
	}

	@Override
	public List<ZhentiTypeIntroduce> getZhentiTypeIntroduceList(Map<String, Object> map) {
		return zhentiTypeIntroduceMapper.getZhentiTypeIntroduceList(map);
	}

}
