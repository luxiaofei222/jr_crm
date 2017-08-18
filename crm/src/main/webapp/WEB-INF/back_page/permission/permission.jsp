<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/css/school/back/common/reset.css">
 <link rel="stylesheet" href="/css/school/back/common/bootstrap.min.css" />
<link rel="stylesheet" href="/css/permission/tree.css" />
<link rel="stylesheet" type="text/css"
	href="/css/school/back/layui/css/layui.css">
<link rel="stylesheet" href="/css/school/back/common/tankuang.css" />
<script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="/js/common/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<title>权限设置页面</title>
<style type="text/css">
#main-container{
	margin:0 auto;
}
#main-container h1{
	margin-top:20px;
    color:#06C1AE;
    margin-bottom:20px;
    font-size:20px;
    text-align:center;
}
.form-group .right_wz{
    text-align:right;
    line-height:35px;
}
</style>
</head>
<script>
$(function(){
	var role_id=$("#role_id").val();
	$('#tree').tree({
	    url: "/back_role/get_menu_tree.jr?role_id="+role_id,
	    lines:true,  //x显示加减号
	    checkbox:true,
	    cascadeCheck:false,
	    //展开前   
        onBeforeExpand:function(node){  
                    // 异步加载的地址并传递参数   
                  $("#tree").tree("options").url ="/back_role/get_menu_tree.jr?menu_id="+node.id+"&role_id="+role_id;   
               },
	    onLoadSuccess:function(node,data){
	         var t = $(this);
	     if(data){
	         $(data).each(function(index,d){
	        if(this.state == 'closed'){
	            t.tree('expandAll');
	        }
	         });
	    }
	    }
	});
})
//关闭弹窗
function close_layer() {
	var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
	parent.layer.close(index);
}
//设置权限
function shezhi_permission(role_id){
	var nodes = $('#tree').tree('getChecked',['checked','indeterminate']);
	  var s = '';//获取选中的菜单一级其上一级权限菜单
      for (var i = 0; i < nodes.length; i++) {
          if (s != '') 
              s += ',';
          s += nodes[i].id;
      }
	$.post("/back_role/save_role_menu.jr",{
		'role_id':role_id,
		'str':s
	},function(data){
		tanchuang(data);
	})  
}
</script> 
<body>
<div id="main-container">
<h1>京人网校后台管理权限设置-<span style="color: orange;">${role.role_name }</span></h1>
<input type="hidden" value="${role.role_id}" id="role_id">
<!-- <div id="tree-container"></div> -->
<ul id="tree"></ul>  
</div>
<div style="clear:both;"></div>
<div class="form-group" style="margin-top:30px;">
    <div class="col-xs-6 right_wz">
     <button type="button" onclick="close_layer()" class="btn btn-warning btn-lg" style="width:100px;">取消</button>
    </div>
    <div class="col-xs-6" style="text-align:left;">
      <button type="button" onclick="shezhi_permission(${role.role_id})" class="btn btn-primary btn-lg" style="width:100px;">保存</button>
    </div>
  </div>
</body>
</html>