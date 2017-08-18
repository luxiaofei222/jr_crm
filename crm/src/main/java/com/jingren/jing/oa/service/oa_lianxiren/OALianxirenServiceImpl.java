package com.jingren.jing.oa.service.oa_lianxiren;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.oa.bean.oa_lianxiren.OALianxiren;
import com.jingren.jing.oa.dao.oa_lianxiren.OALianxirenMapper;
@Service
public class OALianxirenServiceImpl implements OALianxirenService {

	@Resource
	private OALianxirenMapper oALianxirenMapper;
	@Override
	public boolean saveOALianxiren(OALianxiren oaLianxiren) {
		return oALianxirenMapper.saveOALianxiren(oaLianxiren);
	}

	@Override
	public boolean updateOALianxiren(OALianxiren oaLianxiren) {
		return oALianxirenMapper.updateOALianxiren(oaLianxiren);
	}

	@Override
	public List<OALianxiren> getOALianxirenList(Map<String, Object> map) {
		return oALianxirenMapper.getOALianxirenList(map);
	}

}
