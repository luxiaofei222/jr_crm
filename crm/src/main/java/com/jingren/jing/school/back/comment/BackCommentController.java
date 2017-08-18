package com.jingren.jing.school.back.comment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jingren.jing.school.bean.comment.VideoComment;
import com.jingren.jing.school.bean.course.CourseVideo;
import com.jingren.jing.school.bean.user.User;
import com.jingren.jing.school.service.comment.VideoCommentService;
import com.jingren.jing.school.service.course.CourseVideoService;
import com.jingren.jing.school.service.user.UserService;
import com.jingren.jing.util.Pagers;
import com.jingren.jing.util.ResponseUtils;

/**
 * @Title: BackCommentController.java
 * @Package com.jingren.jing.school.back.comment
 * @Description: TODO 评论管理
 * @author 鲁晓飞 MR.Lu
 * @date 2016年12月8日 下午5:43:33
 * @version 网校+CRM系统 V1.0
 */
@Controller
@RequestMapping("back_comment")
public class BackCommentController {

	@Resource
	private VideoCommentService videoCommentService;
	@Resource
	private UserService userService;
	@Resource
	private CourseVideoService courseVideoService;

	/**
	 * @Title: BackCommentController.java
	 * @Package com.jingren.jing.school.back.comment
	 * @Description: TODO 课程评论
	 * @author 鲁晓飞 MR.Lu
	 * @date 2016年12月14日 上午11:15:28
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_video_comment_list.jr")
	public String get_video_comment(Model model, @RequestParam(value = "video_id", required = false) Integer video_id,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "pageNumber", required = false) Integer pageNumber) {
		Map<String, Object> map = new HashMap<>();
		map.put("video_id", video_id);
		map.put("pageNumber", (pageNumber - 1) * limit);
		map.put("limit", limit);
		List<VideoComment> comments = videoCommentService.getVideoCommentList(map);
		for (VideoComment videoComment : comments) {
			map.clear();
			map.put("user_id", videoComment.getUser_id());
			User user = userService.getUser(map);
			videoComment.setUser(user);
		}
		Integer comments_number = videoCommentService.getCommentNumber(map);
		Pagers<VideoComment> pagers = new Pagers<VideoComment>(comments_number, pageNumber, limit);
		pagers.setList(comments);
		model.addAttribute("comments", pagers);
		model.addAttribute("video_id", video_id);
		return "/course_video/comment";
	}

	/**
	 * @Title: BackCommentController.java
	 * @Package com.jingren.jing.school.back.comment
	 * @Description: TODO 评论列表
	 * @author 鲁晓飞 MR.Lu
	 * @date 2016年12月15日 上午11:04:50
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_comment_list.jr")
	public String get_comment_list(Model model, @RequestParam(value = "video_name", required = false) String video_name,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "pageNumber", required = false) Integer pageNumber) {
		Map<String, Object> map = new HashMap<>();
		map.put("pageNumber", (pageNumber - 1) * limit);
		map.put("limit", limit);
		List<VideoComment> comments = videoCommentService.getVideoCommentList(map);
		for (VideoComment videoComment : comments) {
			map.clear();
			map.put("user_id", videoComment.getUser_id());
			User user = userService.getUser(map);
			videoComment.setUser(user);
			map.clear();
			map.put("video_id", videoComment.getVideo_id());
			CourseVideo courseVideo = courseVideoService.getCourseVideo(map);
			videoComment.setCourseVideo(courseVideo);
		}
		Integer comments_number = videoCommentService.getCommentNumber(map);
		Pagers<VideoComment> pagers = new Pagers<VideoComment>(comments_number, pageNumber, limit);
		pagers.setList(comments);
		model.addAttribute("comments", pagers);
		return "/comment/video_comment";
	}

	/**
	 * @Title: BackCommentController.java
	 * @Package com.jingren.jing.school.back.comment
	 * @Description: TODO 删除评论内容
	 * @author 鲁晓飞 MR.Lu
	 * @date 2016年12月15日 上午11:06:25
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/delete_comment.jr")
	public void delete_comment(HttpServletResponse response,
			@RequestParam(value = "str", required = false) String str) {
		String[] array = str.split(",");
		for (String string : array) {
			int id = Integer.valueOf(string);
			videoCommentService.deleteComment(id);
		}
		ResponseUtils.renderText(response, "1");
	}

	/**
	 * @Title: BackCommentController.java
	 * @Package com.jingren.jing.school.back.comment
	 * @Description: TODO 获取评论详情
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年4月5日 下午5:58:07
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_check_comment.jr")
	public String to_check_comment(Model model,
			@RequestParam(value = "v_comment_id", required = false) Integer v_comment_id) {
		Map<String, Object> map = new HashMap<>();
		map.put("v_comment_id", v_comment_id);
		VideoComment comment = videoCommentService.getVideoComment(map);
		map.clear();
		map.put("user_id", comment.getUser_id());
		User user = userService.getUser(map);
		comment.setUser(user);
		map.clear();
		map.put("video_id", comment.getVideo_id());
		CourseVideo courseVideo = courseVideoService.getCourseVideo(map);
		comment.setCourseVideo(courseVideo);
		model.addAttribute("comment", comment);
		return "/comment/check_comment";
	}
}
