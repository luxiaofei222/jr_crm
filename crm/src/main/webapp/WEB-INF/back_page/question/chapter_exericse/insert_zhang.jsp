<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/css/school/back/common/reset.css">
<link rel="stylesheet" href="/css/school/back/common/bootstrap.min.css" />
<link rel="stylesheet" href="/css/school/back/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="/css/school/back/layui/css/layui.css">
<link rel="stylesheet" type="text/css" href="/css/question/back/jiazaidonghua.css">
<script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="/js/school/back/common/bootstrap.min.js"></script>
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<title>添加章</title>
<style type="text/css">
.form-horizontal .form-group .right_wz{
		text-align:right;
}
</style>
</head>
<script>
//关闭弹窗
function colse_layer(){
	var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
    parent.layer.close(index);
}
//检查章名称
function check_chapter_name(){
	var chapter_name=$("#chapter_name").val();
	if(chapter_name!=null&&chapter_name!=""){
		return true;
	}else{
		layer.msg('请输入章节标题！');
		return false;
	}
}
//检查章节序号
function check_chapter_number(){
	var chapter_number=$("#chapter_number").val();
	if(chapter_number!=null&&chapter_number!=""){
		if(isNaN(chapter_number)){
			layer.msg('请输入数字！');
			return false;
		}else{
			return true;
		}
	}else{
		layer.msg('请输入章节序号！');
		return false;
	}
}
//添加章
function add_zhang(question_course_id){
	if(check_chapter_name()&&check_chapter_number()){
		$(".loading").show();
		 $("#myform").ajaxSubmit({
				type : 'POST',
				data:{'question_course_id':question_course_id},
				url : "/back_chapter/insert_chapter_zhang.jr",
				success : function(data) {
					if(data==1){
						$(".loading").hide();
						//添加数据成功，关闭弹出窗之前，刷新列表页面的数据
		                tanchuang('恭喜您，添加成功');
		                $('#myform')[0].reset();
					}else if(data==2){
						$(".loading").hide();
						tanchuang('您输入的章序号有重复的！');
					}else{
						$(".loading").hide();
						tanchuang('很遗憾，添加失败');
					}
				},
				error : function(XmlHttpRequest, textStatus, errorThrown) {
					$(".loading").hide();
					tanchuang('很遗憾，添加失败');
				}
			});
	}
}
</script>
<body>
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
    <p style="color: #fff; position: absolute; top: 45px; left: -35px;">正在添加数据...</p>
  </div> 
  </div>
<form class="form-horizontal add_qiye_dialog" enctype="multipart/form-data" id="myform" style="padding:21px;">
     <div class="form-group" style="margin-top:15px;">
     <label class="col-xs-2 control-label right_wz">章节序号：</label>
      <div class="col-xs-8">
        <input class="form-control" onblur="check_chapter_number()" id="chapter_number" name="chapter_number" placeholder="输入章序号">         
      </div>
      </div>
      <div class="form-group" style="margin-top:15px;">
     	<p style="color: orange;"  class="col-xs-12">注意：序号必须是数字，序号代表你要插入的章节顺序，不可与已存在的章节重复！</p>
  	</div>
    <div class="form-group plus" style="margin-top:15px;">
      <label class="col-xs-2 control-label right_wz">章节名称：</label>
      <div class="col-xs-8">
        <input class="form-control" onblur="check_chapter_name()" id="chapter_name" name="chapter_name" placeholder="输入章名称">         
      </div>
  </div>
      	<div class="form-group" style="margin-top:15px;">
      		<div class="col-xs-6 right_wz">
      		<button type="button" class="btn btn-warning btn-lg" onclick="colse_layer()" style="width:100px;">取消</button>
      		</div>
          <div class="col-xs-6" style="text-align:left;">
          <button type="button" class="btn btn-primary btn-lg" onclick="add_zhang(${question_course_id})" style="width:100px;">保存</button>
          </div>
      	</div>
	</form>	
</body>
</html>