package com.jingren.jing.recruit.service.xueli_baoming;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.recruit.bean.xueli_baoming.XueLiBaoMing;
import com.jingren.jing.recruit.dao.xueli_baoming.XueLiBaoMingMapper;

@Service
public class XueLiBaoMingServiceImpl implements XueLiBaoMingService {

	@Resource
	private XueLiBaoMingMapper xueLiBaoMingMapper;
	@Override
	public boolean saveXueLiBaoMing(XueLiBaoMing xueLiBaoMing) {
		return xueLiBaoMingMapper.saveXueLiBaoMing(xueLiBaoMing);
	}

	@Override
	public boolean deleteXueLiBaoMing(Map<String, Object> map) {
		return xueLiBaoMingMapper.deleteXueLiBaoMing(map);
	}

	@Override
	public XueLiBaoMing getXueLiBaoMing(Map<String, Object> map) {
		return xueLiBaoMingMapper.getXueLiBaoMing(map);
	}

	@Override
	public List<XueLiBaoMing> getXueLiBaoMingList(Map<String, Object> map) {
		return xueLiBaoMingMapper.getXueLiBaoMingList(map);
	}

	@Override
	public Integer getXueLiBaoMingNumber(Map<String, Object> map) {
		return xueLiBaoMingMapper.getXueLiBaoMingNumber(map);
	}

}
