$(function() {
	// 课程推荐板块默认最新课程
	$.post("/sc_coursevideo/get_recommend_course_video.html", {
		'type' : 1
	}, function(data) {
		$("#course_recommed_list").html(data);
	})
	$(function(){
		layui.use('layer', function(){
		  	var layer = layui.layer;
		});  
	});
})
$(function() {
	// 点击章节
	$(".zhangjie").click(function() {
		$(this).next(".chapters").slideToggle();
		$(this).toggleClass("zhankai");
	});
	var is_my_course=$("#is_my_course").val();
	$(".chapters ul li").click(function() {
		var isjinzhi=$(this).attr("jid");
		if(is_my_course==1){
			$(this).addClass("play");
			$(".kj_mc").not(this).removeClass("play");

			$(this).children("div").removeClass("hide");
			$(".kj_mc").not(this).children("div").addClass("play");
		}else{
			if(isjinzhi==0){
				$(this).addClass("play");
				$(".kj_mc").not(this).removeClass("play");

				$(this).children("div").removeClass("hide");
				$(".kj_mc").not(this).children("div").addClass("play");
			}
		}
	});
});

$(function() {
	var is_my_course=$("#is_my_course").val();
	$(".chapters ul li span").click(function() {
		if(is_my_course==1){
			$(this).children().removeClass("hide");
			$("span.s-playing").not(this).children().addClass("hide");
		}
		
	});
});

$(function() {
	var $div_li = $("div.tab_menu ul li");
	$div_li.click(function() {
		$(this).addClass("on") // 当前<li>元素高亮
		.siblings().removeClass("on"); // 去掉其它同辈<li>元素的高亮
		var index = $div_li.index(this); // 获取当前点击的<li>元素 在 全部li元素中的索引。
		$("div.tab_box > div") // 选取子节点。不选取子节点的话，会引起错误。如果里面还有div
		.eq(index).show() // 显示 <li>元素对应的<div>元素
		.siblings().hide(); // 隐藏其它几个同辈的<div>元素
	})

});

// 页码

$(".search_fenye li").click(
		function() {
			var index1 = $(this).index();
			$(".search_fenye li").eq(index1).addClass("fenye_green").siblings()
					.removeClass("fenye_green");
		})

// 获取视频三级小节
/*
 * function get_sub_course_video(video_id,obj){
 * $.post("/sc_coursevideo/get_sanji_video.html",{ 'video_id':video_id, 'type':0
 * },function(data){ $("#sub_course_list").html(data);
 * $(obj).next(".chapters").slideToggle(); $(obj).toggleClass("zhankai"); }) }
 */
// 目录章节三级小节
function get_mulu_course_video(video_id, obj) {
	$.post("/sc_coursevideo/get_sanji_video.html", {
		'video_id' : video_id,
		'type' : 1
	}, function(data) {
		$("#mulu_xiaojie_list"+video_id).html(data);
		$(obj).children(".kejian").slideToggle();
		$(obj).children(".line").slideToggle();
		$(obj).toggleClass("open");
	})
}
// 加入我的课程
function save_my_course(video_id, obj) {
	$.post("/sc_mycourse/save_mycourse.html", {
		'video_id' : video_id 
	}, function(data) {
		if (data == 3) {
			get_login();
		} else if (data == 1) {
			layer.msg("您已经将该课程加入我的课程！");
			$(obj).hide();
			$("#yijiaru").show();
		} else {
			layer.msg("加入失败");
		}
	})
}
// 加入购物车
function save_my_shoping(video_id, obj) {
	$.post("/sc_myshoping/save_myshoping.html", {
		'video_id' : video_id
	}, function(data) {
		if (data == 3) {
			get_login();
		} else if (data == 1) {
			layer.msg("您已经将该课程加入我的购物车！");
			$.post("/sc_myshoping/get_my_shoping_number.html", function(data) {
				$("#myshoping_number").html(data);
			})
			$(obj).hide();
			$("#gouwuche").show();
		} else {
			layer.msg("加入失败");
		}
	})
}
//保存视频播放记录
var record_id = 0;
var long_time=0;
var watchTime=0;
$(function() {
	var is_my_course=$("#is_my_course").val();
	var isjinzhi = $("#sub_course_list1").children("ul").children("li:first").attr("jid");
	if(is_my_course!=1){//如果不是我的课程
		if(isjinzhi==0){
			$("#sub_course_list1").show();
			$("#sub_course_list1").children("ul").children("li:first").addClass("play");
			var id = $("#sub_course_list1").children("ul").children("li:first").val();
			$("#playing" + id).removeClass("hide");
			$.post("/sc_coursevideo/get_sanji_diyige_player.html", {
				'video_id' : id
			}, function(data) {
				if (data != null) {
					var flashvars = {
						f : "/sc_coursevideo/get_sanji_diyige_player.html?video_id="+id,
						s : 1,
						a : 88,
						c : 0,
						p : 1,
						e : 0,
						loaded : 'loadedHandler'
					};
					var video = [ data + '->video/mp4' ];
					CKobject.embed('/ckplayer/ckplayer/ckplayer.swf', 'video',
							'ckplayer_a1', '100%', '100%', false, flashvars, video);
				}
			})
			}else{
				$("#video").hide();
				$(".finish").show();
			}
	}else{//是我的课程
//		$("#sub_course_list1").show();
//		$("#sub_course_list1").children("ul").children("li:first").addClass("play");
//		var id = $("#sub_course_list1").children("ul").children("li:first").val();
//		$("#playing" + id).removeClass("hide");
		var bofang_id=0;
		$(".chapters").children("ul").children("li").each(
				function(){
						if($(this).attr("bid")!=0){
							bofang_id=$(this).attr("bid");
							var id=$(this).parent("ul").parent(".chapters").attr("sid");
							$("#sub_course_list"+id).show();
							$(this).addClass("play");
							$("#playing" + bofang_id).removeClass("hide");
							return;
						}
				  }
		)
		if(bofang_id==0||typeof(bofang_id)=="undefined"){
			$("#sub_course_list1").show();
			$("#sub_course_list1").children("ul").children("li:first").addClass("play");
			bofang_id = $("#sub_course_list1").children("ul").children("li:first").val();
			$("#playing" + bofang_id).removeClass("hide");
		}
		//保存视频观看记录
		$.post("/video_record/save_video_record.html",{
			"video_id":bofang_id
		},function(data){
			if(data.record_id!=0){//成功
				//layer.msg(data.record_id);
				record_id=data.record_id;
			}
		})
		$.post("/sc_coursevideo/get_sanji_diyige_player.html", {
			'video_id' : bofang_id
		}, function(data) {
			if (data != null) {
				$.ajax({
					type : "POST",
					url : "/video_record/get_video_time.html?video_id="
						+ bofang_id,
					async : false,
					success : function(data) {
						long_time=data;
						watchTime=data;
					}
				});
				var flashvars = {
						f : "/sc_coursevideo/get_sanji_diyige_player.html?video_id="+bofang_id,
						s : 1,
						a : 88,
						c : 0,
						p : 1,
						e : 0,
						g: long_time,
						loaded : 'loadedHandler'
					};
				var video = [ data + '->video/mp4' ];
				CKobject.embed('/ckplayer/ckplayer/ckplayer.swf', 'video',
						'ckplayer_a1', '100%', '100%', false, flashvars, video);
			}
		})
	}
})
var setT=null;
//var isnum = true;
function loadedHandler() {
	CKobject.getObjectById('ckplayer_a1').addListener('paused', 'pausedHandler');
}
//var watchTime=0;
function pausedHandler(t) {// 试看时间监听事件
	if(setT){
	      window.clearInterval(setT);
	    }
	    if(!t){
	      setT=window.setInterval(setFunction,1000);
	    }
}
function setFunction(){
    watchTime= parseInt(watchTime)+1;
    if(watchTime%5==0){
    	//layer.msg(record_id);
    	//记录视频观看的时间点
		$.post("/video_record/record_video_time.html",{
			"record_time":watchTime,
			"record_id":record_id
		},function(data){
			if(data==1){//成功
				//layer.msg("记录成功");
				//record_id=data.record_id;
			}
		})
    }
    var course_id=$("#course_id").val();
	if(course_id==31||course_id==32){//一级建造师和二级建造师没有试听
		var is_my_course=$("#is_my_course").val();
		var is_shoufei = $("#is_shoufei").val();
		if(is_my_course!=1){//判断是否是我的课程，是我的课程则直接可以观看
		if (is_shoufei == '付费') {
			CKobject.getObjectById('ckplayer_a1').videoPause();// 暂停播放
			CKobject.getObjectById('ckplayer_a1').removeListener('time',
					'timeHandler');
			$("#video").hide();
			$(".finish").show();
			// 这时是判断当播放时间大于10，并且没有执行过试看判断的情况下进行判断。
			// 这里可以弹出登陆层或弹幕提示层
			//isnum = false;
		} else {
			//isnum = false;
		}
		}else{
			//isnum = false;
		}
	}else{
		if (watchTime > 720) {//原来是试看2分钟现在改成试看12分钟
			var is_my_course=$("#is_my_course").val();
			var is_shoufei = $("#is_shoufei").val();
			if(is_my_course!=1){//判断是否是我的课程，是我的课程则直接可以观看
			if (is_shoufei == '付费') {
				CKobject.getObjectById('ckplayer_a1').videoPause();// 暂停播放
				CKobject.getObjectById('ckplayer_a1').removeListener('time',
						'timeHandler');
				$("#video").hide();
				$(".finish").show();
				// 这时是判断当播放时间大于10，并且没有执行过试看判断的情况下进行判断。
				// 这里可以弹出登陆层或弹幕提示层
				//isnum = false;
			} else {
				//isnum = false;
			}
			}else{
				//isnum = false;
			}
		}
	}
	//return isnum;
   // CKobject._K_('nowTime').innerHTML='当前观看时间：'+watchTime;
  }
// 播放结束后自动播放下一节
function playerstop() {
	var id = $(".play").next("li").val();
	$("li").removeClass("play");
	$("#xiaojie" + id).addClass("play");
	if (typeof (id) == "undefined") {
		$("li").removeClass("play");
		layer.msg("请选择下一章进行播放！");
	} else {
		$.post("/sc_coursevideo/get_sanji_diyige_player.html", {
			'video_id' : id
		}, function(data) {
			var flashvars = {
					f : "/sc_coursevideo/get_sanji_diyige_player.html?video_id="+id,
					s : 1,
					a : 88,
					c : 0,
					p : 1,
					e : 0,
					loaded : 'loadedHandler'
				};
			var video = [ data + '->video/mp4' ];
			CKobject.embed('/ckplayer/ckplayer/ckplayer.swf', 'video',
					'ckplayer_a1', '100%', '100%', false, flashvars, video);
		})
	}
}
// 点击播放视频
function get_video_player_url(video_id,is_jinzhi) {
	if(is_jinzhi==0){
		$.post("/video_record/save_video_record.html",{
			"video_id":video_id
		},function(data){
			if(data.record_id!=0){//成功
				//layer.msg(data.record_id);
				watchTime=0;
				record_id=data.record_id;
			}
		});
		$.post("/sc_coursevideo/get_sanji_diyige_player.html", {
			'video_id' : video_id
		}, function(data) {
			$.ajax({
				type : "POST",
				url : "/video_record/get_video_time.html?video_id="
					+ video_id,
				async : false,
				success : function(data) {
					long_time=data;
					watchTime=data;
				}
			});
			var flashvars = {
					f : "/sc_coursevideo/get_sanji_diyige_player.html?video_id="+video_id,
					s : 1,
					a : 88,
					c : 0,
					p : 1,
					e : 0,
					g : long_time,
					loaded : 'loadedHandler'
				};
			var video = [ data + '->video/mp4' ];
			CKobject.embed('/ckplayer/ckplayer/ckplayer.swf', 'video',
					'ckplayer_a1', '100%', '100%', false, flashvars, video);
		})
	}else{
		layer.msg("禁止点击播放！")
	}
}
//获取用户评价
function get_comment(id){
	var pageNumber = 1;
	var limit = 8;
	$.post("/sc_comment/get_video_comment.html", {
		'video_id' : id,
		'pageNumber' : pageNumber,
		'limit' : limit
	}, function(data) {
		$("#comment_list").html(data);
	})
}
//点击课件
function get_ware(obj){
	$(obj).siblings().removeClass("now");
	$(obj).addClass("now");
	$(".kc_lb").addClass("hide");
	$(".kc_xz").removeClass("hide");
}
function get_video_list(obj){
	$(obj).siblings().removeClass("now");
	$(obj).addClass("now");
	$(".kc_xz").addClass("hide");
	$(".kc_lb").removeClass("hide");
}
//立即购买
function video_pay(video_id){
	location.href="/sc_pay/get_pay_main.html?des_video_id="+video_id
	//layer.msg("暂时不支持线上购买！")
}
