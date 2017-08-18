package com.jingren.jing.oa.controller.notice;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jingren.jing.goeasy.GoEasyPush;
import com.jingren.jing.oa.bean.oa_file.OaFile;
import com.jingren.jing.oa.bean.oa_notice.Notice;
import com.jingren.jing.oa.service.oa_file.OaFileService;
import com.jingren.jing.oa.service.oa_notice.NoticeService;
import com.jingren.jing.util.CommentDate;
import com.jingren.jing.util.DeleteFile;
import com.jingren.jing.util.Pagers;
import com.jingren.jing.util.ResponseUtils;
import com.jingren.jing.util.UploadAddress;

/**
* @Title: NoticeController.java 
* @Package com.jingren.jing.oa.controller.notice 
* @Description: TODO 公司公告
* @author 鲁晓飞 MR.Lu   
* @date 2017年3月30日 下午4:11:26 
* @version 网校+CRM系统 V1.0
 */
@EnableAsync
@Controller
@RequestMapping("notice")
public class NoticeController {

	@Resource
	private OaFileService oaFileService;
	@Resource
	private NoticeService noticeService;
	private static final String annexes ="annexes";//课件目录upload_video
	/**
	* @Title: NoticeController.java 
	* @Package com.jingren.jing.oa.controller.notice 
	* @Description: TODO 公司公告
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年3月31日 上午9:34:48 
	* @version 网校+CRM系统 V1.0
	 * @throws ParseException 
	 */
	@RequestMapping("/get_notice_list.jr")
	public String get_notice_list(Model model,
			@RequestParam(value = "start_time", required = false)  String start_time,
			@RequestParam(value = "end_time", required = false) String end_time,
			@RequestParam(value = "pageNumber", required = false,defaultValue="1")Integer pageNumber) throws ParseException{
		Map<String, Object> map = new HashMap<>();
		if(StringUtils.isNotBlank(start_time)&&StringUtils.isNotBlank(end_time)){//开始时间和结束时间
			map.put("start_time", CommentDate.get_String_date(start_time));
			long currentTime =  CommentDate.get_String_date(end_time).getTime();
			currentTime +=24*60*60*1000-1000;//加23小时59分59秒
			Date date=new Date(currentTime);
			map.put("end_time", date);
			model.addAttribute("start_time", start_time);
			model.addAttribute("end_time", end_time);
		}
		map.put("pageNumber", (pageNumber - 1) * 20);
		map.put("limit", 20);
		Integer notice_number=noticeService.getNoticeNumber(map);
		List<Notice> notices=noticeService.getNoticeList(map);
		Pagers<Notice> pagers=new Pagers<Notice>(notice_number, pageNumber, 20);
		pagers.setList(notices);
		model.addAttribute("notices", pagers);
		return "/oa/notice/notice_list";
	}
	
	/**
	* @Title: NoticeController.java 
	* @Package com.jingren.jing.oa.controller.notice 
	* @Description: TODO 公司文件列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年3月31日 上午9:34:38 
	* @version 网校+CRM系统 V1.0
	 * @throws ParseException 
	 */
	@RequestMapping("/get_file_list.jr")
	public String get_file_list(Model model,
			@RequestParam(value = "start_time", required = false)  String start_time,
			@RequestParam(value = "end_time", required = false) String end_time,
			@RequestParam(value = "pageNumber", required = false,defaultValue="1")Integer pageNumber) throws ParseException{
		Map<String, Object> map = new HashMap<>();
		if(StringUtils.isNotBlank(start_time)&&StringUtils.isNotBlank(end_time)){//开始时间和结束时间
			map.put("start_time", CommentDate.get_String_date(start_time));
			long currentTime =  CommentDate.get_String_date(end_time).getTime();
			currentTime +=24*60*60*1000-1000;//加23小时59分59秒
			Date date=new Date(currentTime);
			map.put("end_time", date);
			model.addAttribute("start_time", start_time);
			model.addAttribute("end_time", end_time);
		}
		map.put("pageNumber", (pageNumber - 1) * 20);
		map.put("limit", 20);
		Integer file_number=oaFileService.getOaFileNumber(map);
		List<OaFile> oaFiles=oaFileService.getOaFileList(map);
		for (OaFile oaFile : oaFiles) {
			String prefix=oaFile.getFile_addr().substring(oaFile.getFile_addr().lastIndexOf(".")+1);
			oaFile.setHouzhui(prefix);
		}
		Pagers<OaFile> pagers=new Pagers<OaFile>(file_number, pageNumber, 20);
		pagers.setList(oaFiles);
		model.addAttribute("oaFiles", pagers);
		return "/oa/notice/file_list";
	}
	/**
	* @Title: NoticeController.java 
	* @Package com.jingren.jing.oa.controller.notice 
	* @Description: TODO 上传文件弹窗
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年3月31日 下午1:04:44 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_add_file.jr")
	public String to_add_file(){
		
		return "/oa/notice/add_file";
	}
	/**
	* @Title: NoticeController.java 
	* @Package com.jingren.jing.oa.controller.notice 
	* @Description: TODO 添加公告弹窗
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年3月31日 下午5:07:42 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_fabu_notice.jr")
	public String to_fabu_notice(){
		
		return "/oa/notice/add_notice";
	}
	/**
	* @Title: NoticeController.java 
	* @Package com.jingren.jing.oa.controller.notice 
	* @Description: TODO 上传课件
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年3月31日 下午1:09:34 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/save_oa_file.jr")
	public void save_video_ware(HttpSession session,HttpServletRequest request,HttpServletResponse response,
			OaFile oaFile,
			@RequestParam(value = "file_up", required = false) MultipartFile file_up){
		if(file_up!=null){
			String path=UploadAddress.getUploadDate(file_up, request, annexes);
			oaFile.setFile_addr(path);//文件路径
			oaFile.setFile_time(new Date());
			if(oaFileService.saveOaFile(oaFile)){
				GoEasyPush.push_message("gonggao", "公司发布了新的文件，您可以下载查看！");
				ResponseUtils.renderText(response, "1");
			}else{
				ResponseUtils.renderText(response, "0");
			}
		}
	}
	/**
	* @Title: NoticeController.java 
	* @Package com.jingren.jing.oa.controller.notice 
	* @Description: TODO 发布新公告
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年3月31日 下午5:09:01 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/save_notice.jr")
	public void save_notice(HttpSession session,HttpServletRequest request,HttpServletResponse response,
			Notice notice){
		notice.setNotice_time(new Date());
		if(noticeService.saveNotice(notice)){
			GoEasyPush.push_message("gonggao", "公司发布了新的公告，请注意查看！");
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
	/**
	* @Title: NoticeController.java 
	* @Package com.jingren.jing.oa.controller.notice 
	* @Description: TODO 删除公司文件
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年3月31日 下午2:49:03 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/delete_file.jr")
	public void delete_file(HttpServletResponse response,HttpServletRequest request,
			@RequestParam(value = "file_id", required = false)Integer file_id){
		Map<String, Object> map=new HashMap<>();
		map.put("file_id", file_id);
		List<OaFile> files=oaFileService.getOaFileList(map);
		if(oaFileService.deleteOaFile(file_id)){
			DeleteFile.deleteFile1(files.get(0).getFile_addr(), request);
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
	/**
	* @Title: NoticeController.java 
	* @Package com.jingren.jing.oa.controller.notice 
	* @Description: TODO 删除公告
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年3月31日 下午5:49:48 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/delete_notice.jr")
	public void delete_notice(HttpServletResponse response,
			@RequestParam(value = "notice_id", required = false)Integer notice_id){
		if(notice_id!=null){
			if(noticeService.deleteNotice(notice_id)){
				ResponseUtils.renderText(response, "1");
			}else{
				ResponseUtils.renderText(response, "0");
			}
		}
	}
	/**
	* @Title: NoticeController.java 
	* @Package com.jingren.jing.oa.controller.notice 
	* @Description: TODO 修改文件信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年3月31日 下午6:20:59 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/update_file.jr")
	public void update_file(HttpServletResponse response,OaFile oaFile){
		Map<String, Object> map=new HashMap<>();
		map.put("file_id", oaFile.getFile_id());
		List<OaFile> files=oaFileService.getOaFileList(map);
		oaFile.setFile_down_time(files.get(0).getFile_down_time()+1);
		if(oaFileService.updateOaFile(oaFile)){
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
}
