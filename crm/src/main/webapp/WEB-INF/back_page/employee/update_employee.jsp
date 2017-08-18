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
//添加角色
function update_employee(employee_id){
	 if (check_employee_name()) {
		 $("#myform").ajaxSubmit({
			type : 'POST',
			url : "/back_employee/update_employee.jr?employee_id="+employee_id,
			success : function(data) {
				if(data==1){
					//添加数据成功，关闭弹出窗之前，刷新列表页面的数据
                    tanchuang('恭喜您，修改成功');
				}else{
					tanchuang('很遗憾，修改失败');
				}
			},
			error : function(XmlHttpRequest, textStatus, errorThrown) {
				tanchuang('很遗憾，修改失败');
			}
		});
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
      <label for="inputPassword1" class="col-xs-6 control-label wz_right" style="font-size:20px;color:#7b7b7b;">要修改的账号：</label>
      <label class="col-xs-6 control-label" style="font-size:20px;color:#7b7b7b;">
        ${employee.login_name }
      </label>
      <div id="error_loginname"></div>
    </div>
    <div class="form-group">
      <label for="inputEmail1" class="col-xs-2 control-label wz_right">姓名：</label>
      <div class="col-xs-9">
        <input type="text" class="form-control" value="  ${employee.employee_name }" onblur="check_employee_name()" name="employee_name" id="employee_name" placeholder="姓名">
      </div>
      	<div id="error_name"></div>
    </div>
    <div class="form-group">
      <label class="col-xs-2 control-label wz_right">所属角色：</label>
      <div class="col-xs-9">
	      <select data-toggle="select" id="role_id" name="role_id" class="form-control select select-primary">
	         <option value="${employee.role.role_id }">${employee.role.role_name }</option>
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
       <c:forEach items="${organizations }" var="organ">
       <c:if test="${employee.organization.parent_id== organ.organization_id}">
		<option selected="selected" value="${organ.organization_id }">${organ.organization_name }</option>
		</c:if>
		 <c:if test="${employee.organization.parent_id!= organ.organization_id}">
		<option value="${organ.organization_id }">${organ.organization_name }</option>
		</c:if>
		</c:forEach>
      </select>
    </div>
    </div>
        <div class="form-group">
      <label class="col-xs-2 control-label wz_right">岗位：</label>
      <div class="col-xs-9">
      <select data-toggle="select" id="organization_id" name="organization_id" class="form-control select select-primary">
         <option value="${employee.organization.organization_id }">${employee.organization.organization_name }</option>
      </select>
    </div>
    </div>
    <div class="form-group">
       <label for="inputPassword1" class="col-xs-2 control-label wz_right">&nbsp;</label>
      <div class="col-xs-9">
        <button type="button" onclick="update_employee(${employee.employee_id})" class="btn btn-success btn-xm">提交</button>
        <button type="reset" class="btn btn-danger btn-xm">重置</button> 
      </div> 
    </div>
	</form>	
</div>