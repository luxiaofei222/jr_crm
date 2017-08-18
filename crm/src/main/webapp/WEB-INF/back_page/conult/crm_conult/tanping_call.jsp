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
//添加跟进内容
function update_genjin_note(record_id){
	var genjin_state=$("#genjin_state").val();
	var record_note=$("#record_note").val();
	$.post("/crm_conult/add_note_conult_call.jr",{
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
<style type="text/css">
	.info_wrapper{
		overflow:hidden;
	}
	.basic_info,.genjin{
		width:50%;
		float:left;
	}
	.basic_info li{
		font-size:16px;
		line-height:45px;
		margin-left:20px;
	}
	.basic_info li .name{
		color:#444!important;
	}
	.basic_info li .info{
		color:cadetblue;
	}
	table tr th,table tr td{
		text-align:center;
	}
	.info_wrapper{
		overflow:hidden;
	}
	.form-group{
		overflow:hidden;
	}
	</style>
<form enctype="multipart/form-data" id="myform" class="form-horizontal">
<div style="padding:10px;">
	<div class="info_wrapper">
	 <ul class="basic_info">
	 	<li><span class="name">客户姓名：</span><span class="info">${netConult.user_name }</li>
	 	<li><span class="name">咨询方式：</span><span  class="info">${netConult.consult_type }</span></li>
	 	<li><span class="name">电话：</span><span  class="info">${netConult.user_phone }</span></li>
	 	<li><span class="name">类别：</span><span  class="info">${netConult.course_parent_name }-${netConult.course_name }</span></li>
	 	<li><span class="name">意向学校：</span><span  class="info">${netConult.hope_sc }</span></li>
	 	<li><span class="name">意向专业：</span><span  class="info">${netConult.zhuanye }${netConult.dictionary_name }</span></li>
	 </ul>
	 <div class="genjin">
	  <div class="form-group">
			<label class="control-label col-xs-4">跟进状态：</label>
			<div class='col-xs-8'>
				<select id="genjin_state" name="genjin_state" class="form-control">
					<option value="1">继续追踪</option>
					<option value="2">客户拒绝</option>
					<option value="3">已成交</option>
				</select>
			</div></div>
		 <div class="form-group" style="margin-top:20px;">
			<label class="control-label col-xs-4">跟进内容：</label>
			<div class="col-xs-8">
			<textarea class="form-control" id="record_note" name="record_note" rows="4" style="color:#444;"></textarea></div>
		</div>
          <div class="col-xs-12" style="text-align:center;margin-top:30px;">
          <button type="button" class="btn" onclick="update_genjin_note(${record_id})" style="width:100px;background-color:#f15151;color:white;">确定</button>
    </div>
	</div>
</div>
	<div class="content_message">
      <table class="table table-hover">
		<thead>
			<tr class="tr_bgcolor warning">
	            <th>编号</th>
	            <th>客户姓名</th>
				<th>时长</th>
				<th>跟进状态</th>
	            <th>跟进内容</th>
	            <th>咨询顾问</th>
	            <th>咨询时间</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${records }" var="record" varStatus="vs">
			<tr>
				<td>
	              <label class="label label-success btn-primary" for="minimal-checkbox-1">${vs.index+1 }</label>
	            </td>
				<td>${record.user_name }</td>
				<td>${record.sec_time_call }</td>
	            <td>${record.genjin_state }</td>
	            <td title="${record.record_note} ">${record.record_note} </td>
	            <td>${record.employee.employee_name }</td>     
	            <td><fmt:formatDate value="${record.call_time}"/></td>      
			</tr>
		</c:forEach>
		</tbody>
	</table>
</div>
</div>
</form>