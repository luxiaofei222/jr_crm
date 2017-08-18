package com.jingren.jing.school.service.message;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.school.bean.message.PrivateMessage;
import com.jingren.jing.school.dao.message.PrivateMessageMapper;
@Service
public class PrivateMessageServiceImpl implements PrivateMessageService {
	@Resource
	private PrivateMessageMapper privateMessageMapper;

	@Override
	public boolean savePrivateMessage(PrivateMessage privateMessage) {
		return privateMessageMapper.savePrivateMessage(privateMessage);
	}

	@Override
	public boolean deletePrivateMessage(Integer message_id) {
		return privateMessageMapper.deletePrivateMessage(message_id);
	}

	@Override
	public PrivateMessage getPrivateMessage(Map<String, Object> map) {
		return privateMessageMapper.getPrivateMessage(map);
	}

	@Override
	public List<PrivateMessage> getPrivateMessageList(Map<String, Object> map) {
		return privateMessageMapper.getPrivateMessageList(map);
	}

	@Override
	public Integer getPrivateMessageNumber(Map<String, Object> map) {
		return privateMessageMapper.getPrivateMessageNumber(map);
	}

	@Override
	public boolean updatePrivateMessage(PrivateMessage privateMessage) {
		return privateMessageMapper.updatePrivateMessage(privateMessage);
	}

	@Override
	public List<PrivateMessage> getPrivateMessageLianxiList(Map<String, Object> map) {
		return privateMessageMapper.getPrivateMessageLianxiList(map);
	}

}
