$(function(){
	$.post("/back_menu/menu_list.jr",function(data){
		$("#menu_first").html(data);
		// 左侧菜单的点击手风琴事件
		$(function () {
		    $('.bar_details').click(function () { 
		        $(this).next('.bar_details_two').slideToggle().parent().siblings().children("ul").slideUp();
		    });
		});
		// 左侧菜单点击与滑过事件
		$(function(){
			$(".sidebarli").click(function() {
				var index=$(this).index();
				$(".sidebarli").eq(index-2).toggleClass("select_bar").siblings().removeClass("select_bar");
			})
			$(".bar_details_two>li").click(function(){
				$(this).addClass("select_bar2");
				$(".bar_details_two>li").not(this).removeClass("select_bar2");
			})
		})
		// 左侧菜单变小后的点击事件
		$(function(){
			$(".sidebarli_two").click(function() {
				var index=$(this).index();
				$(".sidebarli_two").eq(index).addClass("select_bar").siblings().removeClass("select_bar");
			})
		})
	})
	
	
	$(".logoimg img").click(function(){
		$(".sidebarone").animate({width:"200px"},500);
		var rightw2=$(".ht_right").width()-140;
		$(".ht_right").css("width",rightw2);
		$(".ht_right").animate({left:"200px"},500);
		$(".bar_wz").show();
		$(".sidetubiao2").show();
		$(".erji").show();
		$(".bxyc").show();
		$(".logoimg img").attr("src","/images/school/back/left/logo111.png");
		$(".logoimg").css("margin-top","6px");
			$('.bar_details_two li').tipso("destroy");
	})
})

$(function(){
	//获取用户所在岗位
	$.post("/back_employee/get_organzition.jr",function(data){
		$("#organization").html(data);
	})
})
function shouqi(){
	$(".bar_wz").hide();
	$(".sidetubiao2").hide();
	$(".erji").hide();
	$(".bxyc").hide();
	$(".logoimg img").attr("src","/images/school/back/left/side_right.png");
	$(".logoimg").css("margin-top","13px");
	$(".sidebarone").animate({width:"60px"},500);
	var rightw2=$(".ht_right").width()+140;
	$(".ht_right").css("width",rightw2);
	$(".ht_right").animate({left:"60px"},500);
			// 当菜单缩小时显示提示tips
	$('.bar_details_two li').tipso({
	    useTitle:false,
	    position:'right',
	    width:80,
	    offsetX:-141,
	    background:"#1CBAB8"
	});
}
//获取菜单内容
function get_menu_content(url,limit){
	if(url!=null&&url!=""){
		jiazaidonghua();
		$.post(url,{
			'pageNumber' : 1,
			'limit' : limit
		},function(data){
			$("#conten_list").html(data);
		    var windowh=$(window).height();
		    var lefth3=$(".ht_left").height();
		    var righth=$(".back_right").height();
		    if(righth>windowh){
		    	$(".ht_left").css("height",righth);
		    	$(".sidebar_info").css("height",righth-188);
		    }
		})
	}else{
		layer.alert('该功能暂未开发', {
			  icon: 1,
			  skin: 'layer-ext-moon' //该皮肤由layer.seaning.com友情扩展。关于皮肤的扩展规则，去这里查阅
			})
	}
}
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
