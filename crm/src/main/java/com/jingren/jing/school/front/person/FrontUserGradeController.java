package com.jingren.jing.school.front.person;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
/**
* @Title: FrontUserGradeController.java 
* @Package com.jingren.jing.school.front.person 
* @Description: TODO 学员成绩查询
* @author 鲁晓飞 MR.Lu   
* @date 2017年8月18日 上午11:25:26 
* @version 网校+CRM系统 V1.0
 */

import com.jingren.jing.common.university.bean.Review;
import com.jingren.jing.common.university.service.ReviewService;
import com.jingren.jing.educational.bean.grade.UserGrade;
import com.jingren.jing.educational.service.grade.UserGradeService;
import com.jingren.jing.school.bean.course.CourseMenu;
import com.jingren.jing.school.bean.dictionary.Dictionary;
import com.jingren.jing.school.bean.user.User;
import com.jingren.jing.school.service.course.CourseMenuService;
import com.jingren.jing.school.service.dictionary.DictionaryService;
@Controller
@RequestMapping("f_grade")
public class FrontUserGradeController {

	@Resource
	private UserGradeService userGradeService;
	@Resource
	private CourseMenuService courseMenuService;
	@Resource
	private DictionaryService dictionaryService;
	@Resource
	private ReviewService reviewService;
	
	/**
	* @Title: FrontUserGradeController.java 
	* @Package com.jingren.jing.school.front.person 
	* @Description: TODO 获取用户成绩信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年8月18日 上午11:26:45 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_user_grade.html")
	public String get_user_grade(HttpSession session,Model model,HttpServletRequest request){
		User user=(User) session.getAttribute("user_session");
		if (user != null) {
			Map<String, Object> map = new HashMap<>();
			map.put("user_phone", user.getUser_phone());
			List<UserGrade> grades=userGradeService.getUserGradeList(map);
			if(grades.size()>0){
				for (UserGrade userGrade : grades) {
					if(userGrade.getCourse_id()!=null){
						map.clear();
						map.put("course_id", userGrade.getCourse_id());
						CourseMenu courseMenu=courseMenuService.getCouerseMenu(map);
						userGrade.setCourseMenu(courseMenu);
					}
					
					if(userGrade.getDictionary_id()!=null){
						map.clear();
						map.put("dictionary_id", userGrade.getDictionary_id());
						Dictionary dictionary=dictionaryService.getDictionary(map);
						userGrade.setDictionary(dictionary);
					}
					
					if(userGrade.getReview_id()!=null){
						map.clear();
						map.put("review_id", userGrade.getReview_id());
						Review review=reviewService.getReview(map);
						userGrade.setReview(review);
					}
					map.clear();
					map.put("parent_id", userGrade.getGrade_id());
					List<UserGrade> sub_usergrade=userGradeService.getUserGradeList(map);
					userGrade.setGrades(sub_usergrade);
				}
			}
			model.addAttribute("grades", grades);
			return "/school/person/grade/grade_list";
		} else {
			String url = request.getServletPath();
			session.removeAttribute("url");
			session.setAttribute("url", url);
			return "/school/login/login";
		}
	}
}
