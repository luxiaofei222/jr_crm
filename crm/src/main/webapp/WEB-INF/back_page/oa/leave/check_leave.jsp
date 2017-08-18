<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/school/front/index/reset.css" rel="stylesheet"	type="text/css" />
<link rel="stylesheet" href="/css/school/back/common/bootstrap.min.css" />
<link rel="stylesheet" href="/css/school/back/common/flat-ui.min.css" />
<link rel="stylesheet" type="text/css"	href="/css/school/back/layui/css/layui.css">
<script src="/js/school/front/course_video/jquery-1.11.1.min.js" type="text/javascript"></script>
<script src="/js/school/back/common/flat-ui.min.js"></script>
<script src="/js/school/back/common/application.js"></script>
<script type="text/javascript" src="/js/school/front/course_video/jquery.raty.min.js"></script>
<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<title>查看请假详情</title>
<script>
	//关闭弹窗
	function close_layer(){
		var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
		parent.layer.close(index);
	}
	//评价任务
	function tijiao_leave(leave_id){
		var radio =	$("input[type='radio']:checked").val();
		if(radio==0){
			var message=$("#message_str").val();
		}else{
			var message="同意";
		}
		var shenpi_id=$("#shenpi_id").val();
		
		$.post("/back_leave/update_crm_leave.jr",{
			'message':message,
			'leave_id':leave_id,
			'shenpi_id':shenpi_id,
			'radio':radio
		},function(data){
			if(data==1){
				layer.msg("提交成功！");
			}else{
				layer.msg("提交失败！");
			}
		})
	}
	//切换选项
	function get_radio_message(obj){
		var radio_val=$(obj).val();
		if(radio_val==0){
			$("#tujiaozhi").hide();
			$("#message").show();
		}else{
			$("#message").hide();
			$("#tujiaozhi").show();
		}
	}
</script>
<style type="text/css">
    .jujue {
		display:none;
		}
	.jujue button {
		margin-top:20px;
		}
	.form-group select {
	    width: 100%;
	    height: 40px;
	    border: 2px solid #bdc3c7;
	}
	.form-control[disabled]{
	    color: #313131;
	}
    </style>
</head>
<body>
  <div style="padding:20px;">
  <form enctype="multipart/form-data" id="myform"
			class="form-horizontal">
    <div class="form-group">
      <label class="col-xs-2 control-label">请假人员：</label>
      <div class="col-xs-10">
        <input class="form-control" id="" type="text" value="${backLeave.bumen }" disabled style="width: 47%;float: left;margin-right: 38px;"> 
        <input class="form-control" id="" type="text" value="${backLeave.employee.employee_name }" disabled style="width: 47%;float: left;">   
      </div>
    </div> 
    <div class="form-group">
      <label class="col-xs-2 control-label">请假种类：</label>
      <div class="col-xs-10">
        <input class="form-control" type="text" value="${leixing}" disabled>   
      </div>
    </div> 
    <div class="form-group">
      <label class="col-xs-2 control-label">请假事由：</label>
      <div class="col-xs-10">
        <textarea class="form-control" rows="3" disabled>${backLeave.leave_content }</textarea>
      </div>
    </div>
    <div class="form-group">
      <label class="col-xs-2 control-label">请假时间：</label>
      <div class="col-xs-10">
        <input class="form-control" type="text" value="<fmt:formatDate type="both"
									value="${backLeave.leave_start_time }" />--<fmt:formatDate type="both"
									value="${backLeave.leave_end_time }" />" disabled>   
      </div>
    </div> 
    <%-- <div class="form-group">
      <label class="col-xs-2 control-label">请假时长：</label>
      <div class="col-xs-10">
        <input class="form-control" type="text" value="${backLeave.leave_shichang }天" disabled>   
      </div>
    </div> --%>
    <c:if test="${not empty backLeave.jingli_message }">
    <div class="form-group">
      <label class="col-xs-2 control-label">部门经理意见：</label>
      <div class="col-xs-10">
        <textarea class="form-control" rows="3" disabled>${backLeave.jingli_message }</textarea>
      </div>
    </div>
    </c:if>
     <c:if test="${not empty backLeave.zongjian_message }">
    <div class="form-group">
      <label class="col-xs-2 control-label">市场总监意见：</label>
      <div class="col-xs-10">
        <textarea class="form-control" rows="3" disabled>${backLeave.zongjian_message }</textarea> 
      </div>
    </div>
    </c:if>
    <c:if test="${not empty backLeave.boss_message }">
    <div class="form-group">
      <label class="col-xs-2 control-label">总经理意见：</label>
      <div class="col-xs-10">
        <textarea class="form-control" rows="3" disabled>${backLeave.boss_message }</textarea>  
      </div>
    </div>
    </c:if>
      <c:if test="${not empty backLeave.beizhu }">
    <div class="form-group">
      <label class="col-xs-2 control-label">员工说明：</label>
      <div class="col-xs-10">
        <textarea class="form-control" rows="3" disabled>${backLeave.beizhu }</textarea>  
      </div>
    </div>
    </c:if>
    <div class="form-group">
       <label for="inputPassword1" class="col-xs-2 control-label">审批意见：</label>
      <div class="col-xs-10">
        <label class="radio" for="radio4a" style="float:left;margin-top: 10px;margin-right: 35px;">
            <input type="radio" name="optionsRadios" data-toggle="radio" onchange="get_radio_message(this)" value="1" id="radio4a" required checked>同意     
        </label>
        <label class="radio" for="radio4b" style="float:left;">
          <input type="radio" name="optionsRadios" data-toggle="radio" value="0" onchange="get_radio_message(this)" id="radio4b" class="refuse" required>拒绝
        </label>   
      </div> 
    </div>
    <div class="form-group" id="tujiaozhi">
      <label class="col-xs-2 control-label">提交至：</label>
      <div class="col-xs-10">
        <select id="shenpi_id">
        <c:if test="${sessionScope.employee_session.organization_id!=30 }">
          <c:forEach items="${employees_zongjian }" var="employ">
          <option value="${employ.employee_id }">${employ.employee_name }</option>
          </c:forEach>
        </c:if>
         <c:if test="${sessionScope.employee_session.organization_id==30 }">
          <option value="">请选择</option>
          <option value="101">总经理</option>
          </c:if>
        </select>
         <label style="color: orange;">注：如果审批超出权限请选择上级领导，否则后果自负！</label>
      </div>
    </div>
    <div class="form-group jujue" id="message">
      <label class="col-xs-2 control-label">拒绝原因：</label>
      <div class="col-xs-10">
        <textarea class="form-control" id="message_str" rows="3"></textarea>
      </div>
    </div>
    <div class="form-group">
      <label class="col-xs-2 control-label">&nbsp;</label>
      <div class="col-xs-10">
      <c:if test="${backLeave.leave_state==0 && sessionScope.employee_session.organization_id!=30 }">
        <button type="button" onclick="tijiao_leave(${backLeave.leave_id})" class="btn btn-info btn-xm">提交</button>
       </c:if>
       <c:if test="${backLeave.leave_state==1 && sessionScope.employee_session.organization_id==30 }">
        <button type="button" onclick="tijiao_leave(${backLeave.leave_id})" class="btn btn-info btn-xm">提交</button>
       </c:if>
        <button type="button" onclick="close_layer()" class="btn btn-info btn-xm">关闭</button>
      </div>
    </div>
    </form>
  </div>
</body>
</html>