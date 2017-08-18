package com.jingren.jing.task;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jingren.jing.common.downerror.service.DownErrorService;
import com.jingren.jing.common.videokey.service.VideoKeyService;
import com.jingren.jing.crm.bean.companyrecord.BusinessCallRecord;
import com.jingren.jing.crm.service.companyrecord.BusinessCallRecordService;
import com.jingren.jing.question.bean.chapterrecord.ChapterRecord;
import com.jingren.jing.question.bean.zhentirecord.ZhentiRecord;
import com.jingren.jing.question.service.chapterrecord.ChapterRecordService;
import com.jingren.jing.question.service.zhentirecord.ZhentiRecordService;

/**
 * @Title: VideokeyTask.java
 * @Package com.jingren.jing.task
 * @Description: TODO 自动清除视频调用
 * @author 鲁晓飞 MR.Lu
 * @date 2017年3月29日 上午7:57:25
 * @version 网校+CRM系统 V1.0
 */
@Component("videokeyTask")
//@Controller
//@RequestMapping("test")
public class VideokeyTask {

	@Resource
	private VideoKeyService videoKeyService;
	@Resource
	private BusinessCallRecordService businessCallRecordService;
	@Resource
	private DownErrorService downErrorService;
	@Resource
	private  ChapterRecordService chapterRecordService;
	@Resource
	private ZhentiRecordService zhentiRecordService;

	/**
	 * @Title: VideokeyTask.java
	 * @Package com.jingren.jing.task
	 * @Description: TODO 定时清空视频私钥
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年3月29日 上午8:46:45
	 * @version 网校+CRM系统 V1.0
	 */
	@Scheduled(cron = "0 0 4 * * ?")
	//@RequestMapping("/hehe.html")
	public void delete_video_key() {
		videoKeyService.deleteAllVideoKey();
		Map<String, Object> map = new HashMap<>();
		map.put("end_time", get_two_date());
		List<ZhentiRecord> records=zhentiRecordService.getZhentiRecordList(map);
		for (ZhentiRecord zhentiRecord : records) {
			zhentiRecordService.deleteZhentiRecord(zhentiRecord);
		}
		List<ChapterRecord> chapterRecords=chapterRecordService.getChapterRecordList(map);
		ChapterRecord chapterRecord2=new ChapterRecord();
		for (ChapterRecord chapterRecord : chapterRecords) {
			chapterRecord2.setChapter_recourd_id(chapterRecord.getChapter_recourd_id());
			chapterRecordService.deleteChapterRecord(chapterRecord2);
		}
	}

	/**
	 * @Title: VideokeyTask.java
	 * @Package com.jingren.jing.task
	 * @Description: TODO 定时删除180天以前的通话记录
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年3月29日 上午8:46:11
	 * @version 网校+CRM系统 V1.0
	 */
	@Scheduled(cron = "0 0 1 * * ?")
	// @RequestMapping("/delte_chaoshi_record.html")
	public void delte_chaoshi_record() {
		Map<String, Object> map = new HashMap<>();
		map.put("end_time", get_six_date());
		List<BusinessCallRecord> businessCallRecords = businessCallRecordService.getBusinessCallRecordList(map);
		for (BusinessCallRecord businessCallRecord : businessCallRecords) {
			int time = differentDays(businessCallRecord.getCall_time(), new Date());
			if (time > 180) {
				businessCallRecordService.deleteBusinessCallRecord(businessCallRecord.getRecord_id());
			}
		}
	}
	/**
	* @Title: VideokeyTask.java 
	* @Package com.jingren.jing.task 
	* @Description: TODO 定时删除错误下载记录
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年4月13日 下午5:17:35 
	* @version 网校+CRM系统 V1.0
	 */
	@Scheduled(cron = "0 1 */7 * * ?")
	public void delte_down_error() {
		downErrorService.deleteDownError();
	}
	
	/**
	* @Title: VideokeyTask.java 
	* @Package com.jingren.jing.task 
	* @Description: TODO 获取六个月前的时间
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年4月5日 上午8:02:16 
	* @version 网校+CRM系统 V1.0
	 */
	public Date get_six_date() {
		Date dNow = new Date(); // 当前时间
		Date dBefore = new Date();
		Calendar calendar = Calendar.getInstance(); // 得到日历
		calendar.setTime(dNow);// 把当前时间赋给日历
		calendar.add(calendar.MONTH, -6); // 设置为前6月
		dBefore = calendar.getTime();
		return dBefore;
	}
//获取两个月之前的日期
	public Date get_two_date() {
		Date dNow = new Date(); // 当前时间
		Date dBefore = new Date();
		Calendar calendar = Calendar.getInstance(); // 得到日历
		calendar.setTime(dNow);// 把当前时间赋给日历
		calendar.add(calendar.MONTH, -2); // 设置为前6月
		dBefore = calendar.getTime();
		return dBefore;
	}
	/**
	 * @Title: VideokeyTask.java
	 * @Package com.jingren.jing.task
	 * @Description: TODO 获取两个日期间隔
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年3月29日 上午8:46:00
	 * @version 网校+CRM系统 V1.0
	 */
	public static int differentDays(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		int day1 = cal1.get(Calendar.DAY_OF_YEAR);
		int day2 = cal2.get(Calendar.DAY_OF_YEAR);

		int year1 = cal1.get(Calendar.YEAR);
		int year2 = cal2.get(Calendar.YEAR);
		if (year1 != year2) // 同一年
		{
			int timeDistance = 0;
			for (int i = year1; i < year2; i++) {
				if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) // 闰年
				{
					timeDistance += 366;
				} else // 不是闰年
				{
					timeDistance += 365;
				}
			}
			return timeDistance + (day2 - day1);
		} else // 不同年
		{
			// System.out.println("判断day2 - day1 : " + (day2 - day1));
			return day2 - day1;
		}
	}
}
