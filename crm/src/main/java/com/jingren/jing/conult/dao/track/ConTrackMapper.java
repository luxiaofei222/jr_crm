package com.jingren.jing.conult.dao.track;

import java.util.List;
import java.util.Map;

import com.jingren.jing.conult.bean.track.ConTrack;

public interface ConTrackMapper {

	/**
	* @Title: ConTrackMapper.java 
	* @Package com.jingren.jing.conult.dao.track 
	* @Description: TODO 保存追踪记录
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年8月4日 下午5:30:58 
	* @version 网校+CRM系统 V1.0
	 */
	boolean saveConTrack(ConTrack conTrack);
	/**
	* @Title: ConTrackMapper.java 
	* @Package com.jingren.jing.conult.dao.track 
	* @Description: TODO 删除追踪记录
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年8月4日 下午5:31:34 
	* @version 网校+CRM系统 V1.0
	 */
	boolean deleteConTrack(Integer contrack_id);
	/**
	* @Title: ConTrackMapper.java 
	* @Package com.jingren.jing.conult.dao.track 
	* @Description: TODO 追踪信息列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年8月4日 下午5:32:04 
	* @version 网校+CRM系统 V1.0
	 */
	List<ConTrack> getConTrackList(Map<String, Object> map);
}
