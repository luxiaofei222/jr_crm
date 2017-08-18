package com.jingren.jing.school.service.message;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.school.bean.message.Annexes;
import com.jingren.jing.school.dao.message.AnnexesMapper;

/**
 * 附件
* @Title: AnnexesServiceImpl.java 
* @Package com.jingren.jing.school.service.message 
* @Description: TODO
* @author 鲁晓飞 MR.Lu   
* @date 2016年11月28日 上午11:32:14 
* @version 网校+CRM系统 V1.0
 */
@Service
public class AnnexesServiceImpl implements AnnexesService{
	@Resource
	private AnnexesMapper annexesMapper;

	@Override
	public boolean saveAnnexes(Annexes annexes) {
		return annexesMapper.saveAnnexes(annexes);
	}

	@Override
	public boolean deleteAnnexes(Integer message_id) {
		return annexesMapper.deleteAnnexes(message_id);
	}

	@Override
	public List<Annexes> getAnnexesList(Map<String, Object> map) {
		return annexesMapper.getAnnexesList(map);
	}

	@Override
	public Integer getAnnexesNumber(Map<String, Object> map) {
		return annexesMapper.getAnnexesNumber(map);
	}

	
}
