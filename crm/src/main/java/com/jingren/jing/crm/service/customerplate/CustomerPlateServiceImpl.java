package com.jingren.jing.crm.service.customerplate;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.crm.bean.customerplate.CustomerPlate;
import com.jingren.jing.crm.dao.customerplate.CustomerPlateMapper;
@Service
public class CustomerPlateServiceImpl implements CustomerPlateService{
	@Resource
	private CustomerPlateMapper customerPlateMapper;

	@Override
	public boolean saveCustomerPlate(CustomerPlate customerPlate) {
		return customerPlateMapper.saveCustomerPlate(customerPlate);
	}

	@Override
	public boolean updateCustomerPlate(CustomerPlate customerPlate) {
		return customerPlateMapper.updateCustomerPlate(customerPlate);
	}

	@Override
	public boolean deleteCustomerPlate(Integer customerPlate_id) {
		return customerPlateMapper.deleteCustomerPlate(customerPlate_id);
	}

	@Override
	public CustomerPlate getCustomerPlate(Map<String, Object> map) {
		return customerPlateMapper.getCustomerPlate(map);
	}

	@Override
	public List<CustomerPlate> getCustomerPlateList(Map<String, Object> map) {
		return customerPlateMapper.getCustomerPlateList(map);
	}

	@Override
	public Integer getCustomerPlateNumber(Map<String, Object> map) {
		return customerPlateMapper.getCustomerPlateNumber(map);
	}

}
