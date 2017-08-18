package com.jingren.jing.school.service.say;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.school.bean.say.EmployeeSay;
import com.jingren.jing.school.dao.say.EmployeeSayMapper;
@Service
public class EmployeeSayServiceImpl implements EmployeeSayService{
	@Resource
	private EmployeeSayMapper employeeSayMapper;

	@Override
	public boolean saveEmployeeSay(EmployeeSay employeeSay) {
		return employeeSayMapper.saveEmployeeSay(employeeSay);
	}

	@Override
	public boolean updateEmployeeSay(EmployeeSay employeeSay) {
		return employeeSayMapper.updateEmployeeSay(employeeSay);
	}

	@Override
	public boolean deleteEmployeeSay(Integer say_id) {
		return employeeSayMapper.deleteEmployeeSay(say_id);
	}

	@Override
	public EmployeeSay getEmployeeSay(Map<String, Object> map) {
		return employeeSayMapper.getEmployeeSay(map);
	}

	@Override
	public List<EmployeeSay> getEmployeeSayList(Map<String, Object> map) {
		return employeeSayMapper.getEmployeeSayList(map);
	}

	@Override
	public Integer getEmployeeSayNumber(Map<String, Object> map) {
		return employeeSayMapper.getEmployeeSayNumber(map);
	}

}
