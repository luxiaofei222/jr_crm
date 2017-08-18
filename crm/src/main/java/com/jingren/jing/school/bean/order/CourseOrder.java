package com.jingren.jing.school.bean.order;

import java.io.Serializable;
import java.util.Date;

import com.jingren.jing.school.bean.course.CourseVideo;
import com.jingren.jing.school.bean.user.User;
/**
 * 课程订单
* @Title: CourseOrder.java 
* @Package com.jingren.jing.school.bean.order 
* @Description: TODO
* @author 鲁晓飞 MR.Lu   
* @date 2016年11月23日 上午10:28:10 
* @version 网校+CRM系统 V1.0
 */
@SuppressWarnings("serial")
public class CourseOrder implements Serializable{

	private String order_id;
	private Integer video_id;
	private Integer user_id;
	private String pay_money;//支付的金额
	private Date pay_time;//支付时间
	private Date order_time;//订单生成时间
	private String order_state;//订单状态：'关闭','待付款','已付款'
	private String order_number;//订单编号
	private String pay_type;//支付方式：'微信','支付宝'
	private String is_show;//订单信息的状态;'删除','显示'
	private CourseVideo courseVideo;
	private User user;//用户
	
	private  String other_order_no;//第三方支付的订单号
	
	private Integer clearance_id;//套餐id
	
	public Integer getClearance_id() {
		return clearance_id;
	}
	public void setClearance_id(Integer clearance_id) {
		this.clearance_id = clearance_id;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public Integer getVideo_id() {
		return video_id;
	}
	public void setVideo_id(Integer video_id) {
		this.video_id = video_id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getPay_money() {
		return pay_money;
	}
	public void setPay_money(String pay_money) {
		this.pay_money = pay_money;
	}
	public Date getPay_time() {
		return pay_time;
	}
	public void setPay_time(Date pay_time) {
		this.pay_time = pay_time;
	}
	public Date getOrder_time() {
		return order_time;
	}
	public void setOrder_time(Date order_time) {
		this.order_time = order_time;
	}
	public String getOrder_state() {
		return order_state;
	}
	public void setOrder_state(String order_state) {
		this.order_state = order_state;
	}
	public String getOrder_number() {
		return order_number;
	}
	public void setOrder_number(String order_number) {
		this.order_number = order_number;
	}
	public String getPay_type() {
		return pay_type;
	}
	public void setPay_type(String pay_type) {
		this.pay_type = pay_type;
	}
	public String getIs_show() {
		return is_show;
	}
	public void setIs_show(String is_show) {
		this.is_show = is_show;
	}
	public CourseVideo getCourseVideo() {
		return courseVideo;
	}
	public void setCourseVideo(CourseVideo courseVideo) {
		this.courseVideo = courseVideo;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getOther_order_no() {
		return other_order_no;
	}
	public void setOther_order_no(String other_order_no) {
		this.other_order_no = other_order_no;
	}
}
