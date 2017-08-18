package com.jingren.jing.common.organization.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.common.organization.bean.Organization;
import com.jingren.jing.common.organization.dao.OrganizationMapper;
@Service
public class OrganizationServiceImpl implements OrganizationService{
	@Resource
	private OrganizationMapper oranizationMapper;

	@Override
	public boolean saveOranization(Organization organization) {
		return oranizationMapper.saveOranization(organization);
	}

	@Override
	public boolean updateOranization(Organization organization) {
		return oranizationMapper.updateOranization(organization);
	}

	@Override
	public boolean deleteOranization(Integer or_id) {
		return oranizationMapper.deleteOranization(or_id);
	}

	@Override
	public Organization getOranization(Map<String, Object> map) {
		return oranizationMapper.getOranization(map);
	}

	@Override
	public List<Organization> getOranizationList(Map<String, Object> map) {
		return oranizationMapper.getOranizationList(map);
	}

	@Override
	public Integer getOranizationNumber(Map<String, Object> map) {
		return oranizationMapper.getOranizationNumber(map);
	}

}
