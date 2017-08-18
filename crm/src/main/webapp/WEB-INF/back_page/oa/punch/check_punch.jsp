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
	//审批离职
	function tijiao_punch(punch_id){
		var radio =	$("input[type='radio']:checked").val();
		var renshi_info=$("#renshi_info").val();
		if(renshi_info!=null&&renshi_info!=""){
			$.post("/punch/update_punch.jr",{
				'renshi_info':renshi_info,
				'punch_id':punch_id,
				'renshi_state':radio
			},function(data){
				if(data==1){
					layer.msg("提交成功！");
					location.reload();
				}else{
					layer.msg("提交失败！");
				}
			})
		}else{
			layer.msg("你输入您的意见！");
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
      <label class="col-xs-2 control-label">打卡人员：</label>
      <div class="col-xs-10">
        <input class="form-control" id="" type="text" value="${punch.organization.organization_name }" disabled style="width: 47%;float: left;margin-right: 38px;"> 
        <input class="form-control" id="" type="text" value="${punch.employee_name }" disabled style="width: 47%;float: left;">   
      </div>
    </div> 
      <div class="form-group">
      <label class="col-xs-2 control-label">应打卡时间：</label>
      <div class="col-xs-10">
        <input class="form-control" type="text" value="<fmt:formatDate pattern="yyyy年MM月dd日"
									value="${punch.punch_time }" />" disabled>   
      </div>
    </div> 
    <div class="form-group">
      <label class="col-xs-2 control-label">应打卡时间段：</label>
      <div class="col-xs-10">
        <input class="form-control"  type="text" value="${punch.shijianduan }" disabled />
      </div>
    </div>
    <div class="form-group">
      <label class="col-xs-2 control-label">忘记原因：</label>
      <div class="col-xs-10">
        <textarea class="form-control" rows="3" disabled>${punch.punch_info }</textarea>
      </div>
    </div>
    <div class="form-group">
      <label class="col-xs-2 control-label">审核状态：</label>
      <div class="col-xs-10">
        <label class="checkbox-inline">
    		<input type="radio" name="renshi_state" value="1" checked>批准
  		</label>
		<label class="checkbox-inline">
		   <input type="radio" name="renshi_state" value="2">拒绝
		</label>	
      </div>
    </div>
    <div class="form-group">
      <label class="col-xs-2 control-label">人事意见：</label>
      <div class="col-xs-10">
        <textarea class="form-control" rows="3" id="renshi_info" ></textarea>  
      </div>
    </div>
    <div class="form-group">
      <label class="col-xs-2 control-label">&nbsp;</label>
      <div class="col-xs-10">
      <c:if test="${punch.renshi_state==0 }">
      <button type="button" onclick="tijiao_punch(${punch.punch_id})" class="btn btn-info btn-xm">提交</button>
     </c:if>
     <c:if test="${punch.renshi_state!=0 }">
      <button type="button" disabled="disabled" onclick="tijiao_punch(${punch.punch_id})" class="btn btn-info btn-xm">提交</button>
     </c:if>
      <button type="button" onclick="close_layer()" class="btn btn-info btn-xm">关闭</button>
      </div>
    </div>
    </form>
  </div>
</body>
</html>