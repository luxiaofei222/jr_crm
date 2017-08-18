package com.jingren.jing.school.service.classtype;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.school.bean.classtype.ClassType;
import com.jingren.jing.school.dao.classtype.ClassTypeMapper;
@Service
public class ClassTypeServiceImpl implements ClassTypeService{
	@Resource
	private ClassTypeMapper classTypeMapper;

	@Override
	public boolean saveClassType(ClassType classType) {
		return classTypeMapper.saveClassType(classType);
	}

	@Override
	public boolean updateClassType(ClassType classType) {
		return classTypeMapper.updateClassType(classType);
	}

	@Override
	public boolean deleteClassType(Integer class_id) {
		return classTypeMapper.deleteClassType(class_id);
	}

	@Override
	public ClassType getClassType(Map<String, Object> map) {
		return classTypeMapper.getClassType(map);
	}

	@Override
	public List<ClassType> getClassTypeList(Map<String, Object> map) {
		return classTypeMapper.getClassTypeList(map);
	}

	@Override
	public Integer getClassTypeNumber(Map<String, Object> map) {
		return classTypeMapper.getClassTypeNumber(map);
	}

}
