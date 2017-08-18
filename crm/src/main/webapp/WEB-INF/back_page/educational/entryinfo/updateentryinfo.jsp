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
			<label class="col-xs-2 control-label">职业级别：</label>
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
				</select> <select id="dictionary_id" onchange="get_tiaojian_radio(this)"
					name="dictionary_id">
					<c:forEach items="${quchongconditions }" var="con">
						<c:if
							test="${entryInfo.entrycondition_id== con.entrycondition_id}">
							<option selected="selected" value="${con.entrycondition_id }">${con.dictionary.dictionary_name }</option>
						</c:if>
						<c:if
							test="${entryInfo.entrycondition_id!= con.entrycondition_id}">
							<option value="${con.entrycondition_id }">${con.dictionary.dictionary_name }</option>
						</c:if>
					</c:forEach>
				</select>
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
			<link rel="stylesheet" href="/css/school/back/common/flat-ui.min.css"/>
<script src="/js/school/back/common/flat-ui.min.js"></script>
<script src="/js/school/back/common/application.js"></script>
<div class="col-xs-10">
<c:forEach items="${subconditions }" var="condition">
	<c:if test="${entryInfo.entrycondition_id== condition.entrycondition_id}">
	<label class="radio" for="radio4b${condition.entrycondition_id }"> <input type="radio"
		name="optionsRadios" data-toggle="radio" value="${condition.entrycondition_id }" id="radio4b${condition.entrycondition_id }"
		required checked="checked">${condition.entrycondition_content }
	</label>
	</c:if>
	<c:if test="${entryInfo.entrycondition_id!= condition.entrycondition_id}">
	<label class="radio" for="radio4b${condition.entrycondition_id }"> <input type="radio" 
		name="optionsRadios" data-toggle="radio"  value="${condition.entrycondition_id }" id="radio4b${condition.entrycondition_id }"
		required>${condition.entrycondition_content }
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
				<td width="12%"><strong>学员姓名</strong></td>
				<td width="13%"><input type="text"
					onblur="check_entryUserName()" placeholder="请输入姓名"
					value="${entryInfo.entryUserName }" name="entryUserName"
					id="entryUserName" /></td>
				<td width="12%"><strong>性别</strong></td>
				<td width="10%"><select id="entryUserSex" name="entryUserSex">
						<option value="${entryInfo.entryUserSex }">${entryInfo.entryUserSex }</option>
						<option value="男">男</option>
						<option value="女">女</option>
				</select></td>
				<td width="12%"><strong>民族</strong></td>
				<td width="13%"><select id="entryUserNation"
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
				<td width="12%"><strong>文化程度</strong></td>
				<td width="16%"><select id="entryUserEducation"
					name="entryUserEducation">
						<option value="${entryInfo.entryUserEducation}">${entryInfo.entryUserEducation}</option>
						<option value="研究生">研究生</option>
						<option value="本科">本科</option>
						<option value="专科">专科</option>
						<option value="专科以下">专科以下</option>
				</select></td>
			</tr>
			<tr>
				<td width="10%"><strong>出生日期</strong></td>
				<td><input type="text" class="layui-input"
					name="entryUserBirthday" value="${entryInfo.entryUserBirthday}"
					id="entryUserBirthday" placeholder="点击获取日期" /></td>
				<td><strong>证件类型</strong></td>
				<td><select id="documentType" name="documentType">
						<option value="${entryInfo.documentType}">${entryInfo.documentType}</option>
						<option value="身份证">身份证</option>
						<option value="护照">护照</option>
				</select></td>
				<td><strong>证件号码</strong></td>
				<td colspan="3"><input value="${entryInfo.documentNumber}"
					type="text" placeholder="请输入证件号码" id="documentNumber"
					name="documentNumber" /></td>
			</tr>
			<tr>
				<td><strong>手机号</strong></td>
				<td><input type="text" value="${entryInfo.entryUserPhone}"
					onblur="check_entryUserPhone()" placeholder="请输入手机号"
					id="entryUserPhone" name="entryUserPhone" /></td>
				<td><strong>电子邮箱</strong></td>
				<td colspan="3"><input type="text" onblur="check_entryUserMail()"
					placeholder="请输入电子邮箱" value="${entryInfo.entryUserMail}"
					id="entryUserMail" name="entryUserMail" /></td>
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
			    <td><strong>紧急联系人</strong></td>
				<td colspan="2"><input type="text" 
					placeholder="请输入紧急联系人" id="jinji_name" value="${entryInfo.jinji_name}" name="jinji_name" /></td>
			    <td><strong>紧急联系人电话</strong></td>
				<td colspan="4"><input type="text" placeholder="请输入紧急联系人电话"
					id="jinji_phone" value="${entryInfo.jinji_phone}" name="jinji_phone" /></td>
			</tr>
			<tr>
			    <td><strong>邮编</strong></td>
				<td colspan="2"><input type="text" 
					placeholder="请输入邮编" id="zipCode" value="${entryInfo.zipCode}" name="zipCode" /></td>
				<td><strong>现住址</strong></td>
				<td colspan="4"><input type="text" placeholder="请输入现居住地"
					id="entryUserAddress" value="${entryInfo.entryUserAddress}"
					name="entryUserAddress" /></td>
			</tr>
			<tr>
				<td width="12%"><strong>所在单位</strong></td>
				<td colspan="3" width="38%"><input type="text"
					value="${entryInfo.entryUserUnit}" placeholder="请输入所在单位"
					id="entryUserUnit" name="entryUserUnit" oninput="get_company()" />
					<div id="tip">
						<!-- 提示框 -->
					</div></td>
				<td width="12%"><strong>所在职位</strong></td>
				<td colspan="3" width="13%"><input type="text" placeholder="请输入职位"
					value="${entryInfo.entryUserPosition}" id="entryUserPosition"
					name="entryUserPosition" /></td>
			</tr>
			<tr>
				<td width="12%"><strong>参加工作时间</strong></td>
				<td width="13%"><input type="text" class="layui-input"
					value="${entryInfo.inWorkTime}" name="inWorkTime" id="inWorkTime"
					placeholder="点击获取日期" /></td>
				<td width="12%"><strong>连续工龄</strong></td>
				<td width="13%"><input type="text" onblur="check_wordyears()"
					id="workYears" name="workYears" value="${entryInfo.workYears}" /></td>
				<td width="12%"><strong>户口所在地</strong></td>
				<td colspan="3" width="38%"><select style="width: 92px;"
					onchange="get_city()" name="province" id="province">
						<c:forEach items="${cities }" var="pro">
						<c:if test="${entryInfo.entryUserProvince== pro.name}">
							<option selected="selected" value="0">${pro.name }</option>
						</c:if>
						<c:if test="${entryInfo.entryUserProvince!= pro.name}">
							<option  value="${pro.id }">${pro.name }</option>
						</c:if>
						</c:forEach>
				</select> <select style="width: 92px;" onchange="get_area()" id="user_city"
					name="user_city">
						<option  value="0">${entryInfo.entryUserCity }</option>
				</select> <select style="width: 92px;" id="entryUserArea"
					name="entryUserArea">
						<option  value="${entryInfo.entryUserArea }">${entryInfo.entryUserArea }</option>
				</select></td>
			</tr>
			<tr>
				<td colspan="8" width="100%"><strong>课程信息</strong></td>
			</tr>
			<tr>
				<td width="12%"><strong>类别</strong></td>
				<td width="10%"><select id="entryCategory" name="entryCategory">
						<option value="${entryInfo.entryCategory}">${entryInfo.entryCategory}</option>
						<option value="非定向">非定向</option>
						<option value="定向">定向</option>
						<option value="委培">委培</option>
						<option value="自筹">自筹</option>
				</select></td>
				<td width="12%"><strong>属性</strong></td>
				<td width="10%"><select>
						<option>统考</option>
						<option>自考</option>
				</select></td>
				<td width="12%"><strong>班型</strong></td>
				<td width="38%" colspan="3"><select id="mianshoubanxing"
					name="mianshoubanxing">
						<option value="${entryInfo.mianshoubanxing}">${entryInfo.mianshoubanxing}</option>
						<option value="精讲班">精讲班</option>
						<option value="普通班">普通班</option>
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
		</table>
		<div class="caozuo">
		  <input type="button" class="btn btn-success btn-xm" onclick="update_entry_info(${entryInfo.entryInfoId})" value="提交"> 
		  <input type="button" class="btn btn-danger btn-xm" onclick="close_layer()" value="取消">
		</div>
	</form>
</div>