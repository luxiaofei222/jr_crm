// 左侧右侧div随着窗口的宽高改变
window.onload=function(){  
     changeDivWidth();  
}  
window.onresize=function(){  
     changeDivWidth();  
}
function changeDivWidth(){               
    var rightw1=$(window).width()-215;
    var lefth=$(window).height()-188;
    $(".ht_right").css("width",rightw1);
    $(".sidebar_info").css("height",lefth);
    var windowh=$(window).height();
    var lefth3=$(".ht_left").height();
    var righth=$(".back_right").height();
    if(righth>windowh){
    	$(".ht_left").css("height",righth);
    	$(".sidebar_info").css("height",righth-188);
    }
   
}
// 初始化调用layui的layer
$(function(){
	layui.use('layer', function(){
	  	var layer = layui.layer;
		  //点击添加弹出层事件
	});  
});
//加载动画
function jiazaidonghua(){
	  $("#conten_list").html("<div id='preloader_3'></div>");
}
//$(function(){
//	$(".selectall").click(function(){
//		$(".checkbox").each(function(){
//        $(this).prop("checked",true);
//		  })
//		})
//	$(".selectno").click(function(){
//		$(".checkbox").each(function(){
//        $(this).prop("checked",false);
//		  })
//		})
//	})
