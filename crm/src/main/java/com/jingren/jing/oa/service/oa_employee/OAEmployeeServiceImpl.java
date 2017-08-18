package com.jingren.jing.oa.service.oa_employee;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.oa.bean.oa_employee.OAEmployee;
import com.jingren.jing.oa.dao.oa_employee.OAEmployeeMapper;
@Service
public class OAEmployeeServiceImpl implements OAEmployeeService {

	@Resource
	private OAEmployeeMapper oAEmployeeMapper;
	@Override
	public boolean saveOAEmployee(OAEmployee oaEmployee) {
		return oAEmployeeMapper.saveOAEmployee(oaEmployee);
	}

	@Override
	public boolean updateOAEmployee(OAEmployee oaEmployee) {
		return oAEmployeeMapper.updateOAEmployee(oaEmployee);
	}

	@Override
	public boolean deleteOAEmployee(Integer oa_employee_id) {
		return oAEmployeeMapper.deleteOAEmployee(oa_employee_id);
	}

	@Override
	public OAEmployee getOAEmployee(Map<String, Object> map) {
		return oAEmployeeMapper.getOAEmployee(map);
	}

	@Override
	public List<OAEmployee> getOAEmployeeList(Map<String, Object> map) {
		return oAEmployeeMapper.getOAEmployeeList(map);
	}

	@Override
	public Integer getOAEmployeeNumber(Map<String, Object> map) {
		return oAEmployeeMapper.getOAEmployeeNumber(map);
	}

}
