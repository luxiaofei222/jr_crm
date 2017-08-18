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
<script type="text/javascript"
	src="/css/school/back/layui/lay/modules/laydate.js"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<script src="/js/edu/jquery-ui.min.js" type="text/javascript"></script>
<script>
$(function(){
	$(".loading").hide();
})
//选择申报条件
function get_shenbaotiaojian(obj){
	var plan_id=$(obj).val();
	if(plan_id==0){
		$("#entrycondition_id").html("<option value='0'>请选择申报课程</option>");
	}else{
		$.post("/edu_entry/get_condition_list.jr",{
			'entryplan_id':plan_id
		},function(data){
			$("#course_id").html(data)
		})
	}
}
//获取申报级别
function get_dic_list(obj){
	var entrycondition_id=$(obj).val();
	if(entrycondition_id==0){
		$("#dictionary_id").html("<option value='0'>青选择申报级别</option>");
	}else{
		$.post("/edu_entry/get_tiaojianlist.jr",{
			'entrycondition_id':entrycondition_id
		},function(data){
			$("#dictionary_id").html(data)
		})
	}
}
//检测是否选择申报条件
function check_tiaojian(){
	var id = $("#dictionary_id").val();
	if(id==0){
		layer.msg("请选择申报条件！");
		return false;
	}else{
		var entrycondition_id=$("input[type='radio']:checked").val();//获取选中的值
		if(typeof(entrycondition_id)=='undefined'){
			layer.msg("请选择申报条件！");
			return false;
		}else{
			return true;
		}
	}
}
//获取申报条件单选框
function get_tiaojian_radio(obj){
	var entrycondition_id=$(obj).val();
	
	if(entrycondition_id==0){
		$("#tiaojianlist").html("");
	}else{
		$.post("/edu_entry/get_tiaojian_radio.jr",{
			'entrycondition_id':entrycondition_id
		},function(data){
			$("#tiaojianlist").html(data)
		})
	}
}
//关闭弹窗
function close_layer() {
	var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
	parent.layer.close(index);
}
//获取学校地点
function get_entry_school(){
	var entry_city_id=$("#entry_city_id").val();
	$.post("/entry_place/get_sub_entryplace.html",{
		'plcae_id':entry_city_id
	},function(data){
		$("#entry_school_id").html(data);
	})
}
//获取文件名
function get_file_name(){
	 var fileName="";
	  var name = $("#upfile").val();
	  fileName = name.split("\\").pop(); 
	  $("#file_name").val(fileName);
}
//导入信息
function satrt_daoru(){
	if(check_tiaojian()){
		$("#daoru").attr({"disabled":"disabled"});
		$(".loading").show();
		var entrycondition_id=$("input[type='radio']:checked").val();
		$("#myform").ajaxSubmit({
			type : 'POST',
			url : "/entry_info_excle/upload.jr",
			data:{
				'entrycondition_id':entrycondition_id
				},
			success : function(data) {
				$("#tishi").html(data);
				$(".loading").hide();
				 $("#daoru").removeAttr("disabled");//将按钮可用
				tanchuang('报名学员导入成功！');
			},
			error : function(XmlHttpRequest, textStatus, errorThrown) {
				$(".loading").hide();
				 $("#daoru").removeAttr("disabled");//将按钮可用
				tanchuang('很遗憾，系统发生错误，报名学员导入失败！');
			}
		});
	}
}
</script>
 <div class="loading">
   <div class="loader-inner ball-spin-fade-loader">
    <div></div>
    <div></div>
    <div></div>
    <div></div>
    <div></div>
    <div></div>
    <div></div>
    <div></div>
    <p style="color: #fff; position: absolute; top: 45px; left: -35px;">正在导入数据...</p>
  </div>    
</div>
<div class="daoru_shuju_dialog">
<form class="form-horizontal import_xueyuanshuju_dialog" enctype="multipart/form-data" id="myform" style="padding:20px;">
     <div class="form-group">
			<label class="col-xs-2 control-label">报名计划：</label>
			<div class="col-xs-10">
				<select id="entryplanId" onchange="get_shenbaotiaojian(this)"
					name="entryplanId">
					<option value="0">请选择计划</option>
					<c:forEach items="${entryPlans }" var="entryplan">
						<option value="${entryplan.entryplan_id }">${entryplan.entryplan_content }</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-xs-2 control-label">职业级别：</label>
			<div class="col-xs-10">
				<select id="course_id" onchange="get_dic_list(this)"
					name="course_id">
					<option value="0">请选择申报课程</option>
				</select> <select id="dictionary_id" onchange="get_tiaojian_radio(this)"
					name="dictionary_id">
					<option value="0">请选择申报级别</option>
				</select>
			</div>

		</div>
		<div class="form-group" id="tiaojianlist">
			<!-- 报名条件  -->
		</div>
		<div class="form-group">
			<label class="col-xs-2 control-label">选择报名点：</label>
			<div class="col-xs-10">
				<select class="col-xs-3" onchange="get_entry_city()"
					style="width: 30%; margin-right: 30px;" id="entry_province_id"
					name="entry_province_id">
					<c:forEach items="${entryPlaces }" var="place">
						<option value="${place.entryplace_id }">${place.entryplace_name }</option>
					</c:forEach>
				</select> <select class="col-xs-3" style="width: 30%; margin-right: 30px;" onchange="get_entry_school()" id="entry_city_id"
					name="entry_city_id">
					<c:forEach items="${entryPlaces_sub }" var="place">
						<option value="${place.entryplace_id }">${place.entryplace_name }</option>
					</c:forEach>
				</select> <select class="col-xs-3" style="width: 30%;" id="entry_school_id" name="entry_school_id">
					<c:forEach items="${entryPlaces_sub_sub }" var="place">
						<option value="${place.entryplace_id }">${place.entryplace_name }</option>
					</c:forEach>
				</select>
			</div>
		</div>
     <div class="form-group">
      <label class="col-xs-2 control-label" style="text-align: right;">选择数据：</label>
      <div class="col-xs-5">
        <input type="text" class="form-control" id="file_name">
      </div>
      <div class="col-xs-5">
        <a href="javascript:void(0)" class="btn btn-info btn-sm" style="position: relative; margin-right: 35px;">浏览数据
          <input type="file" id="upfile" onchange="get_file_name()" name="upfile" style="position: absolute;opacity:0; top:0px;left:0px; width: 100%; height:100%;" />
        </a>
        <a href="/template/entryinfo_template.xlsx" download="entryinfo_template.xlsx" class="btn btn-warning btn-sm">下载模板</a>
      </div>
    </div>
    <div class="form-group">
      <label for="inputPassword1" class="col-xs-2 control-label"></label>
      <div class="col-xs-10" id="tishi">
       <!-- 提示信息 --> 
       <p>说明：请先下载模板文件，按照要求的格式填写信息，否则导入失败或者出现混乱！</p>
      </div>
    </div>
    <div class="form-group" style="margin-top: 60px;">
       <label for="inputPassword1" class="col-xs-2 control-label">&nbsp;</label>
      <div class="col-xs-10">
        <button type="button" onclick="satrt_daoru()" class="btn btn-info btn-sm" style="margin-right:35px;">开始导入</button> 
        <button type="button" onclick="close_layer()" class="btn btn-success btn-sm">关闭</button> 
      </div> 
    </div>
	</form>	
</div>