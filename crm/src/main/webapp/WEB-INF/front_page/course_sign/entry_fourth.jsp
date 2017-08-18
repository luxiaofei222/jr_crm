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
<script type="text/javascript" src="/js/entry/entry_fouth.js"></script>
<title>选择报名点</title>
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
	<form class="form-horizontal add_qiye_dialog" enctype="multipart/form-data" id="myform">
		<div class="content_info4">
			<div class="reg_message_title">
				<i class="fa fa-plus-square"></i>报名信息
			</div>
			<div class="xiangxi_con">
				<ul class="reg_message_info">
					<li>报名计划：${entryPlan.entryplan_content }</li>
					<li>职业规划：${courseMenu.course_name }-${entryCondition.chengkao_kemu }${dictionary.dictionary_name }</li>
					<li>报名地点：${entryInfo.entryProvince }-${entryInfo.entryCity }-${entryInfo.entrySchool }</li>
					<li>符合条件：${entryCondition.entrycondition_content }</li>
				</ul>
			</div>
		</div>
		<div class="content_info4">
			<div class="reg_message_title">
				<i class="fa fa-plus-square"></i>报考类别
			</div>
			<div class="xiangxi_con">
				<div class="reg_item">
					<label>报考类别：</label>
					<div class="reg_items">
						<select id="entryCategory" name="entryCategory">
							<option value="非定向">非定向</option>
							<option value="定向">定向</option>
							<option value="委培">委培</option>
							<option value="自筹">自筹</option>
						</select>
					</div>
					<div class="reg_error">请选择您的报考类别</div>
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
					<label>证件类型：</label>
					<div class="reg_items">
						<select id="documentType" name="documentType">
							<option value="身份证">身份证</option>
							<option value="护照">护照</option>
						</select>
					</div>
					<div class="reg_error">请选择您的证件类型</div>
				</div>
				<div class="clear"></div>
				<div id="other_card" style="display: none;">
					<div class="reg_item">
						<label>证件照片：</label>
						<div class="reg_items">
							<input type="text" readonly="readonly" id="zhengjian_pic_name"
								onblur="check_zhengjian_pic('证件')" class="photo" /> <a
								href="javascript:void(0)" class="btn_photo">浏览<input
								type="file" id="zhengjian_pic" onchange="get_file_name(this)"
								name="zhengjian_pic" class="btn_photo" /></a>
						</div>
						<div class="reg_error">
							<span>*</span>请选择您的证件照片，大小在100k以内
						</div>
					</div>
				</div>
				<div id="id_card">
					<div class="reg_item">
						<label>正面照片：</label>
						<div class="reg_items">
							<input type="text" readonly="readonly" class="photo"
								id="zhengmian_pic_name" onblur="check_zhengjian_pic('身份证正面')" />
							<a href="javascript:void(0)" class="btn_photo">浏览 <input
								type="file" onchange="get_file_name(this)" id="zhengmian_pic"
								name="zhengmian_pic" class="btn_photo" /></a>
						</div>
						<div class="reg_error">
							<span>*</span>请选择您的身份证正面照片，大小在100k以内
						</div>
					</div>
					<div class="clear"></div>
					<div class="reg_item">
						<label>背面照片：</label>
						<div class="reg_items">
							<input type="text" readonly="readonly" class="photo"
								id="fanmian_pic_name" onblur="check_zhengjian_pic('身份证背面')" />
							<a href="javascript:void(0)" class="btn_photo">浏览<input
								type="file" id="fanmian_pic" onchange="get_file_name(this)"
								name="fanmian_pic" class="btn_photo" /></a>
						</div>
						<div class="reg_error">
							<span>*</span>请选择您的身份证背面照片，大小在100k以内
						</div>
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
						<span>*</span>请输入您的证件号码
					</div>
				</div>
				<div class="clear"></div>
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
				<div class="clear"></div>
				<div class="reg_item">
					<label>性别：</label>
					<div class="reg_items">
					<input class="layui-input" readonly="readonly"
							name="entryUserSex" id="entryUserSex" />
						<!-- <select id="entryUserSex" name="entryUserSex">
							<option value="男">男</option>
							<option value="女">女</option>
						</select> -->
					</div>
					<div class="reg_error">请选择您的性别</div>
				</div>
				<div class="clear"></div>
				<div class="reg_item">
					<label>出生日期：</label>
					<div class="reg_items">
						<input class="layui-input" readonly="readonly"
							name="entryUserBirthday" id="entryUserBirthday" />
					</div>
					<div class="reg_error">
						<span>*</span>请选择您的出生日期
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
					<label>户口所在地：</label>
					<div class="reg_items">
						<select style="width: 72px;" onchange="get_city()" name="province"
							id="province">
							<option value="0">请选择省份</option>
							<c:forEach items="${cities }" var="pro">
								<option value="${pro.id }">${pro.name }</option>
							</c:forEach>
						</select> <select style="width: 72px;" onchange="get_area()" id="user_city"
							name="user_city">
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
					<label>所在单位：</label>
					<div class="reg_items">
						<input type="text" onblur="check_entryUserUnit()"
							id="entryUserUnit" name="entryUserUnit" />
					</div>
					<div class="reg_error">
						<span>*</span>请输入您的所在单位
					</div>
				</div>
				<div class="clear"></div>
				<div class="reg_item">
					<label>所在职位：</label>
					<div class="reg_items">
						<input type="text" onblur="check_entryUserPosition()"
							id="entryUserPosition" name="entryUserPosition" />
					</div>
					<div class="reg_error">
						<span>*</span>请输入您的所在职位
					</div>
				</div>
				<div class="clear"></div>
				<div class="reg_item">
					<label>现住地址：</label>
					<div class="reg_items">
						<input type="text" id="entryUserAddress" name="entryUserAddress" />
					</div>
					<div class="reg_error">请输入您的现住地址</div>
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
					<label>文化程度：</label>
					<div class="reg_items">
						<select id="entryUserEducation" name="entryUserEducation">
							<option value="研究生">研究生</option>
							<option value="本科">本科</option>
							<option value="专科">专科</option>
							<option value="专科以下">专科以下</option>
						</select>
					</div>
					<div class="reg_error">
						<span>*</span>请选择您的文化程度
					</div>
				</div>
				<div class="clear"></div>
				<div class="reg_item">
					<label>学历照片：</label>
					<div class="reg_items">
						<input type="text" readonly="readonly" class="photo" id="xueli_pic_name" /> <a
							href="javascript:void(0)" class="btn_photo">浏览 <input
							type="file" id="xueli_pic" onchange="get_file_name(this)"
							name="xueli_pic" class="btn_photo" /></a>
					</div>
					<div class="reg_error">
						请选择您学历或者证书的照片，大小在100k以内
						<!-- onblur="check_xueli_pic_name()" -->
					</div>
				</div>
				<div class="clear"></div>
				<div class="reg_item">
					<label>参加工作时间：</label>
					<div class="reg_items">
						<input class="layui-input" name="inWorkTime" id="inWorkTime"
							placeholder="点击获取日期" />
					</div>
					<div class="reg_error">请选择您的参加工作时间</div>
				</div>
					<div class="clear"></div>
				<div class="reg_item">
					<label>连续工龄：</label>
					<div class="reg_items">
						<input type="text" id="workYears" readonly="readonly" name="workYears" />
					</div>
					<div class="reg_error">您的连续工龄</div>
				</div>
				<div class="clear"></div>
				<div class="reg_item">
					<label>照片：</label>
					<div class="reg_items">
						<input type="text" class="photo" readonly="readonly"
							onblur="check_user_pic_name()" id="user_pic_name" id="userpic" />
						<a href="javascript:void(0)" class="btn_photo">浏览<input
							type="file" onchange="get_file_name(this)" id="user_pic"
							name="user_pic" class="btn_photo" /></a>
					</div>
					<div class="reg_error">
						<span>*</span>请选择您的蓝色小二寸照片，大小在100k以内
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
					<label>紧急联系人：</label>
					<div class="reg_items">
						<input class="layui-input" name="jinji_name" id="jinji_name"/>
					</div>
					<div class="reg_error">请输入紧急联系人姓名</div>
				</div>
				<div class="clear"></div>
				<div class="reg_item">
					<label>紧急联系人电话：</label>
					<div class="reg_items">
						<input class="layui-input" name="jinji_phone" id="jinji_phone" />
					</div>
					<div class="reg_error">请输入紧急联系人电话</div>
				</div>
				<div class="clear"></div>
				<div class="reg_item">
					<label>业务员推广号：</label>
					<div class="reg_items">
						<input class="layui-input"  onblur="check_job_number()" name="job_number" id="job_number"/>
					</div>
					<div class="reg_error">请输入业务员推广号，没有则填无或者联系您的业务员获取！</div>
				</div>
				<div class="clear"></div>
				<p class="ps">注：请填写本人真实的手机号码，如因手机号码填写错误导致报名失败由考生本人承担责任。</p>
			</div>
			<!--  xiangxi_con end  -->
		</div>
		<div class="operations">
			<input type="button"
				onclick="get_third(${entryInfo.entrycondition_id},${entryInfo.entryInfoId})"
				value="上一步" /><input type="button"
				onclick="get_fifth(${entryInfo.entryInfoId})" value="下一步" />
		</div>
		</form>
	</div>
</body>
<script type="text/javascript">
$(document).ready(function(){

$('#documentType').change(function(){ 

var zhjValue=$(this).children('option:selected').val();//这就是selected的值 

if(zhjValue != "身份证"){

$("#other_card").show();
$("#id_card").hide();

}else if (zhjValue == "身份证"){

$("#other_card").hide();
$("#id_card").show();

}

});

});
</script>
</html>