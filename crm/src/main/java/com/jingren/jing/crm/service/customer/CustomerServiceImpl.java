package com.jingren.jing.crm.service.customer;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.crm.bean.customer.Customer;
import com.jingren.jing.crm.dao.customer.CustomerMapper;
@Service
public class CustomerServiceImpl implements CustomerService {
	@Resource
	private CustomerMapper customerMapper;

	@Override
	public boolean saveCustomer(Customer customer) {
		return customerMapper.saveCustomer(customer);
	}

	@Override
	public boolean updateCustomer(Customer customer) {
		return customerMapper.updateCustomer(customer);
	}

	@Override
	public boolean deleteCustomer(Integer customer_id) {
		return customerMapper.deleteCustomer(customer_id);
	}

	@Override
	public Customer getCustomer(Map<String, Object> map) {
		return customerMapper.getCustomer(map);
	}

	@Override
	public List<Customer> getCustomerList(Map<String, Object> map) {
		return customerMapper.getCustomerList(map);
	}

	@Override
	public Integer getCustomerNumber(Map<String, Object> map) {
		return customerMapper.getCustomerNumber(map);
	}

	@Override
	public Customer getCustomerByid(Integer customer_id) {
		return customerMapper.getCustomerByid(customer_id);
	}

	@Override
	public boolean updateCustomerLizhi(Integer employee_id) {
		return customerMapper.updateCustomerLizhi(employee_id);
	}

}
