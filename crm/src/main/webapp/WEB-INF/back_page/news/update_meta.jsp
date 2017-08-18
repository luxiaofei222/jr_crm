<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" type="text/css" href="/css/school/back/common/reset.css">
	<link rel="stylesheet" href="/css/school/back/common/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css"
	href="/css/school/back/layui/css/layui.css">
    <link rel="stylesheet" href="/css/school/back/common/tankuang.css" />
    <script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="/js/school/back/common/bootstrap.min.js"></script>
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<script>
//修改新闻动态meta信息
function update_meta(news_id){
			 $("#myform").ajaxSubmit({
					type : 'POST',
					url : "/back_news/update_news_meta.jr?news_id="+news_id,
					success : function(data) {
						if(data==1){
							tanchuang("提示：恭喜您，修改meta信息成功！");
						}else{
							 layer.msg("提示：很遗憾，修改失败！");
						}
					},
					error : function(XmlHttpRequest, textStatus, errorThrown) {
						 layer.msg("修改失败！");
					}
				});
}
</script>
<div style="padding: 20px;">
	<form enctype="multipart/form-data" id="myform" class="form-horizontal">
    <div class="form-group mingcheng">
      <label class="col-xs-3 control-label">页面标题：</label>
      <div class="col-xs-8">
        <input type="text" class="form-control" value="${news.meta_title}" name="meta_title" id="meta_title" placeholder="页面标题">
      </div>
    </div>
    <div class="form-group">
       <label for="inputPassword1" class="col-xs-3 control-label">页面关键字：</label>
      <div class="col-xs-8">
        <input type="text" class="form-control" value="${news.meta_key}" name="meta_key" id="meta_key" placeholder="页面关键字">
      </div> 
    </div>
    <div class="form-group">
       <label for="inputPassword1" class="col-xs-3 control-label">页面描述：</label>
      <div class="col-xs-8">
        <input type="text" class="form-control" value="${news.meta_dis}" name="meta_dis" id="meta_dis" placeholder="页面描述">
      </div> 
    </div>
    <div class="form-group">
       <label for="inputPassword1" class="col-xs-3 control-label">&nbsp;</label>
      <div class="col-xs-9">
        <button type="button" onclick="update_meta(${news.news_id})" class="btn btn-success btn-xm">修改</button> 
      </div> 
    </div>
	</form>	
	</div>