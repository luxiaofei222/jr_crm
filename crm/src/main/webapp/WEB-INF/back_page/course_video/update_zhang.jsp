<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
<link rel="stylesheet" href="/css/school/back/common/bootstrap.min.css" />
<link rel="stylesheet" href="/css/school/back/common/flat-ui.min.css" />
<link rel="stylesheet" href="/css/school/back/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="/css/school/back/layui/css/layui.css">
<link rel="stylesheet" href="/css/school/back/common/tankuang.css" />
<script src="/js/school/back/common/flat-ui.min.js"></script>
<script src="/js/school/back/common/application.js"></script>
	<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<title>添加章</title>
</head>
<script>
  function tj_zhang(obj){

	$(obj).parent().parent().after("<div class='form-group mingcheng'><label class='col-xs-2 control-label'>章节名称：</label><div class='col-xs-7'><input type='text' class='form-control' placeholder='章节名称'></div><div class='col-xs-2'><button type='button' class='btn btn-primary btn-xs add_zhang' onClick='tj_zhang(this)'><i class='fa fa-plus'></i></button><button type='button' onClick='sc_zhang(this)' class='btn btn-danger btn-xs delete_zhang'><i class='fa fa-minus'></i></button></div></div>");
	$(obj).parent().parent().find(".btn-primary").show();
	$("div.mingcheng:last").find(".btn-primary").hide();
	if("div.mingcheng:first") {
	  $(obj).parent().parent().next().find(".btn-primary").show();	
		}
  };
  function sc_zhang(obj){
	$(obj).parent().parent().prev().find(".btn-primary").hide();
	$(obj).parent().parent().remove();
	$("div.mingcheng:first").find(".btn-primary").show();
  };
  //检查章的名称是否为null
  function check_zhang_name(){
	  var zhang=$("#video_name").val();
	  if(zhang!=null&&zhang!=""){
			return true;
		}else{
			layer.msg('请输入章节名称！');
			return false;
		}
  }
  //保存章节名称
  function update_zhang(video_id){
	  if (check_zhang_name()) {
			$("#myform").ajaxSubmit({
				type : 'POST',
				url : "/back_video/update_zhang.jr?video_id="+video_id,
				success : function(data) {
					if(data==1){
						//添加数据成功，关闭弹出窗之前，刷新列表页面的数据
	                    tanchuang('恭喜您，修改成功');
					}else{
						tanchuang('很遗憾，修改失败');
					}
				},
				error : function(XmlHttpRequest, textStatus, errorThrown) {
					tanchuang('很遗憾，修改失败');
				}
			});
		} 
  }
</script>
<body>
<div style="padding: 20px;">
  <form enctype="multipart/form-data" id="myform" class="form-horizontal">
    <div class="form-group mingcheng">
      <label class="col-xs-3 control-label">章节名称：</label>
      <div class="col-xs-9">
        <input type="text" onblur="check_zhang_name()" value="${courseVideo.video_name }" class="form-control" name="video_name" id="video_name" placeholder="章节名称">
      </div>
      <!-- <div class="col-xs-2">
        <button type="button" class="btn btn-primary btn-xs add_zhang" onClick="tj_zhang(this)"><i class="fa fa-plus"></i></button>
        <button type="button" class="btn btn-danger btn-xs delete_zhang" onClick="sc_zhang(this)" style="display:none;"><i class="fa fa-minus"></i></button>
      </div> -->
    </div>
    <div class="form-group">
       <label for="inputPassword1" class="col-xs-3 control-label">&nbsp;</label>
      <div class="col-xs-9">
        <button type="button" onclick="update_zhang(${courseVideo.video_id})" class="btn btn-success btn-xm">提交</button>
        <button type="reset" class="btn btn-danger btn-xm">重置</button> 
      </div> 
    </div>
	</form>	
	</div>
</body>
</html>