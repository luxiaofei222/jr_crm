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
function check_login_name(){
	var bol = false;
	var login_name=$("#login_name").val();
	if(login_name!=null&&login_name!=""){
		$.ajax({
			type : "POST",
			url : "/back_employee/check_employee.jr?login_name="
					+ login_name,
			async : false,
			success : function(data) {
				if (data == 2) {
					//角色名称可用
					$("#error_loginname").html("<i class='fa fa-check'></i>");
					bol = true;
				} else {
					//角色名重复
					layer.msg('你输入的账号已存在！');
					bol = false;
				}
			}
		});
	}else{
		layer.msg('账号不能是空的！');
		bol = false;
	}
	return bol;
}

//检测姓名是否为空
function check_employee_name(){
	var employee_name=$("#employee_name").val();
	if(employee_name!=null&&employee_name!=""){
		$("#error_name").html("<i class='fa fa-check'></i>");
		return true;
	}else{
		layer.msg('请输入员工姓名！');
		return false;
	}
}
//检测密码是否为空
function check_employee_pass(){
	var login_password=$("#login_password").val();
	if(login_password!=null&&login_password!=""){
		$("#error_logipass").html("<i class='fa fa-check'></i>");
		return true;
	}else{
		layer.msg('请输入密码！');
		return false;
	}
}
//添加员工信息
function add_employee(){
	 var organization_id=$("#organization_id").val();
	 if (check_employee_name()&&check_login_name()&&check_employee_pass()) {
		 if(organization_id!=0){
		 $("#myform").ajaxSubmit({
			type : 'POST',
			url : "/back_employee/save_employee.jr",
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
	}else{
		 layer.msg('请选择岗位！');
	 } 
	}
}
//获取部门下的岗位
function change_organ(){
	var get_sun_organ=$("#get_sun_organ").val();
	$.post("/back_employee/get_sun_oragn.jr",{
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
      <label for="inputEmail1" class="col-xs-2 control-label wz_right">姓名：</label>
      <div class="col-xs-9">
        <input type="text" class="form-control" onblur="check_employee_name()" name="employee_name" id="employee_name" placeholder="姓名">
      </div>
      	<div id="error_name"></div>
    </div>
    <div class="form-group">
      <label for="inputPassword1" class="col-xs-2 control-label wz_right">账号：</label>
      <div class="col-xs-9">
        <input type="text" class="form-control" onblur="check_login_name()" name="login_name" id="login_name" placeholder="账号">
      </div>
      <div id="error_loginname"></div>
    </div>
    <div class="form-group">
      <label for="inputPassword1" class="col-xs-2 control-label wz_right">密码：</label>
      <div class="col-xs-9">
        <input type="password" onblur="check_employee_pass()" class="form-control" name="login_password" id="login_password" placeholder="密码">
      </div>
      <div id="error_logipass"></div>
    </div>
    <div class="form-group">
      <label class="col-xs-2 control-label wz_right">所属角色：</label>
      <div class="col-xs-9">
	      <select data-toggle="select" id="role_id" name="role_id" class="form-control select select-primary">
	         <c:forEach items="${roles }" var="role">
	          <option value="${role.role_id }">${role.role_name }</option>
	        </c:forEach>
	      </select>
  	</div>
    </div>
    <div class="form-group">
      <label class="col-xs-2 control-label wz_right">所属部门：</label>
      <div class="col-xs-9">
      <select data-toggle="select" id="get_sun_organ" onchange="change_organ()" class="form-control select select-primary">
        <option value="0">请选择部门</option>
       <c:forEach items="${organizations }" var="organ">
		<option value="${organ.organization_id }">${organ.organization_name }</option>
		</c:forEach>
      </select>
    </div>
    </div>
        <div class="form-group">
      <label class="col-xs-2 control-label wz_right">岗位：</label>
      <div class="col-xs-9">
      <select data-toggle="select" id="organization_id" name="organization_id" class="form-control select select-primary">
        <option value="0" >请选择所属岗位</option>
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