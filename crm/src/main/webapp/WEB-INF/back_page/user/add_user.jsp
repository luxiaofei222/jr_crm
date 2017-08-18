<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
<link rel="stylesheet" href="/css/school/back/common/bootstrap.min.css" />
<link rel="stylesheet" href="/css/school/back/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="/css/school/back/common/tankuang.css" />
<link rel="stylesheet" type="text/css" href="/css/school/back/layui/css/layui.css">
	<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<script>
//检测员工账号
function check_user_phone(){
	var bol = false;
	var user_phone=$("#user_phone").val();
	if(user_phone!=null&&user_phone!=""){
		$.ajax({
			type : "POST",
			url : "/back_user/check_phone.jr?user_phone="
					+ user_phone,
			async : false,
			success : function(data) {
				if (data == 1) {
					//手机号可以用
					$("#error_loginname").html("<i class='fa fa-check'></i>");
					bol = true;
				} else {
					//手机号重复
					layer.msg('你输入的手机号已存在！');
					bol = false;
				}
			}
		});
	}else{
		layer.msg('手机号不能是空的！');
		bol = false;
	}
	return bol;
}

//检测姓名是否为空
function check_user_nickname(){
	var user_nickname=$("#user_nickname").val();
	if(user_nickname!=null&&user_nickname!=""){
		$("#error_name").html("<i class='fa fa-check'></i>");
		return true;
	}else{
		layer.msg('请输入用户昵称！');
		return false;
	}
}
//检测密码是否为空
function check_employee_pass(){
	var user_password=$("#user_password").val();
	if(user_password!=null&&user_password!=""){
		$("#error_logipass").html("<i class='fa fa-check'></i>");
		return true;
	}else{
		layer.msg('请输入密码！');
		return false;
	}
}
//添加角色
function add_employee(){
	 if (check_user_nickname()&&check_user_phone()&&check_employee_pass()) {
		 $("#myform").ajaxSubmit({
			type : 'POST',
			url : "/back_user/save_user.jr",
			success : function(data) {
				if(data==1){
					//添加数据成功，关闭弹出窗之前，刷新列表页面的数据
                    tanchuang('恭喜您，添加成功');
                    $('#myform')[0].reset();
				}else{
					tanchuang('很遗憾，添加失败');
				}
			},
			error : function(XmlHttpRequest, textStatus, errorThrown) {
				tanchuang('很遗憾，添加失败');
			}
		});
	}
}
//获取部门下的岗位
function change_organ(){
	var get_sun_organ=$("#get_sun_organ").val();
	$.post("/back_user/get_sun_oragn.jr",{
		'organ_id':get_sun_organ
	},function(data){
		$("#organization_id").html(data);
	})
}
</script>
<style>
.form-horizontal .form-group .wz_right{
	text-align:right;
}
</style>
<div style="padding: 20px;">
	<form enctype="multipart/form-data" id="myform" class="form-horizontal">
    <div class="form-group">
      <label for="inputEmail1" class="col-xs-2 control-label wz_right">昵称：</label>
      <div class="col-xs-9">
        <input type="text" class="form-control" onblur="check_user_nickname()" name="user_nickname" id="user_nickname" placeholder="姓名">
      </div>
      	<div id="error_name"></div>
    </div>
    <div class="form-group">
      <label for="inputPassword1" class="col-xs-2 control-label wz_right">手机号：</label>
      <div class="col-xs-9">
        <input type="text" class="form-control" onblur="check_user_phone()" name="user_phone" id="user_phone" placeholder="账号">
      </div>
      <div id="error_loginname"></div>
    </div>
    <div class="form-group">
      <label for="inputPassword1" class="col-xs-2 control-label wz_right">密码：</label>
      <div class="col-xs-9">
        <input type="password" onblur="check_employee_pass()" class="form-control" name="user_password" id="user_password" placeholder="密码">
      </div>
      <div id="error_logipass"></div>
    </div>
    <div class="form-group">
      <label class="col-xs-2 control-label wz_right">性别：</label>
      <div class="col-xs-9">
	      <select data-toggle="select" id="user_sex" name="user_sex" class="form-control select select-primary">
	          <option value="男">男</option>
	           <option value="女">女</option>
	      </select>
  	</div>
    </div>
    <div class="form-group">
       <label for="inputPassword1" class="col-xs-2 control-label wz_right">&nbsp;</label>
      <div class="col-xs-9">
        <button type="button" onclick="add_employee()" class="btn btn-success btn-xm">提交</button>
        <button type="reset" class="btn btn-danger btn-xm">重置</button> 
      </div> 
    </div>
	</form>	
</div>