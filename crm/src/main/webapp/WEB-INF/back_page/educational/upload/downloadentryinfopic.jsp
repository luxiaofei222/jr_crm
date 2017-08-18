<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" type="text/css"
	href="/css/school/back/common/reset.css">
<link rel="stylesheet" href="/css/school/back/common/bootstrap.min.css" />
<link rel="stylesheet" href="/css/school/back/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="/css/edu/jquery-ui.min.css" />
<link rel="stylesheet" href="/css/school/back/common/flat-ui.min.css"/>
<link rel="stylesheet" type="text/css"
	href="/css/edu/entryinfo/e_tankuang.css" />
<link rel="stylesheet" type="text/css"
	href="/css/school/back/layui/css/layui.css">
<script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
<script type="text/javascript"
	src="/js/school/back/common/bootstrap.min.js"></script>
<script src="/js/school/back/common/flat-ui.min.js"></script>
<script src="/js/school/back/common/application.js"></script>
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<script src="/js/edu/jquery-ui.min.js" type="text/javascript"></script>
<script>
//关闭弹窗
function close_layer() {
	var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
	parent.layer.close(index);
}
//导出学员图片信息
function satrt_daochu(){
	$(".loading").css("display","block");
	var entryplanId=$("#entryplanId").val();
	var len = $("input:checkbox[name=userpic]:checked").length;
	var str = "";
	$('input[name=userpic]').each(function(i) {
		if (0 == i) {
			str = $(this).val();
		} else {
			str += ","+$(this).val();
		}
	});
	if(len>0){
		if(entryplanId!=0){
			$.post("/down_pic/check_info_pic.jr",{
				'entryplanId':entryplanId,
				'str':str
			},function(data){
				if(data==1){
					location.href="/down_pic/down_entry_info_pic_zip.jr?entryplanId="+entryplanId;
				}else{
					layer.msg("暂无学员信息！");
					$(".loading").css("display","none");
				}
			})
		}else{
			$.post("/down_pic/check_info_pic.jr",{
				'str':str
			},function(data){
				if(data==1){
					location.href="/down_pic/down_entry_info_pic_zip.jr";
				}else{
					layer.msg("暂无学员信息！");
					$(".loading").css("display","none");
				}
			})
		}
	}else{
		layer.msg("请选择您要导出的图片类型！");
	}
}
</script>
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
    <p style="color: #fff; position: absolute; top: 45px; left: -145px;">正在导出数据,请等待,如果下载完成,自行关闭窗口！</p>
  </div>    
</div>
<div class="daoru_shuju_dialog">
<form class="form-horizontal import_xueyuanshuju_dialog" enctype="multipart/form-data" id="myform" style="padding:20px;">
     <div class="form-group">
			<label class="col-xs-2 control-label">报名计划：</label>
			<div class="col-xs-10">
				<select id="entryplanId" name="entryplanId">
					<option value="0">请选择计划</option>
					<c:forEach items="${entryPlans }" var="entryplan">
						<option value="${entryplan.entryplan_id }">${entryplan.entryplan_content }</option>
					</c:forEach>
				</select>
			</div>
		</div>
	<div class="form-group">
			<label class="col-xs-2 control-label">&nbsp;</label>
			<div class="col-xs-10">
			  <ul class="pic_leixing">
			    <li>
			      <label class="checkbox checkbox-inline" for="checkbox1">
                    <input type="checkbox" data-toggle="checkbox" name="select_pic" value="userpic" id="checkbox1" class="checkbox" required>
                  </label>
                  <span class="text">学员照片</span>
			    </li>
			    <li>
			      <label class="checkbox checkbox-inline" for="checkbox2">
                    <input type="checkbox" data-toggle="checkbox" name="select_pic" value="sfz" id="checkbox2" class="checkbox" required>
                  </label>
                  <span class="text">身份证正面照片</span>
			    </li>
			    <li>
			      <label class="checkbox checkbox-inline" for="checkbox3">
                    <input type="checkbox" data-toggle="checkbox" name="select_pic" value="sfb" id="checkbox3" class="checkbox" required>
                  </label>
                  <span class="text">身份证背面照片</span>
			    </li>
			    <li>
			      <label class="checkbox checkbox-inline" for="checkbox4">
                    <input type="checkbox" data-toggle="checkbox" name="select_pic" value="xl" id="checkbox4" class="checkbox" required>
                  </label>
                  <span class="text">学历照片</span>
			    </li>
			  </ul>	
			</div>
		</div>
    <div class="form-group" style="margin-top: 60px;">
       <label for="inputPassword1" class="col-xs-2 control-label">&nbsp;</label>
      <div class="col-xs-10">
        <button type="button" onclick="satrt_daochu()" class="btn btn-info btn-sm" style="margin-right:35px;">开始导出</button> 
        <button type="button" onclick="close_layer()" class="btn btn-success btn-sm">关闭</button> 
      </div> 
    </div>
	</form>	
</div>