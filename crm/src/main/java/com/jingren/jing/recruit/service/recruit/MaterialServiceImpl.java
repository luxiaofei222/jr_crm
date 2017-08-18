package com.jingren.jing.recruit.service.recruit;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.recruit.bean.material.Material;
import com.jingren.jing.recruit.dao.material.MaterialMapper;
@Service
public class MaterialServiceImpl implements MaterialService {

	@Resource
	private MaterialMapper materialMapper;
	@Override
	public boolean saveMaterial(Material material) {
		return materialMapper.saveMaterial(material);
	}

	@Override
	public boolean updateMaterial(Material material) {
		return materialMapper.updateMaterial(material);
	}

	@Override
	public boolean deleteMaterial(Map<String, Object> map) {
		return materialMapper.deleteMaterial(map);
	}

	@Override
	public Material getMaterial(Map<String, Object> map) {
		return materialMapper.getMaterial(map);
	}

	@Override
	public List<Material> getMaterialList(Map<String, Object> map) {
		return materialMapper.getMaterialList(map);
	}

	@Override
	public Integer getMaterialNumber(Map<String, Object> map) {
		return materialMapper.getMaterialNumber(map);
	}

}
