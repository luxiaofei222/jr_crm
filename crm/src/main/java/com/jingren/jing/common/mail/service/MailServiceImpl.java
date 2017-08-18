package com.jingren.jing.common.mail.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.common.mail.bean.MailBean;
import com.jingren.jing.common.mail.dao.MailMapper;
@Service
public class MailServiceImpl implements MailService{
	@Resource
	private MailMapper mailMapper;

	public MailBean getMailBean(Integer type) {
		
		return mailMapper.getMailBean(type);
	}

}
