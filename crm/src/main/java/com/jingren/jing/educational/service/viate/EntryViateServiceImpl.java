package com.jingren.jing.educational.service.viate;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.educational.bean.viate.EntryViate;
import com.jingren.jing.educational.dao.viate.EntryViateMapper;
@Service
public class EntryViateServiceImpl implements EntryViateService {

	@Resource
	private EntryViateMapper entryViateMapper;
	@Override
	public boolean saveEntryViate(EntryViate entryViate) {
		return entryViateMapper.saveEntryViate(entryViate);
	}

	@Override
	public boolean deleteEntryViate(Integer entry_info_id) {
		return entryViateMapper.deleteEntryViate(entry_info_id);
	}

	@Override
	public List<EntryViate> getEntryViateList(Integer entry_info_id) {
		return entryViateMapper.getEntryViateList(entry_info_id);
	}

	@Override
	public boolean updateEntryViate(EntryViate entryViate) {
		return entryViateMapper.updateEntryViate(entryViate);
	}

}
