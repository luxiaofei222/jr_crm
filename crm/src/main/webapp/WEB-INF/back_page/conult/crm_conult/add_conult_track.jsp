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
<link rel="stylesheet" type="text/css" href="/dist/summernote.css" />
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
<script type="text/javascript" src='/js/system/distpicker.js'></script>
<script type="text/javascript" src='/js/system/main.js'></script>
<style>
form .form-group label {
	font-size: 15px;
	color: #444;
}

form .form-group input:focus, form .form-group textarea:focus {
	border-color: #e74c3c;
}

form .form-group .btn {
	margin-right: 30px;
}
table tr th,table tr td{
	text-align:center;
}
table.zhuizong tr td{
 	text-align:center;
    width: 112px;
    height: 35px;
    vertical-align:middle;
    line-height: 35px;
 }
 table.zhuizong tr td input:not(.layui-btn-danger),table.zhuizong tr td select{
 	width:100%;
 	height:100%;
 	border:none;
 	line-height: 35px;
    text-align: center;
 }
 .area{overflow:hidden;}
 .area select{float:left;width:30%!important;margin-left:5%;}
 .area select:nth-child(1){
 	margin-left:0%!important;
 }
</style>
<script>
	//添加客户信息
	function save_conult_track(obj,consult_id) {
		if (check_content()) {
			$(obj).attr({
				"disabled" : "disabled"
			});
			var conult_state=$("#conult_state").val();
			var content=$("#content").val();
			$.post("/crm_conult/save_conult_track.jr",{
				'consult_id':consult_id,
				'conult_state':conult_state,
				'content':content
			}, function(data) {
				if (data == 1) {
					//添加数据成功，关闭弹出窗之前，刷新列表页面的数据
					tanchuang('恭喜您，添加成功');
					$(obj).removeAttr("disabled");
					$("#content").val("");
				} else {
					tanchuang('很遗憾，添加失败');
					$(obj).removeAttr("disabled");
				}
			})
		}
	}
	//检查追踪内容
	function check_content(){
		var content=$.trim($("#content").val());
		if(content!=null&&content!=""){
			return true;
		}else{
			layer.msg("请输入您的追踪的详情！");
			return false;
		}
	}
	//修改客户信息
	function update_conult(obj,consult_id){
		$(obj).attr({
			"disabled" : "disabled"
		});
		var user_name=$("#user_name").val();
		var user_sex=$("#user_sex").val();
		var user_phone=$("#user_phone").val();
		var user_qq=$("#user_qq").val();
		var user_weixin=$("#user_weixin").val();
		var hope_sc=$("#hope_sc").val();
		var zhuanye=$("#zhuanye").val();
		var now_edu=$("#now_edu").val();
		var hope_edu=$("#hope_edu").val();
		var province=$("#province").val();
		var city=$("#city").val();
		var area=$("#area").val();
		$.post("/net_conult/update_conult.jr",{
			'consult_id':consult_id,
			'user_name':user_name,
			'user_phone':user_phone,
			'user_qq':user_qq,
			'user_weixin':user_weixin,
			'hope_sc':hope_sc,
			'zhuanye':zhuanye,
			'now_edu':now_edu,
			'hope_edu':hope_edu,
			'province':province,
			'city':city,
			'area':area
		},function(data) {
			if (data == 1) {
				//修改数据成功，关闭弹出窗之前，刷新列表页面的数据
				tanchuang('恭喜您，修改成功');
				$(obj).removeAttr("disabled");
			} else {
				tanchuang('很遗憾，修改失败');
				$(obj).removeAttr("disabled");
			}
		})
	}
</script>
<div style="padding:20px;">
<table class="table table-bordered zhuizong">
	<tbody>
		<tr>
			<td rowspan="4">学员基本信息</td>	
			<td>客户姓名</td>
			<td><input type="text" id="user_name" value="${netConult.user_name }" name="user_name"></td>
			<td>客户性别</td>
			<td>
				<select id="user_sex" name="user_sex">
					<option value="${netConult.user_sex }">${netConult.user_sex }</option>
					<option value="男">男</option>
					<option value="女">女</option>
				</select>
			</td>
			<td>客户电话</td>
			<td><input type="text" id="user_phone" value="${netConult.user_phone }" name="user_phone" ></td>
			<td rowspan="4">
				<input class="layui-btn layui-btn-danger" onclick="update_conult(this,${consult_id})" type="button" value="修改">
			</td>								
		</tr>
		<tr>
			<td>客户QQ</td>
			<td><input type="text" id="user_qq" value="${netConult.user_qq }" name="user_qq"></td>
			<td>客户微信</td>
			<td><input type="text" id="user_weixin" value="${netConult.user_weixin }" name="user_weixin"></td>			
			<td>意向学校</td>
			<td><input type="text" id="hope_sc" value="${netConult.hope_sc }" name="hope_sc"></td>
		</tr>
		<tr>	
			<td>意向专业</td>
			<td><input type="text" id="zhuanye" value="${netConult.zhuanye }" name="zhuanye"></td>
			<td>目前学历</td>
			<td><select name="now_edu" id="now_edu">
			<c:if test="${not empty netConult.now_edu }">
				<option value="${netConult.now_edu }">${netConult.now_edu }</option>
			</c:if>
				<option value="">请选择</option>
				<option value="初中">初中</option>
				<option value="中专">中专</option>
				<option value="高中">高中</option>
				<option value="专科">专科</option>
				<option value="本科">本科</option>
			</select></td>
			<td>意向学历</td>
			<td><select id="hope_edu" name="hope_edu" >
			<c:if test="${not empty netConult.hope_edu }">
				<option value="${netConult.hope_edu }">${netConult.hope_edu }</option>
			</c:if>
				<option value="">请选择</option>
				<option value="专科">专科</option>
				<option value="本科">本科</option>
				<option value="专/本">专/本</option>
			</select></td>
		</tr>
		<tr>
			<td>所在地区</td>
			<td colspan="5"><div data-toggle="distpicker" class="area"><select data-province='<c:if test="${empty netConult.province }">—— 省 ——</c:if><c:if test="${not empty netConult.province }">${netConult.province }</c:if>' name="province" id="province">
			</select>
			<select data-city='<c:if test="${empty netConult.city }">—— 市 ——</c:if><c:if test="${not empty netConult.city }">${netConult.city }</c:if>'   name="city" id="city">
			</select>
			<select data-district='<c:if test="${empty netConult.area }">—— 区 ——</c:if><c:if test="${not empty netConult.area }">${netConult.area }</c:if>' name="area" id="area">
			</select>
			</div>
			</td>
		</tr>
		<tr>
			<td  rowspan="2">客户追踪</td>
			<td>客户意向</td>
			<td colspan="5">
				<select id="conult_state" >
					<option value="1">继续追踪</option>
					<option value="2">客户拒绝</option>
					<option value="3">已成交</option>
				</select>
			</td>
			<td rowspan="3">
				<input class="layui-btn layui-btn-danger" onclick="save_conult_track(this,${consult_id})" type="button" value="添加">
			</td>	
		</tr>
		<tr>
			<td>追踪内容</td>
			<td colspan="5">
				<textarea class="form-control" onblur="check_content()" id="content"
				name="content" rows="3"></textarea>
			</td>
		</tr>
	</tbody>
</table>
</div>
<div style="padding: 0 20px;">
	<table class="table table-hover">
		<thead>
			<tr class="tr_bgcolor warning">
				<th>序号</th>
				<th>顾问</th>
				<th>状态</th>
				<th>内容</th>
				<th>时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${conTracks }" varStatus="vs" var="track">
				<tr>
					<td><label class="label label-success btn-primary"
						for="minimal-checkbox-1">${vs.index+1 }</label></td>
					<td>${track.employee.employee_name }</td>
					<td><c:if test="${track.conult_state==1 }">
	继续追踪
	</c:if> <c:if test="${track.conult_state==2 }">
	拒绝
	</c:if> <c:if test="${track.conult_state==3 }">
	成交
	</c:if></td>
					<td>${track.content}</td>
					<td><fmt:formatDate type="both" value="${track.track_time }" />
					</td>
				</tr>
			</c:forEach>
	</table>
	<c:if test="${empty conTracks }">
	<p style="text-align: center; font-size: 16px; color: orange;">暂无追踪记录</p>
	</c:if>
</div>
