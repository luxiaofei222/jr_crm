package com.jingren.jing.recruit.service.chengkaoyuanxiao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.recruit.bean.chengkaoyuanxiao.CKZhaoSheng;
import com.jingren.jing.recruit.dao.chengkaoyuanxiao.CKZhaoShengMapper;
@Service
public class CKZhaoShengServiceImpl implements CKZhaoShengService {

	@Resource
	private CKZhaoShengMapper ckZhaoShengMapper;
	@Override
	public boolean saveCKZhaoSheng(CKZhaoSheng ckZhaoSheng) {
		return ckZhaoShengMapper.saveCKZhaoSheng(ckZhaoSheng);
	}

	@Override
	public boolean updateCKZhaoSheng(CKZhaoSheng ckZhaoSheng) {
		return ckZhaoShengMapper.updateCKZhaoSheng(ckZhaoSheng);
	}

	@Override
	public boolean deleteCKZhaoSheng(Integer zhaosheng_id) {
		return ckZhaoShengMapper.deleteCKZhaoSheng(zhaosheng_id);
	}

	@Override
	public CKZhaoSheng getCKZhaoSheng(Map<String, Object> map) {
		return ckZhaoShengMapper.getCKZhaoSheng(map);
	}

	@Override
	public List<CKZhaoSheng> getCKZhaoShengList(Map<String, Object> map) {
		return ckZhaoShengMapper.getCKZhaoShengList(map);
	}

	@Override
	public Integer getCKZhaoShengNumber(Map<String, Object> map) {
		return ckZhaoShengMapper.getCKZhaoShengNumber(map);
	}

}
