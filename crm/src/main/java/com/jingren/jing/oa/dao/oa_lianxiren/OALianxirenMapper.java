package com.jingren.jing.oa.dao.oa_lianxiren;

import java.util.List;
import java.util.Map;

import com.jingren.jing.oa.bean.oa_lianxiren.OALianxiren;

public interface OALianxirenMapper {
	/**
	 * @Title: OALianxirenMapper.java
	 * @Package com.jingren.jing.oa.dao.oa_lianxiren
	 * @Description: TODO 保存联系人信息
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年3月1日 下午4:03:04
	 * @version 网校+CRM系统 V1.0
	 */
	boolean saveOALianxiren(OALianxiren oaLianxiren);

	/**
	 * @Title: OALianxirenMapper.java
	 * @Package com.jingren.jing.oa.dao.oa_lianxiren
	 * @Description: TODO 修改联系人信息
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年3月1日 下午4:03:15
	 * @version 网校+CRM系统 V1.0
	 */
	boolean updateOALianxiren(OALianxiren oaLianxiren);

	/**
	 * @Title: OALianxirenMapper.java
	 * @Package com.jingren.jing.oa.dao.oa_lianxiren
	 * @Description: TODO 获取联系人信息
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年3月1日 下午4:03:28
	 * @version 网校+CRM系统 V1.0
	 */
	List<OALianxiren> getOALianxirenList(Map<String, Object> map);
}
