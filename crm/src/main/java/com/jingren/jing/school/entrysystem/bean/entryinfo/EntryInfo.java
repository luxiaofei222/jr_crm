package com.jingren.jing.school.entrysystem.bean.entryinfo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.jingren.jing.educational.bean.family.EntryFamily;
import com.jingren.jing.educational.bean.viate.EntryViate;
import com.jingren.jing.school.bean.course.CourseMenu;
import com.jingren.jing.school.bean.dictionary.Dictionary;
import com.jingren.jing.school.bean.employee.Employee;
import com.jingren.jing.school.entrysystem.bean.entrycondition.EntryCondition;
import com.jingren.jing.school.entrysystem.bean.entryplan.EntryPlan;
/**
* @Title: EntryInfo.java 
* @Package com.jingren.jing.school.entrysystem.bean.entryinfo 
* @Description: TODO 报名信息
* @author 鲁晓飞 MR.Lu   
* @date 2016年12月21日 上午10:50:35 
* @version 网校+CRM系统 V1.0
 */
public class EntryInfo implements Serializable{

	private static final long serialVersionUID = 1L;

	 private Integer entryInfoId;

	    private Integer entryplanId;//报名计划ID
	    private Integer entrycondition_id;//报名条件ID
	    private String entryProvince;//报名地点所在省份
	    private String entryCity;//报名地点所在城市
	    private String entrySchool;//entry_school
	    private String entryCategory;//报考类别
	    private Integer userId;//用户ID
	    private String documentType;//证件类型
	    private String documentNumber;
	    private String entryUserName;
	    private String entryUserSex;
	    private String entryUserBirthday;
	    private String entryUserNation;//民族
	    private String entryPolitical;//政治面貌
	    private String entryUserProvince;
	    private String entryUserCity;
	    private String entryUserArea;
	    private String entryUserMail;
	    private String entryUserUnit;//用户所在单位
	    private String entryUserPosition;
	    private String entryUserAddress;
	    private String zipCode;//邮编
	    private String workYears;
	    private String entryUserEducation;//文化程度
	    private String inWorkTime;
	    private String entryUserPhoto;
	    private String entryUserPhone;
	    private String userCardPositive;
	    private String userCardOpposite;
	    private String certificatePic;//学历证书
	    private Date entryInfoTime;
	    private Integer entryInfoState;//1.报名成功，但是没有审核 2，财务审核成功，提交给教务 3.教务审核成功，但是还未上报 
	    //4财务审核失败 5教务审核失败 6教务上报成功,7部门经理审核 8 市场总监审核 9 总经理审核 0 审核失败打回原籍
	   
	    private Integer isPay;//是否支付 1.支付成功 0未支付
	    private String orderNumber;
	    private String payMoney;
	    private String payType;
	    private String document_photo;
	    
	    private String caiwushenhe_message;//财务审核失败原因
	    private String jiaowushenhe_message;//教务审核失败原因
	    private String ying_pay;//应该支付的金额
	    private String tingkezheng;//听课证
	    private String jiaocai;//教材
	    private String jiaofu;//教辅
	    private String jiaofulingqu;//教辅是否领取
	    private String note;//备注
	    private String wangluoxuexika;//网络学习卡
	    private String zhuanshufuwuka;//专属服务卡
	    private String mianshoubanxing;//面授班型
	    
	    private String employee_name;//业务员姓名
	    private String employee_bumen;//业务员部门
	    private String kaoshimoey;
	    private String peixunfei;
	    private String jiaocaofei;
	    private String ticheng_money;
	    
	    private Dictionary dictionary;
	    private CourseMenu courseMenu;
	    private EntryCondition condition;
	    private EntryPlan entryPlan;
	    private Employee employee;
	    private List<EntryFamily> entryFamily;//家庭成员
	    private List<EntryViate> entryViate;//个人简历
	    private String baokao_qudao;//报名渠道
	    
	    private  Integer company_id;//企业ID
	    private  Integer employee_id;//添加的员工ID
	    private  Integer bumen_id;//员工所属部门ID
	    private Date pay_time;
	    private String pay_time_str;
	    
	    private String baomingjihua;//报名计划
	    private String shenbaotiaojian;//申报条件
	    private String baomingdian;//报名点
	    private String baomingcourse;//报名课程
	    private String hukousuozaidi;//户口所在地
	    private String jinji_name;//紧急联系人姓名
	    private String jinji_phone;//紧急联系人电话
	    private String dengji;
	    
	    private String zhiye_leibie;//报名处报名缴费方式
	    private String qianfei;//欠费金额
	    private String qianfei_info;//欠费金额项目或者欠费原因
	    private Integer zhuanye_id;//专业ID
	    private Integer university_id;//学校ID
	    private String health;//健康状况
	    private String before_name;//曾用名
	    private String user_qq;//用户qq
	    private  String studry_type;//学习形式
	    private  String jiguan;//籍贯
	    private String is_daochu;//是否导出过
	    private String zuzhifei;//组织费
	    private String xuexiao;
	    private String zhuanye;
	    
	    private String isbukao;//是否补考
	    private String zhekou;//折扣
	    private String baokaocengci;//报考层次
	    private String kelei;//科类
	    
	    private String baokaolaoshi;//报考老师
	    private String tijiaoziliao;//提交资料
	    private String suoqueziliao;//所缺资料
	    private String yuanjian_isqiquan;//原件是否齐全
	    private String danwei_phone;//单位联系电话
	    private String baomingjigou;//报名机构
	    private  String fan_fei;//返费
	    private  String bumen_yijian; //部门意见
	    private  String zongjian_yijian; //总监意见
	    private  String boss_yijian; //总经理意见
	    private String feiyong_shuoming;//费用说明
	    private String baokaokemu;
	    private String zonge;//付款总额
	    private String xuefei;//远程教育学费
	    private String xueweiyingyu;//学位英语费用
	    private String jisuanjiyiji;//计算机一级
	    private String yingyusanji;//公共英语三级
	    private String cailiaofei;//材料费
	    private String biye_leibie;//毕业类别
	    
	    private String is_nongmin;//是不是农民工
	    private String queendian;//确认点
	    
	    private Integer review_id;
	    private Integer parent_id;
	    private Integer course_id;
	    
	    private String zhengshu_num;//毕业证书编号
	    private String lunwen_timu;//论文题目
	    private String lunwen_qikan;//论文期刊
	    private String lunwen_time;//论文时间
	    private String lunwen_duzhu;//独著
	    private String lunwen_zhaiyao;//论文摘要
	    private String diyi_xueli;//第一学历
	    private String zuigao_xueli;//最高学历
	    private String biye_sc;//毕业院校
	    private String suoxue_zhuanye;//所学专业
	    private String xuewei;//学位
	    private String biye_time;//毕业时间
	    private String xuezhi;//学制
	    
	    private String zuigao_biye_time;//最高学历毕业时间
	    private String zuigao_xuexiao;//最高学历学校
	    private String zuigao_zhuanye;//最高学历专业
	    private String zuigao_xuewei;//最高学历学位
	    private String zuigaoxuezhi;//最高学历学制
	    private String zuigao_bianhao;//最高学历证书编号
	    private String zuigao_xuexi_type;//最高学历学习形式
	    private String zhicheng_yingyu;//职称英语
	    private String jisuanjinengli;//计算机能力
	    private String can_mian_liyou;//参考 免考理由
	    private String rongyuchenghao;//荣誉成果
	    private String keyanchengguo;//科研成果
	    private String now_zhuanye_zhicheng;//现有职业资格或专业资格
	    private String qude_time;//取得时间
	    
	    private String xuexin_beian;//学信网电子备案表
	    private String zhengshu_dabao;//证书打包
	    private String zhichenglunwen;//职称论文打包
	    private String ercunzhao;//二寸照片打包
	    
	    private  String baokao_banci;//报考班次
	    
	    private  String shenhe_zhuangtai;//审核状态
	    private  String jiaofei_shijian;//交费时间
	    private  String luru_shijian;//录入时间
	    //退费
	    private  String tuifei;//退费
	    private  String tuifei_shuoming;//退费说明
	    private  Date tuifei_time;//退费时间
	    private  Date shenqing_tuifei;//退费申请时间
	    private  Integer tuifei_state;//退费状态：0 未退费 1业务经理审核 2总监审核 3总经理审核 4财务审核 5 退费成功 6被拒绝
	    private  String jingli_tuifei_infeo;//经理退费审核
	    private  String zongjian_tuifei_infeo;//总监退费审核
	    private  String boss_tuifei_infeo;//总经理退费审核
	    private  String caiwu_tuifei_infeo;//财务退费审核
	    private  String tuifei_time_str;//退费时间
	    private  String tuifei_state_str;//退费状态
	    
		public String getTuifei_state_str() {
			return tuifei_state_str;
		}

		public void setTuifei_state_str(String tuifei_state_str) {
			this.tuifei_state_str = tuifei_state_str;
		}

		public String getTuifei_time_str() {
			return tuifei_time_str;
		}

		public void setTuifei_time_str(String tuifei_time_str) {
			this.tuifei_time_str = tuifei_time_str;
		}

		public String getTicheng_money() {
			return ticheng_money;
		}

		public void setTicheng_money(String ticheng_money) {
			this.ticheng_money = ticheng_money;
		}

		public String getTuifei() {
			return tuifei;
		}

		public void setTuifei(String tuifei) {
			this.tuifei = tuifei;
		}

		public String getTuifei_shuoming() {
			return tuifei_shuoming;
		}

		public void setTuifei_shuoming(String tuifei_shuoming) {
			this.tuifei_shuoming = tuifei_shuoming;
		}

		public Date getTuifei_time() {
			return tuifei_time;
		}

		public void setTuifei_time(Date tuifei_time) {
			this.tuifei_time = tuifei_time;
		}

		public Date getShenqing_tuifei() {
			return shenqing_tuifei;
		}

		public void setShenqing_tuifei(Date shenqing_tuifei) {
			this.shenqing_tuifei = shenqing_tuifei;
		}

		public Integer getTuifei_state() {
			return tuifei_state;
		}

		public void setTuifei_state(Integer tuifei_state) {
			this.tuifei_state = tuifei_state;
		}

		public String getJingli_tuifei_infeo() {
			return jingli_tuifei_infeo;
		}

		public void setJingli_tuifei_infeo(String jingli_tuifei_infeo) {
			this.jingli_tuifei_infeo = jingli_tuifei_infeo;
		}

		public String getZongjian_tuifei_infeo() {
			return zongjian_tuifei_infeo;
		}

		public void setZongjian_tuifei_infeo(String zongjian_tuifei_infeo) {
			this.zongjian_tuifei_infeo = zongjian_tuifei_infeo;
		}

		public String getBoss_tuifei_infeo() {
			return boss_tuifei_infeo;
		}

		public void setBoss_tuifei_infeo(String boss_tuifei_infeo) {
			this.boss_tuifei_infeo = boss_tuifei_infeo;
		}

		public String getCaiwu_tuifei_infeo() {
			return caiwu_tuifei_infeo;
		}

		public void setCaiwu_tuifei_infeo(String caiwu_tuifei_infeo) {
			this.caiwu_tuifei_infeo = caiwu_tuifei_infeo;
		}

		public String getZonge() {
			return zonge;
		}

		public void setZonge(String zonge) {
			this.zonge = zonge;
		}

		public String getLuru_shijian() {
			return luru_shijian;
		}

		public void setLuru_shijian(String luru_shijian) {
			this.luru_shijian = luru_shijian;
		}

		public String getShenhe_zhuangtai() {
			return shenhe_zhuangtai;
		}

		public void setShenhe_zhuangtai(String shenhe_zhuangtai) {
			this.shenhe_zhuangtai = shenhe_zhuangtai;
		}

		public String getJiaofei_shijian() {
			return jiaofei_shijian;
		}

		public void setJiaofei_shijian(String jiaofei_shijian) {
			this.jiaofei_shijian = jiaofei_shijian;
		}

		public String getBaokao_banci() {
			return baokao_banci;
		}

		public void setBaokao_banci(String baokao_banci) {
			this.baokao_banci = baokao_banci;
		}

		public String getErcunzhao() {
			return ercunzhao;
		}

		public void setErcunzhao(String ercunzhao) {
			this.ercunzhao = ercunzhao;
		}

		public String getXuexin_beian() {
			return xuexin_beian;
		}

		public void setXuexin_beian(String xuexin_beian) {
			this.xuexin_beian = xuexin_beian;
		}

		public String getZhengshu_dabao() {
			return zhengshu_dabao;
		}

		public void setZhengshu_dabao(String zhengshu_dabao) {
			this.zhengshu_dabao = zhengshu_dabao;
		}

		public String getZhichenglunwen() {
			return zhichenglunwen;
		}

		public void setZhichenglunwen(String zhichenglunwen) {
			this.zhichenglunwen = zhichenglunwen;
		}

		public String getZuigao_biye_time() {
			return zuigao_biye_time;
		}

		public void setZuigao_biye_time(String zuigao_biye_time) {
			this.zuigao_biye_time = zuigao_biye_time;
		}

		public String getZuigao_xuexiao() {
			return zuigao_xuexiao;
		}

		public void setZuigao_xuexiao(String zuigao_xuexiao) {
			this.zuigao_xuexiao = zuigao_xuexiao;
		}

		public String getZuigao_zhuanye() {
			return zuigao_zhuanye;
		}

		public void setZuigao_zhuanye(String zuigao_zhuanye) {
			this.zuigao_zhuanye = zuigao_zhuanye;
		}

		public String getZuigao_xuewei() {
			return zuigao_xuewei;
		}

		public void setZuigao_xuewei(String zuigao_xuewei) {
			this.zuigao_xuewei = zuigao_xuewei;
		}

		public String getZuigaoxuezhi() {
			return zuigaoxuezhi;
		}

		public void setZuigaoxuezhi(String zuigaoxuezhi) {
			this.zuigaoxuezhi = zuigaoxuezhi;
		}

		public String getZuigao_bianhao() {
			return zuigao_bianhao;
		}

		public void setZuigao_bianhao(String zuigao_bianhao) {
			this.zuigao_bianhao = zuigao_bianhao;
		}

		public String getZuigao_xuexi_type() {
			return zuigao_xuexi_type;
		}

		public void setZuigao_xuexi_type(String zuigao_xuexi_type) {
			this.zuigao_xuexi_type = zuigao_xuexi_type;
		}

		public String getZhicheng_yingyu() {
			return zhicheng_yingyu;
		}

		public void setZhicheng_yingyu(String zhicheng_yingyu) {
			this.zhicheng_yingyu = zhicheng_yingyu;
		}

		public String getJisuanjinengli() {
			return jisuanjinengli;
		}

		public void setJisuanjinengli(String jisuanjinengli) {
			this.jisuanjinengli = jisuanjinengli;
		}

		public String getCan_mian_liyou() {
			return can_mian_liyou;
		}

		public void setCan_mian_liyou(String can_mian_liyou) {
			this.can_mian_liyou = can_mian_liyou;
		}

		public String getRongyuchenghao() {
			return rongyuchenghao;
		}

		public void setRongyuchenghao(String rongyuchenghao) {
			this.rongyuchenghao = rongyuchenghao;
		}

		public String getKeyanchengguo() {
			return keyanchengguo;
		}

		public void setKeyanchengguo(String keyanchengguo) {
			this.keyanchengguo = keyanchengguo;
		}

		public String getNow_zhuanye_zhicheng() {
			return now_zhuanye_zhicheng;
		}

		public void setNow_zhuanye_zhicheng(String now_zhuanye_zhicheng) {
			this.now_zhuanye_zhicheng = now_zhuanye_zhicheng;
		}

		public String getQude_time() {
			return qude_time;
		}

		public void setQude_time(String qude_time) {
			this.qude_time = qude_time;
		}

		public String getDiyi_xueli() {
			return diyi_xueli;
		}

		public void setDiyi_xueli(String diyi_xueli) {
			this.diyi_xueli = diyi_xueli;
		}

		public String getZuigao_xueli() {
			return zuigao_xueli;
		}

		public void setZuigao_xueli(String zuigao_xueli) {
			this.zuigao_xueli = zuigao_xueli;
		}

		public String getBiye_sc() {
			return biye_sc;
		}

		public void setBiye_sc(String biye_sc) {
			this.biye_sc = biye_sc;
		}

		public String getSuoxue_zhuanye() {
			return suoxue_zhuanye;
		}

		public void setSuoxue_zhuanye(String suoxue_zhuanye) {
			this.suoxue_zhuanye = suoxue_zhuanye;
		}

		public String getXuewei() {
			return xuewei;
		}

		public void setXuewei(String xuewei) {
			this.xuewei = xuewei;
		}

		public String getBiye_time() {
			return biye_time;
		}

		public void setBiye_time(String biye_time) {
			this.biye_time = biye_time;
		}

		public String getZhengshu_num() {
			return zhengshu_num;
		}

		public void setZhengshu_num(String zhengshu_num) {
			this.zhengshu_num = zhengshu_num;
		}

		public String getLunwen_timu() {
			return lunwen_timu;
		}

		public void setLunwen_timu(String lunwen_timu) {
			this.lunwen_timu = lunwen_timu;
		}

		public String getLunwen_qikan() {
			return lunwen_qikan;
		}

		public void setLunwen_qikan(String lunwen_qikan) {
			this.lunwen_qikan = lunwen_qikan;
		}

		public String getLunwen_time() {
			return lunwen_time;
		}

		public void setLunwen_time(String lunwen_time) {
			this.lunwen_time = lunwen_time;
		}

		public String getLunwen_duzhu() {
			return lunwen_duzhu;
		}

		public void setLunwen_duzhu(String lunwen_duzhu) {
			this.lunwen_duzhu = lunwen_duzhu;
		}

		public String getLunwen_zhaiyao() {
			return lunwen_zhaiyao;
		}

		public void setLunwen_zhaiyao(String lunwen_zhaiyao) {
			this.lunwen_zhaiyao = lunwen_zhaiyao;
		}

		public Integer getReview_id() {
			return review_id;
		}

		public void setReview_id(Integer review_id) {
			this.review_id = review_id;
		}

		public Integer getParent_id() {
			return parent_id;
		}

		public void setParent_id(Integer parent_id) {
			this.parent_id = parent_id;
		}

		public Integer getCourse_id() {
			return course_id;
		}

		public void setCourse_id(Integer course_id) {
			this.course_id = course_id;
		}

		public String getQueendian() {
			return queendian;
		}

		public void setQueendian(String queendian) {
			this.queendian = queendian;
		}

		public String getIs_nongmin() {
			return is_nongmin;
		}

		public void setIs_nongmin(String is_nongmin) {
			this.is_nongmin = is_nongmin;
		}

		public String getZhiye_leibie() {
			return zhiye_leibie;
		}

		public void setZhiye_leibie(String zhiye_leibie) {
			this.zhiye_leibie = zhiye_leibie;
		}

		public String getBiye_leibie() {
			return biye_leibie;
		}

		public void setBiye_leibie(String biye_leibie) {
			this.biye_leibie = biye_leibie;
		}

		public String getCailiaofei() {
			return cailiaofei;
		}

		public void setCailiaofei(String cailiaofei) {
			this.cailiaofei = cailiaofei;
		}

		public String getXuefei() {
			return xuefei;
		}

		public void setXuefei(String xuefei) {
			this.xuefei = xuefei;
		}

		public String getXueweiyingyu() {
			return xueweiyingyu;
		}

		public void setXueweiyingyu(String xueweiyingyu) {
			this.xueweiyingyu = xueweiyingyu;
		}

		public String getJisuanjiyiji() {
			return jisuanjiyiji;
		}

		public void setJisuanjiyiji(String jisuanjiyiji) {
			this.jisuanjiyiji = jisuanjiyiji;
		}

		public String getYingyusanji() {
			return yingyusanji;
		}

		public void setYingyusanji(String yingyusanji) {
			this.yingyusanji = yingyusanji;
		}

		public String getBaokaokemu() {
			return baokaokemu;
		}

		public void setBaokaokemu(String baokaokemu) {
			this.baokaokemu = baokaokemu;
		}

		public String getFeiyong_shuoming() {
			return feiyong_shuoming;
		}

		public void setFeiyong_shuoming(String feiyong_shuoming) {
			this.feiyong_shuoming = feiyong_shuoming;
		}

		public String getBumen_yijian() {
			return bumen_yijian;
		}

		public void setBumen_yijian(String bumen_yijian) {
			this.bumen_yijian = bumen_yijian;
		}

		public String getZongjian_yijian() {
			return zongjian_yijian;
		}

		public void setZongjian_yijian(String zongjian_yijian) {
			this.zongjian_yijian = zongjian_yijian;
		}

		public String getBoss_yijian() {
			return boss_yijian;
		}

		public void setBoss_yijian(String boss_yijian) {
			this.boss_yijian = boss_yijian;
		}

		public String getBaomingjigou() {
			return baomingjigou;
		}

		public void setBaomingjigou(String baomingjigou) {
			this.baomingjigou = baomingjigou;
		}

		public String getTijiaoziliao() {
			return tijiaoziliao;
		}

		public void setTijiaoziliao(String tijiaoziliao) {
			this.tijiaoziliao = tijiaoziliao;
		}

		public String getSuoqueziliao() {
			return suoqueziliao;
		}

		public void setSuoqueziliao(String suoqueziliao) {
			this.suoqueziliao = suoqueziliao;
		}

		public String getYuanjian_isqiquan() {
			return yuanjian_isqiquan;
		}

		public void setYuanjian_isqiquan(String yuanjian_isqiquan) {
			this.yuanjian_isqiquan = yuanjian_isqiquan;
		}

		public String getDanwei_phone() {
			return danwei_phone;
		}

		public void setDanwei_phone(String danwei_phone) {
			this.danwei_phone = danwei_phone;
		}

		public String getBaokaolaoshi() {
			return baokaolaoshi;
		}

		public void setBaokaolaoshi(String baokaolaoshi) {
			this.baokaolaoshi = baokaolaoshi;
		}

		public String getBaokaocengci() {
			return baokaocengci;
		}

		public void setBaokaocengci(String baokaocengci) {
			this.baokaocengci = baokaocengci;
		}

		public String getHukousuozaidi() {
			return hukousuozaidi;
		}

		public void setHukousuozaidi(String hukousuozaidi) {
			this.hukousuozaidi = hukousuozaidi;
		}

		public String getBaomingjihua() {
			return baomingjihua;
		}

		public void setBaomingjihua(String baomingjihua) {
			this.baomingjihua = baomingjihua;
		}

		public String getShenbaotiaojian() {
			return shenbaotiaojian;
		}

		public void setShenbaotiaojian(String shenbaotiaojian) {
			this.shenbaotiaojian = shenbaotiaojian;
		}

		public String getBaomingdian() {
			return baomingdian;
		}

		public void setBaomingdian(String baomingdian) {
			this.baomingdian = baomingdian;
		}

		public Integer getEntryInfoId() {
			return entryInfoId;
		}

		public void setEntryInfoId(Integer entryInfoId) {
			this.entryInfoId = entryInfoId;
		}

		public Integer getEntryplanId() {
			return entryplanId;
		}

		public void setEntryplanId(Integer entryplanId) {
			this.entryplanId = entryplanId;
		}

		public Integer getEntrycondition_id() {
			return entrycondition_id;
		}

		public void setEntrycondition_id(Integer entrycondition_id) {
			this.entrycondition_id = entrycondition_id;
		}

		public String getEntryProvince() {
			return entryProvince;
		}

		public void setEntryProvince(String entryProvince) {
			this.entryProvince = entryProvince;
		}

		public String getEntryCity() {
			return entryCity;
		}

		public void setEntryCity(String entryCity) {
			this.entryCity = entryCity;
		}

		public String getEntrySchool() {
			return entrySchool;
		}

		public void setEntrySchool(String entrySchool) {
			this.entrySchool = entrySchool;
		}

		public String getEntryCategory() {
			return entryCategory;
		}

		public void setEntryCategory(String entryCategory) {
			this.entryCategory = entryCategory;
		}

		public Integer getUserId() {
			return userId;
		}

		public void setUserId(Integer userId) {
			this.userId = userId;
		}

		public String getDocumentType() {
			return documentType;
		}

		public void setDocumentType(String documentType) {
			this.documentType = documentType;
		}

		public String getDocumentNumber() {
			return documentNumber;
		}

		public void setDocumentNumber(String documentNumber) {
			this.documentNumber = documentNumber;
		}

		public String getEntryUserName() {
			return entryUserName;
		}

		public void setEntryUserName(String entryUserName) {
			this.entryUserName = entryUserName.trim();
		}

		public String getEntryUserSex() {
			return entryUserSex;
		}

		public void setEntryUserSex(String entryUserSex) {
			this.entryUserSex = entryUserSex;
		}

		public String getEntryUserBirthday() {
			return entryUserBirthday;
		}

		public void setEntryUserBirthday(String entryUserBirthday) {
			this.entryUserBirthday = entryUserBirthday;
		}

		public String getEntryUserNation() {
			return entryUserNation;
		}

		public void setEntryUserNation(String entryUserNation) {
			this.entryUserNation = entryUserNation;
		}

		public String getEntryPolitical() {
			return entryPolitical;
		}

		public void setEntryPolitical(String entryPolitical) {
			this.entryPolitical = entryPolitical;
		}

		public String getEntryUserProvince() {
			return entryUserProvince;
		}

		public void setEntryUserProvince(String entryUserProvince) {
			this.entryUserProvince = entryUserProvince;
		}

		public String getEntryUserCity() {
			return entryUserCity;
		}

		public void setEntryUserCity(String entryUserCity) {
			this.entryUserCity = entryUserCity;
		}

		public String getEntryUserArea() {
			return entryUserArea;
		}

		public void setEntryUserArea(String entryUserArea) {
			this.entryUserArea = entryUserArea;
		}

		public String getEntryUserMail() {
			return entryUserMail;
		}

		public void setEntryUserMail(String entryUserMail) {
			this.entryUserMail = entryUserMail;
		}

		public String getEntryUserUnit() {
			return entryUserUnit;
		}

		public void setEntryUserUnit(String entryUserUnit) {
			this.entryUserUnit = entryUserUnit;
		}

		public String getEntryUserPosition() {
			return entryUserPosition;
		}

		public void setEntryUserPosition(String entryUserPosition) {
			this.entryUserPosition = entryUserPosition;
		}

		public String getEntryUserAddress() {
			return entryUserAddress;
		}

		public void setEntryUserAddress(String entryUserAddress) {
			this.entryUserAddress = entryUserAddress;
		}

		public String getZipCode() {
			return zipCode;
		}

		public void setZipCode(String zipCode) {
			this.zipCode = zipCode;
		}

		public String getWorkYears() {
			return workYears;
		}

		public void setWorkYears(String workYears) {
			this.workYears = workYears;
		}

		public String getEntryUserEducation() {
			return entryUserEducation;
		}

		public void setEntryUserEducation(String entryUserEducation) {
			this.entryUserEducation = entryUserEducation;
		}

		public String getInWorkTime() {
			return inWorkTime;
		}

		public void setInWorkTime(String inWorkTime) {
			this.inWorkTime = inWorkTime;
		}

		public String getEntryUserPhoto() {
			return entryUserPhoto;
		}

		public void setEntryUserPhoto(String entryUserPhoto) {
			this.entryUserPhoto = entryUserPhoto;
		}

		public String getEntryUserPhone() {
			return entryUserPhone;
		}

		public void setEntryUserPhone(String entryUserPhone) {
			this.entryUserPhone = entryUserPhone;
		}

		public String getUserCardPositive() {
			return userCardPositive;
		}

		public void setUserCardPositive(String userCardPositive) {
			this.userCardPositive = userCardPositive;
		}

		public String getUserCardOpposite() {
			return userCardOpposite;
		}

		public void setUserCardOpposite(String userCardOpposite) {
			this.userCardOpposite = userCardOpposite;
		}

		public String getCertificatePic() {
			return certificatePic;
		}

		public void setCertificatePic(String certificatePic) {
			this.certificatePic = certificatePic;
		}

		public Date getEntryInfoTime() {
			return entryInfoTime;
		}

		public void setEntryInfoTime(Date entryInfoTime) {
			this.entryInfoTime = entryInfoTime;
		}

		public Integer getEntryInfoState() {
			return entryInfoState;
		}

		public void setEntryInfoState(Integer entryInfoState) {
			this.entryInfoState = entryInfoState;
		}

		public Integer getIsPay() {
			return isPay;
		}

		public void setIsPay(Integer isPay) {
			this.isPay = isPay;
		}

		public String getOrderNumber() {
			return orderNumber;
		}

		public void setOrderNumber(String orderNumber) {
			this.orderNumber = orderNumber;
		}

		public String getPayMoney() {
			return payMoney;
		}

		public void setPayMoney(String payMoney) {
			this.payMoney = payMoney;
		}

		public String getPayType() {
			return payType;
		}

		public void setPayType(String payType) {
			this.payType = payType;
		}

		public String getDocument_photo() {
			return document_photo;
		}

		public void setDocument_photo(String document_photo) {
			this.document_photo = document_photo;
		}

		public String getCaiwushenhe_message() {
			return caiwushenhe_message;
		}

		public void setCaiwushenhe_message(String caiwushenhe_message) {
			this.caiwushenhe_message = caiwushenhe_message;
		}

		public String getJiaowushenhe_message() {
			return jiaowushenhe_message;
		}

		public void setJiaowushenhe_message(String jiaowushenhe_message) {
			this.jiaowushenhe_message = jiaowushenhe_message;
		}

		public String getYing_pay() {
			return ying_pay;
		}

		public void setYing_pay(String ying_pay) {
			this.ying_pay = ying_pay;
		}

		public String getTingkezheng() {
			return tingkezheng;
		}

		public void setTingkezheng(String tingkezheng) {
			this.tingkezheng = tingkezheng;
		}

		public String getJiaocai() {
			return jiaocai;
		}

		public void setJiaocai(String jiaocai) {
			this.jiaocai = jiaocai;
		}

		public String getJiaofu() {
			return jiaofu;
		}

		public void setJiaofu(String jiaofu) {
			this.jiaofu = jiaofu;
		}

		public String getJiaofulingqu() {
			return jiaofulingqu;
		}

		public void setJiaofulingqu(String jiaofulingqu) {
			this.jiaofulingqu = jiaofulingqu;
		}

		public String getNote() {
			return note;
		}

		public void setNote(String note) {
			this.note = note;
		}

		public String getWangluoxuexika() {
			return wangluoxuexika;
		}

		public void setWangluoxuexika(String wangluoxuexika) {
			this.wangluoxuexika = wangluoxuexika;
		}

		public String getZhuanshufuwuka() {
			return zhuanshufuwuka;
		}

		public void setZhuanshufuwuka(String zhuanshufuwuka) {
			this.zhuanshufuwuka = zhuanshufuwuka;
		}

		public String getMianshoubanxing() {
			return mianshoubanxing;
		}

		public void setMianshoubanxing(String mianshoubanxing) {
			this.mianshoubanxing = mianshoubanxing;
		}

		public String getKaoshimoey() {
			return kaoshimoey;
		}

		public void setKaoshimoey(String kaoshimoey) {
			this.kaoshimoey = kaoshimoey;
		}

		public String getPeixunfei() {
			return peixunfei;
		}

		public void setPeixunfei(String peixunfei) {
			this.peixunfei = peixunfei;
		}

		public String getJiaocaofei() {
			return jiaocaofei;
		}

		public void setJiaocaofei(String jiaocaofei) {
			this.jiaocaofei = jiaocaofei;
		}

		public Dictionary getDictionary() {
			return dictionary;
		}

		public void setDictionary(Dictionary dictionary) {
			this.dictionary = dictionary;
		}

		public CourseMenu getCourseMenu() {
			return courseMenu;
		}

		public void setCourseMenu(CourseMenu courseMenu) {
			this.courseMenu = courseMenu;
		}

		public EntryCondition getCondition() {
			return condition;
		}

		public void setCondition(EntryCondition condition) {
			this.condition = condition;
		}
		public String getBaokao_qudao() {
			return baokao_qudao;
		}

		public void setBaokao_qudao(String baokao_qudao) {
			this.baokao_qudao = baokao_qudao;
		}

		public Integer getCompany_id() {
			return company_id;
		}

		public void setCompany_id(Integer company_id) {
			this.company_id = company_id;
		}

		public Date getPay_time() {
			return pay_time;
		}

		public void setPay_time(Date pay_time) {
			this.pay_time = pay_time;
		}

		public EntryPlan getEntryPlan() {
			return entryPlan;
		}

		public void setEntryPlan(EntryPlan entryPlan) {
			this.entryPlan = entryPlan;
		}

		public Integer getEmployee_id() {
			return employee_id;
		}

		public void setEmployee_id(Integer employee_id) {
			this.employee_id = employee_id;
		}

		public String getBaomingcourse() {
			return baomingcourse;
		}

		public void setBaomingcourse(String baomingcourse) {
			this.baomingcourse = baomingcourse;
		}

		public String getJinji_name() {
			return jinji_name;
		}

		public void setJinji_name(String jinji_name) {
			this.jinji_name = jinji_name.trim();
		}

		public String getJinji_phone() {
			return jinji_phone;
		}

		public void setJinji_phone(String jinji_phone) {
			this.jinji_phone = jinji_phone;
		}

		public Employee getEmployee() {
			return employee;
		}

		public void setEmployee(Employee employee) {
			this.employee = employee;
		}

		public String getQianfei() {
			return qianfei;
		}

		public void setQianfei(String qianfei) {
			this.qianfei = qianfei;
		}

		public String getQianfei_info() {
			return qianfei_info;
		}

		public void setQianfei_info(String qianfei_info) {
			this.qianfei_info = qianfei_info;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}

		public Integer getZhuanye_id() {
			return zhuanye_id;
		}

		public void setZhuanye_id(Integer zhuanye_id) {
			this.zhuanye_id = zhuanye_id;
		}

		public Integer getUniversity_id() {
			return university_id;
		}

		public void setUniversity_id(Integer university_id) {
			this.university_id = university_id;
		}

		public String getHealth() {
			return health;
		}

		public void setHealth(String health) {
			this.health = health;
		}

		public String getBefore_name() {
			return before_name;
		}

		public void setBefore_name(String before_name) {
			this.before_name = before_name.trim();
		}

		public String getUser_qq() {
			return user_qq;
		}

		public void setUser_qq(String user_qq) {
			this.user_qq = user_qq;
		}

		public String getStudry_type() {
			return studry_type;
		}

		public void setStudry_type(String studry_type) {
			this.studry_type = studry_type;
		}

		public String getIs_daochu() {
			return is_daochu;
		}

		public void setIs_daochu(String is_daochu) {
			this.is_daochu = is_daochu;
		}

		public String getJiguan() {
			return jiguan;
		}

		public void setJiguan(String jiguan) {
			this.jiguan = jiguan;
		}

		public List<EntryFamily> getEntryFamily() {
			return entryFamily;
		}

		public void setEntryFamily(List<EntryFamily> entryFamily) {
			this.entryFamily = entryFamily;
		}

		public List<EntryViate> getEntryViate() {
			return entryViate;
		}

		public void setEntryViate(List<EntryViate> entryViate) {
			this.entryViate = entryViate;
		}

		public String getZuzhifei() {
			return zuzhifei;
		}

		public void setZuzhifei(String zuzhifei) {
			this.zuzhifei = zuzhifei;
		}

		public String getXuexiao() {
			return xuexiao;
		}

		public void setXuexiao(String xuexiao) {
			this.xuexiao = xuexiao;
		}

		public String getZhuanye() {
			return zhuanye;
		}

		public void setZhuanye(String zhuanye) {
			this.zhuanye = zhuanye;
		}

		public String getDengji() {
			return dengji;
		}

		public void setDengji(String dengji) {
			this.dengji = dengji;
		}

		public String getEmployee_name() {
			return employee_name;
		}

		public void setEmployee_name(String employee_name) {
			this.employee_name = employee_name;
		}

		public String getEmployee_bumen() {
			return employee_bumen;
		}

		public void setEmployee_bumen(String employee_bumen) {
			this.employee_bumen = employee_bumen;
		}

		public String getIsbukao() {
			return isbukao;
		}

		public void setIsbukao(String isbukao) {
			this.isbukao = isbukao;
		}

		public String getZhekou() {
			return zhekou;
		}

		public void setZhekou(String zhekou) {
			this.zhekou = zhekou;
		}

		public String getPay_time_str() {
			return pay_time_str;
		}

		public void setPay_time_str(String pay_time_str) {
			this.pay_time_str = pay_time_str;
		}

		public String getXuezhi() {
			return xuezhi;
		}

		public void setXuezhi(String xuezhi) {
			this.xuezhi = xuezhi;
		}

		public String getKelei() {
			return kelei;
		}

		public void setKelei(String kelei) {
			this.kelei = kelei;
		}

		public String getFan_fei() {
			return fan_fei;
		}

		public void setFan_fei(String fan_fei) {
			this.fan_fei = fan_fei;
		}

		public Integer getBumen_id() {
			return bumen_id;
		}

		public void setBumen_id(Integer bumen_id) {
			this.bumen_id = bumen_id;
		}
		
}
