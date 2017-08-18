<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/css/course_sign/reset.css" />
<link rel="stylesheet" type="text/css" href="/css/employee/iconfont.css" />
<link rel="stylesheet" type="text/css"
	href="/font-awesome-4.6.3/css/font-awesome.min.css" />
<link rel="stylesheet" type="text/css" href="/css/employee/update.css" />
<link rel="stylesheet" type="text/css" href="/css/employee/top.css" />
<link rel="stylesheet" type="text/css"
	href="/css/school/back/layui/css/layui.css">
<script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script type="text/javascript" src="/js/employeecenter/cropbox.js"></script>
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<title>修改个人资料</title>
<script>
//退出系统
function logout_jr(){
	layer.confirm("提示：您好，确定要退出本系统吗？",function(){
		layer.closeAll('dialog');
		$.post("/logout.jr",function(data){
			if(data==1){
				location.href=("/login.jr");
			}
		})
		})
}
</script>
</head>
<body>
	<div class="zhezhao">
		<div class="update_dialog">
			<div class="img_top">
				<span class="title left">修改头像</span> <span class="close right"><i
					class="icon iconfont icon-icon4"></i></span>
			</div>
			<div class="clear"></div>
			<div class="container">
				<div class="imageBox">
					<div class="thumbBox"></div>
					<div class="spinner" style="display: none">Loading...</div>
				</div>
				<form enctype="multipart/form-data" id="myform"
					class="form-horizontal left">
					<div class="cropped">
						<span class="title">预览头像</span>
					</div>
					<div class="action">
						<div class="new-contentarea tc">
							<a href="javascript:void(0)" class="upload-img left"> <label
								for="upload-file">浏览图像</label> <input type="file"
								name="upload_file" id="upload-file" />
							</a> <input type="button" id="btnCrop" class="Btnsty_peyton left"
								value="裁切">
						</div>
					</div>
				</form>
				<button class="cancel right" onclick="close_layer()">取消</button>
				<button class="upload right"
					onclick="shangchuan_touxiang(this,${employee.employee_id})">确定</button>
			</div>
		</div>
	</div>
	<div class="top">
		<div class="header">
			<span class="left"> <c:if
					test="${not empty employee.employee_pic }">
					<img src="${employee.employee_pic }" class="left" />
				</c:if> <c:if test="${empty employee.employee_pic }">
					<img src="/images/employee/picture.jpg" class="left" />
				</c:if>
				<div class="left">
					<p>${employee.employee_name }</p>
					 <c:if test="${empty employee.employee_trades }">
        <span>这个人很懒，还没有设置...</span>
     </c:if> <c:if test="${not empty employee.employee_trades }">
      <span title="${employee.employee_trades}">${employee.employee_trades}</span>
     </c:if>
					
				</div>
			</span> <span class="right">
				<ul class="left">
					<li onclick="location.href='/em_log/get_back_log.jr?employee_id=${employee.employee_id}'"><img src="/images/personal/li-1.png" />
					<p>日志</p></li>
					<li onclick="location.href='/back_task/get_back_task.jr?employee_id=${employee.employee_id}'"><img src="/images/personal/li-2.png" />
					<p>任务</p></li>
					<li  onclick="location.href='/back_leave/get_back_leave.jr?employee_id=${employee.employee_id}'"><img src="/images/personal/li-3.png" />
					<p>申请</p></li>
					<li onclick="location.href='/center/to_get_employee.jr?employee_id=${employee.employee_id}'" style="color: #f39801"><img src="/images/personal/li-4.png" />
					<p>修改资料</p></li>
					<li onclick="location.href='/admin.jr'"><img src="/images/personal/li-5.png" />
					<p>进入系统</p></li>
				</ul>
				<div class="left">
					<span class="left">|</span><i
						class="left icon iconfont icon-shouyeshouye" onclick="location.href='/center/get_employee_center.jr'" ></i><i
						class="left icon iconfont icon-guanbi" onclick="logout_jr()"></i>
				</div>
			</span>
		</div>
		<div class="clear"></div>
	</div>
	<div class="content">
		<div class="side_nav left">
			<ul>
				<li class="active"><i class="icon iconfont icon-jibenziliao"></i><span>基本资料</span></li>
				<li><i class="icon iconfont icon-suo"></i><span>修改密码</span></li>
			</ul>
		</div>
		<div class="side_cont left">
			<div class="cont jiben_ziliao" style="display: block;">
				<div class="ziliao left">
					<form enctype="multipart/form-data" id="employeeform"
						class="form-horizontal">
						<ul>
							<li><label>用户名：</label> <input type="text"
								value="${employee.employee_name }" disabled="disabled" /></li>
							<li class="li_sex"><label>性别：</label> <c:if
									test="${employee.employee_sex=='男' }">
									<input type="radio" id="radio-1-1" value="男"
										name="employee_sex" class="regular-radio" checked />
									<label for="radio-1-1" class="nan"></label>
									<em class="nan">男生</em>
									<input type="radio" id="radio-1-2" value="女"
										name="employee_sex" class="regular-radio" />
									<label for="radio-1-2" class="nv"></label>
									<em class="nv">女生</em>
								</c:if> <c:if test="${employee.employee_sex=='女' }">
									<input type="radio" id="radio-1-1" value="男"
										name="employee_sex" class="regular-radio" />
									<label for="radio-1-1" class="nan"></label>
									<em class="nan">男生</em>
									<input type="radio" id="radio-1-2" value="女"
										name="employee_sex" class="regular-radio" checked />
									<label for="radio-1-2" class="nv"></label>
									<em class="nv">女生</em>
								</c:if></li>
							<li><label>个性签名：</label> <input type="text"
								name="employee_trades" value="${employee.employee_trades }" /></li>
							<li><label>联系电话：</label> <input type="text"
								name="employee_phone" value="${employee.employee_phone }" /></li>
							<li><label>QQ：</label> <input type="text"
								value="${employee.employee_qq }" name="employee_qq" /></li>
							<li><label>邮箱：</label> <input type="text"
								name="employee_mail" value="${employee.employee_mail }" /></li>
						</ul>
					</form>
					<input type="button"
						onclick="update_employee_info(this,${employee.employee_id })"
						value="确定" /> <a href="/center/get_employee_center.jr">取消</a>
				</div>

				<div class="update_img left">
					<c:if test="${not empty employee.employee_pic }">
						<img src="${employee.employee_pic }" />
					</c:if>
					<c:if test="${empty employee.employee_pic }">
						<img src="/images/employee/picture.jpg" />
					</c:if>
					<input type="button" value="修改头像" id="update" />
				</div>
				<div class="clear"></div>
			</div>
			<div class="cont xiugai_mima">
				<form>
					<div class="error">原密码不正确</div>
					<ul>
						<li><label>请输入原密码：</label> <input type="password"
							onblur="check_password(${employee.employee_id})"
							id="old_password" /></li>
						<li><label>请输入新密码：</label> <input type="password"
							onblur="check_employee_pass()" id="new_password" /></li>
						<li><label>请再次输入新密码：</label> <input type="password"
							id="confirm_password" onblur="confirm_employee_pass()" /></li>
					</ul>
					<input type="button"
						onclick="update_pass_employee(this,${employee.employee_id})"
						value="确定" /> <a href="/center/get_employee_center.jr">取消</a>
				</form>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
  $(function(){
	  $(".side_nav ul li").click(function(){
		  var index = $(this).index();
		  $(".cont").eq(index).css("display","block").siblings().css("display","none");
		  $(".side_nav ul li").eq(index).addClass("active").siblings().removeClass("active");
		  })
	  $("#update").click(function(){
		  $(".zhezhao").show(1000);
		  })
	  $(".close").click(function(){
		  $(this).parent().parent().parent(".zhezhao").hide();
		  })
	  })
</script>
<script type="text/javascript">
var img="";
//关闭弹窗
function close_layer(){
	$(".zhezhao").hide();
}
$(window).load(function() {
	var options =
	{
		thumbBox: '.thumbBox',
		spinner: '.spinner',
		imgSrc: '/images/school/front/person/avatar.png'
	}
	var cropper = $('.imageBox').cropbox(options);
	$('#upload-file').on('change', function(){
		var reader = new FileReader();
		reader.onload = function(e) {
			options.imgSrc = e.target.result;
			cropper = $('.imageBox').cropbox(options);
		}
		reader.readAsDataURL(this.files[0]);
		this.files = [];
	})
	$('#btnCrop').on('click', function(){
		img = cropper.getDataURL();
		$('.cropped').html('<span class="title">预览头像</span>');
		$('.cropped').append('<img src="'+img+'" align="absmiddle" style="width:60px;margin-top:75px;border-radius:60px;box-shadow:0px 0px 12px #7E7E7E;" ><p style="margin:10px 0px;color:#999;">60px*60px</p>');
		$('.cropped').append('<img src="'+img+'" align="absmiddle" style="width:109px;margin-top:25px;border-radius:109px;box-shadow:0px 0px 12px #7E7E7E;"><p style="margin:10px 0px;color:#999;">109px*109px</p>');
	})
});
//修改头像
function shangchuan_touxiang(obj,employee_id){
	$(obj).attr({"disabled":"disabled"});
	$(obj).html("上传中");
	 $("#myform").ajaxSubmit({
			type : 'POST',
			data:{'employee_id':employee_id,'headpic':img},
			url : "/center/update_employee_pic.jr",
			success : function(data) {
				if(data==1){
					tanchuang("提示：恭喜您，修改头像成功！");
					$(obj).html("上传头像");
					$(obj).removeAttr("disabled");
					$(".spinner").hide();
				}else if(data==2){
					layer.msg("提示：请先点击剪切按钮，对图片进行剪切！");
					$(obj).html("上传头像");
					$(obj).removeAttr("disabled");
				}else{
					 layer.msg("提示：很遗憾，修改头像失败！");
					 $(obj).html("上传头像");
						$(obj).removeAttr("disabled");
				}
			},
			error : function(XmlHttpRequest, textStatus, errorThrown) {
				 layer.msg("修改头像失败！");
				 $(obj).html("上传头像");
					$(obj).removeAttr("disabled");
			}
		});
}
//修改个人资料
function update_employee_info(obj,employee_id){
	$(obj).attr("disabled","disabled");
	$(obj).val("提交中");
	$("#employeeform").ajaxSubmit({
		type : 'POST',
		url : "/center/update_employee.jr?employee_id="+employee_id,
		success : function(data) {
			if(data==1){
				layer.msg("修改成功"); 
				$(obj).removeAttr("disabled");
				$(obj).val("确定");
			}
		},
		error : function(XmlHttpRequest, textStatus, errorThrown) {
			layer.msg("修改失败"); 
			$(obj).removeAttr("disabled");
			$(obj).val("确定");
		}
	});
}
//检查密码
function check_password(employee_id){
	var bol = false;
	var old_password=$("#old_password").val();
	$.ajax({
		type : "POST",
		url : "/center/check_old_password.jr",
		data:{'employee_id':employee_id,
			'pass':old_password},
		async : false,
		success : function(data) {
			if(data==1){
				bol = true;
			}else{
				layer.msg("输入的密码不匹配！");
				bol = false;
			}
		}
	});
	return bol;
}
//检测新密码是否为空
function check_employee_pass(){
	var login_password=$("#new_password").val();
	if(login_password!=null&&login_password!=""){
		return true;
	}else{
		layer.msg('请输入新密码！');
		return false;
	}
}

//检测两次密码是否匹配
function confirm_employee_pass(){
	var login_password=$("#new_password").val();
	var confirm_password=$("#confirm_password").val();
	if(login_password==confirm_password){
		return true;
	}else{
		layer.msg('两次密码输入不一致！');
		return false;
	}
}
//修改密码
function update_pass_employee(obj,employee_id){
	var login_password=$("#new_password").val();
	if(check_password(employee_id)&&check_employee_pass()&&confirm_employee_pass()){
		$(obj).attr("disabled","disabled");
		$(obj).val("提交中");
		$.post("/center/update_employee.jr",{
			'employee_id':employee_id,
			'login_password':login_password
		},function(data){
			if(data==1){
				$(obj).removeAttr("disabled");
				$(obj).val("确定");
				layer.msg("修改成功！");
			}else{
				$(obj).removeAttr("disabled");
				$(obj).val("确定");
				layer.msg("修改失败！");
			}
		})
	}
}
</script>
</html>