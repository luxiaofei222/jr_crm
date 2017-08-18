package com.jingren.jing.school.service.message;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.school.bean.message.JRMessage;
import com.jingren.jing.school.dao.message.MessageMapper;
@Service
public class MessageServiceImpl implements MessageService{
	@Resource
	private MessageMapper messageMapper;

	@Override
	public boolean saveMessage(JRMessage jrMessage) {
		return messageMapper.saveMessage(jrMessage);
	}

	@Override
	public boolean deleteMessage(Integer message_id) {
		return messageMapper.deleteMessage(message_id);
	}

	@Override
	public JRMessage getMessage(Map<String, Object> map) {
		return messageMapper.getMessage(map);
	}

	@Override
	public List<JRMessage> getMessageList(Map<String, Object> map) {
		return messageMapper.getMessageList(map);
	}

	@Override
	public Integer getMessageNumber(Map<String, Object> map) {
		return messageMapper.getMessageNumber(map);
	}

	@Override
	public boolean updateJrmessage(JRMessage jrMessage) {
		return messageMapper.updateJrmessage(jrMessage);
	}

}
