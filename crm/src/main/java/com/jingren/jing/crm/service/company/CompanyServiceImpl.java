package com.jingren.jing.crm.service.company;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.crm.bean.company.Company;
import com.jingren.jing.crm.dao.company.CompanyMapper;
@Service
public class CompanyServiceImpl implements CompanyService{
	@Resource
	private CompanyMapper companyMapper;

	@Override
	public boolean saveCompany(Company company) {
		return companyMapper.saveCompany(company);
	}

	@Override
	public boolean updateCompany(Company company) {
		return companyMapper.updateCompany(company);
	}

	@Override
	public boolean deleteCompany(Integer company_id) {
		return companyMapper.deleteCompany(company_id);
	}

	@Override
	public Company getCompany(Map<String, Object> map) {
		return companyMapper.getCompany(map);
	}

	@Override
	public List<Company> getCompanyList(Map<String, Object> map) {
		return companyMapper.getCompanyList(map);
	}

	@Override
	public Integer getCompanyNumber(Map<String, Object> map) {
		return companyMapper.getCompanyNumber(map);
	}

	@Override
	public Company getCompanyById(Integer company_id) {
		return companyMapper.getCompanyById(company_id);
	}

}
