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
							if(xueli_upload_pic){
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
table tr td textarea {
  width:100%;
  padding:5px;
  resize:none;
  border:none;
  line-height:20px;
  min-height:150px;
  font-size: 14px;
  }
table tr td textarea.chengguo {
  min-height:500px;
  }
table tr td textarea.zhaiyao {
  min-height:300px;
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
								id="user_pic" name="user_pic"
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
								id="zhengjian_pic" 
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
								id="zhengmian_pic" name="zhengmian_pic"
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
								id="fanmian_pic" name="fanmian_pic"
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
								id="xueli_pic" name="xueli_pic"
								style="position: absolute; opacity: 0; top: 0px; left: 0px; width: 100%; height: 100%;" />
							</a>
						</div>
					</li>
				</ul>
			</div>
		</div>
		<div class="form-group" style="display: none;" id="zhicheng_ziliao">
			<script>
				//上传学历照片
				function xueli_upload_pic() {
					var name = $("#xueli_pic").val();
					if (name != "" && name != null) {
					var byteSize = $("#xueli_pic")[0].files[0].size;
						if (byteSize < 2 * 1024 * 1024) {
							return true;
						} else {
							layer.msg('文件大小不能超过2MB');
							return false;
						}
					} else {
						return true;
					}
				}

				//点击加入文件时
				function xuexin_beian_str_add() {
					var fileName = "";
					var name = $("#xuexin_beian_str").val();
					if (name != "" && name != null) {
						fileName = name.split("\\").pop();
						var byteSize = $("#xuexin_beian_str")[0].files[0].size;
						if (byteSize < 2 * 1024 * 1024) {
							$("#xuexin_info").html(fileName);
							return true;
						} else {
							layer.msg('文件大小不能超过2MB');
							return false;
						}
					} else {
						return true;
					}
				}

				function xuexin_tip_huaguo() {
					layer.tips('上传电子版1份，最大2M', "#xuexin_tip", {
						tips : [ 3, '#8BC34A' ],
						time : 3000
					});
				}

				function ercun_tip_huaguo() {
					layer.tips('小二寸白底，蓝底高清电子版照片各1份，打包上传，最大2M', "#ercun_tip", {
						tips : [ 3, '#8BC34A' ],
						time : 3000
					});
				}
				function xueli_tip_zhaopian() {
					layer.tips('学历照片请上传原件扫面件！大小不超过2M', "#xueli_tip", {
						tips : [ 3, '#8BC34A' ],
						time : 3000
					});
				}
			</script>
			<label class="col-xs-2 control-label">上传资料：</label>
			<div class="col-xs-10">
				<!-- 职称评审资料上传 -->
				<ul class="zhicheng_upload">
					<li style="margin-right: 170px;">
						<p class="upload_filename" id="xuexin_info"></p> <img
						src="/images/crm/business/tips.png"
						onmouseover="xuexin_tip_huaguo()" id="xuexin_tip"> <span
						class="zhiceng_wz">学信网电子学籍备案表</span> <a href="javascript:void(0);"
						class="liulanbtn"> 浏览 <input type="file"
							onchange="xuexin_beian_str_add()" id="xuexin_beian_str"
							name="xuexin_beian_str" class="upload_file" />
					</a>
					</li>
					<li>
						<p class="upload_filename" id="ercunzhao_info"></p> <img
						src="/images/crm/business/tips.png" id="ercun_tip"
						onmouseover="ercun_tip_huaguo()"> <span class="zhiceng_wz">两寸打包照片</span>
						<a href="javascript:void(0);" class="liulanbtn"> 浏览 <input
							type="file" onchange="ercunzhao_str_add()" id="ercunzhao_str"
							name="ercunzhao_str" class="upload_file" />
					</a> <script>
						//点击加入文件时
						function ercunzhao_str_add() {
							var fileName = "";
							var filezhouzhui = "";
							var name = $("#ercunzhao_str").val();
							if (name != "" && name != null) {
								fileName = name.split("\\").pop();
								//截取文件后缀名 
								filezhouzhui = name.substring(
										name.lastIndexOf("."), name.length)
										.toUpperCase();
								var byteSize = $("#ercunzhao_str")[0].files[0].size;
								if (byteSize < 2 * 1024 * 1024) {
									if (filezhouzhui != ".ZIP"
											&& filezhouzhui != ".RAR") {
										layer.msg('请上传zip或者rar类型的压缩文件！');
										return false;
									} else {
										$("#ercunzhao_info").html(fileName);
										return true;
									}
								} else {
									layer.msg('文件大小不能超过2MB');
									return false;
								}
							} else {
								return true;
							}
						}

						function zhengshu_tip_huaguo() {
							layer
									.tips(
											'包括荣誉证书、各种奖项、职称证(员级、初级、中级)的扫描件(电子版1份)打包上传，最大2M',
											"#zhengshu_tip", {
												tips : [ 3, '#8BC34A' ],
												time : 3000
											});
						}
					</script>
					</li>
					<li style="margin-left: 26px;">
						<p class="upload_filename" id="zhengshu_dabao_info"></p> <img
						src="/images/crm/business/tips.png"
						onmouseover="zhengshu_tip_huaguo()" id="zhengshu_tip"><span
						class="zhiceng_wz">证书资料打包上传</span> <a href="javascript:void(0);"
						class="liulanbtn"> 浏览 <input type="file"
							onchange="zhengshu_dabao_str_add()" id="zhengshu_dabao_str"
							name="zhengshu_dabao_str" class="upload_file" />
					</a> <script>
						//点击加入文件时
						function zhengshu_dabao_str_add() {
							var fileName = "";
							var filezhouzhui = "";
							var name = $("#zhengshu_dabao_str").val();
							if (name != "" && name != null) {
								fileName = name.split("\\").pop();
								//截取文件后缀名 
								filezhouzhui = name.substring(
										name.lastIndexOf("."), name.length)
										.toUpperCase();
								var byteSize = $("#zhengshu_dabao_str")[0].files[0].size;
								if (byteSize < 2 * 1024 * 1024) {
									if (filezhouzhui != ".ZIP"
											&& filezhouzhui != ".RAR") {
										layer.msg('请上传zip或者rar类型的压缩文件！');
										return false;
									} else {
										$("#zhengshu_dabao_info")
												.html(fileName);
										alert(1)
										return true;
									}
								} else {
									layer.msg('文件大小不能超过2MB');
									return false;
								}
							} else {
								return true;
							}

						}
						function lunwen_tip_huaguo() {
							layer
									.tips(
											'论文1篇，工作总结电子版，工作业绩(招标合同、中标合同、施工合同、验收报告)电子版各一份，打包上传，最大2M',
											"#lunwen_tip", {
												tips : [ 3, '#8BC34A' ],
												time : 3000
											});
						}
					</script>
					</li>
					<li style="margin-left: 156px;">
						<p class="upload_filename" id="zhichenglunwen_info"></p> <img
						src="/images/crm/business/tips.png"
						onmouseover="lunwen_tip_huaguo()" id="lunwen_tip"><span
						class="zhiceng_wz">文件资料打包上传</span> <a href="javascript:void(0);"
						class="liulanbtn"> 浏览 <input type="file"
							onchange="zhichenglunwen_str_add()" id="zhichenglunwen_str"
							name="zhichenglunwen_str" class="upload_file" />
					</a> <script>
						//点击加入文件时
						function zhichenglunwen_str_add() {
							var fileName = "";
							var filezhouzhui = "";
							var name = $("#zhichenglunwen_str").val();
							if (name != "" && name != null) {
								fileName = name.split("\\").pop();
								//截取文件后缀名 
								filezhouzhui = name.substring(
										name.lastIndexOf("."), name.length)
										.toUpperCase();
								var byteSize = $("#zhichenglunwen_str")[0].files[0].size;
								if (byteSize < 2 * 1024 * 1024) {
									if (filezhouzhui != ".ZIP"
											&& filezhouzhui != ".RAR") {
										layer.msg('请上传zip或者rar类型的压缩文件！');
										return false;
									} else {
										$("#zhichenglunwen_info")
												.html(fileName);
										return true;
									}
								} else {
									layer.msg('文件大小不能超过2MB');
									return false;
								}
							} else {
								return true;
							}
						}
					</script>
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
				<td><strong>紧急联系人</strong></td>
				<td colspan="2"><input type="text" placeholder="请输入紧急联系人"
					id="jinji_name" value="${entryInfo.jinji_name}" name="jinji_name" /></td>
				<td><strong>紧急联系人电话</strong></td>
				<td colspan="4"><input type="text" placeholder="请输入紧急联系人电话"
					id="jinji_phone" value="${entryInfo.jinji_phone}"
					name="jinji_phone" /></td>
			</tr>
			<tr>
				<td><strong>邮编</strong></td>
				<td colspan="2"><input type="text" placeholder="请输入邮编"
					id="zipCode" value="${entryInfo.zipCode}" name="zipCode" /></td>
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
				<td colspan="3" width="13%"><input type="text"
					placeholder="请输入职位" value="${entryInfo.entryUserPosition}"
					id="entryUserPosition" name="entryUserPosition" /></td>
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
								<option value="${pro.id }">${pro.name }</option>
							</c:if>
						</c:forEach>
				</select> <select style="width: 92px;" onchange="get_area()" id="user_city"
					name="user_city">
						<option value="0">${entryInfo.entryUserCity }</option>
				</select> <select style="width: 92px;" id="entryUserArea"
					name="entryUserArea">
						<option value="${entryInfo.entryUserArea }">${entryInfo.entryUserArea }</option>
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
				<td width="13%"><select id="wangluoxuexika"
					name="wangluoxuexika">
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
				<td colspan="8" width="100%"><strong>职称评审</strong></td>
			</tr>
			<tr>
				<td colspan="2"><strong>现专业技术职务任职资格</strong></td>
				<td colspan="4"><input type="text" id="now_zhuanye_zhicheng"
					name="now_zhuanye_zhicheng"
					value="${entryInfo.now_zhuanye_zhicheng}"
					placeholder="XX系列XX专业资格名称" /></td>
				<td><strong>取得时间</strong></td>
				<td><input type="text" id="qude_time" name="qude_time" value="${entryInfo.qude_time}"
					placeholder="请选择日期" /></td>
				<script>
			$(function() {
					document.getElementById('qude_time').onclick = function() {
						start.elem = this;
						laydate(start);
					}
				});
			})
		</script>
			</tr>
			<tr>
				<td colspan="8" width="100%"><strong>申报人符合申报评审条件情况</strong></td>
			</tr>
			<tr>
				<td rowspan="4"><strong>第一学历</strong></td>
				<td><strong>毕业院校</strong></td>
				<td colspan="6"><input type="text" placeholder="请输入毕业院校"
					name="biye_sc" value="${entryInfo.biye_sc}" id="biye_sc" /></td>
			</tr>
			<tr>
				<td colspan="2"><strong>毕业证书编号</strong></td>
				<td colspan="2"><input type="text" id="zhengshu_num"
					name="zhengshu_num" value="${entryInfo.zhengshu_num}"
					placeholder="请输入毕业证书编号" /></td>
				<td><strong>学历程度</strong></td>
				<td colspan="2"><select id="diyi_xueli" name="diyi_xueli">
						<option value="${entryInfo.diyi_xueli}">${entryInfo.diyi_xueli}</option>
						<option value="研究生">研究生</option>
						<option value="本科">本科</option>
						<option value="大专">大专</option>
						<option value="高中">高中</option>
				</select></td>
			</tr>
			<tr>
				<td><strong>所学专业</strong></td>
				<td colspan="3"><input type="text" placeholder="请输入所学专业"
					id="suoxue_zhuanye" name="suoxue_zhuanye"
					value="${entryInfo.suoxue_zhuanye}" /></td>
				<td><strong>学位</strong></td>
				<td colspan="2"><input type="text" placeholder="请输入学士学位"
					id="xuewei" name="xuewei" value="${entryInfo.xuewei}" /></td>
			</tr>
			<tr>
				<td><strong>学制</strong></td>
				<td><select id="xuezhi" name="xuezhi">
						<option value="${entryInfo.xuezhi}">${entryInfo.xuezhi}</option>
						<option value="5">5年</option>
						<option value="4">4年</option>
						<option value="3">3年</option>
						<option value="2.5">2.5年</option>
				</select></td>
				<td><strong>毕业时间</strong></td>
				<td colspan="2"><input type="text" placeholder="请选择日期"
					id="biye_time" name="biye_time" value="${entryInfo.biye_time}" /></td>
				<script>
			$(function() {
					document.getElementById('biye_time').onclick = function() {
						start.elem = this;
						laydate(start);
					}
				});
			})
		</script>
				<td><strong>学习形式</strong></td>
				<td><select id="studry_type" name="studry_type">
						<option value="${entryInfo.studry_type}">${entryInfo.studry_type}</option>
						<option value="全日制">全日制</option>
						<option value="函授">函授</option>
						<option value="业余">业余</option>
				</select></td>
			</tr>
			<tr>
				<td rowspan="4"><strong>最高学历</strong></td>
				<td><strong>毕业院校</strong></td>
				<td colspan="6"><input type="text" placeholder="请输入毕业院校"
					id="zuigao_xuexiao" name="zuigao_xuexiao"
					value="${entryInfo.zuigao_xuexiao}" /></td>
			</tr>
			<tr>
				<td colspan="2"><strong>毕业证书编号</strong></td>
				<td colspan="2"><input type="text" placeholder="请输入毕业证书编号"
					id="zuigao_bianhao" name="zuigao_bianhao"
					value="${entryInfo.zuigao_bianhao}" /></td>
				<td><strong>学历程度</strong></td>
				<td colspan="2"><select id="zuigao_xueli" name="zuigao_xueli">
						<option value="${entryInfo.zuigao_xueli}">${entryInfo.zuigao_xueli}</option>
						<option value="研究生">研究生</option>
						<option value="本科">本科</option>
						<option value="大专">大专</option>
						<option value="高中">高中</option>
				</select></td>
			</tr>
			<tr>
				<td><strong>所学专业</strong></td>
				<td colspan="3"><input type="text" placeholder="请输入所学专业"
					id="zuigao_zhuanye" name="zuigao_zhuanye"
					value="${entryInfo.zuigao_zhuanye}" /></td>
				<td><strong>学位</strong></td>
				<td colspan="2"><input type="text" placeholder="请输入学士学位"
					id="zuigao_xuewei" name="zuigao_xuewei"
					value="${entryInfo.zuigao_xuewei}" /></td>
			</tr>
			<tr>
				<td><strong>学制</strong></td>
				<td><select id="zuigaoxuezhi" name="zuigaoxuezhi">
						<option value="${entryInfo.zuigaoxuezhi}">${entryInfo.zuigaoxuezhi}</option>
						<option value="5">5年</option>
						<option value="4">4年</option>
						<option value="3">3年</option>
						<option value="2.5">2.5年</option>
				</select></td>
				<td><strong>毕业时间</strong></td>
				<td colspan="2"><input type="text" placeholder="请选择日期"
					id="zuigao_biye_time" name="zuigao_biye_time"
					value="${entryInfo.zuigao_biye_time}" /></td>
				<script>
			$(function() {
					document.getElementById('zuigao_biye_time').onclick = function() {
						start.elem = this;
						laydate(start);
					}
				});
			})
		</script>
				<td><strong>学习形式</strong></td>
				<td><select id="zuigao_xuexi_type" name="zuigao_xuexi_type">
						<option value="${entryInfo.zuigao_xuexi_type}">${entryInfo.zuigao_xuexi_type}</option>
						<option value="全日制">全日制</option>
						<option value="函授">函授</option>
						<option value="业余">业余</option>
				</select></td>
			</tr>
			<tr>
				<td rowspan="3"><strong>职称外语计算机应用能力考试</strong></td>
				<td><strong>职称外语</strong></td>
				<td colspan="6"><input type="text" id="zhicheng_yingyu"
					name="zhicheng_yingyu" value="${entryInfo.zhicheng_yingyu}"
					placeholder="请输入XX年XX月XX日XX级XX分" /></td>
			</tr>
			<tr>
				<td><strong>计算机应用能力</strong></td>
				<td colspan="6"><input type="text" id="jisuanjinengli"
					name="jisuanjinengli" value="${entryInfo.jisuanjinengli}"
					placeholder="请输入XX年XX月XX日XX级XX分" /></td>
			</tr>
			<tr>
				<td><strong>参考、免考理由</strong></td>
				<td colspan="6"><input type="text" id="can_mian_liyou"
					name="can_mian_liyou" value="${entryInfo.can_mian_liyou}"
					placeholder="请输入参考、免考理由" /></td>
			</tr>
			<tr>
				<td colspan="8" width="100%"><strong>业绩成果</strong></td>
			</tr>
			<tr>
				<td><strong>荣誉称号</strong></td>
				<td colspan="7"><textarea class="rongyu" id="rongyuchenghao"
						name="rongyuchenghao"
						placeholder="具体说明荣誉称号的“称号名称、本人排序、取得时间、发证部门等，卷宗页码”。">${entryInfo.rongyuchenghao}</textarea>
				</td>
			</tr>
			<tr>
				<td><strong>科研成果</strong></td>
				<td colspan="7"><textarea class="chengguo" id="keyanchengguo"
						name="keyanchengguo"
						placeholder="科研成果[具体说明项目（课题）名称、本人排序、立项时间、立项机关、完成及鉴定情况，卷宗页码”，科研奖励的“获奖名称、本人排序、时间、等级等，卷宗页码”]。">${entryInfo.keyanchengguo}</textarea>
				</td>
			</tr>
			<tr>
				<td colspan="8" width="100%"><strong>论文著作</strong></td>
			</tr>
			<tr>
				<td><strong>论文题目</strong></td>
				<td colspan="7"><input type="text" id="lunwen_timu"
					name="lunwen_timu" value="${entryInfo.lunwen_timu}"
					placeholder="请输入论文题目" /></td>
			</tr>
			<tr>
				<td><strong>论文期刊</strong></td>
				<td colspan="2"><input type="text" placeholder="请输入论文期刊"
					id="lunwen_qikan" name="lunwen_qikan"
					value="${entryInfo.lunwen_qikan}" /></td>
				<td><strong>著作</strong></td>
				<td><select id="lunwen_duzhu" name="lunwen_duzhu">
						<option value="${entryInfo.lunwen_duzhu}">${entryInfo.lunwen_duzhu}</option>
						<option value="独著">独著</option>
						<option value="合著">合著</option>
				</select></td>
				<td><strong>发表时间</strong></td>
				<td colspan="2"><input type="text" placeholder="请选择日期"
					id="lunwen_time" name="lunwen_time" value="${entryInfo.lunwen_time}" /></td>
				<script>
			$(function() {
					document.getElementById('lunwen_time').onclick = function() {
						start.elem = this;
						laydate(start);
					}
				});
			})
		</script>
			</tr>
			<tr>
				<td><strong>摘要</strong></td>
				<td colspan="7"><textarea class="zhaiyao" id="lunwen_zhaiyao"
						name="lunwen_zhaiyao"
						placeholder="科研成果[具体说明项目（课题）名称、本人排序、立项时间、立项机关、完成及鉴定情况，卷宗页码”，科研奖励的“获奖名称、本人排序、时间、等级等，卷宗页码”]。">${entryInfo.lunwen_zhaiyao}</textarea>
				</td>
			</tr>
		</table>
		<div class="caozuo">
			<input type="button" class="btn btn-success btn-xm"
				onclick="update_zhicheng_entry_info(${entryInfo.entryInfoId})" value="提交">
			<input type="button" class="btn btn-danger btn-xm"
				onclick="close_layer()" value="取消">
		</div>
	</form>
</div>