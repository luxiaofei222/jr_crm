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
function employee_jump_page(page,limit,card_number,bumen_id,xingbie,birthday,xingming,ruzhi_time_start_str,ruzhi_time_end_str,gangwei_state){
	jiazaidonghua();
	$.post("/oa_employee/get_oa_employee_list.jr",{
		 'card_number':card_number,
		 'bumen_id':bumen_id,
		 'xingbie':xingbie,
		 'birthday':birthday,
		 'xingming':xingming,
		 'ruzhi_time_start_str':ruzhi_time_start_str,
		 'ruzhi_time_end_str':ruzhi_time_end_str,
		 'gangwei_state':gangwei_state,
		'pageNumber' : page,
		'limit' : limit
	},function(data){
		$("#conten_list").html(data);
	})
}
//跳转页面
function employee_tiaozhuan_page(page,limit,card_number,bumen_id,xingbie,birthday,xingming,ruzhi_time_start_str,ruzhi_time_end_str,gangwei_state){
	var page_num=$("#page_num").val();
	if(!isNaN(page_num)){
		if(page<page_num||page_num<0){
			layer.msg("你输入的页数不存在！")
		}else{
			jiazaidonghua();
			 $.post("/oa_employee/get_oa_employee_list.jr", {
				 'card_number':card_number,
				 'bumen_id':bumen_id,
				 'xingbie':xingbie,
				 'birthday':birthday,
				 'xingming':xingming,
				 'ruzhi_time_start_str':ruzhi_time_start_str,
				 'ruzhi_time_end_str':ruzhi_time_end_str,
				 'gangwei_state':gangwei_state,
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
		  document.getElementById('birthday').onclick = function(){
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
			url : "/oa_employee/get_oa_employee_list.jr",
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
//查看修改员工信息
function to_update_employee(oa_employee_id,limit,page){
	jiazaidonghua();
	 $.post("/oa_employee/to_update_employee.jr", {
		 'oa_employee_id':oa_employee_id,
		 'pageNumber' : page,
		 'limit' : limit
		}, function(data) {
			$("#conten_list").html(data);
		})
}
//修改岗位
function to_update_gangwei(oa_employee_id,state,page,limit){
	if(state=="离职"){
		layer.open({
			  type: 2,
			  title: ['员工离职'],
			  area: ['500px', '400px'],
			  shadeClose: false, //点击遮罩关闭
			  content: "/oa_employee/to_add_lizhitime.jr?oa_employee_id="+oa_employee_id
			  });
	}else{
		$.post("/oa_employee/update_oa_employee_gangwei.jr",{
			'oa_employee_id':oa_employee_id,
			'gangwei_state':state
		},function(data){
			if (data == 1) {
				//添加数据成功，关闭弹出窗之前，刷新列表页面的数据
				tanchuang('恭喜您，设置成功');
				employee_jump_page(page,limit);
			} else {
				tanchuang('很遗憾，设置失败');
			}
		})
	}
}
//绑定账号
function to_bangding_zhanghao(oa_employee_id,xingming){
	 layer.open({
		  type: 2,
		  title: ['绑定账号'],
		  area: ['500px', '300px'],
		  shadeClose: false, //点击遮罩关闭
		  content: "/oa_employee/to_bangding_zhanghao.jr?oa_employee_id="+oa_employee_id+"&xingming="+xingming
		  });
}
//解除绑定账号
function to_jiechu_zhanghao(oa_employee_id,state){
	$.post("/oa_employee/update_oa_employee_gangwei.jr",{
		'employee_id':state,
		'oa_employee_id':oa_employee_id
	},function(data){
		if(data==1){
			tanchuang('恭喜您，设置成功');
		}else{
			tanchuang('很遗憾，设置失败');
		}
	})
}
//删除员工信息
function to_delete_employee(oa_employee_id,limit,page){
	layer.confirm("提示：您好，确定要删除吗？",function(){
		layer.closeAll('dialog');
		$.post("/oa_employee/delete_employee.jr",{
			'oa_employee_id':oa_employee_id
		},function(data){
			if(data==1){
				employee_jump_page(page,limit);
			}else{
				tanchuang('很遗憾，系统发生错误');
			}
		})
	})
}
</script>
<div class="back_right">
	<div class="back_r_top">
		<div class="left">员工信息</div>
		<div class="right" id="timer"></div>
	</div>
	<div class="right_content">
		<form enctype="multipart/form-data" id="myform"
			class="form-horizontal">
			<div class="form-group clearfix"
				style="padding-top: 10px; margin-bottom: 5px;">
				<label class="col-xs-1 right_wz">姓名：</label>
				<div class="col-xs-2">
					<input class="form-control" id="xingming" name="xingming">
				</div>
				<label class="col-xs-1 right_wz">身份证号：</label>
				<div class="col-xs-2">
					<input class="form-control" id="card_number"
						name="card_number" >
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
				<label class="col-xs-1 right_wz">性别：</label>
				<div class="col-xs-2">
					<select class="form-control" name="xingbie"
						style="line-height: 35px; height: 35px; padding: 0px;">
						<option value="">请选择</option>
						<option value="男">男</option>
						<option value="女">女</option>
					</select>
				</div>
			</div>
			<div class="form-group clearfix"
				style="margin-top: 0px; margin-bottom: 5px;">
				<label class="col-xs-1 right_wz">入职时间从：</label>
				<div class="col-xs-2">
					<input class="layui-input" name="ruzhi_time_start_str"
						id="ruzhi_time_start">
				</div>
				<label class="col-xs-1 right_wz">到：</label>
				<div class="col-xs-2">
					<input class="layui-input" name="ruzhi_time_end_str"
						id="ruzhi_time_end">
				</div>
				<label class="col-xs-1 right_wz">生日：</label>
				<div class="col-xs-2">
					<input class="layui-input"  name="birthday"
						id="birthday">
				</div>
				<label class="col-xs-1 right_wz">职位状态：</label>
				<div class="col-xs-2">
					<select class="form-control" name="gangwei_state"
						style="line-height: 35px; height: 35px; padding: 0px;">
						<option value="">请选择</option>
						<option value="在职">在职</option>
						<option value="离职">离职</option>
					</select>
				</div>
			</div>
			<div class="form-group clearfix"
				style="margin-top: 0px; margin-bottom: 5px;">
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
						<th>性别</th>
						<th>入职时间</th>
						<th>生日</th>
						<th>联系电话</th>
						<th>转正日期</th>
						<th>岗位状态</th>
						<th>岗位</th>
						<th>建表日期</th>
						<th>操作</th>
					</tr>
				</thead>
				<c:if test="${not empty oaEmployees.list }">
					<tbody>
						<c:forEach items="${oaEmployees.list }" var="employee" varStatus="vs">
							<c:if test="${vs.count%2 == '0' }">
								<tr class="active">
							</c:if>
							<c:if test="${vs.count%2 != '0' }">
								<tr>
							</c:if>
							<td><label class="label label-success btn-primary"
								for="minimal-checkbox-1">${vs.index+1+oaEmployees.begin }</label></td>
							<td>${employee.xingming }</td>
							<td>${employee.xingbie }</td>
							<td><fmt:formatDate pattern="yyyy-MM-dd"
									value="${employee.ruzhi_time }" /></td>
							<td>${employee.birthday }</td>
							<td>${employee.phone }</td>
							<td><fmt:formatDate  pattern="yyyy-MM-dd"
									value="${employee.zhuanzheng_time }" /></td>
							<td>${employee.gangwei_state }
							<c:if test="${employee.gangwei_state =='离职'}">
								(${employee.lizhi_time})
							</c:if>
							</td>
							<td>${employee.organization.organization_name }</td>
							<td><fmt:formatDate type="both"
									value="${employee.oa_employee_time }" /></td>
							<td>
								<button type="button"
										onclick="to_update_employee(${employee.oa_employee_id },${limit },${oaEmployees.pageNumber})"
										class="btn btn-success btn-xs">修改/查看</button>
									<c:if test="${employee.gangwei_state =='在职'}">
									<button type="button"
										onclick="to_update_gangwei(${employee.oa_employee_id },'离职',${oaEmployees.pageNumber},${limit })"
										class="btn btn-warning btn-xs">离职</button>
									</c:if>
									<c:if test="${employee.gangwei_state =='离职'}">
									<button type="button"
										onclick="to_update_gangwei(${employee.oa_employee_id },'在职',${oaEmployees.pageNumber},${limit })"
										class="btn btn-warning btn-xs">上岗</button>
									</c:if>
									<c:if test="${employee.employee_id!=0 }">
									<button type="button"
										onclick="to_jiechu_zhanghao(${employee.oa_employee_id },0)"
										class="btn btn-info btn-xs">解除账号</button>
									</c:if>
									<c:if test="${ employee.employee_id==0 }">
									<button type="button"
										onclick="to_bangding_zhanghao(${employee.oa_employee_id },'${employee.xingming }')"
										class="btn btn-info btn-xs">绑定账号</button>
									</c:if>
									<button type="button"
										onclick="to_delete_employee(${employee.oa_employee_id },${limit },${oaEmployees.pageNumber})"
										class="btn btn-success btn-xs">删除</button>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</c:if>
			</table>
			<c:if test="${ empty oaEmployees.list }">
				<p class="zanwu" style="text-align: center; color: #53C1FE;">暂无员工信息</p>
			</c:if>
		</div>
		<div class="pages">
			<div class="whole left">
				<div class="go_page">
					<span>第</span><input type="text" id="page_num" /> <span>页</span> <a
						href="javascript:void(0)"
						onclick="employee_tiaozhuan_page(${oaEmployees.pages},${limit},'${card_number }','${bumen_id }','${xingbie }','${birthday }','${xingming }','${ruzhi_time_start_str }','${ruzhi_time_end_str }','${gangwei_state }')"
						class="btn_go">GO</a>
				</div>
			</div>
			<ul class="pagination right">
				<li><a href="javascript:void(0)"
					onclick="employee_jump_page(1,${limit },'${card_number }','${bumen_id }','${xingbie }','${birthday }','${xingming }','${ruzhi_time_start_str }','${ruzhi_time_end_str }','${gangwei_state }')"
					class="fa fa-fast-backward"></a></li>
				<c:if test="${oaEmployees.hasPreviousPage==true}">
					<li class="previous"
						onclick="employee_jump_page(${oaEmployees.pageNumber-1},${limit },'${card_number }','${bumen_id }','${xingbie }','${birthday }','${xingming }','${ruzhi_time_start_str }','${ruzhi_time_end_str }','${gangwei_state }')"><a
						href="#fakelink" class="fa fa-angle-left"></a></li>
				</c:if>
				<c:forEach items="${oaEmployees.navigatePageNumbers }" var="page">
					<c:choose>
						<c:when test="${oaEmployees.pageNumber==page}">
							<li class="active"><a href="#fakelink">${page}</a></li>
						</c:when>
						<c:otherwise>
							<li
								onclick="employee_jump_page(${page},${limit },'${card_number }','${bumen_id }','${xingbie }','${birthday }','${xingming }','${ruzhi_time_start_str }','${ruzhi_time_end_str }','${gangwei_state }')"><a
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
							onclick="employee_jump_page(${oaEmployees.pageNumber},20,'${card_number }','${bumen_id }','${xingbie }','${birthday }','${xingming }','${ruzhi_time_start_str }','${ruzhi_time_end_str }','${gangwei_state }')">20</a></li>
						<li><a href="#fakelink"
							onclick="employee_jump_page(${oaEmployees.pageNumber},50,'${card_number }','${bumen_id }','${xingbie }','${birthday }','${xingming }','${ruzhi_time_start_str }','${ruzhi_time_end_str }','${gangwei_state }')">50</a></li>
						<li><a href="#fakelink"
							onclick="employee_jump_page(${oaEmployees.pageNumber},100,'${card_number }','${bumen_id }','${xingbie }','${birthday }','${xingming }','${ruzhi_time_start_str }','${ruzhi_time_end_str }','${gangwei_state }')">100</a></li>
					</ul></li>
				<c:if test="${oaEmployees.hasNextPage==true}">
					<li class="next"
						onclick="employee_jump_page(${oaEmployees.pageNumber+1},${limit },'${card_number }','${bumen_id }','${xingbie }','${birthday }','${xingming }','${ruzhi_time_start_str }','${ruzhi_time_end_str }','${gangwei_state }')"><a
						href="#fakelink" class="fa fa-angle-right"></a></li>
				</c:if>
				<li
					onclick="employee_jump_page(${oaEmployees.pages},${limit },'${card_number }','${bumen_id }','${xingbie }','${birthday }','${xingming }','${ruzhi_time_start_str }','${ruzhi_time_end_str }','${gangwei_state }')"><a
					href="javascript:void(0)" class="fa fa-fast-forward"></a></li>
			</ul>
		</div>
	</div>