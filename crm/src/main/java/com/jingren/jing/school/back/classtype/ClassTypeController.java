package com.jingren.jing.school.back.classtype;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
/**
* @Title: ClassTypeController.java 
* @Package com.jingren.jing.school.back.classtype 
* @Description: TODO 课程班型介绍
* @author 鲁晓飞 MR.Lu   
* @date 2016年12月14日 上午11:12:33 
* @version 网校+CRM系统 V1.0
 */
import org.springframework.web.bind.annotation.RequestParam;

import com.jingren.jing.school.bean.classtype.ClassType;
import com.jingren.jing.school.bean.course.CourseMenu;
import com.jingren.jing.school.bean.dictionary.Dictionary;
import com.jingren.jing.school.service.classtype.ClassTypeService;
import com.jingren.jing.school.service.course.CourseMenuService;
import com.jingren.jing.school.service.dictionary.DictionaryService;
import com.jingren.jing.util.Pagers;
import com.jingren.jing.util.ResponseUtils;
@Controller
@RequestMapping("back_class")
public class ClassTypeController {

	@Resource
	private CourseMenuService courseMenuService;
	@Resource
	private DictionaryService dictionaryService;
	@Resource
	private ClassTypeService classTypeService;
	/**
	* @Title: ClassTypeController.java 
	* @Package com.jingren.jing.school.back.classtype 
	* @Description: TODO 班型
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月14日 上午11:21:28 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_class_type.jr")
	public String  get_class_type(Model model,
			@RequestParam(value="class_name",required=false)String class_name,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "pageNumber", required = false) Integer pageNumber){
		Map<String, Object> map=new HashMap<>();
		if(StringUtils.isNotBlank(class_name)){
			map.put("class_name", class_name);
		}
		map.put("pageNumber", (pageNumber - 1) * limit);
		map.put("limit", limit);
		List<ClassType> classTypes=classTypeService.getClassTypeList(map);
		Integer clInteger=classTypeService.getClassTypeNumber(map);
		for (ClassType classType : classTypes) {
			map.clear();
			map.put("course_id", classType.getCourse_id());
			CourseMenu courseMenu=courseMenuService.getCouerseMenu(map);
			classType.setCourseMenu(courseMenu);
		}
		Pagers<ClassType> pagers = new Pagers<ClassType>(clInteger,
				pageNumber, limit);
		pagers.setList(classTypes);
		model.addAttribute("classTypes", pagers);
		return "/classtype/classtype";
	}
	/**
	* @Title: ClassTypeController.java 
	* @Package com.jingren.jing.school.back.classtype 
	* @Description: TODO 打开添加班型窗口
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月14日 上午11:55:46 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_add_class_type.jr")
	public String to_add_class_type(Model model){
		Map<String, Object> map=new HashMap<>();
		map.put("course_leavl", 1);
		List<CourseMenu> courseMenus=courseMenuService.getCourserMenuList(map);
		model.addAttribute("courseMenus", courseMenus);
		map.clear();
		map.put("course_parent_id", courseMenus.get(0).getCourse_id());//默认第一个课程二级分类
		List<CourseMenu> coursesubMenus=courseMenuService.getCourserMenuList(map);
		model.addAttribute("coursesubMenus", coursesubMenus);
		map.clear();
		map.put("dictionary_parent_id", 1);
		List<Dictionary> dictionaries=dictionaryService.getDictionaryList(map);
		model.addAttribute("dictionaries", dictionaries);
		return "/classtype/add_class_type";
	}
	/**
	* @Title: ClassTypeController.java 
	* @Package com.jingren.jing.school.back.classtype 
	* @Description: TODO 添加班型
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月14日 下午12:53:18 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/save_class_type.jr")
	public void save_class_type(HttpServletResponse response,ClassType classType){
		classType.setClass_time(new Date());
		Map<String, Object> map=new HashMap<>();
		map.put("dictionary_id", classType.getDictionary_id());
		Dictionary dictionary=dictionaryService.getDictionary(map);
		classType.setClass_name(dictionary.getDictionary_name());
		map.put("course_id", classType.getCourse_id());
		ClassType classType2=classTypeService.getClassType(map);
		if(classType2!=null){
			//已经添加过
			ResponseUtils.renderText(response, "2");
		}else{
			if(classTypeService.saveClassType(classType)){
				ResponseUtils.renderText(response, "1");
			}else{
				ResponseUtils.renderText(response, "0");
			}
		}
	}
	/**
	* @Title: ClassTypeController.java 
	* @Package com.jingren.jing.school.back.classtype 
	* @Description: TODO 删除班型介绍
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月14日 下午1:19:04 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/delete_class_type.jr")
	public void delete_class_type(HttpServletResponse response,
			@RequestParam(value="str",required=false)String str){
		String[] array=str.split(",");
		for (String string : array) {
			int id=Integer.valueOf(string);
			classTypeService.deleteClassType(id);
		}
		ResponseUtils.renderText(response, "1");
	}
	/**
	* @Title: ClassTypeController.java 
	* @Package com.jingren.jing.school.back.classtype 
	* @Description: TODO 修改班型弹窗
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月14日 下午1:33:10 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_update_class_type.jr")
	public String to_update_class_type(Model model,
			@RequestParam(value="class_id",required=false)Integer class_id){
		Map<String, Object> map=new HashMap<>();
		map.put("course_leavl", 1);
		List<CourseMenu> courseMenus=courseMenuService.getCourserMenuList(map);
		model.addAttribute("courseMenus", courseMenus);
		map.clear();
		map.put("course_parent_id", courseMenus.get(0).getCourse_id());//默认第一个课程二级分类
		List<CourseMenu> coursesubMenus=courseMenuService.getCourserMenuList(map);
		model.addAttribute("coursesubMenus", coursesubMenus);
		map.clear();
		map.put("dictionary_parent_id", 1);
		List<Dictionary> dictionaries=dictionaryService.getDictionaryList(map);
		model.addAttribute("dictionaries", dictionaries);
		map.clear();
		map.put("class_id", class_id);
		ClassType classType=classTypeService.getClassType(map);
		model.addAttribute("classType", classType);
		return "/classtype/update_class_type";
	}
	/**
	* @Title: ClassTypeController.java 
	* @Package com.jingren.jing.school.back.classtype 
	* @Description: TODO 修改班型介绍
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月14日 下午1:34:03 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/update_class_type.jr")
	public void update_class_type(HttpServletResponse response,ClassType classType){
		if(classTypeService.updateClassType(classType)){
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
	/**
	* @Title: ClassTypeController.java 
	* @Package com.jingren.jing.school.back.classtype 
	* @Description: TODO 查看班型介绍
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月14日 下午1:50:12 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_check_class.jr")
	public String to_check_class(Model model,
			@RequestParam(value="class_id",required=false)Integer class_id){
		Map<String, Object> map=new HashMap<>();
		map.put("class_id", class_id);
		ClassType classType=classTypeService.getClassType(map);
		model.addAttribute("classType", classType);
		return "/classtype/check_classtype";
	}
}
