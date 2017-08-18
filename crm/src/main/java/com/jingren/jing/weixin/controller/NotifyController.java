package com.jingren.jing.weixin.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xml.sax.InputSource;

import com.jingren.jing.school.bean.clearance.Clearance;
import com.jingren.jing.school.bean.clearance.ClearanceVideo;
import com.jingren.jing.school.bean.mycourse.MyCourse;
import com.jingren.jing.school.bean.order.CourseOrder;
import com.jingren.jing.school.service.clearance.ClearanceService;
import com.jingren.jing.school.service.clearance.ClearanceVideoService;
import com.jingren.jing.school.service.mycourse.MyCourseService;
import com.jingren.jing.school.service.order.CourseOrderService;
import com.jingren.jing.util.DeleteFile;
import com.jingren.jing.weixin.bean.notify.NotifyResult;
import com.jingren.jing.weixin.util.WXSignUtils;

/**
* @Title: NotifyController.java 
* @Package com.jingren.jing.weixin.controller 
* @Description: TODO 微信异步通知
* @author 鲁晓飞 MR.Lu   
* @date 2017年4月18日 上午9:39:25 
* @version 网校+CRM系统 V1.0
 */
@Controller
@RequestMapping("wx_notify")
public class NotifyController {

	@Resource
	private CourseOrderService courseOrderService;
	@Resource
	private MyCourseService myCourseService;
	@Resource
	private ClearanceService clearanceService;
	@Resource
	private ClearanceVideoService clearanceVideoService;
	
	/**
	* @Title: NotifyController.java 
	* @Package com.jingren.jing.weixin.controller 
	* @Description: TODO 微信支付统一下单异步通知
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年4月18日 上午11:29:32 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/wx_notify_PC.html")
	public void wx_notify_PC(HttpServletResponse response,HttpServletRequest request,HttpSession session) throws IOException{
		BufferedReader bufferedReader=request.getReader();
			String line="";
			StringBuffer inputString=new StringBuffer();
			PrintWriter writer=response.getWriter();
			while ((line=bufferedReader.readLine())!=null) {
				inputString.append(line);
			}
			
			if (bufferedReader!=null) {
				bufferedReader.close();
			}
			NotifyResult notifyResult=get_xml_object(inputString.toString());
			if (notifyResult != null) {
				if ("SUCCESS".equals(notifyResult.getReturn_code())) {
					if ("SUCCESS".equals(notifyResult.getResult_code())) {
						Map<String, Object> map=new HashMap<>();
						map.put("order_number", notifyResult.getOut_trade_no());
						CourseOrder courseOrder=courseOrderService.getCourseOrder(map);
						if(courseOrder!=null){
							if(courseOrder.getOrder_state().equals("待付款")){
								DeleteFile.deleteFile1(courseOrder.getOther_order_no(), request);
								courseOrder.setPay_time(new Date());
								courseOrder.setOrder_state("已付款");
								courseOrder.setOther_order_no(notifyResult.getTransaction_id());
								DecimalFormat   df   =new   DecimalFormat("0.00");
								courseOrder.setPay_money(String.valueOf(df.format(Double.valueOf((double)notifyResult.getTotal_fee()/100))));
								if(courseOrderService.updateCourseOrder(courseOrder)){//付款成功
									if(courseOrder.getClearance_id()!=null){//套餐
										map.clear();
										map.put("clearance_id", courseOrder.getClearance_id());
										Clearance clearance=clearanceService.getClearance(map);
										List<ClearanceVideo> clearanceVideos=clearanceVideoService.getClearanceVideoList(map);
										for (ClearanceVideo clearanceVideo : clearanceVideos) {//添加课程
											MyCourse myCourse=new MyCourse();
											myCourse.setUser_id(courseOrder.getUser_id());
											myCourse.setVideo_id(clearanceVideo.getVideo_id());
											myCourse.setMy_course_time(new Date());
											myCourse.setDaoqi_time(clearance.getDaoqi_time());
											myCourseService.saveMyCourse(myCourse);
										}
									}else{
										MyCourse myCourse=new MyCourse();
										myCourse.setUser_id(courseOrder.getUser_id());
										myCourse.setVideo_id(courseOrder.getVideo_id());
										myCourse.setMy_course_time(new Date());
										myCourseService.saveMyCourse(myCourse);
									}
									writer.write(WXSignUtils.backWeixin("SUCCESS","OK"));
								}
							}
						}else{
							writer.write(WXSignUtils.backWeixin("SUCCESS","OK"));
						}
					}
				}
			}
	}
	
	public static void main(String[] args) {
		DecimalFormat   df   =new   DecimalFormat("0.00");  
		System.out.println(String.valueOf(df.format(Double.valueOf((double)253/100))));
	}
	//解析异步通知结果
	public NotifyResult get_xml_object(String xmlString) {
		NotifyResult pcResult = new NotifyResult();
		try {
			StringReader read = new StringReader(xmlString);
			// 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
			InputSource source = new InputSource(read);
			// 创建一个新的SAXBuilder
			SAXBuilder sb = new SAXBuilder();
			// 通过输入源构造一个Document
			Document doc;
			doc = (Document) sb.build(source);

			Element root = doc.getRootElement();// 指向根节点
			List<Element> list = root.getChildren();
			if (list != null && list.size() > 0) {
				for (Element element : list) {
					//System.out.println("key是：" + element.getName() + "，值是：" + element.getText());
					if ("return_code".equals(element.getName())) {
						pcResult.setReturn_code(element.getText());
					}
					if ("return_msg".equals(element.getName())) {
						pcResult.setReturn_msg(element.getText());
					}

					if ("appid".equals(element.getName())) {
						pcResult.setAppid(element.getText());
					}

					if ("mch_id".equals(element.getName())) {
						pcResult.setMch_id(element.getText());
					}

					if ("nonce_str".equals(element.getName())) {
						pcResult.setNonce_str(element.getText());
					}

					if ("sign".equals(element.getName())) {
						pcResult.setSign(element.getText());
					}

					if ("result_code".equals(element.getName())) {
						pcResult.setResult_code(element.getText());
					}

					if ("err_code_des".equals(element.getName())) {
						pcResult.setErr_code_des(element.getText());
					}
					
					if ("trade_type".equals(element.getName())) {
						pcResult.setTrade_type(element.getText());
					}
					
					if ("transaction_id".equals(element.getName())) {
						pcResult.setTransaction_id(element.getText());
					}
					
					if ("out_trade_no".equals(element.getName())) {
						pcResult.setOut_trade_no(element.getText());
					}
					
					if ("total_fee".equals(element.getName())) {
						pcResult.setTotal_fee(Integer.valueOf(element.getText()));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pcResult;

	}
}
