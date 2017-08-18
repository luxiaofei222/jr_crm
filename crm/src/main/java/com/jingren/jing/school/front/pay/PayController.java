package com.jingren.jing.school.front.pay;

import java.io.IOException;
import java.io.StringReader;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xml.sax.InputSource;

import com.jingren.jing.school.bean.clearance.Clearance;
import com.jingren.jing.school.bean.clearance.ClearanceVideo;
import com.jingren.jing.school.bean.course.CourseVideo;
import com.jingren.jing.school.bean.mycourse.MyCourse;
import com.jingren.jing.school.bean.order.CourseOrder;
import com.jingren.jing.school.bean.user.User;
import com.jingren.jing.school.service.clearance.ClearanceService;
import com.jingren.jing.school.service.clearance.ClearanceVideoService;
import com.jingren.jing.school.service.course.CourseVideoService;
import com.jingren.jing.school.service.mycourse.MyCourseService;
import com.jingren.jing.school.service.myshoping.MyShopingService;
import com.jingren.jing.school.service.order.CourseOrderService;
import com.jingren.jing.util.DesUtil;
import com.jingren.jing.util.GetIPUtil;
import com.jingren.jing.util.ResponseUtils;
import com.jingren.jing.util.UploadAddress;
import com.jingren.jing.weixin.bean.WeixinAPP;
import com.jingren.jing.weixin.bean.WeixinPCResult;
import com.jingren.jing.weixin.util.JiexiObjec;

@Controller
@RequestMapping("sc_pay")
public class PayController {

	@Resource
	private CourseVideoService courseVideoService;
	@Resource
	private CourseOrderService CourseOrderService;
	@Resource
	private MyShopingService myShopingService;
	@Resource
	private MyCourseService myCourseService;
	@Resource
	private ClearanceService clearanceService;
	@Resource
	private ClearanceVideoService clearanceVideoService;

	//private static final String key = "2d606e307d91773b09314fe63c56d06f";
	private static final String uploadqrcodefilepath = "images/qrcode";

	/**
	 * 跳转至结算页面
	 * 
	 * @Title: PayController.java
	 * @Package com.jingren.jing.school.front.pay
	 * @Description: TODO
	 * @author 鲁晓飞 MR.Lu
	 * @date 2016年11月23日 上午9:24:23
	 * @version 网校+CRM系统 V1.0
	 * @throws Exception 
	 * @throws IOException 
	 */
	@RequestMapping("/get_pay_main.html")
	public String get_pay_main(Model model, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "des_video_id", required = false) String des_video_id) throws IOException, Exception {
		User user = (User) session.getAttribute("user_session");
		if (user != null) {
			String video_id_str=DesUtil.decrypt(des_video_id, "lu_course_pay");
			List<CourseVideo> list = new ArrayList<>();
			double price = 0;
			Map<String, Object> map = new HashMap<>();
			Integer id = Integer.valueOf(video_id_str);
			map.put("video_id", id);
			CourseVideo courseVideo = courseVideoService.getCourseVideo(map);
			price += courseVideo.getVideo_money_now();
			list.add(courseVideo);
			DecimalFormat df = new DecimalFormat("0.00");
			model.addAttribute("zong_price", df.format(price));
			model.addAttribute("list", list);
			model.addAttribute("video_id", des_video_id);
			return "/school/pay/pay";
		} else {
			String url = request.getServletPath();
			session.removeAttribute("url");
			session.setAttribute("url", url + "?video_id=" + des_video_id);
			return "/school/login/login";// 跳转登录页面
		}
	}

	/**
	* @Title: PayController.java 
	* @Package com.jingren.jing.school.front.pay 
	* @Description: TODO 通关方案支付订单页面
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年8月3日 上午11:33:39 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_pay_clearance_main.html")
	public String get_pay_clearance_main(Model model, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "clearance_id", required = false) Integer clearance_id) {
		User user = (User) session.getAttribute("user_session");
		if (user != null) {
			Map<String, Object> map=new HashMap<>();
			map.put("clearance_id", clearance_id);
			Clearance clearance=clearanceService.getClearance(map);
			List<ClearanceVideo> clearanceVideos=clearanceVideoService.getClearanceVideoList(map);
			for (ClearanceVideo clearanceVideo : clearanceVideos) {
				map.clear();
				map.put("video_id", clearanceVideo.getVideo_id());
				CourseVideo courseVideo=courseVideoService.getCourseVideo(map);
				map.clear();
				map.put("video_parent_id", courseVideo.getVideo_id());
				List<CourseVideo>courseVideos2=courseVideoService.getCourseVideoList(map);
				Integer course_num = 0;//课时
				for (CourseVideo courseVideo2 : courseVideos2) {
					map.clear();
					map.put("video_parent_id", courseVideo2.getVideo_id());
					map.put("video_level", 3);
					 course_num +=courseVideoService.getCourseVideoNumber(map);
				}
				courseVideo.setKeshi_number(course_num);
				clearanceVideo.setCourseVideo(courseVideo);
			}
			clearance.setClearanceVideos(clearanceVideos);
			model.addAttribute("clearance", clearance);
			model.addAttribute("clearance_id", clearance_id);
			return "/school/pay/clearance_pay";
		} else {
			String url = request.getServletPath();
			session.removeAttribute("url");
			session.setAttribute("url", url + "?clearance_id=" + clearance_id);
			return "/school/login/login";// 跳转登录页面
		}
	}
	
	/**
	 * 保存订单信息
	 * 
	 * @Title: PayController.java
	 * @Package com.jingren.jing.school.front.pay
	 * @Description: TODO
	 * @author 鲁晓飞 MR.Lu
	 * @date 2016年11月23日 上午11:29:26
	 * @version 网校+CRM系统 V1.0
	 * @throws Exception
	 */
	@RequestMapping("/save_course_order.html")
	public void save_course_order(HttpServletResponse response, HttpServletRequest request,
			@RequestParam(value = "str", required = false) String str,
			@RequestParam(value = "pay_type", required = false) String pay_type, CourseOrder courseOrder,
			HttpSession session) throws Exception {
		User user = (User) session.getAttribute("user_session");
		if (user != null) {
			String[] str_array = str.split(",");
			courseOrder.setUser_id(user.getUser_id());
			courseOrder.setPay_type(pay_type);// 支付方式
				Integer id = Integer.valueOf(str_array[0]);
				courseOrder.setVideo_id(id);// 课程ID
				Map<String, Object> map = new HashMap<>();
				map.put("user_id", user.getUser_id());
				map.put("video_id", id);
				MyCourse course=myCourseService.getMyCourse(map);
				if(course==null){
				myShopingService.deleteMyshopingForPay(map);
				if (CourseOrderService.saveCourseOrder(courseOrder)) {
						WeixinPCResult pcResult = creatWeixinApp(courseOrder, request);
						if (pcResult != null) {
							if ("SUCCESS".equals(pcResult.getReturn_code())) {
								if ("SUCCESS".equals(pcResult.getResult_code())) {
									String qrcodepath = UploadAddress.up_qrcode(request, pcResult.getCode_url(),
											uploadqrcodefilepath);
									courseOrder.setOther_order_no(qrcodepath);
									CourseOrderService.updateCourseOrder(courseOrder);
									session.removeAttribute("order_id");
									session.setAttribute("order_id", courseOrder.getOrder_id());
									ResponseUtils.renderText(response, qrcodepath);// 成功
								}
							}
						}
				} else {
					ResponseUtils.renderText(response, "0");
				}
				}else{
					ResponseUtils.renderText(response, "2");
				}
		} else {
			session.removeAttribute("url");
			session.setAttribute("url", "/sc_myshoping/get_my_shoping_main");
			ResponseUtils.renderText(response, "3");// 未登录
		}
	}

	/**
	* @Title: PayController.java 
	* @Package com.jingren.jing.school.front.pay 
	* @Description: TODO 套餐课程微信支付
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年8月3日 下午5:06:40 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/save_clearance_order.html")
	public void save_clearance_order(HttpServletResponse response, HttpServletRequest request,
			@RequestParam(value = "clearance_id", required = false) Integer clearance_id,
			@RequestParam(value = "pay_type", required = false) String pay_type, CourseOrder courseOrder,
			HttpSession session) throws Exception {
		User user = (User) session.getAttribute("user_session");
		if (user != null) {
			courseOrder.setUser_id(user.getUser_id());
			courseOrder.setPay_type(pay_type);// 支付方式
				courseOrder.setClearance_id(clearance_id);// 套餐ID
				Map<String, Object> map = new HashMap<>();
				map.put("clearance_id", clearance_id);
				List<ClearanceVideo> clearanceVideos=clearanceVideoService.getClearanceVideoList(map);
				if(get_myoucrse(clearanceVideos, user.getUser_id())){//必须保证该学员没有任何套餐的课程
				if (CourseOrderService.saveCourseOrder(courseOrder)) {
						WeixinPCResult pcResult = creatWeixinApp(courseOrder, request);
						if (pcResult != null) {
							if ("SUCCESS".equals(pcResult.getReturn_code())) {
								if ("SUCCESS".equals(pcResult.getResult_code())) {
									String qrcodepath = UploadAddress.up_qrcode(request, pcResult.getCode_url(),
											uploadqrcodefilepath);
									courseOrder.setOther_order_no(qrcodepath);
									CourseOrderService.updateCourseOrder(courseOrder);
									session.removeAttribute("order_id");
									session.setAttribute("order_id", courseOrder.getOrder_id());
									ResponseUtils.renderText(response, qrcodepath);// 成功
								}
							}
						}
				} else {
					ResponseUtils.renderText(response, "0");
				}
				}else{
					ResponseUtils.renderText(response, "2");
				}
		} else {
			session.removeAttribute("url");
			session.setAttribute("url", "/sc_myshoping/get_my_shoping_main");
			ResponseUtils.renderText(response, "3");// 未登录
		}
	}
	
	
	public boolean get_myoucrse(List<ClearanceVideo> clearanceVideos,Integer user_id){
		boolean bol=false;
		for (ClearanceVideo clearanceVideo : clearanceVideos) {
			Map<String, Object> map = new HashMap<>();
			map.put("user_id", user_id);
			map.put("video_id", clearanceVideo.getVideo_id());
			MyCourse course=myCourseService.getMyCourse(map);
			if(course!=null){
				bol=false;
				break;
			}else{
				bol=true;
			}
		}
		return bol;
	}
	
	/**
	 * @Title: PayController.java
	 * @Package com.jingren.jing.school.front.pay
	 * @Description: TODO 创建微信统一下单对象
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年4月17日 下午4:10:11
	 * @version 网校+CRM系统 V1.0
	 */
	public WeixinPCResult creatWeixinApp(CourseOrder courseOrder, HttpServletRequest request) throws Exception {
		WeixinAPP weixinAPP = new WeixinAPP();
		if(courseOrder.getClearance_id()!=null){
			weixinAPP.setBody("京人教育通关套餐结算订单");
			Map<String, Object> map = new HashMap<>();
			map.put("clearance_id", courseOrder.getClearance_id());
			Clearance clearance=clearanceService.getClearance(map);
			Integer peymoney = (int) (clearance.getNew_price() * 100);
			weixinAPP.setTotal_fee(peymoney);
		}else{
			Map<String, Object> map = new HashMap<>();
			map.put("video_id", courseOrder.getVideo_id());
			CourseVideo courseVideo = courseVideoService.getCourseVideo(map);
			Integer peymoney = (int) (courseVideo.getVideo_money_now() * 100);
			weixinAPP.setTotal_fee(peymoney);
		}
		weixinAPP.setDevice_info("WEB");
		weixinAPP.setNonce_str(JiexiObjec.getRandomStringByLength(8));
		weixinAPP.setSign_type("MD5");
		weixinAPP.setOut_trade_no(courseOrder.getOrder_number());
		weixinAPP.setSpbill_create_ip(GetIPUtil.getIpAddress(request));//GetIPUtil.getIpAddress(request)
		weixinAPP.setNotify_url("http://www.jingrenedu.com/wx_notify/wx_notify_PC.html");
		weixinAPP.setProduct_id(String.valueOf(courseOrder.getVideo_id()));
		// 将订单对象转为xml格式
		weixinAPP.setSign(getSign(weixinAPP));
		WeixinPCResult pcResult = httpOrder(JiexiObjec.xmlInfo(weixinAPP));
		return pcResult;
	}

	// 获取微信签名
	public String getSign(WeixinAPP payInfo) throws Exception {
		SortedMap<String, String> params = new TreeMap<String, String>();
        params.put("appid", payInfo.getAppid());
        params.put("mch_id", payInfo.getMch_id());
        params.put("device_info", "WEB"); //设备号
        params.put("nonce_str", payInfo.getNonce_str());
        params.put("body", payInfo.getBody());//商品描述
        params.put("out_trade_no", payInfo.getOut_trade_no());
        params.put("product_id", payInfo.getProduct_id());
        params.put("total_fee", String.valueOf(payInfo.getTotal_fee()));
        params.put("fee_type", payInfo.getFee_type());
        params.put("spbill_create_ip", payInfo.getSpbill_create_ip());
        params.put("trade_type", payInfo.getTrade_type());
        params.put("notify_url", payInfo.getNotify_url()); 
        String sign = "";//签名(该签名本应使用微信商户平台的API证书中的密匙key,但此处使用的是微信公众号的密匙APP_SECRET)
        sign =JiexiObjec.getSign(params);
		return sign;
	}

	// 统一下单请求
	public WeixinPCResult httpOrder(String orderInfo) {
		String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
		try {
			String sb = JiexiObjec.xmlHttpProxy(url, orderInfo, "UTF-8");
			WeixinPCResult pcResult = get_xml_object(sb.toString());
			return pcResult;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 解析xml
	@SuppressWarnings("unchecked")
	public WeixinPCResult get_xml_object(String xmlString) {
		WeixinPCResult pcResult = new WeixinPCResult();
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
					
					if ("prepay_id".equals(element.getName())) {
						pcResult.setPrepay_id(element.getText());
					}
					
					if ("code_url".equals(element.getName())) {
						pcResult.setCode_url(element.getText());
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pcResult;

	}

	/**
	 * 订单详情
	 * 
	 * @Title: PayController.java
	 * @Package com.jingren.jing.school.front.pay
	 * @Description: TODO
	 * @author 鲁晓飞 MR.Lu
	 * @date 2016年11月29日 下午4:04:10
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_order_details.html")
	public String get_order_details(Model model, @RequestParam(value = "order_id", required = false) String order_id) {
		Map<String, Object> map = new HashMap<>();
		map.put("order_id", order_id);
		CourseOrder courseOrder = CourseOrderService.getCourseOrder(map);
		map.clear();
		map.put("video_id", courseOrder.getVideo_id());
		CourseVideo courseVideo = courseVideoService.getCourseVideo(map);
		map.clear();
		map.put("video_parent_id", courseVideo.getVideo_id());
		List<CourseVideo> courseVideos2 = courseVideoService.getCourseVideoList(map);
		Integer course_num = 0;
		for (CourseVideo courseVideo2 : courseVideos2) {
			map.clear();
			map.put("video_parent_id", courseVideo2.getVideo_id());
			map.put("video_level", 3);
			course_num += courseVideoService.getCourseVideoNumber(map);
		}
		courseVideo.setKeshi_number(course_num);// 课时数
		courseOrder.setCourseVideo(courseVideo);
		long curren = courseOrder.getOrder_time().getTime();
		curren += 48 * 60 * 60 * 1000;
		Date da = new Date(curren);// 获取48小时后的时间
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		model.addAttribute("shengyutime", formatter.format(da));
		model.addAttribute("courseOrder", courseOrder);
		return "/school/person/order/order_detail";
	}
}
