package com.jingren.jing.question.comtroller.back.correction;

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

import com.jingren.jing.question.bean.chapter_exercises.ChapterQuerstionOption;
import com.jingren.jing.question.bean.chapter_exercises.ChapterQuestion;
import com.jingren.jing.question.bean.correction.CorrectionQuestion;
import com.jingren.jing.question.service.chapter_exercises.ChapterQuerstionOptionService;
import com.jingren.jing.question.service.chapter_exercises.ChapterQuestionService;
import com.jingren.jing.question.service.correction.CorrectionQuestionService;
import com.jingren.jing.school.bean.user.User;
import com.jingren.jing.school.service.user.UserService;
import com.jingren.jing.util.Pagers;
import com.jingren.jing.util.ResponseUtils;
import com.jingren.jing.util.UploadAddress;

/**
* @Title: BackCorrectionQuestionController.java 
* @Package com.jingren.jing.question.comtroller.back.correction 
* @Description: TODO 纠错信息
* @author 鲁晓飞 MR.Lu   
* @date 2017年2月8日 上午10:34:52 
* @version 网校+CRM系统 V1.0
 */
@Controller
@RequestMapping("back_correction")
public class BackCorrectionQuestionController {

	@Resource
	private CorrectionQuestionService correctionQuestionService;
	@Resource
	private UserService userService;
	@Resource
	private ChapterQuestionService chapterQuestionService;
	@Resource
	private ChapterQuerstionOptionService chapterQuerstionOptionService;
	private static final String UP_FRONT_FILE = "images/chapter_option";
	/**
	* @Title: BackCorrectionQuestionController.java 
	* @Package com.jingren.jing.question.comtroller.back.correction 
	* @Description: TODO 纠错信息列表首页
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月8日 上午10:39:22 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_correction_main.jr")
	public String get_correction_main(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "pageNumber", required = false) Integer pageNumber){
		Map<String, Object> map=new HashMap<>();
		map.put("pageNumber", (pageNumber - 1) * limit);
		map.put("limit", limit);
		Integer correction_number=correctionQuestionService.getgetCorrectionQuestionNumber(map);
		List<CorrectionQuestion> correctionQuestions=correctionQuestionService.getCorrectionQuestionList(map);
		for (CorrectionQuestion correctionQuestion : correctionQuestions) {
			map.clear();
			map.put("user_id", correctionQuestion.getUser_id());
			User user=userService.getUser(map);
			correctionQuestion.setUser(user);
		}
		Pagers<CorrectionQuestion> pagers = new Pagers<CorrectionQuestion>(correction_number,
				pageNumber, limit);
		pagers.setList(correctionQuestions);
		model.addAttribute("correctionQuestions", pagers);
		return "/question/correction/correction_question";
	}
	/**
	* @Title: BackCorrectionQuestionController.java 
	* @Package com.jingren.jing.question.comtroller.back.correction 
	* @Description: TODO 删除纠错信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月8日 上午11:23:04 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/delete_correction.jr")
	public void delete_correction(HttpServletResponse response,
			@RequestParam(value="correction_id",required=false)Integer correction_id){
		if(correctionQuestionService.deleteCorrectionQuestion(correction_id)){
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
	/**
	* @Title: BackCorrectionQuestionController.java 
	* @Package com.jingren.jing.question.comtroller.back.correction 
	* @Description: TODO 查看纠错信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月8日 下午3:36:16 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/check_correction.jr")
	public String check_correction(Model model,
			@RequestParam(value="correction_id",required=false)Integer correction_id){
		Map<String, Object> map=new HashMap<>();
		map.put("correction_id", correction_id);
		CorrectionQuestion correctionQuestion=correctionQuestionService.getCorrectionQuestion(map);
		if(correctionQuestion.getQuestion_type().equals("章节练习")){
			map.clear();
			map.put("chapter_question_id", correctionQuestion.getQuestion_id());
			ChapterQuestion chapterQuestion=chapterQuestionService.getChapterQuestion(map);
			map.clear();
			map.put("chapter_question_id", chapterQuestion.getChapter_question_id());
			List<ChapterQuerstionOption> chapterQuerstionOptions = chapterQuerstionOptionService
					.getChapterQuerstionOptionList(map);
			chapterQuestion.setChapterQuerstionOptions(chapterQuerstionOptions);
			correctionQuestion.setChapterQuestion(chapterQuestion);
		}
		model.addAttribute("correctionQuestion", correctionQuestion);
		CorrectionQuestion correctionQuestion2=new CorrectionQuestion();
		correctionQuestion2.setCorrection_id(correctionQuestion.getCorrection_id());
		correctionQuestion2.setCorrection_state(1);
		correctionQuestionService.updateCorrectionQuestion(correctionQuestion2);//修改查看的状态
		return "/question/correction/update_correction_question";
	}
	/**
	* @Title: BackCorrectionQuestionController.java 
	* @Package com.jingren.jing.question.comtroller.back.correction 
	* @Description: TODO 修改题目
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月8日 下午4:18:12 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/update_chapter_question.jr")
	public void update_chapter_question(HttpServletResponse response,
			@RequestParam(value = "file_upload", required = false) MultipartFile file_upload,HttpServletRequest request,
			ChapterQuestion chapterQuestion){
		if(file_upload!=null){
			String path = UploadAddress.getUploadDate(file_upload, request, UP_FRONT_FILE);
			chapterQuestion.setQuestion_name_pic(path);
		}
		if(chapterQuestionService.updateChapterQuestion(chapterQuestion)){
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
	/**
	* @Title: BackCorrectionQuestionController.java 
	* @Package com.jingren.jing.question.comtroller.back.correction 
	* @Description: TODO 修改选项
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月8日 下午4:28:11 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/update_chapter_question_option.jr")
	public void update_chapter_question_option(HttpServletResponse response,
			ChapterQuerstionOption chapterQuerstionOption){
		if(chapterQuerstionOptionService.updateChapterQuerstionOption(chapterQuerstionOption)){
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
}
