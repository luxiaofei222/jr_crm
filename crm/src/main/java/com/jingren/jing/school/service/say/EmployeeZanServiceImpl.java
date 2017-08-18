package com.jingren.jing.school.service.say;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.school.bean.say.EmployeeZan;
import com.jingren.jing.school.dao.say.EmployeeZanMapper;
@Service
public class EmployeeZanServiceImpl implements EmployeeZanService {

	@Resource
	private EmployeeZanMapper employeeZanMapper;
	@Override
	public boolean saveEmployeeZan(EmployeeZan employeeZan) {
		return employeeZanMapper.saveEmployeeZan(employeeZan);
	}
	@Override
	public List<EmployeeZan> getemployeeZans(Map<String, Object> map) {
		return employeeZanMapper.getemployeeZans(map);
	}

}
