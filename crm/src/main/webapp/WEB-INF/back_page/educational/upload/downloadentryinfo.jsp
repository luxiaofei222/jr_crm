<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" type="text/css"
	href="/css/school/back/common/reset.css">
<link rel="stylesheet" href="/css/school/back/common/bootstrap.min.css" />
<link rel="stylesheet" href="/css/school/back/font-awesome/css/font-awesome.min.css">
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
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<script src="/js/edu/jquery-ui.min.js" type="text/javascript"></script>
<script>
//关闭弹窗
function close_layer() {
	var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
	parent.layer.close(index);
}
//导出学员信息
function satrt_daochu(type){
	var entryplanId=$("#entryplanId").val();
	var parent_id=$("#course_id_parent_id").val();
	var course_id=$("#course_id_plan").val();
	var course_id_review=$("#course_id_review").val();
	if(parent_id!=0){
		//if(course_id!=0){
			$(".loading").css("display","block");
			if(entryplanId!=0){
				location.href="/entry_info_download/export_entry_info_excel.jr?entryplanId="+entryplanId+"&type="+type+"&parent_id="+parent_id+"&course_id="+course_id+"&course_id_review="+course_id_review;
			}else{
				location.href="/entry_info_download/export_entry_info_excel.jr?type="+type+"&parent_id="+parent_id+"&course_id="+course_id+"&course_id_review="+course_id_review;
			}
		/* }else{
			layer.msg("请选择二级分类！")
		} */
	}else{
		layer.msg("请选择一级分类！")
	}
}

//获取二级分类
function get_entry_plan_parent(){
	var parent_id=$("#course_id_parent_id").val();
	if(parent_id!=0){
		if(parent_id==9){
			$("#course_id_review").show();
		}else{
			$("#course_id_review").hide();
			$("#course_id_review").html("<option value='0'>请选择三级分类</option>");
		}
		$.post("/edu_entry/get_sub_couse.jr",{
			'parent_id':parent_id
		},function(data){
			$("#course_id_plan").html(data);
			if(parent_id==9){
				var course_id=$("#course_id_plan").val();
				$.post("/edu_entry/get_sub_review.jr",{
					'course_id':course_id
				},function(data){
					$("#course_id_review").html(data);
				})
			}
		})
	}else{
		$("#course_id_plan").html("<option value='0'>请选择二级分类</option>");
		$("#entryplanId").html("<option value='0'>请选择计划</option>");
	}
}
//获取计划
function get_entry_plan(obj){
	var course_id_plan=$(obj).val();
	var parent_id=$("#course_id_parent_id").val();
		if(course_id_plan!=0){
			if(parent_id!=9){
			$.post("/edu_entry/get_sub_plan.jr",{
				'course_id_plan':course_id_plan
			},function(data){
				$("#entryplanId").html(data);
			})
			}else{
				var course_id=$("#course_id_plan").val();
				$.post("/edu_entry/get_sub_review.jr",{
					'course_id':course_id
				},function(data){
					$("#course_id_review").html(data);
				})
			}
		}else{
			$("#entryplanId").html("<option value='0'>请选择计划</option>");
		}
}
//获取职称评审计划
function get_entry_review_plan(){
	var course_id_review=$("#course_id_review").val();
		if(course_id_plan!=0){
			$.post("/edu_entry/get_sub_review_plan.jr",{
				'review_id':course_id_review
			},function(data){
				$("#entryplanId").html(data);
			})
		}else{
			$("#entryplanId").html("<option value='0'>请选择计划</option>");
		}
}
</script>
 <div class="loading" style="display: none;">
  <div class="loader-inner ball-spin-fade-loader">
    <div></div>
    <div></div>
    <div></div>
    <div></div>
    <div></div>
    <div></div>
    <div></div>
    <div></div>
    <p style="color: #fff; position: absolute; top: 45px; left: -145px;">正在导出数据,请等待,如果下载完成,自行关闭窗口！</p>
  </div>    
</div>
<div class="daoru_shuju_dialog">
<form class="form-horizontal import_xueyuanshuju_dialog" enctype="multipart/form-data" id="myform" style="padding:20px;">
     <div class="form-group">
			<label class="col-xs-2 control-label">报名计划：</label>
			<div class="col-xs-10">
				<select class="col-xs-2" style="width: 57%; margin-right: 42px;" id="course_id_parent_id" onchange="get_entry_plan_parent()">
					<option value="0">请选择分类</option>
					<c:forEach items="${course_list }" var="course">
						<option value="${course.parent_id }">${course.coursename }</option>
					</c:forEach>
				</select>
				<select class="col-xs-2" style="width: 57%;margin-top:15px;" id="course_id_plan" onchange="get_entry_plan(this)">
					<option value="0">请选择二级分类</option>
				</select>
			
				<select class="col-xs-2" style="width: 57%; margin-right: 42px;margin-top:15px;display: none;" id="course_id_review" onchange="get_entry_review_plan()">
					<option value="0">请选择三级分类</option>
				</select>
				<select class="col-xs-2" style="width: 57%;margin-top:15px;" id="entryplanId" 
					name="entryplanId">
					<option value="0">请选择计划</option>
				</select>
			</div>
		</div>
    <div class="form-group" style="margin-top: 60px;">
       <label for="inputPassword1" class="col-xs-2 control-label">&nbsp;</label>
      <div class="col-xs-10">
        <button type="button" onclick="satrt_daochu(${type})" class="btn btn-info btn-sm" style="margin-right:35px;">开始导出</button> 
        <button type="button" onclick="close_layer()" class="btn btn-success btn-sm">关闭</button> 
      </div> 
    </div>
	</form>	
</div>