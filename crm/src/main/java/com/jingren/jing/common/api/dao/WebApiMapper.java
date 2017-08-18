package com.jingren.jing.common.api.dao;

import java.util.List;
import java.util.Map;

import com.jingren.jing.common.api.bean.WebApi;

public interface WebApiMapper {

	boolean saveWebApi(WebApi webApi);
	
	List<WebApi> getWebApiList(Map<String, Object> map);
}
