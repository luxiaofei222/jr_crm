// JavaScript Document
$(".question_others").mouseenter(function() {
	$(".qiehuan").show();
})
$(".question_others").mouseleave(function() {
	$(".qiehuan").hide();
})
$(".que_nav ul li").click(function() {
	$(this).addClass("on").siblings().removeClass("on");
})

$(".mo_ni ul li").click(function() {
	$(this).addClass("on_type").siblings().removeClass("on_type");
})
$(".shiti ul li").click(function() {
	$(this).addClass("on_number").siblings().removeClass("on_number");
})
$(".chapterMenu").hover(function() {
	$(this).toggleClass("is-hover");
})

$(".zhangjie_content li .list-items .nth-1").click(function() {
	$(this).parent().next().slideToggle();
	var val = $(this).find("i").attr("class").replace(/\s+/g, "");
	if (val == "iconiconfonticon-yuanquanjiahao") {
		$(this).find("i").removeClass("icon-yuanquanjiahao");
		$(this).find("i").addClass("icon-yuanquanjianhao");
		$(this).find("i").css("color", "#999");
	} else {
		$(this).find("i").removeClass("icon-yuanquanjianhao");
		$(this).find("i").addClass("icon-yuanquanjiahao");
		$(this).find("i").css("color", "#69a5eb");
	}
})
$(".zhangjie_content li .list-items1 .nth-5").click(
		function() {
			$(this).parent().next().slideToggle();
			var val1 = $(this).children(".is-sub").find("i").attr("class");
			if (val1 == "icon iconfont icon-yuanquanjiahao") {
				$(this).children(".is-sub").find("i").removeClass(
						"icon-yuanquanjiahao");
				$(this).children(".is-sub").find("i").addClass(
						"icon-yuanquanjianhao");
				$(this).children(".is-sub").find("i").css("color", "#999");
			} else {
				$(this).children(".is-sub").find("i").removeClass(
						"icon-yuanquanjianhao");
				$(this).children(".is-sub").find("i").addClass(
						"icon-yuanquanjiahao");
				$(this).children(".is-sub").find("i").css("color", "#69a5eb");
			}
		})
$(function() {
	$(".question_others").mouseenter(function() {
		$(".qiehuan").show();
	})
	$(".question_others").mouseleave(function() {
		$(".qiehuan").hide();
	})
	$(".que_nav ul li").click(function() {
		$(this).addClass("on").siblings().removeClass("on");
		var index = $(this).index();
		$(".que_content").eq(index).show().siblings(".que_content").hide();
	})
	// 练习记录tab切换
	$(".records_left li").click(
			function() {
				$(this).addClass("records_active").siblings().removeClass(
						"records_active");
				// var index1=$(this).index();
				// $(".records_right").eq(index1).show().siblings(".records_right").hide();
			})
	// 练习记录中的章节、真题、模拟的tab切换
	$(".records_topul li").click(
			function() {
				$(this).addClass("records_active").siblings().removeClass(
						"records_active");
				// var index2=$(this).index();
				// $(".records_right_bottom").eq(index2).show().siblings(".records_right_bottom").hide();
			})
	// 划过2016教材版本出现
	$(".width_diff").hover(function() {
		$(".width_diff_info").show();
	}, function() {
		$(".width_diff_info").hide();
	})

	// $(".icon-star2").click(function(){
	// $(this).toggleClass("collect_active");
	// var val2=$(this).attr("class");
	// if(val2=="icon-star2"){
	// $(this).next("em").html("收藏本题");
	// }else{
	// $(this).next("em").html("取消收藏");
	// }
	// })
})
// 添加收藏
function collection_question(obj, chapter_recourd_id) {
	if ($.trim($(obj).children("em").text()) == "收藏本题") {
		$.post("/front_chapter/collection_question.html", {
			'chapter_recourd_id' : chapter_recourd_id,
			'type' : "章节练习"
		}, function(data) {
			if (data == 1) {
				$(obj).children("i").toggleClass("collect_active");
				$(obj).children("em").html("取消收藏");
			}
		})
	} else {
		quxiao_question_collection(obj, chapter_recourd_id);
	}
}
// 取消收藏
function quxiao_question_collection(obj, chapter_recourd_id) {
	if ($.trim($(obj).children("em").text()) == "取消收藏") {
		$.post("/front_chapter/delte_collection_question.html", {
			'chapter_recourd_id' : chapter_recourd_id
		}, function(data) {
			if (data == 1) {
				$(obj).children("i").toggleClass("collect_active");
				$(obj).children("em").html("收藏本题");
			}
		})
	} else {
		collection_question(obj, chapter_recourd_id)
	}
}
// 调用layui的layer
// 纠错反馈的弹框
$(function() {
	layui.use('layer', function() {
		var layer = layui.layer;
		// //点击添加弹出层事件
		// $('.btn-report-error').on('click', function(){
		// layer.open({
		// type: 1,
		// title: ['试题纠错反馈'],
		// area: ['630px', '400px'],
		// shadeClose: true, //点击遮罩关闭
		// content: $('.jiucuofankui')
		// });
		// })
	});
});
// 提交答案的弹框
$(function() {
	layui.use('layer', function() {
		var layer = layui.layer;
		// 点击添加弹出层事件
		$('.btn').on('click', function() {
			layer.open({
				type : 1,
				title : [ '提交答案' ],
				area : [ '563px', '283px' ],
				shadeClose : true, // 点击遮罩关闭
				content : $('.tjanswer')
			});
		})
	});
});
// 关闭弹窗
function close_layer() {
	layer.closeAll();
}
$(function() {
	$(".textarea2").keyup(function() {
		var lenmax = 150;
		var lentxt = $(".textarea2").val().length;
		lenmax = lenmax - lentxt;
		if (lenmax < 0) {
			$(".tips").css("color", "red");
		} else {
			$(".tips").css("color", "#ff6c00");
		}
		;
		$(".num").html(lenmax);
	})
	// 默认有答案的变灰-单选题
	$('.single_choices').each(function(i) {
		if ($(this).attr("pid") == 'on') {
			var hui = $(this).attr("hid");
			$("#hui" + hui).css("background", "#e2e2e2");// 答题后让左侧模块变灰
		}
	});
	// 默认有答案的变灰-多选题
	$('.multi_choices').each(function(i) {
		if ($(this).attr("pid") == 'on') {
			var hui = $(this).attr("hid");
			$("#hui" + hui).css("background", "#e2e2e2");// 答题后让左侧模块变灰
		}
	});
	// 默认有答案的变灰-案例题
	$('.anlikuang').each(function(i) {
		if ($(this).val() != '' && (this).val() != null) {
			var hui = $(this).attr("hid");
			$("#hui" + hui).css("background", "#e2e2e2");// 答题后让左侧模块变灰
		}
	});
	var question_number=$("#zongtishu").val();
	var number_text=$("textarea[pid='on']").length;//案例题答题的数量
	var number_xuanze = $("ul[pid='on']").length;//选择题答题的数量
	$(".js-count-finished").html(number_xuanze+number_text);// 答题卡
	
	$("#shengyu_number").html(question_number - (number_xuanze+number_text));// 未答题数量
	
	// 点击只看错题
//	var scroll=new SideBar({
//	    sidebar:$('.numbers'),
//	    items:$(".exam-main").find(".collect_nei")
//	});
	$(".check_wrong").click(function(){
		if( $(this).prop("checked")){
			 $(".collect_correct").hide();
		}else{
			 $(".collect_correct").show();
		}
	})
})
// 禁用右键、文本选择功能、复制按键
 $(document).bind("contextmenu",function(){return false;});
 $(document).bind("selectstart",function(){return false;});
// 开始做题后右侧时间div固定
$(window).scroll(function() {
	var height = $(window).scrollTop();
	var left1 = ($(window).width() - 1200) / 2 + 760;
	if (height >= 480) {
		$(".exam-side").css({
			"position" : "fixed",
			"z-index" : "800",
			"top" : "0px",
			"left" : left1
		});
	} else {
		$(".exam-side").css({
			"position" : "absolute",
			"z-index" : "800",
			"top" : "265px",
			"left" : "760px"
		});
	}
});
$(window).resize(function() {
	var height = $(window).scrollTop();
	var left1 = ($(window).width() - 1200) / 2 + 760;
	if (height >= 480) {
		$(".exam-side").css({
			"position" : "fixed",
			"z-index" : "800",
			"top" : "0px",
			"left" : left1
		});
	} else {
		$(".exam-side").css({
			"position" : "absolute",
			"z-index" : "800",
			"top" : "265px",
			"left" : "760px"
		});
	}
});

// 点击选项选中选项,在查看解析和错题记录中是不能出现此点击事件的，因为选项不能进行选择了。
// 多选
function xuan_zhong_duoxuan(obj, chapter_question_id, question_number) {
	var jishiqi = $("#jishiqi").attr("state");
	if (jishiqi == 'on') {
		if ($("#biliduo" + chapter_question_id).attr("pid") == 'on') {
			var check_number = $('input[name="' + chapter_question_id
					+ '"]:checked').length
			if (check_number == 0) {
				var hui = $("#biliduo" + chapter_question_id).attr("hid");
				$("#hui" + hui).css("background", "#fff");// 取消答题变白
				$("#biliduo" + chapter_question_id).attr("pid", "off");
			}
		} else {
			var hui = $("#biliduo" + chapter_question_id).attr("hid");
			$("#hui" + hui).css("background", "#e2e2e2");// 答题后让左侧模块变灰
			$("#biliduo" + chapter_question_id).attr("pid", "on");
		}
		$(obj).prev().toggleClass("collect_select");
		var number = $("ul[pid='on']").length;
		$("#jindu_number").text(number);
		$(".js-count-finished").html(number);// 答题卡
		$("#shengyu_number").html(question_number - number);// 未答题数量
		var precent = (number / question_number) * 100 + "%";
		$("#jindu").width(precent);
	} else {
		layer.msg("您现在是暂停状态！");
	}

}
//案例简答题输入答案
function shuru_anli_daan(number,obj,question_number){
	var anliti_answer = $(obj).val();
	if (anliti_answer != "" && anliti_answer != null) {
		$(obj).attr("anliid", "on");
	}else{
		$(obj).attr("anliid", "off");
	}
		var anli_xiaoti=$("textarea[anliid='on']").length;//案例简答题小题答题的数量
		if(anli_xiaoti>0){
			$(obj).parent().siblings(".collect_title").attr("pid", "on");
			var anlijiandanumber=$("div[pid='on']").length;//案例简答题数量
			var number_text=$("textarea[pid='on']").length;//案例题答题的数量
			var number_xuanze = $("ul[pid='on']").length;//选择题答题的数量
			$(".js-count-finished").html(number_xuanze+number_text+anlijiandanumber);// 答题卡
			$("#shengyu_number").html(question_number - (number_xuanze+number_text+anlijiandanumber));// 未答题数量
			$("#hui" + number).css("background", "#e2e2e2");// 答题后让左侧模块变灰
		}else{
			$(obj).parent().siblings(".collect_title").attr("pid", "off");
			var anlijiandanumber=$("div[pid='on']").length;//案例简答题数量
			var number_text=$("textarea[pid='on']").length;//案例题答题的数量
			var number_xuanze = $("ul[pid='on']").length;//选择题答题的数量
			$(".js-count-finished").html(number_xuanze+number_text+anlijiandanumber);// 答题卡
			$("#shengyu_number").html(question_number - (number_xuanze+number_text+anlijiandanumber));// 未答题数量
			$("#hui" + number).css("background", "#fff");
		}
}
//选中技能选择题
function jineng_xuanze(number,obj,question_number){
	var beixuanzhongduo=$(obj).find("input[type=checkbox]:checked").length;
	var beixuanzhongdan=$(obj).find("input[type=radio]:checked").length;
	var number_xuanzhong =beixuanzhongdan+beixuanzhongduo;
	if(number_xuanzhong>0){
		$(obj).attr("pid", "on");
		var anlijiandanumber=$("div[pid='on']").length;//案例简答题数量
		var number_text=$("textarea[pid='on']").length;//案例题答题的数量
		var number_xuanze = $("div[pid='on']").length;//选择题答题的数量
		$(".js-count-finished").html(number_xuanze+number_text);// 答题卡
		$("#shengyu_number").html(question_number - (number_xuanze+number_text+anlijiandanumber));// 未答题数量
		$("#hui" + number).css("background", "#e2e2e2");// 答题后让左侧模块变灰
	} else {
		$(obj).attr("pid", "off");
		var anlijiandanumber=$("div[pid='on']").length;//案例简答题数量
		var number_text=$("textarea[pid='on']").length;//案例题答题的数量
		var number_xuanze = $("div[pid='on']").length;//选择题答题的数量
		$(".js-count-finished").html(number_xuanze+number_text);// 答题卡
		$("#shengyu_number").html(question_number - (number_xuanze+number_text+anlijiandanumber));// 未答题数量
		$("#hui" + number).css("background", "#fff");// 答题后让左侧模块变灰
	}
}
function jineng_duoxuan(obj,chapter_question_id){
	var jishiqi=$("#jishiqi").attr("state");
	if(jishiqi=='on'){
		if($("#jinneng"+chapter_question_id).attr("pid")=='on'){
			var check_number=$('input[name="'+chapter_question_id+'"]:checked').length
			if(check_number==0){
				var hui=$("#jinneng"+chapter_question_id).attr("hid");
				$("#jinneng"+chapter_question_id).attr("pid","off");
			}
		}else{
			var hui=$("#jinneng"+chapter_question_id).attr("hid");
			$("#jinneng"+chapter_question_id).attr("pid","on");
		}
		$(obj).prev().toggleClass("collect_select");
	}else{
		layer.msg("您现在是暂停状态！");
	}
}

function jineng_danxuan(obj,chapter_question_id){
	var jishiqi=$("#jishiqi").attr("state");
	var bili=$("#jinneng"+chapter_question_id).attr("pid");
	if(jishiqi=='on'){
		if(bili=='on'){
			$("#jinneng"+chapter_question_id).attr("pid","on");
		}else{
			$("#jinneng"+chapter_question_id).attr("pid","on");
		}
		var hui=$("#jinneng"+chapter_question_id).attr("hid");
		$("#hui"+hui).css("background","#e2e2e2");//答题后让左侧模块变灰
		$(obj).prev().addClass("collect_select");
		$(obj).parent().siblings().children("input").each(function(i) {
			$(this).prev().removeClass("collect_select");
		});
	//跳到下一个题号
		if($("#zidongxiayiti").prop("checked")){//判断是否设置自动跳转
			var top_val=$(obj).parent().parent().parent().next().attr("class");
			if(typeof(top_val)!='undefined'){
				 var off=$(obj).parent().parent().parent().next().offset().top;
				 $('html,body').finish().animate({
				    scrollTop:off
				  },200);
			}
		}
	}else{
		layer.msg("您现在是暂停状态！");
	}
}
// 单选+点击单选后自动跳转到下一题
function xuan_zhong(obj, chapter_question_id, question_number) {
	var jishiqi = $("#jishiqi").attr("state");
	var bili = $("#bilidan" + chapter_question_id).attr("pid");
	if (jishiqi == 'on') {
		if (bili == 'on') {
			$("#bilidan" + chapter_question_id).attr("pid", "on");
		} else {
			$("#bilidan" + chapter_question_id).attr("pid", "on");
		}
		var hui = $("#bilidan" + chapter_question_id).attr("hid");
		$("#hui" + hui).css("background", "#e2e2e2");// 答题后让左侧模块变灰
		$(obj).prev().addClass("collect_select");
		$(obj).parent().siblings().children("input").each(function(i) {
			$(this).prev().removeClass("collect_select");
		});
		var number = $("ul[pid='on']").length;
		$("#jindu_number").text(number);
		$(".js-count-finished").html(number);// 答题卡
		$("#shengyu_number").html(question_number - number);// 未答题数量
		var precent = (number / question_number) * 100 + "%";
		$("#jindu").width(precent);
		// 跳到下一个题号
		if ($("#zidongxiayiti").prop("checked")) {// 判断是否设置自动跳转
			var top_val = $(obj).parent().parent().parent().next()
					.attr("class");
			if (typeof (top_val) != 'undefined') {
				var off = $(obj).parent().parent().parent().next().offset().top;
				$('html,body').finish().animate({
					scrollTop : off
				}, 200);
			}
		}
	} else {
		layer.msg("您现在是暂停状态！");
	}
}
// 案例题输入答案
function shuru_daan(number, obj,question_number) {
	var anliti_answer = $(obj).val();
	if (anliti_answer != "" && anliti_answer != null) {
		$(obj).attr("pid", "on");
		var number_text=$("textarea[pid='on']").length;//案例题答题的数量
		var number_xuanze = $("ul[pid='on']").length;//选择题答题的数量
		$(".js-count-finished").html(number_xuanze+number_text);// 答题卡
		$("#shengyu_number").html(question_number - (number_xuanze+number_text));// 未答题数量
		$("#hui" + number).css("background", "#e2e2e2");// 答题后让左侧模块变灰
	} else {
		$(obj).attr("pid", "off");
		var number_text=$("textarea[pid='on']").length;//案例题答题的数量
		var number_xuanze = $("ul[pid='on']").length;//选择题答题的数量
		$(".js-count-finished").html(number_xuanze+number_text);// 答题卡
		$("#shengyu_number").html(question_number - (number_xuanze+number_text));// 未答题数量
		$("#hui" + number).css("background", "#fff");// 答题后让左侧模块变灰
	}
}

// 设置自动跳转到上次做题位置
function to_first_null() {
	if ($("#zidongshangci").prop("checked")) {
		$('.exam-main ul').each(function() {
			var pid = $(this).attr("pid");
			if (pid == "off") {
				var off = $(this).offset().top - 130;
				$('html,body').finish().animate({
					scrollTop : off
				}, 200);
				return false;
			}
		})
	}
}
// 历年真题点击提醒跳转
function change_question_type(num, obj) {
	$(obj).addClass("active").siblings().removeClass("active");
	var off1 = $(".well" + num).offset().top;
	$('html,body').finish().animate({
		scrollTop : off1
	}, 200);
}
// 跳转到指定的序号
function jump_question_number(number, obj) {
	$("span[name='cour_color']").removeClass("cur2");
	$(obj).addClass("cur2");
	var off3 = $("#xuhao_tiaozhuan" + number).offset().top;
	$('html,body').finish().animate({
		scrollTop : off3 - 20
	}, 200);
}
// 全屏出现遮罩层，显示动画加载
/*
 * function cover(){ $(".coverfull").show(); }
 */