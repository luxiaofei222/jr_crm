package com.jingren.jing.crm.service.mycompany;

import java.util.List;
import java.util.Map;

import com.jingren.jing.crm.bean.customer.Customer;
import com.jingren.jing.crm.bean.mycompany.MyCompany;

public interface MyCompanyService {
	/**
	* @Title: MyCompanyMapper.java 
	* @Package com.jingren.jing.crm.dao.mycompany 
	* @Description: TODO 保存企业信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月16日 上午9:27:52 
	* @version 网校+CRM系统 V1.0
	 */
	boolean saveMyCompany(MyCompany myCompany);
	/**
	* @Title: MyCompanyMapper.java 
	* @Package com.jingren.jing.crm.dao.mycompany 
	* @Description: TODO 删除我得企业信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月16日 上午9:29:20 
	* @version 网校+CRM系统 V1.0
	 */
	boolean deleteCompany(Integer my_company_id);
	/**
	* @Title: MyCompanyMapper.java 
	* @Package com.jingren.jing.crm.dao.mycompany 
	* @Description: TODO 获取企业信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月16日 上午9:30:43 
	* @version 网校+CRM系统 V1.0
	 */
	MyCompany getMyCompany(Map<String, Object> map);
	/**
	* @Title: MyCompanyMapper.java 
	* @Package com.jingren.jing.crm.dao.mycompany 
	* @Description: TODO 获取企业信息列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月16日 上午9:31:48 
	* @version 网校+CRM系统 V1.0
	 */
	List<MyCompany> getMyCompanyList(Map<String, Object> map);
	/**
	* @Title: MyCompanyMapper.java 
	* @Package com.jingren.jing.crm.dao.mycompany 
	* @Description: TODO 我得企业库数量
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月16日 上午9:33:09 
	* @version 网校+CRM系统 V1.0
	 */
	Integer getMycompanyNumber(Map<String, Object> map);
	/**
	* @Title: MyCompanyMapper.java 
	* @Package com.jingren.jing.crm.dao.mycompany 
	* @Description: TODO 修改我的企业信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年5月22日 下午2:44:47 
	* @version 网校+CRM系统 V1.0
	 */
	boolean updateMyCompany(MyCompany myCompany);
	/**
	* @Title: MyCompanyMapper.java 
	* @Package com.jingren.jing.crm.dao.mycompany 
	* @Description: TODO 删除我得企业信息-通过员工
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月16日 上午9:29:20 
	* @version 网校+CRM系统 V1.0
	 */
	boolean deleteCompanyByEmployee_id(Integer employee_id);
}
