package com.jingren.jing.oa.service.oa_edu;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.oa.bean.oa_edu.OAEdu;
import com.jingren.jing.oa.dao.oa_edu.OAEduMapper;
@Service
public class OAEduServiceImpl implements OAEduService{
	@Resource
	private OAEduMapper oAEduMapper;

	@Override
	public boolean saveOAEdu(OAEdu oaEdu) {
		return oAEduMapper.saveOAEdu(oaEdu);
	}

	@Override
	public boolean updateOAEdu(OAEdu oaEdu) {
		return oAEduMapper.updateOAEdu(oaEdu);
	}

	@Override
	public List<OAEdu> getOAEduList(Map<String, Object> map) {
		return oAEduMapper.getOAEduList(map);
	}

}
