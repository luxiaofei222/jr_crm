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
	/*调用 laydate*/
	$(function() {
		layui.use('laydate', function() {
			var laydate = layui.laydate;
			var start = {
				festival : true,
				istoday : true
			};
			document.getElementById('end_time').onclick = function() {
				start.elem = this;
				laydate(start);
			}
			document.getElementById('start_time').onclick = function() {
				start.elem = this;
				laydate(start);
			}
			document.getElementById('bao_start_time').onclick = function() {
				start.elem = this;
				laydate(start);
			}
			document.getElementById('bao_end_time').onclick = function() {
				start.elem = this;
				laydate(start);
			}
		});
	})
	//关闭弹窗
	function close_layer() {
		var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
		parent.layer.close(index);
	}
	//导出学员信息
	function satrt_daochu() {
		$(".loading").css("display", "block");
		$("#myform").ajaxSubmit({
			type : 'POST',
			url : "/fin_entry_info/get_down_load_record_url.jr",
			success : function(data) {
				location.href = data;
				$(".loading").css("display", "none");
			},
			error : function(XmlHttpRequest, textStatus, errorThrown) {
				tanchuang('查询失败');
			}
		});
	}
</script>
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
	width: 120%;
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
	border-radius: 5px;
}

.guanbi {
	background-color: #5CB85C;
}
</style>
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
	<form class="form-horizontal import_xueyuanshuju_dialog"
		enctype="multipart/form-data" id="myform" style="padding: 20px;">
		<form enctype="multipart/form-data" id="myform"
			class="form-horizontal">
			<div class="form-group clearfix"
				style="padding-top: 10px; margin-bottom: 5px; margin-left: -90px;">
				<label class="col-xs-2 right_wz">学员姓名：</label>
				<div class="col-xs-2">
					<input class="form-control" id="entryUserName" name="entryUserName"
						placeholder="输入学员姓名">
				</div>
				<label class="col-xs-2 right_wz">身份证号：</label>
				<div class="col-xs-2">
					<input class="form-control" id="documentNumber"
						name="documentNumber" placeholder="输入身份证号">
				</div>
				<label class="col-xs-2 right_wz">所在部门：</label>
				<div class="col-xs-2">
					<input class="form-control" id="entryUserPosition"
						name="entryUserPosition" placeholder="所在部门">
				</div>
			</div>
			<div class="form-group clearfix"
				style="margin-top: 0px; margin-bottom: 5px; margin-left: -90px;">
				<label class="col-xs-2 right_wz">业务员：</label>
				<div class="col-xs-2">
					<select class="form-control" name="employee_id"
						style="line-height: 35px; height: 35px; padding: 0px;">
						<option value="">请选择</option>
						<c:forEach items="${employees }" var="employee">
							<option value="${employee.employee_id }">${employee.employee_name }</option>
						</c:forEach>
					</select>
				</div>
				<label class="col-xs-2 right_wz">录入时间从：</label>
				<div class="col-xs-2">
					<input class="layui-input" placeholder="选择报名开始时间" name="start_time"
						id="start_time">
				</div>
				<label class="col-xs-2 right_wz">到：</label>
				<div class="col-xs-2">
					<input class="layui-input" placeholder="选择报名结束时间" name="end_time"
						id="end_time">
				</div>
			</div>
			<div class="form-group clearfix"
				style="margin-top: 0px; margin-bottom: 5px; margin-left: -90px;">
				<label class="col-xs-2 right_wz">报名计划：</label>
				<div class="col-xs-2">
					<select class="form-control" name="entryplanId"
						style="line-height: 35px; height: 35px; padding: 0px;">
						<option value="">请选择</option>
						<c:forEach items="${entryPlans }" var="plan">
							<option value="${plan.entryplan_id }">${plan.entryplan_content }</option>
						</c:forEach>
					</select>
				</div>
				<label class="col-xs-2 right_wz">报考学校：</label>
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
				<label class="col-xs-2 right_wz">专业：</label>
				<div class="col-xs-2">
					<select class="form-control" name="zhuanye_id" id="zhuanye_id"
						style="line-height: 35px; height: 35px; padding: 0px;">
						<option value="">请选择</option>
					</select>
				</div>
			</div>
			<div class="form-group clearfix"
				style="margin-top: 0px; margin-bottom: 5px; margin-left: -90px;">
				<label class="col-xs-2 right_wz">班次：</label>
				<div class="col-xs-2">
					<select class="form-control" name="mianshoubanxing"
						style="line-height: 35px; height: 35px; padding: 0px;">
						<option value="">请选择</option>
						<option value="基础班">基础班</option>
						<option value="精讲班">精讲班</option>
					</select>
				</div>
				<label class="col-xs-2 right_wz">审核状态：</label>
				<div class="col-xs-2">
					<select class="form-control" name="isPay"
						style="line-height: 35px; height: 35px; padding: 0px;">
						<option value="">请选择</option>
						<option value="1">审核成功</option>
						<option value="0">审核失败</option>
					</select>
				</div>
				<label class="col-xs-2 right_wz">支付方式：</label>
				<div class="col-xs-2">
					<select class="form-control" name="payType"
						style="line-height: 35px; height: 35px; padding: 0px;">
						<option value="">请选择</option>
						<option value="支付宝">支付宝</option>
						<option value="微信">微信</option>
						<option value="转账">转账</option>
						<option value="刷卡">刷卡</option>
						<option value="现金">现金</option>
						<option value="穆工行">穆工行</option>
						<option value="穆工行">穆工行</option>
						<option value="吕工行">吕工行</option>
						<option value="吕建行">吕建行</option>
						<option value="吕农行">吕农行</option>
						<option value="沧州对公">沧州对公</option>
						<option value="中国邮政">中国邮政</option>
					</select>
				</div>

			</div>
			<div class="form-group clearfix"
				style="margin-top: 0px; margin-bottom: 5px; margin-left: -90px;">
				<label class="col-xs-2 right_wz">交费时间从：</label>
				<div class="col-xs-2">
					<input class="layui-input" placeholder="选择报名开始时间"
						name="bao_start_time" id="bao_start_time">
				</div>
				<label class="col-xs-2 right_wz">到：</label>
				<div class="col-xs-2">
					<input class="layui-input" placeholder="选择报名结束时间"
						name="bao_end_time" id="bao_end_time">
				</div>
				<label class="col-xs-2 right_wz ">支付金额：</label>
				<div class="col-xs-2">
					<input class="form-control" id="price" name="price"
						placeholder="输入支付金额">
				</div>
			</div>
		</form>
		<div class="form-group">
			<!--        <label for="inputPassword1" class="col-xs-2 control-label">&nbsp;</label> -->
			<div class="col-xs-6 right_wz">
				<button type="button" onclick="satrt_daochu()" class="shaixuan">开始导出</button>
			</div>
			<div class="col-xs-6">
				<button type="button" onclick="close_layer()"
					class="shaixuan guanbi">关闭</button>
			</div>
		</div>
	</form>
</div>