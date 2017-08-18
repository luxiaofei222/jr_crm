<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
<link rel="stylesheet" href="/css/school/back/common/bootstrap.min.css" />
<link rel="stylesheet" href="/css/school/back/common/flat-ui.min.css" />
<link rel="stylesheet" href="/css/school/back/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="/css/school/back/common/tankuang.css" />
<link rel="stylesheet" type="text/css" href="/css/school/back/layui/css/layui.css">
<script src="/js/school/back/common/flat-ui.min.js"></script>
<script src="/js/school/back/common/application.js"></script>
	<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<style type="text/css">
.form-horizontal .form-group .right_wz{
		text-align:right;
}
</style>
<script>
//检测公告内容是否为空
function check_notice(){
	var notice_content=$("#notice_content").val();
	if(notice_content!=null&&notice_content!=""){
		
		return true;
	}else{
		layer.msg('请输入公告内容！');
		return false;
	}
}
//添加角色
function add_notice(obj){
	 if (check_notice()) {
		 $(obj).attr("disabled","disabled");
		$("#myform").ajaxSubmit({
			type : 'POST',
			url : "/notice/save_notice.jr",
			success : function(data) {
				if(data==1){
					//添加数据成功，关闭弹出窗之前，刷新列表页面的数据
                    tanchuang('恭喜您，发布成功');
                    $("#notice_content").val("");
                    $(obj).removeAttr("disabled");
				}else{
					tanchuang('很遗憾，发布失败');
					$(obj).removeAttr("disabled");
				}
			},
			error : function(XmlHttpRequest, textStatus, errorThrown) {
				tanchuang('很遗憾，发布失败');
				$(obj).removeAttr("disabled");
			}
		});
	} 
}
//关闭弹窗
function close_layer(){
	var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
    parent.layer.close(index);
}
</script>
	<form class="form-horizontal" id="myform" role="form" style="padding:20px;">
    <div class="form-group" style="margin-top:15px;">
      <label class="col-xs-3 control-label right_wz">公告：</label>
      <div class="col-xs-8">
        <textarea class="form-control" rows="10" name="notice_content" id="notice_content" onblur="check_notice()" style="color:#444;" placeholder="请输入要发布公告的内容"></textarea>         
      </div>
    </div>
    </div>
    <div class="form-group" style="margin-top:15px;">
          <div class="col-xs-6 right_wz">
          <button type="button" class="btn btn-warning btn-lg" onclick="close_layer()" style="width:100px;">取消</button>
          </div>
          <div class="col-xs-6" style="text-align:left;">
          <button type="button" class="btn btn-primary btn-lg" onclick="add_notice(this)" style="width:100px;">保存</button>
          </div>
        </div>
	</form>	