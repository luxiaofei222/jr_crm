package com.jingren.jing.conult.bean.record;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

import com.jingren.jing.conult.bean.conultinfo.NetConult;
import com.jingren.jing.school.bean.employee.Employee;
import com.jingren.jing.util.CommentDate;
/**
* @Title: NetConultRecord.java 
* @Package com.jingren.jing.conult.bean.record 
* @Description: TODO 客户咨询通话记录
* @author 鲁晓飞 MR.Lu   
* @date 2017年8月5日 上午9:35:43 
* @version 网校+CRM系统 V1.0
 */
@SuppressWarnings("serial")
public class NetConultRecord implements Serializable{

	private Integer record_id;//通话记录ID
	private Integer employee_id;//主叫
	private Integer record_time;//通话时长
	private Date call_time;//通话日期
	private String called_phone;//被叫号码
	private Integer consult_id;//被叫用户
	private String sound_file;//录音文件
	private String record_note;//备注信息
	private NetConult netConult;
	private Integer genjin_state;//跟进状态
	private String call_type;//呼叫类型
	private String zuoxi;//坐席号
	private String crm_recourd_uid;//第三方系统通话记录唯一索引
	private  String call_state;//通话状态
	private Employee employee;
	private String sec_time_call;//转化后的通话时长
	private String shijianchuo;//记录时间转化为时间戳
	private String tonghuashijian;//记录时间转化为年月日时分秒
	private String user_name;//联系人
	private Integer bumen_id;//部门ID
	private String time_long;
	public Integer getRecord_id() {
		return record_id;
	}
	public void setRecord_id(Integer record_id) {
		this.record_id = record_id;
	}
	public Integer getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(Integer employee_id) {
		this.employee_id = employee_id;
	}
	public Integer getRecord_time() {
		return record_time;
	}
	public void setRecord_time(Integer record_time) {
		this.record_time = record_time;
	}
	public Date getCall_time() {
		return call_time;
	}
	public void setCall_time(Date call_time) {
		this.call_time = call_time;
	}
	public String getCalled_phone() {
		return called_phone;
	}
	public void setCalled_phone(String called_phone) {
		this.called_phone = called_phone;
	}
	public Integer getConsult_id() {
		return consult_id;
	}
	public void setConsult_id(Integer consult_id) {
		this.consult_id = consult_id;
	}
	public String getSound_file() {
		return sound_file;
	}
	public void setSound_file(String sound_file) {
		this.sound_file = sound_file;
	}
	public String getRecord_note() {
		return record_note;
	}
	public void setRecord_note(String record_note) {
		this.record_note = record_note;
	}
	public NetConult getNetConult() {
		return netConult;
	}
	public void setNetConult(NetConult netConult) {
		this.netConult = netConult;
	}
	public Integer getGenjin_state() {
		return genjin_state;
	}
	public void setGenjin_state(Integer genjin_state) {
		this.genjin_state = genjin_state;
	}
	public String getCall_type() {
		return call_type;
	}
	public void setCall_type(String call_type) {
		this.call_type = call_type;
	}
	public String getZuoxi() {
		return zuoxi;
	}
	public void setZuoxi(String zuoxi) {
		this.zuoxi = zuoxi;
	}
	public String getCrm_recourd_uid() {
		return crm_recourd_uid;
	}
	public void setCrm_recourd_uid(String crm_recourd_uid) {
		this.crm_recourd_uid = crm_recourd_uid;
	}
	public String getCall_state() {
		return call_state;
	}
	public void setCall_state(String call_state) {
		this.call_state = call_state;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public String getSec_time_call() {
		if(record_time!=null){
			sec_time_call =CommentDate.secToTime(record_time);
		}
		return sec_time_call;
	}
	public void setSec_time_call(String sec_time_call) {
		this.sec_time_call = sec_time_call;
	}
	public String getShijianchuo() {
		shijianchuo=String.valueOf(call_time.getTime()/1000);
		return shijianchuo;
	}
	public void setShijianchuo(String shijianchuo) {
		this.shijianchuo = shijianchuo;
	}
	public String getTonghuashijian() throws ParseException {
		tonghuashijian=CommentDate.get_date_string(call_time);
		return tonghuashijian;
	}
	public void setTonghuashijian(String tonghuashijian) {
		this.tonghuashijian = tonghuashijian;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public Integer getBumen_id() {
		return bumen_id;
	}
	public void setBumen_id(Integer bumen_id) {
		this.bumen_id = bumen_id;
	}
	public String getTime_long() {
		time_long=String.valueOf(call_time.getTime()/1000);
		return time_long;
	}
	public void setTime_long(String time_long) {
		this.time_long = time_long;
	}
	
}
