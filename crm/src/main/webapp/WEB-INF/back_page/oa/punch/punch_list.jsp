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
function punch_jump_page(page,limit,start_time_str ,end_time_str,bumen_id,employee_id,nianyue){
	jiazaidonghua();
	$.post("/punch/get_oa_punch_list.jr",{
		 'start_time_str':start_time_str,
		 'end_time_str':end_time_str,
		 'bumen_id':bumen_id,
		 'employee_id':employee_id,
		 'xingming':xingming,
		 'nianyue':nianyue,
		'pageNumber' : page,
		'limit' : limit
	},function(data){
		$("#conten_list").html(data);
	})
}
//跳转页面
function punch_tiaozhuan_page(page,limit,start_time_str ,end_time_str,bumen_id,employee_id,nianyue){
	var page_num=$("#page_num").val();
	if(!isNaN(page_num)){
		if(page<page_num||page_num<0){
			layer.msg("你输入的页数不存在！")
		}else{
			jiazaidonghua();
			 $.post("/punch/get_oa_punch_list.jr", {
				 'start_time_str':start_time_str,
				 'end_time_str':end_time_str,
				 'bumen_id':bumen_id,
				 'employee_id':employee_id,
				 'xingming':xingming,
				 'nianyue':nianyue,
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
		 document.getElementById('punch_time_start').onclick = function(){
		    start.elem = this;
		    laydate(start);
		  }
		 document.getElementById('punch_time_end').onclick = function(){
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
			url : "/punch/get_oa_punch_list.jr",
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

//审核信息
function to_exam_punch(punch_id){
	 layer.open({
		  type: 2,
		  title: ['审核打卡记录'],
		  area: ['900px', '600px'],
		  shadeClose: false, //点击遮罩关闭
		  content: "/punch/to_exam_punch.jr?punch_id="+punch_id
		  });
}
//删除忘记打卡记录
function to_delete_punch(punch_id,page){
	 layer.confirm("提示：您好，确定要删除吗？",function(){
			layer.closeAll('dialog');
			$.post("/punch/delete_daka.jr",{
				'punch_id':punch_id
			},function(data){
				if(data==1){
					layer.msg("删除成功！");
				}else{
					layer.msg("删除失败！");
				}
			});
			})
}
//选择申请的人
function get_employees(){
	  var bumen_id=$("#bumen_id").val();
	  if(bumen_id!=null&&bumen_id!=""){
		  $.post("/back_leave/get_bumen_employee_list.jr", {
				'bumen_id' : bumen_id
			}, function(data) {
				$("#employee_id").html(data);
			}) 
	  }else{
		  $("#employee_id").html("<option value=''>请选择</option>");
	  }
		
}
</script>
<div class="back_right">
	<div class="back_r_top">
		<div class="left">忘记打卡</div>
		<div class="right" id="timer"></div>
	</div>
	<div class="right_content">
		<form enctype="multipart/form-data" id="myform"
			class="form-horizontal">
			<div class="form-group clearfix"
				style="padding-top: 10px; margin-bottom: 5px;">
				<label class="col-xs-1 right_wz">所在部门：</label>
				<div class="col-xs-2">
					<select class="form-control" name="bumen_id" id="bumen_id"
						onchange="get_employees()"
						style="line-height: 35px; height: 35px; padding: 0px;">
						<option value="">请选择</option>
						<c:forEach items="${organizations }" var="organ">
							<option value="${organ.organization_id }">${organ.organization_name }</option>
						</c:forEach>
					</select>
				</div>
				<label class="col-xs-1 right_wz">申请人：</label>
				<div class="col-xs-2">
					<select class="form-control" name="employee_id" id="employee_id"
						style="line-height: 35px; height: 35px; padding: 0px;">
						<option value="">请选择</option>
					</select>
				</div>
				<label class="col-xs-1 right_wz">申请时间从：</label>
				<div class="col-xs-2">
					<input class="layui-input" name="start_time_str"
						id="punch_time_start">
				</div>
				<label class="col-xs-1 right_wz">到：</label>
				<div class="col-xs-2">
					<input class="layui-input" name="end_time_str" id="punch_time_end">
				</div>
			</div>
			<div class="form-group clearfix"
				style="margin-top: 0px; margin-bottom: 5px;">
				<label class="col-xs-1 right_wz">按月搜索：</label>
				<div class="col-xs-2">
					<select class="form-control" id="time_yue" name="nianyue" style="line-height: 35px; height: 35px; padding: 0px;">
					<option value="">请选择</option>
					<c:forEach items="${time_list }" var="tim">
					<option value="${tim }">${tim }</option>
				</c:forEach>
			</select>
				</div>
				<button class="shaixuan" onclick="shaixuan_tioajian(this,${limit})">筛选</button>
			</div>
		</form>
		<div class="operation" style="height: 0px;">
			<div class="opera_left left">
				<!--左侧按钮  -->
			</div>
		</div>
		<div class="content_message">
			<table class="table table-hover">
				<thead>
					<tr class="tr_bgcolor warning">
						<th>编号</th>
						<th>姓名</th>
						<th>部门</th>
						<th>应打卡日期</th>
						<th>时间段</th>
						<th>忘记原因</th>
						<th>状态</th>
						<th>审批意见</th>
						<th>提交时间</th>
						<th>操作</th>
					</tr>
				</thead>
				<c:if test="${not empty punchs.list }">
					<tbody>
						<c:forEach items="${punchs.list }" var="punch" varStatus="vs">
							<c:if test="${vs.count%2 == '0' }">
								<tr class="active">
							</c:if>
							<c:if test="${vs.count%2 != '0' }">
								<tr>
							</c:if>
							<td><label class="label label-success btn-primary"
								for="minimal-checkbox-1">${vs.index+1+punchs.begin }</label></td>
							<td>${punch.employee_name }</td>
							<td>${punch.organization.organization_name }</td>
							<td><fmt:formatDate pattern="yyyy-MM-dd"
									value="${punch.daka_time }" /></td>
							<td>${punch.shijianduan }</td>
							<td>${punch.punch_info }</td>
							<td><c:if test="${punch.renshi_state==0 }">
					未查看
				</c:if> <c:if test="${punch.renshi_state==1 }">
					已记录
				</c:if> <c:if test="${punch.renshi_state==2 }">
					未批准
				</c:if></td>
							<td><c:if test="${punch.renshi_state!=0 }">
				${punch.renshi_info }
				</c:if> <c:if test="${punch.renshi_state==0 }">
				--
				</c:if></td>
							<td><fmt:formatDate type="both"
									value="${punch.punch_time }" /></td>
							<td>
								<c:if test="${punch.renshi_state==0 }">
								<button type="button"
									onclick="to_exam_punch(${punch.punch_id })"
									class="btn btn-success btn-xs">审核</button>
								</c:if>
								<c:if test="${punch.renshi_state!=0 }">
								<button type="button"
									onclick="to_delete_punch(${punch.punch_id },${punchs.pageNumber })"
									class="btn btn-success btn-xs">删除</button>
								</c:if>
							</td>
							</tr>
						</c:forEach>
					</tbody>
				</c:if>
			</table>
			<c:if test="${ empty punchs.list }">
				<p class="zanwu" style="text-align: center; color: #53C1FE;">暂无打卡记录</p>
			</c:if>
		</div>
		<div class="pages">
			<div class="whole left">
				<div class="go_page">
					<span>第</span><input type="text" id="page_num" /> <span>页</span> <a
						href="javascript:void(0)"
						onclick="punch_tiaozhuan_page(${punchs.pages},${limit},'${start_time_str }','${end_time_str }','${bumen_id }','${employee_id }','${nianyue }')"
						class="btn_go">GO</a>
				</div>
			</div>
			<ul class="pagination right">
				<li><a href="javascript:void(0)"
					onclick="punch_jump_page(1,${limit },'${start_time_str }','${end_time_str }','${bumen_id }','${employee_id }','${nianyue }')"
					class="fa fa-fast-backward"></a></li>
				<c:if test="${punchs.hasPreviousPage==true}">
					<li class="previous"
						onclick="punch_jump_page(${punchs.pageNumber-1},${limit },'${start_time_str }','${end_time_str }','${bumen_id }','${employee_id }','${nianyue }')"><a
						href="#fakelink" class="fa fa-angle-left"></a></li>
				</c:if>
				<c:forEach items="${punchs.navigatePageNumbers }" var="page">
					<c:choose>
						<c:when test="${punchs.pageNumber==page}">
							<li class="active"><a href="#fakelink">${page}</a></li>
						</c:when>
						<c:otherwise>
							<li
								onclick="punch_jump_page(${page},${limit },'${start_time_str }','${end_time_str }','${bumen_id }','${employee_id }','${nianyue }')"><a
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
							onclick="punch_jump_page(${punchs.pageNumber},20,'${start_time_str }','${end_time_str }','${bumen_id }','${employee_id }','${nianyue }')">20</a></li>
						<li><a href="#fakelink"
							onclick="punch_jump_page(${punchs.pageNumber},50,'${start_time_str }','${end_time_str }','${bumen_id }','${employee_id }','${nianyue }')">50</a></li>
						<li><a href="#fakelink"
							onclick="punch_jump_page(${punchs.pageNumber},100,'${start_time_str }','${end_time_str }','${bumen_id }','${employee_id }','${nianyue }')">100</a></li>
					</ul></li>
				<c:if test="${punchs.hasNextPage==true}">
					<li class="next"
						onclick="punch_jump_page(${punchs.pageNumber+1},${limit },'${start_time_str }','${end_time_str }','${bumen_id }','${employee_id }','${nianyue }')"><a
						href="#fakelink" class="fa fa-angle-right"></a></li>
				</c:if>
				<li
					onclick="punch_jump_page(${punchs.pages},${limit },'${start_time_str }','${end_time_str }','${bumen_id }','${employee_id }','${nianyue }')"><a
					href="javascript:void(0)" class="fa fa-fast-forward"></a></li>
			</ul>
		</div>
	</div>