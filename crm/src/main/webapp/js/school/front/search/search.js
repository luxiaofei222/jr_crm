$(function(){
	$(".search_course_head div").click(function(){
		var index=$(this).index();
		$(".search_course_head div").eq(index).addClass("search_green").siblings().removeClass("search_green");
	})
	$("#jiangxu").click(function(){
		$(".price1").attr("src","/images/school/front/search/price1.png");
		$(".price2").attr("src","/images/school/front/search/price3.png");
	})
	$("#shengxu").click(function(){
		$(".price1").attr("src","/images/school/front/search/price1.png");
		$(".price2").attr("src","/images/school/front/search/price3.png");
	})
	$(".course_head2").click(function(){
		$(".price1").attr("src","/images/school/front/search/price.png");
		$(".price2").attr("src","/images/school/front/search/price2.png");
	})
	$(".course_head1").click(function(){
		$(".price1").attr("src","/images/school/front/search/price.png");
		$(".price2").attr("src","/images/school/front/search/price2.png");
	})
	$(".search_fenye li").click(function(){
		var index1=$(this).index();
		$(".search_fenye li").eq(index1).addClass("fenye_green").siblings().removeClass("fenye_green");
	})
})
//搜索
function get_all_course_video(){
	location.href="/sc_search/get_all_video.html";
}
//排序
var pageNumber = 1;
var limit = 16;
function zonghe_search(name,paixu){
		$.post("/sc_search/get_zonghe_search_video.html",{
			'name':name,
			'paixu':paixu,
			'pageNumber' : pageNumber,
			'limit' : limit
		},function(data){
			if(paixu!=null&&paixu!=""&&paixu!="zuire"){
				if(paixu=="DESC"){
					$("#jiangxu").removeClass("search_green");
					$("#jiangxu").hide();
					$("#shengxu").addClass("search_green");
					$("#shengxu").show();
				}else{
					$("#shengxu").removeClass("search_green");
					$("#shengxu").hide();
					$("#jiangxu").addClass("search_green");
					$("#jiangxu").show();
				}
			}
			$("#sc_search_list").html(data);
		})
}

//跳转页面
function course_jump_page(page,name,paixu){
	pageNumber = page;
	$.post("/sc_search/get_zonghe_search_video.html",{
		'name':name,
		'paixu':paixu,
		'pageNumber' : pageNumber,
		'limit' : limit
	},function(data){
		if(paixu!=null&&paixu!=""){
			if(paixu=="DESC"){
				$("#jiangxu").hide();
				$("#shengxu").show();
			}else{
				$("#shengxu").hide();
				$("#jiangxu").show();
			}
		}
		$("#sc_search_list").html(data);
	})
}