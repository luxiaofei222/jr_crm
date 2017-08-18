package com.jingren.jing.crm.dao.company;

import java.util.List;
import java.util.Map;

import com.jingren.jing.crm.bean.company.Company;

public interface CompanyMapper {

	/**
	* @Title: CompanyMapper.java 
	* @Package com.jingren.jing.crm.dao.company 
	* @Description: TODO 添加企业信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月16日 下午5:05:30 
	* @version 网校+CRM系统 V1.0
	 */
	boolean saveCompany(Company company);
	/**
	* @Title: CompanyMapper.java 
	* @Package com.jingren.jing.crm.dao.company 
	* @Description: TODO修改企业信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月16日 下午5:05:55 
	* @version 网校+CRM系统 V1.0
	 */
	boolean updateCompany(Company company);
	/**
	* @Title: CompanyMapper.java 
	* @Package com.jingren.jing.crm.dao.company 
	* @Description: TODO 删除企业信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月16日 下午5:06:25 
	* @version 网校+CRM系统 V1.0
	 */
	boolean deleteCompany(Integer company_id);
	/**
	* @Title: CompanyMapper.java 
	* @Package com.jingren.jing.crm.dao.company 
	* @Description: TODO 获取企业信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月16日 下午5:06:54 
	* @version 网校+CRM系统 V1.0
	 */
	Company getCompany(Map<String, Object> map);
	/**
	* @Title: CompanyMapper.java 
	* @Package com.jingren.jing.crm.dao.company 
	* @Description: TODO 获取企业信息列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月16日 下午5:32:12 
	* @version 网校+CRM系统 V1.0
	 */
	List<Company> getCompanyList(Map<String, Object> map);
	/**
	* @Title: CompanyMapper.java 
	* @Package com.jingren.jing.crm.dao.company 
	* @Description: TODO 企业信息数量
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月16日 下午5:32:47 
	* @version 网校+CRM系统 V1.0
	 */
	Integer getCompanyNumber(Map<String, Object> map);
	/**
	* @Title: CompanyMapper.java 
	* @Package com.jingren.jing.crm.dao.company 
	* @Description: TODO 通过ID获取企业信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月16日 上午11:03:00 
	* @version 网校+CRM系统 V1.0
	 */
	Company getCompanyById(Integer company_id);
}
