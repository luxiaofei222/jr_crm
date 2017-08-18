package com.jingren.jing.oa.service.oa_file;

import java.util.List;
import java.util.Map;

import com.jingren.jing.oa.bean.oa_file.OaFile;

public interface OaFileService {

	/**
	* @Title: OaFileMapper.java 
	* @Package com.jingren.jing.oa.dao.oa_file 
	* @Description: TODO 保存文件
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年3月31日 上午11:24:29 
	* @version 网校+CRM系统 V1.0
	 */
	boolean saveOaFile(OaFile oaFile);
	/**
	* @Title: OaFileService.java 
	* @Package com.jingren.jing.oa.service.oa_file 
	* @Description: TODO 修改文件信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年3月31日 下午6:15:42 
	* @version 网校+CRM系统 V1.0
	 */
	boolean updateOaFile(OaFile oaFile);
	/**
	* @Title: OaFileMapper.java 
	* @Package com.jingren.jing.oa.dao.oa_file 
	* @Description: TODO 删除文件
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年3月31日 上午11:25:02 
	* @version 网校+CRM系统 V1.0
	 */
	boolean deleteOaFile(int file_id);
	/**
	* @Title: OaFileMapper.java 
	* @Package com.jingren.jing.oa.dao.oa_file 
	* @Description: TODO 文件列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年3月31日 上午11:25:30 
	* @version 网校+CRM系统 V1.0
	 */
	List<OaFile> getOaFileList(Map<String, Object> map);
	/**
	* @Title: OaFileMapper.java 
	* @Package com.jingren.jing.oa.dao.oa_file 
	* @Description: TODO 文件数量
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年3月31日 上午11:25:59 
	* @version 网校+CRM系统 V1.0
	 */
	Integer getOaFileNumber(Map<String, Object> map);
}
