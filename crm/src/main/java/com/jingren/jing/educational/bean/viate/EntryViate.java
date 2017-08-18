package com.jingren.jing.educational.bean.viate;

import java.io.Serializable;

public class EntryViate implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer vitae_id;
	private Integer entry_info_id;
	private String time_qujian;//时间区间 何年何月-何年何月
	private String danwei_xuexiao;
	private String zhengmingren;
	public Integer getVitae_id() {
		return vitae_id;
	}
	public void setVitae_id(Integer vitae_id) {
		this.vitae_id = vitae_id;
	}
	public Integer getEntry_info_id() {
		return entry_info_id;
	}
	public void setEntry_info_id(Integer entry_info_id) {
		this.entry_info_id = entry_info_id;
	}
	public String getTime_qujian() {
		return time_qujian;
	}
	public void setTime_qujian(String time_qujian) {
		this.time_qujian = time_qujian;
	}
	public String getDanwei_xuexiao() {
		return danwei_xuexiao;
	}
	public void setDanwei_xuexiao(String danwei_xuexiao) {
		this.danwei_xuexiao = danwei_xuexiao;
	}
	public String getZhengmingren() {
		return zhengmingren;
	}
	public void setZhengmingren(String zhengmingren) {
		this.zhengmingren = zhengmingren;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
