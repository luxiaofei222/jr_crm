<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" type="text/css"
	href="/css/school/back/common/reset.css">
<link rel="stylesheet" href="/css/school/back/common/bootstrap.min.css" />
<link rel="stylesheet"
	href="/css/school/back/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css"
	href="/css/school/back/layui/css/layui.css">
<link rel="stylesheet" type="text/css"
	href="/css/crm/index/lianxiren.css">
   <script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="/js/school/back/common/bootstrap.min.js"></script>
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<script>
//通话记录翻页
function lianxiren_jump_page(page,customer_id){
	jiazaidonghua();
	 $.post("/crm_business/get_record_list.jr", {
		'pageNumber' : page,
		'limit' : 20,
		'customer_id':customer_id
	}, function(data) {
		$("#record_list").html(data);
	}) 
}
$(function(){
	$(".person_message_info li input").focus(function(){
		$(this).removeClass("lianxi_input");
		$(this).addClass("lianxi_input1");
	})
	$(".person_message_info li input").blur(function(){
		$(this).removeClass("lianxi_input1");
		$(this).addClass("lianxi_input");
	})
	$(".person_ps textarea").focus(function(){
		$(this).removeClass("lianxi_input2");
		$(this).addClass("lianxi_input3");
	})
	$(".person_ps textarea").blur(function(){
		$(this).removeClass("lianxi_input3");
		$(this).addClass("lianxi_input2");
	})
	$(".input_name input").focus(function(){
		$(this).removeClass("lianxi_input_name");
		$(this).addClass("lianxi_input_name1");
	})
	$(".input_name input").blur(function(){
		$(this).removeClass("lianxi_input_name1");
		$(this).addClass("lianxi_input_name");
	})
	$(".input_bumen input").focus(function(){
		$(this).removeClass("lianxi_input_name2");
		$(this).addClass("lianxi_input_name3");
	})
	$(".input_bumen input").blur(function(){
		$(this).removeClass("lianxi_input_name3");
		$(this).addClass("lianxi_input_name2");
	})
})
//更新客户联系人姓名
function update_customer_info(customer_id){
	$("#myform").ajaxSubmit({
		type : 'POST',
		url : "/crm_business/update_lianxiren.jr",
		data:{
			'customer_id':customer_id,
			'calltype' : 0
		},
		success : function(data) {
			if(data==1){
				//添加数据成功，关闭弹出窗之前，刷新列表页面的数据
                tanchuang('恭喜您，修改成功');
			}else{
				tanchuang('很遗憾，修改失败');
			}
		},
		error : function(XmlHttpRequest, textStatus, errorThrown) {
			tanchuang('很遗憾，添加失败');
		}
	});
}
//添加跟进内容
function update_genjin_note(record_id){
	var genjin_state=$("#genjin_state").val();
	var record_note=$("#record_note").val();
	$.post("/crm_business/add_note_business_call.jr",{
		'record_id':record_id,
		'record_note':record_note,
		'genjin_state':genjin_state
	},function(data){
		if(data==1){
            tanchuang('恭喜您，修改成功');
		}else{
			tanchuang('很遗憾，修改失败');
		}
	})
}
//关闭弹窗
function close_layer(){
	var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
    parent.layer.close(index);
}
</script>
<form enctype="multipart/form-data" id="myform" class="form-horizontal">
<div class="check_person">
	<div class="person_left pull-left">
		<div class="person_name">
			<c:if test="${customer.customer_sex=='男' }">
				<img width="75px" height="71px"
					src="/images/crm/business/header_man.png" />
			</c:if>
			<c:if test="${customer.customer_sex=='女' }">
				<img src="/images/crm/business/person_header.png" />
			</c:if>
			<p class="input_name">
				<input type="text" value="${customer.customer_name }" name="customer_name" class="lianxi_input_name" placeholder="请输入您的姓名">
			</p>
			<p class="input_bumen">
				<input type="text" class="pull-left lianxi_input_name2" name="customer_depart" value="${customer.customer_depart }"  placeholder="请输入部门">
				<input type="text" class="pull-right lianxi_input_name2"  name="customer_position" value="${customer.customer_position }"  placeholder="请输入职位">
			</p>
		</div>
		<div class="person_message">
			<ul class="form-inline person_message_info">
				<li class="form-group clearfix">
					<i class="fa fa-phone-square"></i>
					<input type="text"  value="${customer.customer_officephone }" name="customer_officephone" placeholder="请输入座机电话" class="lianxi_input">
				</li>
				<li class="form-group clearfix">
					<i class="fa fa-phone"></i>
					<input type="text"  value="${customer.customer_phone }" name="customer_phone" placeholder="请输入手机号码" class="lianxi_input">
				</li>
				<li class="form-group clearfix">
					<i class="fa fa-qq"></i>
					<input type="text"  value="${customer.customer_qq }" name="customer_qq" placeholder="请输入QQ号" class="lianxi_input">
				</li>
				<li class="form-group clearfix">
					<i class="fa fa-wechat"></i>
					<input type="text"  value="${customer.customer_weixin }" name="customer_weixin"  placeholder="请输入微信" class="lianxi_input">
				</li>
				<li class="form-group clearfix">
					<i class="fa fa-envelope"></i>
					<input type="text"  value="${customer.customer_mail }" name="customer_mail"  placeholder="请输入邮箱" class="lianxi_input">
				</li>
				<%-- <li class="form-group clearfix">
					<i class="fa fa-tags"></i>
					<input type="text"  value="${courseMenu.course_name }" placeholder="请输入报考科目" class="lianxi_input">
				</li> --%>
			</ul>
			<div class="person_ps clearfix">
				<label class="pull-left">备注：</label>
				<textarea  placeholder="请输入备注" name="customer_note"  class="lianxi_input2">${customer.customer_note }</textarea>
			</div>
		</div>
		<button type="button" onclick="update_customer_info(${customer.customer_id})" class='btn btn-sm pull-right btn_xiu'>修改</button>
	</div>
	<div class="person_right pull-right" id="record_list">
		<table class="table table-hover">
			<thead>
				<tr class="tr_bgcolor">
					<th>记录时间</th>
					<th>主叫号码</th>
					<th>被叫号码</th>
					<th>通话时长</th>
					<th>跟进内容</th>
				</tr>
			</thead>
			<c:if test="${not empty records.list }">
				<tbody>
					<c:forEach items="${records.list }" var="record" varStatus="vs">
						<c:if test="${vs.count%2 == '0' }">
							<tr class="active">
						</c:if>
						<c:if test="${vs.count%2 != '0' }">
							<tr>
						</c:if>
						<td><fmt:formatDate type="both" value="${record.call_time }" /></td>
						<c:if test="${record.call_type=='呼出' }">
						<td>${record.employee.employee_name }/${record.zuoxi }</td>
						<td>${record.called_phone }</td>
						</c:if>
						<c:if test="${record.call_type=='呼入' }">
						<td>${record.zuoxi }</td>
						<td>${record.employee.employee_name }/${record.called_phone }</td>
						</c:if>
						<td>${record.record_time }</td>
						<td>${record.record_note }</td>
						</tr>
					</c:forEach>
				</tbody>
			</c:if>
		</table>
		<c:if test="${ empty records.list }">
			<p class="zanwu" style="text-align: center; color: #94CE6E;">暂无该联系人通话记录</p>
		</c:if>
		<div class="pages">
			<ul class="pagination pull-right">
				<c:if test="${companies.hasPreviousPage==true}">
					<li class="previous"
						onclick="lianxiren_jump_page(${companies.pageNumber-1},${customer.customer_id })"><a
						href="#fakelink" class="fa fa-angle-left"></a></li>
				</c:if>
				<c:forEach items="${companies.navigatePageNumbers }" var="page">
					<c:choose>
						<c:when test="${companies.pageNumber==page}">
							<li class="active"
								onclick="lianxiren_jump_page(${page},${customer.customer_id })"><a
								href="#fakelink">${page}</a></li>
						</c:when>
						<c:otherwise>
							<li
								onclick="lianxiren_jump_page(${page},${customer.customer_id })"><a
								href="#fakelink">${page}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${companies.hasNextPage==true}">
					<li class="next"
						onclick="lianxiren_jump_page(${companies.pageNumber+1},${customer.customer_id })">><a
						href="#fakelink" class="fa fa-angle-right"></a></li>
				</c:if>
			</ul>
		</div>
	</div>
	<div style="clear: both;"></div>
	<div style="overflow:hidden;margin-top:10px;">
		<label class='pull-left'>跟进状态</label>
		<select class='pull-left lianxi_select'id="genjin_state">
			<option value="继续跟进">继续跟进</option>
			<option value="已经成交">已经成交</option>
			<option value="无意向">无意向</option>
		</select>
	</div>
	<h6 style="font-size: 15px; color: #444;margin-top;5px;margin-bottom:5px;">跟进内容：</h6>
	<div class="form-group clearfix">
		<textarea class="form-control pull-left" id="record_note"  rows="4" style="color: #444;width:90%;"></textarea>
		<button type="button" onclick="update_genjin_note(${record_id})" class="btn btn-lg pull-right"  style="background-color: #94CE6E; color: white;margin-right:8px;margin-top:30px;" >添加</button>
	</div>
	<div class="form-group" style="margin-top: 15px;">
		<div class="col-xs-12" style="text-align: center;">
			<button type="button" class="btn btn-lg" onclick="close_layer()"
				style="width: 100px;background-color: #94CE6E; color: white;">关闭</button>
		</div>
	</div>
</div>
</form>