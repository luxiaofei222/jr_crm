package com.jingren.jing.common.upload;

import java.text.DecimalFormat;

import com.jingren.jing.util.JsonUtil;
import com.jingren.jing.util.NumUtil;

public class ProgressEntity {
    private long pBytesRead = 0L;  
    private long pContentLength = 0L;  
    private int pItems;  
	/** 已读百分比 **/
	private String percent;
	/** 读取速度 **/
	private String speed;
	/** 开始读取的时间 **/
	private long startReatTime = System.currentTimeMillis();
	
	private String hasread;//已经上传的部分转化
    public long getpBytesRead() {  
        return pBytesRead;  
    }  
    public void setpBytesRead(long pBytesRead) {  
        this.pBytesRead = pBytesRead;  
    }  
    public long getpContentLength() {  
        return pContentLength;  
    }  
    public void setpContentLength(long pContentLength) {  
        this.pContentLength = pContentLength;  
    }  
    public int getpItems() {  
        return pItems;  
    }  
    public void setpItems(int pItems) {  
        this.pItems = pItems;  
    }
	public String getPercent() {
		percent = NumUtil.getPercent(pBytesRead, pContentLength);
		return percent;
	}
	public void setPercent(String percent) {
		this.percent = percent;
	}
	public String getSpeed() {
	String 	speed_le = NumUtil.divideNumber(
				NumUtil.divideNumber(pBytesRead * 1000, System.currentTimeMillis()- startReatTime), 
				1000);
	DecimalFormat df = new DecimalFormat("0.00"); 
	if(Double.valueOf(speed_le)>1024){
		speed = df.format((double) Double.valueOf(speed_le) / 1024) + "MB";
	}else{
		speed=speed_le+"KB";
	}
	return speed;
	}
	public void setSpeed(String speed) {
		this.speed = speed;
	}
	public long getStartReatTime() {
		return startReatTime;
	}
	public void setStartReatTime(long startReatTime) {
		this.startReatTime = startReatTime;
	}
	public String getHasread() {
		hasread=NumUtil.get_file_lenth(pBytesRead);
		return hasread;
	}
	public void setHasread(String hasread) {
		this.hasread = hasread;
	}  
	@Override
	public String toString() {
		return JsonUtil.getJsonString4JavaPOJO(this);
	}
    
}
