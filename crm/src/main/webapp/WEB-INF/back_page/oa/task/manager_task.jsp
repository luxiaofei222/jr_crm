<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" href="/css/school/back/common/flat-ui.min.css" />
<link rel="stylesheet" href="/css/page/pages.css" />
<link rel="stylesheet" type="text/css" href="/css/oa/index/finance.css">
<script src="/js/school/back/common/flat-ui.min.js"></script>
<script src="/js/school/back/common/application.js"></script>
<script type="text/javascript"
	src="/css/school/back/layui/lay/modules/laydate.js"></script>
<script src="/js/common/time.js"></script>
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<style>
.select2-chosen {
	color: white !important;
}

.form-group .right_wz {
	text-align: right;
	line-height: 35px;
}

.form-control {
	height: 35px;
}

.form-group {
	margin-bottom: 10xp !important;
}

.form-control:focus {
	border: 2px solid #28A4F4;
}

.shaixuan {
	width: 100px;
	height: 35px;
	line-height: 35px;
	background: #18d1bb;
	border: none;
	color: white;
	float: left;
	border-radius: 5px;
	margin-left: 40px;
}
</style>
<script type="text/javascript">
$(function(){
	$(".pagination-dropdown").click(function(){
		$(this).children(".dropdown-menu").toggle();
	  })
})
//翻页上一页下一页
function task_jump_page(page,limit,task_employee_id ,bumen_id ,task_type ,zhongyao_state ,task_finish_time_start_str ,task_finish_time_end_str){
	jiazaidonghua();
	$.post("/oa_task/get_manager_task.jr",{
		 'task_employee_id':task_employee_id,
		 'bumen_id':bumen_id,
		 'task_type':task_type,
		 'zhongyao_state':zhongyao_state,
		 'task_finish_time_start_str':task_finish_time_start_str,
		 'task_finish_time_end_str':task_finish_time_end_str,
		'pageNumber' : page,
		'limit' : limit
	},function(data){
		$("#conten_list").html(data);
	})
}
//跳转页面
function task_tiaozhuan_page(page,limit,task_employee_id ,bumen_id ,task_type ,zhongyao_state ,task_finish_time_start_str ,task_finish_time_end_str){
	var page_num=$("#page_num").val();
	if(!isNaN(page_num)){
		if(page<page_num||page_num<0){
			layer.msg("你输入的页数不存在！")
		}else{
			jiazaidonghua();
			 $.post("/oa_task/get_manager_task.jr", {
				 'task_employee_id':task_employee_id,
				 'bumen_id':bumen_id,
				 'task_type':task_type,
				 'zhongyao_state':zhongyao_state,
				 'task_finish_time_start_str':task_finish_time_start_str,
				 'task_finish_time_end_str':task_finish_time_end_str,
				 'pageNumber' : page_num,
				 'limit' : limit
				}, function(data) {
					$("#conten_list").html(data);
				}) 
		}
	}else{
		layer.msg("请输入数字！")
	}
}
/*调用 laydate*/
$(function(){
	layui.use('laydate', function(){
		  var laydate = layui.laydate;
		  var start = {
			festival: true
		    ,istoday: true 
		  };
		 document.getElementById('ruzhi_time_start').onclick = function(){
		    start.elem = this;
		    laydate(start);
		  }
		 document.getElementById('ruzhi_time_end').onclick = function(){
			    start.elem = this;
			    laydate(start);
			  }
		});
})
//筛选条件
function shaixuan_tioajian(obj,limit){
		$(obj).attr({"disabled":"disabled"});
		$(obj).html("筛选中");
		$("#myform").ajaxSubmit({
			type : 'POST',
			url : "/oa_task/get_manager_task.jr",
			data:{
				'pageNumber':1,
				'limit':limit
			},
			success : function(data) {
				$("#conten_list").html(data);
			},
			error : function(XmlHttpRequest, textStatus, errorThrown) {
				tanchuang('查询失败');
			}
		});
}
//发布任务
function to_send_task(){
	layer.open({
		  type: 2,
		  title: ['发布任务'],
		  area: ['900px', '550px'],
		  shadeClose: false, //点击遮罩关闭
		  content: "/oa_task/get_send_task_page.jr"
		  });
}
//查看任务详情
function to_check_task(task_id){
	 layer.open({
		  type: 2,
		  title: ['任务详情'],
		  area: ['850px', '500px'],
		  shadeClose: false, //点击遮罩关闭
		  content: "/oa_task/to_check_task.jr?task_id="+task_id
		  });
}
//删除任务信息
function to_delete_task(task_id,limit,page){
	layer.confirm("提示：您好，确定要删除吗？",function(){
		layer.closeAll('dialog');
		$.post("/oa_task/delete_oa_task.jr",{
			'task_id':task_id
		},function(data){
			if(data==1){
				task_jump_page(page,limit);
			}else{
				tanchuang('很遗憾，系统发生错误');
			}
		})
	})
}
</script>
<div class="back_right">
	<div class="back_r_top">
		<div class="left">公司任务</div>
		<div class="right" id="timer"></div>
	</div>
	<div class="right_content">
		<form enctype="multipart/form-data" id="myform"
			class="form-horizontal">
			<div class="form-group clearfix"
				style="padding-top: 10px; margin-bottom: 5px;">
				<label class="col-xs-1 right_wz">任务人：</label>
				<div class="col-xs-2">
					<select class="form-control" name="task_employee_id"
						style="line-height: 35px; height: 35px; padding: 0px;">
						<option value="">请选择</option>
						<c:forEach items="${oaTasks_employee }" var="em">
						<option value="${em.jieshouem.employee_id }">${em.jieshouem.employee_name }</option>
						</c:forEach>
					</select>
				</div>
				<label class="col-xs-1 right_wz">状态：</label>
				<div class="col-xs-2">
					<select class="form-control" name="task_type"
						style="line-height: 35px; height: 35px; padding: 0px;">
						<option value="">请选择</option>
						<option value="已完成">已完成</option>
						<option value="进行中">进行中</option>
						<option value="未完成">未完成</option>
					</select>
				</div>
				<label class="col-xs-1 right_wz">所在部门：</label>
				<div class="col-xs-2">
					<select class="form-control" name="bumen_id" id="bumen_id"
						style="line-height: 35px; height: 35px; padding: 0px;">
						<option value="">请选择</option>
						<c:forEach items="${organizations }" var="organ">
							<option value="${organ.organization_id }">${organ.organization_name }</option>
						</c:forEach>
					</select>
				</div>
				<label class="col-xs-1 right_wz">紧急程度：</label>
				<div class="col-xs-2">
					<select class="form-control" name="zhongyao_state"
						style="line-height: 35px; height: 35px; padding: 0px;">
						<option value="">请选择</option>
						<option value="0">正常</option>
						<option value="1">紧急</option>
						<option value="2">特急</option>
					</select>
				</div>
			</div>
			<div class="form-group clearfix"
				style="margin-top: 0px; margin-bottom: 5px;">
				<label class="col-xs-1 right_wz">截止时间从：</label>
				<div class="col-xs-2">
					<input class="layui-input" name="task_finish_time_start_str"
						id="ruzhi_time_start">
				</div>
				<label class="col-xs-1 right_wz">到：</label>
				<div class="col-xs-2">
					<input class="layui-input" name="task_finish_time_end_str"
						id="ruzhi_time_end">
				</div>
				<button class="shaixuan" onclick="shaixuan_tioajian(this,${limit})">筛选</button>
			</div>
		</form>
		<div class="operation">
			<div class="opera_left left">
				<!--左侧按钮  -->
				<button type="button" onclick="to_send_task()" class="btn left btn-primary" ><i class="fa fa-plus"></i>&nbsp;发布任务</button>  
			</div>
		</div>
		<div class="content_message">
			<table class="table table-hover">
				<thead>
					<tr class="tr_bgcolor warning">
						<th>编号</th>
						<th>发布人</th>
						<th>接收人</th>
						<th>岗位</th>
						<th>状态</th>
						<th>紧急程度</th>
						<th>完成日期</th>
						<th>发布时间</th>
						<th>操作</th>
					</tr>
				</thead>
				<c:if test="${not empty oaTasks.list }">
					<tbody>
						<c:forEach items="${oaTasks.list }" var="task" varStatus="vs">
							<c:if test="${vs.count%2 == '0' }">
								<tr class="active">
							</c:if>
							<c:if test="${vs.count%2 != '0' }">
								<tr>
							</c:if>
							<td><label class="label label-success btn-primary"
								for="minimal-checkbox-1">${vs.index+1+oaTasks.begin }</label></td>
							<td>${task.fabuem.employee_name }</td>
							<td>${task.jieshouem.employee_name }</td>
							<td>${task.gangwei }</td>
							<td>${task.task_type }</td>
							<td>
							<c:if test="${task.zhongyao_state==0 }"><span style="color: green;">正常</span></c:if>
							<c:if test="${task.zhongyao_state==1 }"><span style="color: orange;">紧急</span></c:if>
							<c:if test="${task.zhongyao_state==2 }"><span style="color: red;">特急</span></c:if>
							</td>
							<td><fmt:formatDate  pattern="yyyy-MM-dd"
									value="${task.task_finish_time }" /></td>
							<td><fmt:formatDate type="both"
									value="${task.task_time }" /></td>
							<td>
								<button type="button"
										onclick="to_check_task(${task.task_id })"
										class="btn btn-success btn-xs">查看</button>
									<button type="button"
										onclick="to_delete_task(${task.task_id },${limit },${oaTasks.pageNumber})"
										class="btn btn-success btn-xs">删除</button>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</c:if>
			</table>
			<c:if test="${ empty oaTasks.list }">
				<p class="zanwu" style="text-align: center; color: #53C1FE;">还没有发布过任务哦！</p>
			</c:if>
		</div>
		<div class="pages">
			<div class="whole left">
				<div class="go_page">
					<span>第</span><input type="text" id="page_num" /> <span>页</span> <a
						href="javascript:void(0)"
						onclick="task_tiaozhuan_page(${oaTasks.pages},${limit},'${task_employee_id }','${bumen_id }','${task_type }','${zhongyao_state }','${task_finish_time_start_str }','${task_finish_time_end_str }')"
						class="btn_go">GO</a>
				</div>
			</div>
			<ul class="pagination right">
				<li><a href="javascript:void(0)"
					onclick="task_jump_page(1,${limit },'${task_employee_id }','${bumen_id }','${task_type }','${zhongyao_state }','${task_finish_time_start_str }','${task_finish_time_end_str }')"
					class="fa fa-fast-backward"></a></li>
				<c:if test="${oaTasks.hasPreviousPage==true}">
					<li class="previous"
						onclick="task_jump_page(${oaTasks.pageNumber-1},${limit },'${task_employee_id }','${bumen_id }','${task_type }','${zhongyao_state }','${task_finish_time_start_str }','${task_finish_time_end_str }')"><a
						href="#fakelink" class="fa fa-angle-left"></a></li>
				</c:if>
				<c:forEach items="${oaTasks.navigatePageNumbers }" var="page">
					<c:choose>
						<c:when test="${oaTasks.pageNumber==page}">
							<li class="active"><a href="#fakelink">${page}</a></li>
						</c:when>
						<c:otherwise>
							<li
								onclick="task_jump_page(${page},${limit },'${task_employee_id }','${bumen_id }','${task_type }','${zhongyao_state }','${task_finish_time_start_str }','${task_finish_time_end_str }')"><a
								href="#fakelink">${page}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<li class="pagination-dropdown dropup"><a href="#fakelink"
					class="dropdown-toggle" data-toggle="dropdown"> <i
						class="fui-triangle-up"></i>
				</a>
					<ul class="dropdown-menu">
						<li><a href="#fakelink"
							onclick="task_jump_page(${oaTasks.pageNumber},20,'${task_employee_id }','${bumen_id }','${task_type }','${zhongyao_state }','${task_finish_time_start_str }','${task_finish_time_end_str }')">20</a></li>
						<li><a href="#fakelink"
							onclick="task_jump_page(${oaTasks.pageNumber},50,'${task_employee_id }','${bumen_id }','${task_type }','${zhongyao_state }','${task_finish_time_start_str }','${task_finish_time_end_str }')">50</a></li>
						<li><a href="#fakelink"
							onclick="task_jump_page(${oaTasks.pageNumber},100,'${task_employee_id }','${bumen_id }','${task_type }','${zhongyao_state }','${task_finish_time_start_str }','${task_finish_time_end_str }')">100</a></li>
					</ul></li>
				<c:if test="${oaTasks.hasNextPage==true}">
					<li class="next"
						onclick="task_jump_page(${oaTasks.pageNumber+1},${limit },'${task_employee_id }','${bumen_id }','${task_type }','${zhongyao_state }','${task_finish_time_start_str }','${task_finish_time_end_str }')"><a
						href="#fakelink" class="fa fa-angle-right"></a></li>
				</c:if>
				<li
					onclick="task_jump_page(${oaTasks.pages},${limit },'${task_employee_id }','${bumen_id }','${task_type }','${zhongyao_state }','${task_finish_time_start_str }','${task_finish_time_end_str }')"><a
					href="javascript:void(0)" class="fa fa-fast-forward"></a></li>
			</ul>
		</div>
	</div>