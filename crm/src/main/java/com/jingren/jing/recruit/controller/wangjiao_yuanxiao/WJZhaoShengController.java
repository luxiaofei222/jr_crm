package com.jingren.jing.recruit.controller.wangjiao_yuanxiao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jingren.jing.common.university.bean.ChengkaoSc;
import com.jingren.jing.common.university.bean.University;
import com.jingren.jing.common.university.service.ChengkaoScService;
import com.jingren.jing.common.university.service.UniversityService;
import com.jingren.jing.recruit.bean.chengkaoyuanxiao.CKZhaoSheng;
import com.jingren.jing.recruit.bean.help.HelpCenter;
import com.jingren.jing.recruit.service.chengkaoyuanxiao.CKZhaoShengService;
import com.jingren.jing.recruit.service.help.HelpCenterService;
import com.jingren.jing.recruit.service.recruit.MaterialService;
import com.jingren.jing.util.Pagers;
import com.jingren.jing.util.ResponseUtils;
import com.jingren.jing.util.UploadAddress;
/**
* @Title: WJZhaoShengController.java 
* @Package com.jingren.jing.recruit.controller.wangjiao_yuanxiao 
* @Description: TODO 网络教育招生
* @author 鲁晓飞 MR.Lu   
* @date 2017年6月23日 上午7:57:25 
* @version 网校+CRM系统 V1.0
 */
@Controller
@RequestMapping("wj_zhaosheng")
public class WJZhaoShengController {

	@Resource
	private ChengkaoScService chengkaoScService;
	@Resource
	private CKZhaoShengService ckZhaoShengService;
	@Resource
	private MaterialService materialService;
	@Resource
	private HelpCenterService helpCenterService;
	
	@Resource
	private UniversityService universityService;
	private static final String CK_PIC ="images/xuexiao/wangjiao";
	
	/**
	* @Title: WJZhaoShengController.java 
	* @Package com.jingren.jing.recruit.controller.wangjiao_yuanxiao 
	* @Description: TODO 网络教育招生信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月23日 上午7:59:07 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_wangjiao_zhaosheng_list.jr")
	public String get_wangjiao_zhaosheng_list(Model model,
			@RequestParam(value = "limit", required = false,defaultValue="20")Integer limit,
			@RequestParam(value = "pageNumber", required = false,defaultValue="1")Integer pageNumber){
		Map<String, Object> map = new HashMap<>();
		map.put("pageNumber", (pageNumber - 1) * limit);
		map.put("limit", limit);
		map.put("xuexiao_type", "网教");
		Integer chengkao_number=ckZhaoShengService.getCKZhaoShengNumber(map);
		List<CKZhaoSheng> ckZhaoShengs=ckZhaoShengService.getCKZhaoShengList(map);
		for (CKZhaoSheng ckZhaoSheng : ckZhaoShengs) {
			map.clear();
			map.put("parent_id", 0);
			map.put("zhaosheng_id", ckZhaoSheng.getZhaosheng_id());
			Integer number=materialService.getMaterialNumber(map);
			ckZhaoSheng.setJiaocaijihuanum(number);
			
			HelpCenter helpCenter=helpCenterService.getHelpCenter(map);
			ckZhaoSheng.setCenter(helpCenter);
		}
		Pagers<CKZhaoSheng> pagers=new Pagers<CKZhaoSheng>(chengkao_number, pageNumber, limit);
		pagers.setList(ckZhaoShengs);
		model.addAttribute("ckZhaoShengs", pagers);
		return "/recruit/zhaosheng/wangjiao_zhaosheng";
	}
	/**
	* @Title: WJZhaoShengController.java 
	* @Package com.jingren.jing.recruit.controller.wangjiao_yuanxiao 
	* @Description: TODO 添加网教信息页面
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月23日 上午8:04:33 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_add_wangjiao_info.jr")
	public String get_add_wangjiao_info(Model model){
		Map<String, Object> map = new HashMap<>();
		map.put("parent_id", 0);
		List<University> universities=universityService.getUniversityList(map);
		model.addAttribute("universities", universities);
		return "/recruit/zhaosheng/add_wangjiao_info";
	}
	/**
	* @Title: WJZhaoShengController.java 
	* @Package com.jingren.jing.recruit.controller.wangjiao_yuanxiao 
	* @Description: TODO 保存网教信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月23日 上午8:14:23 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/save_wangjiao_xuexiao.jr")
	public void save_chengkao_xuexiao(CKZhaoSheng ckZhaoSheng,HttpServletResponse response,HttpServletRequest request,
			@RequestParam(value="xiaohun_str",required=false)MultipartFile xiaohun_str,
			@RequestParam(value="fugai_str",required=false)MultipartFile fugai_str,
			@RequestParam(value="xueli_str",required=false)MultipartFile xueli_str,
			@RequestParam(value="xuewei_str",required=false)MultipartFile xuewei_str,
			@RequestParam(value="xiangqing_str",required=false)MultipartFile xiangqing_str){
		if(xiaohun_str!=null){//校徽
			String path=UploadAddress.getUploadDate(xiaohun_str, request, CK_PIC);
			ckZhaoSheng.setXiaohui(path);
		}
		if(fugai_str!=null){//覆盖区域
			String path=UploadAddress.getUploadDate(fugai_str, request, CK_PIC);
			ckZhaoSheng.setFugai_quyu(path);
		}
		if(xueli_str!=null){//学历
			String path=UploadAddress.getUploadDate(xueli_str, request, CK_PIC);
			ckZhaoSheng.setXuelizhengshu(path);
		}
		if(xuewei_str!=null){//学位样本
			String path=UploadAddress.getUploadDate(xuewei_str, request, CK_PIC);
			ckZhaoSheng.setXuewei_zhengshu(path);
		}
		if(xiangqing_str!=null){//详情
			String path=UploadAddress.getUploadDate(xiangqing_str, request, CK_PIC);
			ckZhaoSheng.setXiangqing_beijing(path);
		}
		ckZhaoSheng.setTijiao_time(new Date());
		Map<String, Object> map = new HashMap<>();
		map.put("university_id", ckZhaoSheng.getWangjiao_id());
		University university=universityService.getUniversity(map);
		ckZhaoSheng.setXuexiao_name(university.getUniversity_name());
		if(ckZhaoShengService.saveCKZhaoSheng(ckZhaoSheng)){
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
	/**
	* @Title: WJZhaoShengController.java 
	* @Package com.jingren.jing.recruit.controller.wangjiao_yuanxiao 
	* @Description: TODO 修改网教页面
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月23日 上午8:21:14 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_update_wj_xuexiao.jr")
	public String to_update_wj_xuexiao(Model model,
			@RequestParam(value="zhaosheng_id",required=false)Integer zhaosheng_id){
		Map<String, Object> map = new HashMap<>();
		map.put("parent_id", 0);
		List<University> universities=universityService.getUniversityList(map);
		model.addAttribute("universities", universities);
		map.clear();
		map.put("zhaosheng_id", zhaosheng_id);
		CKZhaoSheng ckZhaoSheng=ckZhaoShengService.getCKZhaoSheng(map);
		model.addAttribute("ckZhaoSheng", ckZhaoSheng);
		return "/recruit/zhaosheng/update_wangjiao_info";
	}
	/**
	* @Title: WJZhaoShengController.java 
	* @Package com.jingren.jing.recruit.controller.wangjiao_yuanxiao 
	* @Description: TODO 修改网教信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月23日 上午8:22:18 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/update_wangjiao_xuexiao.jr")
	public void update_wangjiao_xuexiao(CKZhaoSheng ckZhaoSheng,HttpServletResponse response,HttpServletRequest request,
			@RequestParam(value="xiaohun_str",required=false)MultipartFile xiaohun_str,
			@RequestParam(value="fugai_str",required=false)MultipartFile fugai_str,
			@RequestParam(value="xueli_str",required=false)MultipartFile xueli_str,
			@RequestParam(value="xuewei_str",required=false)MultipartFile xuewei_str,
			@RequestParam(value="xiangqing_str",required=false)MultipartFile xiangqing_str){
		if(xiaohun_str!=null){//校徽
			String path=UploadAddress.getUploadDate(xiaohun_str, request, CK_PIC);
			ckZhaoSheng.setXiaohui(path);
		}
		if(fugai_str!=null){//覆盖区域
			String path=UploadAddress.getUploadDate(fugai_str, request, CK_PIC);
			ckZhaoSheng.setFugai_quyu(path);
		}
		if(xueli_str!=null){//学历
			String path=UploadAddress.getUploadDate(xueli_str, request, CK_PIC);
			ckZhaoSheng.setXuelizhengshu(path);
		}
		if(xuewei_str!=null){//学位样本
			String path=UploadAddress.getUploadDate(xuewei_str, request, CK_PIC);
			ckZhaoSheng.setXuewei_zhengshu(path);
		}
		if(xiangqing_str!=null){//详情
			String path=UploadAddress.getUploadDate(xiangqing_str, request, CK_PIC);
			ckZhaoSheng.setXiangqing_beijing(path);
		}
		Map<String, Object> map = new HashMap<>();
		map.put("university_id", ckZhaoSheng.getWangjiao_id());
		University university=universityService.getUniversity(map);
		ckZhaoSheng.setXuexiao_name(university.getUniversity_name());
		if(ckZhaoShengService.updateCKZhaoSheng(ckZhaoSheng)){
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
}
