package com.jingren.jing.oa.bean.oa_file;

import java.io.Serializable;
import java.util.Date;
/**
* @Title: OaFile.java 
* @Package com.jingren.jing.oa.bean.oa_file 
* @Description: TODO 公司文件
* @author 鲁晓飞 MR.Lu    
* @date 2017年3月31日 上午11:18:12 
* @version 网校+CRM系统 V1.0
 */
@SuppressWarnings("serial")
public class OaFile implements Serializable{

	private Integer file_id;
	private String file_name;
	private String file_addr;
	private Date file_time;
	private Integer file_down_time;
	private String houzhui;
	
	public String getHouzhui() {
		return houzhui;
	}
	public void setHouzhui(String houzhui) {
		this.houzhui = houzhui;
	}
	public Integer getFile_id() {
		return file_id;
	}
	public void setFile_id(Integer file_id) {
		this.file_id = file_id;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public String getFile_addr() {
		return file_addr;
	}
	public void setFile_addr(String file_addr) {
		this.file_addr = file_addr;
	}
	public Date getFile_time() {
		return file_time;
	}
	public void setFile_time(Date file_time) {
		this.file_time = file_time;
	}
	public Integer getFile_down_time() {
		return file_down_time;
	}
	public void setFile_down_time(Integer file_down_time) {
		this.file_down_time = file_down_time;
	}
	
}
