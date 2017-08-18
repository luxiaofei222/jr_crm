package com.jingren.jing.educational.controller.uploadexcel;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.jingren.jing.common.city.service.CityService;
import com.jingren.jing.crm.bean.company.Company;
import com.jingren.jing.crm.bean.customer.Customer;
import com.jingren.jing.crm.service.company.CompanyService;
import com.jingren.jing.crm.service.customer.CustomerService;
import com.jingren.jing.school.bean.employee.Employee;
import com.jingren.jing.school.entrysystem.bean.entryinfo.EntryInfo;
import com.jingren.jing.school.entrysystem.bean.entryplace.EntryPlace;
import com.jingren.jing.school.entrysystem.service.entrycondition.EntryConditionService;
import com.jingren.jing.school.entrysystem.service.entryinfo.EntryInfoService;
import com.jingren.jing.school.entrysystem.service.entryplace.EntryPlaceService;
import com.jingren.jing.school.entrysystem.service.entryplan.EntryPlanService;
import com.jingren.jing.school.service.dictionary.DictionaryService;
import com.jingren.jing.util.DesUtil;
import com.jingren.jing.util.ImportExcelUtil;
import com.jingren.jing.util.ResponseUtils;

@Controller
@RequestMapping("entry_info_excle")
public class EntryInfoUploadExcelController {

	@Resource
	private EntryInfoService entryInfoService;
	@Resource
	private EntryConditionService entryConditionService;
	@Resource
	private DictionaryService dictionaryService;
	@Resource
	private EntryPlanService entryPlanService;
	@Resource
	private CityService cityService;
	@Resource
	private EntryPlaceService entryPlaceService;
	@Resource
	private CompanyService companyService;
	@Resource
	private CustomerService customerService;

	/**
	 * @Title: EntryInfoUploadExcelController.java
	 * @Package com.jingren.jing.educational.controller.uploadexcel
	 * @Description: TODO 学员信息导入
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年1月5日 上午9:48:41
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/upload.jr")
	public void uploadExcel(EntryInfo entryInfo, HttpServletResponse response, Model model, HttpServletRequest request,
			HttpSession session, @RequestParam(value = "entry_city_id", required = false) Integer entry_city_id,
			@RequestParam(value = "entry_province_id", required = false) Integer entry_province_id,
			@RequestParam(value = "entry_school_id", required = false) Integer entry_school_id) throws Exception {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		System.out.println("通过传统方式form表单提交方式导入excel文件！");
		Employee employee = (Employee) session.getAttribute("employee_session");
		InputStream in = null;
		List<List<Object>> listob = null;
		MultipartFile file = multipartRequest.getFile("upfile");
		if (file.isEmpty()) {
			throw new Exception("文件不存在！");
		}
		in = file.getInputStream();
		listob = new ImportExcelUtil().getBankListByExcel(in, file.getOriginalFilename());
		in.close();
		// 公共的学员信息
		entryInfo.setEmployee_id(employee.getEmployee_id());
		entryInfo.setEntryInfoTime(new Date());
		entryInfo.setEntryInfoState(1);// 教务上传学员信息，默认未审核
		entryInfo.setIsPay(0);// 默认已支付
		entryInfo.setOrderNumber(DesUtil.get_baoming_number());// 订单号
		entryInfo.setPay_time(new Date());
		entryInfo.setBaokao_qudao("企业");
		entryInfo.setPayType("报名处");
		Map<String, Object> map = new HashMap<>();
		map.put("entryplace_id", entry_city_id);
		EntryPlace entryPlace = entryPlaceService.getEntryPlace(map);
		map.clear();
		map.put("entryplace_id", entry_province_id);
		EntryPlace entryPlace_province = entryPlaceService.getEntryPlace(map);
		map.clear();
		map.put("entryplace_id", entry_school_id);
		EntryPlace entryPlace_school = entryPlaceService.getEntryPlace(map);
		entryInfo.setEntryCity(entryPlace.getEntryplace_name());// 省份
		entryInfo.setEntryProvince(entryPlace_province.getEntryplace_name());// 城市
		entryInfo.setEntrySchool(entryPlace_school.getEntryplace_name());// 报名校区
		// 该处可调用service相应方法进行数据保存到数据库中，现只对数据输出
		int success = 0;// 成功的条数
		int fail = 0;// 失败的条数
		for (int i = 0; i < listob.size(); i++) {
			List<Object> lo = listob.get(i);
			entryInfo.setEntryUserName(String.valueOf(lo.get(1)).replaceAll(" ", ""));
			entryInfo.setEntryUserSex(String.valueOf(lo.get(2)).replaceAll(" ", ""));
			entryInfo.setEntryUserNation(String.valueOf(lo.get(3)).replaceAll(" ", ""));
			entryInfo.setEntryUserEducation(String.valueOf(lo.get(4)).replaceAll(" ", ""));
			entryInfo.setEntryUserBirthday(String.valueOf(lo.get(5)).replaceAll(" ", ""));
			entryInfo.setDocumentType(String.valueOf(lo.get(6)).replaceAll(" ", ""));
			entryInfo.setDocumentNumber(String.valueOf(lo.get(7)).replaceAll(" ", ""));
			entryInfo.setEntryUserPhone(String.valueOf(lo.get(8)).replaceAll(" ", ""));
			entryInfo.setEntryUserMail(String.valueOf(lo.get(9)).replaceAll(" ", ""));
			entryInfo.setZipCode(String.valueOf(lo.get(10)).replaceAll(" ", ""));
			entryInfo.setEntryUserAddress(String.valueOf(lo.get(11)).replaceAll(" ", ""));
			entryInfo.setEntryPolitical(String.valueOf(lo.get(12)).replaceAll(" ", ""));
			entryInfo.setEntryUserUnit(String.valueOf(lo.get(13)).replaceAll(" ", ""));
			entryInfo.setEntryUserPosition(String.valueOf(lo.get(14)).replaceAll(" ", ""));
			Map<String, Object> mapinfo = new HashMap<>();
			mapinfo.put("company_name", String.valueOf(lo.get(14)).replaceAll(" ", ""));
			Company company = companyService.getCompany(mapinfo);
			if (company != null) {
				entryInfo.setCompany_id(company.getCompany_id());
			} else {
				Company company2 = new Company();
				company2.setCompany_name(String.valueOf(lo.get(14)).replaceAll(" ", ""));
				company2.setCompany_time(new Date());
				company2.setEmployee_id(1);
				companyService.saveCompany(company2);
				Customer customer = new Customer();// 第一个联系人
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
			entryInfo.setInWorkTime(String.valueOf(lo.get(15)).replaceAll(" ", ""));
			entryInfo.setWorkYears(String.valueOf(lo.get(16)).replaceAll(" ", ""));
			entryInfo.setEntryUserProvince(String.valueOf(lo.get(17)).replaceAll(" ", ""));
			entryInfo.setEntryUserCity(String.valueOf(lo.get(18)).replaceAll(" ", ""));
			entryInfo.setEntryUserArea(String.valueOf(lo.get(19)).replaceAll(" ", ""));
			entryInfo.setEntryCategory(String.valueOf(lo.get(20)).replaceAll(" ", ""));
			entryInfo.setMianshoubanxing(String.valueOf(lo.get(21)).replaceAll(" ", ""));
			entryInfo.setPeixunfei(String.valueOf(lo.get(22)).replaceAll(" ", ""));
			entryInfo.setKaoshimoey(String.valueOf(lo.get(23)).replaceAll(" ", ""));
			entryInfo.setJiaocaofei(String.valueOf(lo.get(24)).replaceAll(" ", ""));
			entryInfo.setYing_pay(String.valueOf(lo.get(25)).replaceAll(" ", ""));
			entryInfo.setPayMoney(String.valueOf(lo.get(26)).replaceAll(" ", ""));
			entryInfo.setJinji_name(String.valueOf(lo.get(27)).replaceAll(" ", ""));
			entryInfo.setJinji_phone(String.valueOf(lo.get(28)).replaceAll(" ", ""));
			entryInfo.setZuzhifei(String.valueOf(lo.get(29)).replaceAll(" ", ""));
			if (entryInfoService.saveEntryInfo(entryInfo)) {
				success++;
			} else {
				fail++;
			}
		}
		model.addAttribute("success", success);
		model.addAttribute("fail", fail);
		String fail_data = "";
		if (fail > 0) {
			fail_data = "，失败原因：系统发生错误！";
		}
		ResponseUtils.renderText(response, "<p style='color: orange;'>恭喜您导入成功，共计成功导入" + success
				+ "条数据</p><p style='color: orange;'>导入失败" + fail + "条数据" + fail_data + "</p>");
	}

	// 解析
	@RequestMapping("/jiexi_excel.html")
	public String jiexi_excel(HttpServletResponse response, Model model, HttpServletRequest request, HttpSession session)
			throws Exception {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		System.out.println("通过传统方式form表单提交方式导入excel文件！");
		InputStream in = null;
		InputStream in2 = null;
		List<List<Object>> listob = null;
		List<List<Object>> listob_content = null;
		MultipartFile file = multipartRequest.getFile("upfile");
		if (file.isEmpty()) {
			throw new Exception("文件不存在！");
		}
		in = file.getInputStream();
		in2 = file.getInputStream();
		listob = new ImportExcelUtil().getonelist(in, file.getOriginalFilename());// 标题
		listob_content = new ImportExcelUtil().getotherlist(in2, file.getOriginalFilename());// 内容
		in.close();
		in2.close();
		// 该处可调用service相应方法进行数据保存到数据库中，现只对数据输出
		List<Object> lo = listob.get(0);
		List<String> result = new ArrayList<>();
		for (int i = 0; i < listob_content.size(); i++) {
			List<Object> locon = listob_content.get(i);
			String a = String.valueOf(lo.get(0)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(0)).replaceAll(" ", "");
			String a1 = String.valueOf(lo.get(1)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(1)).replaceAll(" ", "");
			String a2 = String.valueOf(lo.get(2)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(2)).replaceAll(" ", "");
			String a3 = String.valueOf(lo.get(3)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(3)).replaceAll(" ", "");
			String a4 = String.valueOf(lo.get(4)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(4)).replaceAll(" ", "");
			String a5 = String.valueOf(lo.get(5)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(5)).replaceAll(" ", "");
			String a6 = String.valueOf(lo.get(6)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(6)).replaceAll(" ", "");
			String a7 = String.valueOf(lo.get(7)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(7)).replaceAll(" ", "");
			String a8 = String.valueOf(lo.get(8)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(8)).replaceAll(" ", "");
			String a9 = String.valueOf(lo.get(9)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(9)).replaceAll(" ", "");
			String a10 = String.valueOf(lo.get(10)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(10)).replaceAll(" ", "");
			String a11 = String.valueOf(lo.get(11)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(11)).replaceAll(" ", "");
			String a12 = String.valueOf(lo.get(12)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(12)).replaceAll(" ", "");
			String a13 = String.valueOf(lo.get(13)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(13)).replaceAll(" ", "");
			String a14 = String.valueOf(lo.get(14)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(14)).replaceAll(" ", "");
			String a15 = String.valueOf(lo.get(15)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(15)).replaceAll(" ", "");
			String a16 = String.valueOf(lo.get(16)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(16)).replaceAll(" ", "");
			String a17 = String.valueOf(lo.get(17)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(17)).replaceAll(" ", "");
			String a18 = String.valueOf(lo.get(18)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(18)).replaceAll(" ", "");
			String a19 = String.valueOf(lo.get(19)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(19)).replaceAll(" ", "");
			String a20 = String.valueOf(lo.get(20)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(20)).replaceAll(" ", "");
			String a21 = String.valueOf(lo.get(21)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(21)).replaceAll(" ", "");
			String a22 = String.valueOf(lo.get(22)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(22)).replaceAll(" ", "");
			String a23 = String.valueOf(lo.get(23)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(23)).replaceAll(" ", "");
			String a24 = String.valueOf(lo.get(24)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(24)).replaceAll(" ", "");
			String a25 = String.valueOf(lo.get(25)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(25)).replaceAll(" ", "");
			String a26 = String.valueOf(lo.get(26)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(26)).replaceAll(" ", "");
			String a27 = String.valueOf(lo.get(27)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(27)).replaceAll(" ", "");
			String a28 = String.valueOf(lo.get(28)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(28)).replaceAll(" ", "");
			String a29 = String.valueOf(lo.get(29)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(29)).replaceAll(" ", "");
			String a30 = String.valueOf(lo.get(30)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(30)).replaceAll(" ", "");
			String a31 = String.valueOf(lo.get(31)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(31)).replaceAll(" ", "");
			String a32 = String.valueOf(lo.get(32)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(32)).replaceAll(" ", "");
			String a33 = String.valueOf(lo.get(33)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(33)).replaceAll(" ", "");
			String a34 = String.valueOf(lo.get(34)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(34)).replaceAll(" ", "");
			String a35 = String.valueOf(lo.get(35)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(35)).replaceAll(" ", "");
			String a36 = String.valueOf(lo.get(36)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(36)).replaceAll(" ", "");
			String a37 = String.valueOf(lo.get(37)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(37)).replaceAll(" ", "");
			String a38 = String.valueOf(lo.get(38)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(38)).replaceAll(" ", "");
			String a39 = String.valueOf(lo.get(39)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(39)).replaceAll(" ", "");
			String a40 = String.valueOf(lo.get(40)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(40)).replaceAll(" ", "");
			String a41 = String.valueOf(lo.get(41)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(41)).replaceAll(" ", "");
			String a42 = String.valueOf(lo.get(42)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(0)).replaceAll(" ", "");
			String a43 = String.valueOf(lo.get(43)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(43)).replaceAll(" ", "");
			String a44 = String.valueOf(lo.get(44)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(44)).replaceAll(" ", "");
			String a45 = String.valueOf(lo.get(45)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(45)).replaceAll(" ", "");
			String a46 = String.valueOf(lo.get(46)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(46)).replaceAll(" ", "");
			String a47 = String.valueOf(lo.get(47)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(47)).replaceAll(" ", "");
			String a48 = String.valueOf(lo.get(48)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(48)).replaceAll(" ", "");
			String a49 = String.valueOf(lo.get(49)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(49)).replaceAll(" ", "");
			String a50 = String.valueOf(lo.get(50)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(50)).replaceAll(" ", "");
			String a51 = String.valueOf(lo.get(51)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(51)).replaceAll(" ", "");
			String a52 = String.valueOf(lo.get(52)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(52)).replaceAll(" ", "");
			String a53 = String.valueOf(lo.get(53)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(53)).replaceAll(" ", "");
			String a54 = String.valueOf(lo.get(54)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(54)).replaceAll(" ", "");
			String a55 = String.valueOf(lo.get(55)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(55)).replaceAll(" ", "");
			String a56 = String.valueOf(lo.get(56)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(56)).replaceAll(" ", "");
			String a57 = String.valueOf(lo.get(57)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(57)).replaceAll(" ", "");
			String a58 = String.valueOf(lo.get(58)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(58)).replaceAll(" ", "");
			String a59 = String.valueOf(lo.get(59)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(59)).replaceAll(" ", "");
			String a60 = String.valueOf(lo.get(60)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(60)).replaceAll(" ", "");
			String a61 = String.valueOf(lo.get(61)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(61)).replaceAll(" ", "");
			String a62 = String.valueOf(lo.get(62)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(62)).replaceAll(" ", "");
			String a63 = String.valueOf(lo.get(63)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(63)).replaceAll(" ", "");
			String a64 = String.valueOf(lo.get(64)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(64)).replaceAll(" ", "");
			String a65 = String.valueOf(lo.get(65)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(65)).replaceAll(" ", "");
			String a66 = String.valueOf(lo.get(66)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(66)).replaceAll(" ", "");
			String a67= String.valueOf(lo.get(67)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(67)).replaceAll(" ", "");
			String a68 = String.valueOf(lo.get(68)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(68)).replaceAll(" ", "");
			String a69 = String.valueOf(lo.get(69)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(69)).replaceAll(" ", "");
			String a70 = String.valueOf(lo.get(70)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(70)).replaceAll(" ", "");
			String a71 = String.valueOf(lo.get(71)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(71)).replaceAll(" ", "");
			String a72 = String.valueOf(lo.get(72)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(72)).replaceAll(" ", "");
			String a73 = String.valueOf(lo.get(73)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(73)).replaceAll(" ", "");
			String a74 = String.valueOf(lo.get(74)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(74)).replaceAll(" ", "");
			String a75 = String.valueOf(lo.get(75)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(75)).replaceAll(" ", "");
			String a76 = String.valueOf(lo.get(76)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(76)).replaceAll(" ", "");
			String a77 = String.valueOf(lo.get(77)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(77)).replaceAll(" ", "");
			String a78 = String.valueOf(lo.get(78)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(78)).replaceAll(" ", "");
			String a79 = String.valueOf(lo.get(79)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(79)).replaceAll(" ", "");
			String a80 = String.valueOf(lo.get(80)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(80)).replaceAll(" ", "");
			String a81 = String.valueOf(lo.get(81)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(81)).replaceAll(" ", "");
			String a82 = String.valueOf(lo.get(82)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(82)).replaceAll(" ", "");
			String a83 = String.valueOf(lo.get(83)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(83)).replaceAll(" ", "");
			String a84 = String.valueOf(lo.get(84)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(84)).replaceAll(" ", "");
			String a85 = String.valueOf(lo.get(85)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(85)).replaceAll(" ", "");
			String a86 = String.valueOf(lo.get(86)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(86)).replaceAll(" ", "");
			String a87 = String.valueOf(lo.get(87)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(87)).replaceAll(" ", "");
			String a88 = String.valueOf(lo.get(88)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(88)).replaceAll(" ", "");
			String a89 = String.valueOf(lo.get(89)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(89)).replaceAll(" ", "");
			String a90 = String.valueOf(lo.get(90)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(90)).replaceAll(" ", "");
			String a91 = String.valueOf(lo.get(91)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(91)).replaceAll(" ", "");
			String a92 = String.valueOf(lo.get(92)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(92)).replaceAll(" ", "");
			String a93 = String.valueOf(lo.get(93)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(93)).replaceAll(" ", "");
			String a94 = String.valueOf(lo.get(94)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(94)).replaceAll(" ", "");
			String a95 = String.valueOf(lo.get(95)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(95)).replaceAll(" ", "");
			String a96 = String.valueOf(lo.get(96)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(96)).replaceAll(" ", "");
			String a97 = String.valueOf(lo.get(97)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(97)).replaceAll(" ", "");
			String a98 = String.valueOf(lo.get(98)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(98)).replaceAll(" ", "");
			String a99 = String.valueOf(lo.get(99)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(99)).replaceAll(" ", "");
			String a100 = String.valueOf(lo.get(100)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(100)).replaceAll(" ", "");
			String a101 = String.valueOf(lo.get(101)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(101)).replaceAll(" ", "");
			String a102 = String.valueOf(lo.get(102)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(102)).replaceAll(" ", "");
			String a103 = String.valueOf(lo.get(103)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(103)).replaceAll(" ", "");
			String a104 = String.valueOf(lo.get(104)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(104)).replaceAll(" ", "");
			String a105 = String.valueOf(lo.get(105)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(105)).replaceAll(" ", "");
			String a106 = String.valueOf(lo.get(106)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(106)).replaceAll(" ", "");
			String a107 = String.valueOf(lo.get(107)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(107)).replaceAll(" ", "");
			String a108 = String.valueOf(lo.get(108)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(108)).replaceAll(" ", "");
			String a109 = String.valueOf(lo.get(109)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(109)).replaceAll(" ", "");
			String a110 = String.valueOf(lo.get(110)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(110)).replaceAll(" ", "");
			String a111 = String.valueOf(lo.get(111)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(111)).replaceAll(" ", "");
			String a112 = String.valueOf(lo.get(112)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(112)).replaceAll(" ", "");
			String a113 = String.valueOf(lo.get(113)).replaceAll(" ", "") + ":"
					+ String.valueOf(locon.get(113)).replaceAll(" ", "");
			result.add(a);
			result.add(a1);
			result.add(a2);
			result.add(a3);
			result.add(a4);
			result.add(a5);
			result.add(a6);
			result.add(a7);
			result.add(a8);
			result.add(a9);
			result.add(a10);
			result.add(a11);
			result.add(a12);
			result.add(a13);
			result.add(a14);
			result.add(a15);
			result.add(a16);
			result.add(a17);
			result.add(a18);
			result.add(a19);
			result.add(a20);
			result.add(a21);
			result.add(a22);
			result.add(a23);
			result.add(a24);
			result.add(a25);
			result.add(a26);
			result.add(a27);
			result.add(a28);
			result.add(a29);
			result.add(a30);
			result.add(a31);
			result.add(a32);
			result.add(a33);
			result.add(a34);
			result.add(a35);
			result.add(a36);
			result.add(a37);
			result.add(a38);
			result.add(a39);
			result.add(a40);
			result.add(a41);
			result.add(a42);
			result.add(a43);
			result.add(a44);
			result.add(a45);
			result.add(a46);
			result.add(a47);
			result.add(a48);
			result.add(a49);
			result.add(a50);
			result.add(a51);
			result.add(a52);
			result.add(a53);
			result.add(a54);
			result.add(a55);
			result.add(a56);
			result.add(a57);
			result.add(a58);
			result.add(a59);
			result.add(a60);
			result.add(a61);
			result.add(a62);
			result.add(a63);
			result.add(a64);
			result.add(a65);
			result.add(a66);
			result.add(a67);
			result.add(a68);
			result.add(a69);
			result.add(a70);
			result.add(a71);
			result.add(a72);
			result.add(a73);
			result.add(a74);
			result.add(a75);
			result.add(a76);
			result.add(a77);
			result.add(a78);
			result.add(a79);
			result.add(a80);
			result.add(a81);
			result.add(a82);
			result.add(a83);
			result.add(a84);
			result.add(a85);
			result.add(a86);
			result.add(a87);
			result.add(a88);
			result.add(a89);
			result.add(a90);
			result.add(a91);
			result.add(a92);
			result.add(a93);
			result.add(a94);
			result.add(a95);
			result.add(a96);
			result.add(a97);
			result.add(a98);
			result.add(a99);
			result.add(a100);
			result.add(a101);
			result.add(a102);
			result.add(a103);
			result.add(a104);
			result.add(a105);
			result.add(a106);
			result.add(a107);
			result.add(a108);
			result.add(a109);
			result.add(a110);
			result.add(a111);
			result.add(a112);
			result.add(a113);
			}
		model.addAttribute("list", result);
		return "/error/jiexi_list";
	}
}
