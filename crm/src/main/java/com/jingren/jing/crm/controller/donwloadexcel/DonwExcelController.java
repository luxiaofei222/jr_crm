package com.jingren.jing.crm.controller.donwloadexcel;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jingren.jing.crm.bean.company.Company;
import com.jingren.jing.crm.bean.companyrecord.BusinessCallRecord;
import com.jingren.jing.crm.service.company.CompanyService;
import com.jingren.jing.crm.service.companyrecord.BusinessCallRecordService;
import com.jingren.jing.util.CommentDate;
import com.jingren.jing.util.ExcelUtil;
import com.jingren.jing.util.ExcelUtil.ExcelExportData;

/**
 * @Title: DonwExcelController.java
 * @Package com.jingren.jing.crm.controller.donwloadexcel
 * @Description: TODO 导出excel文件
 * @author 鲁晓飞 MR.Lu
 * @date 2016年12月23日 上午9:52:13
 * @version 网校+CRM系统 V1.0
 */
@Controller
@RequestMapping("donwload")
public class DonwExcelController {
	@Resource
	private CompanyService companyService;

	@Resource
	private BusinessCallRecordService businessCallRecordService;

	/**
	 * @Title: DonwExcelController.java
	 * @Package com.jingren.jing.crm.controller.donwloadexcel
	 * @Description: TODO 导出企业信息
	 * @author 鲁晓飞 MR.Lu
	 * @date 2016年12月23日 上午9:52:32
	 * @version 网校+CRM系统 V1.0
	 * @throws Exception 
	 */
	@RequestMapping("/export_business_excel.jr")
	public String  export_business_excel(HttpServletResponse response,
			@RequestParam(value = "start_time", required = false) Date start_time,
			@RequestParam(value = "end_time", required = false) Date end_time) throws Exception {
		// 准备设置excel工作表的标题
		String[] title = { "企业名称", "企业传真", "所属行业", "企业类型", "所属集团", "经营范围", "所在省", "所在市", "邮编", "区号", "企业邮箱", "企业地址",
				"企业网址", "企业规模", "备注信息" };
		Map<String, Object> map = new HashMap<String, Object>();
		List<Company> companies = companyService.getCompanyList(map);
		List<String[]> columNames = new ArrayList<String[]>();
        columNames.add(title);
        List<String[]> fieldNames = new ArrayList<String[]>();
        fieldNames.add(new String[] { "company_name", "company_fax","suoshuhangye","company_type","company_jituan","company_jingying","company_province","company_city"
        		,"company_youbian","company_quhao","company_mail","company_addr","company_web","company_guimo","company_note"});

        LinkedHashMap<String, List<?>> map1 = new LinkedHashMap<String, List<?>>();
        map1.put("企业信息表", companies);
        ExcelExportData setInfo = new ExcelExportData();
        setInfo.setDataMap(map1);
        setInfo.setFieldNames(fieldNames);
        setInfo.setTitles(new String[] { "企业信息表"});
        setInfo.setColumnNames(columNames);
        ExcelUtil.directExpor(setInfo, response,"企业信息表");
		return null;
	}
	/**
	* @Title: DonwExcelController.java 
	* @Package com.jingren.jing.crm.controller.donwloadexcel 
	* @Description: TODO 导出通话记录
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月22日 上午10:39:05 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/export_call_record_excel.jr")
	public String  export_call_record_excel(HttpServletResponse response,
			@RequestParam(value = "start_time", required = false) String start_time,
			@RequestParam(value = "end_time", required = false) String end_time,
			@RequestParam(value = "employee_id", required = false) Integer employee_id,
			@RequestParam(value = "beijiao", required = false) String beijiao,
			@RequestParam(value = "zhujiao", required = false) String zhujiao,
			@RequestParam(value = "record_state", required = false) String record_state,
			@RequestParam(value = "record_type", required = false) String record_type,
			@RequestParam(value = "start_shichang", required = false) Integer start_shichang,
			@RequestParam(value = "end_shichang", required = false) Integer end_shichang) throws Exception {
		// 准备设置excel工作表的标题
		String[] title = { "通话时间", "联系人", "所在企业", "主叫", "被叫", "通话时长", "通话状态", "跟进内容" };
		Map<String, Object> map = new HashMap<String, Object>();
		if(StringUtils.isNotBlank(start_time)&&StringUtils.isNotBlank(end_time)){//开始时间和结束时间
			map.put("start_time", CommentDate.get_String_date(start_time));
			long currentTime =  CommentDate.get_String_date(end_time).getTime();
			currentTime +=24*60*60*1000-1000;//加23小时59分59秒
			Date date=new Date(currentTime);
			map.put("end_time", date);
		}else{
			map.put("start_time", CommentDate.getStartTime());
			map.put("end_time", CommentDate.getnowEndTime());
		}
		
		if(employee_id!=null){//坐席
			map.put("employee_id", employee_id);
		}
		if(StringUtils.isNotBlank(beijiao)){//被叫
			map.put("beijiao", beijiao);
		}
		if(StringUtils.isNotBlank(zhujiao)){//主叫
			map.put("zhujiao", zhujiao);
		}
		if(StringUtils.isNotBlank(record_state)){//呼叫状态
			map.put("record_state", record_state);
		}
		if(StringUtils.isNotBlank(record_type)){//呼叫类型
			map.put("record_type", record_type);
		}
		if(start_shichang!=null&&end_shichang!=null){//通话时长
			map.put("start_shichang", start_shichang);
			map.put("end_shichang", end_shichang);
		}
		List<BusinessCallRecord> businessCallRecords=businessCallRecordService.getBusinessCallRecordList(map);
		for (BusinessCallRecord businessCallRecord : businessCallRecords) {
			map.clear();
			map.put("company_id", businessCallRecord.getCustomer().getCompany_id());
			Company company=companyService.getCompany(map);
			businessCallRecord.setQiye(company.getCompany_name());
			if(businessCallRecord.getCall_type().equals("呼出")){
				businessCallRecord.setZuoxi(businessCallRecord.getEmployee().getEmployee_name()+"("+businessCallRecord.getZuoxi()+")");
			}else{
				businessCallRecord.setCalled_phone(businessCallRecord.getEmployee().getEmployee_name()+"("+businessCallRecord.getCalled_phone()+")");
			}
		}
		List<String[]> columNames = new ArrayList<String[]>();
        columNames.add(title);
        List<String[]> fieldNames = new ArrayList<String[]>();
        fieldNames.add(new String[] { "tonghuashijian", "lianxiren","qiye","zuoxi","called_phone","sec_time_call","call_state","record_note"});

        LinkedHashMap<String, List<?>> map1 = new LinkedHashMap<String, List<?>>();
        map1.put("通话记录表", businessCallRecords);
        ExcelExportData setInfo = new ExcelExportData();
        setInfo.setDataMap(map1);
        setInfo.setFieldNames(fieldNames);
        setInfo.setTitles(new String[] { "通话记录表"});
        setInfo.setColumnNames(columNames);
        ExcelUtil.directExpor(setInfo, response,"通话记录表");
		return null;
	}
	
	 
}
