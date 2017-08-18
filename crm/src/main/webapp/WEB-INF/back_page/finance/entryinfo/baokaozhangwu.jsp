<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" href="/css/school/back/common/flat-ui.min.css" />
<link rel="stylesheet" href="/css/crm/index/qiyeku.css" />
<link rel="stylesheet" type="text/css"
	href="/css/school/back/layui/css/layui.css">
<link rel="stylesheet" href="/css/page/pages.css" />
<link rel="stylesheet" type="text/css" href="/css/edu/index/finance.css">
<script src="/js/school/back/common/flat-ui.min.js"></script>
<script src="/js/school/back/common/application.js"></script>
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
	background: #28A4F4;
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
function info_jump_page(page,limit,entryUserName , documentNumber,entryUserPosition ,employee_id ,start_time ,end_time , entryplanId,university_id ,zhuanye_id ,mianshoubanxing ,isPay ,payType ,price,bao_start_time,bao_end_time){
	jiazaidonghua();
	$.post("/fin_entry_info/get_entry_info_zhangwu.jr",{
		'entryUserName':entryUserName,
		 'documentNumber':documentNumber,
		 'entryUserPosition':entryUserPosition,
		 'employee_id':employee_id,
		 'start_time':start_time,
		 'end_time':end_time,
		 'entryplanId':entryplanId,
		 'university_id':university_id,
		 'zhuanye_id':zhuanye_id,
		 'mianshoubanxing':mianshoubanxing,
		 'isPay':isPay,
		 'payType':payType,
		 'price':price,
		 'bao_start_time':bao_start_time,
		 'bao_end_time':bao_end_time,
			'pageNumber' : page,
			'limit' : limit
	},function(data){
		$("#conten_list").html(data);
	})
}
//添加申报条件
function to_check_infopage(entryinfo_id){
	layer.open({
		type : 2,
		title : [ '报考信息账务查看' ],
		area : [ '900px', '650px' ],
		shadeClose : true, //点击遮罩关闭
		content : "/fin_entry_info/to_check_infopage.jr?entryinfo_id="+entryinfo_id
	});
}
//跳转页面
function info_tiaozhuan_page(page,limit,entryUserName , documentNumber,entryUserPosition ,employee_id ,start_time ,end_time , entryplanId,university_id ,zhuanye_id ,mianshoubanxing ,isPay ,payType ,price,bao_start_time,bao_end_time){
	var page_num=$("#page_num").val();
	if(!isNaN(page_num)){
		if(page<page_num||page_num<0){
			layer.msg("你输入的页数不存在！")
		}else{
			jiazaidonghua();
			 $.post("/fin_entry_info/get_entry_info_zhangwu.jr", {
				 'entryUserName':entryUserName,
				 'documentNumber':documentNumber,
				 'entryUserPosition':entryUserPosition,
				 'employee_id':employee_id,
				 'start_time':start_time,
				 'end_time':end_time,
				 'entryplanId':entryplanId,
				 'university_id':university_id,
				 'zhuanye_id':zhuanye_id,
				 'mianshoubanxing':mianshoubanxing,
				 'isPay':isPay,
				 'payType':payType,
				 'price':price,
				 'bao_start_time':bao_start_time,
				 'bao_end_time':bao_end_time,
					'pageNumber' : page,
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
		 document.getElementById('end_time').onclick = function(){
		    start.elem = this;
		    laydate(start);
		  }
		  document.getElementById('start_time').onclick = function(){
			    start.elem = this;
			    laydate(start);
			  }
		  document.getElementById('bao_start_time').onclick = function(){
			    start.elem = this;
			    laydate(start);
			  }
		  document.getElementById('bao_end_time').onclick = function(){
			    start.elem = this;
			    laydate(start);
			  }
		});
})
//获取专业
function get_zhuanye(){
	var university_id=$("#university_id").val();
	//显示专业
	$.post("/fin_entry_info/get_zhuanye.jr",{
		'university_id':university_id
	},function(data){
		$("#zhuanye_id").html(data);
	})
}
//验证金额的输入类型
function check_start_jine(){
	var mix_price=$("#mix_price").val();
	if(mix_price!=""&&mix_price!=null){
		if(isNaN(mix_price)){
			layer.msg("金额请输入数字！")
			return false;
		}else{
			return true;
		}
	}else{
		return true;
	}
}
function check_end_jine(){
	var max_price=$("#max_price").val();
	if(max_price!=""&&max_price!=null){
		if(isNaN(max_price)){
			layer.msg("金额请输入数字！")
			return false;
		}else{
			return true;
		}
	}else{
		return true;
	}
}
//筛选条件
function shaixuan_tioajian(obj,limit){
		$(obj).attr({"disabled":"disabled"});
		$(obj).html("筛选中");
		$("#myform").ajaxSubmit({
			type : 'POST',
			url : "/fin_entry_info/get_entry_info_zhangwu.jr",
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
//切换状态
function get_entry_info_state(obj,limit){
	var state=$(obj).val();
	jiazaidonghua();
	$.post("/fin_entry_info/get_entry_info_zhangwu.jr", {
		'entryInfoState' : state,
		'pageNumber' : 1,
		'limit' : limit
	}, function(data) {
		if (data != null) {
			$('#conten_list').html(data);
		}
	});
}
//导出数据
function daochu_entry_info(){
	layer.open({
		type : 2,
		title : [ '导出学员信息' ],
		area : [ '1000px', '400px' ],
		shadeClose : true, //点击遮罩关闭
		content : "/fin_entry_info/to_daochu_entry_info.jr"
	});
}
//导出补费信息
function daochu_bufei_info(){
	layer.open({
		type : 2,
		title : [ '导出学员补费信息' ],
		area : [ '1000px', '400px' ],
		shadeClose : true, //点击遮罩关闭
		content : "/fin_entry_info/to_daochu_bufei_info.jr"
	});
}
</script>
<div class="back_right">
	<div class="back_r_top">
		<div class="left">报考账务</div>
		<div class="right" id="timer"></div>
	</div>
	<div class="right_content">
		<form enctype="multipart/form-data" id="myform"
			class="form-horizontal">
			<div class="form-group clearfix"
				style="padding-top: 10px; margin-bottom: 5px;">
				<label class="col-xs-1 right_wz">学员姓名：</label>
				<div class="col-xs-2">
					<input class="form-control" id="entryUserName" name="entryUserName"
						placeholder="输入学员姓名">
				</div>
				<label class="col-xs-1 right_wz">身份证号：</label>
				<div class="col-xs-2">
					<input class="form-control" id="documentNumber"
						name="documentNumber" placeholder="输入身份证号">
				</div>
				<label class="col-xs-1 right_wz">所在部门：</label>
				<div class="col-xs-2">
					<input class="form-control" id="entryUserPosition"
						name="entryUserPosition" placeholder="所在部门">
				</div>
				<label class="col-xs-1 right_wz">业务员：</label>
				<div class="col-xs-2">
					<select class="form-control" name="employee_id"
						style="line-height: 35px; height: 35px; padding: 0px;">
						<option value="">请选择</option>
						<c:forEach items="${employees }" var="employee">
							<option value="${employee.employee_id }">${employee.employee_name }</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="form-group clearfix"
				style="margin-top: 0px; margin-bottom: 5px;">
				<label class="col-xs-1 right_wz">录入时间从：</label>
				<div class="col-xs-2">
					<input class="layui-input" placeholder="选择录入开始时间" name="start_time"
						id="start_time">
				</div>
				<label class="col-xs-1 right_wz">到：</label>
				<div class="col-xs-2">
					<input class="layui-input" placeholder="选择录入结束时间" name="end_time"
						id="end_time">
				</div>
				<label class="col-xs-1 right_wz">报名计划：</label>
				<div class="col-xs-2">
					<select class="form-control" name="entryplanId"
						style="line-height: 35px; height: 35px; padding: 0px;">
						<option value="">请选择</option>
						<c:forEach items="${entryPlans }" var="plan">
							<option value="${plan.entryplan_id }">${plan.entryplan_content }</option>
						</c:forEach>
					</select>
				</div>
				<label class="col-xs-1 right_wz">报考学校：</label>
				<div class="col-xs-2">
					<select class="form-control" onchange="get_zhuanye()"
						name="university_id" id="university_id"
						style="line-height: 35px; height: 35px; padding: 0px;">
						<option value="">请选择</option>
						<c:forEach items="${universities }" var="daxue">
							<option value="${daxue.university_id }">${daxue.university_name }</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="form-group clearfix"
				style="margin-top: 0px; margin-bottom: 5px;">
				<label class="col-xs-1 right_wz">专业：</label>
				<div class="col-xs-2">
					<select class="form-control" name="zhuanye_id" id="zhuanye_id"
						style="line-height: 35px; height: 35px; padding: 0px;">
						<option value="">请选择</option>
					</select>
				</div>
				<label class="col-xs-1 right_wz">班次：</label>
				<div class="col-xs-2">
					<select class="form-control" name="mianshoubanxing"
						style="line-height: 35px; height: 35px; padding: 0px;">
						<option value="">请选择</option>
						<option value="基础班">基础班</option>
						<option value="精讲班">精讲班</option>
					</select>
				</div>
				<label class="col-xs-1 right_wz">缴费状态：</label>
				<div class="col-xs-2">
					<select class="form-control" name="isPay"
						style="line-height: 35px; height: 35px; padding: 0px;">
						<option value="">请选择</option>
						<option value="1">已支付</option>
						<option value="0">未支付</option>
					</select>
				</div>
				<label class="col-xs-1 right_wz">支付方式：</label>
				<div class="col-xs-2">
					<select class="form-control" name="payType"
						style="line-height: 35px; height: 35px; padding: 0px;">
						<option value="">请选择</option>
						<option value="微信">微信</option>
						<option value="支付宝">支付宝</option>
					</select>
				</div>
			</div>
			<div class="form-group clearfix"
				style="margin-top: 0px; margin-bottom: 5px;">
				<label class="col-xs-1 right_wz">支付金额：</label>
				<div class="col-xs-2">
					<input class="form-control" id="price" name="price"
						placeholder="输入支付金额">
				</div>
				<label class="col-xs-1 right_wz">报名时间从：</label>
				<div class="col-xs-2">
					<input class="layui-input" placeholder="选择报名开始时间" name="bao_start_time"
						id="bao_start_time">
				</div>
				<label class="col-xs-1 right_wz">到：</label>
				<div class="col-xs-2">
					<input class="layui-input" placeholder="选择报名结束时间" name="bao_end_time"
						id="bao_end_time">
				</div>
				<button class="shaixuan" onclick="shaixuan_tioajian(this,${limit})">筛选</button>
			</div>
		</form>
		<div class="operation">
			<div class="opera_left left">
				<!--左侧按钮  -->
				<button type="button" onclick="daochu_entry_info()" class="btn left">
					<i class="fa fa-cloud-download"></i>导出数据
				</button>
				<button type="button" onclick="daochu_bufei_info()" class="btn left">
					<i class="fa fa-cloud-download"></i>导出补费信息
				</button>
				<%--   <select class="form-control select left top_select" onchange="get_entry_info_state(this,${limit })"
						data-toggle="select" id="select_val" style="margin-left:20px;">
						<option value="0">请选择审核状态</option>
						<option value="2">财务审核成功</option>
						<option value="4">财务审核失败</option>
						<option value="3">教务审核成功</option>
						<option value="5">教务审核失败</option>
				  </select> --%>
			</div>
			<div class="opera_right right"></div>
		</div>
		<div class="content_message">
			<table class="table table-hover">
				<thead>
					<tr class="tr_bgcolor warning">
						<th>编号</th>
						<!-- <th>订单号</th> -->
						<th>业务员</th>
						<th>所属部门</th>
						<th>学员姓名</th>
						<th>学员身份证号</th>
						<th>报考类别</th>
						<th>报考专业</th>
						<th>缴费状态</th>
						<th>缴费金额</th>
						<th>支付方式</th>
						<th>审核状态</th>
						<th>退费状态</th>
						<th>录入时间</th>
						<th>操作</th>
					</tr>
				</thead>
				<c:if test="${not empty entryInfos.list }">
					<tbody>
						<c:forEach items="${entryInfos.list }" var="info" varStatus="vs">
							<c:if test="${vs.count%2 == '0' }">
								<tr class="active">
							</c:if>
							<c:if test="${vs.count%2 != '0' }">
								<tr>
							</c:if>
							<td><label class="label label-success btn-primary"
								for="minimal-checkbox-1">${vs.index+1+entryInfos.begin }</label></td>
							<%-- <td>${info.orderNumber }</td> --%>
							<td><c:if test="${not empty info.employee.employee_name }">
							${info.employee.employee_name }
							</c:if> <c:if test="${empty info.employee.employee_name }">
							暂无业务员
							</c:if></td>
							<td>
							<c:if test="${not empty info.employee.employee_name }">
							${info.employee.organization.organization_name }
							</c:if>
							 <c:if test="${empty info.employee.employee_name }">
							暂无
							</c:if>
							</td>
							<td>${info.entryUserName }</td>
							<td>${info.documentNumber }</td>
							<td>${info.courseMenu.course_name }(${info.entryPlan.entryplan_content })</td>
							<td><c:if test="${info.courseMenu.course_id==20 }">
							${info.xuexiao }-${info.zhuanye }
							</c:if> <c:if test="${info.courseMenu.course_id==19 }">
							${info.baokaocengci }-${info.baokaokemu }
							</c:if>
							 <c:if test="${info.courseMenu.course_id!=20&&info.courseMenu.course_id!=19 }">
							${info.dictionary.dictionary_name }
							</c:if></td>
							<td><c:if test="${info.isPay==1 }">
							已付款
							</c:if> <c:if test="${info.isPay!=1 }">
							未缴费
							</c:if></td>
							<td>${info.payMoney }</td>
							<td>${info.payType }</td>
							<td><c:if test="${info.entryInfoState==0 }">
								<span style="color: red;">未提交或者审核失败被打回</span>
							</c:if>
							<c:if test="${info.entryInfoState==1 }">
								<span style="color: green;">财务审核中</span>
							</c:if>
							<c:if test="${info.entryInfoState==2 }">
								<span style="color: green;">教务审核中</span>
							</c:if>
							<c:if test="${info.entryInfoState==3 }">
								<span style="color: green;">等待提交考试中心</span>
							</c:if>
							<c:if test="${info.entryInfoState==4 }">
								<span style="color: red;">财务审核失败</span>
							</c:if>
							<c:if test="${info.entryInfoState==5 }">
								<span style="color: red;">教务审核失败</span>
							</c:if>
							<c:if test="${info.entryInfoState==6 }">
								<span style="color: green;">已上报考试中心</span>
							</c:if>
							<c:if test="${info.entryInfoState==7}">
								部门经理审核中
							</c:if>
							<c:if test="${info.entryInfoState==8 }">
								市场总监审核中
							</c:if>
							<c:if test="${info.entryInfoState==9 }">
								总经理审核中
							</c:if>
							</td>
							<td>
							<c:if test="${info.tuifei_state==0 }">
								未退费
							</c:if>
							<c:if test="${info.tuifei_state==1 }">
								经理审核中
							</c:if>
							<c:if test="${info.tuifei_state==2 }">
								总监审核中
							</c:if>
							<c:if test="${info.tuifei_state==3 }">
								总经理审核中
							</c:if>
							<c:if test="${info.tuifei_state==4 }">
								财务审核中
							</c:if>
							<c:if test="${info.tuifei_state==5 }">
								退费成功
							</c:if>
							<c:if test="${info.tuifei_state==6 }">
								拒绝退费
							</c:if>
							</td>
							<td><fmt:formatDate type="both"
									value="${info.entryInfoTime }" /></td>
							<td>
								<button type="button"
									onclick="to_check_infopage(${info.entryInfoId })"
									class="btn btn-warning btn-xs">查看</button> <%-- <button type="button" onclick="to_check_condition(${info.entryInfoId })" class="btn btn-warning btn-xs">补缴费用</button> --%>
							</td>
							</tr>
						</c:forEach>
					</tbody>
				</c:if>
			</table>
			<c:if test="${ empty entryInfos.list }">
				<p class="zanwu" style="text-align: center; color: #53C1FE;">暂无相关学员信息</p>
			</c:if>
		</div>
		<div class="pages">
			<div class="whole left">
				<div class="go_page">
					<span>跳转到第</span><input type="text" id="page_num" /> <span>页</span>
					<a href="javascript:void(0)"
						onclick="info_tiaozhuan_page(${entryInfos.pages},${limit},'${entryUserName }','${ documentNumber}','${entryUserPosition }','${employee_id }','${start_time }','${end_time }','${ entryplanId}','${university_id }','${zhuanye_id }','${mianshoubanxing }','${isPay }','${payType }','${price }','${bao_start_time }','${bao_end_time }')"
						class="btn_go">GO</a>
				</div>
			</div>
			<ul class="pagination right">
				<li><a href="javascript:void(0)"
					onclick="info_jump_page(1,${limit },'${entryUserName }','${ documentNumber}','${entryUserPosition }','${employee_id }','${start_time }','${end_time }','${ entryplanId}','${university_id }','${zhuanye_id }','${mianshoubanxing }','${isPay }','${payType }','${price }','${bao_start_time }','${bao_end_time }')"
					class="fa fa-fast-backward"></a></li>
				<c:if test="${entryInfos.hasPreviousPage==true}">
					<li class="previous"
						onclick="info_jump_page(${entryInfos.pageNumber-1},${limit },'${entryUserName }','${ documentNumber}','${entryUserPosition }','${employee_id }','${start_time }','${end_time }','${ entryplanId}','${university_id }','${zhuanye_id }','${mianshoubanxing }','${isPay }','${payType }','${price }','${bao_start_time }','${bao_end_time }')"><a
						href="#fakelink" class="fa fa-angle-left"></a></li>
				</c:if>
				<c:forEach items="${entryInfos.navigatePageNumbers }" var="page">
					<c:choose>
						<c:when test="${entryInfos.pageNumber==page}">
							<li class="active"><a href="#fakelink">${page}</a></li>
						</c:when>
						<c:otherwise>
							<li
								onclick="info_jump_page(${page},${limit },'${entryUserName }','${ documentNumber}','${entryUserPosition }','${employee_id }','${start_time }','${end_time }','${ entryplanId}','${university_id }','${zhuanye_id }','${mianshoubanxing }','${isPay }','${payType }','${price }','${bao_start_time }','${bao_end_time }')"><a
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
							onclick="info_jump_page(${entryInfos.pageNumber},20,'${entryUserName }','${ documentNumber}','${entryUserPosition }','${employee_id }','${start_time }','${end_time }','${ entryplanId}','${university_id }','${zhuanye_id }','${mianshoubanxing }','${isPay }','${payType }','${price }','${bao_start_time }','${bao_end_time }')">20</a></li>
						<li><a href="#fakelink"
							onclick="info_jump_page(${entryInfos.pageNumber},50,'${entryUserName }','${ documentNumber}','${entryUserPosition }','${employee_id }','${start_time }','${end_time }','${ entryplanId}','${university_id }','${zhuanye_id }','${mianshoubanxing }','${isPay }','${payType }','${price }','${bao_start_time }','${bao_end_time }')">50</a></li>
						<li><a href="#fakelink"
							onclick="info_jump_page(${entryInfos.pageNumber},100,'${entryUserName }','${ documentNumber}','${entryUserPosition }','${employee_id }','${start_time }','${end_time }','${ entryplanId}','${university_id }','${zhuanye_id }','${mianshoubanxing }','${isPay }','${payType }','${price }','${bao_start_time }','${bao_end_time }')">100</a></li>
					</ul></li>
				<c:if test="${entryInfos.hasNextPage==true}">
					<li class="next"
						onclick="info_jump_page(${entryInfos.pageNumber+1},${limit },'${entryUserName }','${ documentNumber}','${entryUserPosition }','${employee_id }','${start_time }','${end_time }','${ entryplanId}','${university_id }','${zhuanye_id }','${mianshoubanxing }','${isPay }','${payType }','${price }','${bao_start_time }','${bao_end_time }')"><a
						href="#fakelink" class="fa fa-angle-right"></a></li>
				</c:if>
				<li
					onclick="info_jump_page(${entryInfos.pageNumber},${limit },'${entryUserName }','${ documentNumber}','${entryUserPosition }','${employee_id }','${start_time }','${end_time }','${ entryplanId}','${university_id }','${zhuanye_id }','${mianshoubanxing }','${isPay }','${payType }','${price }','${bao_start_time }','${bao_end_time }')"><a
					href="javascript:void(0)" class="fa fa-fast-forward"></a></li>
			</ul>
		</div>
	</div>