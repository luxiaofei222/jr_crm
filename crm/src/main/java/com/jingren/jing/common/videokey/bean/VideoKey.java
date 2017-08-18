package com.jingren.jing.common.videokey.bean;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class VideoKey implements Serializable{

	private Integer key_id;
	private String video_key;
	private Date video_time;
	
	public Date getVideo_time() {
		return video_time;
	}
	public void setVideo_time(Date video_time) {
		this.video_time = video_time;
	}
	public Integer getKey_id() {
		return key_id;
	}
	public void setKey_id(Integer key_id) {
		this.key_id = key_id;
	}
	public String getVideo_key() {
		return video_key;
	}
	public void setVideo_key(String video_key) {
		this.video_key = video_key;
	}
	
}
