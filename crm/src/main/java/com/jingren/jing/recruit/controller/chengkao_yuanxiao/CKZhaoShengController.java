package com.jingren.jing.recruit.controller.chengkao_yuanxiao;

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
import com.jingren.jing.common.university.service.ChengkaoScService;
import com.jingren.jing.recruit.bean.chengkaoyuanxiao.CKZhaoSheng;
import com.jingren.jing.recruit.bean.help.HelpCenter;
import com.jingren.jing.recruit.bean.jianzhang.ZsJianzhang;
import com.jingren.jing.recruit.service.chengkaoyuanxiao.CKZhaoShengService;
import com.jingren.jing.recruit.service.help.HelpCenterService;
import com.jingren.jing.recruit.service.jianzhang.ZsJianzhangService;
import com.jingren.jing.recruit.service.recruit.MaterialService;
import com.jingren.jing.util.DeleteFile;
import com.jingren.jing.util.Pagers;
import com.jingren.jing.util.ResponseUtils;
import com.jingren.jing.util.UploadAddress;

/**
* @Title: CKZhaoShengController.java 
* @Package com.jingren.jing.recruit.controller.chengkao_yuanxiao 
* @Description: TODO 成考招生相关
* @author 鲁晓飞 MR.Lu   
* @date 2017年6月19日 上午10:38:49 
* @version 网校+CRM系统 V1.0
 */
@Controller
@RequestMapping("ck_zhaosheng")
public class CKZhaoShengController {

	@Resource
	private ChengkaoScService chengkaoScService;
	@Resource
	private CKZhaoShengService ckZhaoShengService;
	@Resource
	private MaterialService materialService;
	@Resource
	private HelpCenterService helpCenterService;
	@Resource
	private ZsJianzhangService jianzhangService;
	private static final String CK_PIC ="images/xuexiao/chengkao";
	private static final String YUAN_LI ="file/yuanli";
	
	/**
	* @Title: CKZhaoShengController.java 
	* @Package com.jingren.jing.recruit.controller.chengkao_yuanxiao 
	* @Description: TODO 成考招生列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月19日 上午10:47:39 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_chengkao_zhaosheng_list.jr")
	public String get_chengkao_zhaosheng_list(Model model,
			@RequestParam(value = "limit", required = false,defaultValue="20")Integer limit,
			@RequestParam(value = "pageNumber", required = false,defaultValue="1")Integer pageNumber){
		Map<String, Object> map = new HashMap<>();
		map.put("pageNumber", (pageNumber - 1) * limit);
		map.put("limit", limit);
		map.put("xuexiao_type", "成考");
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
		return "/recruit/zhaosheng/chengkao_zhaosheng";
	}
	
	/**
	* @Title: CKZhaoShengController.java 
	* @Package com.jingren.jing.recruit.controller.chengkao_yuanxiao 
	* @Description: TODO 添加成考介绍页面
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月19日 下午3:59:14 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_add_chengkao_info.jr")
	public String get_add_chengkao_info(Model model){
		Map<String, Object> map = new HashMap<>();
		map.put("parent_id", 0);
		List<ChengkaoSc> chengkaoScs=chengkaoScService.getChengkaoScList(map);
		model.addAttribute("chengkaoScs", chengkaoScs);
		return "/recruit/zhaosheng/add_chengkao_info";
	}
	
	/**
	* @Title: CKZhaoShengController.java 
	* @Package com.jingren.jing.recruit.controller.chengkao_yuanxiao 
	* @Description: TODO 保存成考学校信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月19日 下午4:27:32 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/save_chengkao_xuexiao.jr")
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
		map.put("chengkao_id", ckZhaoSheng.getChengkao_id());
		ChengkaoSc chengkaoSc=chengkaoScService.getChengkaoSc(map);
		ckZhaoSheng.setXuexiao_name(chengkaoSc.getChengkao_name());
		if(ckZhaoShengService.saveCKZhaoSheng(ckZhaoSheng)){
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
	/**
	* @Title: CKZhaoShengController.java 
	* @Package com.jingren.jing.recruit.controller.chengkao_yuanxiao 
	* @Description: TODO 修改成考学校
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月19日 下午5:03:51 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/update_chengkao_xuexiao.jr")
	public void update_chengkao_xuexiao(CKZhaoSheng ckZhaoSheng,HttpServletResponse response,HttpServletRequest request,
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
		map.put("chengkao_id", ckZhaoSheng.getChengkao_id());
		ChengkaoSc chengkaoSc=chengkaoScService.getChengkaoSc(map);
		ckZhaoSheng.setXuexiao_name(chengkaoSc.getChengkao_name());
		if(ckZhaoShengService.updateCKZhaoSheng(ckZhaoSheng)){
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
	
	/**
	* @Title: CKZhaoShengController.java 
	* @Package com.jingren.jing.recruit.controller.chengkao_yuanxiao 
	* @Description: TODO 设置学校是否展示
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月19日 下午5:06:39 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/change_state.jr")
	public void change_state(CKZhaoSheng ckZhaoSheng,HttpServletResponse response){
		if(ckZhaoShengService.updateCKZhaoSheng(ckZhaoSheng)){
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
	/**
	* @Title: CKZhaoShengController.java 
	* @Package com.jingren.jing.recruit.controller.chengkao_yuanxiao 
	* @Description: TODO 修改成考招生信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月19日 下午5:14:37 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_update_ck_xuexiao.jr")
	public String to_update_ck_xuexiao(Model model,
			@RequestParam(value="zhaosheng_id",required=false)Integer zhaosheng_id){
		Map<String, Object> map = new HashMap<>();
		map.put("parent_id", 0);
		List<ChengkaoSc> chengkaoScs=chengkaoScService.getChengkaoScList(map);
		model.addAttribute("chengkaoScs", chengkaoScs);
		map.clear();
		map.put("zhaosheng_id", zhaosheng_id);
		CKZhaoSheng ckZhaoSheng=ckZhaoShengService.getCKZhaoSheng(map);
		model.addAttribute("ckZhaoSheng", ckZhaoSheng);
		return "/recruit/zhaosheng/update_chengkao_info";
	}
	/**
	* @Title: CKZhaoShengController.java 
	* @Package com.jingren.jing.recruit.controller.chengkao_yuanxiao 
	* @Description: TODO 院历编辑页面
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月21日 下午2:58:29 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_add_yuanli.jr")
	public String to_add_yuanli(Model model,
			@RequestParam(value="zhaosheng_id",required=false)Integer zhaosheng_id){
		Map<String, Object> map = new HashMap<>();
		map.put("zhaosheng_id", zhaosheng_id);
		CKZhaoSheng ckZhaoSheng=ckZhaoShengService.getCKZhaoSheng(map);
		model.addAttribute("ckZhaoSheng", ckZhaoSheng);
		return "/recruit/material/bianji_yuanli";
	}
	/**
	* @Title: CKZhaoShengController.java 
	* @Package com.jingren.jing.recruit.controller.chengkao_yuanxiao 
	* @Description: TODO 保存院历
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月21日 下午5:42:54 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/save_yuanli.jr")
	public void save_yuanli(CKZhaoSheng ckZhaoSheng,HttpServletResponse response,HttpServletRequest request,
			@RequestParam(value="file_upload",required=false)MultipartFile file_upload,
			@RequestParam(value="yuanlifile",required=false)MultipartFile yuanlifile,
			@RequestParam(value="shoucefile",required=false)MultipartFile shoucefile,
			@RequestParam(value="monitifile",required=false)MultipartFile monitifile){
		Map<String, Object> map = new HashMap<>();
		map.put("zhaosheng_id", ckZhaoSheng.getZhaosheng_id());
		CKZhaoSheng ckZhaoSheng_old=ckZhaoShengService.getCKZhaoSheng(map);
		if(file_upload!=null){
			String path=UploadAddress.getUploadDate(file_upload, request, YUAN_LI);
			ckZhaoSheng.setYuanli_pic(path);
		}
		if(yuanlifile!=null){
			String path=UploadAddress.getUploadDate(yuanlifile, request, YUAN_LI);
			ckZhaoSheng.setYuanli_file(path);
		}
		if(shoucefile!=null){
			String path=UploadAddress.getUploadDate(shoucefile, request, YUAN_LI);
			ckZhaoSheng.setXuesheng_shouce(path);
		}
		if(monitifile!=null){
			String path=UploadAddress.getUploadDate(monitifile, request, YUAN_LI);
			ckZhaoSheng.setMoniti(path);
		}
		if(ckZhaoShengService.updateCKZhaoSheng(ckZhaoSheng)){
			if(ckZhaoSheng_old.getYuanli_file()!=null){
				DeleteFile.deleteFile1(ckZhaoSheng_old.getYuanli_file(), request);
			}
			if(ckZhaoSheng_old.getYuanli_pic()!=null){
				DeleteFile.deleteFile1(ckZhaoSheng_old.getYuanli_pic(), request);
			}
			if(ckZhaoSheng_old.getXuesheng_shouce()!=null){
				DeleteFile.deleteFile1(ckZhaoSheng_old.getXuesheng_shouce(), request);
			}
			if(ckZhaoSheng_old.getMoniti()!=null){
				DeleteFile.deleteFile1(ckZhaoSheng_old.getMoniti(), request);
			}
			ResponseUtils.renderText(response, "1");
		}
	}
	/**
	* @Title: CKZhaoShengController.java 
	* @Package com.jingren.jing.recruit.controller.chengkao_yuanxiao 
	* @Description: TODO 删除招生信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月23日 下午2:49:19 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/delete_zhaosheng.jr")
	public void delete_zhaosheng(HttpServletResponse response,HttpServletRequest request,
			@RequestParam(value = "zhaosheng_id", required = false) Integer zhaosheng_id) {
		Map<String, Object> map = new HashMap<>();
		map.put("zhaosheng_id", zhaosheng_id);
		CKZhaoSheng ckZhaoSheng_old=ckZhaoShengService.getCKZhaoSheng(map);
		if (ckZhaoShengService.deleteCKZhaoSheng(zhaosheng_id)) {
			materialService.deleteMaterial(map);
			helpCenterService.deleteHelpCenter(map);
			ZsJianzhang zsJianzhang=new ZsJianzhang();
			zsJianzhang.setZhaosheng_id(zhaosheng_id);
			jianzhangService.deleteZsJianzhang(zsJianzhang);
			if(ckZhaoSheng_old.getYuanli_file()!=null){
				DeleteFile.deleteFile1(ckZhaoSheng_old.getYuanli_file(), request);
			}
			if(ckZhaoSheng_old.getYuanli_pic()!=null){
				DeleteFile.deleteFile1(ckZhaoSheng_old.getYuanli_pic(), request);
			}
			if(ckZhaoSheng_old.getXuesheng_shouce()!=null){
				DeleteFile.deleteFile1(ckZhaoSheng_old.getXuesheng_shouce(), request);
			}
			if(ckZhaoSheng_old.getMoniti()!=null){
				DeleteFile.deleteFile1(ckZhaoSheng_old.getMoniti(), request);
			}
			ResponseUtils.renderText(response, "1");
		} else {
			ResponseUtils.renderText(response, "0");
		}
	}
}
