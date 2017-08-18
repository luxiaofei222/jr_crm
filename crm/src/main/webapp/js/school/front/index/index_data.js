/* 首页数据加载 */
$(function() {
	if(isIE()){
		location.href="/upgrade.jsp";
	}
	// 课程列表分类
	$.post("/sc_course_class/get_course_first_menu.html", {
		'course_parent_id' : 0
	}, function(data) {
		$("#course_menu_list").html(data);
	})
	// banner大图
	$.post("/sc_ba/get_banner_list.html", function(data) {
		$("#banner_list").html(data);
		setTimeout(function(){
		// 新闻
		$.post("/sc_news/get_news_list.html", function(data) {
			$("#news_list").html(data);
		})
		//资讯顶部列表
		$.post("/front_info/get_course_info_top.html", function(data) {
			$(".zixun_listinfo").html(data);
		})
		//课程推荐板块默认最新课程
		$.post("/sc_coursevideo/get_recommend_course_video.html",
				{'type':0,'yangshi':1}, function(data) {
			$("#course_list_index").html(data);
		})
		//左侧菜单
		$.post("/sc_course_class/left_course_menu.html", function(data) {
			$("#menu").html(data);
		})
		//主体内容
		$.post("/sc_course_class/get_course_content_menu.html", function(data) {
			$("#content").html(data);
		})
		// 友情链接
		$.post("/sc_cooper/get_cooer_list.html", function(data) {
			$("#cooper_list").html(data);
		})
		},500);
	})
	
})
//课程推荐板块课程
function tuijian_course(yangshi,type){
	$("#course_list_index").html("<div class='page1'><div class='circle-loader'><div class='circle-line'><div class='circle circle-blue'></div>" +
			"<div class='circle circle-blue'></div><div class='circle circle-blue'></div>" +
			"</div><div class='circle-line'><div class='circle circle-yellow'></div><div class='circle circle-yellow'></div>" +
			"<div class='circle circle-yellow'></div></div><div class='circle-line'><div class='circle circle-red'></div>" +
			"<div class='circle circle-red'></div><div class='circle circle-red'></div></div><div class='circle-line'>" +
			"<div class='circle circle-green'></div><div class='circle circle-green'></div><div class='circle circle-green'></div>" +
			"</div></div></div>");
	$.post("/sc_coursevideo/get_recommend_course_video.html",{
		'video_type':type,
		'type':0,
		'yangshi':yangshi
	}, function(data) {
		$("#course_list_index").html(data);
	})
}
// 禁用提示
function jinyong() {
	alert("此功能暂未开发！")
}
//判断是否是IE浏览器
function isIE() { //ie?
	 if (!!window.ActiveXObject || "ActiveXObject" in window){
		 
		 return true;
	 }else{
		 return false;
		 }
	 }
