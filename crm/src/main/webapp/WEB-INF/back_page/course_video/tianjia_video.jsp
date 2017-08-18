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
<script type="text/javascript">
//添加视频
function tianjia_video(video_id,type){
	var len = $("input:checkbox[name=files_check]:checked").length;
	
	if(len>0){
		if(type==1){
			if(len>1){
				tanchuang("您只能选择一条视频进行修改！");
			}else{
				var str = "";
				$('input:checkbox[name=files_check]:checked').each(function(i) {
					if (0 == i) {
						str = $(this).val();
					} else {
						str += ","+$(this).val();
					}
				});
				$.post("/back_video/save_video_jie_yidong.jr",{
					'str':str,
					'video_id':video_id
				},function(data){
					if(data==1){
						layer.confirm("恭喜您，添加成功！",function(){
							layer.closeAll('dialog');
							location.reload();
							})
					}else{
						tanchuang("添加失败！");
					}
				});
			}
		}else{
			var str = "";
			$('input:checkbox[name=files_check]:checked').each(function(i) {
				if (0 == i) {
					str = $(this).val();
				} else {
					str += ","+$(this).val();
				}
			});
			$.post("/back_video/save_video_jie_yidong.jr",{
				'str':str,
				'video_id':video_id
			},function(data){
				if(data==1){
					layer.confirm("恭喜您，添加成功！",function(){
						layer.closeAll('dialog');
						location.reload();
						})
				}else{
					tanchuang("添加失败！");
				}
			});
		}
	}else{
		tanchuang("请选择至少一条视频进行添加！");
	}
		
}
</script>
</head>
<body>
	<div style="padding: 20px;">
		<form enctype="multipart/form-data" id="myform"
			class="form-horizontal add_videos_dialog">
			<%-- <c:forEach items="${files }" varStatus="vs" var="readfile">
			<div>
			<input type="checkbox" name="files_check" value="${readfile.feiles_houzhui }">
		${vs.index+1 }&nbsp;&nbsp;<p>${readfile.feiles_houzhui }</p>
			</div>
			</c:forEach> --%>
            <div class="videos">
            <c:forEach items="${files }" varStatus="vs" var="readfile">
              <div class="form-group">
                <label class="checkbox checkbox-inline" for="checkbox${vs.index+1 }">
                  <input type="checkbox" data-toggle="checkbox" name="files_check" value="${readfile.files_name }" id="checkbox${vs.index+1 }" class="checkbox" required>${vs.index+1 }.
                </label>
                <p>${readfile.files_name }</p>
              </div>
              </c:forEach>
            </div>
			<button type="button" onclick="tianjia_video(${video_id},${type })" class="btn btn-success btn-xm">保存</button>
		</form>
	</div>
</body>
</html>