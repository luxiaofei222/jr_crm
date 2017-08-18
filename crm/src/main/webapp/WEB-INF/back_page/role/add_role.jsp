<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
<link rel="stylesheet" href="/css/school/back/common/bootstrap.min.css" />
<link rel="stylesheet" href="/css/school/back/common/flat-ui.min.css" />
<link rel="stylesheet" href="/css/school/back/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="/css/school/back/common/tankuang.css" />
<link rel="stylesheet" type="text/css" href="/css/school/back/layui/css/layui.css">
<script src="/js/school/back/common/flat-ui.min.js"></script>
<script src="/js/school/back/common/application.js"></script>
	<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<script>
//检测角色名称
function check_role(){
	var bol = false;
	var role_name=$("#role_name").val();
	if(role_name!=null&&role_name!=""){
		$.ajax({
			type : "POST",
			url : "/back_role/check_role.jr?role_name="
					+ role_name,
			async : false,
			success : function(data) {
				if (data == 2) {
					//角色名称可用
					$("#panduan1").html("<i class='fa fa-check'></i>");
					bol = true;
				} else {
					//角色名重复
					layer.msg('你输入的角色名称已存在！');
					bol = false;
				}
			}
		});
	}else{
		layer.msg('请输入角色名称！');
		bol = false;
	}
	return bol;
}

//检测角色描述是否为空
function check_role_dis(){
	var role_dis=$("#role_dis").val();
	if(role_dis!=null&&role_dis!=""){
		$("#panduan2").html("<i class='fa fa-check'></i>");
		return true;
	}else{
		layer.msg('请输入角色描述！');
		return false;
	}
}
//添加角色
function add_role(){
	 if (check_role()&&check_role_dis()) {
		$("#myform").ajaxSubmit({
			type : 'POST',
			url : "/back_role/save_role.jr",
			success : function(data) {
				if(data==1){
					//添加数据成功，关闭弹出窗之前，刷新列表页面的数据
                    tanchuang('恭喜您，添加成功');
                    $.post("/back_role/get_role_main.jr",{
						'pageNumber' : 1,
						'limit' : 8
					},function(data){
						$('#myform')[0].reset();
					})
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
</script>
<div style="padding: 20px;">
	<form id="myform" class="form-horizontal">
		<div class="form-group">
			<label for="inputEmail1" class="col-xs-3 control-label">角色名称：</label>
			<div class="col-xs-8">
				<input type="text" name="role_name" onblur="check_role()" class="form-control" id="role_name"
					placeholder="角色名称">
			</div>
			<div id="panduan1"></div>
		</div>
		<div class="form-group">
			<label for="inputPassword1" class="col-xs-3 control-label">角色描述：</label>
			<div class="col-xs-8">
				<input type="text" onblur="check_role_dis()" class="form-control" name="role_dis" id="role_dis"
					placeholder="角色描述">
			</div>
			<div id="panduan2"></div>
		</div>
		<div class="form-group">
			<label for="inputPassword1" class="col-xs-3 control-label">&nbsp;</label>
			<div class="col-xs-8">
				<button type="button" onclick="add_role()" class="btn btn-success btn-xm">提交</button>
				<button type="reset" class="btn btn-danger btn-xm">重置</button>
			</div>
		</div>
	</form>
</div>