package com.jingren.jing.oa.service.oa_file;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.oa.bean.oa_file.OaFile;
import com.jingren.jing.oa.dao.oa_file.OaFileMapper;
@Service
public class OaFileServiceImpl implements OaFileService {

	@Resource
	private OaFileMapper oaFileMapper;
	@Override
	public boolean saveOaFile(OaFile oaFile) {
		return oaFileMapper.saveOaFile(oaFile);
	}

	@Override
	public boolean deleteOaFile(int file_id) {
		return oaFileMapper.deleteOaFile(file_id);
	}

	@Override
	public List<OaFile> getOaFileList(Map<String, Object> map) {
		return oaFileMapper.getOaFileList(map);
	}

	@Override
	public Integer getOaFileNumber(Map<String, Object> map) {
		return oaFileMapper.getOaFileNumber(map);
	}

	@Override
	public boolean updateOaFile(OaFile oaFile) {
		return oaFileMapper.updateOaFile(oaFile);
	}

}
