<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<style>
.form-horizontal .form-group .wz_right {
	text-align: right;
}

body {
	color: #333;
}

.table>tbody>tr>td {
	vertical-align: middle;
}

.dangqian p {
	color: orange;
	display: inline-block;
}

.label {
	padding: 0.3rem 0.6rem;
}

td.status {
	position: relative;
}

td.status span {
	width: 15px;
	height: 15px;
	display: inline-block;
	border-radius: 50%;
	position: absolute;
	top: 15px;
	left: -10px;
}

td.status span.leisure {
	background-color: #0C6;
}

td.status span.no_online {
	background-color: #999;
}
td.status span.online {
	background-color: #FF9900;
}

td.status span.busying {
	background-color: #FF0033;
}
.colse i {
    float: right;
    font-size: 35px;
    color: #999;
    margin-top: -20px;
    margin-right: -20px;
}
</style>
<script>
//设置坐席
function add_zuoxi(extension,employee_id){
	$.post("/back_employee/update_employee.jr",{
		'employee_id':employee_id,
		'zuoxi':extension
	},function(data){
		if(data==1){
            tanchuang('恭喜您，设置成功');
		}else{
			tanchuang('很遗憾，设置失败');
		}
	})
}
//绑定坐席弹窗
function to_add_zuoxi(zuoxi){
	layer.open({
		  type: 2,
		  title: ['绑定坐席'],
		  area: ['500px', '300px'],
		  shadeClose: false, //点击遮罩关闭
		  content: "/back_zuoxi/to_add_zuoxi.jr?zuoxi="+zuoxi
		  });
}
//示忙
function to_shimang(zuoxi){
	 $.ajax({
         type: 'GET',  //这里用GET
         url: 'http://192.168.1.251/api.php',
         dataType: 'jsonp',  //类型
         data: {'f':'setBuys','extension':zuoxi},
         jsonp: 'callback', //jsonp回调参数，必需
         async: false,
         success: function(result) {//返回的json数据
        	var str = unescape(result.replace(/\\u/g, "%u")); 
        	var json = eval('('+str+')');
        	if($.trim(json.status)){
        		//如果电话接通开始记录通话记录
        		layer.msg("设置成功！");
        	}
         }
     })
}
//示闲
function to_shixian(zuoxi){
	 $.ajax({
         type: 'GET',  //这里用GET
         url: 'http://192.168.1.251/api.php',
         dataType: 'jsonp',  //类型
         data: {'f':'setIdle','extension':zuoxi},
         jsonp: 'callback', //jsonp回调参数，必需
         async: false,
         success: function(result) {//返回的json数据
        	var str = unescape(result.replace(/\\u/g, "%u")); 
        	var json = eval('('+str+')');
        	if($.trim(json.status)){
        		//如果电话接通开始记录通话记录
        		layer.msg("设置成功！");
        	}
         }
     })
}
//挂机
function to_guaji(zuoxi){
	 $.ajax({
         type: 'GET',  //这里用GET
         url: 'http://192.168.1.251/api.php',
         dataType: 'jsonp',  //类型
         data: {'f':'hangUp','extension':zuoxi},
         jsonp: 'callback', //jsonp回调参数，必需
         async: false,
         success: function(result) {//返回的json数据
        	var str = unescape(result.replace(/\\u/g, "%u")); 
        	var json = eval('('+str+')');
        	if($.trim(json.status)){
        		//如果电话接通开始记录通话记录
        		layer.msg("设置成功！");
        	}
         }
     })
}
</script>
<div class="zxshz">
<div class="zuoxi_shezhi" style="padding: 35px;">
	<table class="table table-hover">
		<thead>
			<tr class="tr_bgcolor warning">
				<th>编号</th>
				<th>分机号</th>
				<th>状态</th>
				<th>绑定账号</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${zuoxi_list }" var="zuoxi" varStatus="vs">
				 <c:if test="${vs.count%2 == '0' }">
                    <tr class="active">
                    </c:if>
                    <c:if test="${vs.count%2 != '0' }">
                    <tr>
                    </c:if>
					<td><label class="label label-success btn-primary"
						for="minimal-checkbox-1">${vs.index+1 }</label></td>
					<td>${zuoxi.extension }</td>
					<c:if test="${zuoxi.status==0 }">
					<td class="status"><span class="leisure"></span>空闲</td>
					</c:if>
					<c:if test="${zuoxi.status==8 }">
					<td class="status"><span class="ringing"></span>振铃</td>
					</c:if>
					<c:if test="${zuoxi.status==4 }">
					<td class="status"><span class="no_online"></span>不在线</td>
					</c:if>
					<c:if test="${zuoxi.status==1 }">
					<td class="status"><span class="online"></span>通话中</td>
					</c:if>
					<c:if test="${zuoxi.status==11 }">
					<td class="status"><span class="busying"></span>示忙</td>
					</c:if>
					<c:if test="${not empty zuoxi.employee }">
						<td style="color: green;">${zuoxi.employee.employee_name }</td>
					</c:if>
					<c:if test="${ empty zuoxi.employee }">
						<td>未绑定</td>
					</c:if>
					<td>
						<c:if test="${not empty zuoxi.employee }">
							<button type="button" onclick="add_zuoxi('0',${zuoxi.employee.employee_id })" class="btn btn-danger btn-xs">解除绑定</button>
						</c:if>
						<c:if test="${empty zuoxi.employee }">
							<button type="button" onclick="to_add_zuoxi('${zuoxi.extension }')" class="btn btn-info btn-xs">绑定账号</button>
						</c:if>
						<button type="button" onclick="to_shimang('${zuoxi.extension }')" class="btn btn-success btn-xs">示忙</button>
						<button type="button" onclick="to_shixian('${zuoxi.extension }')" class="btn btn-warning btn-xs">示闲</button>
						<button type="button" onclick="to_guaji('${zuoxi.extension }')" class="btn btn-default btn-xs">挂机</button>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
</div>