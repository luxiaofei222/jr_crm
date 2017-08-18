package com.jingren.jing.school.entrysystem.controller.entryinfo;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
/**
* @Title: EntryInfoController.java 
* @Package com.jingren.jing.school.entrysystem.controller.entryinfo 
* @Description: TODO 报名信息录入
* @author 鲁晓飞 MR.Lu   
* @date 2016年12月23日 下午1:39:23 
* @version 网校+CRM系统 V1.0
 */
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jingren.jing.common.city.bean.City;
import com.jingren.jing.common.city.service.CityService;
import com.jingren.jing.common.nation.bean.TbNation;
import com.jingren.jing.common.nation.service.TbNationService;
import com.jingren.jing.common.university.bean.University;
import com.jingren.jing.common.university.service.UniversityService;
import com.jingren.jing.crm.bean.company.Company;
import com.jingren.jing.crm.bean.customer.Customer;
import com.jingren.jing.crm.service.company.CompanyService;
import com.jingren.jing.crm.service.customer.CustomerService;
import com.jingren.jing.educational.bean.family.EntryFamily;
import com.jingren.jing.educational.bean.viate.EntryViate;
import com.jingren.jing.educational.service.family.EntryFamilyService;
import com.jingren.jing.educational.service.viate.EntryViateService;
import com.jingren.jing.school.bean.course.CourseMenu;
import com.jingren.jing.school.bean.dictionary.Dictionary;
import com.jingren.jing.school.bean.employee.Employee;
import com.jingren.jing.school.bean.user.User;
import com.jingren.jing.school.entrysystem.bean.entrycondition.EntryCondition;
import com.jingren.jing.school.entrysystem.bean.entryinfo.EntryInfo;
import com.jingren.jing.school.entrysystem.bean.entryplace.EntryPlace;
import com.jingren.jing.school.entrysystem.bean.entryplan.EntryPlan;
import com.jingren.jing.school.entrysystem.service.entrycondition.EntryConditionService;
import com.jingren.jing.school.entrysystem.service.entryinfo.EntryInfoService;
import com.jingren.jing.school.entrysystem.service.entryplace.EntryPlaceService;
import com.jingren.jing.school.entrysystem.service.entryplan.EntryPlanService;
import com.jingren.jing.school.service.course.CourseMenuService;
import com.jingren.jing.school.service.dictionary.DictionaryService;
import com.jingren.jing.school.service.employee.EmployeeService;
import com.jingren.jing.util.DesUtil;
import com.jingren.jing.util.ResponseUtils;
import com.jingren.jing.util.UploadAddress;
@Controller
@RequestMapping("entry_info")
public class EntryInfoController {

	@Resource
	private EntryInfoService entryInfoService;
	@Resource
	private EntryConditionService entryConditionService;
	@Resource
	private CourseMenuService courseMenuService;
	@Resource
	private EntryPlaceService entryPlaceService;
	@Resource
	private EntryPlanService entryPlanService;
	@Resource
	private DictionaryService dictionaryService;
	@Resource
	private TbNationService tbNationService;
	@Resource
	private CityService cityService;
	@Resource
	private CompanyService companyService;
	@Resource
	private CustomerService customerService;
	@Resource
	private EmployeeService employeeService;
	@Resource
	private  UniversityService universityService;
	@Resource
	private EntryViateService entryViateService;
	@Resource
	private EntryFamilyService entryFamilyService;
	private static final String UP_FRONT_FILE ="images/entry";
	/**
	* @Title: EntryInfoController.java 
	* @Package com.jingren.jing.school.entrysystem.controller.entryinfo 
	* @Description: TODO 获取报名条件
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月23日 下午5:46:42 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_entry_second.html")
	public String  get_entry_second(Model model,HttpSession session,EntryInfo entryInfo,HttpServletRequest request){
		User user = (User) session.getAttribute("user_session");
		if (user != null) {
			Map<String, Object> map_plan=new HashMap<>();
			map_plan.put("entryplan_id", entryInfo.getEntryplanId());
			EntryPlan entryPlan=entryPlanService.getEntryPlan(map_plan);
			if(entryPlan.getValidity_time().getTime()>System.currentTimeMillis()){//截至日期到期
				entryInfo.setIsPay(0);
				entryInfo.setEntryInfoTime(new Date());//报名时间
				entryInfo.setUserId(user.getUser_id());
				entryInfo.setOrderNumber(DesUtil.get_baoming_number());
				if(entryInfoService.saveEntryInfo(entryInfo)){
					Map<String, Object> map=new HashMap<>();
//					map.put("parent_id", 0);
					map.put("entryplan_id", entryInfo.getEntryplanId());
					List<EntryCondition> conditions=entryConditionService.getEntryConditionListByCourseParent(map);
					for (EntryCondition entryCondition : conditions) {
						map.clear();
						map.put("course_id", entryCondition.getCourse_id());
						CourseMenu courseMenu=courseMenuService.getCouerseMenu(map);
						entryCondition.setCourseMenu(courseMenu);
					}
					model.addAttribute("conditions", conditions);
					model.addAttribute("entry_infoid", entryInfo.getEntryInfoId());
					return "/course_sign/entry_second";
			}else{
				model.addAttribute("error", "系统发生错误，请联系本网站管理员！");
				return "/course_sign/entry_first";
			}
			}else{
				model.addAttribute("error", "本计划已经到期，请选择其他计划！");
				return "/course_sign/entry_first";
			}
		}else{
			session.removeAttribute("url");
			session.setAttribute("url", "/entry_plan/get_entry_plan.html");
			return "/school/login/login";
		}
	}
	
	/**
	* @Title: EntryInfoController.java 
	* @Package com.jingren.jing.school.entrysystem.controller.entryinfo 
	* @Description: TODO 获取第三步
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月26日 上午10:10:48 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_entry_third.html")
	public String  get_entry_third(Model model,HttpSession session,EntryInfo entryInfo,HttpServletRequest request){
		User user = (User) session.getAttribute("user_session");
		if (user != null) {
			entryInfo.setUserId(user.getUser_id());
			if(entryInfoService.updateEntryInfo(entryInfo)){
				Map<String, Object> map=new HashMap<>();
				map.put("parent_id", 0);
				List<EntryPlace> entryPlaces=entryPlaceService.getEntryPlaceList(map);
				model.addAttribute("entryPlaces", entryPlaces);//报名地点一级列表
				map.clear();
				map.put("entryInfoId", entryInfo.getEntryInfoId());
				EntryInfo entryInfo2=entryInfoService.getEntryInfo(map);
				model.addAttribute("entryInfo", entryInfo2);
				map.clear();
				map.put("parent_id", entryPlaces.get(0).getEntryplace_id());
				List<EntryPlace> entryPlaces_sub=entryPlaceService.getEntryPlaceList(map);
				model.addAttribute("entryPlaces_sub", entryPlaces_sub);
				map.clear();
				map.put("parent_id", entryPlaces_sub.get(0).getEntryplace_id());
				List<EntryPlace> entryPlaces_sub_1=entryPlaceService.getEntryPlaceList(map);
				model.addAttribute("entryPlaces_sub_sub", entryPlaces_sub_1);
				return "/course_sign/entry_third";
			}else{
				model.addAttribute("error", "系统发生错误，请联系本网站管理员！");
				return "/course_sign/entry_first";
			}
		}else{
			session.removeAttribute("url");
			session.setAttribute("url", "/entry_plan/get_entry_plan.html");
			return "/school/login/login";
		}
	}
	/**
	* @Title: EntryInfoController.java 
	* @Package com.jingren.jing.school.entrysystem.controller.entryinfo 
	* @Description: TODO 添加报名地点
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月26日 上午10:32:43 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/save_entry_place.html")
	public void  save_entry_place(Model model,HttpSession session,EntryInfo entryInfo,HttpServletRequest request,
			@RequestParam(value="entry_city_id",required=false)Integer entry_city_id,
			@RequestParam(value="entry_province_id",required=false)Integer entry_province_id,
			@RequestParam(value="entry_school_id",required=false)Integer entry_school_id,
			HttpServletResponse response){
		User user = (User) session.getAttribute("user_session");
		Map<String, Object> map=new HashMap<>();
		map.put("entryplace_id", entry_city_id);
		EntryPlace entryPlace=entryPlaceService.getEntryPlace(map);
		map.clear();
		map.put("entryplace_id", entry_province_id);
		EntryPlace entryPlace_province=entryPlaceService.getEntryPlace(map);
		map.clear();
		map.put("entryplace_id", entry_school_id);
		EntryPlace entryPlace_school=entryPlaceService.getEntryPlace(map);
		if (user != null) {
			entryInfo.setEntryCity(entryPlace.getEntryplace_name());//省份
			entryInfo.setEntryProvince(entryPlace_province.getEntryplace_name());//城市
			entryInfo.setEntrySchool(entryPlace_school.getEntryplace_name());//报名校区
			entryInfo.setUserId(user.getUser_id());
			if(entryInfoService.updateEntryInfo(entryInfo)){
				
				ResponseUtils.renderText(response, "1");//成功，进入第五步
			}else{
				ResponseUtils.renderText(response, "0");//系统发生错误
			}
		}else{
			ResponseUtils.renderText(response, "3");//登录超时
		}
	}
	/**
	* @Title: EntryInfoController.java 
	* @Package com.jingren.jing.school.entrysystem.controller.entryinfo 
	* @Description: TODO 第四步页面
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月26日 上午10:43:51 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_entry_fourth.html")
	public String  get_entry_fourth(Model model,HttpSession session,HttpServletRequest request,
			@RequestParam(value="entryInfoId",required=false)Integer entryInfoId){
		User user = (User) session.getAttribute("user_session");
		if (user != null) {
			Map<String, Object> map=new HashMap<>();
			map.put("entryInfoId", entryInfoId);
			EntryInfo entryInfo=entryInfoService.getEntryInfo(map);
			map.clear();
			map.put("entryplan_id", entryInfo.getEntryplanId());
			EntryPlan entryPlan=entryPlanService.getEntryPlan(map);//报名计划
			model.addAttribute("entryPlan", entryPlan);
			map.clear();
			map.put("entrycondition_id", entryInfo.getEntrycondition_id());
			EntryCondition entryCondition=entryConditionService.getEntryCondition(map);//报名条件
			map.clear();
			map.put("course_id", entryCondition.getCourse_id());
			CourseMenu courseMenu=courseMenuService.getCouerseMenu(map);
			List<TbNation> nations=tbNationService.getTbNationList();//民族列表
			map.clear();
			map.put("leveltype", 1);
			List<City> cities = cityService.getCityList(map);
			model.addAttribute("cities", cities);
			model.addAttribute("courseMenu", courseMenu);
			model.addAttribute("entryCondition", entryCondition);
			model.addAttribute("entryInfo", entryInfo);
			model.addAttribute("nations", nations);
			if(entryCondition.getCourse_id()==20){
				map.clear();
				map.put("university_id", entryCondition.getUniversity_id());
				University university=universityService.getUniversity(map);
				model.addAttribute("university", university);
				map.clear();
				map.put("parent_id", entryCondition.getUniversity_id());
				map.put("university_type", entryCondition.getBaokao_cengci());
				List<University> universities=universityService.getUniversityList(map);
				model.addAttribute("universities", universities);
				return "/course_sign/entry_fourth_daxue";
			}else if(entryCondition.getCourse_id()==19){
				
				return "/course_sign/entry_fourth";
			}else{
				map.clear();
				map.put("dictionary_id", entryCondition.getDictionary_id());
				Dictionary dictionary=dictionaryService.getDictionary(map);
				model.addAttribute("dictionary", dictionary);
				return "/course_sign/entry_fourth";
			}
		}else{
			session.removeAttribute("url");
			session.setAttribute("url", "/entry_plan/get_entry_plan.html");
			return "/school/login/login";
		}
	}
	/**
	* @Title: EntryInfoController.java 
	* @Package com.jingren.jing.school.entrysystem.controller.entryinfo 
	* @Description: TODO 报名第四步
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月26日 下午5:03:27 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/save_fourth_info.html")
	public void save_fourth_info(EntryInfo entryInfo,HttpServletResponse response,HttpServletRequest request,
			HttpSession session,
			@RequestParam(value="star_time",required=false) String star_time,
			@RequestParam(value="end_time",required=false) String end_time,
			@RequestParam(value="suozaidanwei",required=false) String suozaidanwei,
			@RequestParam(value="zhengmingren",required=false) String zhengmingren,
			@RequestParam(value="xingming",required=false) String xingming,
			@RequestParam(value="guanxi",required=false) String guanxi,
			@RequestParam(value="nianling",required=false) String nianling,
			@RequestParam(value="family_danwei",required=false) String family_danwei,
			@RequestParam(value="job_number",required=false) String job_number,
			@RequestParam(value="zhengjian_pic",required=false) MultipartFile zhengjian_pic,
			@RequestParam(value="zhengmian_pic",required=false) MultipartFile zhengmian_pic,
			@RequestParam(value="fanmian_pic",required=false) MultipartFile fanmian_pic,
			@RequestParam(value="xueli_pic",required=false) MultipartFile xueli_pic,
			@RequestParam(value="user_pic",required=false) MultipartFile user_pic,
			@RequestParam(value="province",required=false) Integer province,
			@RequestParam(value="user_city",required=false) Integer user_city,
			@RequestParam(value="province_jiguang",required=false) Integer province_jiguang,
			@RequestParam(value="jiguan_city",required=false) Integer jiguan_city){
		User user = (User) session.getAttribute("user_session");
		if(user!=null){
			//个人简历
			if(StringUtils.isNotBlank(star_time)){
				String[] star_time_arry=star_time.split(",");
				String[] end_time_arry=end_time.split(",");
				String[] suozaidanwei_arry=suozaidanwei.split(",");
				String[] zhengmingren_arry=zhengmingren.split(",");
				EntryViate entryViate=new EntryViate();
				entryViate.setEntry_info_id(entryInfo.getEntryInfoId());
				for (int i = 0; i < star_time_arry.length; i++) {
					entryViate.setTime_qujian(star_time_arry[i]+"至"+end_time_arry[i]);
					entryViate.setDanwei_xuexiao(suozaidanwei_arry[i]);
					entryViate.setZhengmingren(zhengmingren_arry[i]);
					if (!suozaidanwei_arry[i].equals("暂无") || !zhengmingren_arry[i].equals("暂无")) {
						entryViateService.saveEntryViate(entryViate);
					}
				}
			}
			//家庭成员
			if(StringUtils.isNotBlank(xingming)){
				String[] xingming_arry=xingming.split(",");
				String[] guanxi_arry=guanxi.split(",");
				String[] nianling_arry=nianling.split(",");
				String[] family_danwei_arry=family_danwei.split(",");
				EntryFamily entryFamily=new EntryFamily();
				entryFamily.setEntry_info_id(entryInfo.getEntryInfoId());
				for (int i = 0; i < xingming_arry.length; i++) {
					entryFamily.setFamily_name(xingming_arry[i]);
					entryFamily.setGuanxi(guanxi_arry[i]);
					entryFamily.setNianling(nianling_arry[i]);
					entryFamily.setGongzuodanwei(family_danwei_arry[i]);
					if (!xingming_arry[i].equals("暂无") || !guanxi_arry[i].equals("暂无") || !nianling_arry[i].equals("暂无")
							|| !family_danwei_arry[i].equals("暂无")) {
						entryFamilyService.saveEntryFamily(entryFamily);
					}
				}
			}
			if(StringUtils.isNotBlank(job_number)){
				Map<String, Object> map=new HashMap<>();
				map.put("job_number", job_number.replace(" ", ""));
				Employee employee=employeeService.getEmployee(map);
				if(employee!=null){
					entryInfo.setEmployee_id(employee.getEmployee_id());//添加推广员账号
				}
			}
			if(zhengjian_pic!=null){
				String zhengjianpath=UploadAddress.getUploadDate(zhengjian_pic, request, UP_FRONT_FILE);
				entryInfo.setDocument_photo(zhengjianpath);
			}
			if(zhengmian_pic!=null){
				String zhengmianpath=UploadAddress.getUploadDate(zhengmian_pic, request, UP_FRONT_FILE);
				entryInfo.setUserCardPositive(zhengmianpath);//身份证正面
			}
			if(fanmian_pic!=null){
				String fanmianpath=UploadAddress.getUploadDate(fanmian_pic, request, UP_FRONT_FILE);
				entryInfo.setUserCardOpposite(fanmianpath);//身份证反面
			}
			if(xueli_pic!=null){
				String xueli_picpath=UploadAddress.getUploadDate(xueli_pic, request, UP_FRONT_FILE);
				entryInfo.setCertificatePic(xueli_picpath);//学历证书
			}
			if(user_pic!=null){
				String user_picpath=UploadAddress.getUploadDate(user_pic, request, UP_FRONT_FILE);
				entryInfo.setEntryUserPhoto(user_picpath);//个人免冠照片
			}
			if(province!=null&&province!=0){
				Map<String, Object> map=new HashMap<>();
				map.put("id", province);
				City city=cityService.getCity(map);
				if(city!=null){
					entryInfo.setEntryUserProvince(city.getName());
				}
			}
			if(user_city!=null&&user_city!=0){
				Map<String, Object> map=new HashMap<>();
				map.put("id", user_city);
				City city=cityService.getCity(map);
				if(city!=null){
					entryInfo.setEntryUserCity(city.getName());
				}
			}
			if(province_jiguang!=null&&province_jiguang!=0&&jiguan_city!=null&&jiguan_city!=0){//籍贯
				Map<String, Object> map=new HashMap<>();
				map.put("id", province_jiguang);
				City city=cityService.getCity(map);
				map.clear();
				map.put("id", jiguan_city);
				City city2=cityService.getCity(map);
				if(city!=null&&city2!=null){
					entryInfo.setJiguan(city.getName()+city2.getName());;
				}
			}
			entryInfo.setUserId(user.getUser_id());
			Map<String, Object> mapinfo=new HashMap<>();
//			mapinfo.put("company_name", entryInfo.getEntryUserUnit());
//			Company company=companyService.getCompany(mapinfo);
			if(StringUtils.isNotBlank(entryInfo.getEntryUserUnit())){
				mapinfo.put("company_name_upload", entryInfo.getEntryUserUnit());
				List<Company> company1 = companyService.getCompanyList(mapinfo);
				if(company1.size()>0){
					entryInfo.setCompany_id(company1.get(0).getCompany_id());
				}else{
					Company company2=new Company();
					company2.setCompany_name(entryInfo.getEntryUserUnit());
					company2.setCompany_time(new Date());
					company2.setEmployee_id(1);
					companyService.saveCompany(company2);
					Customer customer=new Customer();//第一个联系人
					customer.setCompany_id(company2.getCompany_id());
					customer.setCustomer_time(new Date());
					customer.setCustomer_name(entryInfo.getEntryUserName());
					customer.setCustomer_position(entryInfo.getEntryUserPosition());
					customer.setCustomer_sex(entryInfo.getEntryUserSex());
					customer.setCustomer_phone(entryInfo.getEntryUserPhone());
					customer.setCustomer_mail(entryInfo.getEntryUserMail());
					customer.setEmployee_id(1);
					customerService.saveCustomer(customer);
				}
			}
			if(entryInfoService.updateEntryInfo(entryInfo)){
				ResponseUtils.renderText(response, "1");
			}else{
				ResponseUtils.renderText(response, "0");//报名失败
			}
		}else{
			ResponseUtils.renderText(response, "3");//登录超时
		}
	}
	/**
	* @Title: EntryInfoController.java 
	* @Package com.jingren.jing.school.entrysystem.controller.entryinfo 
	* @Description: TODO 判断推广员存在不存在
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年3月16日 上午10:15:11 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/panduan_job_number.html")
	public void panduan_job_number(HttpServletResponse response,
			@RequestParam(value="job_number",required=false) String job_number){
		if(StringUtils.isNotBlank(job_number)){
			Map<String, Object> map=new HashMap<>();
			map.put("job_number", job_number.replace(" ", ""));
			Employee employee=employeeService.getEmployee(map);
			if(employee!=null){
				ResponseUtils.renderText(response, "1");
			}else{
				ResponseUtils.renderText(response, "0");
			}
		}
	}
/**
* @Title: EntryInfoController.java 
* @Package com.jingren.jing.school.entrysystem.controller.entryinfo 
* @Description: TODO 进入第五步
* @author 鲁晓飞 MR.Lu   
* @date 2016年12月27日 上午8:54:04 
* @version 网校+CRM系统 V1.0
 */
	@RequestMapping("/get_entry_fifth.html")
	public String get_entry_fifth(Model model,
			@RequestParam(value="entryInfoId",required=false) Integer entryInfoId){
		Map<String, Object> map=new HashMap<>();
		map.put("entryInfoId", entryInfoId);
		EntryInfo entryInfo=entryInfoService.getEntryInfo(map);
		model.addAttribute("entryInfo", entryInfo);
		map.clear();
		map.put("entrycondition_id", entryInfo.getEntrycondition_id());
		EntryCondition condition=entryConditionService.getEntryCondition(map);
		model.addAttribute("condition", condition);
		map.clear();
		map.put("course_id", condition.getCourse_id());
		CourseMenu courseMenu=courseMenuService.getCouerseMenu(map);
		model.addAttribute("courseMenu", courseMenu);
		if(condition.getDictionary_id()!=null){
			map.clear();
			map.put("dictionary_id", condition.getDictionary_id());
			Dictionary dictionary=dictionaryService.getDictionary(map);
			model.addAttribute("dictionary", dictionary);
		}
		if(condition.getCourse_id()==20){
			map.clear();
			map.put("entryplan_id", entryInfo.getEntryplanId());
			EntryPlan entryPlan=entryPlanService.getEntryPlan(map);//报名计划
			model.addAttribute("entryPlan", entryPlan);
			map.clear();//学校
			map.put("university_id", condition.getUniversity_id());
			University university=universityService.getUniversity(map);
			model.addAttribute("university", university);
			
			map.clear();//专业
			map.put("university_id", entryInfo.getZhuanye_id());
			University university2=universityService.getUniversity(map);
			model.addAttribute("university2", university2);
			
			map.clear();
			List<EntryFamily> entryFamilies=entryFamilyService.getEntryFamilyList(entryInfoId);
			List<EntryViate> entryViates=entryViateService.getEntryViateList(entryInfoId);
			model.addAttribute("entryFamilies", entryFamilies);//家庭成员
			model.addAttribute("entryViates", entryViates);//个人简历
			return "/course_sign/entry_fifth_wangxiao";
		}else{
			return "/course_sign/entry_fifth";
		}
	}
	
	/**
	* @Title: EntryInfoController.java 
	* @Package com.jingren.jing.school.entrysystem.controller.entryinfo 
	* @Description: TODO 进入第六步
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月27日 上午8:54:32 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_entry_sixth.html")
	public String get_entry_sixth(Model model,HttpSession session,EntryInfo entryInfo){
		User user = (User) session.getAttribute("user_session");
		if(user!=null){
			entryInfo.setEntryInfoState(1);//报名信息状态
			entryInfo.setUserId(user.getUser_id());
			if(entryInfoService.updateEntryInfo(entryInfo)){
				Map<String, Object> map=new HashMap<>();
				map.put("entryInfoId", entryInfo.getEntryInfoId());
				EntryInfo entryInfo2=entryInfoService.getEntryInfo(map);
				map.clear();
				map.put("entryplan_id", entryInfo2.getEntryplanId());
				EntryPlan entryPlan=entryPlanService.getEntryPlan(map);//报名计划
				model.addAttribute("entryPlan", entryPlan);
				model.addAttribute("entryInfo", entryInfo);
				return "/course_sign/entry_sixth";
			}else{
				return get_entry_fifth(model, entryInfo.getEntryInfoId());
			}
		}else{
			session.removeAttribute("url");
			session.setAttribute("url", "/entry_plan/get_entry_plan.html");
			return "/school/login/login";
		}
	}
	
	/**
	* @Title: EntryInfoController.java 
	* @Package com.jingren.jing.school.entrysystem.controller.entryinfo 
	* @Description: TODO 选择支付方式并支付-----支付还未写
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月27日 上午10:39:41 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_entry_seventh.html")
	public void get_entry_seventh(Model model,HttpSession session,HttpServletResponse response,EntryInfo entryInfo){
		User user = (User) session.getAttribute("user_session");
		if(user!=null){
			entryInfo.setEntryInfoState(1);//报名信息状态
			entryInfo.setUserId(user.getUser_id());
			if(entryInfoService.updateEntryInfo(entryInfo)){
				
				ResponseUtils.renderText(response, "1");
			}else{
				ResponseUtils.renderText(response, "0");
			}
		}else{
			ResponseUtils.renderText(response, "3");
		}
	}
	/**
	* @Title: EntryInfoController.java 
	* @Package com.jingren.jing.school.entrysystem.controller.entryinfo 
	* @Description: TODO 支付成功后跳转页面
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月27日 上午10:40:37 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/pay_success.html")
	public String pay_success(Model model,HttpSession session,
			@RequestParam(value="entryInfoId",required=false) Integer entryInfoId){
		Map<String, Object> map=new HashMap<>();
		map.put("entryInfoId", entryInfoId);
		EntryInfo entryInfo=entryInfoService.getEntryInfo(map);
		model.addAttribute("entryInfo", entryInfo);
		map.clear();
		map.put("entrycondition_id", entryInfo.getEntrycondition_id());
		EntryCondition condition=entryConditionService.getEntryCondition(map);
		model.addAttribute("condition", condition);
		map.clear();
		map.put("course_id", condition.getCourse_id());
		CourseMenu courseMenu=courseMenuService.getCouerseMenu(map);
		model.addAttribute("courseMenu", courseMenu);
		if(condition.getCourse_id()==20){
			map.clear();
			map.put("entryplan_id", entryInfo.getEntryplanId());
			EntryPlan entryPlan=entryPlanService.getEntryPlan(map);//报名计划
			model.addAttribute("entryPlan", entryPlan);
			map.clear();//学校
			map.put("university_id", condition.getUniversity_id());
			University university=universityService.getUniversity(map);
			model.addAttribute("university", university);
			
			map.clear();//专业
			map.put("university_id", entryInfo.getZhuanye_id());
			University university2=universityService.getUniversity(map);
			model.addAttribute("university2", university2);
			
			map.clear();
			List<EntryFamily> entryFamilies=entryFamilyService.getEntryFamilyList(entryInfoId);
			List<EntryViate> entryViates=entryViateService.getEntryViateList(entryInfoId);
			model.addAttribute("entryFamilies", entryFamilies);//家庭成员
			model.addAttribute("entryViates", entryViates);//个人简历
			return "/course_sign/entry_seventh_wangxiao";
		}else{
			map.clear();
			map.put("dictionary_id", condition.getDictionary_id());
			Dictionary dictionary=dictionaryService.getDictionary(map);
			model.addAttribute("dictionary", dictionary);
			return "/course_sign/entry_seventh";
		}
		
	}
}
