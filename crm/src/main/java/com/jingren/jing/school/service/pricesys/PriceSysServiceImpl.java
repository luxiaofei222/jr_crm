package com.jingren.jing.school.service.pricesys;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.school.bean.pricesys.PriceSys;
import com.jingren.jing.school.dao.pricesys.PriceSysMapper;
@Service
public class PriceSysServiceImpl implements PriceSysService {
	
	@Resource
	private PriceSysMapper priceSysMapper;

	@Override
	public boolean addPriceSys(PriceSys priceSys) {
		return priceSysMapper.addPriceSys(priceSys);
	}

	@Override
	public boolean updatePriceSys(PriceSys priceSys) {
		return priceSysMapper.updatePriceSys(priceSys);
	}

	@Override
	public boolean deletePriceSys(Integer price_id) {
		return priceSysMapper.deletePriceSys(price_id);
	}

	@Override
	public PriceSys getPriceSys(Map<String, Object> map) {
		return priceSysMapper.getPriceSys(map);
	}

	@Override
	public List<PriceSys> getPriceSysList(Map<String, Object> map) {
		return priceSysMapper.getPriceSysList(map);
	}

	@Override
	public Integer getPriceSysNumber(Map<String, Object> map) {
		return priceSysMapper.getPriceSysNumber(map);
	}

}
