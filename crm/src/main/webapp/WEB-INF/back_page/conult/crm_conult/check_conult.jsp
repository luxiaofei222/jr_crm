<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="/css/school/back/common/reset.css">
<link rel="stylesheet" href="/css/school/back/common/bootstrap.min.css" />
<link rel="stylesheet" type="text/css"
	href="/css/school/back/layui/css/layui.css">
<script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<title>查看网询客户详情</title>
<style>
 table.info tr td{
 	text-align:center;
 }
 table.info tr td:nth-child(2n+1){
 	font-weight:bold;
 	color:#fff;
 	background-color:#06C1AE;
 }
 table.record tr td,table.record tr th{
 	text-align:center;
 }
</style>
<script>
//同步信息
function tongbu_call_record(obj,record_id,uid,time,is_ip){
	if(is_ip==1){
		$(obj).attr({"disabled":"disabled"});
		$(obj).html("同步中");
		$.ajax({
	        type: 'GET',  //这里用GET
	        url: 'http://192.168.1.251/api.php', //获取通话记录
	        dataType: 'jsonp',  //类型
	        data: {'f':'getCallInfo','uniqueid':uid,'pop_time':time},//上线后更改为 phone
	        jsonp: 'callback', //jsonp回调参数，必需
	        async: false,
	        success: function(result) {//返回的json数据
	       	var str = unescape(result.replace(/\\u/g, "%u")); 
	       	if(str.length>=2){
	       		var json = eval('('+str+')');
		        	$.post("/crm_conult/update_conult_call.jr",{
		        		'record_id':record_id,
		        		'record_time':json.billsec,
		        		'sound_file':json.userfield,
		        		'call_state':json.disposition
		        	},function(data){
		        		if(data==1){
		        			$(obj).html("同步信息");
							$(obj).removeAttr("disabled");
		        			layer.msg("同步成功！")
		        		}else{
		        			$(obj).html("同步信息");
							$(obj).removeAttr("disabled");
		        			layer.msg("同步失败");
		        		}
		        	})
	       	}else{
	       		$(obj).html("同步信息");
				$(obj).removeAttr("disabled");
				layer.msg("同步失败");
	       	}
	        }
	    })
	}else{
		layer.msg("您不在公司哦，无法操作！");
	}
}

//试听录音文件  
function listen_the_tape(record_id,uid,time,is_ip){
	if(is_ip==1){
		layer.open({
			type : 2,
			title : [ '试听录音' ],
			area : [ '500px', '200px' ],
			shadeClose : false, //点击遮罩关闭
			content : "/crm_call/listen_the_tape.jr?uniqueid="+uid+"&date="+time+"&record_id="+record_id
		});
	}else{
		layer.msg("您不在公司哦，无法试听！")
	}
}
</script>
</head>
<body style="padding:20px;">
	<table class="table table-bordered table-striped table-hover info">
		<tbody>
			<tr>
				<td>所属分类</td>
				<td>${netConult.course_name }</td>
				<td>等级</td>
				<td>${netConult.dictionary_name }</td>
				<td>客户姓名</td>
				<td>${netConult.user_name }</td>
			</tr>
			<tr>
				<td>客户性别</td>
				<td>${netConult.user_sex }</td>
				<td>客户电话</td>
				<td>${netConult.user_phone }</td>
				<td>客户QQ</td>
				<td>${netConult.user_qq }</td>
			</tr>
			<tr>
				<td>客户微信</td>
				<td>${netConult.user_weixin }</td>
				<td>意向学校</td>
				<td>${netConult.hope_sc }</td>
				<td>意向专业</td>
				<td>${netConult.zhuanye }</td>
			</tr>
			<tr>
				<td>目前学历</td>
				<td>${netConult.now_edu }</td>
				<td>意向学历</td>
				<td>${netConult.hope_edu }</td>
				<td>资讯方式</td>
				<td>${netConult.consult_type }</td>
			</tr>
			<tr>
				<td>搜索关键词</td>
				<td>${netConult.search_word }</td>
				<td>所在地区</td>
				<td>${netConult.province }-${netConult.city }-${netConult.area }</td>
				<td>客户IP</td>
				<td>${netConult.user_ip }</td>
			</tr>
			<tr>
				<td>资讯日期</td>
				<td><fmt:formatDate value="${netConult.zixun_time }" /></td>
				<td>录入日期</td>
				<td><fmt:formatDate value="${netConult.consult_time }" /></td>
				<td>目前业务状态：</td>
				<td><c:if test="${netConult.conult_state==0 }">
              	未回访
              </c:if> <c:if test="${netConult.conult_state==1 }">
              	正在联系
              </c:if> <c:if test="${netConult.conult_state==2 }">
              	已拒绝
              </c:if> <c:if test="${netConult.conult_state==3 }">
              	成交
              </c:if></td>
			</tr>
			<tr>
				<td>备注</td>
				<td colspan='5'>${netConult.note }</td>
			</tr>
			<tr>
				<td>问题描述</td>
				<td colspan='5'>${netConult.question_info }</td>
			</tr>
		</tbody>
	</table>
	<c:forEach items="${records }" var="record" varStatus="vs">
	 <table class="table table-hover record">
		<thead>
			<tr class="tr_bgcolor warning">
	            <th>序号</th>
	            <th>通话时长</th>
				<th>咨询顾问</th>
	            <th>通话状态</th>
	            <th>通话时间</th>
	            <th>备注</th>
	            <th>操作</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>
	              <label class="label label-success btn-primary" for="minimal-checkbox-1">${vs.index+1 }</label>
	            </td>
				<td>${record.sec_time_call}</td>
				<td>${record.employee.employee_name }</td>
	            
	            <c:if test="${record.call_state=='ANSWERED' }">
							<td>已接听</td>
							</c:if>
							<c:if test="${record.call_state=='NO ANSWER' }">
							<td>未接通</td>
							</c:if>
							<c:if test="${record.call_state=='FAILED' }">
							<td>失败</td>
							</c:if>
	            <td><fmt:formatDate type="both" value="${record.call_time }" /></td>
	            <td>${record.record_note }</td>
	             <td><c:if test="${record.record_time!=0 }">
		<button type="button" onclick="listen_the_tape(${record.record_id },'${record.crm_recourd_uid}','${record.shijianchuo }',${is_ip })" class="btn btn-danger btn-xs">试听</button>
	</c:if>
	<button type="button" onclick="tongbu_call_record(this,${record.record_id },'${record.crm_recourd_uid }','${record.time_long }',${is_ip })" class="btn btn-success btn-xs">同步信息</button>
	</c:forEach></td>
			</tr>
		</tbody>
	</table>
</body>
</html>