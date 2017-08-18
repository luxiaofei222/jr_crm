package com.jingren.jing.question.comtroller.front.correction;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
/**
* @Title: FrontCorrectionController.java 
* @Package com.jingren.jing.question.comtroller.front.correction 
* @Description: TODO 纠错信息
* @author 鲁晓飞 MR.Lu   
* @date 2017年2月8日 上午8:35:43 
* @version 网校+CRM系统 V1.0
 */
import org.springframework.web.bind.annotation.RequestParam;

import com.jingren.jing.question.bean.chapterrecord.ChapterRecord;
import com.jingren.jing.question.bean.correction.CorrectionQuestion;
import com.jingren.jing.question.service.chapterrecord.ChapterRecordService;
import com.jingren.jing.question.service.correction.CorrectionQuestionService;
import com.jingren.jing.school.bean.user.User;
import com.jingren.jing.util.ResponseUtils;
@Controller
@RequestMapping("front_correction")
public class FrontCorrectionController {

	@Resource
	private CorrectionQuestionService correctionQuestionService;
	@Resource
	private ChapterRecordService chapterRecordService;
	/**
	* @Title: FrontCorrectionController.java 
	* @Package com.jingren.jing.question.comtroller.front.correction 
	* @Description: TODO 纠错信息弹出窗口
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月8日 上午8:38:26 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_correction.html")
	public String get_correction(Model model,
			@RequestParam(value="chapter_recourd_id",required=false)Integer chapter_recourd_id){
		if(chapter_recourd_id!=null){
			model.addAttribute("chapter_recourd_id", chapter_recourd_id);
			model.addAttribute("type", "章节练习");
		}
		
		return "/question/jiucuo_tijiao/jiucuo";
	}
	/**
	* @Title: FrontCorrectionController.java 
	* @Package com.jingren.jing.question.comtroller.front.correction 
	* @Description: TODO 保存纠错信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月8日 上午9:10:38 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/save_correction.html")
	public void save_correction(CorrectionQuestion correctionQuestion,HttpSession session,HttpServletResponse response,
			@RequestParam(value="str",required=false)String str,
			@RequestParam(value="chapter_recourd_id",required=false)Integer chapter_recourd_id){
		Map<String, Object> map=new HashMap<>();
		User user=(User) session.getAttribute("user_session");
		if(user!=null){
			correctionQuestion.setUser_id(user.getUser_id());
			correctionQuestion.setCorrection_type(str);
			correctionQuestion.setCorrection_time(new Date());
			map.put("chapter_recourd_id", chapter_recourd_id);
			ChapterRecord chapterRecord=chapterRecordService.getChapterRecord(map);
			correctionQuestion.setQuestion_id(chapterRecord.getChapter_question_id());//题目ID
			if(correctionQuestionService.saveCorrectionQuestion(correctionQuestion)){
				ResponseUtils.renderText(response, "1");
			}else{
				ResponseUtils.renderText(response, "0");
			}
		}
	}
}
