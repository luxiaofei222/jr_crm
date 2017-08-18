package com.jingren.jing.school.back.advertising;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
/**
* @Title: AdvertisingController.java 
* @Package com.jingren.jing.school.back.advertising 
* @Description: TODO 广告管理
* @author 鲁晓飞 MR.Lu   
* @date 2016年12月7日 下午3:48:08 
* @version 网校+CRM系统 V1.0
 */
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jingren.jing.school.bean.advertising.Advertising;
import com.jingren.jing.school.service.advertising.AdvertisingService;
import com.jingren.jing.util.DeleteFile;
import com.jingren.jing.util.Pagers;
import com.jingren.jing.util.ResponseUtils;
import com.jingren.jing.util.UploadAddress;
@Controller
@RequestMapping("back_adver")
public class BackAdvertisingController {

	@Resource
	private AdvertisingService advertisingService;
	private static final String uploadfilepath = "images/school/back/schoole_ba";
	/**
	* @Title: BackAdvertisingController.java 
	* @Package com.jingren.jing.school.back.advertising 
	* @Description: TODO 广告列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月7日 下午3:55:26 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_advertising_main.jr")
	public String  get_advertising_main(Model model,
			@RequestParam(value="adver_type",required=false)String adver_type,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "pageNumber", required = false) Integer pageNumber){
		Map<String, Object> map = new HashMap<>();
		if(StringUtils.isNotBlank(adver_type)){
			map.put("adver_type", adver_type);
		}
		map.put("pageNumber", (pageNumber - 1) * limit);
		map.put("limit", limit);
		List<Advertising> advertisings=advertisingService.getAdvertisingList(map);
		Integer adver_number=advertisingService.getAdvertisingNumber(map);
		Pagers<Advertising> pagers = new Pagers<Advertising>(adver_number, pageNumber, limit);
		pagers.setList(advertisings);
		model.addAttribute("advertisings", pagers);
		return "/advertising/advertising";
	}
	/**
	* @Title: BackAdvertisingController.java 
	* @Package com.jingren.jing.school.back.advertising 
	* @Description: TODO 添加广告信息弹窗
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月7日 下午4:34:32 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_save_adver.jr")
	public String to_save_adver(){
		return "/advertising/add_adver";
	}
	/**
	* @Title: BackAdvertisingController.java 
	* @Package com.jingren.jing.school.back.advertising 
	* @Description: TODO 保存广告信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月7日 下午4:51:27 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/save_adver.jr")
	public void save_adver(HttpServletRequest request,HttpServletResponse response,Advertising advertising,
			@RequestParam(value = "file_pic", required = false) MultipartFile file_pic){
		if(file_pic!=null){
			String path=UploadAddress.getUploadDate(file_pic, request, uploadfilepath);
			advertising.setAdver_pic(path);
			advertising.setAdver_time(new Date());
			if(advertisingService.saveAdvertising(advertising)){
				ResponseUtils.renderText(response, "1");
			}else{
				ResponseUtils.renderText(response, "0");
			}
		}
	}
	/**
	* @Title: BackAdvertisingController.java 
	* @Package com.jingren.jing.school.back.advertising 
	* @Description: TODO 删除广告信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月7日 下午5:10:33 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/delete_adver.jr")
	public void delete_adver(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="adver_id",required=false)Integer adver_id){
		Map<String, Object> map=new HashMap<>();
		map.put("adver_id", adver_id);
		Advertising advertising=advertisingService.getAdvertising(map);
		if(advertising.getAdver_pic()!=null){
			if(DeleteFile.findfile(advertising.getAdver_pic(), request)){
				DeleteFile.deleteFile1(advertising.getAdver_pic(), request);
			}
		}
		if(advertisingService.deleteAdvertising(adver_id)){
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
	/**
	* @Title: BackAdvertisingController.java 
	* @Package com.jingren.jing.school.back.advertising 
	* @Description: TODO 设置广告上架 下架
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月7日 下午5:13:17 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/update_isshow.jr")
	public void update_isshow(HttpServletResponse response,Advertising advertising){
		if(advertisingService.updateAdvertising(advertising)){
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
	/**
	* @Title: BackAdvertisingController.java 
	* @Package com.jingren.jing.school.back.advertising 
	* @Description: TODO 去修改
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月28日 下午5:23:16 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_update_adver.jr")
	public String  to_update_adver(Model model,
			@RequestParam(value="adver_id",required=false)Integer adver_id){
		Map<String, Object> map=new HashMap<>();
		map.put("adver_id", adver_id);
		Advertising advertising=advertisingService.getAdvertising(map);
		model.addAttribute("advertising", advertising);
		return "/advertising/update_adver";
	}
}
