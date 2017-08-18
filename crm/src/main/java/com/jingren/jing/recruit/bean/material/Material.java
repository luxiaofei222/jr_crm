package com.jingren.jing.recruit.bean.material;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Material implements Serializable{

	
	private Integer material_id;
	private String material_jihua;//教材计划
	private String ruxue_pici;//入学批次
	private String cengci;//层次
	private Integer zhuanye_id;
	private String zhuanye_name;//专业名称
	private Integer parent_id;
	private String kecheng_name;//课程名称
	private Integer is_dianzi;
	private String kecheng_leibie;//课程类别
	private String shuming;//书名
	private String zuozhe;//作者
	private String xuanxiu_leixing;//选修类型
	private String shangpin_fenlei;//商品分类
	private String beizhu;//备注
	private Date tijiao_time;//提交时间
	private Integer zhaosheng_id;
	private Integer zongxuefen;//总学分
	private Integer bixiuxuefen;//必修课学分
	private Integer xuanxiuxuefen;//选修课学分
	private String kechengshuxing;//课程属性
	private Integer kecheng_xuefen;//课程学分
	
	public Integer getZongxuefen() {
		return zongxuefen;
	}
	public void setZongxuefen(Integer zongxuefen) {
		this.zongxuefen = zongxuefen;
	}
	public Integer getBixiuxuefen() {
		return bixiuxuefen;
	}
	public void setBixiuxuefen(Integer bixiuxuefen) {
		this.bixiuxuefen = bixiuxuefen;
	}
	public Integer getXuanxiuxuefen() {
		return xuanxiuxuefen;
	}
	public void setXuanxiuxuefen(Integer xuanxiuxuefen) {
		this.xuanxiuxuefen = xuanxiuxuefen;
	}
	public String getKechengshuxing() {
		return kechengshuxing;
	}
	public void setKechengshuxing(String kechengshuxing) {
		this.kechengshuxing = kechengshuxing;
	}
	public Integer getKecheng_xuefen() {
		return kecheng_xuefen;
	}
	public void setKecheng_xuefen(Integer kecheng_xuefen) {
		this.kecheng_xuefen = kecheng_xuefen;
	}
	public Integer getMaterial_id() {
		return material_id;
	}
	public void setMaterial_id(Integer material_id) {
		this.material_id = material_id;
	}
	public String getMaterial_jihua() {
		return material_jihua;
	}
	public void setMaterial_jihua(String material_jihua) {
		this.material_jihua = material_jihua;
	}
	public String getRuxue_pici() {
		return ruxue_pici;
	}
	public void setRuxue_pici(String ruxue_pici) {
		this.ruxue_pici = ruxue_pici;
	}
	public String getCengci() {
		return cengci;
	}
	public void setCengci(String cengci) {
		this.cengci = cengci;
	}
	public Integer getZhuanye_id() {
		return zhuanye_id;
	}
	public void setZhuanye_id(Integer zhuanye_id) {
		this.zhuanye_id = zhuanye_id;
	}
	public String getZhuanye_name() {
		return zhuanye_name;
	}
	public void setZhuanye_name(String zhuanye_name) {
		this.zhuanye_name = zhuanye_name;
	}
	public Integer getParent_id() {
		return parent_id;
	}
	public void setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
	}
	public String getKecheng_name() {
		return kecheng_name;
	}
	public void setKecheng_name(String kecheng_name) {
		this.kecheng_name = kecheng_name;
	}
	public Integer getIs_dianzi() {
		return is_dianzi;
	}
	public void setIs_dianzi(Integer is_dianzi) {
		this.is_dianzi = is_dianzi;
	}
	public String getKecheng_leibie() {
		return kecheng_leibie;
	}
	public void setKecheng_leibie(String kecheng_leibie) {
		this.kecheng_leibie = kecheng_leibie;
	}
	public String getShuming() {
		return shuming;
	}
	public void setShuming(String shuming) {
		this.shuming = shuming;
	}
	public String getZuozhe() {
		return zuozhe;
	}
	public void setZuozhe(String zuozhe) {
		this.zuozhe = zuozhe;
	}
	public String getXuanxiu_leixing() {
		return xuanxiu_leixing;
	}
	public void setXuanxiu_leixing(String xuanxiu_leixing) {
		this.xuanxiu_leixing = xuanxiu_leixing;
	}
	public String getShangpin_fenlei() {
		return shangpin_fenlei;
	}
	public void setShangpin_fenlei(String shangpin_fenlei) {
		this.shangpin_fenlei = shangpin_fenlei;
	}
	public String getBeizhu() {
		return beizhu;
	}
	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}
	public Date getTijiao_time() {
		return tijiao_time;
	}
	public void setTijiao_time(Date tijiao_time) {
		this.tijiao_time = tijiao_time;
	}
	public Integer getZhaosheng_id() {
		return zhaosheng_id;
	}
	public void setZhaosheng_id(Integer zhaosheng_id) {
		this.zhaosheng_id = zhaosheng_id;
	}
	
}
