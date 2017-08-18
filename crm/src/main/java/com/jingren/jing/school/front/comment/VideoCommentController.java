package com.jingren.jing.school.front.comment;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jingren.jing.school.bean.comment.VideoComment;
import com.jingren.jing.school.bean.user.User;
import com.jingren.jing.school.service.comment.VideoCommentService;
import com.jingren.jing.school.service.user.UserService;
import com.jingren.jing.util.GetIPUtil;
import com.jingren.jing.util.Pagers;
import com.jingren.jing.util.ResponseUtils;
import com.jingren.jing.util.WordGuolvUtil;

/**
 * 视频评论
* @Title: VideoCommentController.java 
* @Package com.jingren.jing.school.front.comment 
* @Description: TODO
* @author 鲁晓飞 MR.Lu   
* @date 2016年11月23日 下午1:53:40 
* @version 网校+CRM系统 V1.0
 */
@Controller
@RequestMapping("sc_comment")
public class VideoCommentController {

	@Resource
	private VideoCommentService videoCommentService;
	@Resource
	private UserService userService;
	
	/**
	 * 评论分页
	* @Title: VideoCommentController.java 
	* @Package com.jingren.jing.school.front.comment 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月23日 下午2:06:47 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_video_comment.html")
	public String get_video_comment(Model model,HttpSession session,
			@RequestParam(value="video_id",required=false)Integer video_id,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "pageNumber", required = false) Integer pageNumber){
		Map<String, Object> map=new HashMap<>();
		map.put("video_id", video_id);
		map.put("pageNumber", (pageNumber - 1) * limit);
		map.put("limit", limit);
		Integer comments_number=videoCommentService.getCommentNumber(map);
		List<VideoComment> comments=videoCommentService.getVideoCommentList(map);
		for (VideoComment videoComment : comments) {
			map.clear();
			map.put("user_id", videoComment.getUser_id());
			User user=userService.getUser(map);
			videoComment.setUser(user);
		}
		Pagers<VideoComment> pagers = new Pagers<VideoComment>(comments_number,
				pageNumber, limit);
		pagers.setList(comments);
		model.addAttribute("comments", pagers);
		model.addAttribute("comments_number", comments_number);
		model.addAttribute("video_id", video_id);
		return "/school/comment/comment";
	}
	/**
	 * 保存评论信息
	* @Title: VideoCommentController.java 
	* @Package com.jingren.jing.school.front.comment 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月23日 下午3:16:33 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/save_comment.html")
	public void save_comment(HttpSession session,HttpServletRequest request,
			VideoComment videoComment,HttpServletResponse response){
		User user=(User) session.getAttribute("user_session");
		if(user!=null){
			videoComment.setUser_id(user.getUser_id());
			videoComment.setV_comment_ip(GetIPUtil.getIpAddress(request));
			videoComment.setV_comment_time(new Date());
			if(videoCommentService.saveComment(videoComment)){
				ResponseUtils.renderText(response, "1");
			}else{
				ResponseUtils.renderText(response, "0");
			}
		}else{
			ResponseUtils.renderText(response, "3");//登录失效
		}
	}
}
