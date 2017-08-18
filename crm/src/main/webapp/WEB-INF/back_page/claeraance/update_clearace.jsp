<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
<link rel="stylesheet" href="/css/school/back/common/bootstrap.min.css" />
<link rel="stylesheet"
	href="/css/school/back/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css"
	href="/css/school/back/layui/css/layui.css">
<link rel="stylesheet" href="/css/school/back/common/tankuang.css" />
<script src="/js/school/back/common/bootstrap.min.js"
	type="text/javascript"></script>
<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script type="text/javascript"
	src="/css/school/back/layui/lay/modules/laydate.js"></script>
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<title>修改通关方案</title>
</head>
<style type="text/css">
.form-group .right_wz{
		text-align:right;
}
</style>
<script>
	//关闭弹窗
	function close_layer() {
		var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
		parent.layer.close(index);
	}
	//获取二级分类
	function get_sub_course() {
		var course_parent_id = $("#course_parent_id").val();
		$.post("/back_user/get_sub_course.jr", {
			'course_id' : course_parent_id
		}, function(data) {
			$("#course_id").html(data);
		})
	}
	//修改通关方案
	function update_clearance(clearance_id) {
		if(check_clearance_price()&&check_new_price()){
			$("#myform").ajaxSubmit({
				type : 'POST',
				url : "/back_clearance/update_clearance.jr?clearance_id="+clearance_id,
				success : function(data) {
					if (data == 1) {
						tanchuang("提示：恭喜您，修改通关方案成功！");
					} else if (data == 2) {
						layer.msg("提示：该课程的通关方案数量超过规定数量，您可以修改或者删除之前的方案！");
					} else {
						layer.msg("提示：很遗憾，修改失败！");
					}
				},
				error : function(XmlHttpRequest, textStatus, errorThrown) {
					layer.msg("修改失败！");
				}
			});
		}
	}
	
	//检查原价格
	function check_clearance_price(){
		var clearance_price=$("#clearance_price").val();
		if(clearance_price!=null&&clearance_price!=""){
			if (isNaN(clearance_price)) {
				layer.msg("金额请输入数字！")
				return false;
			} else {
				return true;
			}
		}else{
			layer.msg("请输入原价！")
			return false;
		}
	}

	function check_new_price(){
		var new_price=$("#new_price").val();
		if(new_price!=null&&new_price!=""){
			if (isNaN(new_price)) {
				layer.msg("金额请输入数字！")
				return false;
			} else {
				return true;
			}
		}else{
			layer.msg("请输入现价！")
			return false;
		}
	}
</script>
<body>
	<form enctype="multipart/form-data" class="form-horizontal" id="myform" style="padding: 20px;">
		<div class="form-group">
			<label class="col-xs-3 control-label right_wz">课程分类：</label>
			<div class="col-xs-8">
				<select onchange="get_sub_course()" id="course_parent_id"
					name="course_parent_id" class="form-control select select-primary">
					<c:forEach items="${courseMenus }" var="cou_menu">
						<c:if test="${clearance.course_parent_id==cou_menu.course_id }">
						<option selected="selected" value="${cou_menu.course_id }">${cou_menu.course_name }</option>
						</c:if>
					</c:forEach>
				</select>
			</div>
			</div>
			<div class="form-group" style="margin-top:20px;">
			<label class="col-xs-3 control-label right_wz">选择课程：</label>
			<div class="col-xs-8">
				<select id="course_id" name="course_id"
					class="form-control select select-primary">
					<c:forEach items="${coursesubMenus }" var="cou_menu">
					<c:if test="${clearance.course_id==cou_menu.course_id }">
						<option selected="selected" value="${cou_menu.course_id }">${cou_menu.course_name }</option>
					</c:if>
					</c:forEach>
				</select>
			</div>
		</div>
			<div class="form-group" style="margin-top:15px;">
      <label class="col-xs-3 control-label right_wz">等级：</label>
      <div class="col-xs-8">
        <input class="form-control" value="${clearance.dengji}"  id="dengji" name="dengji" placeholder="输入等级，没有区分等级则不填">         
      </div>
    </div>
		<div class="form-group" style="margin-top:15px;">
      <label class="col-xs-3 control-label right_wz">套餐名称：</label>
      <div class="col-xs-8">
        <input class="form-control" value="${clearance.clearance_name}"  id="clearance_name" name="clearance_name" placeholder="输入套餐名称">         
      </div>
    </div>
    <div class="form-group" style="margin-top:15px;">
      <label class="col-xs-3 control-label right_wz">原价：</label>
      <div class="col-xs-8">
        <input class="form-control" onblur="check_new_price()" value="${clearance.clearance_price}" id="clearance_price" name="clearance_price" placeholder="输如套餐价格">         
      </div>
    </div>
    <div class="form-group" style="margin-top:15px;">
      <label class="col-xs-3 control-label right_wz">现价：</label>
      <div class="col-xs-8">
        <input class="form-control"  value="${clearance.new_price}" onblur="check_new_price()" id="new_price" name="new_price" placeholder="输入套餐价格">         
      </div>
    </div>
     <div class="form-group" style="margin-top:15px;">
      <label class="col-xs-3 control-label right_wz">课程到期时间：</label>
      <div class="col-xs-8"> 
        <input class="layui-input" value="<fmt:formatDate value='${clearance.daoqi_time }'/>" onclick="layui.laydate({elem: this})"   id="daoqi_time_str" name="daoqi_time_str" placeholder="请选择到期时间">         
      </div>
    </div>
    <div class="form-group" style="margin-top:15px;">
      <label class="col-xs-3 control-label right_wz">包含：</label>
      <div class="col-xs-8">
        <input class="form-control" value="${clearance.clearrance_baohan}" id="clearrance_baohan" name="clearrance_baohan" placeholder="输入套餐包含">         
      </div>
    </div>
    <div class="form-group" style="margin-top:15px;">
      <label class="col-xs-3 control-label right_wz">特色：</label>
      <div class="col-xs-8">
        <textarea class="form-control"  id="clearrance_tese" name="clearrance_tese" placeholder="请输入套餐特色" rows="3" style="color:#444;resize:none;">${clearance.clearrance_tese}</textarea>         
      </div>
    </div>
     <div class="form-group" style="margin-top:-10px;margin-bottom:0px;">
      <label class="col-xs-3 control-label right_wz"></label>
       <p  class="col-xs-8" style="color: orange;">不同的套餐特色请用逗号隔开</p>  
    </div>
    <div class="form-group" style="margin-top:5px;">
      <label class="col-xs-3 control-label right_wz">保障：</label>
      <div class="col-xs-8">
        <input class="form-control" value="${clearance.clearrance_baozhang}"  id="clearrance_baozhang" name="clearrance_baozhang" placeholder="输入套餐保障">         
      </div>
    </div>
		<div class="form-group" style="overflow:hidden;margin-top:5px;">
			<div class="col-xs-6 right_wz">
				<button type="button" onclick="close_layer()"
					class="btn btn-warning btn-lg" style="width: 100px;">取消</button>
			</div>
			<div class="col-xs-6" style="text-align: left;">
				<button type="button" onclick="update_clearance(${clearance.clearance_id})"
					class="btn btn-primary btn-lg" style="width: 100px;">保存</button>
			</div>
		</div>
	</form>
</body>
</html>