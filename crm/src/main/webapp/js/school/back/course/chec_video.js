$(function() {
		$(".zhangjie").click(function() {
			$(this).next(".chapters").slideToggle();
			$(this).toggleClass("zhankai");
		});
	});
	$(function() {
		$(".chapters ul li").click(function() {
			$(this).addClass("play");
			$("li.kj_mc").not(this).removeClass("play");
		});
	});
	$(function() {
		$(".chapters ul li span").click(function() {
			$(this).children().removeClass("hide");
			$("span.s-playing").not(this).children().addClass("hide");
		});
	});
	$(function() {
		var $div_li = $("div.tab_menu ul li");
		$div_li.click(function() {
			$(this).addClass("on") //当前<li>元素高亮
			.siblings().removeClass("on"); //去掉其它同辈<li>元素的高亮
			var index = $div_li.index(this); // 获取当前点击的<li>元素 在 全部li元素中的索引。
			$("div.tab_box > div") //选取子节点。不选取子节点的话，会引起错误。如果里面还有div 
			.eq(index).show() //显示 <li>元素对应的<div>元素
			.siblings().hide(); //隐藏其它几个同辈的<div>元素
		})
	});
	//自动播放第一个视频
	$(function() {
		var id = $("#jie_ul1").children("li:first").val();
		$.post("/sc_coursevideo/get_sanji_diyige_player.html", {
			'video_id' : id
		}, function(data) {
			if (data != null) {
				var flashvars = {
					f : data,
					s : 0,
					c : 0,
					p : 1,
					e : 0
				};
				var video = [ data + '->video/mp4' ];
				CKobject.embed('/ckplayer/ckplayer/ckplayer.swf', 'video',
						'ckplayer_a1', '100%', '100%', false, flashvars, video);
			}
		})
	})
	
	// 播放结束后自动播放下一节
	function playerstop() {
		var id = $(".play").next("li").val();
		$("li.kj_mc").removeClass("play");
		$("#xiaojie" + id).addClass("play");
		if (typeof (id) == "undefined") {
			alert("请选择下一章进行播放！")
		} else {
			$.post("/sc_coursevideo/get_sanji_diyige_player.html", {
				'video_id' : id
			}, function(data) {
				var flashvars = {
					f : data,
					s : 0,
					c : 0,
					p : 1,
					e : 0
				};
				var video = [ data + '->video/mp4' ];
				CKobject.embed('/ckplayer/ckplayer/ckplayer.swf', 'video',
						'ckplayer_a1', '100%', '100%', false, flashvars, video);
			})
		}
	}
	
	// 点击播放视频
	function get_video_player_url(video_id) {
		$.post("/sc_coursevideo/get_sanji_diyige_player.html", {
			'video_id' : video_id
		}, function(data) {
			var flashvars = {
				f : data,
				s : 0,
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
	//获取评论内容
	function get_comment(video_id){
		var pageNumber=1;
		var limit = 10;
		$.post("/back_comment/get_video_comment_list.jr", {
			'video_id' : video_id,
			'pageNumber' : pageNumber,
			'limit' : limit
		}, function(data) {
			$("#comment_list").html(data);
		})
	}
	
	//跳转分页
	function comment_jump_page(page,id){
		pageNumber=page;
		var limit = 10;
		$.post("/back_comment/get_video_comment_list.jr", {
			'video_id' : id,
			'pageNumber' : pageNumber,
			'limit' : limit
		}, function(data) {
			$("#comment_list").html(data);
		})
	}
	//返回
	function fanhui(){
		video_jump_page(1)
	}