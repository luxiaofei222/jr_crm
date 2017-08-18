<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/css/course_sign/reset.css" />
<link rel="stylesheet" type="text/css"
	href="/css/course_sign/registration-common.css" />
<link rel="stylesheet"
	href="/css/school/back/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css"
	href="/css/school/back/layui/css/layui.css">
<script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script type="text/javascript"
	src="/css/school/back/layui/lay/modules/laydate.js"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<script type="text/javascript" src="/js/common/icheck.js"></script>
<script type="text/javascript" src="/js/entry/entry_fouth_daxue.js"></script>
<title>填写报名信息</title>
</head>
<body>
	<div class="registration_title">
		<div class="reg_top">
			<a href="/index.jsp"><img src="/images/course_sign/logo.png" /></a><span>京人教育网上报名系统</span>
		</div>
	</div>
	<div class="reg_nav">
		<ul class="ul_nav">
			<li><span></span>
				<div class="str">
					<h3>01</h3>
					<p>选择计划</p>
				</div></li>
			<li><span></span>
				<div class="str">
					<h3>02</h3>
					<p>选择报名条件</p>
				</div></li>
			<li><span></span>
				<div class="str">
					<h3>03</h3>
					<p>选择报名点</p>
				</div></li>
			<li class="step_now"><span></span>
				<div class="str">
					<h3>04</h3>
					<p>填写报名信息</p>
				</div></li>
			<li><span></span>
				<div class="str">
					<h3>05</h3>
					<p>确认报考信息</p>
				</div></li>
			<li><span></span>
				<div class="str">
					<h3>06</h3>
					<p>报名费缴付</p>
				</div></li>
			<li><span></span>
				<div class="str">
					<h3>07</h3>
					<p>报名成功</p>
				</div></li>
		</ul>
	</div>
	<!--  reg_nav   end   fa-plus-square  fa-minus-square-->
	<div class="reg_content">
		<form class="form-horizontal add_qiye_dialog"
			enctype="multipart/form-data" id="myform">
			<div class="content_info4">
				<div class="reg_message_title">
					<i class="fa fa-plus-square"></i>报名信息
				</div>
				<div class="xiangxi_con">
					<ul class="reg_message_info">
						<li>报名计划：${entryPlan.entryplan_content }-${entryCondition.kaoshi_pici }</li>
						<li>报考学校：${university.university_name }</li>
						<li>报考层次：${entryCondition.baokao_cengci }</li>
						<li>报名地点：${entryInfo.entryProvince }-${entryInfo.entryCity }-${entryInfo.entrySchool }</li>
					</ul>
				</div>
			</div>
			<div class="content_info4">
				<div class="reg_message_title">
					<i class="fa fa-plus-square"></i>报考专业
				</div>
				<div class="xiangxi_con">
					<div class="reg_item">
						<label>报考专业：</label>
						<div class="reg_items">
							<select id="zhuanye_id" name="zhuanye_id">
								<c:forEach items="${universities }" var="univer">
									<option value="${univer.university_id }">${univer.university_zhuanye }</option>
								</c:forEach>
							</select>
						</div>
						<div class="reg_error">请选择您要报考的专业</div>
					</div>
					<div class="clear"></div>
				</div>
			</div>
			<div class="content_info4">
				<div class="reg_message_title">
					<i class="fa fa-plus-square"></i>用户信息
				</div>
				<div class="xiangxi_con" style="border-bottom: #fff;">
					<div class="reg_item">
						<label>姓名：</label>
						<div class="reg_items">
							<input type="text" id="entryUserName"
								onblur="check_entryUserName()" name="entryUserName" />
						</div>
						<div class="reg_error">
							<span>*</span>请输入您的姓名
						</div>
					</div>
					<div class="reg_item">
						<label>曾用名：</label>
						<div class="reg_items">
							<input type="text" id="before_name"name="before_name" />
						</div>
						<div class="reg_error">
							请输入您的曾用名
						</div>
					</div>
					<div class="clear"></div>
					<div class="reg_item">
						<label>证件类型：</label>
						<div class="reg_items">
							<input type="text" id="documentType" name="documentType"
								value="身份证" readonly="readonly" />
						</div>
						<div class="reg_error">
							<span>*</span>只支持身份证
						</div>
					</div>
					<div class="clear"></div>
					<div class="reg_item">
						<label>证件号码：</label>
						<div class="reg_items">
							<input type="text" onblur="check_documentNumber()"
								id="documentNumber" name="documentNumber" />
						</div>
						<div class="reg_error">
							<span>*</span>请输入您的身份证号
						</div>
					</div>
					<div class="clear"></div>
					<div class="reg_item">
						<label>性别：</label>
						<div class="reg_items">
						<input class="layui-input" readonly="readonly"
							name="entryUserSex" id="entryUserSex" />
						<!-- 	<select id="entryUserSex" name="entryUserSex">
								<option value="男">男</option>
								<option value="女">女</option>
							</select> -->
						</div>
						<!-- <div class="reg_error">请选择您的性别</div> -->
					</div>
					<div class="clear"></div>
					<div class="reg_item">
						<label>出生日期：</label>
						<div class="reg_items">
							<input readonly="readonly" name="entryUserBirthday"
								id="entryUserBirthday" type="text" />
						</div>
						<div class="reg_error">
							<span>*</span>您的出生日期
						</div>
					</div>
					<div class="clear"></div>
					<div class="reg_item">
						<label>身份证正面照片：</label>
						<div class="reg_items">
							<input type="text" readonly="readonly" class="photo"
								id="zhengmian_pic_name" onblur="check_zhengjian_pic()"
								class="photo" /> <a href="javascript:void(0)" class="btn_photo">浏览<input
								type="file" onchange="get_file_name(this)" id="zhengmian_pic"
								name="zhengmian_pic" class="btn_photo" /></a>
						</div>
						<div class="reg_error">
							<span>*</span>请选择您的身份证正面照片
						</div>
					</div>
					<div class="clear"></div>
					<div class="reg_item">
						<label>身份证背面照片：</label>
						<div class="reg_items">
							<input type="text" readonly="readonly" class="photo"
								id="fanmian_pic_name" onblur="check_zhengjian_pic()" /> <a
								href="javascript:void(0)" class="btn_photo">浏览<input
								type="file" id="fanmian_pic" onchange="get_file_name(this)"
								name="fanmian_pic" class="btn_photo" /></a>
						</div>
						<div class="reg_error">
							<span>*</span>请选择您的身份证背面照片
						</div>
					</div>
					<div class="clear"></div>
					<div class="reg_item">
						<label>民族：</label>
						<div class="reg_items">
							<select id="entryUserNation" name="entryUserNation">
								<c:forEach items="${nations }" var="na">
									<option value="${na.nation }">${na.nation }</option>
								</c:forEach>
							</select>
						</div>
						<div class="reg_error">请选择您的民族</div>
					</div>
					<div class="clear"></div>
					<div class="reg_item">
						<label>政治面貌：</label>
						<div class="reg_items">
							<select id="entryPolitical" name="entryPolitical">
								<option value="党员">党员</option>
								<option value="团员">团员</option>
								<option value="群众">群众</option>
							</select>
						</div>
						<div class="reg_error">请选择您的政治面貌</div>
					</div>
					<div class="clear"></div>
					<div class="reg_item">
						<label>健康状况：</label>
						<div class="reg_items">
							<select id="health" name="health">
								<option value="良好">良好</option>
								<option value="疾病">疾病</option>
							</select>
						</div>
						<div class="reg_error">请选择您的健康状况</div>
					</div>
					<div class="clear"></div>
					<div class="reg_item">
						<label>籍贯：</label>
						<div class="reg_items">
							<select style="width: 115px;" onchange="get_jiguan_city()"
								name="province_jiguang" id="province_jiguan">
								<option value="0">请选择省份</option>
								<c:forEach items="${cities }" var="pro">
									<option value="${pro.id }">${pro.name }</option>
								</c:forEach>
							</select> <select style="width: 115px;" id="jiguan_city"
								name="jiguan_city">
								<option value="0">请选择城市</option>
							</select>
						</div>
						<div class="reg_error">请选择您的籍贯</div>
					</div>
					<div class="clear"></div>
					<div class="reg_item">
						<label>户口所在地：</label>
						<div class="reg_items">
							<select style="width: 72px;" onchange="get_city()"
								name="province" id="province">
								<option value="0">请选择省份</option>
								<c:choose>
									<c:when test="${entryCondition.university_id==34 }">
										<option value="130000">河北省</option>
									</c:when>
									<c:when test="${entryCondition.university_id==1}">
										<c:forEach items="${cities }" var="pro">
											<c:if
												test="${pro.id!=110000 && pro.id!=310000 &&pro.id!=440000}">
												<option value="${pro.id }">${pro.name }</option>
											</c:if>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<c:forEach items="${cities }" var="pro">
											<option value="${pro.id }">${pro.name }</option>
										</c:forEach>
									</c:otherwise>
								</c:choose>

							</select> <select style="width: 72px;" onchange="get_area()"
								id="user_city" name="user_city">
								<option value="0">请选择城市</option>
							</select> <select style="width: 72px;" id="entryUserArea"
								name="entryUserArea">
								<option value="0">请选择地区</option>
							</select>
						</div>
						<div class="reg_error">请选择您的户口所在地</div>
					</div>
					<div class="clear"></div>
					<div class="reg_item">
						<label>电子邮箱：</label>
						<div class="reg_items">
							<input type="text" onblur="check_entryUserMail()"
								id="entryUserMail" name="entryUserMail" />
						</div>
						<div class="reg_error">
							<span>*</span>请输入您的电子邮箱
						</div>
					</div>
					<div class="clear"></div>
					<div class="reg_item">
						<label>联系QQ：</label>
						<div class="reg_items">
							<input type="text" id="user_qq" name="user_qq" />
						</div>
						<div class="reg_error">请输入您的联系QQ</div>
					</div>
					<div class="clear"></div>
					<div class="reg_item">
						<label>邮编：</label>
						<div class="reg_items">
							<input type="text" id="zipCode" name="zipCode" />
						</div>
						<div class="reg_error">请输入您的邮编</div>
					</div>
					<div class="clear"></div>
					<div class="reg_item">
						<label>学历照片：</label>
						<div class="reg_items">
							<input type="text" readonly="readonly" class="photo"
								id="xueli_pic_name" /> <a href="javascript:void(0)"
								class="btn_photo">浏览 <input type="file" id="xueli_pic"
								onchange="get_file_name(this)" name="xueli_pic"
								class="btn_photo" /></a>
						</div>
						<div class="reg_error">
							请选择您学历或者证书的照片<br />高起专不需要提供最高学历，专升本需提供专科毕业证照片（学信网可查）。
						</div>
					</div>
					<div class="clear"></div>
					<div class="reg_item">
						<label>免冠照片：</label>
						<div class="reg_items">
							<input type="text" class="photo" readonly="readonly"
								onblur="check_user_pic_name()" id="user_pic_name" id="userpic" />
							<a href="javascript:void(0)" class="btn_photo">浏览<input
								type="file" onchange="get_file_name(this)" id="user_pic"
								name="user_pic" class="btn_photo" /></a>
						</div>
						<div class="reg_error">
							<span>*</span>请选择您的蓝色小二寸免冠照片
						</div>
					</div>
					<div class="clear"></div>
					<div class="reg_item">
						<label>手机号码：</label>
						<div class="reg_items">
							<input type="text" id="entryUserPhone"
								onblur="check_entryUserPhone()" name="entryUserPhone" />
						</div>
						<div class="reg_error">
							<span>*</span>请输入您的手机号码
						</div>
					</div>
					<div class="clear"></div>
					<div class="reg_item">
						<label>业务员推广号：</label>
						<div class="reg_items">
						    <!-- <input class="layui-input" name="job_number" id="job_number" /> -->
							<input type="text" onblur="check_job_number()" name="job_number" id="job_number" />
						</div>
						<div class="reg_error">请输入业务员推广号，没有则不填或者联系您的业务员获取！</div>
					</div>
					<div class="clear"></div>
					<div class="reg_item">
						<label>个人简历：</label>
						<div class="jianli">
							<div class="neirong">
								<div class="neirongs">
									<div class="jianli_item">
										<label>工作时间：</label>
										<div class="jianli_items">
											<input class="str_date" value="2017年01月" id="star_time_str"
												name="star_time_str" type="text" /> <span class="zhi">至</span>
											<input class="end_date" value="至今" id="end_time_str"
												name="end_time_str" type="text" />
										</div>
									</div>
									<div class="clear"></div>
									<div class="jianli_item">
										<label>所在单位：</label>
										<div class="jianli_items">
											<input type="text" value="暂无" name="suozaidanwei_str" />
										</div>
									</div>
									<div class="clear"></div>
									<div class="jianli_item">
										<label>证明人：</label>
										<div class="jianli_items">
											<input type="text" value="暂无" name="zhengmingren_str" />
										</div>
									</div>
									<div class="anniu">
										<i class="fa fa-plus" onclick="tjjl(this)"></i>
									</div>
									<div class="clear"></div>
								</div>
							</div>
						</div>
					</div>
					<div class="clear"></div>
					<div class="reg_item">
						<label>家庭成员：</label>
						<div class="jiating">
							<div class="neirong">
								<div class="neirongs">
									<div class="jiating_item">
										<label>姓名：</label>
										<div class="jiating_items">
											<input type="text" value="暂无" name="xingming_str" />
										</div>
									</div>
									<div class="clear"></div>
									<div class="jiating_item">
										<label>家庭关系：</label>
										<div class="jiating_items">
											<input type="text" value="暂无"  name="guanxi_str" />
										</div>
									</div>
									<div class="clear"></div>
									<div class="jiating_item">
										<label>年龄：</label>
										<div class="jiating_items">
											<input type="text" value="暂无" name="nianling_str" />
										</div>
									</div>
									<div class="clear"></div>
									<div class="jiating_item">
										<label>所在单位：</label>
										<div class="jiating_items">
											<input type="text" value="暂无" name="family_danwei_str" />
										</div>
									</div>
									<div class="anniu">
										<i class="fa fa-plus" onclick="tjjt(this)"></i>
									</div>
									<div class="clear"></div>
								</div>
							</div>
						</div>
					</div>
					<div class="clear"></div>
					<p class="ps">注：电话必须真实有效，方便以后及时接收信息，如电话错误，信息收到不及时，导致无法正常毕业，恕概不负责。</p>
				</div>
				<!--  xiangxi_con end  -->
			</div>
			<div class="operations">
				<input type="button"
					onclick="get_third(${entryInfo.entrycondition_id},${entryInfo.entryInfoId})"
					value="上一步" /><input onclick="get_fifth(${entryInfo.entryInfoId})"
					type="button" value="下一步" />
			</div>
		</form>
	</div>
</body>
<script type="text/javascript">
  function tjjl(obj){
	  var val= $(obj).attr("class").replace(/\s+/g, "");
	   if(val=="fafa-plus"){
		  $(obj).removeClass("fa-plus");
		  $(obj).addClass("fa-minus"); 
		  $(obj).parent().parent().after('<div class="neirongs"><div class="jianli_item"><label>工作时间：</label><div class="jianli_items"><input value="2017年01月" id="star_time_str" name="star_time_str" class="str_date" placeholder="请输入X年X月" type="text" /><span class="zhi">至</span><input id="end_time_str" name="end_time_str"  class="end_date" value="至今" type="text" /></div></div><div class="clear"></div><div class="jianli_item"><label>所在单位：</label><div class="jianli_items"><input type="text" value="暂无" name="suozaidanwei_str"  /></div></div><div class="clear"></div><div class="jianli_item"><label>证明人：</label><div class="jianli_items"><input name="zhengmingren_str" value="暂无" type="text" /></div></div><div class="anniu"><i class="fa fa-plus" onclick="tjjl(this)"></i></div><div class="clear"></div></div>');
		  $(obj).parent().css("background-color","#999"); 
		   }
		   else{   
			$(obj).parent().parent().remove();
	  }
	  }
  
  function tjjt(obj){
	  var val= $(obj).attr("class").replace(/\s+/g, "");
	   if(val=="fafa-plus"){
		  $(obj).removeClass("fa-plus");
		  $(obj).addClass("fa-minus"); 
		  $(obj).parent().parent().after('<div class="neirongs"><div class="jiating_item"><label>姓名：</label><div class="jianli_items"><input type="text" value="暂无" name="xingming_str" /></div></div><div class="clear"></div><div class="jiating_item"><label>家庭关系：</label><div class="jiating_items"><input name="guanxi_str" value="暂无" type="text" /></div></div><div class="clear"></div><div class="jiating_item"><label>年龄：</label><div class="jiating_items"><input name="nianling_str" type="text" value="暂无"/></div></div><div class="clear"></div><div class="jiating_item"><label>所在单位：</label><div class="jiating_items"><input type="text" name="family_danwei_str" value="暂无" /></div></div><div class="anniu"><i class="fa fa-plus" onclick="tjjt(this)"></i></div><div class="clear"></div></div>');
		  $(obj).parent().css("background","#999");
		   }
		   else{			
			$(obj).parent().parent().remove();
	  }
	  }
</script>
</html>