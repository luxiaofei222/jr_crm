package com.jingren.jing.oa.service.oa_notice;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.oa.bean.oa_notice.Notice;
import com.jingren.jing.oa.dao.oa_notice.NoticeMapper;
@Service
public class NoticeServiceImpl implements NoticeService{
	@Resource
	private NoticeMapper noticeMapper;

	@Override
	public boolean saveNotice(Notice notice) {
		return noticeMapper.saveNotice(notice);
	}

	@Override
	public boolean updateNotice(Notice notice) {
		return noticeMapper.updateNotice(notice);
	}

	@Override
	public boolean deleteNotice(Integer notice_id) {
		return noticeMapper.deleteNotice(notice_id);
	}

	@Override
	public List<Notice> getNoticeList(Map<String, Object> map) {
		return noticeMapper.getNoticeList(map);
	}

	@Override
	public Integer getNoticeNumber(Map<String, Object> map) {
		return noticeMapper.getNoticeNumber(map);
	}

	@Override
	public List<Notice> getNotice() {
		return noticeMapper.getNotice();
	}

}
