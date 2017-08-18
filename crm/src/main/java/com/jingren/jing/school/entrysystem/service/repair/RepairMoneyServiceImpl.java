package com.jingren.jing.school.entrysystem.service.repair;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.school.entrysystem.bean.repair.RepairMoney;
import com.jingren.jing.school.entrysystem.dao.repair.RepairMoneyMapper;
@Service
public class RepairMoneyServiceImpl implements RepairMoneyService {

	@Resource
	private RepairMoneyMapper RepairMoneyMapper;
	@Override
	public boolean saveRepairMoney(RepairMoney repairMoney) {
		return RepairMoneyMapper.saveRepairMoney(repairMoney);
	}

	@Override
	public boolean updateRepairMoney(RepairMoney repairMoney) {
		return RepairMoneyMapper.updateRepairMoney(repairMoney);
	}

	@Override
	public boolean deleteRepairMoney(Integer repair_id) {
		return RepairMoneyMapper.deleteRepairMoney(repair_id);
	}

	@Override
	public RepairMoney getRepairMoney(Map<String, Object> map) {
		return RepairMoneyMapper.getRepairMoney(map);
	}

	@Override
	public List<RepairMoney> getRepairMoneyList(Map<String, Object> map) {
		return RepairMoneyMapper.getRepairMoneyList(map);
	}

}
