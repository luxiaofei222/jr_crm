package com.jingren.jing.personal.bean.leave;

import java.io.Serializable;
import java.util.Date;

import com.jingren.jing.school.bean.employee.Employee;
/**
* @Title: BackLeave.java 
* @Package com.jingren.jing.personal.bean.leave 
* @Description: TODO 请假系统
* @author 鲁晓飞 MR.Lu   
* @date 2017年5月31日 上午9:30:59 
* @version 网校+CRM系统 V1.0
 */
@SuppressWarnings("serial")
public class BackLeave implements Serializable{

	private Integer leave_id;
	private Integer employee_id;
	private String leave_content;
	private Date leave_start_time;
	private Date leave_end_time;
	private Integer leave_type;//请假类型，1.事假 2.病假 3.婚假 4.产假 5.丧假 6其他 7.倒休 8.年假
	private Integer jingli_id;
	private String jingli_message;
	private Integer zongjian_id;
	private String zongjian_message;
	private Integer boss_id;
	private String boss_message;
	private Date leave_time;
	private Integer leave_state;//审批状态：0.经理审批中 1.总监审批中 2.总经理审批中  3.批准，休假中 4.已销假 5.拒绝
	private Integer bumen_id;//部门ID
	private String leave_shichang;//请假时长
	private Employee employee;
	private String bumen;
	private Date xiaojia_time;
	private String beizhu;
	private Integer qingjiazhong;
	private String nianyue;//年月
	private String nianyueri;//年月日
	
	public String getNianyue() {
		return nianyue;
	}
	public void setNianyue(String nianyue) {
		this.nianyue = nianyue;
	}
	public String getNianyueri() {
		return nianyueri;
	}
	public void setNianyueri(String nianyueri) {
		this.nianyueri = nianyueri;
	}
	public Integer getQingjiazhong() {
		return qingjiazhong;
	}
	public void setQingjiazhong(Integer qingjiazhong) {
		this.qingjiazhong = qingjiazhong;
	}
	public Date getXiaojia_time() {
		return xiaojia_time;
	}
	public void setXiaojia_time(Date xiaojia_time) {
		this.xiaojia_time = xiaojia_time;
	}
	public String getBeizhu() {
		return beizhu;
	}
	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}
	public String getBumen() {
		return bumen;
	}
	public void setBumen(String bumen) {
		this.bumen = bumen;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public String getLeave_shichang() {
		return leave_shichang;
	}
	public void setLeave_shichang(String leave_shichang) {
		this.leave_shichang = leave_shichang;
	}
	public Integer getBumen_id() {
		return bumen_id;
	}
	public void setBumen_id(Integer bumen_id) {
		this.bumen_id = bumen_id;
	}
	public Integer getLeave_id() {
		return leave_id;
	}
	public void setLeave_id(Integer leave_id) {
		this.leave_id = leave_id;
	}
	public Integer getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(Integer employee_id) {
		this.employee_id = employee_id;
	}
	public String getLeave_content() {
		return leave_content;
	}
	public void setLeave_content(String leave_content) {
		this.leave_content = leave_content;
	}
	
	public Date getLeave_start_time() {
		return leave_start_time;
	}
	public void setLeave_start_time(Date leave_start_time) {
		this.leave_start_time = leave_start_time;
	}
	public Date getLeave_end_time() {
		return leave_end_time;
	}
	public void setLeave_end_time(Date leave_end_time) {
		this.leave_end_time = leave_end_time;
	}
	public Integer getLeave_type() {
		return leave_type;
	}
	public void setLeave_type(Integer leave_type) {
		this.leave_type = leave_type;
	}
	public Integer getJingli_id() {
		return jingli_id;
	}
	public void setJingli_id(Integer jingli_id) {
		this.jingli_id = jingli_id;
	}
	public String getJingli_message() {
		return jingli_message;
	}
	public void setJingli_message(String jingli_message) {
		this.jingli_message = jingli_message;
	}
	public Integer getZongjian_id() {
		return zongjian_id;
	}
	public void setZongjian_id(Integer zongjian_id) {
		this.zongjian_id = zongjian_id;
	}
	public String getZongjian_message() {
		return zongjian_message;
	}
	public void setZongjian_message(String zongjian_message) {
		this.zongjian_message = zongjian_message;
	}
	public Integer getBoss_id() {
		return boss_id;
	}
	public void setBoss_id(Integer boss_id) {
		this.boss_id = boss_id;
	}
	public String getBoss_message() {
		return boss_message;
	}
	public void setBoss_message(String boss_message) {
		this.boss_message = boss_message;
	}
	public Date getLeave_time() {
		return leave_time;
	}
	public void setLeave_time(Date leave_time) {
		this.leave_time = leave_time;
	}
	public Integer getLeave_state() {
		return leave_state;
	}
	public void setLeave_state(Integer leave_state) {
		this.leave_state = leave_state;
	}
	
}
