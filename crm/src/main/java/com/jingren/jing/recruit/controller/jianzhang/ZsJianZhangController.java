package com.jingren.jing.recruit.controller.jianzhang;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jingren.jing.recruit.bean.chengkaoyuanxiao.CKZhaoSheng;
import com.jingren.jing.recruit.bean.jianzhang.ZsJianzhang;
import com.jingren.jing.recruit.service.chengkaoyuanxiao.CKZhaoShengService;
import com.jingren.jing.recruit.service.jianzhang.ZsJianzhangService;
import com.jingren.jing.util.DeleteFile;
import com.jingren.jing.util.ResponseUtils;
import com.jingren.jing.util.UploadAddress;
/**
* @Title: ZsJianZhangController.java 
* @Package com.jingren.jing.recruit.controller.jianzhang 
* @Description: TODO 招生简章
* @author 鲁晓飞 MR.Lu   
* @date 2017年6月23日 下午3:35:29 
* @version 网校+CRM系统 V1.0
 */
@Controller
@RequestMapping("zs_jianzhang")
public class ZsJianZhangController {

	@Resource
	private CKZhaoShengService ckZhaoShengService;
	@Resource
	private ZsJianzhangService jianzhangService;
	private static final String UP_FRONT_FILE ="file/zsjz";
	
	/**
	* @Title: ZsJianZhangController.java 
	* @Package com.jingren.jing.recruit.controller.jianzhang 
	* @Description: TODO 编辑器本地上传
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月23日 下午3:35:01 
	* @version 网校+CRM系统 V1.0
	 */
	@ResponseBody
	@RequestMapping("/uploadpic.jr")
	public Map<String, Object> uploadpic(HttpServletResponse response,
			HttpServletRequest request,
			@RequestParam(value = "ajaxTaskFile", required = false) MultipartFile ajaxTaskFile){
		Map<String,Object> map=new HashMap<>();
		String path = UploadAddress.getUploadDate(ajaxTaskFile, request,
				UP_FRONT_FILE);
//		System.out.println(path);
		map.put("file_path", "http://www.jingrenedu.com"+path);
		return map;
	}
	/**
	* @Title: ZsJianZhangController.java 
	* @Package com.jingren.jing.recruit.controller.jianzhang 
	* @Description: TODO 添加招生简章页面
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月26日 下午3:15:40 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_add_zsjianzhang.jr")
	public String to_add_zsjianzhang(Model model,
			@RequestParam(value="zhaosheng_id",required=false)Integer zhaosheng_id){
		Map<String, Object> map=new HashMap<>();
		map.put("zhaosheng_id", zhaosheng_id);
		CKZhaoSheng ckZhaoSheng=ckZhaoShengService.getCKZhaoSheng(map);
		model.addAttribute("ckZhaoSheng", ckZhaoSheng);
		return "/recruit/zs_jianzhang/add_jianzhang";
	}
	/**
	* @Title: ZsJianZhangController.java 
	* @Package com.jingren.jing.recruit.controller.jianzhang 
	* @Description: TODO 保存招生简章
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月26日 下午5:03:04 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/save_jianzhang.jr")
	public void save_jianzhang(ZsJianzhang jianzhang,HttpServletResponse response){
		Map<String, Object> map=new HashMap<>();
		map.put("zhaosheng_id", jianzhang.getZhaosheng_id());
		List<ZsJianzhang> jianzhangs=jianzhangService.getZsJianzhangList(map);
		if(jianzhangs.size()>0){
			jianzhang.setNianfen(jianzhangs.get(0).getNianfen());
		}
		jianzhang.setTijiao_time(new Date());
		if(jianzhangService.saveZsJianzhang(jianzhang)){
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
	/**
	* @Title: ZsJianZhangController.java 
	* @Package com.jingren.jing.recruit.controller.jianzhang 
	* @Description: TODO 上传招生简章文件
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月26日 下午5:15:45 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_upload_zs_jianzhang.jr")
	public String to_upload_zs_jianzhang(Model model,
			@RequestParam(value="zhaosheng_id",required=false)Integer zhaosheng_id){
		Map<String, Object> map=new HashMap<>();
		map.put("zhaosheng_id", zhaosheng_id);
		CKZhaoSheng ckZhaoSheng=ckZhaoShengService.getCKZhaoSheng(map);
		map.put("jianzhang_file", "file");
		List<ZsJianzhang> jianzhangs=jianzhangService.getZsJianzhangList(map);
		model.addAttribute("jianzhangs", jianzhangs);
		model.addAttribute("ckZhaoSheng", ckZhaoSheng);
		return "/recruit/zs_jianzhang/upload_jianzhang";
	}
	/**
	* @Title: ZsJianZhangController.java 
	* @Package com.jingren.jing.recruit.controller.jianzhang 
	* @Description: TODO 上传简章文件
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月26日 下午6:35:27 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/upload_jianzhang.jr")
	public void  upload_jianzhang(HttpServletResponse response,HttpServletRequest request,
			ZsJianzhang zsJianzhang,@RequestParam(value="file_upload",required=false)MultipartFile file_upload){
		if(file_upload!=null){
			String path=UploadAddress.getUploadDate(file_upload, request, UP_FRONT_FILE);
			zsJianzhang.setJianzhang_file(path);
		}
		Map<String, Object> map=new HashMap<>();
		map.put("zhaosheng_id", zsJianzhang.getZhaosheng_id());
		List<ZsJianzhang> jianzhangs=jianzhangService.getZsJianzhangList(map);
		if(jianzhangs.size()>0){//判断年份
			zsJianzhang.setNianfen(jianzhangs.get(0).getNianfen());
		}
		map.put("jianzhang_file", "file");
		List<ZsJianzhang> jianzhangs_files=jianzhangService.getZsJianzhangList(map);
		if(jianzhangs_files.size()>0){//判断文件
			DeleteFile.deleteFile1(jianzhangs_files.get(0).getJianzhang_file(), request);
			ZsJianzhang zsJianzhang2=new ZsJianzhang();
			zsJianzhang2.setJianzhang_id(jianzhangs_files.get(0).getJianzhang_id());
			jianzhangService.deleteZsJianzhang(zsJianzhang2);
		}
		zsJianzhang.setTijiao_time(new Date());
		if(jianzhangService.saveZsJianzhang(zsJianzhang)){
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
	/**
	* @Title: ZsJianZhangController.java 
	* @Package com.jingren.jing.recruit.controller.jianzhang 
	* @Description: TODO 删除招生简章
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月26日 下午7:10:30 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("delete_jianzhang.jr")
	public void  delete_jianzhang(HttpServletResponse response,HttpServletRequest request,
			@RequestParam(value="jianzhang_id",required=false) Integer jianzhang_id){
		Map<String, Object> map=new HashMap<>();
		map.put("jianzhang_id", jianzhang_id);
		ZsJianzhang zsJianzhang=jianzhangService.getZsJianzhang(map);
		if(zsJianzhang.getJianzhang_file()!=null){
			DeleteFile.deleteFile1(zsJianzhang.getJianzhang_file(), request);
		}
		ZsJianzhang zsJianzhang2=new ZsJianzhang();
		zsJianzhang2.setJianzhang_id(jianzhang_id);
		if(jianzhangService.deleteZsJianzhang(zsJianzhang2)){
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
	/**
	* @Title: ZsJianZhangController.java 
	* @Package com.jingren.jing.recruit.controller.jianzhang 
	* @Description: TODO 查看招生简章
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月27日 上午8:37:21 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_check_zs_jianzhang.jr")
	public String to_check_zs_jianzhang(Model model,
			@RequestParam(value="zhaosheng_id",required=false)Integer zhaosheng_id){
		Map<String, Object> map=new HashMap<>();
		map.put("zhaosheng_id", zhaosheng_id);
		List<ZsJianzhang> jianzhangs=jianzhangService.getZsJianzhangList(map);
		model.addAttribute("jianzhangs", jianzhangs);
		CKZhaoSheng ckZhaoSheng=ckZhaoShengService.getCKZhaoSheng(map);
		model.addAttribute("ckZhaoSheng", ckZhaoSheng);
		return "/recruit/zs_jianzhang/check_jianzhang";
	}
}
