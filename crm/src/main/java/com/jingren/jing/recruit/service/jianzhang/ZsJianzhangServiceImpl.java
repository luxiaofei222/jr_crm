package com.jingren.jing.recruit.service.jianzhang;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.recruit.bean.jianzhang.ZsJianzhang;
import com.jingren.jing.recruit.dao.jianzhang.ZsJianzhangMapper;
@Service
public class ZsJianzhangServiceImpl implements ZsJianzhangService{
	@Resource
	private ZsJianzhangMapper zsJianzhangMapper;

	@Override
	public boolean saveZsJianzhang(ZsJianzhang zsJianzhang) {
		return zsJianzhangMapper.saveZsJianzhang(zsJianzhang);
	}

	@Override
	public boolean updateZsJianzhang(ZsJianzhang zsJianzhang) {
		return zsJianzhangMapper.updateZsJianzhang(zsJianzhang);
	}

	@Override
	public boolean deleteZsJianzhang(ZsJianzhang zsJianzhang) {
		return zsJianzhangMapper.deleteZsJianzhang(zsJianzhang);
	}

	@Override
	public ZsJianzhang getZsJianzhang(Map<String, Object> map) {
		return zsJianzhangMapper.getZsJianzhang(map);
	}

	@Override
	public List<ZsJianzhang> getZsJianzhangList(Map<String, Object> map) {
		return zsJianzhangMapper.getZsJianzhangList(map);
	}

	@Override
	public Integer getZsJianzhangNumber(Map<String, Object> map) {
		return zsJianzhangMapper.getZsJianzhangNumber(map);
	}

}
