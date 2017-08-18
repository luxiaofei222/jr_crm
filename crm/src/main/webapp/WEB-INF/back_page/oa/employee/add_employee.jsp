<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="/css/school/back/common/reset.css">
<link rel="stylesheet" type="text/css" href="/css/oa/employee.css">
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<title>添加新员工入职登记表</title>
</head>
<script type="text/javascript">
	//切换岗位
	function change_gangwei() {
		var bumen_id = $("#bumen_id").val();
		$.post("/back_employee/get_sun_oragn.jr", {
			'organ_id' : bumen_id
		}, function(data) {
			$("#gangwei_id").html(data);
		})
	}
	//保存入职员工
	function save_oa_employee(obj) {
		$(obj).attr({
			"disabled" : "disabled"
		});
		$(obj).html("正在保存");
		//教育经历
		var school_start_time = "";
		var school_end_time = "";
		var school_name_str = "";
		var zhuanye_str_school = "";
		var xueli_str_school = "";
		$('input:text[name=start_time_str_shool]').each(function(i) {
			if (0 == i) {
				school_start_time = $(this).val();
			} else {
				school_start_time += "," + $(this).val();
			}
		});
		$('input:text[name=end_time_str_school]').each(function(i) {
			if (0 == i) {
				school_end_time = $(this).val();
			} else {
				school_end_time += "," + $(this).val();
			}
		});
		$('input:text[name=school_name_str]').each(function(i) {
			if (0 == i) {
				school_name_str = $(this).val();
			} else {
				school_name_str += "," + $(this).val();
			}
		});
		$('input:text[name=zhuanye_str]').each(function(i) {
			if (0 == i) {
				zhuanye_str_school = $(this).val();
			} else {
				zhuanye_str_school += "," + $(this).val();
			}
		});
		$('input:text[name=xueli_str]').each(function(i) {
			if (0 == i) {
				xueli_str_school = $(this).val();
			} else {
				xueli_str_school += "," + $(this).val();
			}
		});
		//工作经历
		var work_start_time = "";
		var work_end_time = "";
		var danwei_name_str = "";
		var gangwei_str = "";
		var gongzuo_neirong_str = "";
		$('input:text[name=star_time_work]').each(function(i) {
			if (0 == i) {
				work_start_time = $(this).val();
			} else {
				work_start_time += "," + $(this).val();
			}
		});
		$('input:text[name=end_time_work]').each(function(i) {
			if (0 == i) {
				work_end_time = $(this).val();
			} else {
				work_end_time += "," + $(this).val();
			}
		});
		$('input:text[name=danwei_name]').each(function(i) {
			if (0 == i) {
				danwei_name_str = $(this).val();
			} else {
				danwei_name_str += "," + $(this).val();
			}
		});
		$('input:text[name=gangwei]').each(function(i) {
			if (0 == i) {
				gangwei_str = $(this).val();
			} else {
				gangwei_str += "," + $(this).val();
			}
		});
		$('input:text[name=gongzuo_neirong]').each(function(i) {
			if (0 == i) {
				gongzuo_neirong_str = $(this).val();
			} else {
				gongzuo_neirong_str += "," + $(this).val();
			}
		});
		//家庭联系人
		var guanxi_jiating_str = "";
		var name_jiating_str = "";
		var age_jiating_str = "";
		var danwei_jiating_str = "";
		var zhiwu_jiating_str = "";
		var phone_jiating_str = "";
		$('input:text[name=guanxi_jiating]').each(function(i) {
			if (0 == i) {
				guanxi_jiating_str = $(this).val();
			} else {
				guanxi_jiating_str += "," + $(this).val();
			}
		});
		$('input:text[name=name_jiating]').each(function(i) {
			if (0 == i) {
				name_jiating_str = $(this).val();
			} else {
				name_jiating_str += "," + $(this).val();
			}
		});
		$('input:text[name=age_jiating]').each(function(i) {
			if (0 == i) {
				age_jiating_str = $(this).val();
			} else {
				age_jiating_str += "," + $(this).val();
			}
		});
		$('input:text[name=danwei_jiating]').each(function(i) {
			if (0 == i) {
				danwei_jiating_str = $(this).val();
			} else {
				danwei_jiating_str += "," + $(this).val();
			}
		});
		$('input:text[name=zhiwu_jiating]').each(function(i) {
			if (0 == i) {
				zhiwu_jiating_str = $(this).val();
			} else {
				zhiwu_jiating_str += "," + $(this).val();
			}
		});
		$('input:text[name=phone_jiating]').each(function(i) {
			if (0 == i) {
				phone_jiating_str = $(this).val();
			} else {
				phone_jiating_str += "," + $(this).val();
			}
		});
		//紧急联系人
		var guanxi_jinji_str = "";
		var name_jinji_str = "";
		var age_jinji_str = "";
		var danwei_jinji_str = "";
		var zhiwu_jinji_str = "";
		var phone_jinji_str = "";
		$('input:text[name=guanxi_jinji]').each(function(i) {
			if (0 == i) {
				guanxi_jinji_str = $(this).val();
			} else {
				guanxi_jinji_str += "," + $(this).val();
			}
		});
		$('input:text[name=name_jinji]').each(function(i) {
			if (0 == i) {
				name_jinji_str = $(this).val();
			} else {
				name_jinji_str += "," + $(this).val();
			}
		});
		$('input:text[name=age_jinji]').each(function(i) {
			if (0 == i) {
				age_jinji_str = $(this).val();
			} else {
				age_jinji_str += "," + $(this).val();
			}
		});
		$('input:text[name=danwei_jinji]').each(function(i) {
			if (0 == i) {
				danwei_jinji_str = $(this).val();
			} else {
				danwei_jinji_str += "," + $(this).val();
			}
		});
		$('input:text[name=zhiwu_jinji]').each(function(i) {
			if (0 == i) {
				zhiwu_jinji_str = $(this).val();
			} else {
				zhiwu_jinji_str += "," + $(this).val();
			}
		});
		$('input:text[name=phone_jinji]').each(function(i) {
			if (0 == i) {
				phone_jinji_str = $(this).val();
			} else {
				phone_jinji_str += "," + $(this).val();
			}
		});
		$("#myform").ajaxSubmit({
			type : 'POST',
			url : "/oa_employee/save_oa_employee.jr",
			data : {
				'school_start_time' : school_start_time,
				'school_end_time' : school_end_time,
				'school_name_str' : school_name_str,
				'zhuanye_str_school' : zhuanye_str_school,
				'xueli_str_school' : xueli_str_school,
				'work_start_time' : work_start_time,
				'work_end_time' : work_end_time,
				'danwei_name_str' : danwei_name_str,
				'gangwei_str' : gangwei_str,
				'gongzuo_neirong_str' : gongzuo_neirong_str,
				'guanxi_jiating_str' : guanxi_jiating_str,
				'name_jiating_str' : name_jiating_str,
				'age_jiating_str' : age_jiating_str,
				'danwei_jiating_str' : danwei_jiating_str,
				'zhiwu_jiating_str' : zhiwu_jiating_str,
				'phone_jiating_str' : phone_jiating_str,
				'guanxi_jinji_str' : guanxi_jinji_str,
				'name_jinji_str' : name_jinji_str,
				'age_jinji_str' : age_jinji_str,
				'danwei_jinji_str' : danwei_jinji_str,
				'zhiwu_jinji_str' : zhiwu_jinji_str,
				'phone_jinji_str' : phone_jinji_str,
			},
			success : function(data) {
				if (data == 1) {
					//添加数据成功，关闭弹出窗之前，刷新列表页面的数据
					tanchuang('恭喜您，添加成功');
					$(obj).html("添加");
					 $(obj).removeAttr("disabled");
					$('#myform')[0].reset();
				} else {
					tanchuang('很遗憾，添加失败');
				}
			},
			error : function(XmlHttpRequest, textStatus, errorThrown) {
				tanchuang('很遗憾，添加失败');
			}
		});
	}
</script>
<body>
	<div class="entry_table">
		<h3>员工入职登记表</h3>
		<form enctype="multipart/form-data" id="myform"
			class="form-horizontal">
			<table cellpadding="0" cellspacing="0" border="1">
				<tr>
					<td rowspan="7">个人资料</td>
					<td>部门</td>
					<td><select id="bumen_id" name="bumen_id"
						onchange="change_gangwei()">
							<c:forEach items="${organizations }" var="organ">
								<option value="${organ.organization_id }">${organ.organization_name }</option>
							</c:forEach>
					</select></td>
					<td>岗位</td>
					<td><select id="gangwei_id" name="gangwei_id">
							<c:forEach items="${organizations_sub }" var="organ_sub">
								<option value="${organ_sub.organization_id }">${organ_sub.organization_name }</option>
							</c:forEach>
					</select></td>
					<td>入职日期</td>
					<td><input class="layui-input" placeholder="点击选择"
						name="ruzhi_time_str" id="ruzhi_time_str"></td>
					<td rowspan="4"><img id="zhiyuan" width="140" height="140" />
						<a href="javascript:void(0)" class="btn btn-info btn-xs">选择照片
							<input type="file" id="employee_pic_str" name="employee_pic_str"
							style="position: absolute; opacity: 0; top: 0px; left: 0px; width: 100%; height: 100%;" />
					</a></td>
				</tr>
				<tr>
					<td>姓名</td>
					<td><input type="text" id="xingming" name="xingming" /></td>
					<td>性别</td>
					<td><select id="xingbie" name="xingbie">
							<option value="男">男</option>
							<option value="女">女</option>
					</select></td>
					<td>民族</td>
					<td><select id="minzu" name="minzu">
							<c:forEach items="${nations }" var="nation">
								<option value="${nation.nation }">${nation.nation }</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td>出生日期</td>
					<td><input class="layui-input" placeholder="点击选择"
						id="birthday" name="birthday"></td>
					<td>政治面貌</td>
					<td><select id="zhengzhi_mianmao" name="zhengzhi_mianmao">
							<option value="群众">群众</option>
							<option value="团员">团员</option>
							<option value="党员">党员</option>
					</select></td>
					<td>婚姻状况</td>
					<td><select id="hunyin_state" name="hunyin_state">
							<option value="未婚">未婚</option>
							<option value="已婚">已婚</option>
					</select></td>
				</tr>
				<tr>
					<td>最高学历</td>
					<td><select id="zuigao_xueli" name="zuigao_xueli">
							<option value="博士">博士</option>
							<option value="硕士">硕士</option>
							<option value="本科">本科</option>
							<option value="专科">专科</option>
							<option value="高中">高中</option>
					</select></td>
					<td>联系电话</td>
					<td><input type="text" id="phone" name="phone" /></td>
					<td>QQ</td>
					<td><input type="text" id="qq" name="qq" /></td>
				</tr>
				<tr>
					<td>身份证号</td>
					<td colspan="2"><input id="card_number" name="card_number"
						type="text" /></td>
					<td>E-mail</td>
					<td colspan="3"><input type="text" id="mail" name="mail" /></td>
				</tr>
				<tr>
					<td>户籍地址</td>
					<td colspan="2"><input type="text" id="huji" name="huji" /></td>
					<td>转正日期</td>
					<td colspan="3"><input class="layui-input" placeholder="点击选择"
						id="zhuanzheng_time_str" name="zhuanzheng_time_str"></td>
				</tr>
				<tr>
					<td>籍贯</td>
					<td colspan="2"><input type="text" id="jiguan" name="jiguan" /></td>
					<td>现住址</td>
					<td colspan="3"><input type="text" id="now_addr"
						name="now_addr" /></td>
				</tr>
				<tr>
					<td rowspan="4">教育经历</td>
					<td colspan="3">起止时间</td>
					<td colspan="2">院校</td>
					<td>专业</td>
					<td>学历学位</td>
				</tr>
				<tr>
					<td colspan="3"><input class="layui-input input_str"
						placeholder="开始日" id="start_time_str1" name="start_time_str_shool">--<input
						class="layui-input input_end" placeholder="截止日" id="end_time_str1"
						name="end_time_str_school"></td>
					<td colspan="2"><input type="text" id="school_name_str"
						name="school_name_str" /></td>
					<td><input type="text" id="zhuanye_str" name="zhuanye_str" /></td>
					<td><input type="text" id="xueli_str" name="xueli_str" /></td>
				</tr>
				<tr>
					<td colspan="3"><input class="layui-input input_str"
						placeholder="开始日" id="start_time_str2" name="start_time_str_shool">--<input
						class="layui-input input_end" placeholder="截止日" id="end_time_str2"
						name="end_time_str_school"></td>
					<td colspan="2"><input type="text" id="school_name_str"
						name="school_name_str" /></td>
					<td><input type="text" id="zhuanye_str" name="zhuanye_str" /></td>
					<td><input type="text" id="xueli_str" name="xueli_str" /></td>
				</tr>
				<tr>
					<td colspan="3"><input class="layui-input input_str"
						placeholder="开始日" id="start_time_str3" name="start_time_str_shool">--<input
						class="layui-input input_end" placeholder="截止日" id="end_time_str3"
						name="end_time_str_school"></td>
					<td colspan="2"><input type="text" id="school_name_str"
						name="school_name_str" /></td>
					<td><input type="text" id="zhuanye_str" name="zhuanye_str" /></td>
					<td><input type="text" id="xueli_str" name="xueli_str" /></td>
				</tr>
				<tr>
					<td rowspan="4">工作经历</td>
					<td colspan="3">起止时间</td>
					<td>工作单位</td>
					<td>岗位</td>
					<td colspan="2">工作内容</td>
				</tr>
				<tr>
					<td colspan="3"><input class="layui-input input_str"
						placeholder="开始日" id="start_time_str4" name="star_time_work">--<input
						class="layui-input input_end" placeholder="截止日" id="end_time_str4"
						name="end_time_work"></td>
					<td><input type="text" id="danwei_name" name="danwei_name" /></td>
					<td><input type="text" id="gangwei" name="gangwei" /></td>
					<td colspan="2"><input type="text" id="gongzuo_neirong"
						name="gongzuo_neirong" /></td>
				</tr>
				<tr>
					<td colspan="3"><input class="layui-input input_str"
						placeholder="开始日" id="start_time_str5" name="star_time_work">--<input
						class="layui-input input_end" placeholder="截止日" id="end_time_str5"
						name="end_time_work"></td>
					<td><input type="text" id="danwei_name" name="danwei_name" /></td>
					<td><input type="text" id="gangwei" name="gangwei" /></td>
					<td colspan="2"><input type="text" id="gongzuo_neirong"
						name="gongzuo_neirong" /></td>
				</tr>
				<tr>
					<td colspan="3"><input class="layui-input input_str"
						placeholder="开始日" id="start_time_str6" name="star_time_work">--<input
						class="layui-input input_end" placeholder="截止日" id="end_time_str6"
						name="end_time_work"></td>
					<td><input type="text" id="danwei_name" name="danwei_name" /></td>
					<td><input type="text" id="gangwei" name="gangwei" /></td>
					<td colspan="2"><input type="text" id="gongzuo_neirong"
						name="gongzuo_neirong" /></td>
				</tr>
				<tr>
					<td rowspan="4">家庭成员</td>
					<td>关系</td>
					<td>姓名</td>
					<td>年龄</td>
					<td colspan="2">工作单位</td>
					<td>职务</td>
					<td>联系电话</td>
				</tr>
				<tr>
					<td><input type="text" id="guanxi_jiating"
						name="guanxi_jiating" /></td>
					<td><input type="text" id="name_jiating" name="name_jiating" /></td>
					<td><input type="text" id="age_jiating" name="age_jiating" /></td>
					<td colspan="2"><input type="text" id="danwei_jiating"
						name="danwei_jiating" /></td>
					<td><input type="text" id="zhiwu_jiating" name="zhiwu_jiating" /></td>
					<td><input type="text" id="phone_jiating" name="phone_jiating" /></td>
				</tr>
				<tr>
					<td><input type="text" id="guanxi_jiating"
						name="guanxi_jiating" /></td>
					<td><input type="text" id="name_jiating" name="name_jiating" /></td>
					<td><input type="text" id="age_jiating" name="age_jiating" /></td>
					<td colspan="2"><input type="text" id="danwei_jiating"
						name="danwei_jiating" /></td>
					<td><input type="text" id="zhiwu_jiating" name="zhiwu_jiating" /></td>
					<td><input type="text" id="phone_jiating" name="phone_jiating" /></td>
				</tr>
				<tr>
					<td><input type="text" id="guanxi_jiating"
						name="guanxi_jiating" /></td>
					<td><input type="text" id="name_jiating" name="name_jiating" /></td>
					<td><input type="text" id="age_jiating" name="age_jiating" /></td>
					<td colspan="2"><input type="text" id="danwei_jiating"
						name="danwei_jiating" /></td>
					<td><input type="text" id="zhiwu_jiating" name="zhiwu_jiating" /></td>
					<td><input type="text" id="phone_jiating" name="phone_jiating" /></td>
				</tr>
				<tr>
					<td rowspan="2">紧急联系人</td>
					<td><input type="text" id="guanxi_jinji" name="guanxi_jinji" /></td>
					<td><input type="text" id="name_jinji" name="name_jinji" /></td>
					<td><input type="text" id="age_jinji" name="age_jinji" /></td>
					<td colspan="2"><input type="text" id="danwei_jinji"
						name="danwei_jinji" /></td>
					<td><input type="text" id="zhiwu_jinji" name="zhiwu_jinji" /></td>
					<td><input type="text" id="phone_jinji" name="phone_jinji" /></td>
				</tr>
				<tr>
					<td><input type="text" id="guanxi_jinji" name="guanxi_jinji" /></td>
					<td><input type="text" id="name_jinji" name="name_jinji" /></td>
					<td><input type="text" id="age_jinji" name="age_jinji" /></td>
					<td colspan="2"><input type="text" id="danwei_jinji"
						name="danwei_jinji" /></td>
					<td><input type="text" id="zhiwu_jinji" name="zhiwu_jinji" /></td>
					<td><input type="text" id="phone_jinji" name="phone_jinji" /></td>
				</tr>
			</table>
			<div class="add_reset">
				<button class="layui-btn layui-add" onclick="save_oa_employee(this)">添加</button>
				<button class="layui-btn layui-btn-warm" type="reset">重置</button>
			</div>
		</form>
	</div>
</body>
<script>
	$(function() {
		layui
				.use(
						'laydate',
						function() {
							var laydate = layui.laydate;
							var start = {
								festival : true,
								istoday : true
							};
							document.getElementById('birthday').onclick = function() {//获取生日时间
								start.elem = this;
								laydate(start);
							}
							document.getElementById('zhuanzheng_time_str').onclick = function() {//获取生日时间
								start.elem = this;
								laydate(start);
							}
							document.getElementById('ruzhi_time_str').onclick = function() {//获取入职日期
								start.elem = this;
								laydate(start);
							}
							document.getElementById('start_time_str1').onclick = function() {
								start.elem = this;
								laydate(start);
							}
							document.getElementById('end_time_str1').onclick = function() {
								start.elem = this;
								laydate(start);
							}
							document.getElementById('start_time_str2').onclick = function() {
								start.elem = this;
								laydate(start);
							}
							document.getElementById('end_time_str2').onclick = function() {
								start.elem = this;
								laydate(start);
							}
							document.getElementById('start_time_str3').onclick = function() {
								start.elem = this;
								laydate(start);
							}
							document.getElementById('end_time_str3').onclick = function() {
								start.elem = this;
								laydate(start);
							}
							document.getElementById('start_time_str4').onclick = function() {
								start.elem = this;
								laydate(start);
							}
							document.getElementById('end_time_str4').onclick = function() {
								start.elem = this;
								laydate(start);
							}
							document.getElementById('start_time_str5').onclick = function() {
								start.elem = this;
								laydate(start);
							}
							document.getElementById('end_time_str5').onclick = function() {
								start.elem = this;
								laydate(start);
							}
							document.getElementById('start_time_str6').onclick = function() {
								start.elem = this;
								laydate(start);
							}
							document.getElementById('end_time_str6').onclick = function() {
								start.elem = this;
								laydate(start);
							}
						});
	})
	$(function() {
		$("#employee_pic_str")
				.change(
						function() {
							var $file = $(this);
							var fileObj = $file[0];
							var windowURL = window.URL || window.webkitURL;
							var dataURL;
							var $img = $("#zhiyuan");

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
	})
</script>
</html>