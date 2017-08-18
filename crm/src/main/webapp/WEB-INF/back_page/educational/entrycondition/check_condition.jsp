<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" type="text/css"
	href="/css/school/back/common/reset.css">
<link rel="stylesheet" href="/css/school/back/common/bootstrap.min.css" />
<link rel="stylesheet"
	href="/css/school/back/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css"
	href="/css/jiaowu/condition_check.css" />
<link rel="stylesheet" type="text/css"
	href="/css/school/back/layui/css/layui.css">
<link rel="stylesheet" type="text/css"
	href="/css/school/back/layui/css/modules/laydate/laydate.css">
<script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
<script type="text/javascript"
	src="/js/school/back/common/bootstrap.min.js"></script>
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script type="text/javascript"
	src="/css/school/back/layui/lay/modules/laydate.js"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<style>
.form-horizontal .form-group .right_wz {
	text-align: right;
}
</style>
<script>
	//关闭弹窗
	function colse_layer() {
		var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
		parent.layer.close(index);
	}
	//修改申报条件
	function update_content(entrycondition_id){
		var content=$("#content"+entrycondition_id).val();
		$.post("/edu_condition/update_condition.jr",{
			'entrycondition_id':entrycondition_id,
			'entrycondition_content':content
		},function(data){
			if(data==1){
				location.reload();
				layer.msg("修改成功！");
			}else{
				layer.msg("修改失败！");
			}
		})
	}
	//修改相关信息
	function update_info(entrycondition_id,dictionary_id){
		var info=$("#info"+entrycondition_id).val();
		$.post("/edu_condition/update_condition.jr",{
			'xiangguang_info':info,
			'entrycondition_id':entrycondition_id
		},function(data){
			if(data==1){
				location.reload();
				layer.msg("修改成功！");
			}else{
				layer.msg("修改失败！");
			}
		})
	}
	//修改成考学费
	function update_xuefei(entrycondition_id){
		var xuefei=$("#xuefei"+entrycondition_id).val();
		$.post("/edu_condition/update_condition.jr",{
			'chengkao_xuefei':xuefei,
			'entrycondition_id':entrycondition_id
		},function(data){
			if(data==1){
				location.reload();
				layer.msg("修改成功！");
			}else{
				layer.msg("修改失败！");
			}
		})
	}
</script>
<div class="condition_wrapper">
	<c:forEach items="${conditions }" var="cond">
		<h2 class="condition_title">${cond.courseMenu.course_name }</h2>
		<c:if test="${not empty cond.kaoshi_pici }">
		<h3>批次：${cond.kaoshi_pici }</h3>
		</c:if>
		<ul class="condition_ul">
			<c:forEach items="${cond.houtaisublist }" var="houtai" varStatus="vs">
				<c:if test="${vs.count%2 == '0' }">
					<li class="condition_li  li_red">
				</c:if>
				<c:if test="${vs.count%2 != '0' }">
					<li class="condition_li  li_yellow">
				</c:if>
				<div class="condition_li_title">
				${houtai.university.university_name }
				${houtai.baokao_cengci }
				<%-- <c:if test="${houtai.course_id==19 }">
				-[${houtai.chengkao_kemu }]
				</c:if> --%>
				${houtai.dictionary.dictionary_name }
				<%-- <c:if test="${empty houtai.chengkao_kemu }">
				${houtai.chengkao_kemu}
				</c:if> --%>
				</div>
				<ul class="condition_nei_ul">
					<c:forEach items="${houtai.sub_conditionlist }" var="quchong">
						<c:if test="${quchong.course_id==19 }">
							成考报考科目-(${quchong.chengkao_kemu})
						</c:if>
						<li class="condition_li1"><span>申报条件：</span> <textarea 
							type="text" value="${quchong.entrycondition_content }"
							placeholder="请输入申报条件" id="content${quchong.entrycondition_id }">${quchong.entrycondition_content }</textarea>
							<button type="button" class="btn btn-danger btn-ms"
								style="width: 100px;" onclick="update_content(${quchong.entrycondition_id})">修改</button>
						</li>
						<li class="xgsm">
							<span>相关说明：</span> <textarea id="info${quchong.entrycondition_id }">${quchong.xiangguang_info }</textarea>
							<button type="button" onclick="update_info(${quchong.entrycondition_id })" class="btn btn-danger btn-ms"style="width: 100px;">修改</button>
						</li>
						<c:if test="${quchong.course_id==19 }">
							<li class="xgsm">
							<span>成考学费：</span> <input type="text" class="xuefei_input" id="xuefei${quchong.entrycondition_id }" value="${quchong.chengkao_xuefei }" />
							<button type="button" onclick="update_xuefei(${quchong.entrycondition_id })" class="btn btn-danger btn-ms"style="width: 100px;">修改</button>
						</li>
						</c:if>
					</c:forEach>
				</ul>
				</li>
			</c:forEach>
		</ul>
	</c:forEach>
</div>
<div class="form-group" style="margin-top: 15px;">
	<div class="col-xs-6 right_wz">
		<button type="button" onclick="colse_layer()"
			class="btn btn-warning btn-lg" style="width: 100px;">关闭</button>
	</div>
</div>