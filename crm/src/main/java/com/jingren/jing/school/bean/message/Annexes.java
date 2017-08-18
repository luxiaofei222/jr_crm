package com.jingren.jing.school.bean.message;

import java.io.Serializable;
/**
 * 附件
* @Title: Annexes.java 
* @Package com.jingren.jing.school.bean.message 
* @Description: TODO
* @author 鲁晓飞 MR.Lu   
* @date 2016年11月28日 上午11:09:34 
* @version 网校+CRM系统 V1.0
 */
@SuppressWarnings("serial")
public class Annexes implements Serializable{

	private Integer message_id;
	private String message_annexes;//附件名
	private String annexes_file;//附件路径
	private String file_length;//获取文件大小
	private String houzhui;//文件的后缀名
	public Integer getMessage_id() {
		return message_id;
	}
	public void setMessage_id(Integer message_id) {
		this.message_id = message_id;
	}
	public String getMessage_annexes() {
		return message_annexes;
	}
	public void setMessage_annexes(String message_annexes) {
		this.message_annexes = message_annexes;
	}
	public String getAnnexes_file() {
		return annexes_file;
	}
	public void setAnnexes_file(String annexes_file) {
		this.annexes_file = annexes_file;
	}
	public String getFile_length() {
		return file_length;
	}
	public void setFile_length(String file_length) {
		this.file_length = file_length;
	}
	public String getHouzhui() {
		return houzhui;
	}
	public void setHouzhui(String houzhui) {
		this.houzhui = houzhui;
	}
	
}
