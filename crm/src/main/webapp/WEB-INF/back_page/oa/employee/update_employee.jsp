<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="/css/school/back/common/reset.css">
<link rel="stylesheet" type="text/css" href="/css/oa/employee.css">
<script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
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
	function update_employee_info(obj,oa_employee_id) {
		$(obj).attr({
			"disabled" : "disabled"
		});
		$(obj).html("正在修改");
		$("#myform").ajaxSubmit({
			type : 'POST',
			url : "/oa_employee/update_oa_employee.jr",
			data:{'oa_employee_id':oa_employee_id},
			success : function(data) {
				if (data == 1) {
					//添加数据成功，关闭弹出窗之前，刷新列表页面的数据
					tanchuang('恭喜您，修改成功');
					$(obj).html("点击修改");
					 $(obj).removeAttr("disabled");
				} else {
					tanchuang('很遗憾，修改失败');
				}
			},
			error : function(XmlHttpRequest, textStatus, errorThrown) {
				tanchuang('很遗憾，添加失败');
			}
		});
	} 
	
	//刷新当前页面
	function to_update_employee(oa_employee_id,limit,page){
		jiazaidonghua();
		 $.post("/oa_employee/to_update_employee.jr", {
			 'oa_employee_id':oa_employee_id,
			 'pageNumber' : page,
			 'limit' : limit
			}, function(data) {
				$("#conten_list").html(data);
			})
	}
	//返回员工信息列表
	function fanhui_employee_list(page,limit){
		jiazaidonghua();
		$.post("/oa_employee/get_oa_employee_list.jr",{
			'pageNumber' : page,
			'limit' : limit
		},function(data){
			$("#conten_list").html(data);
		})
	}
</script>
<body>
	<div class="entry_table">
		<h3>员工入职登记表</h3>
		<form enctype="multipart/form-data" id="myform" class="form-horizontal">
			<table cellpadding="0" cellspacing="0" border="1">
				<tr>
					<td rowspan="7">个人资料</td>
					<td>部门</td>
					<td><select id="bumen_id" name="bumen_id"
						onchange="change_gangwei()">
							<c:forEach items="${organizations }" var="organ">
							<c:if test="${oaEmployee.bumen_id==organ.organization_id }">
								<option selected="selected" value="${organ.organization_id }">${organ.organization_name }</option>
							</c:if>
							<c:if test="${oaEmployee.bumen_id!=organ.organization_id }">
								<option value="${organ.organization_id }">${organ.organization_name }</option>
							</c:if>
							</c:forEach>
					</select></td>
					<td>岗位</td>
					<td><select id="gangwei_id" name="gangwei_id">
							<c:forEach items="${organizations_sub }" var="organ_sub">
							<c:if test="${oaEmployee.gangwei_id==organ.organization_id }">
								<option selected="selected" value="${organ_sub.organization_id }">${organ_sub.organization_name }</option>
							</c:if>
							<c:if test="${oaEmployee.gangwei_id!=organ.organization_id }">
								<option value="${organ_sub.organization_id }">${organ_sub.organization_name }</option>
							</c:if>
							</c:forEach>
					</select></td>
					<td>入职日期</td>
					<td><input class="layui-input" placeholder="点击选择" value="<fmt:formatDate  pattern='yyyy-MM-dd' value='${oaEmployee.ruzhi_time }' />"
						name="ruzhi_time_str" id="ruzhi_time_str"></td>
					<td rowspan="4"><img id="zhiyuan" src="${oaEmployee.employee_pic }" width="140" height="140" />
						<a href="javascript:void(0)" class="btn btn-info btn-xs">选择照片
							<input type="file" id="employee_pic_str" name="employee_pic_str"
							style="position: absolute; opacity: 0; top: 0px; left: 0px; width: 100%; height: 100%;" />
					</a></td>
				</tr>
				<tr>
					<td>姓名</td>
					<td><input type="text" id="xingming" value="${oaEmployee.xingming }" name="xingming" /></td>
					<td>性别</td>
					<td><select id="xingbie" name="xingbie">
							<option value="${oaEmployee.xingbie }">${oaEmployee.xingbie }</option>
							<option value="男">男</option>
							<option value="女">女</option>
					</select></td>
					<td>民族</td>
					<td><select id="minzu" name="minzu">
							<c:forEach items="${nations }" var="nation">
								<c:if test="${oaEmployee.minzu==nation.nation }">
								<option selected="selected" value="${nation.nation }">${nation.nation }</option>
								</c:if>
								<c:if test="${oaEmployee.minzu!=nation.nation }">
								<option value="${nation.nation }">${nation.nation }</option>
								</c:if>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td>出生日期</td>
					<td><input class="layui-input" placeholder="点击选择" value="${oaEmployee.birthday }"
						id="birthday" name="birthday"></td>
					<td>政治面貌</td>
					<td><select id="zhengzhi_mianmao" name="zhengzhi_mianmao">
							<option value="${oaEmployee.zhengzhi_mianmao }">${oaEmployee.zhengzhi_mianmao }</option>
							<option value="群众">群众</option>
							<option value="团员">团员</option>
							<option value="党员">党员</option>
					</select></td>
					<td>婚姻状况</td>
					<td><select id="hunyin_state" name="hunyin_state">
					<option value="${oaEmployee.hunyin_state }">${oaEmployee.hunyin_state }</option>
							<option value="未婚">未婚</option>
							<option value="已婚">已婚</option>
					</select></td>
				</tr>
				<tr>
					<td>最高学历</td>
					<td><select id="zuigao_xueli" name="zuigao_xueli">
					<option value="${oaEmployee.zuigao_xueli }">${oaEmployee.zuigao_xueli }</option>
							<option value="博士">博士</option>
							<option value="硕士">硕士</option>
							<option value="本科">本科</option>
							<option value="专科">专科</option>
							<option value="高中">高中</option>
					</select></td>
					<td>联系电话</td>
					<td><input type="text" id="phone" value="${oaEmployee.phone }" name="phone" /></td>
					<td>QQ</td>
					<td><input type="text" id="qq" name="qq" value="${oaEmployee.qq }" /></td>
				</tr>
				<tr>
					<td>身份证号</td>
					<td colspan="2"><input id="card_number" name="card_number" value="${oaEmployee.card_number }"
						type="text" /></td>
					<td>E-mail</td>
					<td colspan="3"><input type="text" id="mail" name="mail" value="${oaEmployee.mail }" /></td>
				</tr>
				<tr>
					<td>户籍地址</td>
					<td colspan="2"><input type="text" id="huji" value="${oaEmployee.huji }" name="huji" /></td>
					<td>转正日期</td>
					<td colspan="3"><input class="layui-input" value="<fmt:formatDate  pattern='yyyy-MM-dd' value='${oaEmployee.zhuanzheng_time }' />"
						id="zhuanzheng_time_str" name="zhuanzheng_time_str"></td>
				</tr>
				<tr>
					<td>籍贯</td>
					<td colspan="2"><input type="text" id="jiguan" value="${oaEmployee.jiguan }" name="jiguan" /></td>
					<td>现住址</td>
					<td colspan="3"><input type="text" id="now_addr" value="${oaEmployee.now_addr }"
						name="now_addr" /></td>
				</tr>
				<tr>
					<td>修改个人资料</td>
					<td colspan="7"><button class="layui-btn layui-xiugai" onclick="update_employee_info(this,${oaEmployee.oa_employee_id})" >点击修改</button></td>
				</tr>
				<tr>
					<td rowspan="${fn:length(oaEdus)+1}">教育经历</td>
					<td colspan="2">起止时间</td>
					<td colspan="2">院校</td>
					<td colspan="2">专业</td>
					<td>学历学位</td>
				</tr>
				<c:forEach items="${oaEdus }" var="edu" varStatus="vs">
				<tr>
					<td colspan="2"><input class="layui-input input_str"
						value="${edu.start_time }" id="start_time_str${vs.index+1 }" name="start_time_str_shool">--<input
						class="layui-input input_end" placeholder="截止日" id="end_time_str${vs.index+1 }" value="${edu.end_time }"
						name="end_time_str_school"></td>
					<td colspan="2"><input type="text" id="school_name_str${vs.index+1 }" value="${edu.school_name }"
						name="school_name_str" /></td>
					<td colspan="2"><input type="text" id="zhuanye_str${vs.index+1 }" value="${edu.zhuanye }" name="zhuanye_str" /></td>
					<td><input type="text" id="xueli_str${vs.index+1 }" value="${edu.xueli }" name="xueli_str" /></td>
					<td><input class="layui-btn layui-xiugai" onclick="update_employee_edu(${edu.oa_edu_id},${vs.index+1 })" type="button" value="修改" /></td>
				</tr>
				</c:forEach>
					<script>
					//修改教育经历
					function update_employee_edu(oa_edu_id,number){
						var start_time=$("#start_time_str"+number).val();
						var end_time=$("#end_time_str"+number).val();
						var school_name=$("#school_name_str"+number).val();
						var zhuanye=$("#zhuanye_str"+number).val();
						var xueli=$("#xueli_str"+number).val();
						$.post("/oa_employee/uodate_employee_edu.jr",{
							'oa_edu_id':oa_edu_id,
							'start_time':start_time,
							'end_time':end_time,
							'school_name':school_name,
							'zhuanye':zhuanye,
							'xueli':xueli
						},function(data){
							if(data==1){
								layer.msg("修改成功！");
							}else{
								layer.msg("系统发生错误！");
							}
						})
					}
					</script>
				<tr>
					<td rowspan="${fn:length(oaWorks)+1}">工作经历</td>
					<td colspan="2">起止时间</td>
					<td colspan="2">工作单位</td>
					<td>岗位</td>
					<td colspan="2">工作内容</td>
				</tr>
				<c:forEach items="${oaWorks }" var="work" varStatus="vs">
				<tr>
					<td colspan="2"><input class="layui-input input_str"
						value="${work.start_time }" id="start_time_work${vs.index+1 }" name="star_time_work">--<input
						class="layui-input input_end" value="${work.end_time }" id="end_time_work${vs.index+1 }"
						name="end_time_work"></td>
					<td colspan="2"><input type="text" id="danwei_name${vs.index+1 }" name="danwei_name"  value="${work.danwei_name }"/></td>
					<td><input type="text" id="gangwei${vs.index+1 }" name="gangwei" value="${work.gangwei }"/></td>
					<td colspan="2"><input type="text" id="gongzuo_neirong${vs.index+1 }" value="${work.gongzuo_neirong }"
						name="gongzuo_neirong" /></td>
						<td><input class="layui-btn layui-xiugai" type="button" value="修改" onclick="update_employee_work(${work.oa_work_id},${vs.index+1 })" /></td>
				</tr>
				</c:forEach>
					<script>
					//修改工作经历
					function update_employee_work(oa_work_id,number){
						var start_time=$("#start_time_work"+number).val();
						var end_time=$("#end_time_work"+number).val();
						var danwei_name=$("#danwei_name"+number).val();
						var gangwei=$("#gangwei"+number).val();
						var gongzuo_neirong=$("#gongzuo_neirong"+number).val();
						$.post("/oa_employee/uodate_employee_work.jr",{
							'oa_work_id':oa_work_id,
							'start_time':start_time,
							'end_time':end_time,
							'danwei_name':danwei_name,
							'gangwei':gangwei,
							'gongzuo_neirong':gongzuo_neirong
						},function(data){
							if(data==1){
								layer.msg("修改成功！");
							}else{
								layer.msg("系统发生错误！");
							}
						})
					}
					</script>
				<tr>
					<td rowspan="${fn:length(lianxirens_jiating)+1}">家庭成员</td>
					<td>关系</td>
					<td>姓名</td>
					<td>年龄</td>
					<td colspan="2">工作单位</td>
					<td>职务</td>
					<td>联系电话</td>
				</tr>
				<c:forEach items="${lianxirens_jiating }" varStatus="vs" var="jiating">
				<tr>
					<td><input type="text" id="guanxi_jiating${vs.index+1 }" value="${jiating.guanxi }"
						name="guanxi_jiating" /></td>
					<td><input type="text" id="name_jiating${vs.index+1 }" name="name_jiating" value="${jiating.name }" /></td>
					<td><input type="text" id="age_jiating${vs.index+1 }" name="age_jiating" value="${jiating.age }" /></td>
					<td colspan="2"><input type="text" id="danwei_jiating${vs.index+1 }" value="${jiating.danwei }"
						name="danwei_jiating" /></td>
					<td><input type="text" id="zhiwu_jiating${vs.index+1 }" value="${jiating.zhiwu }" name="zhiwu_jiating" /></td>
					<td><input type="text" id="phone_jiating${vs.index+1 }" value="${jiating.phone }"  name="phone_jiating" /></td>
					<td><input type="button" value="修改" class="layui-btn layui-xiugai" onclick="update_employee_jiating(${jiating.oa_lianxiren_id},${vs.index+1 })" /></td>
				</tr>
				</c:forEach>
					<script>
					//修改家庭联系人
					function update_employee_jiating(oa_lianxiren_id,number){
						var guanxi=$("#guanxi_jiating"+number).val();
						var name=$("#name_jiating"+number).val();
						var age=$("#age_jiating"+number).val();
						var danwei=$("#danwei_jiating"+number).val();
						var zhiwu=$("#zhiwu_jiating"+number).val();
						var phone=$("#phone_jiating"+number).val();
						$.post("/oa_employee/uodate_employee_jiating.jr",{
							'oa_lianxiren_id':oa_lianxiren_id,
							'guanxi':guanxi,
							'name':name,
							'age':age,
							'danwei':danwei,
							'phone':phone,
							'zhiwu':zhiwu
						},function(data){
							if(data==1){
								layer.msg("修改成功！");
							}else{
								layer.msg("系统发生错误！");
							}
						})
					}
					</script>
				<c:forEach items="${lianxirens_jinji }" var="jinji" varStatus="vs">
				<tr>
				<c:if test="${vs.index==0 }">
					<td rowspan="${fn:length(lianxirens_jinji)}">紧急联系人</td>
				</c:if>
					<td><input type="text" id="guanxi_jinji${vs.index+1 }" name="guanxi_jinji" value="${jinji.guanxi }"/></td>
					<td><input type="text" id="name_jinji${vs.index+1 }" name="name_jinji" value="${jinji.name }" /></td>
					<td><input type="text" id="age_jinji${vs.index+1 }" name="age_jinji" value="${jinji.age }"/></td>
					<td colspan="2"><input type="text" id="danwei_jinji${vs.index+1 }" value="${jinji.danwei }"
						name="danwei_jinji" /></td>
					<td><input type="text" id="zhiwu_jinji${vs.index+1 }" name="zhiwu_jinji" value="${jinji.zhiwu }" /></td>
					<td><input type="text" id="phone_jinji${vs.index+1 }" name="phone_jinji" value="${jinji.phone }" /></td>
					<td><input type="button" value="修改" class="layui-btn layui-xiugai" onclick="update_employee_jinji(${jinji.oa_lianxiren_id},${vs.index+1 })"  /></td>
				</tr>
			</c:forEach>
				<script>
					//修改紧急联系人
					function update_employee_jinji(oa_lianxiren_id,number){
						var guanxi=$("#guanxi_jinji"+number).val();
						var name=$("#name_jinji"+number).val();
						var age=$("#age_jinji"+number).val();
						var danwei=$("#danwei_jinji"+number).val();
						var zhiwu=$("#zhiwu_jinji"+number).val();
						var phone=$("#phone_jinji"+number).val();
						$.post("/oa_employee/uodate_employee_jiating.jr",{
							'oa_lianxiren_id':oa_lianxiren_id,
							'guanxi':guanxi,
							'name':name,
							'age':age,
							'danwei':danwei,
							'phone':phone,
							'zhiwu':zhiwu
						},function(data){
							if(data==1){
								layer.msg("修改成功！");
							}else{
								layer.msg("系统发生错误！");
							}
						})
					}
					</script>
			</table>
			</form>
		<div class="add_reset">
				<button class="layui-btn layui-btn-normal" onclick="add_employee_jingli(${oaEmployee.oa_employee_id})">添加个人经历</button>
				<button class="layui-btn layui-add" onclick="to_update_employee(${oaEmployee.oa_employee_id},${limit },${pageNumber})">刷新</button>
				<button class="layui-btn layui-btn-warm" onclick="fanhui_employee_list(${pageNumber},${limit })">返回</button>
			</div>
	</div>
</body>
<script>
//添加个人经历
function add_employee_jingli(oa_employee_id){
	layer.open({
		  type: 2,
		  title: ['添加个人经历'],
		  area: ['1100px', '700px'],
		  shadeClose: false, //点击遮罩关闭
		  content: "/oa_employee/to_add_employee_jingli.jr?oa_employee_id="+oa_employee_id
		  });
}

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
							if($("#start_time_str1").length>0){
							document.getElementById('start_time_str1').onclick = function() {
								start.elem = this;
								laydate(start);
							}
							}
							if($("#end_time_str1").length>0){
							document.getElementById('end_time_str1').onclick = function() {
								start.elem = this;
								laydate(start);
							}
							}
							if($("#start_time_str2").length>0){
						 	document.getElementById('start_time_str2').onclick = function() {
								start.elem = this;
								laydate(start);
							}
							}
						 	if($("#end_time_str2").length>0){
							document.getElementById('end_time_str2').onclick = function() {
								start.elem = this;
								laydate(start);
							}
						 	}
						 	if($("#start_time_str3").length>0){
							document.getElementById('start_time_str3').onclick = function() {
								start.elem = this;
								laydate(start);
							}
						 	}
						 	if($("#end_time_str3").length>0){
							document.getElementById('end_time_str3').onclick = function() {
								start.elem = this;
								laydate(start);
							}
						 	}
						 	if($("#start_time_str4").length>0){
							document.getElementById('start_time_str4').onclick = function() {
								start.elem = this;
								laydate(start);
							}
						 	}
						 	if($("#end_time_str4").length>0){
							document.getElementById('end_time_str4').onclick = function() {
								start.elem = this;
								laydate(start);
							}
						 	}
						 	if($("#start_time_str5").length>0){
							document.getElementById('start_time_str5').onclick = function() {
								start.elem = this;
								laydate(start);
							}
						 	}
						 	if($("#end_time_str5").length>0){
							document.getElementById('end_time_str5').onclick = function() {
								start.elem = this;
								laydate(start);
							}
						 	}
						 	if($("#start_time_str6").length>0){
							document.getElementById('start_time_str6').onclick = function() {
								start.elem = this;
								laydate(start);
							}
						 	}
						 	if($("#end_time_str6").length>0){
							document.getElementById('end_time_str6').onclick = function() {
								start.elem = this;
								laydate(start);
							} 
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