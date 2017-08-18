package com.jingren.jing.crm.bean.customerplate;

import java.io.Serializable;
import java.util.Date;
/**
* @Title: CustomerPlate.java 
* @Package com.jingren.jing.crm.bean.customerplate 
* @Description: TODO 客户信息追加板块-企业库
* @author 鲁晓飞 MR.Lu   
* @date 2016年12月16日 下午1:18:51 
* @version 网校+CRM系统 V1.0
 */
@SuppressWarnings("serial")
public class CustomerPlate implements Serializable{

	private Integer plate_id;
	private Integer customer_id;
	private String palte_title;
	private String plate_content;
	private Date plate_time;
	private Integer company_id;//企业id
	public Integer getPlate_id() {
		return plate_id;
	}
	public void setPlate_id(Integer plate_id) {
		this.plate_id = plate_id;
	}
	public Integer getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}
	public String getPalte_title() {
		return palte_title;
	}
	public void setPalte_title(String palte_title) {
		this.palte_title = palte_title;
	}
	public String getPlate_content() {
		return plate_content;
	}
	public void setPlate_content(String plate_content) {
		this.plate_content = plate_content;
	}
	public Date getPlate_time() {
		return plate_time;
	}
	public void setPlate_time(Date plate_time) {
		this.plate_time = plate_time;
	}
	public Integer getCompany_id() {
		return company_id;
	}
	public void setCompany_id(Integer company_id) {
		this.company_id = company_id;
	}
	
}
