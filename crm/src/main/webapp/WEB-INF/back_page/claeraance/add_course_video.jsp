<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
<link rel="stylesheet" href="/css/school/back/common/bootstrap.min.css" />
<link rel="stylesheet" href="/css/school/back/common/flat-ui.min.css" />
<link rel="stylesheet"
	href="/css/school/back/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="/css/school/back/common/tankuang.css" />
<link rel="stylesheet" type="text/css"
	href="/css/school/back/layui/css/layui.css">
<script src="/js/school/back/common/flat-ui.min.js"></script>
<script src="/js/school/back/common/application.js"></script>
<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<style type="text/css">
.form-horizontal .form-group .right_wz {
	text-align: right;
	color: #444;
}
</style>
<script>
//关闭弹窗
function layer_close(){
	var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
    parent.layer.close(index);
}
//保存我的课程
function save_clearance_video(obj,clearance_id){
	 var str="";  
     $('input:checkbox[name=video_id]:checked').each(function(i){
      if(0==i){
    	  str = $(this).val();
      }else{
    	  str += (","+$(this).val());
      }
     });
     $(obj).attr('disabled',"true");
     $.post("/back_clearance/save_clearance_video.jr",{
    	 'str':str,
    	 'clearance_id':clearance_id
     },function(data){
    	 if(data==1){
    		 location.reload();
    		 //tanchuang("恭喜，课程添加成功！");
    	 }else if(data==2){
    		 tanchuang("请选择您要添加的课程！");
    		 $(obj).removeAttr("disabled");
    	 }else{
    		 tanchuang("课程添加失败，系统发生问题！");
    		 $(obj).removeAttr("disabled");
    	 }
     })
}
</script>
<div style="padding: 20px;">
	<form enctype="multipart/form-data" id="myform" class="form-horizontal">
		<div id="video_list">
			<c:if test="${empty courseVideos }">
      <div style="color: orange;text-align:center;margin:0 auto">暂无课程</div>
      </c:if>
      <c:if test="${not empty courseVideos }">
      <c:forEach items="${courseVideos }" varStatus="vs" var="video">
      <div class="form-group">
      <c:if test="${vs.index==0 }">
            <label class="col-xs-3 control-label right_wz">课程列表：</label>
       </c:if>
        <c:if test="${vs.index!=0 }">
            <label class="col-xs-3 control-label right_wz"></label>
       </c:if>
            <div class="col-xs-5">
               <c:if test="${video.is_jiaru_course==false }">
                  <label class="checkbox checkbox-inline" for="checkbox${video.video_id }">
                        <input type="checkbox" data-toggle="checkbox" value="${video.video_id }" name="video_id" id="checkbox${video.video_id }" class="checkbox" required>
                  </label>
                </c:if>
              <label class="control-label">${video.video_name }</label> 
            </div>
            <div class="col-xs-2">
            <label style="color: orange;" class="control-label">￥${video.video_money_now }</label> 
            </div>
             <div class="col-xs-2">
           <c:if test="${video.is_jiaru_course==true }">
                  <label style="color: orange;" class="control-label">已加入</label>
                </c:if> 
            </div>
            </div>
      </c:forEach>
      </c:if>
		</div>
		<div class="form-group" style="margin-top: 15px;">
			<div class="col-xs-6 right_wz">
				<button type="button" onclick="save_clearance_video(this,${clearance_id})"
					class="btn btn-info btn-lg" style="width: 100px;">保存</button>
			</div>
			<div class="col-xs-6" style="text-align: left;">
				<button type="button" onclick="layer_close()"
					class="btn btn-danger btn-lg" style="width: 100px;">关闭</button>
			</div>
		</div>
	</form>
</div>
