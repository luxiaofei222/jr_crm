<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" type="text/css"
	href="/css/school/back/common/reset.css">
<link rel="stylesheet" type="text/css"
	href="/js/common/bootstrap-select.css">
<link rel="stylesheet" href="/css/school/back/common/bootstrap.min.css" />
<link rel="stylesheet"
	href="/css/school/back/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css"
	href="/css/school/back/layui/css/layui.css">
<!-- <link rel="stylesheet" type="text/css"
	href="/css/school/back/layui/css/modules/laydate/laydate.css"> -->
<script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="/js/common/bootstrap-select.js"></script>
<script type="text/javascript"
	src="/js/school/back/common/bootstrap.min.js"></script>
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<!-- <script type="text/javascript"
	src="/css/school/back/layui/lay/modules/laydate.js"></script> -->
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<style>
.form-horizontal .form-group .right_wz {
	text-align: right;
}
</style>
<script>
$(function(){

	 $('.selectpicker').selectpicker({
        'selectedText': 'cat'
    });
	 
	layui.use('laydate', function(){
		  var laydate = layui.laydate;
		  var start = {
			festival: true
		    ,istoday: true
		  };
		  document.getElementById('kaoshi_qici').onclick = function(){
		    start.elem = this;
		    laydate(start);
		  }
		});
	
})
//添加联系人信息信息
function save_condition(entryplan_id,parent_id,course_id,review_id){
	var str = "";
	var len =$('input:text[name=content]').length;
	$('input:text[name=content]').each(function(i) {
		if (0 == i) {
			str = $(this).val();
		} else {
			str += ","+$(this).val();
		}
	});
	var str2 = "";
	$('input:text[name=xiangguanginfo]').each(function(i) {
		if (0 == i) {
			if($(this).val()!=null&&$(this).val()!=""){
				str2 = $(this).val();
			}else{
				str2="暂无"
			}
		} else {
			if($(this).val()!=null&&$(this).val()!=""){
				str2 += ","+$(this).val();
			}else{
				str2+= ","+"暂无"
			}
		}
	});
	var chengkao_kemu=$("#chengkao_kemu").val();
	if(str!=""&&str!=null){
		/* var course_id=$("#course_id").val(); */
		if(course_id==19){
			if(chengkao_kemu!=null){
				$("#myform").ajaxSubmit({
					type : 'POST',
					url : "/edu_condition/save_entry_condition.jr?entryplan_id="+entryplan_id,
					data:{'str':str,'str2':str2,'chengkao_kemu':chengkao_kemu.toString(),
						'course_parent_id':parent_id,'course_id':course_id},
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
				layer.msg("请选择等级或者课目");
			}
		}else{
			$("#myform").ajaxSubmit({
				type : 'POST',
				url : "/edu_condition/save_entry_condition.jr?entryplan_id="+entryplan_id,
				data:{'str':str,'str2':str2,'course_parent_id':parent_id,'course_id':course_id,'review_id':review_id},
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
	}else{
		layer.msg("请添加一条申报条件")
	}
}
//获取二级菜单
function get_sub_course(){
	var course_parent_id=$("#course_parent_id").val();
	$.post("/back_user/get_sub_course.jr",{
		'course_id':course_parent_id
	},function(data){
		$("#course_id").html(data);
	})
	if(course_parent_id==18){
		$("#cengci").show();
		$("#dictionary_id").html("");
		$.post("/edu_condition/get_dic_list.jr",{
			'course_id':course_parent_id,
			'type':1
		},function(data){
			$("#dictionary_id").hide();
			$("#dictionary_id").html("");
			$("#chengkao_xuanze").show();
			$("#chengkao_kemu").html(data);
			$('.selectpicker').selectpicker("refresh");
		})
	}else{
		$("#daxue").hide();
		$("#dictionary_id").show();
		$("#chengkao_xuanze").hide();
		$("#zhuanye").hide();
		$("#dengji").show();
		//显示等级
		$.post("/back_video/get_dic_list.jr",{
			'course_id':course_parent_id,
			'type':1
		},function(data){
			$("#dictionary_id").html(data);
			$('.selectpicker').selectpicker("refresh");
		})
		$("#cengci").hide();
	}
	
}
//显示等级
function get_dic_list(){
	var course_id=$("#course_id").val();
	if(course_id==20){
		$("#dengji").hide();
		$("#daxue").show();
		//$("#cengci").show();
		$("#zhuanye").show();
		$("#dictionary_id").html("");
		$('.selectpicker').selectpicker("refresh");
	}else{
		$("#daxue").hide();
		//$("#cengci").hide();
		$("#zhuanye").hide();
		$("#dengji").show();
		// 显示等级
		if(course_id==19){
			$.post("/edu_condition/get_dic_list.jr",{
				'course_id':course_id,
				'type':2
			},function(data){
				$("#dictionary_id").hide();
				$("#dictionary_id").html("");
				$("#chengkao_xuanze").show();
				$("#chengkao_kemu").html(data);
				$('.selectpicker').selectpicker("refresh");
			}) 
		}else{
			$.post("/back_video/get_dic_list.jr",{
				'course_id':course_id,
				'type':2
			},function(data){
				$("#chengkao_xuanze").hide();
				$("#dictionary_id").html(data);
				$("#dictionary_id").show();
				$("#chengkao_kemu").html("");
				$('.selectpicker').selectpicker("refresh");
			}) 
		}
	}
	
}
//关闭弹窗
function colse_layer(){
	var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
    parent.layer.close(index);
}
//专业
function get_zhuanye(){
	var university_id=$("#university_id").val();
	var  type_university=$("#baokao_cengci").val();
	//显示专业
	$.post("/edu_condition/get_zhuanye.jr",{
		'university_id':university_id,
		'type_university':type_university
	},function(data){
		$("#zhuanye_id").html(data);
	})
}
//获取专业信息
function get_chengkao_zhuanye(course_id,university_id){
	if(course_id==19){
		var baokao_cengci=$("#baokao_cengci").val();
		$.post("/edu_condition/get_chengkao_zhuanye.jr",{
			'university_id':university_id,
			'cengci':baokao_cengci
		},function(data){
			$("#zhuanye_id").html(data);
		})
	}
}
</script>
<form class="form-horizontal add_qiye_dialog"
	enctype="multipart/form-data" id="myform" style="padding: 21px;">
	<%-- 	<div class="form-group" style="margin-top: 15px;">
		<label class="col-xs-2 control-label right_wz">类别：</label>
		<div class="col-xs-8">
			<select data-toggle="select" onchange="get_sub_course()"
				id="course_parent_id" name="course_parent_id"
				class="form-control select select-primary">
				<c:forEach items="${courseMenus }" var="cou_menu">
					<option value="${cou_menu.course_id }">${cou_menu.course_name }</option>
				</c:forEach>
			</select>
		</div>
	</div>
	<div class="form-group" style="margin-top: 15px;">
		<label class="col-xs-2 control-label right_wz">课程：</label>
		<div class="col-xs-8">
			<select data-toggle="select" onchange="get_dic_list()" id="course_id"
				name="course_id" class="form-control select select-primary">
				<c:forEach items="${coursesubMenus }" var="cou_menu">
					<option value="${cou_menu.course_id }">${cou_menu.course_name }</option>
				</c:forEach>
			</select>
		</div>
	</div> --%>
	<div class="form-group" id="dengji" style="margin-top: 15px;">

		<c:if test="${entryPlan.parent_id!=18 }">
			<label class="col-xs-2 control-label right_wz">等级(科目)：</label>
			<div class="col-xs-8">
				<select data-toggle="select" id="dictionary_id" name="dictionary_id"
					class="form-control select select-primary">
					<c:forEach items="${dictionaries }" var="dic">
						<option value="${dic.dictionary_id }">${dic.dictionary_name }</option>
					</c:forEach>
				</select>
			</div>
		</c:if>
		<c:if test="${entryPlan.course_id==19 }">
			<label class="col-xs-2 control-label right_wz">等级(科目)：</label>
			<div class="col-xs-8">
				<div id="chengkao_xuanze">
					<select id="chengkao_kemu" class="selectpicker bla bla bli"
						multiple data-live-search="true">
						<c:forEach items="${dictionaries }" var="dic">
							<option value="${dic.dictionary_name }">${dic.dictionary_name }</option>
						</c:forEach>
					</select>
				</div>
			</div>
		</c:if>
	</div>
	<c:if test="${entryPlan.course_id==20 }">
		<div class="form-group" id="daxue" style="margin-top: 15px;">
			<label class="col-xs-2 control-label right_wz">学校：</label>
			<div class="col-xs-8">
				<select data-toggle="select" id="university_id" name="university_id"
					class="form-control select select-primary">
					<c:forEach items="${universities }" var="univer">
						<option value="${univer.university_id }">${univer.university_name }</option>
					</c:forEach>
				</select>
			</div>
		</div>
	</c:if>
	<c:if test="${entryPlan.parent_id==18 }">
		<div class="form-group" id="cengci" style="margin-top: 15px;">
			<label class="col-xs-2 control-label right_wz">报考层次：</label>
			<div class="col-xs-8">
				<select data-toggle="select" id="baokao_cengci" onchange="get_chengkao_zhuanye('${entryPlan.course_id }','${entryPlan.university_id }')" name="baokao_cengci"
					class="form-control select select-primary">
					<option value="专升本">专升本</option>
					<option value="高起专">高起专</option>
					<option value="高起本">高起本</option>
				</select>
			</div>
		</div>
	</c:if>
<%-- 	<c:if test="${entryPlan.course_id==19 }">
	 <div class="form-group" style="margin-top: 15px;">
		<label class="col-xs-2 control-label right_wz">专业：</label>
		<div class="col-xs-8">
			<select data-toggle="select" id="zhuanye_id" name="zhuanye_id"
				class="form-control select select-primary">
				<c:if test="${empty chengkaoScs }">
				<option value="0">请先在报考计划选择大学</option>
				</c:if>
				<c:forEach items="${chengkaoScs }" var="chengkao">
					<option value="${chengkao.chengkao_id }">${chengkao.chengkao_name }</option>
				</c:forEach>
			</select>
		</div>
	</div> 
	</c:if> --%>
	<div class="form-group" style="margin-top: 15px;">
		<label class="col-xs-2 control-label right_wz">考试期次：</label>
		<div class="col-xs-8">
			<input class="form-control" id="kaoshi_qici" name="kaoshi_qici"
				placeholder="选择考试期次,无则不填">
		</div>
	</div>
	<div class="form-group" style="margin-top: 15px;">
		<label class="col-xs-2 control-label right_wz">考试批次：</label>
		<div class="col-xs-8">
			<input class="form-control" id="kaoshi_pici" name="kaoshi_pici"
				placeholder="请输入考试批次,无则不填">
		</div>
	</div>
	<c:if test="${entryPlan.course_id==19 }">
	<div class="form-group" style="margin-top: 15px;">
		<label class="col-xs-2 control-label right_wz">成考学费：</label>
		<div class="col-xs-8">
			<input class="form-control" id="chengkao_xuefei" name="chengkao_xuefei"
				value="0">
		</div>
	</div>
	</c:if>
	<script type="text/javascript">
	//添加一条申报条件
	function tj(obj){
		  var val= $(obj).attr("class");
		   if(val=="fa fa-plus-circle"){
		    $(obj).parent().parent().parent().after('<div><div class="form-group plus" style="margin-top:15px;"><label class="col-xs-2 control-label right_wz">申报条件：</label><div class="col-xs-8"><input class="form-control" id="content" name="content"  placeholder="输入申报条件"></div></div><div class="form-group" style="margin-top:15px;"><label class="col-xs-2 control-label right_wz">相关信息：</label><div class="col-xs-8"><input class="form-control" id="xiangguanginfo" name="xiangguanginfo" placeholder="输入相关信息"></div><div class="col-xs-2"><i class="fa fa-plus-circle" style="font-size:30px;color:#1ABC9C;line-height:34px;" onclick="tj(this)"></i></div></div></div>');
		    $(obj).removeClass("fa fa-plus-circle");
		    $(obj).addClass("fa fa-minus-circle"); 
		    $(obj).css("color","#999");
		    } else{
		    $(obj).parent().parent().parent().remove();
		    }
		 }
	</script>
	<div>
	<div class="form-group plus" style="margin-top: 15px;">
		<label class="col-xs-2 control-label right_wz">申报条件：</label>
		<div class="col-xs-8">
			<input class="form-control" id="content" name="content"
				placeholder="输入申报条件">
		</div>
		<div class="col-xs-2">
			<i class="fa fa-plus-circle"
				style="font-size: 30px; color: #1ABC9C; line-height: 34px;"
				onclick="tj(this)"></i>
		</div>
	</div>
	<div class="form-group" style="margin-top: 15px;">
		<label class="col-xs-2 control-label right_wz">相关信息：</label>
		<div class="col-xs-8">
			<input class="form-control" id="xiangguanginfo" name="xiangguanginfo"
				placeholder="输入相关信息">
		</div>
	</div>
	</div>
	<div class="form-group" style="margin-top: 15px;">
		<div class="col-xs-6 right_wz">
			<button type="button" onclick="colse_layer()"
				class="btn btn-warning btn-lg" style="width: 100px;">取消</button>
		</div>
		<div class="col-xs-6" style="text-align: left;">
			<button type="button" onclick="save_condition(${entryplan_id},${entryPlan.parent_id},${entryPlan.course_id},'${entryPlan.review_id }')"
				class="btn btn-primary btn-lg" style="width: 100px;">保存</button>
		</div>
	</div>
</form>
