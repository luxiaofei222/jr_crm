<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
<link rel="stylesheet" href="/css/school/back/common/bootstrap.min.css" />
<link rel="stylesheet" href="/css/school/back/common/flat-ui.min.css" />
<link rel="stylesheet"
	href="/css/school/back/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css"
	href="/css/school/back/layui/css/layui.css">
<link rel="stylesheet" href="/css/school/back/common/tankuang.css" />
<!-- <script src="/js/school/back/common/bootstrap.min.js"
	type="text/javascript"></script> -->
<script src="/js/school/back/common/flat-ui.min.js"></script>
<script src="/js/school/back/common/application.js"></script>
<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<title>查看通话记录详情</title>
<style>
table {
    width: 635px;
    margin: 0 auto;
    line-height: 40px;
    border: #94CE6E solid 1px;
}
table tr td {
    padding-left: 15px;
    border: #94CE6E solid 1px;
    color: #313131;
}
table tr td.title {
    background-color: #A8D988;
    font-weight: bold;
    color: #fff;
    text-align: center;
    padding-left: 0px;
    width: 30%;
}
table tr td textarea {
	width: 99%;
    height: 120px;
    margin: 5px auto;
    line-height: 25px;
    text-align: left;
    resize:none;
    border: none;
}
input.remarks {
    width: 90px;
    height: 30px;
    line-height: 30px;
    text-align: center;
    color: #fff;
    cursor: pointer;
    border: none;
    border-radius:3px;
    background-color: #94CE6E;
    margin-right: 10px;
    margin-top: 25px;
    float: right;
}
input.remarks:hover {
    background-color: #A8D988;
}
</style>
</head>
<body>
	<div style="padding: 20px;">
	    <table>
	      <tr>
	        <td class="title">客户名称</td>
	        <td>${businessCallRecord.customer.customer_name }</td>
	      </tr>
	      <tr>
	        <td class="title">时间</td>
	        <td><fmt:formatDate type="both" value="${businessCallRecord.call_time }" /></td>
	      </tr>
	      <tr>
	        <td class="title">呼叫类型</td>
	        <td>${businessCallRecord.call_type }</td>
	      </tr>
	      <tr>
	        <td class="title">状态</td>
	        <td><c:if test="${businessCallRecord.call_state=='ANSWERED' }">
				已接听
			</c:if>
			<c:if test="${businessCallRecord.call_state=='NO ANSWER' }">
				未接通
			</c:if></td>
	      </tr>
	      <tr>
	        <td class="title">跟进人</td>
	        <td>${businessCallRecord.employee.employee_name }(${businessCallRecord.employee.zuoxi})</td>
	      </tr>
	      <tr>
	        <td class="title">跟进内容</td>
	        <td><textarea id="record_note">${businessCallRecord.record_note }</textarea></td>
	      </tr>
	    </table>
	    <p style="color: orange;">注：如果有跟进内容，不要删除，否则你点击添加后会把你删掉的内容情空，有新的直接在后面追加即可！</p>
	    <input type="button" value="添加备注" onclick="update_record_note(${businessCallRecord.record_id})" class="remarks" />
	</div>
</body>
<script>
function update_record_note(record_id){
	var record_note=$("#record_note").val();
	if(record_note!=null&&record_note!=""){
		$.post("/crm_call/update_record_note.jr",{
			'record_id':record_id,
			'record_note':record_note
		},function(data){
			if(data==1){
				layer.msg("备注成功！");
			}else{
				layer.msg("备注失败！");
			}
		})
	}else{
		layer.msg("请输入备注信息！");
	}
}
</script>
</html>