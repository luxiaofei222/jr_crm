package com.jingren.jing.recruit.bean.chengkaoyuanxiao;

import java.io.Serializable;
import java.util.Date;

import com.jingren.jing.recruit.bean.help.HelpCenter;
/**
* @Title: CKZhaoSheng.java 
* @Package com.jingren.jing.recruit.bean.chengkaoyuanxiao 
* @Description: TODO 成考招生-主表
* @author 鲁晓飞 MR.Lu   
* @date 2017年6月19日 上午9:39:55 
* @version 网校+CRM系统 V1.0
 */
@SuppressWarnings("serial")
public class CKZhaoSheng implements Serializable{

	private Integer zhaosheng_id;
	private Integer chengkao_id;//成考学校ID
	private String xiaohui;//校徽
	private String remen_zhuanye;//热门专业
	private String shouye_beijing;//首页背景
	private String xiangqing_beijing;//详情背景
	private String yuanxiao_jieshao; //院校介绍
	private String fugai_quyu; //覆盖区域
	private String xuelizhengshu; //学历证书
	private String xuewei_zhengshu; //学位证书
	private Date tijiao_time; //提交时间
	private Integer zhaosheng_state; //学校招生状态
	
	private String xuexiao_name;//学校名称
	private Integer wangjiao_id;//网教ID
	private String xuexiao_type;//学校类型
	private Integer is_show;//是否显示
	private String xiaoxun;//校训
	private Integer jiaocaijihuanum;//教材计划数量
	
	private String yuanli_pic;
	private String yuanli_file;
	private String yuanli_name;
	private String xuesheng_shouce;//学生手册
	private String shoufei_shuoming;//费用说明
	private String moniti;//模拟题
	private HelpCenter center;//帮助中心
	
	public HelpCenter getCenter() {
		return center;
	}
	public void setCenter(HelpCenter center) {
		this.center = center;
	}
	public String getXuesheng_shouce() {
		return xuesheng_shouce;
	}
	public void setXuesheng_shouce(String xuesheng_shouce) {
		this.xuesheng_shouce = xuesheng_shouce;
	}
	public String getShoufei_shuoming() {
		return shoufei_shuoming;
	}
	public void setShoufei_shuoming(String shoufei_shuoming) {
		this.shoufei_shuoming = shoufei_shuoming;
	}
	public String getMoniti() {
		return moniti;
	}
	public void setMoniti(String moniti) {
		this.moniti = moniti;
	}
	public String getYuanli_pic() {
		return yuanli_pic;
	}
	public void setYuanli_pic(String yuanli_pic) {
		this.yuanli_pic = yuanli_pic;
	}
	public String getYuanli_file() {
		return yuanli_file;
	}
	public void setYuanli_file(String yuanli_file) {
		this.yuanli_file = yuanli_file;
	}
	public String getYuanli_name() {
		return yuanli_name;
	}
	public void setYuanli_name(String yuanli_name) {
		this.yuanli_name = yuanli_name;
	}
	public Integer getJiaocaijihuanum() {
		return jiaocaijihuanum;
	}
	public void setJiaocaijihuanum(Integer jiaocaijihuanum) {
		this.jiaocaijihuanum = jiaocaijihuanum;
	}
	public String getXiaoxun() {
		return xiaoxun;
	}
	public void setXiaoxun(String xiaoxun) {
		this.xiaoxun = xiaoxun;
	}
	public Integer getIs_show() {
		return is_show;
	}
	public void setIs_show(Integer is_show) {
		this.is_show = is_show;
	}
	public Integer getWangjiao_id() {
		return wangjiao_id;
	}
	public void setWangjiao_id(Integer wangjiao_id) {
		this.wangjiao_id = wangjiao_id;
	}
	public String getXuexiao_type() {
		return xuexiao_type;
	}
	public void setXuexiao_type(String xuexiao_type) {
		this.xuexiao_type = xuexiao_type;
	}
	public Integer getZhaosheng_id() {
		return zhaosheng_id;
	}
	public void setZhaosheng_id(Integer zhaosheng_id) {
		this.zhaosheng_id = zhaosheng_id;
	}
	public Integer getChengkao_id() {
		return chengkao_id;
	}
	public void setChengkao_id(Integer chengkao_id) {
		this.chengkao_id = chengkao_id;
	}
	public String getXiaohui() {
		return xiaohui;
	}
	public void setXiaohui(String xiaohui) {
		this.xiaohui = xiaohui;
	}
	public String getRemen_zhuanye() {
		return remen_zhuanye;
	}
	public void setRemen_zhuanye(String remen_zhuanye) {
		this.remen_zhuanye = remen_zhuanye;
	}
	public String getShouye_beijing() {
		return shouye_beijing;
	}
	public void setShouye_beijing(String shouye_beijing) {
		this.shouye_beijing = shouye_beijing;
	}
	public String getXiangqing_beijing() {
		return xiangqing_beijing;
	}
	public void setXiangqing_beijing(String xiangqing_beijing) {
		this.xiangqing_beijing = xiangqing_beijing;
	}
	public String getYuanxiao_jieshao() {
		return yuanxiao_jieshao;
	}
	public void setYuanxiao_jieshao(String yuanxiao_jieshao) {
		this.yuanxiao_jieshao = yuanxiao_jieshao;
	}
	public String getFugai_quyu() {
		return fugai_quyu;
	}
	public void setFugai_quyu(String fugai_quyu) {
		this.fugai_quyu = fugai_quyu;
	}
	public String getXuelizhengshu() {
		return xuelizhengshu;
	}
	public void setXuelizhengshu(String xuelizhengshu) {
		this.xuelizhengshu = xuelizhengshu;
	}
	public String getXuewei_zhengshu() {
		return xuewei_zhengshu;
	}
	public void setXuewei_zhengshu(String xuewei_zhengshu) {
		this.xuewei_zhengshu = xuewei_zhengshu;
	}
	public Date getTijiao_time() {
		return tijiao_time;
	}
	public void setTijiao_time(Date tijiao_time) {
		this.tijiao_time = tijiao_time;
	}
	public Integer getZhaosheng_state() {
		return zhaosheng_state;
	}
	public void setZhaosheng_state(Integer zhaosheng_state) {
		this.zhaosheng_state = zhaosheng_state;
	}
	public String getXuexiao_name() {
		return xuexiao_name;
	}
	public void setXuexiao_name(String xuexiao_name) {
		this.xuexiao_name = xuexiao_name;
	}
	
}
