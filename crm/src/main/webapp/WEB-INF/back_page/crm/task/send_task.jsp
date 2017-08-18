<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<script src="/js/school/back/common/bootstrap.min.js"
	type="text/javascript"></script>
<script src="/js/school/back/common/flat-ui.min.js"></script>
<script src="/js/school/back/common/application.js"></script>
<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<title>添加章</title>
</head>
<script>
  //检查任务内容是否为null
  function check_content(){
	  var task_content=$("#task_content").val();
	  if(task_content!=null&&task_content!=""){
			return true;
		}else{
			layer.msg('请输入任务内容！');
			return false;
		}
  }
  //保存章节名称
  function send_gongsi_task(obj){
	  if(check_content()&&check_time()){
			$(obj).attr({
				"disabled" : "disabled"
			});
			$(obj).html("发布中");
		  $("#myform").ajaxSubmit({
				type : 'POST',
				url : "/crm_task/send_task.jr",
				data:{'task_jibie':'部门'},
				success : function(data) {
					if(data==1){
						layer.msg("发布成功！");
						$(obj).html("发布");
						$(obj).removeAttr("disabled");
						$('#myform')[0].reset();
					}else{
						layer.msg("发布失败！");
					}
				},
				error : function(XmlHttpRequest, textStatus, errorThrown) {
					layer.msg("发布失败！");
				}
			}); 
	  }
  }
  //选择接收任务的人
  function change_employee(){
	  var bumen_id=$("#bumen_id").val();
		$.post("/crm_task/get_bumen_employee_list.jr", {
			'bumen_id' : bumen_id
		}, function(data) {
			$("#task_employee_id").html(data);
		})
  }
  $(function() {
		layui.use('laydate',
						function() {
							var laydate = layui.laydate;
							var start = {
								festival : true,
								istoday : true
							};
							document.getElementById('task_finish_time').onclick = function() {//获取预计完成任务时间
								start.elem = this;
								laydate(start);
							}
						});
	})
	
function get_filename(){
	  var fileName="";
	  var name = $("#file_name").val();
	  fileName = name.split("\\").pop();  
      //截取文件后缀名 fileName=fileName.substring(0, fileName.lastIndexOf("."));
	  var byteSize  = $("#file_name")[0].files[0].size;
	     if(byteSize<500*1024*1024){
	    	 $("#file_name_file").html(fileName);
	    	 return true;
	     }else{
	    	 layer.msg('文件大小不能超过500MB'); 
	    	 return false;
	     }
  }
  //检查时间
  function check_time(){
	  var task_content=$("#task_finish_time").val();
	  if(task_content!=null&&task_content!=""){
			return true;
		}else{
			layer.msg('请选择完成时间！');
			return false;
		}
  }
</script>
<body>
	<div style="padding: 20px;">
	<form enctype="multipart/form-data" id="myform"
			class="form-horizontal">
    <div class="form-group">
      <label class="col-xs-3 control-label">部门/接收人：</label>
      <div class="col-xs-8">
        <select class="form-control left" onchange="change_employee()" name="bumen_id" id="bumen_id" style="width: 47%; float: left; margin-right: 30px;">
          <c:forEach items="${organizations }" var="organ">
			<option value="${organ.organization_id }">${organ.organization_name }</option>
		 </c:forEach>
        </select>
        <select class="form-control left" id="task_employee_id" name="task_employee_id" style="width: 47%; float: left;">
          <c:forEach items="${employees }" var="em">
			<option value="${em.employee_id }">${em.employee_name }</option>
			</c:forEach>
        </select>
      </div>
    </div>
    <div class="form-group">
      <label class="col-xs-3 control-label">紧急程度：</label>
      <div class="col-xs-8">
        <select class="form-control left" id="zhongyao_state" name="zhongyao_state">
          <option value="0">正常</option>
          <option value="1">紧急</option>
          <option value="2">特急</option>
        </select>
      </div>
    </div>
    <div class="form-group">
      <label class="col-xs-3 control-label">完成时间：</label>
      <div class="col-xs-8">
        <input type="text" class="form-control" onblur="check_time()"  name="task_finish_time_str" id="task_finish_time" placeholder="请选择时间">
      </div>
    </div>
    <div class="form-group">
       <label for="inputPassword1" class="col-xs-3 control-label">内容：</label>
      <div class="col-xs-8">
        <textarea class="form-control" onblur="check_content()" id="task_content" name="task_content" rows="4"></textarea>
      </div> 
    </div>
    <div class="form-group">
       <label for="inputPassword1" class="col-xs-3 control-label">附件：</label>
      <div class="col-xs-8">
        <a href="javascript:void(0)" class="btn btn-info btn-xm" style="position:relative; cursor:pointer; float: left;"><input type="file" name="file_name" id="file_name" onchange="get_filename()" value="上传附件" style="position:absolute; opacity:0; cursor:pointer;"/>上传附件</a>
     	<div id="file_name_file" style="line-height: 3; float: left; margin-left: 30px;"></div>
      </div> 
    </div>
    <div class="form-group">
       <label for="inputPassword1" class="col-xs-3 control-label">&nbsp;</label>
      <div class="col-xs-9">
        <button type="button" onclick="send_gongsi_task(this)" class="btn btn-success btn-xm">发布</button> 
        <button type="reset"  class="btn btn-success btn-xm">重置</button> 
      </div> 
    </div>
	</form>
	</div>
</body>
</html>