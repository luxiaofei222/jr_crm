package com.jingren.jing.recruit.controller.material;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
/**
* @Title: MaterialController.java 
* @Package com.jingren.jing.recruit.controller.material 
* @Description: TODO 教材计划
* @author 鲁晓飞 MR.Lu   
* @date 2017年6月20日 下午2:26:38 
* @version 网校+CRM系统 V1.0
 */
import org.springframework.web.bind.annotation.RequestParam;

import com.jingren.jing.common.university.bean.ChengkaoSc;
import com.jingren.jing.common.university.bean.University;
import com.jingren.jing.common.university.service.ChengkaoScService;
import com.jingren.jing.common.university.service.UniversityService;
import com.jingren.jing.recruit.bean.chengkaoyuanxiao.CKZhaoSheng;
import com.jingren.jing.recruit.bean.material.Material;
import com.jingren.jing.recruit.service.chengkaoyuanxiao.CKZhaoShengService;
import com.jingren.jing.recruit.service.recruit.MaterialService;
import com.jingren.jing.util.Pagers;
import com.jingren.jing.util.ResponseUtils;

@Controller
@RequestMapping("material")
public class MaterialController {

	@Resource
	private MaterialService materialService;
	@Resource
	private CKZhaoShengService ckZhaoShengService;
	@Resource
	private ChengkaoScService chengkaoScService;
	@Resource
	private UniversityService universityService;

	/**
	 * @Title: MaterialController.java
	 * @Package com.jingren.jing.recruit.controller.material
	 * @Description: TODO 教材计划列表
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年6月20日 下午2:40:28
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_material_list.jr")
	public String get_material_list(Model model,
			@RequestParam(value = "zhaosheng_id", required = false) Integer zhaosheng_id,
			@RequestParam(value = "limit", required = false, defaultValue = "20") Integer limit,
			@RequestParam(value = "pageNumber", required = false, defaultValue = "1") Integer pageNumber,
			@RequestParam(value = "ckpage", required = false, defaultValue = "1") Integer ckpage) {
		Map<String, Object> map = new HashMap<>();
		map.put("pageNumber", (pageNumber - 1) * limit);
		map.put("limit", limit);
		map.put("parent_id", 0);
		map.put("zhaosheng_id", zhaosheng_id);
		CKZhaoSheng ckZhaoSheng = ckZhaoShengService.getCKZhaoSheng(map);
		model.addAttribute("ckZhaoSheng", ckZhaoSheng);
		Integer material_number = materialService.getMaterialNumber(map);
		List<Material> materials = materialService.getMaterialList(map);
		Pagers<Material> pagers = new Pagers<Material>(material_number, pageNumber, limit);
		pagers.setList(materials);
		model.addAttribute("materials", pagers);
		model.addAttribute("ckpage", ckpage);
		return "/recruit/material/material_list";
	}

	/**
	 * @Title: MaterialController.java
	 * @Package com.jingren.jing.recruit.controller.material
	 * @Description: TODO 网教招生信息计划列表
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年6月23日 上午8:30:40
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_wangjiao_material_list.jr")
	public String get_wangjiao_material_list(Model model,
			@RequestParam(value = "zhaosheng_id", required = false) Integer zhaosheng_id,
			@RequestParam(value = "limit", required = false, defaultValue = "20") Integer limit,
			@RequestParam(value = "pageNumber", required = false, defaultValue = "1") Integer pageNumber,
			@RequestParam(value = "ckpage", required = false, defaultValue = "1") Integer ckpage) {
		Map<String, Object> map = new HashMap<>();
		map.put("pageNumber", (pageNumber - 1) * limit);
		map.put("limit", limit);
		map.put("parent_id", 0);
		map.put("zhaosheng_id", zhaosheng_id);
		CKZhaoSheng ckZhaoSheng = ckZhaoShengService.getCKZhaoSheng(map);
		model.addAttribute("ckZhaoSheng", ckZhaoSheng);
		Integer material_number = materialService.getMaterialNumber(map);
		List<Material> materials = materialService.getMaterialList(map);
		Pagers<Material> pagers = new Pagers<Material>(material_number, pageNumber, limit);
		pagers.setList(materials);
		model.addAttribute("materials", pagers);
		model.addAttribute("ckpage", ckpage);
		return "/recruit/material/wangjiao_material_list";
	}

	/**
	 * @Title: MaterialController.java
	 * @Package com.jingren.jing.recruit.controller.material
	 * @Description: TODO 添加教材计划
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年6月20日 下午3:33:54
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_add_material.jr")
	public String to_add_material(Model model,
			@RequestParam(value = "zhaosheng_id", required = false) Integer zhaosheng_id) {
		Map<String, Object> map = new HashMap<>();
		map.put("zhaosheng_id", zhaosheng_id);
		CKZhaoSheng ckZhaoSheng = ckZhaoShengService.getCKZhaoSheng(map);
		if (ckZhaoSheng.getChengkao_id() != null) {// 专业
			map.clear();
			map.put("parent_id", ckZhaoSheng.getChengkao_id());
			map.put("cengci", "专升本");
			List<ChengkaoSc> chengkaoScs = chengkaoScService.getChengkaoScList(map);
			model.addAttribute("chengkaoScs", chengkaoScs);
			model.addAttribute("info", "0");
		} else {
			map.clear();
			map.put("parent_id", ckZhaoSheng.getWangjiao_id());
			map.put("university_type", "专升本");
			List<University> universities = universityService.getUniversityList(map);
			model.addAttribute("universities", universities);
			model.addAttribute("info", "1");
		}
		model.addAttribute("ckZhaoSheng", ckZhaoSheng);
		return "/recruit/material/add_material";
	}

	/**
	 * @Title: MaterialController.java
	 * @Package com.jingren.jing.recruit.controller.material
	 * @Description: TODO 获取成考专业
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年6月20日 下午5:10:46
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_chengkao_zhuanye.jr")
	public String get_chengkao_zhuanye(Model model, @RequestParam(value = "cengci", required = false) String cengci,
			@RequestParam(value = "chengkao_id", required = false) Integer chengkao_id,
			@RequestParam(value = "wangjiao_id", required = false) Integer wangjiao_id) {
		Map<String, Object> map = new HashMap<>();
		if (chengkao_id != null) {
			map.clear();
			map.put("parent_id", chengkao_id);
			map.put("cengci", cengci);
			List<ChengkaoSc> chengkaoScs = chengkaoScService.getChengkaoScList(map);
			model.addAttribute("chengkaoScs", chengkaoScs);
		} else {
			map.clear();
			map.put("parent_id", wangjiao_id);
			map.put("university_type", cengci);
			List<University> universities = universityService.getUniversityList(map);
			model.addAttribute("universities", universities);
		}
		return "/recruit/material/zhuanye";
	}

	/**
	 * @Title: MaterialController.java
	 * @Package com.jingren.jing.recruit.controller.material
	 * @Description: TODO 保存成考教材计划
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年6月20日 下午5:22:36
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/save_chengkao_material.jr")
	public void save_chengkao_material(HttpServletResponse response, Material material,
			@RequestParam(value = "info", required = false) Integer info) {
		Map<String, Object> map = new HashMap<>();
		if (info == 0) {// 成考
			map.put("chengkao_id", material.getZhuanye_id());
			ChengkaoSc chengkaoSc = chengkaoScService.getChengkaoSc(map);
			material.setZhuanye_name(chengkaoSc.getChengkao_name());
		} else {
			map.put("university_id", material.getZhuanye_id());
			University university = universityService.getUniversity(map);
			material.setZhuanye_name(university.getUniversity_zhuanye());
		}
		material.setTijiao_time(new Date());
		map.clear();
		map.put("ruxue_pici", material.getRuxue_pici());
		map.put("zhuanye_id", material.getZhuanye_id());
		map.put("zhaosheng_id", material.getZhaosheng_id());
		List<Material> materials = materialService.getMaterialList(map);
		if (materials.size() > 0) {
			ResponseUtils.renderText(response, "2");
		} else {
			if (materialService.saveMaterial(material)) {
				ResponseUtils.renderText(response, "1");
			} else {
				ResponseUtils.renderText(response, "0");
			}
		}
	}

	/**
	 * @Title: MaterialController.java
	 * @Package com.jingren.jing.recruit.controller.material
	 * @Description: TODO 修改教材计划
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年6月20日 下午5:43:49
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_update_material.jr")
	public String to_update_material(Model model,
			@RequestParam(value = "material_id", required = false) Integer material_id) {
		Map<String, Object> map = new HashMap<>();
		map.put("material_id", material_id);
		Material material = materialService.getMaterial(map);
		map.clear();
		map.put("zhaosheng_id", material.getZhaosheng_id());
		CKZhaoSheng ckZhaoSheng = ckZhaoShengService.getCKZhaoSheng(map);
		if (ckZhaoSheng.getChengkao_id() != null) {// 专业
			map.clear();
			map.put("parent_id", ckZhaoSheng.getChengkao_id());
			map.put("cengci", material.getCengci());
			List<ChengkaoSc> chengkaoScs = chengkaoScService.getChengkaoScList(map);
			model.addAttribute("chengkaoScs", chengkaoScs);
			model.addAttribute("info", "0");
		} else {
			map.clear();
			map.put("parent_id", ckZhaoSheng.getWangjiao_id());
			map.put("university_type", "专升本");
			List<University> universities = universityService.getUniversityList(map);
			model.addAttribute("universities", universities);
			model.addAttribute("info", "1");
		}
		model.addAttribute("material", material);
		model.addAttribute("ckZhaoSheng", ckZhaoSheng);
		return "/recruit/material/update_material";
	}

	/**
	 * @Title: MaterialController.java
	 * @Package com.jingren.jing.recruit.controller.material
	 * @Description: TODO 修改教材计划
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年6月20日 下午5:59:04
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/update_chengkao_material.jr")
	public void update_chengkao_material(HttpServletResponse response, Material material,
			@RequestParam(value = "info", required = false) Integer info) {
		Map<String, Object> map = new HashMap<>();
		map.put("material_id", material.getMaterial_id());
		Material material_old = materialService.getMaterial(map);// 开始的教材计划
		if (material_old.getZhuanye_id() != material.getZhuanye_id()) {
			if (info == 0) {// 成考
				map.put("chengkao_id", material.getZhuanye_id());
				ChengkaoSc chengkaoSc = chengkaoScService.getChengkaoSc(map);
				material.setZhuanye_name(chengkaoSc.getChengkao_name());
			} else {
				map.put("university_id", material.getZhuanye_id());
				University university = universityService.getUniversity(map);
				material.setZhuanye_name(university.getUniversity_zhuanye());
			}
			map.clear();
			map.put("ruxue_pici", material.getRuxue_pici());
			map.put("zhuanye_id", material.getZhuanye_id());
			map.put("zhaosheng_id", material.getZhaosheng_id());
			List<Material> materials = materialService.getMaterialList(map);
			if (materials.size() > 0) {
				ResponseUtils.renderText(response, "2");
			} else {
				if (materialService.updateMaterial(material)) {
					ResponseUtils.renderText(response, "1");
				} else {
					ResponseUtils.renderText(response, "0");
				}
			}
		} else {
			if (materialService.updateMaterial(material)) {
				ResponseUtils.renderText(response, "1");
			} else {
				ResponseUtils.renderText(response, "0");
			}
		}
	}

	/**
	 * @Title: MaterialController.java
	 * @Package com.jingren.jing.recruit.controller.material
	 * @Description: TODO 添加教材信息页面
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年6月21日 上午8:53:52
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_add_jiaocai.jr")
	public String to_add_jiaocai(Model model,
			@RequestParam(value = "material_id", required = false) Integer material_id,
			@RequestParam(value = "xuexiao_name", required = false) String xuexiao_name) {
		Map<String, Object> map = new HashMap<>();
		map.put("material_id", material_id);
		Material material = materialService.getMaterial(map);
		model.addAttribute("material", material);
		model.addAttribute("xuexiao_name", xuexiao_name);
		return "/recruit/material/add_textbook";
	}

	/**
	 * @Title: MaterialController.java
	 * @Package com.jingren.jing.recruit.controller.material
	 * @Description: TODO 保存教材
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年6月21日 上午8:56:57
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/save_jiaocai.jr")
	public void save_jiaocai(HttpServletResponse response, Material material) {
		Map<String, Object> map = new HashMap<>();
		map.put("material_id", material.getParent_id());
		Material material_old = materialService.getMaterial(map);
		material.setCengci(material_old.getCengci());
		material.setZhuanye_name(material_old.getZhuanye_name());
		material.setZhaosheng_id(material_old.getZhaosheng_id());
		material.setRuxue_pici(material_old.getRuxue_pici());
		material.setTijiao_time(new Date());
		if (materialService.saveMaterial(material)) {
			ResponseUtils.renderText(response, "1");
		} else {
			ResponseUtils.renderText(response, "0");
		}
	}

	/**
	 * @Title: MaterialController.java
	 * @Package com.jingren.jing.recruit.controller.material
	 * @Description: TODO 查看教材信息
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年6月21日 上午9:09:15
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_check_jiaocai.jr")
	public String to_check_jiaocai(Model model,
			@RequestParam(value = "material_id", required = false) Integer material_id) {
		Map<String, Object> map = new HashMap<>();
		map.put("material_id", material_id);
		Material material = materialService.getMaterial(map);
		model.addAttribute("material", material);
		// map.clear();
		// map.put("zhaosheng_id", material.getZhaosheng_id());
		// CKZhaoSheng ckZhaoSheng=ckZhaoShengService.getCKZhaoSheng(map);
		// model.addAttribute("ckZhaoSheng", ckZhaoSheng);
		map.clear();
		map.put("parent_id", material_id);
		List<Material> materials = materialService.getMaterialList(map);
		model.addAttribute("materials", materials);
		return "/recruit/material/check_jiaocai";
	}

	/**
	 * @Title: MaterialController.java
	 * @Package com.jingren.jing.recruit.controller.material
	 * @Description: TODO 删除教材信息
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年6月21日 下午1:43:23
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/delete_material.jr")
	public void delete_material(HttpServletResponse response,
			@RequestParam(value = "material_id", required = false) Integer material_id) {
		Map<String, Object> map = new HashMap<>();
		map.put("material_id", material_id);
		if (materialService.deleteMaterial(map)) {
			map.clear();
			map.put("parent_id", material_id);
			materialService.deleteMaterial(map);
			ResponseUtils.renderText(response, "1");
		} else {
			ResponseUtils.renderText(response, "0");
		}
	}
}
