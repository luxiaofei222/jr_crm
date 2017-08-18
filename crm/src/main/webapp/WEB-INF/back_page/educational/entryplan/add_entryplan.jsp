<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" type="text/css" href="/css/school/back/common/reset.css">
	<link rel="stylesheet" href="/css/school/back/common/bootstrap.min.css" />
	<link rel="stylesheet" href="/css/school/back/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="/dist/summernote.css" />
    <link rel="stylesheet" type="text/css" href="/css/school/back/layui/css/layui.css">
   <link rel="stylesheet" type="text/css" href="/css/school/back/layui/css/modules/laydate/laydate.css">
    <script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="/js/school/back/common/bootstrap.min.js"></script>
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script type="text/javascript"
	src="/css/school/back/layui/lay/modules/laydate.js"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<style>
form .form-group label {
	font-size:15px;
	color:#444;
	}
form .form-group input:focus,form .form-group textarea:focus {
	border-color:#e74c3c;
	}
form .form-group .btn {
	margin-right:30px;
	}
</style>
<script>
$(function(){
	layui.use('laydate', function(){
		  var laydate = layui.laydate;
		  var start = {
			festival: true
		    ,istoday: true
		  };
		  document.getElementById('jiezhiriqi').onclick = function(){
		    start.elem = this;
		    laydate(start);
		  }
		});
})
//添加联系人信息信息
function save_entry_plan(){
	if(check_entryplan_content()&&check_jiezhiriqi()&&check_baomingfei()&&check_jiaocaifei()){
		$("#myform").ajaxSubmit({
			type : 'POST',
			url : "/edu_entry_plan/save_entry_plan.jr",
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
//计划内容
function check_entryplan_content(){
	var entryplan_content=$("#entryplan_content").val();
	if(entryplan_content!=null&&entryplan_content!=""){
		
		return true;
	}else{
		layer.msg('请输入计划内容！');
		return false;
	}
}
//检查报名费
function check_baomingfei(){
	var baomingfei=$("#baomingfei").val();
	if(baomingfei!=null&&baomingfei!=""){
		if(isNaN(baomingfei)){
			layer.msg('报名费只能是数字！');
			return false;
		}else{
			return true;
		}
	}else{
		layer.msg('请输入报名费！');
		return false;
	}
}
//检查教材费
function check_jiaocaifei(){
	var jiaocaifei=$("#jiaocaifei").val();
	if(jiaocaifei!=null&&jiaocaifei!=""){
		if(isNaN(jiaocaifei)){
			layer.msg('教材费只能是数字！');
			return false;
		}else{
			return true;
		}
	}else{
		layer.msg('请输入教材费！');
		return false;
	}
}
//检查截至日期
function check_jiezhiriqi(){
	var jiezhiriqi=$("#jiezhiriqi").val();
	if(jiezhiriqi!=null&&jiezhiriqi!=""){
		return true;
	}else{
		layer.msg('请选择截止日期！');
		return false;
	}
}
function get_sub_course(){
	var course_parent_id=$("#course_parent_id").val();
	if(course_parent_id==18){
		//$("#xueli").show();
		$("#feixueli").hide();
		$("#chengkao_nian").val("0");
		$("#chengkao_zong").val("0");
	}else{
		//$("#xueli").hide();
		$("#feixueli").show();
		$("#chengkao_nian").val("0");
		$("#chengkao_zong").val("0");
	}
	if(course_parent_id==9){//显示职称评审3级
		$("#zhicheng_sanji").show();
	}else{
		$("#zhicheng_sanji").hide();
		$("#review_id").html("<option value='0'>请选择</option>");
	}
	$.post("/back_user/get_sub_course.jr",{
		'course_id':course_parent_id
	},function(data){
		$("#course_id").html(data);
		if(course_parent_id==9){//显示职称评审3级
			var course_id=$("#course_id").val();
			$.post("/edu_entry_plan/get_review_list.jr",{
				'course_id':course_id
			},function(data){
				$("#review_id").html(data);
			})
		}
		var course_id=$("#course_id").val();
		if(course_id==19){
			$("#chengkao_xuexiao").show();
		}else{
			$("#chengkao_xuexiao").hide();
		}
	})
}

function get_dic_list(){
	var course_id=$("#course_id").val();
	var course_parent_id=$("#course_parent_id").val();
	if(course_id==19){
		//$("#xueli").show();
		$("#feixueli").hide();
		$("#putongbanfei").val("0");
		$("#jingjiangbanfei").val("0");
		$("#chengkao_xuexiao").show();
	}else{
		$("#putongbanfei").val("0");
		$("#jingjiangbanfei").val("0");
		$("#chengkao_nian").val("0");
		$("#chengkao_zong").val("0");
		//$("#xueli").hide();
		$("#feixueli").hide();
		$("#chengkao_xuexiao").hide();
	}
	if(course_parent_id==9){//显示职称评审3级
		$.post("/edu_entry_plan/get_review_list.jr",{
			'course_id':course_id
		},function(data){
			$("#review_id").html(data);
		})
	}else{
		$("#review_id").html("<option value='0'>请选择</option>");
	}
}
</script>
<form class="form-horizontal add_qiye_dialog" enctype="multipart/form-data" id="myform" style="padding:21px;">
<div class="form-group" style="margin-top: 15px;">
		<label class="col-xs-2 control-label right_wz">类别：</label>
		<div class="col-xs-8">
			<select data-toggle="select" onchange="get_sub_course()"
				id="course_parent_id" name="parent_id"
				class="form-control select select-primary">
				<c:forEach items="${courseMenus }" var="cou_menu">
					<option value="${cou_menu.course_id }">${cou_menu.course_name }</option>
				</c:forEach>
			</select>
		</div>
	</div>
	<div class="form-group" style="margin-top: 15px;">
		<label class="col-xs-2 control-label right_wz"></label>
		<div class="col-xs-8">
			<select data-toggle="select" onchange="get_dic_list()" id="course_id"
				name="course_id" class="form-control select select-primary">
				<c:forEach items="${coursesubMenus }" var="cou_menu">
					<option value="${cou_menu.course_id }">${cou_menu.course_name }</option>
				</c:forEach>
			</select>
		</div>
	</div>
	<div class="form-group" id="zhicheng_sanji" style="margin-top: 15px; display: none;">
		<label class="col-xs-2 control-label right_wz"></label>
		<div class="col-xs-8">
			<select data-toggle="select"  id="review_id"
				name="review_id" class="form-control select select-primary">
				<option value="0">请选择</option>
				<%-- <c:forEach items="${coursesubMenus }" var="cou_menu">
					<option value="${cou_menu.course_id }">${cou_menu.course_name }</option>
				</c:forEach> --%>
			</select>
		</div>
	</div>
	<div id="chengkao_xuexiao" class="form-group" style="margin-top: 15px;display: none">
		<label class="col-xs-2 control-label right_wz">成考学校：</label>
		<div class="col-xs-8">
			<select data-toggle="select" 
				id="university_id" name="university_id"
				class="form-control select select-primary">
				<option value="0">请选择</option>
				<c:forEach items="${chengkaoScs }" var="chengkao">
					<option value="${chengkao.chengkao_id }">${chengkao.chengkao_name }</option>
				</c:forEach>
			</select>
		</div>
	</div>
     <div class="form-group">
      <label class="col-xs-2 control-label">报名计划：</label>
      <div class="col-xs-9">
        <input type="text" onblur="check_entryplan_content()" class="form-control" name="entryplan_content" id="entryplan_content" placeholder="请输入报考计划">  
      </div>
    </div>
    <div class="form-group">
      <label class="col-xs-2 control-label">相关说明：</label>
      <div class="col-xs-9">
        <input type="text" class="form-control" name="entryplan_explain" id="entryplan_explain" placeholder="相关说明">
      </div>
    </div>
    <div class="form-group">
      <label class="col-xs-2 control-label">截至日期：</label>
      <div class="col-xs-9">
        <input type="text" class="form-control" onblur="check_jiezhiriqi()"   name="jiezhiriqi" id="jiezhiriqi" placeholder="添加截至日期">   
      </div>
    </div>   
     <div class="form-group">
      <label class="col-xs-2 control-label">报名费：</label>
      <div class="col-xs-9">
        <input type="text" class="form-control" value="0" onblur="check_baomingfei()" name="baomingfei" id="baomingfei" placeholder="报名费">
      </div>
    </div>
     <div class="form-group">
      <label class="col-xs-2 control-label">教材费：</label>
      <div class="col-xs-9">
        <input type="text" class="form-control" value="0" onblur="check_jiaocaifei()" name="jiaocaifei" id="jiaocaifei" placeholder="教材费">
      </div>
    </div>
    <div id="feixueli">
     <div class="form-group">
      <label class="col-xs-2 control-label">培训费：</label>
      <label class="col-xs-2" style="font-size:13px;text-align:center;">普通班：</label>
      <div class="col-xs-7">
       	<input type="text" class="form-control" value="0" name="putongbanfei" id="putongbanfei" placeholder="普通班费用">
      </div>
     </div>
     <div class="form-group">
      <label class="col-xs-2 control-label"></label>
      <label class="col-xs-2" style="font-size:13px;text-align:center;">精讲班：</label>
      <div class="col-xs-7">
      	<input type="text" class="form-control" value="0" name="jingjiangbanfei" id="jingjiangbanfei" placeholder="精讲班费用">
      </div>
    </div>
    </div>
   <%--   <div id="xueli" style="display: none;">
     <div class="form-group">
      <label class="col-xs-2 control-label">学费：</label>
        <div class="col-xs-7">
      	<input type="text" class="form-control" value="${entryPlan.chengkao_zong}" name="chengkao_zong" id="chengkao_zong" placeholder="精讲班费用">
     	 </div> --%>
      <!-- <label class="col-xs-2" style="font-size:13px;text-align:center;">每年：</label>
      <div class="col-xs-7">
       	<input type="text" class="form-control" value="0" name="chengkao_nian" id="chengkao_nian" placeholder="成考学费">
      </div> -->
     </div>
     <!-- <div class="form-group">
      <label class="col-xs-2 control-label"></label>
      <label class="col-xs-2" style="font-size:13px;text-align:center;">总额：</label>
      <div class="col-xs-7">
      	<input type="text" class="form-control" value="0" name="chengkao_zong" id="chengkao_zong" placeholder="成考学费">
      </div>
    </div> -->
    <!-- </div> -->
    <div class="form-group">
       <label for="inputPassword1" class="col-xs-2 control-label">&nbsp;</label>
      <div class="col-xs-9">
        <button type="button" onclick="save_entry_plan()" class="btn btn-success btn-xm">提交</button>
        <button type="reset" class="btn btn-danger btn-xm">重置</button> 
      </div> 
    </div>
	</form>	