package com.jingren.jing.crm.service.mycompany;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.crm.bean.customer.Customer;
import com.jingren.jing.crm.bean.mycompany.MyCompany;
import com.jingren.jing.crm.dao.mycompany.MyCompanyMapper;
@Service
public class MyCompanyServiceImpl implements MyCompanyService{

	@Resource
	private MyCompanyMapper myCompanyMapper;
	@Override
	public boolean saveMyCompany(MyCompany myCompany) {
		return myCompanyMapper.saveMyCompany(myCompany);
	}

	@Override
	public boolean deleteCompany(Integer my_company_id) {
		return myCompanyMapper.deleteCompany(my_company_id);
	}

	@Override
	public MyCompany getMyCompany(Map<String, Object> map) {
		return myCompanyMapper.getMyCompany(map);
	}

	@Override
	public List<MyCompany> getMyCompanyList(Map<String, Object> map) {
		return myCompanyMapper.getMyCompanyList(map);
	}

	@Override
	public Integer getMycompanyNumber(Map<String, Object> map) {
		return myCompanyMapper.getMycompanyNumber(map);
	}

	@Override
	public boolean updateMyCompany(MyCompany myCompany) {
		return myCompanyMapper.updateMyCompany(myCompany);
	}

	@Override
	public boolean deleteCompanyByEmployee_id(Integer employee_id) {
		return myCompanyMapper.deleteCompanyByEmployee_id(employee_id);
	}


}
