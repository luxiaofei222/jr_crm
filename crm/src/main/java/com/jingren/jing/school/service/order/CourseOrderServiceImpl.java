package com.jingren.jing.school.service.order;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.school.bean.order.CourseOrder;
import com.jingren.jing.school.dao.order.CourseOrderMapper;
import com.jingren.jing.util.DesUtil;
@Service
public class CourseOrderServiceImpl implements CourseOrderService{
	@Resource
	private CourseOrderMapper courseOrderMapper;

	@Override
	public boolean saveCourseOrder(CourseOrder courseOrder) {
		String order_id=DesUtil.getUUID();
		courseOrder.setOrder_id(order_id);//添加订单ID
		courseOrder.setOrder_time(new Date());//订单时间
		courseOrder.setOrder_number(DesUtil.get_order_number());//订单编号
		return courseOrderMapper.saveCourseOrder(courseOrder);
	}

	@Override
	public boolean updateCourseOrder(CourseOrder courseOrder) {
		return courseOrderMapper.updateCourseOrder(courseOrder);
	}

	@Override
	public boolean deleteCourseOrder(String order_id) {
		return courseOrderMapper.deleteCourseOrder(order_id);
	}

	@Override
	public CourseOrder getCourseOrder(Map<String, Object> map) {
		return courseOrderMapper.getCourseOrder(map);
	}

	@Override
	public List<CourseOrder> getCourseOrderList(Map<String, Object> map) {
		return courseOrderMapper.getCourseOrderList(map);
	}

	@Override
	public Integer getCouseOrderNumber(Map<String, Object> map) {
		return courseOrderMapper.getCouseOrderNumber(map);
	}

}
