package com.jingren.jing.educational.service.family;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.educational.bean.family.EntryFamily;
import com.jingren.jing.educational.dao.family.EntryFamilyMapper;
@Service
public class EntryFamilyServiceImpl implements EntryFamilyService {
	@Resource
	private EntryFamilyMapper entryFamilyMapper;

	@Override
	public boolean saveEntryFamily(EntryFamily entryFamily) {
		return entryFamilyMapper.saveEntryFamily(entryFamily);
	}

	@Override
	public boolean deleteEntryFamily(Integer entry_infoid) {
		return entryFamilyMapper.deleteEntryFamily(entry_infoid);
	}

	@Override
	public List<EntryFamily> getEntryFamilyList(Integer entry_infoid) {
		return entryFamilyMapper.getEntryFamilyList(entry_infoid);
	}

	@Override
	public boolean updateEntryFamily(EntryFamily entryFamily) {
		return entryFamilyMapper.updateEntryFamily(entryFamily);
	}

}
