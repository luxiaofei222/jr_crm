package com.jingren.jing.school.service.employee;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.school.bean.employee.Employee;
import com.jingren.jing.school.dao.employee.EmployeeMapper;
@Service
public class EmployeeServiceImpl implements EmployeeService{
	@Resource
	private EmployeeMapper employeeMapper;

	@Override
	public boolean saveEmployee(Employee employee) {
		return employeeMapper.saveEmployee(employee);
	}

	@Override
	public boolean updateEmployee(Employee employee) {
		return employeeMapper.updateEmployee(employee);
	}

	@Override
	public boolean deleteEmployee(Integer employee_id) {
		return employeeMapper.deleteEmployee(employee_id);
	}

	@Override
	public List<Employee> getEmployeeList(Map<String, Object> map) {
		return employeeMapper.getEmployeeList(map);
	}

	@Override
	public Integer getEmployeeNumber(Map<String, Object> map) {
		return employeeMapper.getEmployeeNumber(map);
	}

	@Override
	public Employee getEmployee(Map<String, Object> map) {
		return employeeMapper.getEmployee(map);
	}

	@Override
	public Employee getEmployeeByid(Integer employee_id) {
		return employeeMapper.getEmployeeByid(employee_id);
	}

	@Override
	public String getEmployeeJobnumber() {
		return employeeMapper.getEmployeeJobnumber();
	}

}
