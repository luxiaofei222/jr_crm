package com.jingren.jing.school.service.myshoping;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.school.bean.myshoping.MyShoping;
import com.jingren.jing.school.dao.myshoping.MyShopingMapper;
@Service
public class MyShopingServiceImpl implements MyShopingService{
	@Resource
	private MyShopingMapper myShopingMapper;

	@Override
	public boolean saveMyShoping(MyShoping MyShoping) {
		return myShopingMapper.saveMyShoping(MyShoping);
	}

	@Override
	public boolean deleteMyShoping(Integer MyShoping_id) {
		return myShopingMapper.deleteMyShoping(MyShoping_id);
	}

	@Override
	public MyShoping getMyShoping(Map<String, Object> map) {
		return myShopingMapper.getMyShoping(map);
	}

	@Override
	public List<MyShoping> getMyShopingList(Map<String, Object> map) {
		return myShopingMapper.getMyShopingList(map);
	}

	@Override
	public Integer getMyShopingNumber(Map<String, Object> map) {
		return myShopingMapper.getMyShopingNumber(map);
	}

	@Override
	public boolean deleteMyshopingForPay(Map<String, Object> map) {
		return myShopingMapper.deleteMyshopingForPay(map);
	}

}
