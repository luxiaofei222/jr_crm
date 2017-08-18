package com.jingren.jing.crm.controller.uploadexel;

import java.io.InputStream;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.jingren.jing.crm.bean.company.Company;
import com.jingren.jing.crm.bean.customer.Customer;
import com.jingren.jing.crm.service.company.CompanyService;
import com.jingren.jing.crm.service.customer.CustomerService;
import com.jingren.jing.school.bean.employee.Employee;
import com.jingren.jing.util.ImportExcelUtil;
import com.jingren.jing.util.ResponseUtils;

@Controller
@RequestMapping("up_excle")
public class UploadExcelController {

	@Resource
	private CompanyService companyService;
	@Resource
	private CustomerService customerService;

	/**
	 * @Title: UploadExcelController.java
	 * @Package com.jingren.jing.crm.controller.uploadexel
	 * @Description: TODO 通过传统方式form表单提交方式导入excel文件 导入企业信息
	 * @author 鲁晓飞 MR.Lu
	 * @date 2016年12月22日 下午5:52:18
	 * @version 网校+CRM系统 V1.0
	 */
	@ResponseBody
	@RequestMapping("/delte_chongfu.jr")
	public String delte_chongfu(){
		Map<String, Object> map = new HashMap<>();
		List<Company> company1 = companyService.getCompanyList(map);
		int fail = 0;
		for (Company company : company1) {
			map.put("company_name_upload", company.getCompany_name());
			List<Company> company2 = companyService.getCompanyList(map);
			if(company2.size()>1){
				for (int j = 1; j < company2.size(); j++) {
					companyService.deleteCompany(company2.get(j).getCompany_id());
					System.out.println(fail);
					fail++;
				}
			}
		}
		return String.valueOf(fail);
	}
	
	@RequestMapping("/upload.jr")
	public void uploadExcel(HttpServletResponse response,Model model,HttpServletRequest request, HttpSession session) throws Exception {
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

		// 该处可调用service相应方法进行数据保存到数据库中，现只对数据输出
		int success = 0;// 成功的条数
		int fail = 0;// 失败的条数
		int fail_cus = 0;
		for (int i = 0; i <listob.size(); i++) {
			List<Object> lo = listob.get(i);
			Map<String, Object> map = new HashMap<>();
			map.put("company_name_upload", String.valueOf(lo.get(1)));
			List<Company> company1 = companyService.getCompanyList(map);
			if (company1.size()>0) {
				if(company1.size()>1){
					for (int j = 1; j < company1.size(); j++) {
						companyService.deleteCompany(company1.get(j).getCompany_id());
					}
				}
				fail++;
			} else {
				if(StringUtils.isNotBlank(String.valueOf(lo.get(20)))||StringUtils.isNotBlank(String.valueOf(lo.get(21)))){
				Company company = new Company();
				company.setCompany_name(String.valueOf(lo.get(1)).replaceAll(" ", ""));
				company.setCompany_fax(String.valueOf(lo.get(2)).replaceAll(" ", ""));
				company.setSuoshuhangye(String.valueOf(lo.get(3)).replaceAll(" ", ""));
				company.setCompany_type(String.valueOf(lo.get(4)).replaceAll(" ", ""));
				company.setCompany_jituan(String.valueOf(lo.get(5)).replaceAll(" ", ""));
				company.setCompany_jingying(String.valueOf(lo.get(6)).replaceAll(" ", ""));
				company.setCompany_province(String.valueOf(lo.get(7)).replaceAll(" ", ""));
				company.setCompany_city(String.valueOf(lo.get(8)).replaceAll(" ", ""));
				company.setCompany_youbian(String.valueOf(lo.get(9)).replaceAll(" ", ""));
				company.setCompany_quhao(String.valueOf(lo.get(10)).replaceAll(" ", ""));
				company.setCompany_mail(String.valueOf(lo.get(11)).replaceAll(" ", ""));
				company.setCompany_addr(String.valueOf(lo.get(12)).replaceAll(" ", ""));
				company.setCompany_web(String.valueOf(lo.get(13)).replaceAll(" ", ""));
				company.setCompany_guimo(String.valueOf(lo.get(14)).replaceAll(" ", ""));
				company.setCompany_note(String.valueOf(lo.get(15)).replaceAll(" ", ""));
				company.setCompany_time(new Date());
				company.setEmployee_id(employee.getEmployee_id());
				if(companyService.saveCompany(company)){
					if(StringUtils.isNotBlank(String.valueOf(lo.get(20)))||StringUtils.isNotBlank(String.valueOf(lo.get(21)))){
						Customer customer=new Customer();//第一个联系人
						customer.setCompany_id(company.getCompany_id());
						customer.setCustomer_time(new Date());
						customer.setCustomer_name(String.valueOf(lo.get(16)).replaceAll(" ", ""));
						customer.setCustomer_depart(String.valueOf(lo.get(17)).replaceAll(" ", ""));
						customer.setCustomer_position(String.valueOf(lo.get(18)).replaceAll(" ", ""));
						customer.setCustomer_sex(String.valueOf(lo.get(19)).replaceAll(" ", ""));
						customer.setCustomer_officephone(String.valueOf(lo.get(20)).replaceAll(" ", ""));
						customer.setCustomer_phone(String.valueOf(lo.get(21)).replaceAll(" ", ""));
						customer.setCustomer_mail(String.valueOf(lo.get(22)).replaceAll(" ", ""));
						customer.setEmployee_id(employee.getEmployee_id());
						customerService.saveCustomer(customer);
					}
					if(StringUtils.isNotBlank(String.valueOf(lo.get(27)))||StringUtils.isNotBlank(String.valueOf(lo.get(28)))){
						Customer customer=new Customer();//第二个联系人
						customer.setCompany_id(company.getCompany_id());
						customer.setCustomer_time(new Date());
						customer.setCustomer_name(String.valueOf(lo.get(23)).replaceAll(" ", ""));
						customer.setCustomer_depart(String.valueOf(lo.get(24)).replaceAll(" ", ""));
						customer.setCustomer_position(String.valueOf(lo.get(25)).replaceAll(" ", ""));
						customer.setCustomer_sex(String.valueOf(lo.get(26)).replaceAll(" ", ""));
						customer.setCustomer_officephone(String.valueOf(lo.get(27)).replaceAll(" ", ""));
						customer.setCustomer_phone(String.valueOf(lo.get(28)).replaceAll(" ", ""));
						customer.setCustomer_mail(String.valueOf(lo.get(29)).replaceAll(" ", ""));
						customer.setEmployee_id(employee.getEmployee_id());
						customerService.saveCustomer(customer);
					}
				}
				success++;
				}else{
					fail_cus++;
				}
			}
		}
		model.addAttribute("success", success);
		model.addAttribute("fail", fail);
		String fail_data="";
		String fail_data_cus="";
		if(fail>0){
			fail_data="，失败原因：企业名称已存在！";
		}
		if(fail_cus>0){
			fail_data_cus="和没有企业联系人的数量："+fail_cus;
		}
		ResponseUtils.renderText(response, "<p style='color: orange;'>恭喜您导入成功，共计成功导入"+success+"条数据</p><p style='color: orange;'>导入失败"+fail+"条数据"+fail_data+fail_data_cus+"</p>");
	}
}
