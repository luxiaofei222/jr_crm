package com.jingren.jing.recruit.service.help;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.recruit.bean.help.HelpCenter;
import com.jingren.jing.recruit.dao.help.HelpCenterMapper;
@Service
public class HelpCenterServiceImpl implements HelpCenterService {

	@Resource
	private HelpCenterMapper helpCenterMapper;
	@Override
	public boolean saveHelpCenter(HelpCenter helpCenter) {
		return helpCenterMapper.saveHelpCenter(helpCenter);
	}

	@Override
	public boolean updateHelpCenter(HelpCenter helpCenter) {
		return helpCenterMapper.updateHelpCenter(helpCenter);
	}

	@Override
	public boolean deleteHelpCenter(Map<String, Object> map) {
		return helpCenterMapper.deleteHelpCenter(map);
	}

	@Override
	public HelpCenter getHelpCenter(Map<String, Object> map) {
		return helpCenterMapper.getHelpCenter(map);
	}

	@Override
	public List<HelpCenter> getHelpCenterList(Map<String, Object> map) {
		return helpCenterMapper.getHelpCenterList(map);
	}

	@Override
	public Integer getHelpCenterNumber(Map<String, Object> map) {
		return helpCenterMapper.getHelpCenterNumber(map);
	}

}
