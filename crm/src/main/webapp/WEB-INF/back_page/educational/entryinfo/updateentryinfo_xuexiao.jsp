<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" type="text/css"
	href="/css/school/back/common/reset.css">
<link rel="stylesheet" type="text/css"
	href="/css/edu/entryinfo/e_tankuang.css" />
<link rel="stylesheet" href="/css/school/back/common/bootstrap.min.css" />
<link rel="stylesheet"
	href="/css/school/back/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css"
	href="/css/edu/entryinfo/e_tankuang.css" />
<link rel="stylesheet" type="text/css"
	href="/css/school/back/layui/css/layui.css">
<script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
<script type="text/javascript"
	src="/js/school/back/common/bootstrap.min.js"></script>
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script type="text/javascript"
	src="/css/school/back/layui/lay/modules/laydate.js"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<script src="/js/edu/add_entryinfo.js" type="text/javascript"></script>
<script>
	$(function() {
		$("#user_pic")
				.change(
						function() {
							var $file = $(this);
							var fileObj = $file[0];
							var windowURL = window.URL || window.webkitURL;
							var dataURL;
							var $img = $("#preview1");

							if (fileObj && fileObj.files && fileObj.files[0]) {
								dataURL = windowURL
										.createObjectURL(fileObj.files[0]);
								$img.attr('src', dataURL);
							} else {
								dataURL = $file.val();
								var imgObj = document.getElementById("preview");
								// 两个坑:
								// 1、在设置filter属性时，元素必须已经存在在DOM树中，动态创建的Node，也需要在设置属性前加入到DOM中，先设置属性在加入，无效；
								// 2、src属性需要像下面的方式添加，上面的两种方式添加，无效；
								imgObj.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
								imgObj.filters
										.item("DXImageTransform.Microsoft.AlphaImageLoader").src = dataURL;

							}
						});

		$("#zhengjian_pic")
				.change(
						function() {
							var $file = $(this);
							var fileObj = $file[0];
							var windowURL = window.URL || window.webkitURL;
							var dataURL;
							var $img = $("#preview2");

							if (fileObj && fileObj.files && fileObj.files[0]) {
								dataURL = windowURL
										.createObjectURL(fileObj.files[0]);
								$img.attr('src', dataURL);
							} else {
								dataURL = $file.val();
								var imgObj = document.getElementById("preview");
								// 两个坑:
								// 1、在设置filter属性时，元素必须已经存在在DOM树中，动态创建的Node，也需要在设置属性前加入到DOM中，先设置属性在加入，无效；
								// 2、src属性需要像下面的方式添加，上面的两种方式添加，无效；
								imgObj.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
								imgObj.filters
										.item("DXImageTransform.Microsoft.AlphaImageLoader").src = dataURL;

							}
						});

		$("#zhengmian_pic")
				.change(
						function() {
							var $file = $(this);
							var fileObj = $file[0];
							var windowURL = window.URL || window.webkitURL;
							var dataURL;
							var $img = $("#preview3");

							if (fileObj && fileObj.files && fileObj.files[0]) {
								dataURL = windowURL
										.createObjectURL(fileObj.files[0]);
								$img.attr('src', dataURL);
							} else {
								dataURL = $file.val();
								var imgObj = document.getElementById("preview");
								// 两个坑:
								// 1、在设置filter属性时，元素必须已经存在在DOM树中，动态创建的Node，也需要在设置属性前加入到DOM中，先设置属性在加入，无效；
								// 2、src属性需要像下面的方式添加，上面的两种方式添加，无效；
								imgObj.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
								imgObj.filters
										.item("DXImageTransform.Microsoft.AlphaImageLoader").src = dataURL;

							}
						});

		$("#fanmian_pic")
				.change(
						function() {
							var $file = $(this);
							var fileObj = $file[0];
							var windowURL = window.URL || window.webkitURL;
							var dataURL;
							var $img = $("#preview4");

							if (fileObj && fileObj.files && fileObj.files[0]) {
								dataURL = windowURL
										.createObjectURL(fileObj.files[0]);
								$img.attr('src', dataURL);
							} else {
								dataURL = $file.val();
								var imgObj = document.getElementById("preview");
								// 两个坑:
								// 1、在设置filter属性时，元素必须已经存在在DOM树中，动态创建的Node，也需要在设置属性前加入到DOM中，先设置属性在加入，无效；
								// 2、src属性需要像下面的方式添加，上面的两种方式添加，无效；
								imgObj.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
								imgObj.filters
										.item("DXImageTransform.Microsoft.AlphaImageLoader").src = dataURL;

							}
						});

		$("#xueli_pic")
				.change(
						function() {
							var $file = $(this);
							var fileObj = $file[0];
							var windowURL = window.URL || window.webkitURL;
							var dataURL;
							var $img = $("#preview5");

							if (fileObj && fileObj.files && fileObj.files[0]) {
								dataURL = windowURL
										.createObjectURL(fileObj.files[0]);
								$img.attr('src', dataURL);
							} else {
								dataURL = $file.val();
								var imgObj = document.getElementById("preview");
								// 两个坑:
								// 1、在设置filter属性时，元素必须已经存在在DOM树中，动态创建的Node，也需要在设置属性前加入到DOM中，先设置属性在加入，无效；
								// 2、src属性需要像下面的方式添加，上面的两种方式添加，无效；
								imgObj.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
								imgObj.filters
										.item("DXImageTransform.Microsoft.AlphaImageLoader").src = dataURL;

							}
						});

	});
	
	//获取城市地点
	function get_entry_city(){
		var entry_province_id=$("#entry_province_id").val();
		$.post("/entry_place/get_sub_entryplace.html",{
			'plcae_id':entry_province_id
		},function(data){
			$("#entry_province_id").html(data);
		})
	}
	//获取学校地点
	function get_entry_school(){
		var entry_city_id=$("#entry_city_id").val();
		$.post("/entry_place/get_sub_entryplace.html",{
			'plcae_id':entry_city_id
		},function(data){
			$("#entry_school_id").html(data);
		})
	}
</script>
<style type="text/css">
input.update1,input.update2{
    border: none;
    width: 60px;
    height: 25px;
    text-align: center;
    line-height: 25px;
    color: #fff;
    cursor: pointer;
    background-color: #58d68d;
    border-radius: 3px;
}
input.update2 {
    background-color: #f1c40f;
}
</style>
<div class="tianjia_xueyuan_dialog">
	<form class="form-horizontal import_xueyuanshuju_dialog"
		enctype="multipart/form-data" id="myform" style="padding: 20px;">
		<%-- <div class="form-group">
			<label class="col-xs-2 control-label">报名计划：</label>
			<div class="col-xs-10">
				<select id="entryplanId" onchange="get_shenbaotiaojian(this)"
					name="entryplanId">
					<c:forEach items="${entryPlans }" var="entryplan">
						<c:if test="${entryInfo.entryplanId==entryplan.entryplan_id  }">
							<option selected="selected" value="${entryplan.entryplan_id }">${entryplan.entryplan_content }</option>
						</c:if>
						<c:if test="${entryInfo.entryplanId!=entryplan.entryplan_id  }">
							<option value="${entryplan.entryplan_id }">${entryplan.entryplan_content }</option>
						</c:if>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-xs-2 control-label">职业类别：</label>
			<div class="col-xs-10">
				<select id="course_id" onchange="get_dic_list(this)"
					name="course_id">
					<c:forEach items="${conditions }" var="con">
						<c:if
							test="${entryInfo.entrycondition_id== con.entrycondition_id}">
							<option selected="selected" value="${con.entrycondition_id }">${con.courseMenu.course_name }</option>
						</c:if>
						<c:if
							test="${entryInfo.entrycondition_id!= con.entrycondition_id}">
							<option value="${con.entrycondition_id }">${con.courseMenu.course_name }</option>
						</c:if>
					</c:forEach>
				</select> 
			</div>
		</div>
			<div class="form-group">
			<label class="col-xs-2 control-label">报考学校：</label>
			<div class="col-xs-10">
				<select id="university_id" name="university_id">
					<c:forEach items="${quchongconditions }" var="con">
						<option  value="${con.university_id }">${con.xuexiao }</option>
					</c:forEach>
				</select> 
			</div>
		</div>
		<div class="form-group">
			<label class="col-xs-2 control-label">报考层次：</label>
			<div class="col-xs-10">
				${entryCondition2.baokao_cengci }
			</div>
		</div>
		<div class="form-group">
			<label class="col-xs-2 control-label">相关业务员：</label>
			<div class="col-xs-10">
				<select id="employee_id" name="employee_id">
					<option value="">请选择业务员</option>
					<c:forEach items="${employees }" var="employee">
					<c:if test="${entryInfo.employee_id==mployee.employee_id }">
					<option selected="selected" value="${employee.employee_id }">${employee.employee_name }</option>
					</c:if>
					<c:if test="${entryInfo.employee_id!=mployee.employee_id }">
					<option value="${employee.employee_id }">${employee.employee_name }</option>
					</c:if>
					</c:forEach>
				</select> 
			</div>
		</div>
		<div class="form-group" id="tiaojianlist">
			<!-- 报名条件  -->
			<label class="col-xs-2 control-label">报名条件：</label>
			<link rel="stylesheet" href="/css/school/back/common/flat-ui.min.css" />
			<script src="/js/school/back/common/flat-ui.min.js"></script>
			<script src="/js/school/back/common/application.js"></script>
			<div class="col-xs-10">
				<c:forEach items="${subconditions }" var="condition">
					<c:if
						test="${entryInfo.entrycondition_id== condition.entrycondition_id}">
						<label class="radio" for="radio4b${condition.entrycondition_id }">
							<input type="radio" name="optionsRadios" data-toggle="radio"
							value="${condition.entrycondition_id }"
							id="radio4b${condition.entrycondition_id }" required
							checked="checked">${condition.entrycondition_content }
						</label>
					</c:if>
					<c:if
						test="${entryInfo.entrycondition_id!= condition.entrycondition_id}">
						<label class="radio" for="radio4b${condition.entrycondition_id }">
							<input type="radio" name="optionsRadios" data-toggle="radio"
							value="${condition.entrycondition_id }"
							id="radio4b${condition.entrycondition_id }" required>${condition.entrycondition_content }
						</label>
					</c:if>
				</c:forEach>
			</div>
		</div>
		<div class="form-group">
			<label class="col-xs-2 control-label">选择报名点：</label>
			<div class="col-xs-10">
				<select class="col-xs-3" onchange="get_entry_city()"
					style="width: 30%; margin-right: 35px;" id="entry_province_id"
					name="entry_province_id">
					<c:forEach items="${entryPlaces }" var="place">
						<c:if test="${entryInfo.entryProvince==place.entryplace_name }">
							<option selected="selected" value="${place.entryplace_id }">${place.entryplace_name }</option>
						</c:if>
						<c:if test="${entryInfo.entryProvince!=place.entryplace_name }">
							<option value="${place.entryplace_id }">${place.entryplace_name }</option>
						</c:if>
					</c:forEach>
				</select> <select class="col-xs-3" style="width: 30%; margin-right: 35px;"
					onchange="get_entry_school()" id="entry_city_id"
					name="entry_city_id">
					<c:forEach items="${entryPlaces_sub }" var="place">
						<c:if test="${entryInfo.entryCity== place.entryplace_name}">
							<option selected="selected" value="${place.entryplace_id }">${place.entryplace_name }</option>
						</c:if>
						<c:if test="${entryInfo.entryCity!= place.entryplace_name}">
							<option value="${place.entryplace_id }">${place.entryplace_name }</option>
						</c:if>
					</c:forEach>
				</select> <select class="col-xs-3" style="width: 30%;" id="entry_school_id"
					name="entry_school_id">
					<c:forEach items="${entryPlaces_sub_sub }" var="place">
						<c:if test="${entryInfo.entrySchool== place.entryplace_name}">
							<option selected="selected" value="${place.entryplace_id }">${place.entryplace_name }</option>
						</c:if>
						<c:if test="${entryInfo.entrySchool!= place.entryplace_name}">
							<option value="${place.entryplace_id }">${place.entryplace_name }</option>
						</c:if>
					</c:forEach>
				</select>
			</div>
		</div> --%>
		<div class="form-group">
			<label class="col-xs-2 control-label" style="text-align: left;">上传图片：</label>
			<div class="col-xs-10">
				<ul class="pics">
					<li>
						<div class="xueyuan_pic">
							<img id="preview1" src="${entryInfo.entryUserPhoto }" width="140"
								height="140" />
						</div>
						<div class="pic_btn">
							<p>学员照片</p>
							<a href="javascript:void(0)" class="btn btn-info btn-xs"
								style="position: relative;">浏览 <input type="file"
								id="user_pic" onchange="get_file_name()" name="user_pic"
								style="position: absolute; opacity: 0; top: 0px; left: 0px; width: 100%; height: 100%;" />
							</a>
						</div>

					</li>
					<%-- <li>
						<div class="xueyuan_pic">
							<img id="preview2" src="${entryInfo.document_photo }" width="100"
								height="100" />
						</div>
						<div class="pic_btn">
							<p>证件照片</p>
							<a href="javascript:void(0)" class="btn btn-info btn-xs"
								style="position: relative;">浏览 <input type="file"
								id="zhengjian_pic" onchange="get_file_name()"
								name="zhengjian_pic"
								style="position: absolute; opacity: 0; top: 0px; left: 0px; width: 100%; height: 100%;" />
							</a>
						</div>

					</li> --%>
					<li>
						<div class="xueyuan_pic">
							<img id="preview3" src="${entryInfo.userCardPositive }"
								width="140" height="140" />
						</div>
						<div class="pic_btn">
							<p>身份证正面照片</p>
							<a href="javascript:void(0)" class="btn btn-info btn-xs"
								style="position: relative;">浏览 <input type="file"
								id="zhengmian_pic" onchange="get_file_name()"
								name="zhengmian_pic"
								style="position: absolute; opacity: 0; top: 0px; left: 0px; width: 100%; height: 100%;" />
							</a>
						</div>

					</li>
					<li>
						<div class="xueyuan_pic">
							<img id="preview4" src="${entryInfo.userCardOpposite }"
								width="140" height="140" />
						</div>
						<div class="pic_btn">
							<p>身份证背面照片</p>
							<a href="javascript:void(0)" class="btn btn-info btn-xs"
								style="position: relative;">浏览 <input type="file"
								id="fanmian_pic" onchange="get_file_name()" name="fanmian_pic"
								style="position: absolute; opacity: 0; top: 0px; left: 0px; width: 100%; height: 100%;" />
							</a>
						</div>

					</li>
					<li>
						<div class="xueyuan_pic">
							<img id="preview5" src="${entryInfo.certificatePic }" width="140"
								height="140" />
						</div>
						<div class="pic_btn">
							<p>学历照片</p>
							<a href="javascript:void(0)" class="btn btn-info btn-xs"
								style="position: relative;">浏览 <input type="file"
								id="xueli_pic" onchange="get_file_name()" name="xueli_pic"
								style="position: absolute; opacity: 0; top: 0px; left: 0px; width: 100%; height: 100%;" />
							</a>
						</div>
					</li>
				</ul>
			</div>
		</div>
		<table border="1" class="tianjia_xueyuan">
			<tr>
				<td colspan="8"><strong>基本信息</strong></td>
			</tr>
			<tr>
				<td width="12%"><strong>报考专业</strong></td>
				<td colspan="7"><select id="zhuanye_id" name="zhuanye_id">
						<c:forEach items="${universities }" var="univer">
						<c:if test="${entryInfo.zhuanye_id==univer.university_id  }">
							<option selected="selected" value="${univer.university_id }">${univer.university_zhuanye }</option>
						</c:if>
						<c:if test="${entryInfo.zhuanye_id!=univer.university_id  }">
							<option value="${univer.university_id }">${univer.university_zhuanye }</option>
						</c:if>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td width="12%"><strong>学员姓名</strong></td>
				<td width="13%"><input type="text"
					onblur="check_entryUserName()" placeholder="请输入姓名"
					value="${entryInfo.entryUserName }" name="entryUserName"
					id="entryUserName" /></td>
				<td width="12%"><strong>曾用名</strong></td>
				<td width="13%"><input type="text" placeholder="请输入曾用名"
					id="before_name" value="${entryInfo.before_name }" name="before_name" /></td>
				<td width="12%"><strong>性别</strong></td>
				<td width="10%"><select id="entryUserSex" name="entryUserSex">
						<option value="${entryInfo.entryUserSex }">${entryInfo.entryUserSex }</option>
						<option value="男">男</option>
						<option value="女">女</option>
				</select></td>
				<td width="12%"><strong>民族</strong></td>
				<td width="16%"><select id="entryUserNation"
					name="entryUserNation">
						<c:forEach items="${nations }" var="na">
							<c:if test="${entryInfo.entryUserNation== na.nation}">
								<option selected="selected" value="${na.nation }">${na.nation }</option>
							</c:if>
							<c:if test="${entryInfo.entryUserNation!= na.nation}">
								<option value="${na.nation }">${na.nation }</option>
							</c:if>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td><strong>证件类型</strong></td>
				<td><select id="documentType" name="documentType">
						<option value="${entryInfo.documentType}">${entryInfo.documentType}</option>
						<option value="身份证">身份证</option>
				</select></td>
				<td><strong>证件号码</strong></td>
				<td colspan="3"><input value="${entryInfo.documentNumber}"  onblur="check_documentNumber()"
					type="text" placeholder="请输入证件号码" id="documentNumber"
					name="documentNumber" /></td>
				<td width="10%"><strong>出生日期</strong></td>
				<td><input type="text" class="layui-input" readonly="readonly"
					name="entryUserBirthday" value="${entryInfo.entryUserBirthday}"
					id="entryUserBirthday"  /></td>
			</tr>
			<tr>
				<td><strong>手机号</strong></td>
				<td><input type="text" value="${entryInfo.entryUserPhone}"
					onblur="check_entryUserPhone()" placeholder="请输入手机号"
					id="entryUserPhone" name="entryUserPhone" /></td>
				<td><strong>电子邮箱</strong></td>
				<td colspan="3"><input type="text"
					onblur="check_entryUserMail()" placeholder="请输入电子邮箱"
					value="${entryInfo.entryUserMail}" id="entryUserMail"
					name="entryUserMail" /></td>
				<td width="12%"><strong>政治面貌</strong></td>
				<td width="13%"><select id="entryPolitical"
					name="entryPolitical">
						<option value="${entryInfo.entryPolitical}">${entryInfo.entryPolitical}</option>
						<option value="党员">党员</option>
						<option value="团员">团员</option>
						<option value="群众">群众</option>
				</select></td>

			</tr>
			<tr>
				<td><strong>邮编</strong></td>
				<td colspan="2"><input type="text" placeholder="请输入邮编"
					id="zipCode" value="${entryInfo.zipCode}" name="zipCode" /></td>
				<td><strong>联系QQ</strong></td>
				<td colspan="2"><input type="text" placeholder="请输入联系QQ"
					id="user_qq" value="${entryInfo.user_qq}" name="user_qq" /></td>
				<td><strong>健康状况</strong></td>
				<td colspan="2"><select id="health" name="health">
						<option value="健康">健康</option>
						<option value="疾病">疾病</option>
				</select></td>
			</tr>
			<tr>
				<td width="12%"><strong>户口所在地</strong></td>
				<td colspan="3" width="38%"><select style="width: 95px;"
					onchange="get_city()" name="province" id="province">
						<option value="0">请选择省份</option>
						<c:forEach items="${cities }" var="pro">
						<c:if test="${entryInfo.entryUserProvince==pro.name }">
							<option selected="selected" value="${pro.id }">${pro.name }</option>
						</c:if>
						<c:if test="${entryInfo.entryUserProvince!=pro.name }">
							<option  value="${pro.id }">${pro.name }</option>
						</c:if>
						</c:forEach>
				</select> <select style="width: 95px;" onchange="get_area()" id="user_city"
					name="user_city">
						<option  value="0">${entryInfo.entryUserCity }</option>
				</select> <select style="width: 95px;" id="entryUserArea"
					name="entryUserArea">
						<option  value="0">${entryInfo.entryUserArea }</option>
				</select></td>
			</tr>
			<tr>
				<td colspan="8" width="100%"><strong>资料领取</strong></td>
			</tr>
			<tr>
				<td width="12%"><strong>证书</strong></td>
				<td width="13%"><select id="tingkezheng" name="tingkezheng">
						<option value="${entryInfo.tingkezheng}">${entryInfo.tingkezheng}</option>
						<option value="已领取">已领取</option>
						<option value="未领取">未领取</option>
				</select></td>
				<td width="12%"><strong>教材</strong></td>
				<td width="13%"><select id="jiaocai" name="jiaocai">
						<option value="${entryInfo.jiaocai}">${entryInfo.jiaocai}</option>
						<option value="未发">未发</option>
						<option value="已发">已发</option>
				</select></td>
				<td width="12%"><strong>教辅</strong></td>
				<td width="13%"><select id="jiaofu" name="jiaofu">
						<option value="${entryInfo.jiaofu}">${entryInfo.jiaofu}</option>
						<option value="未发">未发</option>
						<option value="已发">已发</option>
				</select></td>
				<td width="12%"><select id="jiaofulingqu" name="jiaofulingqu">
						<option value="${entryInfo.jiaofulingqu}">${entryInfo.jiaofulingqu}</option>
						<option value="未领">未领取</option>
						<option value="已领">已领取</option>
				</select></td>
				<td width="16%"></td>
			</tr>
			<tr>
				<td width="12%"><strong>成绩</strong></td>
				<td width="13%"><select id="wangluoxuexika" name="wangluoxuexika">
				<option value="${entryInfo.wangluoxuexika}">${entryInfo.wangluoxuexika}</option>
				<option value="合格">合格</option>
				<option value="不合格">不合格</option>
				</select></td>
				<td width="12%"><strong>是否录取</strong></td>
				<td width="13%"><select id="zhuanshufuwuka" name="zhuanshufuwuka">
				<option value="${entryInfo.zhuanshufuwuka}">${entryInfo.zhuanshufuwuka}</option>
				<option value="录取">录取</option>
				<option value="未录取">未录取</option>
		</select></td>
					<td width="12%"><strong>报考班次</strong></td>
				<td width="13%"><input type="text" id="baokao_banci"
					name="baokao_banci" value="${entryInfo.baokao_banci }" /></td>
				<td colspan="2"></td>
			</tr>
			<tr>
				<td colspan="8" width="100%"><strong>个人资料</strong></td>
			</tr>
			<tr>
				<td colspan="3"><strong>工作时间</strong></td>
				<td colspan="2"><strong>所在单位</strong></td>
				<td colspan="2"><strong>证明人</strong></td>
				<td><strong>操作</strong></td>
			</tr>
			<c:if test="${not empty entryViates }">
			<c:forEach items="${entryViates }" var="viat">
			<tr>
				<td colspan="3"><input type="text" id="time_qujian${viat.vitae_id }" value="${viat.time_qujian }" /></td>
				<td colspan="2"><input type="text" id="danwei_xuexiao${viat.vitae_id }" value="${viat.danwei_xuexiao }" /></td>
				<td colspan="2"><input type="text" id="zhengmingren${viat.vitae_id }" value="${viat.zhengmingren }" /></td>
				<td><input type="button"  value="修改" class="update1" onclick="update_entry_viat(${viat.vitae_id })" /></td>
			</tr>
			</c:forEach>
			<script>
			//修改个人简历
			function update_entry_viat(vitae_id){
				var time_qujian=$("#time_qujian"+vitae_id).val();
				var danwei_xuexiao=$("#danwei_xuexiao"+vitae_id).val();
				var zhengmingren=$("#zhengmingren"+vitae_id).val();
				$.post("/edu_entry/update_entry_viat.jr",{
					'vitae_id':vitae_id,
					'time_qujian':time_qujian,
					'danwei_xuexiao':danwei_xuexiao,
					'zhengmingren':zhengmingren
				},function(data){
					if(data==1){
						layer.msg("修改成功！");
					}else{
						layer.msg("修改失败！");
					}
				})
			}
			</script>
			</c:if>
			<tr>
				<td colspan="2"><strong>姓名</strong></td>
				<td colspan="2"><strong>关系</strong></td>
				<td><strong>年龄</strong></td>
				<td colspan="2"><strong>所在单位</strong></td>
				<td><strong>操作</strong></td>
			</tr>
			<c:if test="${not empty entryFamilies }">
			<c:forEach items="${entryFamilies }" var="family">
			<tr>
				<td colspan="2"><input type="text" id="family_name${family.family_id }" value="${family.family_name }" /></td>
				<td colspan="2"><input type="text" id="guanxi${family.family_id }" value="${family.guanxi }" /></td>
				<td><input type="text" id="nianling${family.family_id }" value="${family.nianling }" /></td>
				<td colspan="2"><input type="text" id="gongzuodanwei${family.family_id }" value="${family.gongzuodanwei }" /></td>
				<td><input type="button"  value="修改" class="update2" onclick="update_entry_family(${family.family_id })" /></td>
			</tr>
			</c:forEach>
				<script>
			//修改家庭关系
			function update_entry_family(family_id){
				var family_name=$("#family_name"+family_id).val();
				var guanxi=$("#guanxi"+family_id).val();
				var nianling=$("#nianling"+family_id).val();
				var gongzuodanwei=$("#gongzuodanwei"+family_id).val();
				$.post("/edu_entry/update_entry_family.jr",{
					'family_id':family_id,
					'family_name':family_name,
					'guanxi':guanxi,
					'nianling':nianling,
					'gongzuodanwei':gongzuodanwei
				},function(data){
					if(data==1){
						layer.msg("修改成功！");
					}else{
						layer.msg("修改失败！");
					}
				})
			}
			</script>
			</c:if>
		</table>
		<div class="jianli_chengyuan">
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
							<input type="text" value="暂无" name="guanxi_str" />
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
</div>
		<div class="caozuo">
			<input type="button" class="btn btn-success btn-xm"
				onclick="update_entry_xuexiao_info(${entryInfo.entryInfoId})" value="提交">
			<input type="button" class="btn btn-danger btn-xm"
				onclick="close_layer()" value="取消">
		</div>
	</form>
</div>
<script type="text/javascript">
	function tjjl(obj) {
		var val = $(obj).attr("class").replace(/\s+/g, "");
		if (val == "fafa-plus") {
			$(obj).removeClass("fa-plus");
			$(obj).addClass("fa-minus");
			$(obj)
					.parent()
					.parent()
					.after(
							'<div class="neirongs"><div class="jianli_item"><label>工作时间：</label><div class="jianli_items"><input value="2017年01月" id="star_time_str" name="star_time_str" class="str_date" placeholder="请输入X年X月" type="text" /><span class="zhi">至</span><input id="end_time_str" name="end_time_str"  class="end_date" value="至今" type="text" /></div></div><div class="clear"></div><div class="jianli_item"><label>所在单位：</label><div class="jianli_items"><input type="text" value="暂无" name="suozaidanwei_str"  /></div></div><div class="clear"></div><div class="jianli_item"><label>证明人：</label><div class="jianli_items"><input name="zhengmingren_str" value="暂无" type="text" /></div></div><div class="anniu"><i class="fa fa-plus" onclick="tjjl(this)"></i></div><div class="clear"></div></div>');
			$(obj).parent().css("background-color", "#999");
		} else {
			$(obj).parent().parent().remove();
		}
	}

	function tjjt(obj) {
		var val = $(obj).attr("class").replace(/\s+/g, "");
		if (val == "fafa-plus") {
			$(obj).removeClass("fa-plus");
			$(obj).addClass("fa-minus");
			$(obj)
					.parent()
					.parent()
					.after(
							'<div class="neirongs"><div class="jiating_item"><label>姓名：</label><div class="jianli_items"><input type="text" value="暂无" name="xingming_str" /></div></div><div class="clear"></div><div class="jiating_item"><label>家庭关系：</label><div class="jiating_items"><input name="guanxi_str" value="暂无" type="text" /></div></div><div class="clear"></div><div class="jiating_item"><label>年龄：</label><div class="jiating_items"><input name="nianling_str" type="text" value="暂无"/></div></div><div class="clear"></div><div class="jiating_item"><label>所在单位：</label><div class="jiating_items"><input type="text" name="family_danwei_str" value="暂无" /></div></div><div class="anniu"><i class="fa fa-plus" onclick="tjjt(this)"></i></div><div class="clear"></div></div>');
			$(obj).parent().css("background", "#999");
		} else {
			$(obj).parent().parent().remove();
		}
	}
</script>
<style type="text/css">
.clear {
	clear: both;
}

.jianli_chengyuan {
	margin-top: 30px;
}

.reg_item label {
	width: 78px;
	text-align: right;
	float: left;
	height: 30px;
	line-height: 30px;
	font-weight: bold;
}

.reg_item input {
	width: 243px;
	height: 28px;
	border: #b2b2b2 solid 1px;
	float: left;
	margin-left: 15px;
	padding-left: 5px;
}

.reg_item .jianli, .reg_item .jianli_item .jianli_items, .reg_item .jiating,
	.reg_item .jiating_item .jiating_items {
	float: left;
}

.reg_item .jianli .neirong, .reg_item .jiating .neirong {
	float: left;
	margin-left: 15px;
}

.reg_item .jianli .neirongs, .reg_item .jiating .neirongs {
	padding: 5px;
	width: 600px;
	border: #28A4F4 dashed 2px;
	position: relative;
	margin-bottom: 20px;
}

.reg_item .jianli_item, .reg_item .jiating_item {
	padding-bottom: 15px;
	height: 32px;
}

.reg_item .jianli_item .str_date, .reg_item .jianli_item .end_date {
	float: left;
	width: 96px;
}

.reg_item .jianli_item span.zhi {
	float: left;
	display: block;
	margin: 0px 0px 0px 15px;
	line-height: 30px;
}

.reg_item .jianli .anniu, .reg_item .jiating .anniu {
	float: left;
	width: 25px;
	height: 25px;
	border-radius: 50%;
	background-color: #28A4F4;
	color: #fff;
	text-align: center;
	position: absolute;
	right: -50px;
	top: 0px;
	cursor: pointer;
}

.reg_item .jianli .anniu i, .reg_item .jiating .anniu i {
	margin-top: 2px;
	font-size: 22px;
}
</style>