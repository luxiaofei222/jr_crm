<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" type="text/css"
	href="/css/school/back/common/reset.css">
<link rel="stylesheet" href="/css/school/back/common/bootstrap.min.css" />
<link rel="stylesheet"
	href="/css/school/back/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="/css/edu/jquery-ui.min.css" />
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
<script src="/js/edu/jquery-ui.min.js" type="text/javascript"></script>
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
							if (xueli_upload_pic()) {
								var $file = $(this);
								var fileObj = $file[0];
								var windowURL = window.URL || window.webkitURL;
								var dataURL;
								var $img = $("#preview5");

								if (fileObj && fileObj.files
										&& fileObj.files[0]) {
									dataURL = windowURL
											.createObjectURL(fileObj.files[0]);
									$img.attr('src', dataURL);
								} else {
									dataURL = $file.val();
									var imgObj = document
											.getElementById("preview");
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
	function get_entry_city() {
		var entry_province_id = $("#entry_province_id").val();
		$.post("/entry_place/get_sub_entryplace.html", {
			'plcae_id' : entry_province_id
		}, function(data) {
			$("#entry_province_id").html(data);
		})
	}
	//获取学校地点
	function get_entry_school() {
		var entry_city_id = $("#entry_city_id").val();
		$.post("/entry_place/get_sub_entryplace.html", {
			'plcae_id' : entry_city_id
		}, function(data) {
			$("#entry_school_id").html(data);
		})
	}
	//获取计划
	function get_entry_plan(obj) {
		var course_id_plan = $(obj).val();
		var parent_id = $("#course_id_parent_id").val();
		if (course_id_plan != 0) {
			if (parent_id != 9) {
				$.post("/edu_entry/get_sub_plan.jr", {
					'course_id_plan' : course_id_plan
				}, function(data) {
					$("#entryplanId").html(data);
				})
			} else {
				var course_id = $("#course_id_plan").val();
				$.post("/edu_entry/get_sub_review.jr", {
					'course_id' : course_id
				}, function(data) {
					$("#course_id_review").html(data);
				})
			}
		} else {
			$("#entryplanId").html("<option value='0'>请选择计划</option>");
		}
	}
	//获取职称评审计划
	function get_entry_review_plan() {
		var course_id_review = $("#course_id_review").val();
		if (course_id_plan != 0) {
			$.post("/edu_entry/get_sub_review_plan.jr", {
				'review_id' : course_id_review
			}, function(data) {
				$("#entryplanId").html(data);
			})
		} else {
			$("#entryplanId").html("<option value='0'>请选择计划</option>");
		}
	}
	//筛选专业
	function get_yuancheng_zhuanye(course_id, cengci, university_id) {
		if (course_id == 20) {
			//显示专业
			$.post("/edu_condition/get_zhuanye.jr", {
				'university_id' : university_id,
				'type_university' : cengci
			}, function(data) {
				$("#zhuanye_id").html(data);
			})
		} else if (course_id == 19) {
			var entrycondition_id = $("input[type='radio']:checked").val();
			//显示专业
			$.post("/edu_condition/get_chengkao_zhuanye.jr", {
				'university_id' : university_id,
				'cengci' : cengci
			}, function(data) {
				$("#zhuanye_id").html(data);
			})
			//显示成考学费
			$.post("/edu_condition/get_chengkao_xuefei.jr", {
				'entrycondition_id' : entrycondition_id
			}, function(data) {
				$("#xuefei").html(data);
			})
		}
	}
	//获取二级分类
	function get_entry_plan_parent() {
		var parent_id = $("#course_id_parent_id").val();
		if (parent_id != 0) {
			$("#entryplanId").html("<option value='0'>请选择计划</option>");
			if (parent_id == 9) {
				//$(".lunwen").show();
				$("#zhicheng_ziliao").show();
				$("#course_id_review").show();
			} else {
				//$(".lunwen").hide();
				$("#zhicheng_ziliao").hide();
				$("#course_id_review").hide();
				$("#course_id_review").html(
						"<option value='0'>请选择三级分类</option>");
				$("#entryplanId").html("<option value='0'>请选择计划</option>");
			}
			$.post("/edu_entry/get_sub_couse.jr", {
				'parent_id' : parent_id
			}, function(data) {
				$("#course_id_plan").html(data);
				if (parent_id == 9) {
					var course_id = $("#course_id_plan").val();
					$.post("/edu_entry/get_sub_review.jr", {
						'course_id' : course_id
					}, function(data) {
						$("#course_id_review").html(data);
					})
				}
			})
		} else {
			$("#course_id_plan").html("<option value='0'>请选择二级分类</option>");
			$("#entryplanId").html("<option value='0'>请选择计划</option>");
		}
	}
</script>
<div class="tianjia_xueyuan_dialog">
	<form class="form-horizontal import_xueyuanshuju_dialog"
		enctype="multipart/form-data" id="myform" style="padding: 20px;">
		<div class="form-group">
			<label class="col-xs-2 control-label">报名计划：</label>
			<div class="col-xs-10">
				<select class="col-xs-2" style="width: 47%; margin-right: 42px;"
					id="course_id_parent_id" onchange="get_entry_plan_parent()">
					<option value="0">请选择分类</option>
					<c:forEach items="${course_list }" var="course">
						<option value="${course.parent_id }">${course.coursename }</option>
					</c:forEach>
				</select> <select class="col-xs-2" style="width: 47%;" id="course_id_plan"
					onchange="get_entry_plan(this)">
					<option value="0">请选择二级分类</option>
					<%-- <c:forEach items="${course_list }" var="course">
						<option value="${course.course_id }">${course.coursename }</option>
					</c:forEach> --%>
				</select> <select class="col-xs-2"
					style="width: 47%; margin-right: 42px; margin-top: 5px; display: none;"
					id="course_id_review" onchange="get_entry_review_plan()">
					<option value="0">请选择三级分类</option>
					<%-- <c:forEach items="${course_list }" var="course">
						<option value="${course.course_id }">${course.coursename }</option>
					</c:forEach> --%>
				</select> <select class="col-xs-2" style="width: 47%; margin-top: 5px;"
					id="entryplanId" onchange="get_shenbaotiaojian(this)"
					name="entryplanId">
					<option value="0">请选择计划</option>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-xs-2 control-label">类、级别(学校)：</label>
			<div class="col-xs-10">
				<select id="course_id" onchange="get_dic_list(this)"
					name="course_id">
					<option value="0">请选择申报类别</option>
				</select> <select id="dictionary_id" onchange="get_tiaojian_radio(this)"
					name="dictionary_id">
					<option value="0">请选择申报级别(学校)</option>
				</select>
			</div>

		</div>
		<div class="form-group">
			<label class="col-xs-2 control-label">相关业务员：</label>
			<div class="col-xs-10">
				<select id="employee_id" name="employee_id">
					<option value="">请选择业务员</option>
					<c:forEach items="${employees }" var="employee">
						<c:if
							test="${sessionScope.employee_session.employee_id ==employee.employee_id}">
							<option selected="selected" value="${employee.employee_id }">${employee.employee_name }</option>
						</c:if>
						<c:if
							test="${sessionScope.employee_session.employee_id !=employee.employee_id}">
							<option value="${employee.employee_id }">${employee.employee_name }</option>
						</c:if>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="form-group" id="tiaojianlist">
			<!-- 报名条件  -->
		</div>
		<div class="form-group">
			<label class="col-xs-2 control-label">选择报名点：</label>
			<div class="col-xs-10">
				<select class="col-xs-3" onchange="get_entry_city()"
					style="width: 30%; margin-right: 35px;" id="entry_province_id"
					name="entry_province_id">
					<c:forEach items="${entryPlaces }" var="place">
						<option value="${place.entryplace_id }">${place.entryplace_name }</option>
					</c:forEach>
				</select> <select class="col-xs-3" style="width: 30%; margin-right: 35px;"
					onchange="get_entry_school()" id="entry_city_id"
					name="entry_city_id">
					<c:forEach items="${entryPlaces_sub }" var="place">
						<option value="${place.entryplace_id }">${place.entryplace_name }</option>
					</c:forEach>
				</select> <select class="col-xs-3" style="width: 30%;" id="entry_school_id"
					name="entry_school_id">
					<c:forEach items="${entryPlaces_sub_sub }" var="place">
						<option value="${place.entryplace_id }">${place.entryplace_name }</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-xs-2 control-label" style="text-align: left;">上传图片：</label>
			<div class="col-xs-10">
				<ul class="pics">
					<li>
						<div class="xueyuan_pic">
							<img id="preview1" width="140" height="140" />
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
					<!-- <li>
						<div class="xueyuan_pic">
							<img id="preview2" width="100" height="100" />
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

					</li> -->
					<li>
						<div class="xueyuan_pic">
							<img id="preview3" width="140" height="140" />
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
							<img id="preview4" width="140" height="140" />
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
							<img id="preview5" width="140" height="140" />
						</div>
						<div class="pic_btn">
							<img src="/images/crm/business/tips.png"
								style="width: 25px; height: 25px; float: left;"
								onmouseover="xueli_tip_zhaopian()" id="xueli_tip">
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
		<div id="info_table">
			<!-- 学员信息填报页面 -->
			<table border="1" class="tianjia_xueyuan">
				<tr>
					<td colspan="8"><strong>基本信息</strong></td>
				</tr>
				<tr>
					<td width="12%"><strong>学员姓名</strong></td>
					<td width="13%"><input type="text"
						onblur="check_entryUserName()" placeholder="请输入姓名"
						name="entryUserName" id="entryUserName" /></td>
					<td width="12%"><strong>性别</strong></td>
					<td width="10%"><select id="entryUserSex" name="entryUserSex">
							<option value="男">男</option>
							<option value="女">女</option>
					</select></td>
					<td width="12%"><strong>民族</strong></td>
					<td width="13%"><select id="entryUserNation"
						name="entryUserNation">
							<c:forEach items="${nations }" var="na">
								<option value="${na.nation }">${na.nation }</option>
							</c:forEach>
					</select></td>
					<td width="12%"><strong>文化程度</strong></td>
					<td width="16%"><select id="entryUserEducation"
						name="entryUserEducation">
							<option value="研究生">研究生</option>
							<option value="本科">本科</option>
							<option value="专科">专科</option>
							<option value="专科以下">专科以下</option>
					</select></td>
				</tr>
				<tr>
					<td width="10%"><strong>出生日期</strong></td>
					<td><input type="text" class="layui-input"
						name="entryUserBirthday" id="entryUserBirthday"
						readonly="readonly" /></td>
					<td><strong>证件类型</strong></td>
					<td><select id="documentType" name="documentType">
							<option value="身份证">身份证</option>
							<option value="护照">护照</option>
					</select></td>
					<td><strong>证件号码</strong></td>
					<td colspan="3"><input type="text" placeholder="请输入证件号码"
						id="documentNumber" name="documentNumber"
						onblur="check_documentNumber()" /></td>
				</tr>
				<tr>
					<td><strong>手机号</strong></td>
					<td><input type="text" onblur="check_entryUserPhone()"
						placeholder="请输入手机号" id="entryUserPhone" name="entryUserPhone" /></td>
					<td><strong>电子邮箱</strong></td>
					<td colspan="3"><input type="text"
						onblur="check_entryUserMail()" placeholder="请输入电子邮箱"
						id="entryUserMail" name="entryUserMail" /></td>
					<td width="12%"><strong>政治面貌</strong></td>
					<td width="13%"><select id="entryPolitical"
						name="entryPolitical">
							<option value="党员">党员</option>
							<option value="团员">团员</option>
							<option value="群众">群众</option>
					</select></td>
				</tr>
				<tr>
					<td><strong>紧急联系人</strong></td>
					<td colspan="2"><input type="text" placeholder="请输入紧急联系人"
						id="jinji_name" name="jinji_name" /></td>
					<td><strong>紧急联系人电话</strong></td>
					<td colspan="4"><input type="text" placeholder="请输入紧急联系人电话"
						id="jinji_phone" name="jinji_phone" /></td>
				</tr>
				<tr>
					<td><strong>邮编</strong></td>
					<td colspan="2"><input type="text" placeholder="请输入邮编"
						id="zipCode" name="zipCode" /></td>
					<td><strong>现住址</strong></td>
					<td colspan="4"><input type="text" placeholder="请输入现居住地"
						id="entryUserAddress" name="entryUserAddress" /></td>
				</tr>
				<tr>
					<td width="12%"><strong>所在单位</strong></td>
					<td colspan="4" width="38%"><input type="text"
						oninput="get_company()" placeholder="请输入所在单位" id="entryUserUnit"
						name="entryUserUnit" />
						<div id="tip">
							<!-- 提示框 -->
						</div></td>
					<td width="12%"><strong>所在职位</strong></td>
					<td colspan="2" width="13%"><input type="text"
						placeholder="请输入职位" id="entryUserPosition"
						name="entryUserPosition" /></td>
				</tr>
				<tr>
					<script>
						$(
								function() {
									layui
											.use(
													'laydate',
													function() {
														var laydate = layui.laydate;
														var start = {
															festival : true,
															istoday : true,
															choose : function(
																	datas) {
																check_work_time();
															}
														};
														//					document.getElementById('entryUserBirthday').onclick = function() {
														//						start.elem = this;
														//						laydate(start);
														//					}
														document
																.getElementById('inWorkTime').onclick = function() {
															start.elem = this;
															laydate(start);
														}
													});
								})
					</script>
					<td width="12%"><strong>参加工作时间</strong></td>
					<td width="13%"><input type="text" class="layui-input"
						onblur="check_wordyears()" name="inWorkTime" id="inWorkTime"
						placeholder="点击获取日期" /></td>
					<td width="12%"><strong>连续工龄</strong></td>
					<td width="13%"><input type="text" readonly="readonly"
						id="workYears" name="workYears" /></td>
					<td width="12%"><strong>户口所在地</strong></td>
					<td colspan="3" width="38%"><select style="width: 95px;"
						onchange="get_city()" name="province" id="province">
							<option value="0">请选择省份</option>
							<c:forEach items="${cities }" var="pro">
								<option value="${pro.id }">${pro.name }</option>
							</c:forEach>
					</select> <select style="width: 95px;" onchange="get_area()" id="user_city"
						name="user_city">
							<option value="0">请选择城市</option>
					</select> <select style="width: 95px;" id="entryUserArea"
						name="entryUserArea">
							<option value="0">请选择地区</option>
					</select></td>
				</tr>
				<tr>
					<td colspan="8" width="100%"><strong>课程信息</strong></td>
				</tr>
				<tr>
					<td width="12%"><strong>类别</strong></td>
					<td width="10%"><select id="entryCategory"
						name="entryCategory">
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
							<option value="精讲班">精讲班</option>
							<option value="普通班">普通班</option>
					</select></td>
				</tr>
				<tr>
					<td colspan="8" width="100%"><strong>财务信息</strong></td>
				</tr>
				<tr>
					<td><strong><strong>收费项目</strong></strong></td>
					<td colspan="2"><strong>应缴</strong></td>
					<td><strong>组织费</strong></td>
					<td><strong>降费</strong></td>
					<td><strong>实缴</strong></td>
					<td><strong>支付方式</strong></td>
					<td><strong>欠费</strong></td>
				</tr>
				<tr>
					<td colspan="8" width="100%">
						<div id="feiyong">
							<!--切换各种费用  -->
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="8" width="100%"><strong>资料领取</strong></td>
				</tr>
				<tr>
					<td width="12%"><strong>证书</strong></td>
					<td width="13%"><select id="tingkezheng" name="tingkezheng">
							<option value="未领取">未领取</option>
							<option value="已领取">已领取</option>
					</select></td>
					<td width="12%"><strong>教材</strong></td>
					<td width="13%"><select id="jiaocai" name="jiaocai">
							<option value="未发">未发</option>
							<option value="已发">已发</option>
					</select></td>
					<td width="12%"><strong>教辅</strong></td>
					<td width="13%"><select id="jiaofu" name="jiaofu">
							<option value="未发">未发</option>
							<option value="已发">已发</option>
					</select></td>
					<td width="12%"><select id="jiaofulingqu" name="jiaofulingqu">
							<option value="未领">未领取</option>
							<option value="已领">已领取</option>
					</select></td>
					<td width="16%"></td>
				</tr>
				<tr>
					<td width="12%"><strong>成绩</strong></td>
					<td width="13%"><select id="wangluoxuexika"
						name="wangluoxuexika">
							<option value="">请选择</option>
							<option value="合格">合格</option>
							<option value="不合格">不合格</option>
					</select></td>
					<td width="12%"><strong>是否录取</strong></td>
					<td width="13%"><select id="zhuanshufuwuka"
						name="zhuanshufuwuka">
							<option value="">请选择</option>
							<option value="录取">录取</option>
							<option value="未录取">未录取</option>
					</select></td>
					<td width="12%"><strong>报考班次</strong></td>
					<td width="13%"><input type="text" id="baokao_banci"
						name="baokao_banci" onblur="check_baokao_banci()" /></td>
					<td colspan="2"></td>
				</tr>
			</table>
			<div class="caozuo">
				<input type="button" class="btn btn-success btn-xm"
					onclick="save_entry_info(this)" value="提交"> <input
					type="button" class="btn btn-danger btn-xm" onclick="close_layer()"
					value="取消">
			</div>
		</div>
	</form>
</div>