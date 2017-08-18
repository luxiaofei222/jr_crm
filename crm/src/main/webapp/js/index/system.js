$(function(){
	layui.use('layer', function(){
	  	var layer = layui.layer;
		  //点击添加弹出层事件
	});  
	if(isIE()){
		location.href="/upgrade.jsp";
	}
});
//退出系统
function logout_jr(){
	layer.confirm("提示：您好，确定要退出本系统吗？",function(){
		layer.closeAll('dialog');
		$.post("/logout.jr",function(data){
			if(data==1){
				location.href=("/login.jr");
			}
		})
		})
}
//进入某个系统
function get_system_main(menu_id,is_permison){
	if(is_permison=='true'){
		location.href="/main.jr?menu_id="+menu_id;
	}else{
		layer.msg("您没有本系统的权限，请联系管理员！")
	}
}
//判断是否是IE浏览器
function isIE() { //ie?
	 if (!!window.ActiveXObject || "ActiveXObject" in window){
		 return true;
	 }else{
		 return false;
		 }
	 }