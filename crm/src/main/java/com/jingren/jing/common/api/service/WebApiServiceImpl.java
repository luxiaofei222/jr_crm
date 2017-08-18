package com.jingren.jing.common.api.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.common.api.bean.WebApi;
import com.jingren.jing.common.api.dao.WebApiMapper;
@Service
public class WebApiServiceImpl implements WebApiService {

	@Resource
	private WebApiMapper webApiMapper;
	@Override
	public boolean saveWebApi(WebApi webApi) {
		return webApiMapper.saveWebApi(webApi);
	}

	@Override
	public List<WebApi> getWebApiList(Map<String, Object> map) {
		return webApiMapper.getWebApiList(map);
	}

}
