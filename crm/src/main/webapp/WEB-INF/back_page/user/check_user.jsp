<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
<link rel="stylesheet" href="/css/school/back/common/bootstrap.min.css" />
<link rel="stylesheet" href="/css/school/back/common/flat-ui.min.css" />
<link rel="stylesheet"
	href="/css/school/back/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="/css/school/back/common/tankuang.css" />
<link rel="stylesheet" type="text/css"
	href="/css/school/back/layui/css/layui.css">
<script src="/js/school/back/common/flat-ui.min.js"></script>
<script src="/js/school/back/common/application.js"></script>
<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<style type="text/css">
.form-horizontal .form-group .left_wz {
	text-align: left;
	color: #9C9C9C;
}

.form-horizontal .form-group.nobottom {
	margin-bottom: 0px !important;
}

.head_img {
    width: 76px; 
    height: 76px; 
    border-radius: 38px; 
    margin:0 auto; 
    display:block;
}
.nicheng {
    text-align:center;
}
.form-horizontal .form-group.jborder {
	border-bottom: 1px solid #ccc;
}

.course {
	width: 960px;
	margin: 25px auto;
	border: #06c1ae solid 2px;
}

h3 {
	text-align: center;
	line-height: 35px;
	font-weight: bold;
}

table tr td {
	border: #06c1ae solid 2px;
	text-align: center;
	line-height: 45px;
	color: #313131;
	width: 217px;
}

table tr.title td {
	font-weight: bold;
	line-height: 35px;
}

table input {
	width: 80px;
	height: 30px;
	border: none;
	border-radius: 3px;
	text-align: center;
	line-height: 30px;
	color: #fff;
	background-color: #415b76;
	cursor: pointer;
}

table tr td p {
	line-height: 25px;
	font-size: 14px;
}

.zw {
    text-align: center;
    color:orange;
}
</style>
<script>
$(function(){
	layui.use('layer', function(){
	  	var layer = layui.layer;
		  //点击添加弹出层事件
	});  
});
	//删除用户的课程
	function delete_my_course(obj, video_id, user_id) {
		$(obj).attr('disabled', "true");
		$.post("/back_user/delete_mycourse.jr", {
			'video_id' : video_id,
			'user_id' : user_id
		}, function(data) {
			location.reload();
		})
	}
	//设置失效时间
	function set_daoqi_time(my_course_id){
		 layer.open({
			  type: 2,
			  title: ['设置失效时间'],
			  area: ['500px', '300px'],
			  shadeClose: true, //点击遮罩关闭
			  content: "/back_user/to_set_daoqi_time.jr?my_course_id="+my_course_id
			  });
	}
</script>
<div style="padding: 20px;">
	<div class="form-group nobottom" style="margin-top: 15px;">
	  <img class="head_img" src="${user.user_pic }" />
	</div>
	<div style="clear:both;"></div>
	<div class="form-group nobottom">
		<!-- <label class="col-xs-2 control-label">昵称：</label> -->
		<p class="nicheng">${user.user_nickname }</p>
	</div>
	<div style="clear:both;"></div>
	<div class="form-group nobottom jborder">
		<label class="col-xs-2 control-label">性别：</label> <label
			class="col-xs-4 control-label left_wz">${user.user_sex }</label> <label
			class="col-xs-2 control-label">出生年月：</label> <label
			class="col-xs-4 control-label left_wz"> <c:if
				test="${empty user.user_birthday }">
				<span style="color: orange;">暂无</span>
			</c:if> <fmt:formatDate value="${user.user_birthday }" /></label>
	</div>
	<div style="clear:both;"></div>
	<div class="form-group nobottom jborder">
		<label class="col-xs-2 control-label">注册时间：</label> <label
			class="col-xs-4 control-label left_wz"><fmt:formatDate
				type="both" value="${user.user_time }" /></label> <label
			class="col-xs-2 control-label">电话：</label> <label
			class="col-xs-4 control-label left_wz"> <c:if
				test="${empty user.user_phone }">
				<span style="color: orange;">暂无</span>
			</c:if> ${user.user_phone }
		</label>
	</div>
	<div style="clear:both;"></div>
	<div class="form-group nobottom jborder">
		<label class="col-xs-2 control-label">QQ：</label> <label
			class="col-xs-4 control-label left_wz"> <c:if
				test="${empty user.user_qq }">
				<span style="color: orange;">暂无</span>
			</c:if> ${user.user_qq }
		</label> <label class="col-xs-2 control-label">邮箱：</label> <label
			class="col-xs-4 control-label left_wz"> <c:if
				test="${empty user.user_mail }">
				<span style="color: orange;">暂无</span>
			</c:if> ${user.user_mail }
		</label>
	</div>
	<div style="clear:both;"></div>
	<div class="form-group nobottom jborder">
		<label class="col-xs-2 control-label">所在地：</label> <label
			class="col-xs-4 control-label left_wz"> <c:if
				test="${empty user.user_province && empty user.user_city}">
				<span style="color: orange;">暂无</span>
			</c:if> ${user.user_province }&nbsp;${user.user_city }
		</label> <label class="col-xs-2 control-label">职业：</label> <label
			class="col-xs-4 control-label left_wz"> <c:if
				test="${empty user.user_zhiye }">
				<span style="color: orange;">暂无</span>
			</c:if> ${user.user_zhiye }
		</label>
	</div>
	<div style="clear:both;"></div>
	<div class="form-group nobottom jborder">
		<label class="col-xs-2 control-label">公司名称：</label> <label
			class="col-xs-4 control-label left_wz"> <c:if
				test="${empty user.user_bus }">
				<span style="color: orange;">暂无</span>
			</c:if> ${user.user_bus }
		</label>
		<label class="col-xs-2 control-label">相关业务员：</label> <label
			class="col-xs-4 control-label left_wz"> <c:if
				test="${empty user.employee_name }">
				<span style="color: orange;">暂无</span>
			</c:if> ${user.employee_name }
		</label>
	</div>
	<div style="clear:both;"></div>
	<div class="form-group nobottom jborder">
		<label class="col-xs-2 control-label">消费总金额：</label> <label
			class="col-xs-4 control-label left_wz">${zongjin }元</label>
	</div>
	<div style="clear:both;"></div>
	<div class="form-group nobottom">
		<c:if test="${empty courses }">
			<p class="zw">暂无课程</p>
		</c:if>
		<script>
		function mycourse_note(my_course_id,note){
			if(note==null||note==""){
				note="暂无任何操作！";
			}
			layer.tips(note, "#note_tip"+my_course_id, {
				tips : [2,'#59BBC0' ],
				time : 3000
			});
		}
		</script>
		<c:if test="${not empty courses }">
			<table class="course" border="1" cellspacing="1" cellpadding="0">
				<tr>
					<td colspan="6"><h3>全部课程</h3></td>
				</tr>
				<tr class="title">
					<td>课程名称</td>
					<td>剩余天数</td>
					<td>到期时间</td>
					<td>操作人员</td>
					<td>添加日期</td>
					<td>操作</td>
				</tr>
				<c:forEach items="${courses }" var="mycouse">
				<tr>
					<td><p id="note_tip${mycouse.my_course_id }" onmouseover="mycourse_note(${mycouse.my_course_id },'${mycouse.note }')">${mycouse.courseVideo.video_name }</p></td>
					<td>
					<c:if test="${empty mycouse.daoqi_time }">
						暂无
					</c:if>
					<c:if test="${not empty mycouse.daoqi_time }">
						${mycouse.tianshu }天
					</c:if>
					</td>
					<td>
					<c:if test="${empty mycouse.daoqi_time }">
						暂无
					</c:if>
					<c:if test="${not empty mycouse.daoqi_time }">
					<fmt:formatDate value="${mycouse.daoqi_time }"/>
					</c:if>
					</td>
					<td>
					<c:if test="${empty mycouse.employee_name }">
						暂无
					</c:if>
					<c:if test="${not empty mycouse.employee_name }">
					${mycouse.employee_name }
					</c:if>
					</td>
					<td>
					<fmt:formatDate value="${mycouse.my_course_time }"/>
					</td>
					<td><input type="button" onclick="set_daoqi_time(${mycouse.my_course_id })" value="到期时间" /><input type="button"
						value="解除绑定"  onclick="delete_my_course(this,${mycouse.video_id },${mycouse.user_id })" style="background-color: #e74c3c; margin-left: 10px;" /></td>
				</tr>
				</c:forEach>
			</table>
		</c:if>
	</div>
</div>
